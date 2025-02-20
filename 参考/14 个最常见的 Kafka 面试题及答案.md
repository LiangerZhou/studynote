> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://www.toutiao.com/i6456660580726997517

本文为您盘点了 14 个最常见的 Kafka 面试题，同时也是对 Apache Kafka 初学者必备知识点的一个整理与介绍。

![](http://p1.pstatp.com/large/322100048a75711cd5e8)

**1、请说明什么是 Apache Kafka?**

Apache Kafka 是由 Apache 开发的一种发布订阅消息系统，它是一个分布式的、分区的和重复的日志服务。

**2、请说明什么是传统的消息传递方法?**

传统的消息传递方法包括两种：

* 排队：在队列中，一组用户可以从服务器中读取消息，每条消息都发送给其中一个人。

* 发布 - 订阅：在这个模型中，消息被广播给所有的用户。

**3、请说明 Kafka 相对传统技术有什么优势?**

Apache Kafka 与传统的消息传递技术相比优势之处在于：

快速: 单一的 Kafka 代理可以处理成千上万的客户端，每秒处理数兆字节的读写操作。

可伸缩: 在一组机器上对数据进行分区和简化，以支持更大的数据

持久: 消息是持久性的，并在集群中进行复制，以防止数据丢失。

设计: 它提供了容错保证和持久性

**4、在 Kafka 中 broker 的意义是什么?**

在 Kafka 集群中，broker 术语用于引用服务器。

**5、Kafka 服务器能接收到的最大信息是多少?**

Kafka 服务器可以接收到的消息的最大大小是 1000000 字节。

**6、解释 Kafka 的 Zookeeper 是什么? 我们可以在没有 Zookeeper 的情况下使用 Kafka 吗?**

Zookeeper 是一个开放源码的、高性能的协调服务，它用于 Kafka 的分布式应用。

不，不可能越过 Zookeeper，直接联系 Kafka broker。一旦 Zookeeper 停止工作，它就不能服务客户端请求。

* Zookeeper 主要用于在集群中不同节点之间进行通信

* 在 Kafka 中，它被用于提交偏移量，因此如果节点在任何情况下都失败了，它都可以从之前提交的偏移量中获取

* 除此之外，它还执行其他活动，如: leader 检测、分布式同步、配置管理、识别新节点何时离开或连接、集群、节点实时状态等等。

**7、解释 Kafka 的用户如何消费信息?**

在 Kafka 中传递消息是通过使用 sendfile API 完成的。它支持将字节从套接口转移到磁盘，通过内核空间保存副本，并在内核用户之间调用内核。

**8、解释如何提高远程用户的吞吐量?**

如果用户位于与 broker 不同的数据中心，则可能需要调优套接口缓冲区大小，以对长网络延迟进行摊销。

**9、解释一下，在数据制作过程中，你如何能从 Kafka 得到准确的信息?**

在数据中，为了精确地获得 Kafka 的消息，你必须遵循两件事: 在数据消耗期间避免重复，在数据生产过程中避免重复。

这里有两种方法，可以在数据生成时准确地获得一个语义:

* 每个分区使用一个单独的写入器，每当你发现一个网络错误，检查该分区中的最后一条消息，以查看您的最后一次写入是否成功

* 在消息中包含一个主键 (UUID 或其他)，并在用户中进行反复制

**10、解释如何减少 ISR 中的扰动? broker 什么时候离开 ISR?**

ISR 是一组与 leaders 完全同步的消息副本，也就是说 ISR 中包含了所有提交的消息。ISR 应该总是包含所有的副本，直到出现真正的故障。如果一个副本从 leader 中脱离出来，将会从 ISR 中删除。

**11、Kafka 为什么需要复制?**

Kafka 的信息复制确保了任何已发布的消息不会丢失，并且可以在机器错误、程序错误或更常见些的软件升级中使用。

**12、如果副本在 ISR 中停留了很长时间表明什么?**

如果一个副本在 ISR 中保留了很长一段时间，那么它就表明，跟踪器无法像在 leader 收集数据那样快速地获取数据。

**13、请说明如果首选的副本不在 ISR 中会发生什么?**

如果首选的副本不在 ISR 中，控制器将无法将 leadership 转移到首选的副本。

**14、有可能在生产后发生消息偏移吗?**

在大多数队列系统中，作为生产者的类无法做到这一点，它的作用是触发并忘记消息。broker 将完成剩下的工作，比如使用 id 进行适当的元数据处理、偏移量等。

作为消息的用户，你可以从 Kafka broker 中获得补偿。如果你注视 SimpleConsumer 类，你会注意到它会获取包括偏移量作为列表的 MultiFetchResponse 对象。此外，当你对 Kafka 消息进行迭代时，你会拥有包括偏移量和消息发送的 MessageAndOffset 对象。