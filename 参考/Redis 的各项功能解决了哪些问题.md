> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://www.cnblogs.com/linianhui/p/what-problem-does-redis-solve.html

> 先看一下 Redis 是一个什么东西。[官方简介](https://redis.io/topics/introduction)解释到：Redis 是一个基于 BSD 开源的项目，是一个把结构化的数据放在内存中的一个存储系统，你可以把它作为数据库，缓存和消息中间件来使用。同时支持 strings，lists，hashes，sets，sorted sets，bitmaps，hyperloglogs 和 geospatial indexes 等数据类型。它还内建了复制，lua 脚本，LRU，事务等功能，通过 redis sentinel 实现高可用，通过 redis cluster 实现了自动分片。以及事务，发布 / 订阅，自动故障转移等等。

综上所述，Redis 提供了丰富的功能，初次见到可能会感觉眼花缭乱，这些功能都是干嘛用的？都解决了什么问题？什么情况下才会用到相应的功能？那么下面从零开始，一步一步的演进来粗略的解释下。

# 1 从零开始

最初的需求非常简单，我们有一个提供热点新闻列表的 api：http://api.xxx.com/hot-news，api 的消费者抱怨说每次请求都要 2 秒左右才能返回结果。

随后我们就着手于如何提升一下 api 消费者感知的性能，很快最简单粗暴的第一个方案就出来了：为 API 的响应加上基于 HTTP 的缓存控制 cache-control:max-age=600 ，即让消费者可以缓存这个响应十分钟。如果 api 消费者如果有效的利用了响应中的缓存控制信息，则可以有效的改善其感知的性能（10 分钟以内）。但是还有 2 个弊端：第一个是在缓存生效的 10 分钟内，api 消费者可能会得到旧的数据；第二个是如果 api 的客户端无视缓存直接访问 API 依然是需要 2 秒，治标不治本呐。

# 2 基于本机内存的缓存

为了解决调用 API 依然需要 2 秒的问题，经过排查，其主要原因在于使用 SQL 获取热点新闻的过程中消耗了将近 2 秒的时间，于是乎，我们又想到了一个简单粗暴的解决方案，即把 SQL 查询的结果直接缓存在当前 api 服务器的内存中（设置缓存有效时间为 1 分钟）。后续 1 分钟内的请求直接读缓存，不再花费 2 秒去执行 SQL 了。假如这个 api 每秒接收到的请求时 100 个，那么一分钟就是 6000 个，也就是只有前 2 秒拥挤过来的请求会耗时 2 秒，后续的 58 秒中的所有请求都可以做到即使响应，而无需再等 2 秒的时间。

其他 API 的小伙伴发现这是个好办法，于是很快我们就发现 API 服务器的内存要爆满了。。。

# 3 服务端的 Redis

在 API 服务器的内存都被缓存塞满的时候，我们发现不得不另想解决方案了。最直接的想法就是我们把这些缓存都丢到一个专门的服务器上吧，把它的内存配置的大大的。然后我们就盯上了 redis。。。至于如何配置部署 redis 这里不解释了，redis 官方有详细的介绍。随后我们就用上了一台单独的服务器作为 Redis 的服务器，API 服务器的内存压力得以解决。

## 3.1 持久化（Persistence）

单台的 Redis 服务器一个月总有那么几天心情不好，心情不好就罢工了，导致所有的缓存都丢失了（redis 的数据是存储在内存的嘛）。虽然可以把 Redis 服务器重新上线，但是由于内存的数据丢失，造成了缓存雪崩，API 服务器和数据库的压力还是一下子就上来了。所以这个时候 Redis 的持久化功能就派上用场了，可以缓解一下缓存雪崩带来的影响。redis 的持久化指的是 redis 会把内存的中的数据写入到硬盘中，在 redis 重新启动的时候加载这些数据，从而最大限度的降低缓存丢失带来的影响。

## 3.2 哨兵（Sentinel）和复制（Replication）

Redis 服务器毫无征兆的罢工是个麻烦事。那么怎办办？答曰：备份一台，你挂了它上。那么如何得知某一台 redis 服务器挂了，如何切换，如何保证备份的机器是原始服务器的完整备份呢？这时候就需要 Sentinel 和 Replication 出场了。Sentinel 可以管理多个 Redis 服务器，它提供了监控，提醒以及自动的故障转移的功能；Replication 则是负责让一个 Redis 服务器可以配备多个备份的服务器。Redis 也是利用这两个功能来保证 Redis 的高可用的。此外，Sentinel 功能则是对 Redis 的发布和订阅功能的一个利用。

## 3.3 集群（Cluster）

单台服务器资源的总是有上限的，CPU 资源和 IO 资源我们可以通过主从复制，进行读写分离，把一部分 CPU 和 IO 的压力转移到从服务器上。但是内存资源怎么办，主从模式做到的只是相同数据的备份，并不能横向扩充内存；单台机器的内存也只能进行加大处理，但是总有上限的。所以我们就需要一种解决方案，可以让我们横向扩展。**最终的目的既是把每台服务器只负责其中的一部分，让这些所有的服务器构成一个整体，对外界的消费者而言，这一组分布式的服务器就像是一个集中式的服务器一样（之前在解读 REST 的博客中解释过分布式于基于网络的差异：[基于网络应用的架构](http://www.cnblogs.com/linianhui/p/rest_network-based-software-architecture.html#auto_id_6)）。**

在 Redis 官方的分布式方案出来之前，有 twemproxy 和 codis 两种方案，这两个方案总体上来说都是依赖 proxy 来进行分布式的，也就是说 redis 本身并不关心分布式的事情，而是交由 twemproxy 和 codis 来负责。而 redis 官方给出的 cluster 方案则是把分布式的这部分事情做到了每一个 redis 服务器中，使其不再需要其他的组件就可以独立的完成分布式的要求。**我们这里不关心这些方案的优略，我们关注一下这里的分布式到底是要处理那些事情? 也就是 twemproxy 和 codis 独立处理的处理分布式的这部分逻辑和 cluster 集成到 redis 服务的这部分逻辑到底在解决什么问题？**

如我们前面所说的，一个分布式的服务在外界看来就像是一个集中式的服务一样。那么要做到这一点就面临着有一个问题需要解决：既是增加或减少分布式服务中的服务器的数量，对消费这个服务的客户端而言应该是无感的；那么也就意味着客户端不能穿透分布式服务，把自己绑死到某一个台的服务器上去，因为一旦如此，你就再也无法新增服务器，也无法进行故障替换。解决这个问题有两个路子：第一个路子最直接，那就是我加一个中间层来隔离这种具体的依赖，即 twemproxy 采用的方式，让所有的客户端只能通过它来消费 redsi 服务，通过它来隔离这种依赖（但是你会发现 twermproxy 会成为一个单点），这种情况下每台 redis 服务器都是独立的，它们之间彼此不知对方的存在；第二个路子是让 redis 服务器知道彼此的存在，通过重定向的机制来引导客户端来完成自己所需要的操作，比如客户端链接到了某一个 redis 服务器，说我要执行这个操作，redis 服务器发现自己无法完成这个操作，那么就把能完成这个操作的服务器的信息给到客户端，让客户端去请求另外的一个服务器，这时候你就会发现每一个 redis 服务器都需要保持一份完整的分布式服务器信息的一份资料，不然它怎么知道让客户端去找其他的哪个服务器来执行客户端想要的操作呢。

上面这一大段解释了这么多，不知有没有发现不管是第一个路子还是第二个路子，都有一个共同的东西存在，**那就是分布式服务中所有服务器以及其能提供的服务的信息。这些信息无论如何也是要存在的**，区别在于第一个路子是把这部分信息单独来管理，用这些信息来协调后端的多个独立的 redis 服务器；第二个路子则是让每一个 redis 服务器都持有这份信息，彼此知道对方的存在，来达成和第一个路子一样的目的，优点是不再需要一个额外的组件来处理这部分事情。

Redis Cluster 的具体实现细节则是采用了 Hash 槽的概念，即预先分配出来 16384 个槽：在客户端通过对 Key 进行 CRC16（key）% 16384 运算得到对应的槽是哪一个；在 redis 服务端则是每个服务器负责一部分槽，当有新的服务器加入或者移除的时候，再来迁移这些槽以及其对应的数据，同时每个服务器都持有完整的槽和其对应的服务器的信息，这就使得服务器端可以进行对客户端的请求进行重定向处理。

# 4 客户端的 Redis

上面的第三小节主要介绍的是 Redis 服务端的演进步骤，解释了 Redis 如何从一个单机的服务，进化为一个高可用的、去中心化的、分布式的存储系统。这一小节则是关注下客户端可以消费的 redis 服务。

## 4.1 数据类型

redis 支持丰富的数据类型，从最基础的 string 到复杂的常用到的数据结构都有支持：

1.  string：最基本的数据类型，二进制安全的字符串，最大 512M。
2.  list：按照添加顺序保持顺序的字符串列表。
3.  set：无序的字符串集合，不存在重复的元素。
4.  sorted set：已排序的字符串集合。
5.  hash：key-value 对的一种集合。
6.  bitmap：更细化的一种操作，以 bit 为单位。
7.  hyperloglog：基于概率的数据结构。

这些众多的数据类型，主要是为了支持各种场景的需要，当然每种类型都有不同的时间复杂度。其实这些复杂的数据结构相当于之前我在《解读 REST》这个系列博客[基于网络应用的架构风格](http://www.cnblogs.com/linianhui/p/rest_network-based-software-architecture-style.html#auto_id_15)中介绍到的**远程数据访问（Remote Data Access = RDA）**的具体实现，即通过在服务器上执行一组标准的操作命令，在服务端之间得到想要的缩小后的结果集，从而简化客户端的使用，也可以提高网络性能。比如如果没有 list 这种数据结构，你就只能把 list 存成一个 string，客户端拿到完整的 list，操作后再完整的提交给 redis，会产生很大的浪费。

## 4.2 事务

上述数据类型中，每一个数据类型都有独立的命令来进行操作，很多情况下我们需要一次执行不止一个命令，而且需要其同时成功或者失败。redis 对事务的支持也是源自于这部分需求，即支持一次性按顺序执行多个命令的能力，并保证其原子性。

## 4.3 Lua 脚本

在事务的基础上，如果我们需要在服务端一次性的执行更复杂的操作（包含一些逻辑判断），则 lua 就可以排上用场了（比如在获取某一个缓存的时候，同时延长其过期时间）。redis 保证 lua 脚本的原子性，一定的场景下，是可以代替 redis 提供的事务相关的命令的。相当于[基于网络应用的架构风格](http://www.cnblogs.com/linianhui/p/rest_network-based-software-architecture-style.html#auto_id_18)中介绍到的**远程求值（Remote Evluation = REV）**的具体实现。

## 4.4 管道

因为 redis 的客户端和服务器的连接时基于 TCP 的， 默认每次连接都时只能执行一个命令。管道则是允许利用一次连接来处理多条命令，从而可以节省一些 tcp 连接的开销。管道和事务的差异在于管道是为了节省通信的开销，但是并不会保证原子性。

## 4.5 分布式锁

官方推荐采用 Redlock 算法，即使用 string 类型，加锁的时候给的一个具体的 key，然后设置一个随机的值；取消锁的时候用使用 lua 脚本来先执行获取比较，然后再删除 key。具体的命令如下：

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "")

<pre>SET resource_name my_random_value NX PX 30000

if redis.call("get",KEYS[1]) == ARGV[1] then
    return redis.call("del",KEYS[1])
else
    return 0
end
</pre>

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "")

# 总结

本篇着重从抽象层面来解释下 redis 的各项功能以及其存在的目的，而没有关心其具体的细节是什么。从而可以聚焦于其解决的问题，依据抽象层面的概念可以使得我们在特定的场景下选择更合适的方案，而非局限于其技术细节。

以上均是笔者个人的一些理解，如果不当之处，欢迎指正。

# 参考

Redis 文档：[https://github.com/antirez/redis-doc](https://github.com/antirez/redis-doc)

Redis 简介：[https://redis.io/topics/introduction](https://redis.io/topics/introduction)

Redis 持久化（Persistence）：[https://redis.io/topics/persistence](https://redis.io/topics/persistence)

Redis 发布 / 订阅（Pub/Sub）：[https://redis.io/topics/pubsub](https://redis.io/topics/pubsub)

Redis 哨兵（Sentinel）：[https://redis.io/topics/sentinel](https://redis.io/topics/sentinel)

Redis 复制（Replication）：[https://redis.io/topics/replication](https://redis.io/topics/replication)

Redis 集群（cluster）：[https://redis.io/topics/cluster-tutorial](https://redis.io/topics/cluster-tutorial)

RedIs 事务（Transaction）：[https://redis.io/topics/transactions](https://redis.io/topics/transactions)

Redis 数据类型（data types）：[https://redis.io/topics/data-types-intro](https://redis.io/topics/data-types-intro)

Redis 分布式锁：[https://redis.io/topics/distlock](https://redis.io/topics/distlock)

Redis 管道（pipelining ）：[https://redis.io/topics/pipelining](https://redis.io/topics/pipelining)

Redis Lua Script：[https://redis.io/commands/eval](https://redis.io/commands/eval)