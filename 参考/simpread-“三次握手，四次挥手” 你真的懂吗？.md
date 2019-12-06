> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://zhuanlan.zhihu.com/p/53374516

记得刚毕业找工作面试的时候，经常会被问到：你知道 “3 次握手，4 次挥手” 吗？这时候我会 “胸有成竹” 地“背诵”前期准备好的“答案”，第一次怎么怎么，第二次…… 答完就没有下文了，面试官貌似也没有深入下去的意思，深入下去我也不懂，皆大欢喜！

作为程序员，要有 “刨根问底” 的精神。知其然，更要知其所以然。这篇文章希望能抽丝剥茧，还原背后的原理。

什么是 “3 次握手，4 次挥手”
-----------------

TCP 是一种面向连接的单播协议，在发送数据前，通信双方必须在彼此间建立一条连接。所谓的 “连接”，其实是客户端和服务器的内存里保存的一份关于对方的信息，如 ip 地址、端口号等。

TCP 可以看成是一种字节流，它会处理 IP 层或以下的层的丢包、重复以及错误问题。在连接的建立过程中，双方需要交换一些连接的参数。这些参数可以放在 TCP 头部。

TCP 提供了一种可靠、面向连接、字节流、传输层的服务，采用三次握手建立一个连接。采用 4 次挥手来关闭一个连接。

TCP 服务模型
--------

在了解了建立连接、关闭连接的 “三次握手和四次挥手” 后，我们再来看下 TCP 相关的东西。

一个 TCP 连接由一个 4 元组构成，分别是两个 IP 地址和两个端口号。一个 TCP 连接通常分为三个阶段：启动、数据传输、退出（关闭）。

当 TCP 接收到另一端的数据时，它会发送一个确认，但这个确认不会立即发送，一般会延迟一会儿。ACK 是累积的，一个确认字节号 N 的 ACK 表示所有直到 N 的字节（不包括 N）已经成功被接收了。这样的好处是如果一个 ACK 丢失，很可能后续的 ACK 就足以确认前面的报文段了。

一个完整的 TCP 连接是双向和对称的，数据可以在两个方向上平等地流动。给上层应用程序提供一种`双工服务`。一旦建立了一个连接，这个连接的一个方向上的每个 TCP 报文段都包含了相反方向上的报文段的一个 ACK。

序列号的作用是使得一个 TCP 接收端可丢弃重复的报文段，记录以杂乱次序到达的报文段。因为 TCP 使用 IP 来传输报文段，而 IP 不提供重复消除或者保证次序正确的功能。另一方面，TCP 是一个字节流协议，绝不会以杂乱的次序给上层程序发送数据。因此 TCP 接收端会被迫先保持大序列号的数据不交给应用程序，直到缺失的小序列号的报文段被填满。

TCP 头部
------

