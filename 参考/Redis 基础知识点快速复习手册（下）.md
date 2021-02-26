> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483773&idx=1&sn=6bbd589e174b5d6f8bb3d6b242eb6132&chksm=fbdb18fcccac91eaa8c9d941c1d3f8d2f3874841c417d30e3ccd185b1494d51ea2fdf384c876&scene=21#wechat_redirect

前言
==

本文快速回顾了 Redis 书籍、博客以及本人面试中遇到的基础知识点，方便大家快速回顾知识。

用作面试复习，事半功倍。

分为上下篇，下篇主要内容为：  

*   持久化
    

*   RDB 快照持久化
    
*   AOF 持久化
    

*   发布与订阅
    
*   事务
    
*   事件
    

*   文件事件
    
*   时间事件
    
*   事件的调度与执行
    

*   复制（增强读性能）
    

*   连接过程
    
*   主从链
    

*   Sentinel（哨兵）
    
*   分片（增强写性能）
    

*   P2P 架构
    
*   为什么是 16384？
    
*   客户端存储路由信息
    
*   无损扩容
    
*   主从复制
    
*   Redis 集群相对单机在功能上限制
    
*   Redis-cluster （Redis 分布式）
    

*   应用实例：一个简单的论坛系统分析
    

*   文章信息
    
*   点赞功能
    
*   对文章进行排序
    

*   Redis 经典面试题
    

*   Redis 有哪些数据结构？
    
*   使用过 Redis 分布式锁么，它是什么回事？
    
*   假如 Redis 里面有 1 亿个 key，其中有 10w 个 key 是以某个固定的已知的前缀开头的，如果将它们全部找出来？
    
*   使用过 Redis 做异步队列么，你是怎么用的？
    
*   如果对方追问 redis 如何实现延时队列？
    
*   如果有大量的 key 需要设置同一时间过期，一般需要注意什么？
    
*   Redis 如何做持久化的？
    
*   对方追问 bgsave 的原理是什么？
    
*   Pipeline 有什么好处，为什么要用 pipeline？
    
*   Redis 的同步机制了解么？
    
*   是否使用过 Redis 集群，集群的原理是什么？
    

面试知识点复习手册
=========

**全复习手册文章导航：**

点击公众号下方技术推文——面试冲刺

**已发布知识点复习手册**

