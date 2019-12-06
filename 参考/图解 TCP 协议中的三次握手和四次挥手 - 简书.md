> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://www.jianshu.com/p/9968b16b607e

[![](https://upload.jianshu.io/users/upload_avatars/1641067/46b5895f9b33?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96/format/webp)](https://www.jianshu.com/u/8366dabb46be)

12016.08.18 23:21:05 字数 1,031 阅读 17,600

最近在复习计算机网络，看到 TCP 这一章，总结一下。

建立 TCP 需要三次握手才能建立，而断开连接则需要四次握手。整个过程如下图所示：

![](http://upload-images.jianshu.io/upload_images/1641067-cfbdc82ef9f5c5c0.jpg)

1.jpg

先来看看如何建立连接的：

![](http://upload-images.jianshu.io/upload_images/1641067-8d52ca990ffbee0a.png)

2.png

首先 Client 端发送连接请求报文，Server 段接受连接后回复 ACK 报文，并为这次连接分配资源。Client 端接收到 ACK 报文后也向 Server 段发送报文，并分配资源，这样 TCP 连接就建立了。

如何断开连接呢？简单的过程如下：

![](http://upload-images.jianshu.io/upload_images/1641067-5ed8bf6c24244b4c.png)

3.png

**注意中断连接端可以是 Client 端，也可以是 Server 端**

假设 Client 端发起中断请求，也就是发送 FIN 报文。Server 端接到 FIN 报文后，意思是说 “我 client 端要发给你了”，但是如果你还没有数据要发送完成，则不必急着关闭 Socket，可以继续发送数据。所以所以你先发送 ACK，"告诉 Client 端，你的请求我收到了，但是我还没准备好，请继续你等我的消息"。这个时候 Client 端就进入 FIN_WAIT 状态，继续等待 Server 端的 FIN 报文。当 Server 端确定数据已发送完成，则向 Client 端发送 FIN 报文，"告诉 Client 端，好了，我这边数据发完了，准备好关闭连接了"。Client 端收到 FIN 报文后，"就知道可以关闭连接了，但是他还是不相信网络，怕 Server 端不知道要关闭，所以发送 ACK 后进入 TIME_WAIT 状态，如果 Server 端没有收到 ACK 则可以重传。“，Server 端收到 ACK 后，" 就知道可以断开连接了 "。Client 端等待了 2MSL 后依然没有收到回复，则证明 Server 端已正常关闭，那好，我 Client 端也可以关闭连接了。Ok，TCP 连接就这样关闭了！

整个过程 Client 端所经历的状态如下：

![](http://upload-images.jianshu.io/upload_images/1641067-71248ccd102db6f0.png)

4.png

而 Server 端所经历的过程如下：

![](http://upload-images.jianshu.io/upload_images/1641067-331cd0ae6ee641c8.png)

5.png

**注意在 TIME_WAIT 状态中，如果 TCP client 端最后一次发送的 ACK 丢失了，它将重新发送。TIME_WAIT 状态中所需要的时间是依赖于实现方法的。典型的值为 30 秒、1 分钟和 2 分钟。等待之后连接正式关闭，并且所有的资源 (包括端口号) 都被释放。**

1.  为什么连接的时候是三次握手，关闭的时候却是四次握手？
    
    因为当 Server 端收到 Client 端的 SYN 连接请求报文后，可以直接发送 SYN+ACK 报文。其中 ACK 报文是用来应答的，SYN 报文是用来同步的。但是关闭连接时，当 Server 端收到 FIN 报文时，很可能并不会立即关闭 SOCKET，所以只能先回复一个 ACK 报文，告诉 Client 端，"你发的 FIN 报文我收到了"。只有等到我 Server 端所有的报文都发送完了，我才能发送 FIN 报文，因此不能一起发送。故需要四步握手。
    
2.  为什么 TIME_WAIT 状态需要经过 2MSL(最大报文段生存时间) 才能返回到 CLOSE 状态？
    
    *   为了保证发送的最后一个 ACK 报文段能够到达 B
    *   防止 “已失效的连接请求报文段” 出现在本连接中。在发送完最后一个 ACK 报文段后，再经过实践 2MSL，就可以使本连接持续的时间内所产生的所有报文段，都从网络中消失。这样就可以使下一个新的连接中不会出现这种就得连接请求报文段。

### TCP 有限状态机

TCP 有限状态机如下图：

*   粗实箭头表示客户进程的正常迁移
*   粗虚线箭头表示对服务器进程的正常迁移
*   细线箭头表示异常变迁

![](http://upload-images.jianshu.io/upload_images/1641067-133b96a90ee97aa1.png)

6.png

"小礼物走一走，来简书关注我"

还没有人赞赏，支持一下

[![](https://upload.jianshu.io/users/upload_avatars/1641067/46b5895f9b33?imageMogr2/auto-orient/strip|imageView2/1/w/100/h/100/format/webp)](https://www.jianshu.com/u/8366dabb46be)

总资产 346 (约 34.81 元) 共写了 2.1W 字获得 486 个赞共 145 个粉丝

### 被以下专题收入，发现更多相似内容

### 推荐阅读[更多精彩内容](https://www.jianshu.com/)

*   前言 ArrayList 作为 Java 集合框架中最常用的类，在一般情况下，用它存储集合数据最适合不过。知其然知...
    
    [![](https://upload-images.jianshu.io/upload_images/15975224-e8745e2dcd6f3fe7.jpeg?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/240/format/webp)](https://www.jianshu.com/p/4f8748f70da1)
*   选择排序 代码 原理说明：0 索引和后续的索引比较、1 索引和后续的索引比较...... 首次循环，可以得出 最小值 或...
    
*   "狼哥，面试又跪了，碰到了知识盲区"" 哪个？"" 一面还可以，二面面试官问我零拷贝的原理，懵逼了... 这块内容没去研...
    
    [![](https://upload.jianshu.io/users/upload_avatars/2184951/e504c85fb1dc.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/48/h/48/format/webp)占小狼](https://www.jianshu.com/u/90ab66c248e6)阅读 119
    
    [![](https://upload-images.jianshu.io/upload_images/2184951-730b2ba2fb35256a.png?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/240/format/webp)](https://www.jianshu.com/p/2581342317ce)
*   问题描述：【Hash Table】817. Linked List Components 解题思路： 这道题是给一...
    
*   分块查找 条件：、1、将表分成几块，且表或者有序，或者分块有序; 若 i<j，则第 j 块中所有记录的关键字均大于第 i 块中...
    
    [![](https://upload-images.jianshu.io/upload_images/16966221-d06a66b9ba365b04.png?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/240/format/webp)](https://www.jianshu.com/p/c8c920f4a044)