![](https://pic1.zhimg.com/v2-8f5725f163d7f6390a75f3a2d337bc1c_b.jpg)![](https://pic1.zhimg.com/v2-8f5725f163d7f6390a75f3a2d337bc1c_r.jpg)

源端口和目的端口在 TCP 层确定双方进程，序列号表示的是报文段数据中的第一个字节号，ACK 表示确认号，该确认号的发送方期待接收的下一个序列号，即最后被成功接收的数据字节序列号加 1，这个字段只有在 ACK 位被启用的时候才有效。

当新建一个连接时，从客户端发送到服务端的第一个报文段的 SYN 位被启用，这称为 SYN 报文段，这时序列号字段包含了在本次连接的这个方向上要使用的第一个序列号，即初始序列号`ISN`，之后发送的数据是 ISN 加 1，因此 SYN 位字段会`消耗`一个序列号，这意味着使用重传进行可靠传输。而不消耗序列号的 ACK 则不是。

头部长度（图中的数据偏移）以 32 位字为单位，也就是以 4bytes 为单位，它只有 4 位，最大为 15，因此头部最大长度为 60 字节，而其最小为 5，也就是头部最小为 20 字节（可变选项为空）。

ACK —— 确认，使得确认号有效。 RST —— 重置连接（经常看到的 reset by peer）就是此字段搞的鬼。 SYN —— 用于初如化一个连接的序列号。 FIN —— 该报文段的发送方已经结束向对方发送数据。

当一个连接被建立或被终止时，交换的报文段只包含 TCP 头部，而没有数据。

状态转换
----

三次握手和四次挥手的状态转换如下图。

![](https://pic3.zhimg.com/v2-e8aaab48ff996e5cd8a5b39dc450bd6a_b.jpg)![](https://pic3.zhimg.com/v2-e8aaab48ff996e5cd8a5b39dc450bd6a_r.jpg)

为什么要 “三次握手，四次挥手”
----------------

三次握手
----

换个易于理解的视角来看为什么要 3 次握手。

客户端和服务端通信前要进行连接，“3 次握手” 的作用就是`双方都能明确自己和对方的收、发能力是正常的`。

`第一次握手`：客户端发送网络包，服务端收到了。这样服务端就能得出结论：客户端的发送能力、服务端的接收能力是正常的。

`第二次握手`：服务端发包，客户端收到了。这样客户端就能得出结论：服务端的接收、发送能力，客户端的接收、发送能力是正常的。 从客户端的视角来看，我接到了服务端发送过来的响应数据包，说明服务端接收到了我在第一次握手时发送的网络包，并且成功发送了响应数据包，这就说明，服务端的接收、发送能力正常。而另一方面，我收到了服务端的响应数据包，说明我第一次发送的网络包成功到达服务端，这样，我自己的发送和接收能力也是正常的。

`第三次握手`：客户端发包，服务端收到了。这样服务端就能得出结论：客户端的接收、发送能力，服务端的发送、接收能力是正常的。 第一、二次握手后，服务端并不知道客户端的接收能力以及自己的发送能力是否正常。而在第三次握手时，服务端收到了客户端对第二次握手作的回应。从服务端的角度，我在第二次握手时的响应数据发送出去了，客户端接收到了。所以，我的发送能力是正常的。而客户端的接收能力也是正常的。

经历了上面的三次握手过程，客户端和服务端都确认了自己的接收、发送能力是正常的。之后就可以正常通信了。

每次都是接收到数据包的一方可以得到一些结论，发送的一方其实没有任何头绪。我虽然有发包的动作，但是我怎么知道我有没有发出去，而对方有没有接收到呢？

而从上面的过程可以看到，最少是需要三次握手过程的。两次达不到让双方都得出自己、对方的接收、发送能力都正常的结论。其实每次收到网络包的一方至少是可以得到：对方的发送、我方的接收是正常的。而每一步都是有关联的，下一次的 “响应” 是由于第一次的 “请求” 触发，因此每次握手其实是可以得到额外的结论的。比如第三次握手时，服务端收到数据包，表明看服务端只能得到客户端的发送能力、服务端的接收能力是正常的，但是结合第二次，说明服务端在第二次发送的响应包，客户端接收到了，并且作出了响应，从而得到额外的结论：客户端的接收、服务端的发送是正常的。

用表格总结一下：

![](https://pic2.zhimg.com/v2-0e041d18bf194379f89222fdedb07d55_b.png)![](https://pic2.zhimg.com/v2-0e041d18bf194379f89222fdedb07d55_r.jpg)

四次挥手
----

TCP 连接是双向传输的对等的模式，就是说双方都可以同时向对方发送或接收数据。当有一方要关闭连接时，会发送指令告知对方，我要关闭连接了。这时对方会回一个 ACK，此时一个方向的连接关闭。但是另一个方向仍然可以继续传输数据，等到发送完了所有的数据后，会发送一个 FIN 段来关闭此方向上的连接。接收方发送 ACK 确认关闭连接。注意，接收到 FIN 报文的一方只能回复一个 ACK, 它是无法马上返回对方一个 FIN 报文段的，因为结束数据传输的 “指令” 是上层应用层给出的，我只是一个“搬运工”，我无法了解`“上层的意志”`。

“三次握手，四次挥手” 怎么完成？
-----------------

其实 3 次握手的目的并不只是让通信双方都了解到一个连接正在建立，还在于利用数据包的选项来传输特殊的信息，交换初始序列号 ISN。

3 次握手是指发送了 3 个报文段，4 次挥手是指发送了 4 个报文段。注意，SYN 和 FIN 段都是会利用重传进行可靠传输的。

![](https://pic4.zhimg.com/v2-07c065a0321f887ae69e269d8dda9f43_b.jpg)![](https://pic4.zhimg.com/v2-07c065a0321f887ae69e269d8dda9f43_r.jpg)

三次握手
----

1.  客户端发送一个 SYN 段，并指明客户端的初始序列号，即 ISN(c).
2.  服务端发送自己的 SYN 段作为应答，同样指明自己的 ISN(s)。为了确认客户端的 SYN，将 ISN(c)+1 作为 ACK 数值。这样，每发送一个 SYN，序列号就会加 1. 如果有丢失的情况，则会重传。
3.  为了确认服务器端的 SYN，客户端将 ISN(s)+1 作为返回的 ACK 数值。

四次挥手
----

![](https://pic3.zhimg.com/v2-629f51f6f535ebd7683f944707b21d1e_b.jpg)![](https://pic3.zhimg.com/v2-629f51f6f535ebd7683f944707b21d1e_r.jpg)

1. 客户端发送一个 FIN 段，并包含一个希望接收者看到的自己当前的序列号 K. 同时还包含一个 ACK 表示确认对方最近一次发过来的数据。 2. 服务端将 K 值加 1 作为 ACK 序号值，表明收到了上一个包。这时上层的应用程序会被告知另一端发起了关闭操作，通常这将引起应用程序发起自己的关闭操作。 3. 服务端发起自己的 FIN 段，ACK=K+1, Seq=L 4. 客户端确认。ACK=L+1

为什么建立连接是三次握手，而关闭连接却是四次挥手呢？
--------------------------

这是因为服务端在 LISTEN 状态下，收到建立连接请求的 SYN 报文后，把 ACK 和 SYN 放在一个报文里发送给客户端。而关闭连接时，当收到对方的 FIN 报文时，仅仅表示对方不再发送数据了但是还能接收数据，己方是否现在关闭发送数据通道，需要上层应用来决定，因此，己方 ACK 和 FIN 一般都会分开发送。

“三次握手，四次挥手” 进阶
--------------

ISN
---

三次握手的一个重要功能是客户端和服务端交换 ISN(Initial Sequence Number), 以便让对方知道接下来接收数据的时候如何按序列号组装数据。

如果 ISN 是固定的，攻击者很容易猜出后续的确认号。

```
ISN = M + F(localhost, localport, remotehost, remoteport)

```

M 是一个计时器，每隔 4 毫秒加 1。 F 是一个 Hash 算法，根据源 IP、目的 IP、源端口、目的端口生成一个随机数值。要保证 hash 算法不能被外部轻易推算得出。

序列号回绕
-----

因为 ISN 是随机的，所以序列号容易就会超过 2^31-1. 而 tcp 对于丢包和乱序等问题的判断都是依赖于序列号大小比较的。此时就出现了所谓的 tcp 序列号回绕（sequence wraparound）问题。怎么解决？

```
/*
* The next routines deal with comparing 32 bit unsigned ints
* and worry about wraparound (automatic with unsigned arithmetic).
*/
static inline int before(__u32 seq1, __u32 seq2)
{
    return (__s32)(seq1-seq2) < 0;
}

#define after(seq2, seq1) before(seq1, seq2)

```

上述代码是内核中的解决回绕问题代码。**s32 是有符号整型的意思，而** u32 则是无符号整型。序列号发生回绕后，序列号变小，相减之后，把结果变成有符号数了，因此结果成了负数。

```
假设seq1=255， seq2=1（发生了回绕）。
seq1 = 1111 1111 seq2 = 0000 0001
我们希望比较结果是
 seq1 - seq2=
 1111 1111
-0000 0001
-----------
 1111 1110

由于我们将结果转化成了有符号数，由于最高位是1，因此结果是一个负数，负数的绝对值为
 0000 0001 + 1 = 0000 0010 = 2

因此seq1 - seq2 < 0

```

syn flood 攻击
------------

最基本的 DoS 攻击就是利用合理的服务请求来占用过多的服务资源，从而使合法用户无法得到服务的响应。syn flood 属于 Dos 攻击的一种。

如果恶意的向某个服务器端口发送大量的 SYN 包，则可以使服务器打开大量的半开连接，分配 TCB（Transmission Control Block）, 从而消耗大量的服务器资源，同时也使得正常的连接请求无法被相应。当开放了一个 TCP 端口后，该端口就处于 Listening 状态，不停地监视发到该端口的 Syn 报文，一 旦接收到 Client 发来的 Syn 报文，就需要为该请求分配一个 TCB，通常一个 TCB 至少需要 280 个字节，在某些操作系统中 TCB 甚至需要 1300 个字节，并返回一个 SYN ACK 命令，立即转为 SYN-RECEIVED 即半开连接状态。系统会为此耗尽资源。

常见的防攻击方法有：

无效连接的监视释放
---------

监视系统的半开连接和不活动连接，当达到一定阈值时拆除这些连接，从而释放系统资源。这种方法对于所有的连接一视同仁，而且由于 SYN Flood 造成的半开连接数量很大，正常连接请求也被淹没在其中被这种方式误释放掉，因此这种方法属于入门级的 SYN Flood 方法。

延缓 TCB 分配方法
-----------

消耗服务器资源主要是因为当 SYN 数据报文一到达，系统立即分配 TCB，从而占用了资源。而 SYN Flood 由于很难建立起正常连接，因此，当正常连接建立起来后再分配 TCB 则可以有效地减轻服务器资源的消耗。常见的方法是使用 Syn Cache 和 Syn Cookie 技术。

Syn Cache 技术
------------

系统在收到一个 SYN 报文时，在一个专用 HASH 表中保存这种半连接信息，直到收到正确的回应 ACK 报文再分配 TCB。这个开销远小于 TCB 的开销。当然还需要保存序列号。

Syn Cookie 技术
-------------

Syn Cookie 技术则完全不使用任何存储资源，这种方法比较巧妙，它使用一种特殊的算法生成 Sequence Number，这种算法考虑到了对方的 IP、端口、己方 IP、端口的固定信息，以及对方无法知道而己方比较固定的一些信息，如 MSS(Maximum Segment Size，最大报文段大小，指的是 TCP 报文的最大数据报长度，其中不包括 TCP 首部长度。)、时间等，在收到对方 的 ACK 报文后，重新计算一遍，看其是否与对方回应报文中的（Sequence Number-1）相同，从而决定是否分配 TCB 资源。

使用 SYN Proxy 防火墙
----------------

一种方式是防止墙 dqywb 连接的有效性后，防火墙才会向内部服务器发起 SYN 请求。防火墙代服务器发出的 SYN ACK 包使用的序列号为 c, 而真正的服务器回应的序列号为 c', 这样，在每个数据报文经过防火墙的时候进行序列号的修改。另一种方式是防火墙确定了连接的安全后，会发出一个 safe reset 命令，client 会进行重新连接，这时出现的 syn 报文会直接放行。这样不需要修改序列号了。但是，client 需要发起两次握手过程，因此建立连接的时间将会延长。

连接队列
----

在外部请求到达时，被服务程序最终感知到前，连接可能处于 SYN_RCVD 状态或是 ESTABLISHED 状态，但还未被应用程序接受。

![](https://pic2.zhimg.com/v2-c4688fba5db30b31c913f549108c9735_b.jpg)![](https://pic2.zhimg.com/v2-c4688fba5db30b31c913f549108c9735_r.jpg)

对应地，服务器端也会维护两种队列，处于 SYN_RCVD 状态的半连接队列，而处于 ESTABLISHED 状态但仍未被应用程序 accept 的为全连接队列。如果这两个队列满了之后，就会出现各种丢包的情形。

```
查看是否有连接溢出
netstat -s | grep LISTEN

```

半连接队列满了
-------

在三次握手协议中，服务器维护一个半连接队列，该队列为每个客户端的 SYN 包开设一个条目 (服务端在接收到 SYN 包的时候，就已经创建了 request_sock 结构，存储在半连接队列中)，该条目表明服务器已收到 SYN 包，并向客户发出确认，正在等待客户的确认包。这些条目所标识的连接在服务器处于 Syn_RECV 状态，当服务器收到客户的确认包时，删除该条目，服务器进入 ESTABLISHED 状态。

> 目前，Linux 下默认会进行 5 次重发 SYN-ACK 包，重试的间隔时间从 1s 开始，下次的重试间隔时间是前一次的双倍，5 次的重试时间间隔为 1s, 2s, 4s, 8s, 16s, 总共 31s, 称为`指数退避`，第 5 次发出后还要等 32s 才知道第 5 次也超时了，所以，总共需要 1s + 2s + 4s+ 8s+ 16s + 32s = 63s, TCP 才会把断开这个连接。由于，SYN 超时需要 63 秒，那么就给攻击者一个攻击服务器的机会，攻击者在短时间内发送大量的 SYN 包给 Server(俗称 SYN flood 攻击)，用于耗尽 Server 的 SYN 队列。对于应对 SYN 过多的问题，linux 提供了几个 TCP 参数：tcp_syncookies、tcp_synack_retries、tcp_max_syn_backlog、tcp_abort_on_overflow 来调整应对。  

![](https://pic1.zhimg.com/v2-f998ee97330a3a258ad617ea10257c4c_b.jpg)![](https://pic1.zhimg.com/v2-f998ee97330a3a258ad617ea10257c4c_r.jpg)

全连接队列满
------

当第三次握手时，当 server 接收到 ACK 包之后，会进入一个新的叫 accept 的队列。

当 accept 队列满了之后，即使 client 继续向 server 发送 ACK 的包，也会不被响应，此时 ListenOverflows+1，同时 server 通过 tcp_abort_on_overflow 来决定如何返回，0 表示直接丢弃该 ACK，1 表示发送 RST 通知 client；相应的，client 则会分别返回`read timeout` 或者 `connection reset by peer`。另外，tcp_abort_on_overflow 是 0 的话，server 过一段时间再次发送 syn+ack 给 client（也就是重新走握手的第二步），如果 client 超时等待比较短，就很容易异常了。而客户端收到多个 SYN ACK 包，则会认为之前的 ACK 丢包了。于是促使客户端再次发送 ACK ，在 accept 队列有空闲的时候最终完成连接。若 accept 队列始终满员，则最终客户端收到 RST 包（此时服务端发送 syn+ack 的次数超出了 tcp_synack_retries）。

服务端仅仅只是创建一个定时器，以固定间隔重传 syn 和 ack 到服务端

![](https://pic2.zhimg.com/v2-df71dbb7c5f6743eca7fba07d1a0b869_b.jpg)![](https://pic2.zhimg.com/v2-df71dbb7c5f6743eca7fba07d1a0b869_r.jpg)

命令
--

netstat -s 命令

> [root@server ~]# netstat -s | egrep "listen|LISTEN" 667399 times the listen queue of a socket overflowed 667399 SYNs to LISTEN sockets ignored  
> 比如上面看到的 667399 times ，表示全连接队列溢出的次数，隔几秒钟执行下，如果这个数字一直在增加的话肯定全连接队列偶尔满了。  
> [root@server ~]# netstat -s | grep TCPBacklogDrop 查看 Accept queue 是否有溢出  

ss 命令

> [root@server ~]# ss -lnt State Recv-Q Send-Q Local Address:Port Peer Address:Port LISTEN 0 128 _:6379_ : _LISTEN 0 128_ :22 _:_ 如果 State 是 listen 状态，Send-Q 表示第三列的 listen 端口上的全连接队列最大为 50，第一列 Recv-Q 为全连接队列当前使用了多少。 非 LISTEN 状态中 Recv-Q 表示 receive queue 中的 bytes 数量；Send-Q 表示 send queue 中的 bytes 数值。  

小结
--

当外部连接请求到来时，TCP 模块会首先查看 max_syn_backlog，如果处于 SYN_RCVD 状态的连接数目超过这一阈值，进入的连接会被拒绝。根据 tcp_abort_on_overflow 字段来决定是直接丢弃，还是直接 reset.

从服务端来说，三次握手中，第一步 server 接受到 client 的 syn 后，把相关信息放到半连接队列中，同时回复 syn+ack 给 client. 第三步当收到客户端的 ack, 将连接加入到全连接队列。

一般，全连接队列比较小，会先满，此时半连接队列还没满。如果这时收到 syn 报文，则会进入半连接队列，没有问题。但是如果收到了三次握手中的第 3 步 (ACK)，则会根据 tcp_abort_on_overflow 字段来决定是直接丢弃，还是直接 reset. 此时，客户端发送了 ACK, 那么客户端认为三次握手完成，它认为服务端已经准备好了接收数据的准备。但此时服务端可能因为全连接队列满了而无法将连接放入，会重新发送第 2 步的 syn+ack, 如果这时有数据到来，服务器 TCP 模块会将数据存入队列中。一段时间后，client 端没收到回复，超时，连接异常，client 会主动关闭连接。

“三次握手，四次挥手”redis 实例分析
---------------------

1.  我在 dev 机器上部署 redis 服务，端口号为 6379,
2.  通过 tcpdump 工具获取数据包，使用如下命令

```
tcpdump -w /tmp/a.cap port 6379 -s0
-w把数据写入文件，-s0设置每个数据包的大小默认为68字节，如果用-S 0则会抓到完整数据包

```

1.  在 dev2 机器上用 redis-cli 访问 dev:6379, 发送一个 ping, 得到回复 pong
2.  停止抓包，用 tcpdump 读取捕获到的数据包

```
tcpdump -r /tmp/a.cap -n -nn -A -x| vim -
（-x 以16进制形式展示，便于后面分析）

```

共收到了 7 个包。

抓到的是 IP 数据包，IP 数据包分为 IP 头部和 IP 数据部分，IP 数据部分是 TCP 头部加 TCP 数据部分。

IP 的数据格式为：

![](https://pic4.zhimg.com/v2-a8702bdb6e9cf9fd29e824ac07542067_b.jpg)![](https://pic4.zhimg.com/v2-a8702bdb6e9cf9fd29e824ac07542067_r.jpg)

它由固定长度 20B + 可变长度构成。

```
10:55:45.662077 IP dev2.39070 > dev.6379: Flags [S], seq 4133153791, win 29200, options [mss 1460,sackOK,TS val 2959270704 ecr 0,nop,wscale 7], length 0
        0x0000:  4500 003c 08cf 4000 3606 14a5 0ab3 b561
        0x0010:  0a60 5cd4 989e 18eb f65a ebff 0000 0000
        0x0020:  a002 7210 872f 0000 0204 05b4 0402 080a
        0x0030:  b062 e330 0000 0000 0103 0307

```

对着 IP 头部格式，来拆解数据包的具体含义。

![](https://pic3.zhimg.com/v2-1b33f99c890277f54fd15370635860ee_b.jpg)![](https://pic3.zhimg.com/v2-1b33f99c890277f54fd15370635860ee_r.jpg)

剩余的数据部分即为 TCP 协议相关的。TCP 也是 20B 固定长度 + 可变长度部分。

![](https://pic4.zhimg.com/v2-4949f0578dbde508f35ef4aef9a932b7_b.jpg)![](https://pic4.zhimg.com/v2-4949f0578dbde508f35ef4aef9a932b7_r.jpg)

可变长度部分，协议如下：

![](https://pic1.zhimg.com/v2-82bc4c9a7d5ae823c09502c687d3b6e0_b.jpg)![](https://pic1.zhimg.com/v2-82bc4c9a7d5ae823c09502c687d3b6e0_r.jpg)

这样第一个包分析完了。dev2 向 dev 发送 SYN 请求。`也就是三次握手中的第一次了。` `SYN seq(c)=4133153791`

第二个包，dev 响应连接，ack=4133153792. 表明 dev 下次准备接收这个序号的包，用于 tcp 字节注的顺序控制。dev（也就是 server 端）的初始序号为 seq=4264776963, syn=1. `SYN ack=seq(c)+1 seq(s)=4264776963`

第三个包，client 包确认，这里使用了相对值应答。seq=4133153792, 等于第二个包的 ack. ack=4264776964. `ack=seq(s)+1, seq=seq(c)+1` 至此，三次握手完成。接下来就是发送 ping 和 pong 的数据了。

接着第四个包。

```
10:55:48.090073 IP dev2.39070 > dev.6379: Flags [P.], seq 1:15, ack 1, win 229, options [nop,nop,TS val 2959273132 ecr 3132256230], length 14
        0x0000:  4500 0042 08d1 4000 3606 149d 0ab3 b561
        0x0010:  0a60 5cd4 989e 18eb f65a ec00 fe33 5504
        0x0020:  8018 00e5 4b5f 0000 0101 080a b062 ecac
        0x0030:  bab2 6fe6 2a31 0d0a 2434 0d0a 7069 6e67
        0x0040:  0d0a

```

tcp 首部长度为 32B, 可选长度为 12B. IP 报文的总长度为 66B, 首部长度为 20B, 因此 TCP 数据部分长度为 14B. seq=0xf65a ec00=4133153792 ACK, PSH. 数据部分为 2a31 0d0a 2434 0d0a 7069 6e67 0d0a

```
0x2a31         -> *1
0x0d0a         -> \r\n
0x2434         -> $4
0x0d0a         -> \r\n
0x7069 0x6e67  -> ping
0x0d0a         -> \r\n

```

dev2 向 dev 发送了 ping 数据，第四个包完毕。

第五个包，dev2 向 dev 发送 ack 响应。 序列号为 0xfe33 5504=4264776964, ack 确认号为 0xf65a ec0e=4133153806=(4133153792+14).

第六个包，dev 向 dev2 响应 pong 消息。序列号 fe33 5504，确认号 f65a ec0e, TCP 头部可选长度为 12B, IP 数据报总长度为 59B, 首部长度为 20B, 因此 TCP 数据长度为 7B. 数据部分 2b50 4f4e 470d 0a, 翻译过来就是`+PONG\r\n`.

至此，Redis 客户端和 Server 端的三次握手过程分析完毕。

欢迎一起交流~~

![](https://pic3.zhimg.com/v2-995dfaa4badce2fe43071da3e5ff459a_b.jpg)![](https://pic3.zhimg.com/v2-995dfaa4badce2fe43071da3e5ff459a_r.jpg)

参考
--

【redis】[https://segmentfault.com/a/1190000015044878](https://link.zhihu.com/?target=https%3A//segmentfault.com/a/1190000015044878)

【tcp option】[https://blog.csdn.net/wdscq1234/article/details/52423272](https://link.zhihu.com/?target=https%3A//blog.csdn.net/wdscq1234/article/details/52423272)

【滑动窗口】[https://www.zhihu.com/question/32255109](https://www.zhihu.com/question/32255109)

【全连接队列】[http://jm.taobao.org/2017/05/25/525-1/](https://link.zhihu.com/?target=http%3A//jm.taobao.org/2017/05/25/525-1/)

【client fooling】 [https://github.com/torvalds/linux/commit/5ea8ea2cb7f1d0db15762c9b0bb9e7330425a071](https://link.zhihu.com/?target=https%3A//github.com/torvalds/linux/commit/5ea8ea2cb7f1d0db15762c9b0bb9e7330425a071)

【backlog RECV_Q】[http://blog.51cto.com/59090939/1947443](https://link.zhihu.com/?target=http%3A//blog.51cto.com/59090939/1947443)

【定时器】[https://www.cnblogs.com/menghuanbiao/p/5212131.html](https://link.zhihu.com/?target=https%3A//www.cnblogs.com/menghuanbiao/p/5212131.html)

【队列图示】[https://www.itcodemonkey.com/article/5834.html](https://link.zhihu.com/?target=https%3A//www.itcodemonkey.com/article/5834.html)

【tcp flood 攻击】[https://www.cnblogs.com/hubavyn/p/4477883.html](https://link.zhihu.com/?target=https%3A//www.cnblogs.com/hubavyn/p/4477883.html)

【MSS MTU】[https://blog.csdn.net/LoseInVain/article/details/5369426](https://link.zhihu.com/?target=https%3A//blog.csdn.net/LoseInVain/article/details/5369426)

写下你的评论...  

巨详细…

原来这才是三次握手的真正意思啊, md 我上学的时候老师只说 3 次握手, 我特么以为是客户端问服务端三次, 服务端回答三次呢

亲，这边建议您回去重读

这么好的文章

写的真好... 很多人都回答不出来为什么要三次握手四次挥手

感谢博主的分享。有个问题。SYN 占用 1 个序列号为什么意味着使用重传进行可靠传输？

因为必须要对这个序列号作确认 (ack), 不确认就会重传

非常感谢

感谢作者，这个对 TCP 三次握手四次挥手的讲解太细致了，我会把他分享出去。

谢谢

![](https://pic2.zhimg.com/v2-90359a720808ff45062287127cfa1039_r.gif)

或许可以加上为啥两次握手不行，为啥有 time wait 状态

感谢作者为互联网上的学习资源贡献了这篇优质文章

![](https://pic2.zhimg.com/v2-90359a720808ff45062287127cfa1039_r.gif)

该评论已删除

赞！推荐！

感觉大学听的白听了 [捂脸]

你好，我有一个问题想请教一下，就是半连接状态下，被动关闭方发送数据之后还会受到确认吗？

写得太好了！

谢谢