[Java 基础知识点面试手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=1&sn=de2751593468f470902b698c19f8987f&chksm=fbdb18d3ccac91c56939e55cd1f0ca1b4753fd178d229440ecbf89752f5e0d518acd453e2497&scene=21#wechat_redirect)

[Java 基础知识点面试手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=2&sn=5610afab774b4114110c993fd0fdc43d&chksm=fbdb18d3ccac91c5e3d2978e3a780b14d09e97c997a5c50410d1adb1930da76f40238d8f409d&scene=21#wechat_redirect)  

[Java 容器（List、Set、Map）知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483743&idx=1&sn=cc38aab9429905ddc757b529a386d1dd&chksm=fbdb18deccac91c8d0be8b3ae0e4266bb08ead73a2e57f2c977705e7b26ceaaec7aff5d5c67c&scene=21#wechat_redirect)  

[Java 容器（List、Set、Map）知识点快速复习手册（中）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483753&idx=1&sn=74b8180dc1a1804c355174ed34e6e33d&chksm=fbdb18e8ccac91fe3ce31ed9713bf23598e7f98dcb6d5a22b79aa92b1c773134bfebe71fbbca&scene=21#wechat_redirect)  

[Java 容器（List、Set、Map）知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483762&idx=1&sn=1f121db6552a2e77d53c500fa812fc6c&chksm=fbdb18f3ccac91e58229dd3efd09c876722d58863c2b6ff6d444b0825a955a776ced947d8470&scene=21#wechat_redirect)

[Redis 基础知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483768&idx=1&sn=ea83244e4b9f1d6f912ca0aadab74466&chksm=fbdb18f9ccac91efe9e32704ac3d69cf1ad390ddae0f169c118ea8b9da91c6e4e6e849677a6d&scene=21#wechat_redirect)

持久化
===

Redis 是内存型数据库，为了保证数据在断电后不会丢失，需要将内存中的数据持久化到硬盘上。

https://my.oschina.net/davehe/blog/174662

RDB 快照持久化
---------

将某个时间点的所有数据都存放到硬盘上。

可以将快照复制到其它服务器从而创建具有相同数据的服务器副本。

如果系统发生故障，将会丢失最后一次创建快照之后的数据。

如果数据量很大，保存快照的时间会很长。

AOF 持久化
-------

将写命令添加到 AOF 文件（Append Only File）的末尾。

对硬盘的文件进行写入时，写入的内容首先会被存储到缓冲区，然后由操作系统决定什么时候将该内容同步到硬盘，用户可以调用 file.flush() 方法请求操作系统尽快将缓冲区存储的数据同步到硬盘。可以看出写入文件的数据不会立即同步到硬盘上，在将写命令添加到 AOF 文件时，要根据需求来保证何时同步到硬盘上。

有以下同步选项：

| 选项 | 同步频率 |
| --- | --- |
| always | 每个写命令都同步 |
| everysec | 每秒同步一次 |
| no | 让操作系统来决定何时同步 |

*   always 选项会严重减低服务器的性能；
    
*   everysec 选项比较合适，可以保证系统奔溃时只会丢失一秒左右的数据，并且 Redis 每秒执行一次同步对服务器性能几乎没有任何影响；
    
*   no 选项并不能给服务器性能带来多大的提升，而且也会增加系统奔溃时数据丢失的数量。
    

**随着服务器写请求的增多，AOF 文件会越来越大。Redis 提供了一种将 AOF 重写的特性，能够去除 AOF 文件中的冗余写命令, 使得 AOF 文件的体积不会超出保存数据集状态所需的实际大小。**

**如果 AOF 文件出错了，怎么办？**

服务器可能在程序正在对 AOF 文件进行写入时停机， 如果停机造成了 AOF 文件出错（corrupt）， 那么 Redis 在重启时会拒绝载入这个 AOF 文件， 从而确保数据的一致性不会被破坏。

发布与订阅
=====

订阅者订阅了频道之后，发布者向频道发送字符串消息会被所有订阅者接收到。

某个客户端使用 SUBSCRIBE 订阅一个频道，其它客户端可以使用 PUBLISH 向这个频道发送消息。

发布与订阅模式和观察者模式有以下不同：

*   观察者模式中，**观察者和主题都知道对方的存在**；而在发布与订阅模式中，发布者与订阅者不知道对方的存在，它们之间通过频道进行通信。
    
*   **观察者模式是同步的**，当事件触发时，主题会去调用观察者的方法；而发布与订阅模式是异步的；
    

事务
==

http://www.runoob.com/redis/redis-transactions.html

事务中的多个命令被一次性发送给服务器，而不是一条一条发送，这种方式被称为**流水线**，它可以减少客户端与服务器之间的网络通信次数从而提升性能。

**Redis 最简单的事务实现方式是使用 MULTI 和 EXEC 命令将事务操作包围起来。**

它先以 MULTI 开始一个事务， 然后将多个命令入队到事务中， 最后由 EXEC 命令触发事务， 一并执行事务中的所有命令

**单个 Redis 命令的执行是原子性的**，但 Redis **没有在事务上增加任何维持原子性的机制**，所以 Redis 事务的执行并不是原子性的。

事务可以理解为一个打包的批量执行脚本，但批量指令并非原子化的操作，**中间某条指令的失败不会导致前面已做指令的回滚，也不会造成后续的指令不做。**

事件
==

Redis 服务器是一个事件驱动程序。

文件事件
----

服务器通过套接字与客户端或者其它服务器进行通信，文件事件就是对套接字操作的抽象。

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYrSS5EuA5MMd87iaUhNRwcyfzuENzLRUROCldYg9KmMAyqw2BwJc31kF0YrLd6aHiasZzfbbsGXkCA/640?wx_fmt=png)image.png

Redis 基于 Reactor 模式开发了自己的网络时间处理器，使用 I/O 多路复用程序来同时监听多个套接字，并将到达的时间传送给文件事件分派器，分派器会根据套接字产生的事件类型调用响应的时间处理器。

时间事件
----

服务器有一些操作需要在给定的时间点执行，时间事件是对这类定时操作的抽象。

时间事件又分为：

*   定时事件：是让一段程序在指定的时间之内执行一次；
    
*   周期性事件：是让一段程序每隔指定时间就执行一次。
    

Redis 将所有时间事件都放在一个无序链表中，通过遍历整个链表查找出已到达的时间事件，并调用响应的事件处理器。

事件的调度与执行
--------

服务器需要不断监听文件事件的套接字才能得到待处理的文件事件，但是不能一直监听，否则时间事件无法在规定的时间内执行，因此监听时间应该根据距离现在最近的时间事件来决定。

事件调度与执行由 aeProcessEvents 函数负责，伪代码如下：

```
 1def aeProcessEvents():
 2    # 获取到达时间离当前时间最接近的时间事件
 3    time_event = aeSearchNearestTimer()
 4    # 计算最接近的时间事件距离到达还有多少毫秒
 5    remaind_ms = time_event.when - unix_ts_now()
 6    # 如果事件已到达，那么 remaind_ms 的值可能为负数，将它设为 0
 7    if remaind_ms < 0:
 8        remaind_ms = 0
 9    # 根据 remaind_ms 的值，创建 timeval
10    timeval = create_timeval_with_ms(remaind_ms)
11    # 阻塞并等待文件事件产生，最大阻塞时间由传入的 timeval 决定
12    aeApiPoll(timeval)
13    # 处理所有已产生的文件事件
14    procesFileEvents()
15    # 处理所有已到达的时间事件
16    processTimeEvents()


```

将 aeProcessEvents 函数置于一个循环里面，加上初始化和清理函数，就构成了 Redis 服务器的主函数，伪代码如下：

```
1def main():
2    # 初始化服务器
3    init_server()
4    # 一直处理事件，直到服务器关闭为止
5    while server_is_not_shutdown():
6        aeProcessEvents()
7    # 服务器关闭，执行清理操作
8    clean_server()


```

从事件处理的角度来看，服务器运行流程如下：

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYrSS5EuA5MMd87iaUhNRwcy19WTu2yJfZl9MHEcx2t3LypGyNelzN9aDLtxMTqgJDajibwibibcxaOdA/640?wx_fmt=png)image.png

复制（增强读性能）
=========

通过使用 slaveof host port 命令来让一个服务器成为另一个服务器的从服务器。

**一个从服务器只能有一个主服务器，并且不支持主主复制。**

连接过程
----

1.  **主服务器创建快照文件，发送给从服务器，并在发送期间使用缓冲区记录执行的写命令。快照文件发送完毕之后，开始向从服务器发送存储在缓冲区中的写命令；**
    
2.  **从服务器丢弃所有旧数据**，载入主服务器发来的快照文件，之后从服务器开始接受主服务器发来的写命令；
    
3.  **主服务器每执行一次写命令，就向从服务器发送相同的写命令。**
    

主从链
---

随着负载不断上升，主服务器可能无法很快地更新所有从服务器，或者重新连接和重新同步从服务器将导致系统超载。为了解决这个问题，可以创建一个中间层来分担主服务器的复制工作。中间层的服务器是最上层服务器的从服务器，又是最下层服务器的主服务器。

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYrSS5EuA5MMd87iaUhNRwcyViajfh6ug4tX7dM3tibicPgKM6OBknGsGAYb58tECfjPiaXmUCIS7xCmhA/640?wx_fmt=png)image.png

Sentinel（哨兵）
============

Sentinel（哨兵）可以监听主服务器，并在主服务器进入下线状态时，自动从从服务器中选举出新的主服务器。

分片（增强写性能）
=========

分片是将数据划分为多个部分的方法，可以将数据存储到多台机器里面.

主要有三种分片方式：

*   客户端分片：客户端使用一致性哈希等算法决定键应当分布到哪个节点。
    
*   代理分片：将客户端请求发送到代理上，由代理转发请求到正确的节点上。
    
*   **服务器分片：Redis Cluster。**
    

Redis-cluster （Redis 分布式）
-------------------------

https://blog.csdn.net/chunlongyu/article/details/53339288

但从 Redis 3.0 开始，引入了 Redis Cluster，从此 Redis 进入了真正的 “分布式集群 “时代。

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYrSS5EuA5MMd87iaUhNRwcyrsF4mdgB96aF2aTjlYXbuo5ObZkicqalGa58Lia4heVtwSJ3Z4MpiaHlA/640?wx_fmt=png)image.png

### P2P 架构

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYrSS5EuA5MMd87iaUhNRwcyk3NElXDYOU1IFsabdUaX5LascMibzTic7PGJ3sib7BlC9ARynal15Jmxw/640?wx_fmt=png)image.png

### 为什么是 16384？

很显然，我们需要维护节点和槽之间的映射关系，每个节点需要知道自己有哪些槽，并且需要在结点之间传递这个消息。

为了节省存储空间，**每个节点用一个 Bitmap 来存放其对应的槽**:  
2k = 2*1024*8 = 16384，也就是说，每个结点用 2k 的内存空间，总共 16384 个比特位，就可以存储该结点对应了哪些槽。**然后这 2k 的信息，通过 Gossip 协议，在结点之间传递。**

### 客户端存储路由信息

**对于客户端来说，维护了一个路由表：每个槽在哪台机器上。这样存储 (key, value) 时，根据 key 计算出槽，再根据槽找到机器。**

### 无损扩容

虽然 Hash 环（Memcached）可以减少扩容时失效的 key 的数量，但毕竟有丢失。而在 redis-cluster 中，**当新增机器之后，槽会在机器之间重新分配，同时被影响的数据会自动迁移，从而做到无损扩容。**

这里可以结合补充知识点 - 缓存 - 一致性哈希来一起理解。虚拟槽改变的是槽的分配，一致性哈希则会使旁边的节点 key 失效。

### 主从复制

redis-cluster 也引入了 master-slave 机制，从而提供了 fail-over 机制，这很大程度上解决了 “缓存雪崩 “的问题。关于这个，后面有机会再详细阐述。

https://blog.csdn.net/men_wen/article/details/72853078

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYrSS5EuA5MMd87iaUhNRwcyULJzdZJKLQgooMnibT6uP9kqncGVhBOICibE9c4V75Hic1NEyLVunpiaRw/640?wx_fmt=png)image.png

