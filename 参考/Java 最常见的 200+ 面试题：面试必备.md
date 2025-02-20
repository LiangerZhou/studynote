> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://blog.csdn.net/Majker/article/details/88094127

### <a></a>Java 最常见的 200+ 面试题：面试必备

*   [前言](#_1)
*   [面试题模块介绍](#_19)
*   [适宜阅读人群](#_26)
*   [具体面试题](#_31)

*   [一、Java 基础](#Java__34)
*   [二、容器](#_54)
*   [三、多线程](#_73)
*   [四、反射](#_98)
*   [五、对象拷贝](#_105)
*   [六、Java Web](#Java_Web_111)
*   [七、异常](#_123)
*   [八、网络](#_131)
*   [九、设计模式](#_143)
*   [十、Spring/Spring MVC](#SpringSpring_MVC_148)
*   [十一、Spring Boot/Spring Cloud](#Spring_BootSpring_Cloud_165)
*   [十二、Hibernate](#Hibernate_177)
*   [十三、Mybatis](#Mybatis_192)
*   [十四、RabbitMQ](#RabbitMQ_205)
*   [十五、Kafka](#Kafka_225)
*   [十六、Zookeeper](#Zookeeper_233)
*   [十七、MySql](#MySql_243)
*   [十八、Redis](#Redis_261)
*   [十九、JVM](#JVM_278)

# <a></a><a target="_blank"></a>前言

本文纯粹转载，请关注[原文](https://blog.csdn.net/sufu1065/article/details/88051083)。
JAVA 学习的东西很多很杂，有些东西长时间不用，基本都要忘光光的，所以有时候需要回顾下，在复习中发现之前不知道的东西，补缺补漏，提高自己的技术水平，完善自己的知识体系 = = ！

> *   第一：有更多的人因此而学到了更多的知识，这不算是一件坏事，恰好相反。
> *   第二：这只是一种经验的高度提炼，让那些有技术能力的人，学会如何表达自己所掌握的知识，这也是一件好事。
> *   第三：如果只是死记硬背这些面试题，如果面试官能再深入问纠一些细节，也可识破之中的 “玄机”。
> *   第四：学习有很多种方式，但只有好学者才会临池学书。如果是不想学的人，无论你提供什么资料，他都会视而不见，我只是为好学者，提供一份自我实现的学习资料而已。

对应到我们这份面试题也是一样，首先你如果能真的记住其中大部分的答案：
第一，说明你的脑子不笨；
第二，说明你有上进心，也愿意学习；
第三，记住了这份面试题之后，即使你的能力刚开始没有那么好，但有了理论支撑之后，再去工作实践的时候，就有了理论指导，结果也不会太差。

所以如果您是面试官，恰好又看到这里，如果条件允许的话，请多给这样愿意学又很聪明的年轻人一些机会，即使他们现在并没有太多的实践经验。

# <a></a><a target="_blank"></a>面试题模块介绍

说了这么多，下面进入我们本文的主题，我们这份面试题，包含的内容了十九了模块：**Java 基础、容器、多线程、反射、对象拷贝、Java Web 模块、异常、网络、设计模式、Spring/Spring MVC、Spring Boot/Spring Cloud、Hibernate、Mybatis、RabbitMQ、Kafka、Zookeeper、MySql、Redis、JVM** 。如下图所示：
![](https://img-blog.csdnimg.cn/2019030321125533.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01hamtlcg==,size_16,color_FFFFFF,t_70)

可能对于初学者不需要后面的框架和 JVM 模块的知识，读者朋友们可根据自己的情况，选择对应的模块进行阅读。

# <a></a><a target="_blank"></a>适宜阅读人群

需要面试的初 / 中 / 高级 java 程序员
想要查漏补缺的人
想要不断完善和扩充自己 java 技术栈的人
java 面试官

# <a></a><a target="_blank"></a>具体面试题

下面一起来看 208 道面试题，具体的内容。

## <a></a><a target="_blank"></a>一、Java 基础

> *   1.JDK 和 JRE 有什么区别？
> *   2.== 和 equals 的区别是什么？
> *   3\. 两个对象的 hashCode() 相同，则 equals() 也一定为 true，对吗？
> *   4.final 在 java 中有什么作用？
> *   5.java 中的 Math.round(-1.5) 等于多少？
> *   6.String 属于基础的数据类型吗？
> *   7.java 中操作字符串都有哪些类？它们之间有什么区别？
> *   8.String str="i" 与 String str=new String(“i”) 一样吗？
> *   9\. 如何将字符串反转？
> *   10.String 类的常用方法都有那些？
> *   11\. 抽象类必须要有抽象方法吗？
> *   12\. 普通类和抽象类有哪些区别？
> *   13\. 抽象类能使用 final 修饰吗？
> *   14\. 接口和抽象类有什么区别？
> *   15.java 中 IO 流分为几种？
> *   16.BIO、NIO、AIO 有什么区别？
> *   17.Files 的常用方法都有哪些？

## <a></a><a target="_blank"></a>二、容器

> *   18.java 容器都有哪些？
> *   19.Collection 和 Collections 有什么区别？
> *   20.List、Set、Map 之间的区别是什么？
> *   21.HashMap 和 Hashtable 有什么区别？
> *   22\. 如何决定使用 HashMap 还是 TreeMap？
> *   23\. 说一下 HashMap 的实现原理？
> *   24\. 说一下 HashSet 的实现原理？
> *   25.ArrayList 和 LinkedList 的区别是什么？
> *   26\. 如何实现数组和 List 之间的转换？
> *   27.ArrayList 和 Vector 的区别是什么？
> *   28.Array 和 ArrayList 有何区别？
> *   29\. 在 Queue 中 poll() 和 remove() 有什么区别？
> *   30\. 哪些集合类是线程安全的？
> *   31\. 迭代器 Iterator 是什么？
> *   32.Iterator 怎么使用？有什么特点？
> *   33.Iterator 和 ListIterator 有什么区别？
> *   34\. 怎么确保一个集合不能被修改？

## <a></a><a target="_blank"></a>三、多线程

> *   35\. 并行和并发有什么区别？
> *   36\. 线程和进程的区别？
> *   37\. 守护线程是什么？
> *   38\. 创建线程有哪几种方式？
> *   39\. 说一下 runnable 和 callable 有什么区别？
> *   40\. 线程有哪些状态？
> *   41.sleep() 和 wait() 有什么区别？
> *   42.notify() 和 notifyAll() 有什么区别？
> *   43\. 线程的 run() 和 start() 有什么区别？
> *   44\. 创建线程池有哪几种方式？
> *   45\. 线程池都有哪些状态？
> *   46\. 线程池中 submit() 和 execute() 方法有什么区别？
> *   47\. 在 java 程序中怎么保证多线程的运行安全？
> *   48\. 多线程锁的升级原理是什么？
> *   49\. 什么是死锁？
> *   50\. 怎么防止死锁？
> *   51.ThreadLocal 是什么？有哪些使用场景？
> *   52\. 说一下 synchronized 底层实现原理？
> *   53.synchronized 和 volatile 的区别是什么？
> *   54.synchronized 和 Lock 有什么区别？
> *   55.synchronized 和 ReentrantLock 区别是什么？
> *   56\. 说一下 atomic 的原理？

## <a></a><a target="_blank"></a>四、反射

> *   57\. 什么是反射？
> *   58\. 什么是 java 序列化？什么情况下需要序列化？
> *   59\. 动态代理是什么？有哪些应用？
> *   60\. 怎么实现动态代理？

## <a></a><a target="_blank"></a>五、对象拷贝

> *   61\. 为什么要使用克隆？
> *   62\. 如何实现对象克隆？
> *   63\. 深拷贝和浅拷贝区别是什么？

## <a></a><a target="_blank"></a>六、Java Web

> *   64.jsp 和 servlet 有什么区别？
> *   65.jsp 有哪些内置对象？作用分别是什么？
> *   66\. 说一下 jsp 的 4 种作用域？
> *   67.session 和 cookie 有什么区别？
> *   68\. 说一下 session 的工作原理？
> *   69\. 如果客户端禁止 cookie 能实现 session 还能用吗？
> *   70.spring mvc 和 struts 的区别是什么？
> *   71\. 如何避免 sql 注入？
> *   72\. 什么是 XSS 攻击，如何避免？
> *   73\. 什么是 CSRF 攻击，如何避免？

## <a></a><a target="_blank"></a>七、异常

> *   74.throw 和 throws 的区别？
> *   75.final、finally、finalize 有什么区别？
> *   76.try-catch-finally 中哪个部分可以省略？
> *   77.try-catch-finally 中，如果 catch 中 return 了，finally 还会执行吗？
> *   78\. 常见的异常类有哪些？

## <a></a><a target="_blank"></a>八、网络

> *   79.http 响应码 301 和 302 代表的是什么？有什么区别？
> *   80.forward 和 redirect 的区别？
> *   81\. 简述 tcp 和 udp 的区别？
> *   82.tcp 为什么要三次握手，两次不行吗？为什么？
> *   83\. 说一下 tcp 粘包是怎么产生的？
> *   84.OSI 的七层模型都有哪些？
> *   85.get 和 post 请求有哪些区别？
> *   86\. 如何实现跨域？
> *   87\. 说一下 JSONP 实现原理？

## <a></a><a target="_blank"></a>九、设计模式

> *   88\. 说一下你熟悉的设计模式？
> *   89\. 简单工厂和抽象工厂有什么区别？

## <a></a><a target="_blank"></a>十、Spring/Spring MVC

> *   90\. 为什么要使用 spring？
> *   91\. 解释一下什么是 aop？
> *   92\. 解释一下什么是 ioc？
> *   93.spring 有哪些主要模块？
> *   94.spring 常用的注入方式有哪些？
> *   95.spring 中的 bean 是线程安全的吗？
> *   96.spring 支持几种 bean 的作用域？
> *   97.spring 自动装配 bean 有哪些方式？
> *   98.spring 事务实现方式有哪些？
> *   99\. 说一下 spring 的事务隔离？
> *   100\. 说一下 spring mvc 运行流程？
> *   101.spring mvc 有哪些组件？
> *   102.@RequestMapping 的作用是什么？
> *   103.@Autowired 的作用是什么？

## <a></a><a target="_blank"></a>十一、Spring Boot/Spring Cloud

> *   104\. 什么是 spring boot？
> *   105\. 为什么要用 spring boot？
> *   106.spring boot 核心配置文件是什么？
> *   107.spring boot 配置文件有哪几种类型？它们有什么区别？
> *   108.spring boot 有哪些方式可以实现热部署？
> *   109.jpa 和 hibernate 有什么区别？
> *   110\. 什么是 spring cloud？
> *   111.spring cloud 断路器的作用是什么？
> *   112.spring cloud 的核心组件有哪些？

## <a></a><a target="_blank"></a>十二、Hibernate

> *   113\. 为什么要使用 hibernate？
> *   114\. 什么是 ORM 框架？
> *   115.hibernate 中如何在控制台查看打印的 sql 语句？
> *   116.hibernate 有几种查询方式？
> *   117.hibernate 实体类可以被定义为 final 吗？
> *   118\. 在 hibernate 中使用 Integer 和 int 做映射有什么区别？
> *   119.hibernate 是如何工作的？
> *   120.get() 和 load() 的区别？
> *   121\. 说一下 hibernate 的缓存机制？
> *   122.hibernate 对象有哪些状态？
> *   123\. 在 hibernate 中 getCurrentSession 和 openSession 的区别是什么？
> *   124.hibernate 实体类必须要有无参构造函数吗？为什么？

## <a></a><a target="_blank"></a>十三、Mybatis

> *   125.mybatis 中 #{} 和 ${} 的区别是什么？
> *   126.mybatis 有几种分页方式？
> *   127.RowBounds 是一次性查询全部结果吗？为什么？
> *   128.mybatis 逻辑分页和物理分页的区别是什么？
> *   129.mybatis 是否支持延迟加载？延迟加载的原理是什么？
> *   130\. 说一下 mybatis 的一级缓存和二级缓存？
> *   131.mybatis 和 hibernate 的区别有哪些？
> *   132.mybatis 有哪些执行器（Executor）？
> *   133.mybatis 分页插件的实现原理是什么？
> *   134.mybatis 如何编写一个自定义插件？

## <a></a><a target="_blank"></a>十四、RabbitMQ

> *   135.rabbitmq 的使用场景有哪些？
> *   136.rabbitmq 有哪些重要的角色？
> *   137.rabbitmq 有哪些重要的组件？
> *   138.rabbitmq 中 vhost 的作用是什么？
> *   139.rabbitmq 的消息是怎么发送的？
> *   140.rabbitmq 怎么保证消息的稳定性？
> *   141.rabbitmq 怎么避免消息丢失？
> *   142\. 要保证消息持久化成功的条件有哪些？
> *   143.rabbitmq 持久化有什么缺点？
> *   144.rabbitmq 有几种广播类型？
> *   145.rabbitmq 怎么实现延迟消息队列？
> *   146.rabbitmq 集群有什么用？
> *   147.rabbitmq 节点的类型有哪些？
> *   148.rabbitmq 集群搭建需要注意哪些问题？
> *   149.rabbitmq 每个节点是其他节点的完整拷贝吗？为什么？
> *   150.rabbitmq 集群中唯一一个磁盘节点崩溃了会发生什么情况？
> *   151.rabbitmq 对集群节点停止顺序有要求吗？

## <a></a><a target="_blank"></a>十五、Kafka

> *   152.kafka 可以脱离 zookeeper 单独使用吗？为什么？
> *   153.kafka 有几种数据保留的策略？
> *   154.kafka 同时设置了 7 天和 10G 清除数据，到第五天的时候消息达到了 10G，这个时候 kafka 将如何处理？
> *   155\. 什么情况会导致 kafka 运行变慢？
> *   156\. 使用 kafka 集群需要注意什么？

## <a></a><a target="_blank"></a>十六、Zookeeper

> *   157.zookeeper 是什么？
> *   158.zookeeper 都有哪些功能？
> *   159.zookeeper 有几种部署模式？
> *   160.zookeeper 怎么保证主从节点的状态同步？
> *   161\. 集群中为什么要有主节点？
> *   162\. 集群中有 3 台服务器，其中一个节点宕机，这个时候 zookeeper 还可以使用吗？
> *   163\. 说一下 zookeeper 的通知机制？

## <a></a><a target="_blank"></a>十七、MySql

> *   164\. 数据库的三范式是什么？
> *   165\. 一张自增表里面总共有 7 条数据，删除了最后 2 条数据，重启 mysql 数据库，又插入了一条数据，此时 id 是几？
> *   166\. 如何获取当前数据库版本？
> *   167\. 说一下 ACID 是什么？
> *   168.char 和 varchar 的区别是什么？
> *   169.float 和 double 的区别是什么？
> *   170.mysql 的内连接、左连接、右连接有什么区别？
> *   171.mysql 索引是怎么实现的？
> *   172\. 怎么验证 mysql 的索引是否满足需求？
> *   173\. 说一下数据库的事务隔离？
> *   174\. 说一下 mysql 常用的引擎？
> *   175\. 说一下 mysql 的行锁和表锁？
> *   176\. 说一下乐观锁和悲观锁？
> *   177.mysql 问题排查都有哪些手段？
> *   178\. 如何做 mysql 的性能优化？

## <a></a><a target="_blank"></a>十八、Redis

> *   179.redis 是什么？都有哪些使用场景？
> *   180.redis 有哪些功能？
> *   181.redis 和 memecache 有什么区别？
> *   182.redis 为什么是单线程的？
> *   183\. 什么是缓存穿透？怎么解决？
> *   184.redis 支持的数据类型有哪些？
> *   185.redis 支持的 java 客户端都有哪些？
> *   186.jedis 和 redisson 有哪些区别？
> *   187\. 怎么保证缓存和数据库数据的一致性？
> *   188.redis 持久化有几种方式？
> *   189.redis 怎么实现分布式锁？
> *   190.redis 分布式锁有什么缺陷？
> *   191.redis 如何做内存优化？
> *   192.redis 淘汰策略有哪些？
> *   193.redis 常见的性能问题有哪些？该如何解决？

## <a></a><a target="_blank"></a>十九、JVM

> *   194\. 说一下 jvm 的主要组成部分？及其作用？
> *   195\. 说一下 jvm 运行时数据区？
> *   196\. 说一下堆栈的区别？
> *   197\. 队列和栈是什么？有什么区别？
> *   198\. 什么是双亲委派模型？
> *   199\. 说一下类加载的执行过程？
> *   200\. 怎么判断对象是否可以被回收？
> *   201.java 中都有哪些引用类型？
> *   202\. 说一下 jvm 有哪些垃圾回收算法？
> *   203\. 说一下 jvm 有哪些垃圾回收器？
> *   204\. 详细介绍一下 CMS 垃圾回收器？
> *   205\. 新生代垃圾回收器和老生代垃圾回收器都有哪些？有什么区别？
> *   206\. 简述分代垃圾回收器是怎么工作的？
> *   207\. 说一下 jvm 调优的工具？
> *   208\. 常用的 jvm 调优的参数都有哪些？

* * *

作者：王磊的博客
来源：CSDN
原文：[https://blog.csdn.net/sufu1065/article/details/88051083](https://blog.csdn.net/sufu1065/article/details/88051083)
版权声明：本文为博主原创文章，转载请附上博文链接！