> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://blog.csdn.net/u014131617/article/details/89575124 版权声明：作者支付宝 18696232390 喜欢的可以打钱！ https://blog.csdn.net/u014131617/article/details/89575124

更多干货，关注微信公众号
![](https://img-blog.csdnimg.cn/20190515174945372.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTQxMzE2MTc=,size_16,color_FFFFFF,t_70)

### <a></a>文章目录

*   [Java 基础](#Java__4)

*   [1\. JDK 和 JRE 有什么区别？](#1_JDK__JRE__6)
*   [2\. == 和 equals 的区别是什么？](#2___equals__13)
*   [3\. 两个对象的 hashCode() 相同，则 equals() 也一定为 true，对吗？](#3__hashCode_equals_true_18)
*   [4\. final 在 Java 中有什么作用？](#4_final__Java__22)
*   [5\. Java 中的 Math.round(-1.5) 等于多少？](#5_Java__Mathround15__26)
*   [6\. String 属于基础的数据类型吗？](#6_String__29)
*   [7\. Java 中操作字符串都有哪些类？它们之间有什么区别？](#7_Java__37)
*   [8\. String str="i" 与 String str=new String(“i”) 一样吗？](#8_String_stri_String_strnew_Stringi_48)
*   [9\. 如何将字符串反转？](#9__52)
*   [10\. String 类的常用方法都有那些？](#10_String__60)
*   [11\. 抽象类必须要有抽象方法吗？](#11__65)
*   [12\. 普通类和抽象类有哪些区别？](#12__69)
*   [13\. 抽象类能使用 final 修饰吗？](#13__final__77)
*   [14\. 接口和抽象类有什么区别？](#14__81)
*   [15\. Java 中 IO 流分为几种？](#15_Java__IO__94)
*   [16\. BIO、NIO、AIO 有什么区别？](#16_BIONIOAIO__99)
*   [17\. Files 的常用方法都有哪些？](#17_Files_106)

*   [容器](#_113)

*   [18\. Java 容器都有哪些？](#18_Java__114)
*   [19\. Collection 和 Collections 有什么区别？](#19_Collection__Collections__117)
*   [20\. List、Set、Map 之间的区别是什么？](#20_ListSetMap__121)
*   [21\. HashMap 和 Hashtable 有什么区别？](#21_HashMap__Hashtable__126)
*   [22\. 如何决定使用 HashMap 还是 TreeMap？](#22__HashMap__TreeMap_133)
*   [23\. 说一下 HashMap 的实现原理？](#23__HashMap__136)
*   [24\. 说一下 HashSet 的实现原理？](#24__HashSet__143)
*   [25\. ArrayList 和 LinkedList 的区别是什么？](#25_ArrayList__LinkedList__150)
*   [26\. 如何实现数组和 List 之间的转换？](#26__List__154)
*   [27\. ArrayList 和 Vector 的区别是什么？](#27_ArrayList__Vector__158)
*   [28\. Array 和 ArrayList 有何区别？](#28_Array__ArrayList__165)
*   [29\. 在 Queue 中 poll() 和 remove() 有什么区别？](#29__Queue__poll_remove_171)
*   [30\. 哪些集合类是线程安全的？](#30__174)
*   [31\. 迭代器 Iterator 是什么？](#31__Iterator__177)
*   [32\. Iterator 怎么使用？有什么特点？](#32_Iterator__180)
*   [33\. Iterator 和 ListIterator 有什么区别？](#33_Iterator__ListIterator__193)
*   [34\. 怎么确保一个集合不能被修改？](#34__199)

*   [多线程](#_203)

*   [35\. 并行和并发有什么区别？](#35__205)
*   [36\. 线程和进程的区别？](#36__209)
*   [37\. 守护线程是什么？](#37__217)
*   [38\. 创建线程有哪几种方式？](#38__222)
*   [39\. 说一下 runnable 和 callable 有什么区别？](#39__runnable__callable__226)
*   [40\. 线程有哪些状态？](#40__231)
*   [41\. sleep() 和 wait() 有什么区别？](#41_sleep__wait__238)
*   [42\. notify() 和 notifyAll() 有什么区别？](#42_notify_notifyAll_242)
*   [43\. 线程的 run() 和 start() 有什么区别？](#43__run_start_245)
*   [44\. 创建线程池有哪几种方式？](#44__249)
*   [45\. 线程池都有哪些状态？](#45__256)
*   [46\. 线程池中 submit() 和 execute() 方法有什么区别？](#46__submit_execute_259)
*   [47\. 在 Java 程序中怎么保证多线程的运行安全？](#47__Java__264)
*   [48\. 多线程锁的升级原理是什么？](#48__267)
*   [49\. 什么是死锁？](#49__270)
*   [50\. 怎么防止死锁？](#50__273)
*   [51\. ThreadLocal 是什么？有哪些使用场景？](#51_ThreadLocal__276)
*   [52\. 说一下 Synchronized 底层实现原理？](#52__Synchronized__280)
*   [53\. Synchronized 和 volatile 的区别是什么？](#53_Synchronized__volatile__287)
*   [54\. Synchronized 和 Lock 有什么区别？](#54_Synchronized__Lock__294)
*   [55\. Synchronized 和 ReentrantLock 区别是什么？](#55_Synchronized__ReentrantLock__302)
*   [56\. 说一下 Atomic 的原理？](#56__Atomic__305)

*   [反射](#_309)

*   [1\. 什么是反射？](#1___311)
*   [58\. 什么是 Java 序列化？什么情况下需要序列化？](#58__Java__314)
*   [59\. 动态代理是什么？有哪些应用？](#59__319)
*   [60\. 怎么实现动态代理？](#60__322)

*   [对象拷贝](#_325)

*   [61\. 为什么要使用克隆？](#61__327)
*   [62\. 如何实现对象克隆？](#62__329)
*   [63\. 深拷贝和浅拷贝区别是什么？](#63__334)

*   [Java Web](#Java_Web_338)

*   [64\. JSP 和 servlet 有什么区别？](#64_JSP__servlet__340)
*   [65\. JSP 有哪些内置对象？作用分别是什么？](#65_JSP__346)
*   [66\. 说一下 JSP 的 4 种作用域？](#66__JSP__4__357)
*   [67\. Session 和 Cookie 有什么区别？](#67_Session__Cookie__360)
*   [68\. 说一下 Session 的工作原理？](#68__Session__366)
*   [69\. 如果客户端禁止 Cookie 能实现 Session 还能用吗？](#69__Cookie__Session__368)
*   [70\. Spring MVC 和 Struts 的区别是什么？](#70_Spring_MVC__Struts__372)
*   [71\. 如何避免 SQL 注入？](#71__SQL__379)
*   [72\. 什么是 XSS 攻击，如何避免？](#72__XSS__390)
*   [73\. 什么是 CSRF 攻击，如何避免？](#73__CSRF__395)
*   [74\. throw 和 throws 的区别？](#74_throw__throws__403)
*   [75\. final、finally、finalize 有什么区别？](#75_finalfinallyfinalize__410)
*   [76\. try-catch-finally 中哪个部分可以省略？](#76_trycatchfinally__415)
*   [77\. try-catch-finally 中，如果 catch 中 return 了，finally 还会执行吗？](#77_trycatchfinally__catch__return_finally__418)
*   [78\. 常见的异常类有哪些？](#78__421)

*   [网络](#_424)

*   [79\. HTTP 响应码 301 和 302 代表的是什么？有什么区别？](#79_HTTP__301__302__426)
*   [80\. forward 和 redirect 的区别？](#80_forward__redirect__431)
*   [81\. 简述 TCP 和 UDP 的区别？](#81__TCP__UDP__436)
*   [82\. TCP 为什么要三次握手，两次不行吗？为什么？](#82_TCP__443)
*   [83\. 说一下 TCP 粘包是怎么产生的？](#83__TCP__446)
*   [84\. OSI 的七层模型都有哪些？](#84_OSI__450)
*   [85\. Get 和 Post 请求有哪些区别？](#85_Get_Post__453)
*   [86\. 如何实现跨域？](#86__457)
*   [87\. 说一下 JSONP 实现原理？](#87__JSONP__466)

*   [设计模式](#_469)

*   [88\. 说一下你熟悉的设计模式？](#88__471)
*   [89\. 简单工厂和抽象工厂有什么区别？](#89__473)

*   [Spring/Spring MVC](#SpringSpring_MVC_477)

*   [90\. 为什么要使用 Spring？](#90__Spring_479)
*   [91\. 解释一下什么是 AOP？](#91__AOP_482)
*   [92\. 解释一下什么是 IOC？](#92__IOC_484)
*   [93\. Spring 有哪些主要模块？](#93_Spring__486)
*   [94\. Spring 常用的注入方式有哪些？](#94_Spring__495)
*   [95\. Spring 中的 Bean 是线程安全的吗？](#95_Spring__Bean__497)
*   [96\. Spring 支持几种 Bean 的作用域？](#96_Spring__Bean__504)
*   [97\. Spring 自动装配 Bean 有哪些方式？](#97_Spring__Bean__511)
*   [98\. Spring 事务实现方式有哪些？](#98_Spring__514)
*   [99\. 说一下 Spring 的事务隔离？](#99__Spring__520)
*   [100\. 说一下 Spring MVC 运行流程？](#100__Spring_MVC__524)
*   [101\. Spring MVC 有哪些组件？](#101_Spring_MVC__536)
*   [102\. @RequestMapping 的作用是什么？](#102_RequestMapping__539)
*   [103\. @Data 的作用是什么？](#103_Data__542)

*   [Spring Boot/Spring Cloud](#Spring_BootSpring_Cloud_545)

*   [104\. 什么是 Spring Boot？](#104__Spring_Boot_547)
*   [105\. 为什么要用 Spring Boot？](#105__Spring_Boot_550)
*   [106\. Spring Boot 核心配置文件是什么？](#106_Spring_Boot__553)
*   [107\. Spring Boot 配置文件有哪几种类型？它们有什么区别？](#107_Spring_Boot__556)
*   [108\. Spring Boot 有哪些方式可以实现热部署？](#108_Spring_Boot__559)
*   [109\. JPA 和 Hibernate 有什么区别？](#109_JPA__Hibernate__563)
*   [110\. 什么是 Spring Cloud？](#110__Spring_Cloud_569)
*   [111\. Spring Cloud 断路器的作用是什么？](#111_Spring_Cloud__575)
*   [1\. Spring Cloud 的核心组件有哪些？](#1___Spring_Cloud__577)

*   [Hibernate](#Hibernate_584)

*   [1\. 为什么要使用 Hibernate？](#1____Hibernate_586)
*   [2\. 什么是 ORM 框架？](#2____ORM__591)
*   [3\. Hibernate 中如何在控制台查看打印的 SQL 语句？](#3___Hibernate__SQL__593)
*   [4\. Hibernate 有几种查询方式？](#4___Hibernate__599)
*   [5\. Hibernate 实体类可以被定义为 final 吗？](#5___Hibernate__final__603)
*   [6\. 在 Hibernate 中使用 Integer 和 int 做映射有什么区别？](#6____Hibernate__Integer__int__605)
*   [7\. Hibernate 是如何工作的？](#7___Hibernate__609)
*   [8\. get() 和 load() 的区别？](#8___get_load_618)
*   [9\. 说一下 Hibernate 的缓存机制？](#9____Hibernate__621)
*   [10\. Hibernate 对象有哪些状态？](#10__Hibernate__623)
*   [11\. 在 Hibernate 中 getCurrentSession 和 openSession 的区别是什么？](#11___Hibernate__getCurrentSession__openSession__627)
*   [12\. Hibernate 实体类必须要有无参构造函数吗？为什么？](#12__Hibernate__630)

*   [Mybatis](#Mybatis_633)

*   [1\. Mybatis 中 #{} 和 ${} 的区别是什么？](#1___Mybatis____635)
*   [126\. Mybatis 有几种分页方式？](#126_Mybatis__643)
*   [127\. RowBounds 是一次性查询全部结果吗？为什么？](#127_RowBounds__648)
*   [128\. Mybatis 逻辑分页和物理分页的区别是什么？](#128_Mybatis__650)
*   [129\. Mybatis 是否支持延迟加载？延迟加载的原理是什么？](#129_Mybatis__654)
*   [130\. 说一下 Mybatis 的一级缓存和二级缓存？](#130__Mybatis__657)
*   [131\. Mybatis 和 Hibernate 的区别有哪些？](#131_Mybatis__Hibernate__660)
*   [132\. Mybatis 有哪些执行器（Executor）？](#132_Mybatis_Executor_663)
*   [133\. Mybatis 分页插件的实现原理是什么？](#133_Mybatis__665)
*   [134\. Mybatis 如何编写一个自定义插件？](#134_Mybatis__667)

*   [RabbitMQ](#RabbitMQ_671)

*   [135\. RabbitMQ 的使用场景有哪些？](#135_RabbitMQ__673)
*   [136\. RabbitMQ 有哪些重要的角色？](#136_RabbitMQ__679)
*   [137\. RabbitMQ 有哪些重要的组件？](#137_RabbitMQ__681)
*   [138\. RabbitMQ 中 VHost 的作用是什么？](#138_RabbitMQ__VHost__685)
*   [139\. RabbitMQ 的消息是怎么发送的？](#139_RabbitMQ__687)
*   [140\. RabbitMQ 怎么保证消息的稳定性？](#140_RabbitMQ__689)
*   [141\. RabbitMQ 怎么避免消息丢失？](#141_RabbitMQ__694)
*   [142\. 要保证消息持久化成功的条件有哪些？](#142__697)
*   [143\. RabbitMQ 持久化有什么特点？](#143_RabbitMQ__700)
*   [144\. RabbitMQ 有几种广播类型？](#144_RabbitMQ__703)
*   [145\. RabbitMQ 怎么实现延迟消息队列？](#145_RabbitMQ__708)
*   [146\. RabbitMQ 集群有什么用？](#146_RabbitMQ__713)
*   [147\. RabbitMQ 节点的类型有哪些？](#147_RabbitMQ__715)
*   [148\. RabbitMQ 集群搭建需要注意哪些问题？](#148_RabbitMQ__718)
*   [149\. RabbitMQ 每个节点是其他节点的完整拷贝吗？为什么？](#149_RabbitMQ__721)
*   [150\. RabbitMQ 集群中唯一一个磁盘节点崩溃了会发生什么情况？](#150_RabbitMQ__723)
*   [151\. RabbitMQ 对集群节点停止顺序有要求吗？](#151_RabbitMQ__731)

*   [Kafka](#Kafka_734)

*   [152\. Kafka 可以脱离 ZooKeeper 单独使用吗？为什么？](#152_Kafka__ZooKeeper__736)
*   [153\. Kafka 有几种数据保留的策略？](#153_Kafka__738)
*   [154\. Kafka 同时设置了 7 天和 10G 清除数据，到第五天的时候消息达到了 10G，这个时候 Kafka 将如何处理？](#154_Kafka__7__10G__10G_Kafka__741)
*   [155\. 什么情况会导致 Kafka 运行变慢？](#155__Kafka__745)
*   [156\. 使用 Kafka 集群需要注意什么？](#156__Kafka__747)

*   [ZooKeeper](#ZooKeeper_750)

*   [157\. ZooKeeper 是什么？](#157_ZooKeeper__752)
*   [158\. ZooKeeper 都有哪些功能？](#158_ZooKeeper__754)
*   [159\. ZooKeeper 有几种部署模式？](#159_ZooKeeper__756)
*   [160\. ZooKeeper 怎么保证主从节点的状态同步？](#160_ZooKeeper__760)
*   [161\. 集群中为什么要有主节点？](#161__762)
*   [162\. 集群中有 3 台服务器，其中一个节点宕机，这个时候 ZooKeeper 还可以使用吗？](#162__3__ZooKeeper__764)
*   [163\. 说一下 ZooKeeper 的通知机制？](#163__ZooKeeper___766)

*   [MySQL](#MySQL_768)

*   [164\. 数据库的三范式是什么？](#164__770)
*   [165\. 一张自增表里面总共有 7 条数据，删除了最后 2 条数据，重启 MySQL 数据库，又插入了一条数据，此时 ID 是几？](#165__7__2__MySQL__ID__774)
*   [166\. 如何获取当前数据库版本？](#166__777)
*   [167\. 说一下 ACID 是什么？](#167__ACID__784)
*   [168\. Char 和 VarChar 的区别是什么？](#168_Char__VarChar__791)
*   [169\. Float 和 Double 的区别是什么？](#169_Float__Double__796)
*   [170\. MySQL 的内连接、左连接、右连接有什么区别？](#170_MySQL__799)
*   [171\. MySQL 索引是怎么实现的？](#171_MySQL_804)
*   [172\. 怎么验证 MySQL 的索引是否满足需求？](#172__MySQL_807)
*   [173\. 说一下数据库的事务隔离？](#173__809)
*   [174\. 说一下 MySQL 常用的引擎？](#174__MySQL_811)
*   [175\. 说一下 MySQL 的行锁和表锁？](#175__MySQL_817)
*   [176\. 说一下乐观锁和悲观锁？](#176__821)
*   [177\. MySQL 问题排查都有哪些手段？](#177_MySQL_825)
*   [178\. 如何做 MySQL 的性能优化？](#178__MySQL_833)

*   [Redis](#Redis_855)

*   [179\. Redis 是什么？都有哪些使用场景？](#179_Redis__857)
*   [180\. Redis 有哪些功能？](#180_Redis__861)
*   [181\. Redis 和 MemeCache 有什么区别？](#181_Redis__MemeCache__863)
*   [182\. Redis 为什么是单线程的？](#182_Redis__868)
*   [183\. 什么是缓存穿透？怎么解决？](#183__870)
*   [184\. Redis 支持的数据类型有哪些？](#184_Redis__872)
*   [185\. Redis 支持的 Java 客户端都有哪些？](#185_Redis__Java__874)
*   [186\. Jedis 和 Redisson 有哪些区别？](#186_Jedis__Redisson__876)
*   [187\. 怎么保证缓存和数据库数据的一致性？](#187__879)
*   [188\. Redis 持久化有几种方式？](#188_Redis__881)
*   [189\. Redis 怎么实现分布式锁？](#189_Redis__883)
*   [190\. Redis 分布式锁有什么缺陷？](#190_Redis__888)
*   [191\. Redis 如何做内存优化？](#191_Redis__890)
*   [192\. Redis 淘汰策略有哪些？](#192_Redis__897)
*   [193\. Redis 常见的性能问题有哪些？该如何解决？](#193_Redis__910)

*   [JVM](#JVM_921)

*   [194\. 说一下 JVM 的主要组成部分？及其作用？](#194__JVM__923)
*   [1\. 说一下 JVM 运行时数据区？](#1____JVM__935)
*   [2\. 说一下堆栈的区别？](#2____937)
*   [3\. 队列和栈是什么？有什么区别？](#3____940)
*   [4\. 什么是双亲委派模型？](#4____946)
*   [5\. 说一下类加载的执行过程？](#5____948)
*   [6\. 怎么判断对象是否可以被回收？](#6____951)
*   [7\. Java 中都有哪些引用类型？](#7___Java__953)
*   [8\. 说一下 JVM 有哪些垃圾回收算法？](#8____JVM__959)
*   [9\. 说一下 JVM 有哪些垃圾回收器？](#9____JVM__964)
*   [10\. 详细介绍一下 CMS 垃圾回收器？](#10___CMS__972)
*   [11\. 新生代垃圾回收器和老生代垃圾回收器都有哪些？有什么区别？](#11___975)
*   [206\. 简述分代垃圾回收器是怎么工作的？](#206__984)
*   [207\. 说一下 JVM 调优的工具？](#207__JVM__989)
*   [208\. 常用的 JVM 调优的参数都有哪些？](#208__JVM__994)

*   [杂七杂八](#_1005)

*   [209\. a = a + b 和 a += b 的区别？](#209_a__a__b__a__b__1006)

# <a></a><a id="Java__4" target="_blank"></a>Java 基础

## <a></a><a id="1_JDK__JRE__6" target="_blank"></a>1\. JDK 和 JRE 有什么区别？

<pre>1
2
3
jre是java运行环境的意思，包含java虚拟机和java类库，是使用java语言编写程序是所需要的软件环境
jdk是java开发工具包，是程序员使用java语言编写java程序时所用到的开发包
可以总结一句话，jdk和jre，一个用于开发一个用于运行

</pre>

## <a></a><a id="2___equals__13" target="_blank"></a>2\. == 和 equals 的区别是什么？

<pre>1
2
==是一个比较运算符，基本数据类型比较的是值，引用数据类型比较的是地址
equals是一个方法，只能比较引用数据类型，重写前比较的是地址值，重写后比较的是属性的值

</pre>

## <a></a><a id="3__hashCode_equals_true_18" target="_blank"></a>3\. 两个对象的 hashCode() 相同，则 equals() 也一定为 true，对吗？

不一定为 true。正常情况下，因为 equals() 方法比较的就是对象在内存中的值，如果值相同，那么 Hashcode 值也应该相同。但是如果不重写 hashcode 方法，就会出现不相等的情况。

## <a></a><a id="4_final__Java__22" target="_blank"></a>4\. final 在 Java 中有什么作用？

用于装饰类，类属性，类方法
对于被 final 修饰的类属性而言，子类就不能给它重新赋值，如果重新赋值，会报错

## <a></a><a id="5_Java__Mathround15__26" target="_blank"></a>5\. Java 中的 Math.round(-1.5) 等于多少？

-1

## <a></a><a id="6_String__29" target="_blank"></a>6\. String 属于基础的数据类型吗？

不是， String 是一个对象
基础的数据类型有
int，short，long，char，boolean，float，double，byte，
对应的基本类型的包装类：
Integer，Short，Long，Character，Float，Double，Byte

## <a></a><a id="7_Java__37" target="_blank"></a>7\. Java 中操作字符串都有哪些类？它们之间有什么区别？

<pre>1
2
3
4
5
6
7
8
String字符串的各种操作：
1）获取  length，charAt，indexOf
2）判断  contains，isEmpty，startsWith,endsWith,equals
3)  转换  copyValueOf
4）替换 replace
5）切割  split 
6）子串  subString
7）转换，去空隔，比较  toUpperCase，trim，顺序比较

</pre>

## <a></a><a id="8_String_stri_String_strnew_Stringi_48" target="_blank"></a>8\. String str="i" 与 String str=new String(“i”) 一样吗？

<pre>1
不一样，前一个是常量，后者又重新new了一个对象，内存空间不一样

</pre>

## <a></a><a id="9__52" target="_blank"></a>9\. 如何将字符串反转？

1）递归方法
2）通过 charAt 返回 char 进行字符串的拼接
3) 将字符串转换成字符数组倒叙拼接然后返回值
4）调用 StringBuffer 中的 reverse StringBuffer(s).reverse().toString()
5）把字符串转换成字符数组首位对调位置

## <a></a><a id="10_String__60" target="_blank"></a>10\. String 类的常用方法都有那些？

equals，length，concat，charAt，substring，codePointAt，indexOf。。。。。。

## <a></a><a id="11__65" target="_blank"></a>11\. 抽象类必须要有抽象方法吗？

<pre>1
抽象类不一定要有抽象方法但有抽象方法的类一定是抽象类

</pre>

## <a></a><a id="12__69" target="_blank"></a>12\. 普通类和抽象类有哪些区别？

1）抽象类不能被实例化
2）抽象类可以有构造函数，被继承时子类必须继承父类一个构造方法，抽象方法不能被声明为静态
3）抽象方法只需声明，无需实现，可以允许普通方法有主体
4）含抽象方法的类必须声明为抽象类
5）抽象的子类必须实现抽象类中所有抽象方法，否则这个子类也是抽象类

## <a></a><a id="13__final__77" target="_blank"></a>13\. 抽象类能使用 final 修饰吗？

<pre>1
final关键字不能用来修饰抽象类额接口

</pre>

## <a></a><a id="14__81" target="_blank"></a>14\. 接口和抽象类有什么区别？

1）抽象类和接口都不能直接实例化，如果要实例化，抽象类变量必须实现所有抽象方法的子类对象，接口变量必须指向实现所有接口方法的类对象
2）抽象类要被子类继承，接口要被类实现
3）接口只能做方法申明，抽象类中可以做方法申明，也可以做方法实现
4）接口里定义的变量只能是公共的静态的常量，抽象类中的变量是普通变量
5）抽象类中的抽象方法必须全部被子类实现，如果子类不能实现全部父类抽象方法，那么该子类只能是抽象类。同样，一个实现接口的时候，如果不能全部实现接口方法，那么该类也只能为抽象类
6）抽象方法只能申明，不能实现，接口是设计的结果，抽象类是重构的接果
7）抽象类里可以没有抽象的方法
8) 如果一个类里又抽象方法，那么这个类只能是抽象类
9）抽象方法要被实现，不能是静态的，也不能是私有的
10）接口可以继承接口，并可多继承接口，但类只能单根继承

## <a></a><a id="15_Java__IO__94" target="_blank"></a>15\. Java 中 IO 流分为几种？

根据处理数据类型不同：字符流和字节流
根据数据流向不同：输入流和输出流

## <a></a><a id="16_BIONIOAIO__99" target="_blank"></a>16\. BIO、NIO、AIO 有什么区别？

BIO 和 NIO 是两种不同的网络通信模型，如今 NIO 已经大量应用再 Jetty、zookeeper、Netty 等开源框架中
BIO 适用于连接数目比较小，并且一次发送大量数据的场景，这种方式对服务器资源要求比较高，并发局限于应用中
NIO 服务器需要支持超大量的长时间连接。比如 10000 个连接以上，并且每个客户端并不会频繁地发送太多数据。
BIO：同步阻塞 NIO：同步非阻塞 AIO 异步非阻塞

## <a></a><a id="17_Files_106" target="_blank"></a>17\. Files 的常用方法都有哪些？

<pre>1
2
3
4
5
创建：createNewFile，mkdir，mdirs
删除：delete，deleteOnExit
判断：exists，isFile，isDirectory，isHidden，isAbsolute
获取：getName，getAbsolutePath，length，getParent，lastModified
文件夹相关 startic File

</pre>

# <a></a><a id="_113" target="_blank"></a>容器

## <a></a><a id="18_Java__114" target="_blank"></a>18\. Java 容器都有哪些？

<pre>1
 数组，String，java.util下的集合容器

</pre>

## <a></a><a id="19_Collection__Collections__117" target="_blank"></a>19\. Collection 和 Collections 有什么区别？

<pre>1
2
 java.util.collection是一个集合接口，提供对集合对象进行基本操作的通用接口的方法
 Collections是集合类的一个工具类（辅助类），提供一系列的静态方法，用于对集合中的元素进行排序、搜索以及线程安全各项操作。

</pre>

## <a></a><a id="20_ListSetMap__121" target="_blank"></a>20\. List、Set、Map 之间的区别是什么？

<pre>1
2
3
 list：有序可以重复，可以插入多个null元素
 Set：无序不可以重复，只允许有一个null元素
 map：不是collection的子接口或者实现类，推行键值对，一个key一个value

</pre>

## <a></a><a id="21_HashMap__Hashtable__126" target="_blank"></a>21\. HashMap 和 Hashtable 有什么区别？

<pre>1
2
3
4
5
 1）HashMap和HashTable都实现了map的接口，主要区别在于线程安全性、同步以及速度
 2）Hashmap是非同步的，而hashtable是同步的，意味着哈市table是线程安全的，多个线程可以共享一个hashtable
 3）Hashmap的迭代器是fail-fast迭代器，而hashTable不是
 4）Hashtable在单线程的环境下要比hashmap要慢；如果不需要同步，在单线程环境下，使用hashmap性能比hashtable好
 5）Hashmap不能保证随着时间的推移map中的元素次序是不变的

</pre>

## <a></a><a id="22__HashMap__TreeMap_133" target="_blank"></a>22\. 如何决定使用 HashMap 还是 TreeMap？

<pre>1
 Hashmap通过hascode对其内容进行查找，TreeMap所有的元素都保持某种固定的顺序，

</pre>

## <a></a><a id="23__HashMap__136" target="_blank"></a>23\. 说一下 HashMap 的实现原理？

<pre>1
2
3
4
5
  HashMap由数据和链表组成，数组是hashmap的主体，链表主要是解决哈希冲突而存在的，如果定位到的数组位置不含链表，那么对于查找，添加等操作很快，仅需一次寻址即可；如果定位到的数组包含链表，对于添加操作，其时间复杂度为o，首先遍历链表，存在即覆盖；对于查找操作来讲，还需遍历链表，然后通过key对象的equals方法逐一比对查找。所以，性能考虑，HashMAp中的链表出现越少，性能才会越好。

  1 HashMap由数据和链表组成
  2 链表主要是解决哈希冲突而存在的 对于查找，添加等操作很快
  3 通过key对象的equals方法逐一比对查找，HashMAp中的链表出现越少，性能才会越好

</pre>

## <a></a><a id="24__HashSet__143" target="_blank"></a>24\. 说一下 HashSet 的实现原理？

<pre>1
2
3
4
5
 iterator：返回对此set中元素进行迭代的迭代器
 size：返回此set中元素的数量（set的容量）
 isEmpty：判断Hashset集合是否为空
 contains：判断某个元素是否存在于HashSet中
 add（）：如果此set中尚未包含指定元素，则添加指定元素

</pre>

## <a></a><a id="25_ArrayList__LinkedList__150" target="_blank"></a>25\. ArrayList 和 LinkedList 的区别是什么？

<pre>1
2
 ArrayList使用了数组的实现，封装了对内部数组的操作
 linkedList使用了循环双向链表数据结构

</pre>

## <a></a><a id="26__List__154" target="_blank"></a>26\. 如何实现数组和 List 之间的转换？

<pre>1
2
 list转数组：toArray(arraylist.size)方法
 数组转list：Arrays的asList（a）的方法

</pre>

## <a></a><a id="27_ArrayList__Vector__158" target="_blank"></a>27\. ArrayList 和 Vector 的区别是什么？

<pre>1
2
3
4
5
 1)Vector的方法都是同步的，是线程安全的，而ArrayList的方法不是，由于线程同步必然影响性能，因此，ArrayList的性能比Vector好
 2）当Vector或ArrayList的元素超过它的初始大小时，Vector会将它的容量翻倍，而ArrayList只增加50%的大小，这样，ArrayList就有利于节约内存空间。

 1 Vector的方法都是同步的，是线程安全的 ArrayList的方法不是
 2 Vector会将它的容量翻倍，ArrayList只增加50%的大小

</pre>

## <a></a><a id="28_Array__ArrayList__165" target="_blank"></a>28\. Array 和 ArrayList 有何区别？

<pre>1
2
3
4
 1）ArrayList是Array的复杂版本
 2）ArrayList可以存储异构对象，而Array只能存储相同类型的数据 [1,a,true] [1,2,3,4]
 3）Array的长度实际上是不可变的，二维变长数组实际上的长度也是固定的，可变的是其中元素的长度而ArrayList的长度可以指定也可以不指定，是变长的
 4）存取和增删的不同

</pre>

## <a></a><a id="29__Queue__poll_remove_171" target="_blank"></a>29\. 在 Queue 中 poll() 和 remove() 有什么区别？

<pre>1
 remove和poll方法都是删除队列的头元素，remove方法在队列为空的情况下将抛出异常，而poll方法将返回null

</pre>

## <a></a><a id="30__174" target="_blank"></a>30\. 哪些集合类是线程安全的？

<pre>1
 Vector，HashTable，ConcurrentHashMap，Stack

</pre>

## <a></a><a id="31__Iterator__177" target="_blank"></a>31\. 迭代器 Iterator 是什么？

<pre>1
 为各种容器提供了公共的操作接口，使得对容器的遍历操作与其具体的底层实现相隔离，达到解耦的效果。

</pre>

## <a></a><a id="32_Iterator__180" target="_blank"></a>32\. Iterator 怎么使用？有什么特点？

<pre>1
2
3
4
5
6
7
8
9
10
11
12
  Iterator的常用方法：
         1.boolean hasNext（）
         2.Object next（）
         3.void remove()
         4.void forEachRemaining(Consumer action)
  Iterator的特点：
          遍历过程：
         1.不允许线程对集合元素进行修改，否则会抛出异常
         2.可以通过remove方法来移除集合中的元素
         3.Iterator必须依附某个Collection对象而存在，本身不具有装载数据对象的功能
         4.Iterator.remove方法删除的是上一次Iterator.next()方法返回的对象
         5.强调以下next（）方法，该方法通过游标指向的形式返回Iterator下一个元素

</pre>

## <a></a><a id="33_Iterator__ListIterator__193" target="_blank"></a>33\. Iterator 和 ListIterator 有什么区别？

<pre>1
2
3
4
5
     1.iterator（）方法在set和list接口中都有定义，但li's'tIterator()仅存在于list接口中（或者实现类中）
     2.listIterator有add（）方法，可以向list中添加对象，而Iterator不能
     3.两个都有hasNext（）和next（）方法，可以实现顺序向后遍历，但listIterator有hasPrevious（）和previous（）方法，可以实现逆向遍历，Iterator就不可以。
     4.listIterator可以定位当前的索引位置，nextIndex（）和previousIndex（）可以实现，Iterator没有此功能
     5.都可以实现删除对象，但listIterator可以实现对象的修改，set方法可以实现，Iterator仅能遍历，不可以修改    

</pre>

## <a></a><a id="34__199" target="_blank"></a>34\. 怎么确保一个集合不能被修改？

<pre>1
 将参数中的List返回一个不可修改的List

</pre>

# <a></a><a id="_203" target="_blank"></a>多线程

## <a></a><a id="35__205" target="_blank"></a>35\. 并行和并发有什么区别？

<pre>1
2
 并发：指应用能够交替执行不同的任务，其实并发有点类似于多线程的原理
 并行：指应用能够同时执行不同的任务。

</pre>

## <a></a><a id="36__209" target="_blank"></a>36\. 线程和进程的区别？

<pre>1
2
3
4
5
6
 主要区别在于它们是不同的操作系统资源管理方式。
 1）一个程序至少有一个进程，一个进程至少有一个线程
 2）线程的划分尺度小于进程，使得多线程程序的并发性高
 3）进程在执行过程中拥有独立的内存单元，而多个线程共享内存，从而大大地提高了程序的运行效率
 4）每个独立的线程有一个程序运行的入口、顺序执行序列和程序的出口。但线程不能够独立执行，必须依存于应用程序中，由应用程序提供多个线程执行控制。
 5）多线程的意义在于一个应用程序中，有多个执行部分可以同时执行，但操作系统并没有将多个线程看做多个独立的应用，来实现进程的调度和管理以及资源分配。

</pre>

## <a></a><a id="37__217" target="_blank"></a>37\. 守护线程是什么？

<pre>1
2
3
  守护线程，是一个服务线程，准确的来说是服务其他的线程，另一个线程是用户线程
  守护线程如垃圾回收线程
  用户线程就是应用程序里的自定义线程	

</pre>

## <a></a><a id="38__222" target="_blank"></a>38\. 创建线程有哪几种方式？

<pre>1
2
 继承Thread类
 实现runable接口

</pre>

## <a></a><a id="39__runnable__callable__226" target="_blank"></a>39\. 说一下 runnable 和 callable 有什么区别？

<pre>1
2
 最大的不同点：实现Callable接口的任务线程能返回执行结果，而实现runable接口的任务线程不能返回结果
 Callable接口的call（）方法允许抛出异常，而Runable接口的run（）方法的异常只能在内部消化，不能继续上抛.

</pre>

## <a></a><a id="40__231" target="_blank"></a>40\. 线程有哪些状态？

<pre>1
2
3
4
5
 1.新建状态
 2.就绪状态
 3.运行状态
 4.阻塞状态
 5.死亡状态

</pre>

## <a></a><a id="41_sleep__wait__238" target="_blank"></a>41\. sleep() 和 wait() 有什么区别？

<pre>1
2
对于sleep（）方法，是属于Thread类，而wait（）方法属于Object类
调用sleep（）方法时，线程不会释放对象锁，而调用wait（）方法时，线程会放弃对象锁，进入此对象的等待锁定池，只有针对此对象调用nofity（）方法后本线程才进入对象锁定池准备

</pre>

## <a></a><a id="42_notify_notifyAll_242" target="_blank"></a>42\. notify() 和 notifyAll() 有什么区别？

<pre>1
 notifyAll()：唤醒所有wait线程    notify：只随机唤醒一个wait线程

</pre>

## <a></a><a id="43__run_start_245" target="_blank"></a>43\. 线程的 run() 和 start() 有什么区别？

<pre>1
2
 run（）相当于线程的任务处理逻辑入口方法，由java虚拟机在运行相应线程时直接调用，而不是由应用代码进行调用
start（）的作用是启动相应的线程。start（）调用结束并不表示相应的线程已经开始，这个线程可能稍后运行，也有可能永远也不会运行

</pre>

## <a></a><a id="44__249" target="_blank"></a>44\. 创建线程池有哪几种方式？

1.newCachedThreadPool
2.newFixedThreadPool
3.newSingleThreadExecutor
4.newSingleThreadScheduledExecutor 和 newScheduledThreadPool(int corePoolSize)
5.newWorkStealingPool

## <a></a><a id="45__256" target="_blank"></a>45\. 线程池都有哪些状态？

Running、ShutDown、Stop、Tidying、Terminated

## <a></a><a id="46__submit_execute_259" target="_blank"></a>46\. 线程池中 submit() 和 execute() 方法有什么区别？

1\. 接收的参数不一样
2.submit 有返回值，而 execute 没有
3.submit 方便 Exception 处理

## <a></a><a id="47__Java__264" target="_blank"></a>47\. 在 Java 程序中怎么保证多线程的运行安全？

线程的安全性体现在三个方面：原子性，可见性，有序性

## <a></a><a id="48__267" target="_blank"></a>48\. 多线程锁的升级原理是什么？

在 java 中锁共有 4 种状态，由低到高依次为无状态锁，偏向锁，轻量级锁和重量级锁状态，这几个状态会随着竞争情况升级。锁可以升级但不能降级。

## <a></a><a id="49__270" target="_blank"></a>49\. 什么是死锁？

指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去。

## <a></a><a id="50__273" target="_blank"></a>50\. 怎么防止死锁？

1\. 加锁顺序 2\. 加锁时限 3\. 死锁检测

## <a></a><a id="51_ThreadLocal__276" target="_blank"></a>51\. ThreadLocal 是什么？有哪些使用场景？

ThreadLocal 叫做线程本地变量，也有叫做线程本地存储，即为变量在每个线程中创建了一个副本，每个线程可以访问自己内部的副本变量
最常见的 ThreadLocal 使用场景为用来解决数据库连接、session 管理等

## <a></a><a id="52__Synchronized__280" target="_blank"></a>52\. 说一下 Synchronized 底层实现原理？

<pre>1
2
3
4
方法级的同步是隐式，即无需通过字节码指令来控制的，它实现在方法调用和返回操作之中。jvm可以从方法常量池中的方法表结构中的ACC_SYNCHRONIZED访问标志区分一个方法是否是同步方法。当方法调用时，调用指令将会检查方法的ACC_SYNCHRONIZED访问标志是否被设置，如果设置了，执行线程将先持有monitor，然后再执行方法，最后在方法完成时释放monitor。在方法执行期间，执行线程持有了monitor，其他任何线程都无法再获得同一个monitor。如果一个同步方法执行期间抛出了异常，并且在方法内部无法处理此异常，那这个同步方法所持有的monitor将在异常抛到同步方法之外时自动释放。
1.同步 过字节码指令来控制
2.jvm 从方法常量池中 访问标志区分一个方法是否是同步方法 调用指令 检查方法的ACC_SYNCHRONIZED访问标志是否被设置
3.获取锁，其他线程无法获取锁

</pre>

## <a></a><a id="53_Synchronized__volatile__287" target="_blank"></a>53\. Synchronized 和 volatile 的区别是什么？

*   volatile 本质是在告诉 Jvm 当前变量在寄存器（工作内存）中的值是不确定的，需要从主存中读取；synchronized 则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞
    *volatile 仅能使用在变量级别；synchronized 则可以使用在变量、方法和类级别的
    *volatile 仅能实现变量的修改可见性，不能保证原子性；而 synchronized 则可以保证变量的修改可见性和原子性
    *volatile 不会造成线程的阻塞；synchronized 可能会造成线程的阻塞
    *volatile 标记的变量不会被编译器优化；synchronized 标记的变量可以被编译器优化

## <a></a><a id="54_Synchronized__Lock__294" target="_blank"></a>54\. Synchronized 和 Lock 有什么区别？

* 首先 synchronized 是 java 内置关键字，在 jvm 层面，lock 是个 java 类
*synchronized 无法判断是否获取锁的状态，lock 可以判断是否获取到锁
*synchronized 会自动释放锁，lock 需在 finally 中手工释放锁，否则容易造成线程死锁
* 用 sychronized 关键字的两个线程 1 和线程 2，如果当前线程 1 获得锁，线程 2 等待。如果线程 1 阻塞，线程 2 会一直等待下去，而 Lock 锁就不一定会等待下去，如果尝试获取不到锁，线程可以不用一直等待就结束了
*synchronized 的锁可重入、不可中断、非公平，而 Lock 锁皆可实行
*Lock 锁适合大量同步的代码的同步问题，synchronized 锁适合代码少量的同步问题

## <a></a><a id="55_Synchronized__ReentrantLock__302" target="_blank"></a>55\. Synchronized 和 ReentrantLock 区别是什么？

这两种方式最大区别就是对于 Synchronized 来说他是 java 语言的关键字，是原生语法层面的互斥，需要 jvm 实现。而 reemntrantLock 它是 jDK1.5 之后提供的 API 层面的互斥锁，需要 lock（）和 unlock（）方法配合 try/finally 语句块来完成。

## <a></a><a id="56__Atomic__305" target="_blank"></a>56\. 说一下 Atomic 的原理？

使用 atomic 修饰属性，编译器会设置默认读写方法为原子读写，并使用互斥锁添加保护。

# <a></a><a id="_309" target="_blank"></a>反射

## <a></a><a id="1___311" target="_blank"></a>1\. 什么是反射？

指运行中的程序检查自己和软件运行环境的能力，他可以根据它发现的进行改变。通俗的讲就是反射可以在运行时根据指定的类名获得类的信息。

## <a></a><a id="58__Java__314" target="_blank"></a>58\. 什么是 Java 序列化？什么情况下需要序列化？

是一种用来处理对象流的机制，对象流也就是将对象的内容进行流化，将数据分解成字节流，以便存储在文件中或在网络中传输。
情况：1\. 对象序列化可以实现分布式对象
2.java 对象序列化不仅保留一个对象的数据，而且递归保存对象引用的每一个对象的数据

## <a></a><a id="59__319" target="_blank"></a>59\. 动态代理是什么？有哪些应用？

<pre>1
   当想要给实现了某个接口的类中的方法，加一些额外的处理，比如加日志，加事务，加权限，Spring的AOP等。    

</pre>

## <a></a><a id="60__322" target="_blank"></a>60\. 怎么实现动态代理？

<pre>1
    首先必须定义一个接口，还要有一个InvocationHandler处理类。再有一个工具类Proxy。利用到InvacationHandler，并接代理类源码，将其编译生成代理类的二进制码，利用机器加载，并将其实例化产生代理对象，最后返回

</pre>

# <a></a><a id="_325" target="_blank"></a>对象拷贝

## <a></a><a id="61__327" target="_blank"></a>61\. 为什么要使用克隆？

<pre>1
 想对一个对象进行处理，又想保留原有的数据进行接下来的操作，就需要克隆了。

</pre>

## <a></a><a id="62__329" target="_blank"></a>62\. 如何实现对象克隆？

<pre>1
2
3
 两种不同的克隆方式，浅克隆（ShallowClone）和深克隆（DeepClone）
 两种方式：实现Cloneable接口并重写object类中的clone（）方法
                 实现Serializable接口，通过对象的序列化和反序列化实现克隆，可以实现真正的深度克隆

</pre>

## <a></a><a id="63__334" target="_blank"></a>63\. 深拷贝和浅拷贝区别是什么？

<pre>1
2
 浅克隆只是复制了对象的引用地址，两个对象指向同一个内存地址，所以修改其中任意的值，另一个值都会随之变化，这就是浅拷贝
 深拷贝是将对象及值复制过来，两个对象修改其中任意的值另一个值不会改变，这就是深拷贝

</pre>

# <a></a><a id="Java_Web_338" target="_blank"></a>Java Web

## <a></a><a id="64_JSP__servlet__340" target="_blank"></a>64\. JSP 和 servlet 有什么区别？

<pre>1
2
3
4
 jsp的本质是servlet，jvm只能识别java类，不能识别jsp的代码，web容器将jsp的代码编译成jvm额能够识别的java类
 jsp更擅长表现于页面显示，servlet更擅长于逻辑控制
 Servlet中没有内置对象，jsp的内置对象都是通过HTTPServletRequest对象，HttpServletREsponse对象以及HttpServlet对象得到的
 jsp是Servlet的一种简化，使用jsp只需完成程序员需要输出到客户端的内容，jsp中的java脚本如何镶嵌到一个类中，youjsp容器完成

</pre>

## <a></a><a id="65_JSP__346" target="_blank"></a>65\. JSP 有哪些内置对象？作用分别是什么？

<pre>1
2
3
4
5
6
7
8
9
 page：指的是JSP被翻译成Servlet的对象的引用
 pageContext：对象可以用来获得其他8个内置对象，还可以作为Jsp的域范围对象使用
 request：代表的是请求对象，可以用于获得客户机的信息，也可以作为域对象使用，使用request保存的数据在一次请求范围内有效
 Session：代表的是一次会话，可以用来保存用户的私有信息，也可以作为域对象使用，使用session保存的数据在一次会话范围有效
 Application：代表整个应用范围，使用这个对象保存的数据在整个web应用中都有效
 Response：是响应对象，代表的是从服务器向浏览器响应数据
 Out：jspWrite是用于向页面输出内容的对象
 Config：指的是ServletConfig用于JSP翻译成Servlet后获得Servlet的配置的对象
 Exception：在页面设置isErrorPage=“true”，即可使用，是Throwable的引用，用来获得页面错误的信息

</pre>

## <a></a><a id="66__JSP__4__357" target="_blank"></a>66\. 说一下 JSP 的 4 种作用域？

<pre>1
 page，request，session，application

</pre>

## <a></a><a id="67_Session__Cookie__360" target="_blank"></a>67\. Session 和 Cookie 有什么区别？

<pre>1
2
3
4
  1.Cookie以文本文件格式存储在浏览器中，而session存储在服务器端它存储了限制数据量。它只允许4kb他没有在cookie中保存多个变量
  2.cookie的存储限制了数据量，只允许4kb，而session是无限量的
  3.我们可以轻松地访问cookie值但是我们无法轻松访问会话值，因此它更安全
  4.设置cookie时间可以使cookie过期，但是使用session-destory（），将会销毁会话

</pre>

## <a></a><a id="68__Session__366" target="_blank"></a>68\. 说一下 Session 的工作原理？

<pre>1
  当打开浏览器到关闭浏览器，这一期间称为一个会话，也就是一个session，是保存在服务器端的，每当客户端请求页面时，服务器就会自动分配一个ID来唯一识别这个用户。一般不用session来保存大量的数据，这样会占用大量的资源。

</pre>

## <a></a><a id="69__Cookie__Session__368" target="_blank"></a>69\. 如果客户端禁止 Cookie 能实现 Session 还能用吗？

<pre>1
2
  在Asp中，Session必须依赖Cookie才可用，session是存储在服务器端的，而Cookie是存储在客户端的，相对而言，session的那全性和可靠性程度都比cookie高
  在Php中通过相关额配置，可以让session不依赖Cookie而存在

</pre>

## <a></a><a id="70_Spring_MVC__Struts__372" target="_blank"></a>70\. Spring MVC 和 Struts 的区别是什么？

1\. 底层实现机制：struts2 是 filter springmvc 是 servlet
2\. 运行效率: struts2 底层是 Servlet，参数基于属性封装，如果配置单例，会出现线程安全问题，所以配置多例
springmvc 底层是 servlet，单例
3\. 参数封装：struts2 基于属性封装
springMvc 基于方法进行封装

## <a></a><a id="71__SQL__379" target="_blank"></a>71\. 如何避免 SQL 注入？

<pre>1
2
3
4
5
6
7
8
9
               1.检查变量数据类型和格式
               2.过滤特殊字符
               3.绑定变量，使用预编译语句
  总结：使用预编译绑定变量的sql语句
           严格加密处理用户的机密信息
           不要随意开启生产环境中的webserver的错误信息
           使用正则表达式过滤传入的参数
           字符串过滤
           检查是否包含非法字

</pre>

## <a></a><a id="72__XSS__390" target="_blank"></a>72\. 什么是 XSS 攻击，如何避免？

即跨站脚本攻击，是一种常见于 web 应用于 web 应用程序中的计算机安全漏洞
1\. 获取用户输入，不用. innerHtml, 用 innerText
2\. 对用户输入进行过滤，如 HtmlEncode 函数实现应该至少进行 &<>"’/ 等符号转义成 & It&gt&quot&#x27&#x2F

## <a></a><a id="73__CSRF__395" target="_blank"></a>73\. 什么是 CSRF 攻击，如何避免？

即跨站请求伪造
1\. 请求令牌
2.token 验证
3\. 配置 angular 提交表头
4\. 再次测试跨域 post
异常

## <a></a><a id="74_throw__throws__403" target="_blank"></a>74\. throw 和 throws 的区别？

<pre>1
2
3
4
5
 1.throw是在代码块内的，即在捕获方法内的异常并抛出时用的
try{} cacth(Exce e){ throw new Exc()}
 2.throws是针对方法的，即将方法的异常信息抛出去
void main() throws Ex
 3.可以理解为throw是主动的，而throws是被动的

</pre>

## <a></a><a id="75_finalfinallyfinalize__410" target="_blank"></a>75\. final、finally、finalize 有什么区别？

<pre>1
2
3
 final：用来修饰类，方法和变量
 finally：作为异常的处理部分，只能在try/catch语句中，表示这段语句最终一定会被执行
 finalize：是在java.lang.Object里定义的，也就是说每一个对象都有这么个方法，该对象被回收时被调用。

</pre>

## <a></a><a id="76_trycatchfinally__415" target="_blank"></a>76\. try-catch-finally 中哪个部分可以省略？

<pre>1
 try语句后面必须要有一个别的语句跟在后面，如try-catch，try-finally，try-catch-finally

</pre>

## <a></a><a id="77_trycatchfinally__catch__return_finally__418" target="_blank"></a>77\. try-catch-finally 中，如果 catch 中 return 了，finally 还会执行吗？

<pre>1
 会执行，在return前执行

</pre>

## <a></a><a id="78__421" target="_blank"></a>78\. 常见的异常类有哪些？

<pre>1
  IOEException、RunntimeException、ArrayIndexOUtOfBoundsException、NullpointerException

</pre>

# <a></a><a id="_424" target="_blank"></a>网络

## <a></a><a id="79_HTTP__301__302__426" target="_blank"></a>79\. HTTP 响应码 301 和 302 代表的是什么？有什么区别？

<pre>1
2
3
 301，302都是Http状态的编码，都代表着某个URL发生了转移
 区别：    301 redirect：代表永久性转移
              302 redirect：代表暂时性转移

</pre>

## <a></a><a id="80_forward__redirect__431" target="_blank"></a>80\. forward 和 redirect 的区别？

<pre>1
2
3
  代表了两种请求转发方式：直接转发（forward）和间接转发（redirect）
  举例：直接：A找B借钱，B说没有，B去找C借，借到借不到都会把信息传递给A
            简接：A找B借钱，B说没有，让A去找C借

</pre>

## <a></a><a id="81__TCP__UDP__436" target="_blank"></a>81\. 简述 TCP 和 UDP 的区别？

<pre>1
2
3
4
5
  1.基于连接与无连接
  2.对于系统资源的要求（TCP较多，UDP少）
  3.UDP程序结构较为简单
  4.流模式与数据包模式
  5.TCP保证数据正确性，UDP可能丢包，TCP保证数据顺序，UDP不保证

</pre>

## <a></a><a id="82_TCP__443" target="_blank"></a>82\. TCP 为什么要三次握手，两次不行吗？为什么？

<pre>1
  不行，因为为了防止已失效的连接请求又传送到服务器端，因而产生错误

</pre>

## <a></a><a id="83__TCP__446" target="_blank"></a>83\. 说一下 TCP 粘包是怎么产生的？

<pre>1
2
 要发送的数据小于TCP发送缓冲区的大小，TCP将多次写入缓冲区的数据一次发送出去，将会发生粘包
 接收数据端的应用层没有及时读取接收缓冲区中的数据，将会发生粘包

</pre>

## <a></a><a id="84_OSI__450" target="_blank"></a>84\. OSI 的七层模型都有哪些？

<pre>1
 应用层，表示层，会话层，传输层，网络层，数据链路层，物理层

</pre>

## <a></a><a id="85_Get_Post__453" target="_blank"></a>85\. Get 和 Post 请求有哪些区别？

<pre>1
2
 Get：从指定的资源请求数据
 post：向指定的资源提交要被处理的数据

</pre>

## <a></a><a id="86__457" target="_blank"></a>86\. 如何实现跨域？

<pre>1
2
3
4
5
6
7
8
 1.图片ping或script标签跨域
 2.JSONP跨域
 3.CORS
 4.window.name+iframe
 5.window.postMessage()
 6.修改document.domain跨子域
 7.WebSocket
 8.代理

</pre>

## <a></a><a id="87__JSONP__466" target="_blank"></a>87\. 说一下 JSONP 实现原理？

<pre>1
  浏览器只对XHR请求有同源请求限制，而对script标签src属性、link标签ref属性和img标签src属性没有这种限制，利用这个“漏洞”就可以很好的解决跨域请求。JSONP就是利用了script标签无同源限制的特点来实现的，当向第三方站点请求时，我们可以将此请求放在<script>标签的src属性里，这就如同我们请求一个普通的JS脚本，可以自由的向不同的站点请求。

</pre>

# <a></a><a id="_469" target="_blank"></a>设计模式

## <a></a><a id="88__471" target="_blank"></a>88\. 说一下你熟悉的设计模式？

<pre>1
      工厂方法模式，抽象工厂模式，单例工模式

</pre>

## <a></a><a id="89__473" target="_blank"></a>89\. 简单工厂和抽象工厂有什么区别？

<pre>1
2
      简单工厂就是一个专门生产某个产品的类
      抽象工厂不仅生产鼠标，同时生产键盘

</pre>

# <a></a><a id="SpringSpring_MVC_477" target="_blank"></a>Spring/Spring MVC

## <a></a><a id="90__Spring_479" target="_blank"></a>90\. 为什么要使用 Spring？

<pre>1
2
  Spring带来了它的两大特征AOP和IOC，如果没有Spring，我们不得不在使用每一个类之前，实例化一个对象。当然我们可以用工厂方法来做这件事，就可以集中管理并且让调用者和被调用者之间的耦合松散。于是需要大量的工厂类，并且在增加或者改变接口实现的时候，还需要对工厂进行调整。而Spring就像一个大工厂一样，使用了大量的反射机制来生成需要实例的对象。
  除此以外Spring还带来了强大的代理，我们使用的每一个注入的对象都是经过代理的增强对象，同时可以使用aop包来定义一些与业务逻辑不相关的切面。增强功能模块的内聚，拆分功能模块和非业务模块。而Aop又是建立在IOP基础上，因此如果没有Spring，功能模块和非功能模块混在一起，导致逻辑混乱不清晰。

</pre>

## <a></a><a id="91__AOP_482" target="_blank"></a>91\. 解释一下什么是 AOP？

<pre>1
  面向切面编程，可以说是OOP的补充与完善。实现AOP的技术，主要分为两大类：一是采用动态代理技术，利用截取消息的方式，对该消息进行装饰，以取代原有对象行为的执行；二是采用静态织入的方式，引入特定的语法创建方面，从而使得编译器可以在编译期间织入有关方面的代码

</pre>

## <a></a><a id="92__IOC_484" target="_blank"></a>92\. 解释一下什么是 IOC？

<pre>1
 控制反转，即借助于第三方实现具有依赖关系的对象之间的解耦

</pre>

## <a></a><a id="93_Spring__486" target="_blank"></a>93\. Spring 有哪些主要模块？

<pre>1
2
3
4
5
6
7
8
 七大功能模块：
                     一、Spring Core：Core模块是Spring的核心类库，Spring的所有功能都依赖于该类库，Core主要实现IOC功能，Spring的所有功能都是借助IOC实现的。
                     二、AOP：是Spring的AOP库，提供了AOP（拦截器）机制，并提供常用的拦截器，供用户自定义和配置
                     三、ORM：提供对常用的ORM框架的管理和辅助支持，Spring支持常用的Hibernate，mybatis，jdao等框架的支持，Spring本身并不对ORM进行实现，仅对常见的ORM框架可进                   行封装，并对其进行管理。
                     四、DAO：Spring提供对JDBC的支持，对JDBC进行封装，允许JDBC使用Spring资源，并能统一管理JDBC事务，并不对JDBC进行实现。
                     五、MVC：WebMVC模块为Spring提供了一套轻量级的MVC实现，在Spring的开发中，我们既可以Struts也可以用Spring自己的MVC框架，相对于Struts，Spring自己的MVC框                   架更加简洁和方便。
                     六、WEB：常见的框架如Struts1，struts2,jsf的支持，Spring能够管理这些框架，将Spring的资源注入给框架，也能在这些框架的前后插入拦截器
                     七、Context：提供框架式的Bean访问方式，其他程序可以通过Context访问Spring的Bean资源，相当于资源注入。

</pre>

## <a></a><a id="94_Spring__495" target="_blank"></a>94\. Spring 常用的注入方式有哪些？

<pre>1
  构造方法的注入、setter注入、基于注解的注入

</pre>

## <a></a><a id="95_Spring__Bean__497" target="_blank"></a>95\. Spring 中的 Bean 是线程安全的吗？

<pre>1
2
3
4
  Spring容器本身并没有提供Bean的线程安全策略
  对于线程安全问题，要从原型和单例分别说明：
        对于原型Bean，每次创建一个新对象，也就是线程之间并不存在Bean共享，自然不会有线程安全的问题
        对于单例Bean，如果单例Bean是一个无状态的Bean，也就是线程中的操作不会对Bean的成员执行查询以外的操作，那么这个单例Bean是线程安全的。

</pre>

对于有状态的 bean，Spring 官方提供的 bean，一般提供了通过 ThreadLocal 去解决线程安全的方法

## <a></a><a id="96_Spring__Bean__504" target="_blank"></a>96\. Spring 支持几种 Bean 的作用域？

<pre>1
2
3
4
5
   singleton：单例模式，在整个Spring  ioc容器中，使用singleton定义的Bean将只有一个实例
   prototype：原型模式，每次通过容器的getBean方法获取prototype定义的Bean时，将会产生一个新的Bean实例
   request：对于每次HTTP 请求，使用request定义的bean都将会产生一个新实例
   session：对于每次HTTP Session，使用session定义的bean都将产生一个新实例
   globalsession：每一个全局的HTTP Session，使用session定义的bean都将产生一个新实例

</pre>

## <a></a><a id="97_Spring__Bean__511" target="_blank"></a>97\. Spring 自动装配 Bean 有哪些方式？

<pre>1
 使用XML配置自动装配Bean、使用@Autowired和@Resource注解来自动装配Bean

</pre>

## <a></a><a id="98_Spring__514" target="_blank"></a>98\. Spring 事务实现方式有哪些？

<pre>1
2
3
4
 1）编程式事务管理对基于POJO的应用来说是唯一选择
 2）基于TransactionProxyFactoryBean的声明式事务管理
 3）基于@Transactional的声明式事务管理
 4）基于Aspectj AOP配置事务

</pre>

## <a></a><a id="99__Spring__520" target="_blank"></a>99\. 说一下 Spring 的事务隔离？

<pre>1
2
  在一个典型的应用程序中，多个事务同时进行，经常会为了完成他们的工作而操作同一个数据。并发虽然是必须的，但会导致脏读，不可重复读和幻影读
  在理想状态下，事务之间将完全隔离，从而防止这些问题的发生。然而，完全隔离会影响性能，因为隔离经常牵扯到锁定在数据库中的记录

</pre>

## <a></a><a id="100__Spring_MVC__524" target="_blank"></a>100\. 说一下 Spring MVC 运行流程？

<pre>1
2
3
4
5
6
7
8
9
10
11
   1）用户发送请求到DispatchServlet
   2）DispatchServlet根据请求路径查询具体的Handler
   3）HandlerMapping返回一个HandlerExcutionChain给DispatchServlet
   4）DispatchServlet调用HandlerAdapter适配器
   5）HandlerAdapter调用具体的Handler处理业务
   6）Handler处理结束返回一个具体的ModelAndView给适配器
   7）适配器将ModelAndView给DispatchServlet
   8）DispatchServlet把视图名称给viewResolver视图解析器
   9）viewREsource返回一个具体的视图给DispatchServlet
  10）渲染视图
  11）展示给用户

</pre>

## <a></a><a id="101_Spring_MVC__536" target="_blank"></a>101\. Spring MVC 有哪些组件？

<pre>1
  Handlermapping、HandlerAdapter、HandlerExceptionREsolver、viewResolver、RequestToViewNameTranslator、LocalResolver、ThemeResolver、MultipartResolver、FlashMapManager

</pre>

## <a></a><a id="102_RequestMapping__539" target="_blank"></a>102\. @RequestMapping 的作用是什么？

<pre>1
   用来处理请求地址映射的注解，可用于类和方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径

</pre>

## <a></a><a id="103_Data__542" target="_blank"></a>103\. @Data 的作用是什么？

<pre>1
 Spring可以自动帮你把Bean里面引用的对象的Setter/getter方法省略，它会自动帮你set/get

</pre>

# <a></a><a id="Spring_BootSpring_Cloud_545" target="_blank"></a>Spring Boot/Spring Cloud

## <a></a><a id="104__Spring_Boot_547" target="_blank"></a>104\. 什么是 Spring Boot？

<pre>1
    Springboot是一个框架，一种全新的编程规范，它的产生简化了框架的使用，所谓简化是指简化了Spring众多框架中所需的大量且繁琐的配置文件，所有SpringBoot是一个服务于框架的框架，服务范围是简化配置文件

</pre>

## <a></a><a id="105__Spring_Boot_550" target="_blank"></a>105\. 为什么要用 Spring Boot？

<pre>1
    让文件配置变得相当简单、让应用部署变得简单，可以快速开启一个Web容器进行开发。

</pre>

## <a></a><a id="106_Spring_Boot__553" target="_blank"></a>106\. Spring Boot 核心配置文件是什么？

<pre>1
   bootstrap（.yml或者.properties）、application（.yml或者.properties）

</pre>

## <a></a><a id="107_Spring_Boot__556" target="_blank"></a>107\. Spring Boot 配置文件有哪几种类型？它们有什么区别？

<pre>1
  注解，获取Springboot的环境变量

</pre>

## <a></a><a id="108_Spring_Boot__559" target="_blank"></a>108\. Spring Boot 有哪些方式可以实现热部署？

<pre>1
2
  Spring Loaded
  Spring-boot-devtools

</pre>

## <a></a><a id="109_JPA__Hibernate__563" target="_blank"></a>109\. JPA 和 Hibernate 有什么区别？

<pre>1
2
3
4
   Hiberante是JPA规范的一个具体实现
   Hibrenate有jpa没有的特性
   Hinernate的效率更快
   JPA有更好的移植性，通用性

</pre>

## <a></a><a id="110__Spring_Cloud_569" target="_blank"></a>110\. 什么是 Spring Cloud？

<pre>1
2
3
4
   Spring Cloud是一个微服务框架，相比Dubbo等RPC框架，Spring Cloud提供的全套的分布式系统的解决方案
   SpringCloud对微服务基础框架Netflix的多个开源组件进行了封装，同时又实现了和云端平台以及和Spring  boot开发框架的集成
   Spring Cloud为微服务架构开发涉及的配置管理，服务治理，熔断机制，智能路由，微代理，控制总线，一次性Token，全局一致性锁，leader选举，分布式session，集群状态管理等操作提供了一种简单的开发方式
   SpringCloud为开发者提供了快速构建分布式系统的工具，开发者可以快速的启动服务和构建应用、同时能够快速和云平台进行对接。

</pre>

## <a></a><a id="111_Spring_Cloud__575" target="_blank"></a>111\. Spring Cloud 断路器的作用是什么？

<pre>1
   为了防止在分布式系统中出现这种瀑布似的连锁反应导致的灾难

</pre>

## <a></a><a id="1___Spring_Cloud__577" target="_blank"></a>1\. Spring Cloud 的核心组件有哪些？

<pre>1
2
3
4
5
   服务发现---Netflix  EureKa
   客服端负载均衡---Netflix Ribbon
   断路器---Netflix   Hystrix
   服务网关---Netflix Zuul
   分布式配置---Spring Cloud Config

</pre>

# <a></a><a id="Hibernate_584" target="_blank"></a>Hibernate

## <a></a><a id="1____Hibernate_586" target="_blank"></a>1\. 为什么要使用 Hibernate？

<pre>1
2
3
4
   1）对JDBC访问数据库的代码进行了封装，大大简化了数据访问层繁琐的重复性代码
   2）Hibernate是一个基于JDBC的主流持久化框架，是一个优秀的ORM实现，很大程度的简化了Dao层的编码工作
   3）hibrenate使用了java的反射机制，而不是字节码增强程序来实现透明性
   4）Hibrenate的性能非常好，因为它是一个轻量级的框架。映射的灵活性很出色，支持各种关系数据库，从一对多到多对多的各种复杂关系

</pre>

## <a></a><a id="2____ORM__591" target="_blank"></a>2\. 什么是 ORM 框架？

<pre>1
   通过描述对象和数据库之间的元数据，将程序的对象自动持久化到关系数据库中

</pre>

## <a></a><a id="3___Hibernate__SQL__593" target="_blank"></a>3\. Hibernate 中如何在控制台查看打印的 SQL 语句？

<pre>1
2
3
4
5
   使用application.properties配置文件，使用yml也可以达到同样的效果
   在jpa下一级不直接是hibernate，而是properties
        spring.jpa.properties.hibernate.show_sql=true     //控制台是否打印
        spring.jpa.properties.hibernate.format_sql=true    //格式化sql语句
        spring.jpa.properties.hibernate.use_sql_comments=true    //指出是什么操作生成了该语句

</pre>

## <a></a><a id="4___Hibernate__599" target="_blank"></a>4\. Hibernate 有几种查询方式？

<pre>1
2
3
       一、Hql查询
       二、QBC（Query by  Criteria）查询
       三、原生SQL查询

</pre>

## <a></a><a id="5___Hibernate__final__603" target="_blank"></a>5\. Hibernate 实体类可以被定义为 final 吗？

<pre>1
   可以是可以，但是这种做法并不好。因为Hibernate会使用代理模式在延迟关联的情况下提高性能，如果你把实体类定义成final类之后，因为java不允许对final类进行扩展，所以Hibernate就无法再使用代理了。如此一来就限制了使用可以提升性能的手段。

</pre>

## <a></a><a id="6____Hibernate__Integer__int__605" target="_blank"></a>6\. 在 Hibernate 中使用 Integer 和 int 做映射有什么区别？

<pre>1
2
3
  1）返回数据库字段值是null的话，int的类型会报错
  2）通过jdbc将实体存储到数据库的操作通过sql语句，基本数据类型可以直接存储，对象需要序列化存储
  3）在很多应用中，需要对某些对象进行序列化，让它们离开内存空间，入住物理硬盘，以便长期保存

</pre>

## <a></a><a id="7___Hibernate__609" target="_blank"></a>7\. Hibernate 是如何工作的？

<pre>1
2
3
4
5
6
7
8
   1）读取并解析配置文件
   2）读取并解析映射信息，创建SessionFactory
   3）打开Session
   1) 创建事务Transation
   5）持久化操作
   6）提交事务
   7）关闭Session
   8）关闭SessionFactory

</pre>

## <a></a><a id="8___get_load_618" target="_blank"></a>8\. get() 和 load() 的区别？

<pre>1
2
  使用get（）来根据ID进行单条查询，当get（）方法被调用时就会发出SQL语句，并且返回的对象也是实际的对象
 当调用load（）方法的时候会返回一个目标对象的代理对象，在这个代理对象中只存储了目标对象的iD值，只有当调用除ID值以外的属性值的时候才会发出SQL查询的

</pre>

## <a></a><a id="9____Hibernate__621" target="_blank"></a>9\. 说一下 Hibernate 的缓存机制？

<pre>1
   一级缓存就是session级别的缓存，在事务范围内有效，内置的不能被卸载。二级缓存是SessionFactory级别的缓存，从应用启动到应用结束有效。是可以选的，默认没有二级缓存，需要手动开启。保存数据库后，缓存在内存中保存一份，如果更新了数据库就要同步更新。

</pre>

## <a></a><a id="10__Hibernate__623" target="_blank"></a>10\. Hibernate 对象有哪些状态？

<pre>1
2
3
    1）Transient 瞬时：对象刚new出来，还没设id，设了其他值
    2）Persistent 持久：调用了save（）、saveOrUpdate（），就变成Persistent，有id
    3）Detached 脱管：当session close（）完之后，变成了Detached

</pre>

## <a></a><a id="11___Hibernate__getCurrentSession__openSession__627" target="_blank"></a>11\. 在 Hibernate 中 getCurrentSession 和 openSession 的区别是什么？

<pre>1
2
    1）openSession从字面上可以看得出来，是打开一个新的session对象而且每次打开使用都是打开一个新的session，假如连续使用多次，则获得的session不是同一个对象，并且使用完需要调用close方法关闭session
    2）getCurrentSession，从字面上可以就看得出来，是获取当前上下文一个session对象，当第一次使用此方法时会自动产生一个session对象，并且连续使用多次时，得到的session都是同一个对象，这就是与openSession得区别之一，简单而言，getCurrentSession就是：如果有已经使用的，用旧的，如果没有，建新的。

</pre>

## <a></a><a id="12__Hibernate__630" target="_blank"></a>12\. Hibernate 实体类必须要有无参构造函数吗？为什么？

<pre>1
2
   肯定的。Hibernate框架会调用这个默认构造方法来构造实例对象，即Class类得newInstance方法，这个方法就是通过调用默认构造方法来创建实例对象
   当查询得时候返回得实体类是一个对象实例，是Hibernate动态通过反射生成得。反射的Class.forName("className").newInstance()需要对应的类提供一个无参构造方法，必须有个无参的构造方法将对象创建出来，单从Hibernate的角度讲，它是通过反射创建实体对象的，所以没有构造方法是不行的，另外Hibernate也可以通过有参的构造方法创建对象。

</pre>

# <a></a><a id="Mybatis_633" target="_blank"></a>Mybatis

## <a></a><a id="1___Mybatis____635" target="_blank"></a>1\. Mybatis 中 #{} 和 ${} 的区别是什么？

<pre>1
   1\. #将传入的数据都当成一个字符串，会对自动传入的数据加一个双引号。如：order by #user_id#，如果传入的值是111,那么解析成sql时的值为order by "111", 如果传入的值是id，则解析成的sql为order by "id".

</pre>

1\. $ 将传入的数据直接显示生成在 sql 中。如：order by <span class="MathJax" id="MathJax-Element-1-Frame" tabindex="0" style="position: relative;" data-mathml="<math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><nobr aria-hidden="true">userid</nobr><math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi>u</mi><mi>s</mi><mi>e</mi><msub><mi>r</mi><mi>i</mi></msub><mi>d</mi></mrow> <annotation encoding="application/x-tex">user_id</annotation></semantics></math><script type="math/mml" id="MathJax-Element-1"><math><semantics><mrow><mi>u</mi><mi>s</mi><mi>e</mi><msub><mi>r</mi><mi>i</mi></msub><mi>d</mi></mrow><annotation encoding="application/x-tex">user_id</annotation></semantics></math></script>useri​d，如果传入的值是 111, 那么解析成 sql 时的值为 order by user_id, 如果传入的值是 id，则解析成的 sql 为 order by id.
　　 2\. #方式能够很大程度防止 sql 注入。
　　 4. <span class="MathJax" id="MathJax-Element-2-Frame" tabindex="0" style="position: relative;" data-mathml="<math xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><nobr aria-hidden="true">方式无法防止 Sql 注入。5.</nobr> <math xmlns="http://www.w3.org/1998/Math/MathML"><semantics><mrow><mi mathvariant="normal">方</mi><mi mathvariant="normal">式</mi><mi mathvariant="normal">无</mi><mi mathvariant="normal">法</mi><mi mathvariant="normal">防</mi><mi mathvariant="normal">止</mi> <mi>S</mi><mi>q</mi><mi>l</mi> <mi mathvariant="normal">注</mi><mi mathvariant="normal">入</mi><mi mathvariant="normal">。</mi><mn>5.</mn></mrow> <annotation encoding="application/x-tex">方式无法防止 Sql 注入。5.</annotation></semantics></math><script type="math/mml" id="MathJax-Element-2"><math><semantics><mrow><mi mathvariant="normal">方</mi><mi mathvariant="normal">式</mi><mi mathvariant="normal">无</mi><mi mathvariant="normal">法</mi><mi mathvariant="normal">防</mi><mi mathvariant="normal">止</mi><mi>S</mi><mi>q</mi><mi>l</mi><mi mathvariant="normal">注</mi><mi mathvariant="normal">入</mi><mi mathvariant="normal">。</mi><mn>5.</mn></mrow><annotation encoding="application/x-tex">方式无法防止Sql注入。 5.</annotation></semantics></math></script>方式无法防止 Sql 注入。5\. 方式一般用于传入数据库对象，例如传入表名.
　　6\. 一般能用 #的就别用 $.<semantics><mrow><mi mathvariant=&quot;normal&quot;>&amp;#x65B9;</mi><mi mathvariant=&quot;normal&quot;>&amp;#x5F0F;</mi><mi mathvariant=&quot;normal&quot;>&amp;#x65E0;</mi><mi mathvariant=&quot;normal&quot;>&amp;#x6CD5;</mi><mi mathvariant=&quot;normal&quot;>&amp;#x9632;</mi><mi mathvariant=&quot;normal&quot;>&amp;#x6B62;</mi><mi>S</mi><mi>q</mi><mi>l</mi><mi mathvariant=&quot;normal&quot;>&amp;#x6CE8;</mi><mi mathvariant=&quot;normal&quot;>&amp;#x5165;</mi><mi mathvariant=&quot;normal&quot;>&amp;#x3002;</mi><mn>5.</mn></mrow><annotation encoding=&quot;application/x-tex&quot;>      &amp;#x65B9;&amp;#x5F0F;&amp;#x65E0;&amp;#x6CD5;&amp;#x9632;&amp;#x6B62;Sql&amp;#x6CE8;&amp;#x5165;&amp;#x3002;5.</annotation></semantics></math>" role="presentation"></span><semantics><mrow><mi>u</mi><mi>s</mi><mi>e</mi><msub><mi>r</mi><mi>i</mi></msub><mi>d</mi></mrow><annotation encoding=&quot;application/x-tex&quot;>      user_id</annotation></semantics></math>" role="presentation"></span>

## <a></a><a id="126_Mybatis__643" target="_blank"></a>126\. Mybatis 有几种分页方式？

<pre>1
2
3
4
    1.数组分页
    2.sql分页
    3.拦截器分页
    4.RowBounds分页

</pre>

## <a></a><a id="127_RowBounds__648" target="_blank"></a>127\. RowBounds 是一次性查询全部结果吗？为什么？

<pre>1
   不是，RowBounds在处理分页时，只是简单的把offer之前的数据都skip掉，超过limit之后的数据不取出

</pre>

## <a></a><a id="128_Mybatis__650" target="_blank"></a>128\. Mybatis 逻辑分页和物理分页的区别是什么？

<pre>1
2
   1.逻辑分页，内存开销比较大，在数据量比较小的情况下效率比物理分页高；在数据量大的情况下，内存开销过大，容易内存溢出，不建议使用
   2.物理分页，内存开销比较小，在数据量比较小的情况下效率比逻辑分页还要低，在数据量很大的情况下，建议使用物理分页

</pre>

## <a></a><a id="129_Mybatis__654" target="_blank"></a>129\. Mybatis 是否支持延迟加载？延迟加载的原理是什么？

<pre>1
   支持。在mybatis中，延迟加载也是使用动态代理完成的，但在mybatis中被代理的对象不是one方而是many方本身，默认情况下一旦我访问了这个延迟加载对象任何一个属性，都会触发这个延迟加载对象的加载，这不是我们想要的结果，我们想要的是在需要dept的时候才去查询dept对象，去改变默认配置，使只有调用clone方法的时候才会触发完全加载

</pre>

## <a></a><a id="130__Mybatis__657" target="_blank"></a>130\. 说一下 Mybatis 的一级缓存和二级缓存？

<pre>1
2
   Mybatis对缓存提供支持，但是在没有配置的默认情况下，它只开启一级缓存，一级缓存只是相对于同一个SqlSession而言。所以在参数和SQL完全一样的情况下，我们使用同一个SqlSession对象调用一个Mapper方法，往往只执行一次SQL，因为使用SelSession第一次查询后，MyBatis会将其放在缓存中，以后再查询的时候，如果没有声明需要刷新，并且缓存没有超时的情况下，SqlSession都会取出当前缓存的数据，而不会再次发送SQL到数据库。
  MyBatis的二级缓存是Application级别的缓存，它可以提高对数据库查询的效率，以提高应用的性能

</pre>

## <a></a><a id="131_Mybatis__Hibernate__660" target="_blank"></a>131\. Mybatis 和 Hibernate 的区别有哪些？

<pre>1
2
   1、Mybatis和hibernate不同，它不完全是一个ORM框架，因为MyBatis需要程序员自己编写Sql语句，不过mybatis可以通过XML或注解方式灵活配置要运行的sql语句，并将java对象和sql语句映射生成最终执行的sql，最后将sql执行的结果再映射生成java对象。                   
   2、Mybatis学习门槛低，简单易学，程序员直接编写原生态sql，可严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发，例如互联网软件、企业运营类软件等，因为这类软件需求变化频繁，一但需求变化要求成果输出迅速。但是灵活的前提是mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件则需要自定义多套sql映射文件，工作量大。                   3、Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。但是Hibernate的缺点是学习门槛高，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之间如何权衡，以及怎样用好Hibernate需要具有很强的经验和能力才行。

</pre>

## <a></a><a id="132_Mybatis_Executor_663" target="_blank"></a>132\. Mybatis 有哪些执行器（Executor）？

<pre>1
   SimpleExecutor、ReuseExecutor、BatchExecutor

</pre>

## <a></a><a id="133_Mybatis__665" target="_blank"></a>133\. Mybatis 分页插件的实现原理是什么？

<pre>1
   就是在StatementHandler之前进行拦截，对MappedStatement进行一系列的操作（大致就是拼上分页sql）

</pre>

## <a></a><a id="134_Mybatis__667" target="_blank"></a>134\. Mybatis 如何编写一个自定义插件？

<pre>1
2
3
   1\. 编写Interceptor的实现类
   2\. 使用@Intercepts注解完成插件签名 说明插件的拦截四大对象之一的哪一个对象的哪一个方法
   3\. 将写好的插件注册到全局配置文件中

</pre>

# <a></a><a id="RabbitMQ_671" target="_blank"></a>RabbitMQ

## <a></a><a id="135_RabbitMQ__673" target="_blank"></a>135\. RabbitMQ 的使用场景有哪些？

<pre>1
2
3
4
5
   简单的发送与接收，没有特别的处理
   单发送多接收
   发布、订阅模式，发送端发送广播消息，多个接收端接收
   按路线发送接收
   按topics发送接收

</pre>

## <a></a><a id="136_RabbitMQ__679" target="_blank"></a>136\. RabbitMQ 有哪些重要的角色？

<pre>1
    management、policymaker、monitorng、administrator

</pre>

## <a></a><a id="137_RabbitMQ__681" target="_blank"></a>137\. RabbitMQ 有哪些重要的组件？

<pre>1
2
3
    Spring AMQP
    message
    Exchange

</pre>

## <a></a><a id="138_RabbitMQ__VHost__685" target="_blank"></a>138\. RabbitMQ 中 VHost 的作用是什么？

<pre>1
    通过在各个实例间提供逻辑上分离，允许你为不同应用程序安全保密地运行数据

</pre>

## <a></a><a id="139_RabbitMQ__687" target="_blank"></a>139\. RabbitMQ 的消息是怎么发送的？

<pre>1
   举个例子,假如你想投递一封邮件,你可以将邮件投递到某个邮箱,然后邮递员从邮箱中获取邮件,并将邮件交付到接收方,在这个过程中,RabbitMQ就 类似于邮箱 、邮局和邮递员，RabbitMQ是一个消息队列,它可以接收程序发送的消息,然后放入到相应的消息队列中,另外一些程序可以从消息队列中获取数据,以此完成程序之间的通信

</pre>

## <a></a><a id="140_RabbitMQ__689" target="_blank"></a>140\. RabbitMQ 怎么保证消息的稳定性？

<pre>1
2
3
   1.publisher confirms（发布方确认）
   2.message持久化
   3.acknowledgement（consumer确认）

</pre>

## <a></a><a id="141_RabbitMQ__694" target="_blank"></a>141\. RabbitMQ 怎么避免消息丢失？

<pre>1
   将Queue与Message都设置为可持久化的（durable），这样可以保证绝大部分情况下我们的RabbitMQ消息不会丢失。但依然解决不了小概率丢失事件的发生（比如RabbitMQ服务器已经接收到生产者的消息，但还没来得及持久化该消息时RabbitMQ服务器就断电了），如果我们需要对这种小概率事件也要管理起来，那么我们要用到事务。

</pre>

## <a></a><a id="142__697" target="_blank"></a>142\. 要保证消息持久化成功的条件有哪些？

<pre>1
2
       为了长时间的存储和管理消息，一般会使用数据库。在Activemq中默认使用的是Derby DB。当然也可更改配置来使用其他的DB。
       如果要使用不在上面列表中的DB，可以通过配置SQL语句和JDBC驱动来支持自己的DB。Broker在启动的时候读取配置文件，若在配置文件中指定了特定的JDBC驱动，则会在classpath路径下自动检测配置的JDBC驱动

</pre>

## <a></a><a id="143_RabbitMQ__700" target="_blank"></a>143\. RabbitMQ 持久化有什么特点？

<pre>1
    消息的可靠性是RabbitMQ的一大特色，服务会把持久化的queue存放在硬盘上，当服务重启的时候，会重新什么之前被持久化的queue。队列是可以被持久化，但是里面的消息是否为持久化那还要看消息的持久化设置。也就是说，重启之前那个queue里面还没有发出去的消息的话，重启之后那队列里面是不是还存在原来的消息，这个就要取决于发生着在发送消息时对消息的设置了。

</pre>

如果要在重启后保持消息的持久化必须设置消息是持久化的标识。

## <a></a><a id="144_RabbitMQ__703" target="_blank"></a>144\. RabbitMQ 有几种广播类型？

<pre>1
2
3
 1.fanout广播模式
 2.direct广播模式：能选择性的发送接收消息，在终端运行并输入参数
 3.Topic细致的消息过滤广播模式

</pre>

## <a></a><a id="145_RabbitMQ__708" target="_blank"></a>145\. RabbitMQ 怎么实现延迟消息队列？

<pre>1
2
3
4
   延迟任务通过消息的TTL和Dead letter Exchange来实现。我们需要建立2个队列，一个用于发送消息，一个用于消息过期后的转发目标队列。
   第一步，首先需要创建2个队列。
   第二步，实现消息的Producer。
   第三步，实现消息的Consumer。

</pre>

## <a></a><a id="146_RabbitMQ__713" target="_blank"></a>146\. RabbitMQ 集群有什么用？

<pre>1
    一个rabbitmq集群中可以共享user，vhost，queue，exchange等，所有的数据和状态都是必须在所有节点上复制的，一个例外是，那些当前只属于创建它的节点的消息队列，尽管它们可见且可被所有节点读取。rabbitmq节点可以动态的加入到集群中，一个节点它可以加入到集群中，也可以从集群环集群会进行一个基本的负载均衡。

</pre>

## <a></a><a id="147_RabbitMQ__715" target="_blank"></a>147\. RabbitMQ 节点的类型有哪些？

<pre>1
   内存节点、磁盘节点。顾名思义内存节点就是将所有数据放在内存，磁盘节点将数据放在磁盘

</pre>

## <a></a><a id="148_RabbitMQ__718" target="_blank"></a>148\. RabbitMQ 集群搭建需要注意哪些问题？

<pre>1
   每个节点Cookie的同步；主机之间 必须可以相互识别并可达，/etc/hosts文件配置必须准确

</pre>

## <a></a><a id="149_RabbitMQ__721" target="_blank"></a>149\. RabbitMQ 每个节点是其他节点的完整拷贝吗？为什么？

<pre>1
   不是，队列的完整信息只放在一个节点，其他节点存放的是该队列的指针

</pre>

## <a></a><a id="150_RabbitMQ__723" target="_blank"></a>150\. RabbitMQ 集群中唯一一个磁盘节点崩溃了会发生什么情况？

<pre>1
2
3
4
5
6
7
   　如果唯一的磁盘节点崩溃，集群是可以保持运行的，但不能更改任何东西。
       不能创建队列
       不能创建交换器
       不能创建绑定
       不能添加用户
      不能更改权限
      不能添加和删除集群几点

</pre>

## <a></a><a id="151_RabbitMQ__731" target="_blank"></a>151\. RabbitMQ 对集群节点停止顺序有要求吗？

<pre>1
2
     启动顺序：磁盘节点 => 内存节点
     关闭顺序：内存节点 => 磁盘节点

</pre>

# <a></a><a id="Kafka_734" target="_blank"></a>Kafka

## <a></a><a id="152_Kafka__ZooKeeper__736" target="_blank"></a>152\. Kafka 可以脱离 ZooKeeper 单独使用吗？为什么？

<pre>1
  不可以，kafka必须要依赖一个zookeeper集群才能运行。kafka系群里面各个broker都是通过zookeeper来同步topic列表以及其它broker列表的，一旦连不上zookeeper，kafka也就无法工作。

</pre>

## <a></a><a id="153_Kafka__738" target="_blank"></a>153\. Kafka 有几种数据保留的策略？

<pre>1
2
  1）N天前的删除。
  2）保留最近的多少Size数据。

</pre>

## <a></a><a id="154_Kafka__7__10G__10G_Kafka__741" target="_blank"></a>154\. Kafka 同时设置了 7 天和 10G 清除数据，到第五天的时候消息达到了 10G，这个时候 Kafka 将如何处理？

<pre>1
2
  Kafka Broker默认的消息保留策略是：要么保留一定时间，要么保留到消息达到一定大小的字节数  
  当消息达到设置的条件上限时，旧消息就会过期并被删除，所以在任何时刻，可用消息的总量都不会超过配置参数所指定的大小。

</pre>

topic 可以配置自己的保留策略，可以将消息保留到不再使用他们为止。因为在一个大文件里查找和删除消息是很费时的事，也容易出错，所以，分区被划分为若干个片段。默认情况下，每个片段包含 1G 或者一周的数据，以较小的那个为准。在 broker 往 leader 分区写入消息时，如果达到片段上限，就关闭当前文件，并打开一个新文件。当前正在写入数据的片段叫活跃片段。当所有片段都被写满时，会清除下一个分区片段的数据，如果配置的是 7 个片段，每天打开一个新片段，就会删除一个最老的片段，循环使用所有片段。

## <a></a><a id="155__Kafka__745" target="_blank"></a>155\. 什么情况会导致 Kafka 运行变慢？

<pre>1
 由于producer是线程安全的，所以采用单实例，但一次发送阻塞将会影响后续的数据处理 

</pre>

## <a></a><a id="156__Kafka__747" target="_blank"></a>156\. 使用 Kafka 集群需要注意什么？

<pre>1
2
  配置好zookeeper之后，关闭防火墙
  启动zk服务，在bin目录下三台依次启动

</pre>

# <a></a><a id="ZooKeeper_750" target="_blank"></a>ZooKeeper

## <a></a><a id="157_ZooKeeper__752" target="_blank"></a>157\. ZooKeeper 是什么？

<pre>1
   Zookeeper是一个分布式的，开放源码的分布式应用程序协调服务，是Google的Chubby一个开源的实现，是Hadoop和Hbase的重要组件。它是一个为分布式应用提供一致性服务的软件，提供的功能包括：配置维护、域名服务、分布式同步、组服务等。Zookeeper的目标就是封装好复杂易出错的关键服务，将简单易用的接口和性能高效、功能稳定的系统提供给用户。

</pre>

## <a></a><a id="158_ZooKeeper__754" target="_blank"></a>158\. ZooKeeper 都有哪些功能？

<pre>1
  配置维护、域名服务、分布式同步、组服务

</pre>

## <a></a><a id="159_ZooKeeper__756" target="_blank"></a>159\. ZooKeeper 有几种部署模式？

<pre>1
2
单机部署：一台集群上运行；
集群部署：多台集群运行；

</pre>

伪集群部署：一台集群启动多个 zookeeper 实例运行。

## <a></a><a id="160_ZooKeeper__760" target="_blank"></a>160\. ZooKeeper 怎么保证主从节点的状态同步？

<pre>1
  zookeeper 的核心是原子广播，这个机制保证了各个 server 之间的同步。实现这个机制的协议叫做 zab 协议。 zab 协议有两种模式，分别是恢复模式（选主）和广播模式（同步）。当服务启动或者在领导者崩溃后，zab 就进入了恢复模式，当领导者被选举出来，且大多数 server 完成了和 leader 的状态同步以后，恢复模式就结束了。状态同步保证了 leader 和 server 具有相同的系统状态

</pre>

## <a></a><a id="161__762" target="_blank"></a>161\. 集群中为什么要有主节点？

<pre>1
 在分布式环境中，有些业务逻辑只需要集群中的某一台机器进行执行，其他的机器可以共享这个结果，这样可以大大减少重复计算，提高性能，所以就需要主节点

</pre>

## <a></a><a id="162__3__ZooKeeper__764" target="_blank"></a>162\. 集群中有 3 台服务器，其中一个节点宕机，这个时候 ZooKeeper 还可以使用吗？

<pre>1
   可以继续使用，单数服务器只要没超过一半的服务器宕机就可以继续使用。

</pre>

## <a></a><a id="163__ZooKeeper___766" target="_blank"></a>163\. 说一下 ZooKeeper 的通知机制？

<pre>1
   客户端端会对某个 znode 建立一个 watcher 事件，当该 znode 发生变化时，这些客户端会收到 zookeeper 的通知，然后客户端可以根据 znode 变化来做出业务上的改变。

</pre>

# <a></a><a id="MySQL_768" target="_blank"></a>MySQL

## <a></a><a id="164__770" target="_blank"></a>164\. 数据库的三范式是什么？

<pre>1
2
3
  第一：当关系模式R的所有属性都不能在分解为更基本的数据单位时，称R是满足第一范式的，简记为1NF。满足第一范式是关系模式规范化的最低要求，否则，将有很多基本操作在这样的关系模式中实现不了。
  第二：如果关系模式R满足第一范式，并且R的所有非主属性都完全依赖于R的每一个候选关键属性，称为R满足第二范式，简记为2NF
  第三：设R是一个满足第一范式条件的关系模式，X是R的任意属性集，如果X非传递依赖于R的任意一个候选关键字，称R满足第三范式。

</pre>

## <a></a><a id="165__7__2__MySQL__ID__774" target="_blank"></a>165\. 一张自增表里面总共有 7 条数据，删除了最后 2 条数据，重启 MySQL 数据库，又插入了一条数据，此时 ID 是几？

<pre>1
  7-2+1=6

</pre>

## <a></a><a id="166__777" target="_blank"></a>166\. 如何获取当前数据库版本？

<pre>1
2
3
 一、在查询器中输入“Select@@Version”并运行，查看运行结果，对照便知版本
 二、运行SQL SERVER服务管理器，在任务栏小托盘处，右键单击管理器图标，选“关于”，在弹出的窗口中，对照信息便知
 三、在添加或删除程序中查看SQL SERVER的支持信息，可直接查看到版本号

</pre>

## <a></a><a id="167__ACID__784" target="_blank"></a>167\. 说一下 ACID 是什么？

<pre>1
2
3
4
5
  ACID是事务的四大特性：
  Atomicity：原子性现在对于一个事务来讲，要么一起执行成功要么一起失败，执行的过程是不能被打断或者执行其他操作的
 Consistency：一致性表现为事务进行过后和执行前，整体系统是稳定的，比如对于入账出账操作是不会有总资金的变化的
 Isolation：隔离性表示各个事务间不会相互影响，数据库一般会提供多种级别的隔离。实际上多个事务是并发执行的，但它们之间不会相互影响
 Durability：持久性表示一旦一个事务成功了，那么他的改变是永久性的被记录和操作

</pre>

## <a></a><a id="168_Char__VarChar__791" target="_blank"></a>168\. Char 和 VarChar 的区别是什么？

<pre>1
2
3
  CHAR的长度是固定的，而VARCHAR2的长度是可以变化的
  CHAR的效率比VARCHAR2的效率稍高
  目前VARCHAR是VARCHAR的同义词

</pre>

## <a></a><a id="169_Float__Double__796" target="_blank"></a>169\. Float 和 Double 的区别是什么？

<pre>1
   Double精度高，有效数字16位，float精度7位，但double消耗的内存是float的两倍，double的运算速度比float慢得多

</pre>

## <a></a><a id="170_MySQL__799" target="_blank"></a>170\. MySQL 的内连接、左连接、右连接有什么区别？

<pre>1
 　1.内连接,显示两个表中有联系的所有数据;

</pre>

2\. 左链接, 以左表为参照, 显示所有数据;
　　 3\. 右链接, 以右表为参照显示数据;

## <a></a><a id="171_MySQL_804" target="_blank"></a>171\. MySQL 索引是怎么实现的？

<pre>1
   索引是一种高效获取数据的存储结构，例：hash、 二叉、 红黑。索引是一个排序的列表，在这个列表中存储着索引的值和包含这个值的数据所在行的物理地址，在数据十分庞大的时候，索引可以大大加快查询的速度，这是因为使用索引后可以不用扫描全表来定位某行的数据，而是先通过索引表找到该行数据对应的物理地址然后访问相应的数据。

</pre>

## <a></a><a id="172__MySQL_807" target="_blank"></a>172\. 怎么验证 MySQL 的索引是否满足需求？

<pre>1
  用explain，加在select前面，然后运行，查看key那列有没有用到你那个索引

</pre>

## <a></a><a id="173__809" target="_blank"></a>173\. 说一下数据库的事务隔离？

<pre>1
  任何支持事务的数据库都必须具备四个特性，分别是：原子性，一致性，隔离性，持久性，也就是我们常说的事务ACID，这样才能保证事务中数据的正确性。事务的隔离性就是指，多个并发的事务同时访问一个数据库时，一个事务不应该被另一个事务所干扰，每一个并发的事务间要相互进行隔离。

</pre>

## <a></a><a id="174__MySQL_811" target="_blank"></a>174\. 说一下 MySQL 常用的引擎？

<pre>1
2
3
4
5
  在Mysql数据库中，常用的引擎主要有两个：Innodb和MyIASM
  Innodb：提供了对数据库ACID事务的支持，并且还提供了行级锁和外键的约束。它的设计目标就是处理大数据容量的数据库系统，它本身实际上是基于Mysql后台的完整的系统
  MyISAM：是Mysql的默认引擎，但不提供事务的支持，也不支持行级锁和外键。因此当执行Insert插入和Update更新语句时，即执行写操作的时候要锁定这个表。所以会导致效率降低。
  大容量的数据集时趋向于选择Innodb。因为它支持事务处理和故障的恢复。Innodb可以利用数据日志来进行数据的恢复。主键的查询在Innodb也是比较快的
  大批量的插入语句时在MyIASM引擎中执行的比较快，但是Update语句在Innodb下执行的会比较快，尤其在并发量大的时候

</pre>

## <a></a><a id="175__MySQL_817" target="_blank"></a>175\. 说一下 MySQL 的行锁和表锁？

<pre>1
2
3
   表级锁：开销小，加锁快；不会出现死锁；锁定粒度大，发生锁冲突的概率最高，并发度 最低
   行级锁：开销大，加锁慢；会出现死锁；锁定粒度小，发生锁冲突的概率最低，并发度也最高
   锁是计算机协调多个进程或纯线程并发访问某一资源的机制。在数据库中，除传统的计算资源的争用以外，数据也是一种供许多用户共享的资源

</pre>

## <a></a><a id="176__821" target="_blank"></a>176\. 说一下乐观锁和悲观锁？

<pre>1
2
3
   悲观锁，顾名思义，就是很悲观，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会block直到它自己拿到锁。传统的关系型数据库里面就用到了很多这种锁机制，比如行锁，表锁，读锁，写锁等，都是在做操作之前先上锁。
   乐观锁，顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号等机制。乐观锁适用于多读的应用类型，这样可以提高吞吐量，像数据库如果提供类似于write_condition机制的其实都是提供的乐观锁
   两种锁各有优缺点，不可认为一种好于另一种，像乐观锁适用于写比较少的情况下，即冲突真的很少发生的时候，这样可以省去了锁的开销，加大了系统的整个吞吐量。但如果经常产生冲突，上层应用会不断的进行retry，这样反倒是降低了性能，所以这种情况下用悲观锁就比较合适。

</pre>

## <a></a><a id="177_MySQL_825" target="_blank"></a>177\. MySQL 问题排查都有哪些手段？

<pre>1
2
3
4
5
6
7
  1.事务级别  ：select @@global.tx_isolation
  2.输出数据当前状态：SHOW ENGINE INNODB STATUS 可用于排查死锁问题，锁定行数等问题
  3.查询数据库连接信息：select * from information_schema.PROCESSLIST
  4.查询事务信息：select * from information_schema.INNODB_TRX
  5.查询数据库锁等待信息：select * from information_schema.INNODB_LOCK_WAITS
  6.手动杀掉某个进程：kill trx_mysql_thread_id
   。。。。。。

</pre>

## <a></a><a id="178__MySQL_833" target="_blank"></a>178\. 如何做 MySQL 的性能优化？

<pre>1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
   1.为查询缓存优化你的查询
   2.EXplain的Select 查询
   3.当只要有一行数据时使用LIMIT1
   4.为搜索字段建索引
   5.在Join表的时候使用相当类型的例，并将其索引
   6.千万不要ORDER BY  RAND（）
   7.避免SELECT *
   8.永远为每张表设置一个ID|
   9.使用ENUM而不是VARCHAR
  10.从PROCEDURE ANAKYSE（）取得建议
  11.尽可能的使用NOT NULL
  12.Prepared Statements
  13.无缓冲的查询
  14.把IP地址存成UNSIGNED INT
  15.固定长度的表会更快
  16.垂直分割
  17.拆分大的DELETE或INSERT语句
  18.越小的列会越快
  19.选择正确的存储引擎
  20.使用一个对象关系映射器
  21.小心“永久链接”

</pre>

# <a></a><a id="Redis_855" target="_blank"></a>Redis

## <a></a><a id="179_Redis__857" target="_blank"></a>179\. Redis 是什么？都有哪些使用场景？

<pre>1
2
   是由意大利人开发的一款内存高速缓存数据库。全称为远程数据服务，该软件用c语言编写，是一个key-value存储系统，支持丰富的数据类型
   众多语言都支持Redis，因为Redis交换数据快，所以在服务器中常用来存储一些需要频繁调取的数据，这样可以大大节省系统直接读取磁盘来获得数据的I/O开销，更重要的是可以极大提升速度。

</pre>

拿大型网站来举个例子，比如 a 网站首页一天有 100 万人访问，其中有一个板块为推荐新闻。要是直接从数据库查询，那么一天就要多消耗 100 万次数据库请求。上面已经说过，Redis 支持丰富的数据类型，所以这完全可以用 Redis 来完成，将这种热点数据存到 Redis（内存）中，要用的时候，直接从内存取，极大的提高了速度和节约了服务器的开销。

## <a></a><a id="180_Redis__861" target="_blank"></a>180\. Redis 有哪些功能？

<pre>1
   慢查询、缓存

</pre>

## <a></a><a id="181_Redis__MemeCache__863" target="_blank"></a>181\. Redis 和 MemeCache 有什么区别？

<pre>1
2
3
4
  1.与Memached仅支持简单的key-value结构的数据记录不同，Redis支持的数据类型要丰富的多：String、hash、List、Set和Sort Set。Redis内部使用一个redisObject对象来表示所有的key和value
  2.内存管理机制不同，并不是所有的数据都是一直存储在内存中的。当物理内存用完时，Redis可以将一些很久没用到的value交换到磁盘
  3.数据持久化支持。Redis虽然是基于内存的存储系统，但它本身是支持内存数据的持久化的，而且提供两种主要的持久化策略：RDB快照和AOF日志。而memcached是不支持数据持久化操作的
  4.集群管理不同。memcached是全内存的数据缓冲系统，redis虽然支持数据的持久化，但是全内存毕竟才是其高性能的本质。作为基于内存的存储系统来说，机器物理内存的大小就是系统能够容纳的最大数据量。如果需要处理的数据量超过了单台机器的物理内存大小，就需要构建分布式集群来扩展存储能力。

</pre>

## <a></a><a id="182_Redis__868" target="_blank"></a>182\. Redis 为什么是单线程的？

<pre>1
    以前有个误区，认为高性能服务器一定是多线程来实现的，其实不然，redis核心就是如果我的数据全在内存里，单线程的区操作效率是最高的。因为多线程的本质就是COU模拟出来多个线程的情况，这种模拟出来的情况就有一个代价，就是上下文的切换，对于一个内存的系统来说，它没有上下文的切换就是效率最高的。redis用单个CPU绑定一块内存的数据，然后针对这块内存的数据进行多次读写的时候，都是在一个CPu上完成的，所以它是单线程处理

</pre>

## <a></a><a id="183__870" target="_blank"></a>183\. 什么是缓存穿透？怎么解决？

<pre>1
   缓存穿透，是指查询一个数据库一定不存在的数据。正常的使用缓存流程大致是，数据查询先进行缓存查询，如果key不存在或者key已经过期，再对数据库进行查询，并把查询到的对象，放进缓存。如果数据库查询对象为空，则不放进缓存

</pre>

## <a></a><a id="184_Redis__872" target="_blank"></a>184\. Redis 支持的数据类型有哪些？

<pre>1
   String，hash，list，set，zset

</pre>

## <a></a><a id="185_Redis__Java__874" target="_blank"></a>185\. Redis 支持的 Java 客户端都有哪些？

<pre>1
    Redission、jedis，lettuce，官方推荐使用Redisson

</pre>

## <a></a><a id="186_Jedis__Redisson__876" target="_blank"></a>186\. Jedis 和 Redisson 有哪些区别？

<pre>1
2
   Jedis时java实现redis的客户端，它的Api提供了全面类似于Redis原生命令的支持。相比于其他Redis封装框架更加原生。它的使用主要是使用JedisPool
   Redisson是一个在Redis的基础上实现的Java驻内存数据网格。它不仅提供了一系列额分布式的java常用对象，还提供了许多分布式服务

</pre>

## <a></a><a id="187__879" target="_blank"></a>187\. 怎么保证缓存和数据库数据的一致性？

<pre>1
    我们只能采取合适的策略来降低缓存和数据库间数据不一致的概率，而无法保证两者间的强一致性。合适的策略包括 合适的缓存更新策略，更新数据库后要及时更新缓存、缓存失败时增加重试机制，例如MQ模式的消息队列。

</pre>

## <a></a><a id="188_Redis__881" target="_blank"></a>188\. Redis 持久化有几种方式？

<pre>1
   RDB与AOF两种方式

</pre>

## <a></a><a id="189_Redis__883" target="_blank"></a>189\. Redis 怎么实现分布式锁？

<pre>1
2
3
4
   1）setNx一个锁key，相应的value为当前时间加上过期时间的时钟
   2）如果setNx成功，或者当前时钟大于此时key对应的时钟则加锁成功，否则加锁失败退出
   3）加锁成功执行相应的业务操作
   4）释放锁时判断当前时钟是否小于锁key的value，如果当前时钟小于锁key对应的value对应的value则执行删除锁key的操作

</pre>

## <a></a><a id="190_Redis__888" target="_blank"></a>190\. Redis 分布式锁有什么缺陷？

<pre>1
   对于单点的redis能很好的实现分布式锁，如果redis集群，会出现master宕机的情况。如果master宕机，此时锁key还没有同步到slave节点上，会出现机器B从新的master上获取了一个重复的锁

</pre>

## <a></a><a id="191_Redis__890" target="_blank"></a>191\. Redis 如何做内存优化？

<pre>1
2
3
4
5
6
   1）redisObject对象
   2）缩减键值对象
   3)共享对象池
   4）字符串优化
   5）编码优化
   6）控制key的数量

</pre>

## <a></a><a id="192_Redis__897" target="_blank"></a>192\. Redis 淘汰策略有哪些？

<pre>1
  1）voltile-lru：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰

</pre>

2）volatile-ttl：从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰

<pre>1
2
3
  3）volatile-random：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰

  4）allkeys-lru：从数据集（server.db[i].dict）中挑选最近最少使用的数据淘汰

</pre>

5）allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰

6）no-enviction（驱逐）：禁止驱逐数据

## <a></a><a id="193_Redis__910" target="_blank"></a>193\. Redis 常见的性能问题有哪些？该如何解决？

<pre>1
2
3
4
5
   1.Master写内存快照
   2.Master AOF持久化
   3.Master调用BGREWRITEAOF
   4.Redis主从复制的性能问题
   5.单点故障问题

</pre>

总结：Master 最好不要做任何持久化工作，包括内存快照和 AOF 日志文件，特别是不要启用内存快照做持久化。
如果数据比较关键，某个 Slave 开启 AOF 备份数据，策略为每秒同步一次。
为了主从复制的速度和连接的稳定性，Slave 和 Master 最好在同一个局域网内。
尽量避免在压力较大的主库上增加从库
为了 Master 的稳定性，主从复制不要用图状结构，用单向链表结构更稳定，即主从关系为：Master<–Slave1<–Slave2<–Slave3…….，这样的结构也方便解决单点故障问题，实现 Slave 对 Master 的替换，也即，如果 Master 挂了，可以立马启用 Slave1 做 Master，其他不变。

# <a></a><a id="JVM_921" target="_blank"></a>JVM

## <a></a><a id="194__JVM__923" target="_blank"></a>194\. 说一下 JVM 的主要组成部分？及其作用？

<pre>1
2
3
4
5
  堆。 堆是Java对象的存储区域，任何用new字段分配的Java对象实例和数组，都被分配在堆上，Java堆可使用-Xms -Xmx进行内存控制，值得一提的是从JDK1.7版本之后，运行时常量池从方法区移到了堆上。

  方法区。它用于存储已被虚拟机加载的类信息，常量，静态变量，即时编译器编译后的代码等数据，方法区在JDK1.7版本及以前被称为永久代，从JDK1.8永久代被移除。

  虚拟机栈。虚拟机栈中执行每个方法的时候，都会创建一个栈帧用于存储局部变量表，操作数栈，动态链接，方法出口等信息。

</pre>

本地方法栈。与虚拟机栈发挥的作用相似，相比于虚拟机栈为 Java 方法服务，本地方法栈为虚拟机使用的 Native 方法服务，执行每个本地方法的时候，都会创建一个栈帧用于存储局部变量表，操作数栈，动态链接，方法出口等信息。

<pre>1
  程序计数器。指示Java虚拟机下一条需要执行的字节码指令。 

</pre>

以上五个区域是 Java 虚拟机内存划分情况，其中方法区和堆被 JVM 中多个线程共享，比如类的静态常量就被存放在方法区，供类对象之间共享，虚拟机栈，本地方法栈，pc 寄存器是每个线程独立拥有的，不会与其他线程共享。
所以 Java 在通过 new 创建一个类对象实例的时候，一方面会在虚拟机栈中创建一个该对象的引用，另一方面会在堆上创建类对象的实例，然后将对象引用指向该对象的实例。对象引用存放在每一个方法对应的栈帧中。

## <a></a><a id="1____JVM__935" target="_blank"></a>1\. 说一下 JVM 运行时数据区？

<pre>1
    JVM运行时数据区包括数据隔离的数据区：方法区和堆，线程隔离的数据区：虚拟机栈，本地方法栈，程序计数器

</pre>

## <a></a><a id="2____937" target="_blank"></a>2\. 说一下堆栈的区别？

<pre>1
2
   栈内存首先是一片内存区域，存储的都是局部变量，凡是定义在方法中的都是局部变量（方法外的是全局变量），for循环内部定义的也是局部变量，是先加载函数才能进行局部变量的定义，所以方法先进栈，然后再定义变量，变量有自己的作用域，一旦离开作用域，变量就会被释放。栈内存的更新速度很快，因为局部变量的生命周期都很短。
   堆内存存储的是数组和对象，凡是new建立的都是在堆中，堆中存放的都是实体，实体用于封装数据，而且是封装多个，如果一个数据消失，这个实体也没有消失，还可以用，所以堆是不会随时释放的，但栈不一样，栈里存放的都是单个变量，变量被释放了，那就没有了。堆里的实体虽然不会被释放，但是会被当成垃圾，java有垃圾回收机制不定时的收取。

</pre>

## <a></a><a id="3____940" target="_blank"></a>3\. 队列和栈是什么？有什么区别？

<pre>1
2
3
4
5
   队列是一种特殊的线性表，特殊之处在于它只允许在表的前端（front）进行删除操作，而在表的后端（rear）进行插入操作，和栈一样，队列是一种操作受限制的线性表。进行插入操作的端称为队尾，进行删除操作的端称为队头。
   栈（stack）又名堆栈，它是一种运算受限的线性表。其限制是仅允许在表的一端进行插入和删除运算。这一端被称为栈顶，相对地，把另一端称为栈底。
   区别：1.操作的名称不同，队列的插入称为入队，队列的删除称为出队。栈的插入称为进栈，栈的删除称为出栈
             2.可操作的方式不同。队列是在队尾入队，队头出队，即两边都可操作。而栈的进栈和出栈都是在栈顶进行的，无法对栈底直接进行操作
             3.操作的方法不同。队列是先进先出，即队列的修改是依先进先出的原则进行的。新来的成员总要加入队尾，每次离开的成员总是队列头上。而栈为后进先出，即每次删除的总是当前栈中最新的元素，即最后插入的元素，而最先插入的被放在栈的底部，要到最后才能删除。

</pre>

## <a></a><a id="4____946" target="_blank"></a>4\. 什么是双亲委派模型？

<pre>1
    是JVM中类加载的机制。这个模型要求除了Bootstrap ClassLoader外，其余的类加载器都要有自己的父加载器。子加载器通过组合来复用父加载器的代码，而不是使用继承。在某个类加载器class文件时，它首先委托父加载器去加载这个类，依次传递到顶层类加载器。如果顶层加载不了，子加载器才会尝试加载这个类。

</pre>

## <a></a><a id="5____948" target="_blank"></a>5\. 说一下类加载的执行过程？

<pre>1
2
   类从被加载到JVM中开始到卸载为止，整个生命周期包括：加载，验证，准备，解析，初始化，使用和卸载七个阶段，其中类加载过程包括加载、验证、准备、解析和初始化五个阶段。
   当一个类加载器接收到一个类加载的任务时，不会立即展开加载，而是将加载任务委托给它的父类加载器去执行，每一层的类都采用相同的方式，直到委托给最顶层的启动类加载器为止。如果父类加载器无法加载委托给它的类，便将类的加载任务退回给下一级类加载器去执行加载。

</pre>

## <a></a><a id="6____951" target="_blank"></a>6\. 怎么判断对象是否可以被回收？

<pre>1
   如果一个程序无法引用到该对象，那么这个对象就没有到达根对象的路径，或者说从跟对象开始无法引用到该对象，该对象就是不可达的

</pre>

## <a></a><a id="7___Java__953" target="_blank"></a>7\. Java 中都有哪些引用类型？

<pre>1
2
3
4
5
  1.强引用
  2.软引用
  3.弱引用
  4.虚引用
  引用队列

</pre>

## <a></a><a id="8____JVM__959" target="_blank"></a>8\. 说一下 JVM 有哪些垃圾回收算法？

<pre>1
2
3
4
   1.标记清除算法
   2.复制算法
   3.标记整理算法
   4.分代收集算法

</pre>

## <a></a><a id="9____JVM__964" target="_blank"></a>9\. 说一下 JVM 有哪些垃圾回收器？

<pre>1
2
3
4
5
6
7
  1.串行回收器
       1.1 新生代串行回收器
       1.2 老年代串行回收器
  2.并行回收器
        2.1新生代ParNew回收器
        2.2新生代ParalleIGC回收器
        2.3老年代ParallelOldGC

</pre>

## <a></a><a id="10___CMS__972" target="_blank"></a>10\. 详细介绍一下 CMS 垃圾回收器？

<pre>1
2
   CMS垃圾回收的全称是Concurrent Mark-Sweep Collector，从名字上可以看出两点，一个使用的是并发收集，第二个使用的收集算法是Mark-Sweep.从而也可以推测出该收集器的特点是低延迟并且会有浮动垃圾的问题。
     CMS收集器是为了低延迟而生，通过尽可能的并行执行垃圾回收的几个阶段来把延迟控制到最低。CMS收集器是老年代的垃圾收集器，一般情况下会有ParNew来配合执行(默认情况下也是ParNew)，ParNew也是使用并行的算法来执行新生代的回收。当然除此之外，你还可以选择使用Serial收集器来收集新生代，不过一般很少这样使用。通过咱们说的CMS收集器是指广义上的CMS收集器，包含以下几个：ParNew（Young）GC + CMS（Old）GC ＋ Serial GC 算法（应对核心的CMS GC某些时候的不赶趟，开销很大）。本文重点介绍一些CMS在Old区域的回收。

</pre>

## <a></a><a id="11___975" target="_blank"></a>11\. 新生代垃圾回收器和老生代垃圾回收器都有哪些？有什么区别？

<pre>1
   1\. 新生代（New Generation）

</pre>

大多数情况下 Java 程序中新建的对象都从新生代分配内存，新生代由 Eden Space 和两块相同大小的 Survivor Space（通常又称为 S0 和 S1 或 From 和 To）构成，可通过 - Xmn 参数来指定新生代的大小，也可通过 - XX:SurvivorRatio 来调整 Eden Space 及 Survivor Space 的大小。不同的 GC 方式会以不同的方式按此值来划分 Eden Space 和 Survivor Space，有些 GC 方式还会根据运行状况来动态调整 Eden、S0、S1 的大小。
2\. 老年代（Old Generation 或 Tenuring Generation）
用于存放新生代中经过多次垃圾回收仍然存活的对象，例如缓存对象，新建的对象也有可能在老年代上直接分配内存。主要有两种状况（由不同的 GC 实现来决定）：一种为大对象，可通过在启动参数上设置 - XX:PretenureSizeThreshold=1024（单位为字节，默认值为 0）来代表当对象超过多大时就不在新生代分配，而是直接在老年代分配，此参数在新生代采用 Parallel Scavenge GC 时无效，Parallel Scavenge GC 会根据运行状况决定什么对象直接在老年代上分配内存；另一种为大的数组对象，且数组中无引用外部对象。
新生代和老生代因为结构划分不一样，其串行收集器算法也不一样
新生代串行收集器
采用 stop the world 策略，步骤大概是：先从 eden 区扫描，把存活的对象拷贝到 to 区，如果 to 区放不下的对象直接拷贝到 old 区。再从 from 区扫描存活对象，如果对象存活次数超过阀值的就移到老年区，其他的移到 to 区。做完之后 from 和 to 区概念互换 (from 和 to 只是运行时的概念，其实就对应存活 1 区和存活 2 区)

## <a></a><a id="206__984" target="_blank"></a>206\. 简述分代垃圾回收器是怎么工作的？

<pre>1
2
3
     首先，垃圾回收器将某些特殊的对象定义为GC根对象。
     接下来，垃圾回收器会对内存中的整个对象图进行遍历，它先从GC根对象开始，然后是根对象引用的其它对象，比如实例变量。回收器将访问到的所有对象都标记为存活。
     存活对象在上图中被标记为蓝色。当标记阶段完成了之后，所有的存活对象都已经被标记完了。其它的那些（上图中灰色的那些）也就是GC根对象不可达的对象，也就是说你的应用不会再用到它们了。这些就是垃圾对象，回收器将会在接下来的阶段中清除它们。

</pre>

## <a></a><a id="207__JVM__989" target="_blank"></a>207\. 说一下 JVM 调优的工具？

<pre>1
2
3
  Jconsole（Java Monitoring and Management Console）是从java5开始，在JDK中自带的java监控和管理控制台，用于对JVM中内存，线程和类等的监控，是一个基于JMX（java management extensions）的GUI性能监测工具。jconsole使用jvm的扩展机制获取并展示虚拟机中运行的应用程序的性能和资源消耗等信息。
  VisualVM 是一个工具，它提供了一个可视界面，用于查看 Java 虚拟机 (Java Virtual Machine, JVM) 上运行的基于 Java 技术的应用程序（Java 应用程序）的详细信息。VisualVM 对 Java Development Kit (JDK) 工具所检索的 JVM 软件相关数据进行组织，并通过一种使您可以快速查看有关多个 Java 应用程序的数据的方式提供该信息。您可以查看本地应用程序以及远程主机上运行的应用程序的相关数据。此外，还可以捕获有关 JVM 软件实例的数据，并将该数据保存到本地系统，以供后期查看或与其他用户共享。
  MAT(Memory Analyzer Tool)，一个基于Eclipse的内存分析工具，是一个快速、功能丰富的Java heap分析工具，它可以帮助我们查找内存泄漏和减少内存消耗。使用内存分析工具从众多的对象中进行分析，快速的计算出在内存中对象的占用大小，看看是谁阻止了垃圾收集器的回收工作，并可以通过报表直观的查看到可能造成这种结果的对象。

</pre>

## <a></a><a id="208__JVM__994" target="_blank"></a>208\. 常用的 JVM 调优的参数都有哪些？

<pre>1
2
3
4
5
6
7
8
    （1）-Xms20M
     表示设置JVM启动内存的最小值为20M，必须以M为单位
     （2）-Xmx20M
     表示设置JVM启动内存的最大值为20M，必须以M为单位。将-Xmx和-Xms设置为一样可以避免JVM内存自动扩展。大的项目-Xmx和-Xms一般都要设置到10G、20G甚至还要高
      3）-verbose:gc
     表示输出虚拟机中GC的详细情况
     （4）-Xss128k
     表示可以设置虚拟机栈的大小为128k

</pre>

# <a></a><a id="_1005" target="_blank"></a>杂七杂八

## <a></a><a id="209_a__a__b__a__b__1006" target="_blank"></a>209\. a = a + b 和 a += b 的区别？

<pre>1
2
3
4
5
6
a = a + b 不会做类型转换的
a int  b str
a + b  int无法转成str

a += b
a = a(String) + b

</pre>