### Redis 集群相对单机在功能上有一定限制。

key 批量操作支持有限。如：MSET``MGET，目前**只支持具有相同 slot 值的 key 执行批量操作**。

key 事务操作支持有限。**支持多 key 在同一节点上的事务操作，不支持分布在多个节点的事务功能。**

key 作为数据分区的最小粒度，因此**不能将一个大的键值对象映射到不同的节点。如：hash、list。**

不支持多数据库空间。单机下 Redis 支持 16 个数据库，**集群模式下只能使用一个数据库空间，即 db 0。**

**复制结构只支持一层，不支持嵌套树状复制结构。**

例子：一个简单的论坛系统分析
==============

该论坛系统功能如下：

*   可以发布文章；
    
*   可以对文章进行点赞；
    
*   在首页可以按文章的发布时间或者文章的点赞数进行排序显示。
    

文章信息
----

文章包括标题、作者、赞数等信息，在关系型数据库中很容易构建一张表来存储这些信息，在 Redis 中可以使用 HASH 来存储每种信息以及其对应的值的映射。

Redis 没有关系型数据库中的表这一概念来将同种类型的数据存放在一起，而是使用命名空间的方式来实现这一功能。键名的前面部分存储命名空间，后面部分的内容存储 ID，通常使用 : 来进行分隔。例如下面的 HASH 的键名为 article:92617，其中 article 为命名空间，ID 为 92617。

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYrSS5EuA5MMd87iaUhNRwcyt5J80JExp0KI6pibmHpJkcZAp16RvqKVS3icCbnczSNZ0M3xMrvRMjGg/640?wx_fmt=png)image.png

点赞功能
----

当有用户为一篇文章点赞时，除了要对该文章的 votes 字段进行加 1 操作，还必须记录该用户已经对该文章进行了点赞，防止用户点赞次数超过 1。可以建立文章的已投票用户集合来进行记录。

为了节约内存，规定一篇文章发布满一周之后，就不能再对它进行投票，而文章的已投票集合也会被删除，可以为文章的已投票集合设置一个一周的过期时间就能实现这个规定。

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYrSS5EuA5MMd87iaUhNRwcyzKGwX1e0ZrBelH5V9c4w3gVlrrTPc4SempYyQ2RfapdnIic2MVx7LDg/640?wx_fmt=png)image.png

对文章进行排序
-------

为了按发布时间和点赞数进行排序，可以建立一个文章发布时间的有序集合和一个文章点赞数的有序集合。（下图中的 score 就是这里所说的点赞数；下面所示的有序集合分值并不直接是时间和点赞数，而是根据时间和点赞数间接计算出来的）

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYrSS5EuA5MMd87iaUhNRwcyH8aSNEIibj6uk0FrbQJfvtGZsDMHw9Zpibrrsb26VMo1jAiaS1JlnvmNg/640?wx_fmt=png)image.png

Redis 经典面试题
===========

**Redis 有哪些数据结构？**
------------------

字符串 String、字典 Hash、列表 List、集合 Set、有序集合 SortedSet。

如果你是 Redis 中高级用户，还需要加上下面几种数据结构 HyperLogLog、Geo、Pub/Sub。

如果你说还玩过 Redis Module，像 BloomFilter，RedisSearch，Redis-ML，面试官得眼睛就开始发亮了。

**使用过 Redis 分布式锁么，它是什么回事？**
---------------------------

先拿 setnx 来争抢锁，抢到之后，再用 expire 给锁加一个过期时间防止锁忘记了释放。

这时候对方会告诉你说你回答得不错，然后接着问如果在 setnx 之后执行 expire 之前进程意外 crash 或者要重启维护了，那会怎么样？

这时候你要给予惊讶的反馈：唉，是喔，这个锁就永远得不到释放了。紧接着你需要抓一抓自己得脑袋，故作思考片刻，好像接下来的结果是你主动思考出来的，然后回答：**我记得 set 指令有非常复杂的参数，这个应该是可以同时把 setnx 和 expire 合成一条指令来用的**。对方这时会显露笑容，心里开始默念：摁，这小子还不错。

**假如 Redis 里面有 1 亿个 key，其中有 10w 个 key 是以某个固定的已知的前缀开头的，如果将它们全部找出来？**
-------------------------------------------------------------------

使用 keys 指令可以扫出指定模式的 key 列表。

**对方接着追问：如果这个 redis 正在给线上的业务提供服务，那使用 keys 指令会有什么问题？**

这个时候你要回答 redis 关键的一个特性：**redis 的单线程的**。keys 指令会导致线程阻塞一段时间，线上服务会停顿，直到指令执行完毕，服务才能恢复。这个时候**可以使用 scan 指令**，scan 指令可以无阻塞的提取出指定模式的 key 列表，**但是会有一定的重复概率，在客户端做一次去重就可以了**，但是整体所花费的时间会比直接用 keys 指令长。

**使用过 Redis 做异步队列么，你是怎么用的？**
----------------------------

一般使用 list 结构作为队列，rpush 生产消息，lpop 消费消息。当 lpop 没有消息的时候，要适当 sleep 一会再重试。

如果对方追问可不可以不用 sleep 呢？**list 还有个指令叫 blpop，在没有消息的时候，它会阻塞住直到消息到来。**

如果对方追问能不能生产一次消费多次呢？使用 pub/sub 主题订阅者模式，可以实现 1:N 的消息队列。

**如果对方追问 pub/sub 有什么缺点？**

在消费者下线的情况下，生产的消息会丢失，得使用专业的消息队列如 rabbitmq 等。

如果对方追问 redis 如何实现延时队列？
----------------------

**使用 sortedset，拿时间戳作为 score**，消息内容作为 key 调用 zadd 来生产消息，消费者用 zrangebyscore 指令获取 N 秒之前的数据轮询进行处理。

如果有大量的 key 需要设置同一时间过期，一般需要注意什么？
-------------------------------

如果大量的 key 过期时间设置的过于集中，到过期的那个时间点，redis 可能会出现短暂的卡顿现象。**一般需要在时间上加一个随机值，使得过期时间分散一些。**

Redis 如何做持久化的？
--------------

bgsave 做镜像全量持久化，aof 做增量持久化。

因为 bgsave 会耗费较长时间，不够实时，在停机的时候会导致大量丢失数据，所以需要 aof 来配合使用。**在 redis 实例重启时，会使用 bgsave 持久化文件重新构建内存，再使用 aof 重放近期的操作指令来实现完整恢复重启之前的状态。**

对方追问那如果突然机器掉电会怎样？取决于 aof 日志 sync 属性的配置，如果不要求性能，在每条写指令时都 sync 一下磁盘，就不会丢失数据。但是在高性能的要求下每次都 sync 是不现实的，一般都使用定时 sync，比如 1s1 次，这个时候最多就会丢失 1s 的数据。

对方追问 bgsave 的原理是什么？
-------------------

你给出两个词汇就可以了，fork 和 cow。fork 是指 redis 通过创建子进程来进行 bgsave 操作，cow 指的是 copy on write，子进程创建后，父子进程共享数据段，父进程继续提供读写服务，写脏的页面数据会逐渐和子进程分离开来。

Pipeline 有什么好处，为什么要用 pipeline？
------------------------------

可以将多次 IO 往返的时间缩减为一次，前提是 pipeline 执行的指令之间没有因果相关性。使用 redis-benchmark 进行压测的时候可以发现影响 redis 的 QPS 峰值的一个重要因素是 pipeline 批次指令的数目。

Redis 的同步机制了解么？
---------------

Redis 可以使用主从同步，从从同步。第一次同步时，主节点做一次 bgsave，并同时将后续修改操作记录到内存 buffer，待完成后将 rdb 文件全量同步到复制节点，复制节点接受完成后将 rdb 镜像加载到内存。加载完成后，再通知主节点将期间修改的操作记录同步到复制节点进行重放就完成了同步过程。

是否使用过 Redis 集群，集群的原理是什么？
------------------------

Redis Sentinal 着眼于高可用，在 master 宕机时会自动将 slave 提升为 master，继续提供服务。

Redis Cluster 着眼于扩展性，在单个 redis 内存不足时，使用 Cluster 进行分片存储。

参考与拓展阅读
=======

*   https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Redis.md
    
*   [【3y】从零单排学 Redis【青铜】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484359&idx=1&sn=0994c6246990b7ad42a2d3f294042316&chksm=ebd742c6dca0cbd0a826ace13f4d4eeff282052f4a97b31654ef1b3b32f991374f5c67a45ae9&scene=21#wechat_redirect)  
    

关注我
===

我是蛮三刀把刀，目前为后台开发工程师。主要关注后台开发，网络安全，Python 爬虫等技术。

来微信和我聊聊：yangzd1102

Github：https://github.com/qqxx6661

### 原创博客主要内容

*   笔试面试复习知识点手册
    
*   Leetcode 算法题解析（前 150 题）
    
*   剑指 offer 算法题解析
    
*   Python 爬虫相关实战
    
*   后台开发相关实战
    

**同步更新以下博客**

**1. Csdn**

http://blog.csdn.net/qqxx6661

拥有专栏：Leetcode 题解（Java/Python）、Python 爬虫开发

**2. 知乎**

https://www.zhihu.com/people/yang-zhen-dong-1/

拥有专栏：码农面试助攻手册

**3. 掘金**

https://juejin.im/user/5b48015ce51d45191462ba55

**4. 简书**

https://www.jianshu.com/u/b5f225ca2376

### 个人公众号：Rude3Knife

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbLlp9yKrq0VbJlyAgSWsGX57bQF0Fiaia8PoX2Dlub9716EiaPibewVTAMm3I7icAXE3qVGFtUtWApQnA/640?wx_fmt=png)个人公众号：Rude3Knife

**如果文章对你有帮助，不妨收藏起来并转发给您的朋友们~**