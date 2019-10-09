> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=2&sn=5610afab774b4114110c993fd0fdc43d&chksm=fbdb18d3ccac91c5e3d2978e3a780b14d09e97c997a5c50410d1adb1930da76f40238d8f409d&scene=21#wechat_redirect

前言
==

本文快速回顾了 Java 中最基础的知识点，用作面试复习，事半功倍。

此为下篇，内容包括：高并发编程，Java8 新特性。

高并发编程  

========

多线程和单线程的区别和联系：
--------------

答：

在单核 CPU 中，将 CPU 分为很小的时间片，在每一时刻只能有一个线程在执行，是一种微观上轮流占用 CPU 的机制。

多线程会存在线程上下文切换，会导致程序执行速度变慢，即采用一个拥有两个线程的进程执行所需要的时间比一个线程的进程执行两次所需要的时间要多一些。

结论：**即采用多线程不会提高程序的执行速度，反而会降低速度，但是对于用户来说，可以减少用户的响应时间。**

如何指定多个线程的执行顺序？
--------------

三种方式：https://blog.csdn.net/difffate/article/details/63684290

**方式一**

解析：面试官会给你举个例子，如何让 10 个线程按照顺序打印 0123456789？（写代码实现）

答：  
设定一个 orderNum，每个线程执行结束之后，更新 orderNum，指明下一个要执行的线程。并且唤醒所有的等待线程。  
在每一个线程的开始，要 while 判断 orderNum 是否等于自己的要求值！！不是，则 wait，是则执行本线程。

**方式二**

通过主线程 Join()

**方式三**

通过线程执行时 Join()

线程和进程的区别（必考）
------------

答：

进程是一个 “执行中的程序”，是系统进行资源分配和调度的一个独立单位；

线程是进程的一个实体，一个进程中拥有多个线程，线程之间共享地址空间和其它资源（所以通信和同步等操作线程比进程更加容易）；

线程上下文的切换比进程上下文切换要快很多。

（1）进程切换时，涉及**到当前进程的 CPU 环境的保存和新被调度运行进程的 CPU 环境的设置**。

（2）线程切**换仅需要保存和设置少量的寄存器内容，不涉及存储管理方面的操作**。

多线程产生死锁的 4 个必要条件
----------------

答：

*   资源互斥条件：一个资源每次只能被一个线程使用；
    
*   请求与保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放；
    
*   不剥夺条件：进程已经获得的资源，在未使用完之前，不能强行剥夺；
    
*   循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系。
    

sleep() 和 wait(n)、wait( ) 的区别
-----------------------------

答：

sleep 方法：是 Thread 类的静态方法，当前线程将睡眠 n 毫秒，线程进入阻塞状态。当睡眠时间到了，会解除阻塞，进行运行状态，等待 CPU 的到来。**睡眠不释放锁（如果有的话）**；

wait 方法：是 Object 的方法，必须与 synchronized 关键字一起使用，线程进入阻塞状态，当 notify 或者 notifyall 被调用后，会解除阻塞。**但是，只有重新占用互斥锁之后才会进入可运行状态。睡眠时，释放互斥锁。**

synchronized 关键字
----------------

该关键字是一个几种锁的封装。详细请看 Java 虚拟机知识点面试手册。

volatile 关键字
------------

经典：https://www.jianshu.com/p/195ae7c77afe

解析：关于指令重排序的问题，可以查阅 DCL 双检锁失效相关资料。

答：

通过关键字 sychronize 可以防止多个线程进入同一段代码，在某些特定场景中，volatile 相当于一个轻量级的 sychronize，因为不会引起线程的上下文切换。**该关键字可以保证可见性不保证原子性。**

功能：

主内存和工作内存，直接与主内存产生交互，进行读写操作，保证可见性；

禁止 JVM 进行的指令重排序。

ThreadLocal（线程局部变量）关键字
----------------------

详解：https://www.cnblogs.com/dolphin0520/p/3920407.html

答：

当使用 ThreadLocal 维护变量时，其为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立的改变自己的副本，而不会影响其他线程对应的副本。

ThreadLocal 内部实现机制：

每个线程内部都会维护一个类似 HashMap 的对象，称为 ThreadLocalMap，里边会包含若干了 Entry（K-V 键值对），相应的线程被称为这些 Entry 的属主线程；  
Entry 的 Key 是一个 ThreadLocal 实例，Value 是一个线程特有对象。

Entry 的作用即是：为其属主线程建立起一个 ThreadLocal 实例与一个线程特有对象之间的对应关系；

Entry 对 Key 的引用是弱引用；

Entry 对 Value 的引用是强引用。

**其中最重要的一个应用实例就是经典 Web 交互模型中的 “一个请求对应一个服务器线程”（Thread-per-Request）的处理方式**

Atomic 关键字
----------

答：可以使基本数据类型以原子的方式实现自增自减等操作。

内存泄漏和内存溢出
---------

答：

### 内存泄露的几种方式：

1.  静态集合类像 HashMap、Vector 等的使用最容易出现内存泄露，这些静态变量的生命周期和应用程序一致，所有的对象 Object 也不能被释放，因为他们也将一直被 Vector 等应用着。
    

```
1Static Vector v = new Vector(); 
2for (int i = 1; i<100; i++) 
3{ 
4    Object o = new Object(); 
5    v.add(o); 
6    o = null; 
7}


```

2.  各种连接，数据库连接，网络连接，IO 连接等没有显示调用 close 关闭，不被 GC 回收导致内存泄露。
    
3.  监听器的使用，在释放对象的同时没有相应删除监听器的时候也可能导致内存泄露。
    

概念：

*   内存溢出指的是内存不够用了；
    
*   内存泄漏是指对象可达，但是没用了，或者你把指向内存的引用给弄丢了。即本该被 GC 回收的对象并没有被回收；
    
*   内存泄露是导致内存溢出的原因之一；内存泄露积累起来将导致内存溢出。
    

Java 8
======

Java 语言的新特性
-----------

https://blog.csdn.net/u014470581/article/details/54944384

### **Lambda 表达式**（也称为闭包）

1.8 以前，开发者只能使用匿名内部类代替 Lambda 表达式，现在允许我们**将函数当成参数传递给某个方法**，或者把代码本身当做数据处理。

```
1Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );


```

如果 Lambda 表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来，类似于 Java 中的函数体，例如：

```
1Arrays.asList( "a", "b", "d" ).forEach( e -> {
2    System.out.print( e );
3    System.out.print( e );
4} );


```

Lambda 表达式有返回值，返回值的类型也由编译器推理得出。如果 Lambda 表达式中的语句块只有一行，则可以不用使用 return 语句，下列两个代码片段效果相同：

```
1Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );


```

和

```
1Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
2    int result = e1.compareTo( e2 );
3    return result;
4} );


```

### 函数式接口

**指的是只有一个函数的接口**，java.lang.Runnable 和 java.util.concurrent.Callable 就是函数式接口的例子；

只要某个开发者在该接口中添加一个函数，则该接口就不再是函数式接口进而导致编译失败。为了克服这种代码层面的脆弱性，并显式说明某个接口是函数式接口，java8 提供了一个特殊的注解 @Functionallnterface 来标明该接口是一个函数式接口。

不过有一点需要注意，**默认方法和静态方法不会破坏函数式接口的定义**，因此如下的代码是合法的。

```
1@FunctionalInterface
2public interface FunctionalDefaultMethods {
3    void method();
4
5    default void defaultMethod() {            
6    }        
7}


```

### 接口的默认方法和静态方法

默认方法和抽象方法之间的区别在于抽象方法需要实现，而默认方法不需要。接口提供的默认方法会被接口的实现类继承或者覆写。（**换句话说，可以直接继承，也可以覆写该默认方法**）

静态方法：见上方链接

由于 JVM 上的默认方法的实现在**字节码层面提供了支持**，**因此效率非常高**。默认方法允许在**不打破现有继承体系的基础上改进接口**。该特性在官方库中的应用是：给 java.util.Collection 接口添加新方法，如 stream()、parallelStream()、forEach() 和 removeIf() 等等。

尽管默认方法有这么多好处，但在实际开发中应该谨慎使用：在复杂的继承体系中，默认方法**可能引起歧义和编译错误**。如果你想了解更多细节，可以参考官方文档。

### 方法引用

不甚理解，见上方链接

### 引入重复注解

注解有一个很大的限制是：在同一个地方不能多次使用同一个注解。Java 8 打破了这个限制，引入了重复注解的概念，允许在同一个地方多次使用同一个注解。

在 Java 8 中使用 @Repeatable 注解定义重复注解，实际上，这并不是语言层面的改进，而是编译器做的一个 trick，底层的技术仍然相同。

### 更好的类型推断

Java 8 编译器在类型推断方面有很大的提升，在很多场景下编译器可以推导出某个参数的数据类型，从而使得代码更为简洁。

### 注解的使用场景拓宽

**注解几乎可以使用在任何元素上**：局部变量、接口类型、超类和接口实现类，甚至可以用在函数的异常定义上。

Java 编译器的新特性
------------

### 参数名称

为了在运行时获得 Java 程序中方法的参数名称，老一辈的 Java 程序员必须使用不同方法，例如 Paranamer liberary。Java 8 终于将这个特性规范化，在语言层面（使用反射 API 和 Parameter.getName() 方法）和字节码层面（使用新的 javac 编译器以及 - parameters 参数）提供支持。

Java 官方库的新特性
------------

### HashMap/CurrentHashMap 等变化

HashMap 是数组 + 链表 + 红黑树（**JDK1.8 增加了红黑树部分**）实现。

### Optional

在 Java 8 之前，Google Guava 引入了 Optionals 类来解决 NullPointerException，从而避免源码被各种 null 检查污染，以便开发者写出更加整洁的代码。Java 8 也将 Optional 加入了官方库。

接下来看一点使用 Optional 的例子：可能为空的值或者某个类型的值：

```
1Optional< String > fullName = Optional.ofNullable( null );
2System.out.println( "Full Name is set? " + fullName.isPresent() );        
3System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) ); 
4System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );


```

如果 Optional 实例持有一个非空值，则 isPresent() 方法返回 true，否则返回 false；orElseGet() 方法，Optional 实例持有 null，则可以接受一个 lambda 表达式生成的默认值；map() 方法可以将现有的 Opetional 实例的值转换成新的值；orElse() 方法与 orElseGet() 方法类似，但是在持有 null 的时候返回传入的默认值。

### Streams

新增的 Stream API（java.util.stream）将生成环境的函数式编程引入了 Java 库中。

```
1// Calculate total points of all active tasks using sum()
2final long totalPointsOfOpenTasks = tasks
3    .stream()
4    .filter( task -> task.getStatus() == Status.OPEN )
5    .mapToInt( Task::getPoints )
6    .sum();
7
8System.out.println( "Total points: " + totalPointsOfOpenTasks );


```

### Date/Time API

包含了所有关于日期、时间、时区、持续时间和时钟操作的类。（Java 8 的日期与时间问题解决方案）

新的 java.time 包包含了所有关于日期、时间、时区、Instant（跟日期类似但是精确到纳秒）、duration（持续时间）和时钟操作的类。

**这些类都是不可变的、线程安全的。**

### Base64

对 Base64 编码的支持已经被加入到 Java 8 官方库中，这样不需要使用第三方库就可以进行 Base64 编码

### 并行数组

Java8 版本新增了很多新的方法，用于支持并行数组处理。

最重要的方法是 parallelSort()，可以显著加快多核机器上的数组排序。

### 并发性

基于新增的 lambda 表达式和 steam 特性，为 Java 8 中为 java.util.concurrent.ConcurrentHashMap 类添加了新的方法来**支持聚焦操作**；

另外，也为 java.util.concurrentForkJoinPool 类添加了新的方法来**支持通用线程池操作**

JVM 的新特性
--------

### JVM 内存管理方面，**由元空间代替了永久代。**

> 其实，移除永久代的工作从 JDK1.7 就开始了。JDK1.7 中，存储在永久代的部分数据就已经转移到了 Java Heap 或者是 Native Heap。但永久代仍存在于 JDK1.7 中，并没完全移除，譬如符号引用 (Symbols) 转移到了 native heap；字面量 (interned strings) 转移到了 java heap；类的静态变量 (class statics) 转移到了 java heap。

区别：

1.  元空间并不在虚拟机中，而是**使用本地内存**；
    
2.  默认情况下，元空间的大小**仅受本地内存限制**；
    
3.  也可以通过 -XX：MetaspaceSize 指定元空间大小。
    

**为什么要做这个转换？所以，最后给大家总结以下几点原因：**

*   **字符串**存在永久代中，容易出现性能问题和内存溢出。
    
*   **类及方法的信息等比较难确定其大小**，因此对于永久代的大小指定比较困难，太小容易出现永久代溢出，太大则容易导致老年代溢出。
    
*   永久代会**为 GC 带来不必要的复杂度**，并且回收效率偏低。
    
*   Oracle 可能会将 HotSpot 与 JRockit 合二为一。
    

Java 7 和 8 的新特性（英文）
-------------------

**New highlights in Java SE 8**

1.  Lambda Expressions
    
2.  Pipelines and Streams
    
3.  Date and Time API
    
4.  Default Methods
    
5.  Type Annotations
    
6.  Nashhorn JavaScript Engine
    
7.  Concurrent Accumulators
    
8.  Parallel operations
    
9.  PermGen Error Removed
    

**New highlights in Java SE 7**

1.  Strings in Switch Statement
    
2.  Type Inference for Generic Instance Creation
    
3.  Multiple Exception Handling
    
4.  Support for Dynamic Languages
    
5.  Try with Resources
    
6.  Java nio Package
    
7.  Binary Literals, Underscore in literals
    
8.  Diamond Syntax
    

*   Difference between Java 1.8 and Java 1.7?
    
*   Java 8 特性
    

Java 与 C++ 的区别
--------------

*   Java 是**纯粹的面向对象语言**，所有的对象都继承自 java.lang.Object，
    
*   C++ 为了兼容 C 即**支持面向对象也支持面向过程**。
    
*   Java 通过**虚拟机从而实现跨平台特性**，但是 C++ 依赖于特定的平台。
    
*   Java **没有指针**，它的引用可以理解为安全指针，而 C++ 具有和 C 一样的指针。
    
*   Java **支持自动垃圾回收**，而 C++ 需要手动回收。
    
*   Java **不支持多重继承**，只能通过**实现多个接口**来达到相同目的，而 C++ 支持多重继承。
    
*   Java **不支持操作符重载**，虽然可以对两个 String 对象支持加法运算，但是这是语言内置支持的操作，不属于操作符重载，而 C++ 可以。
    
*   Java 的 **goto 是保留字，但是不可用**，C++ 可以使用 goto。
    
*   Java **不支持条件编译**，C++ 通过 #ifdef #ifndef 等预处理命令从而实现条件编译。
    

What are the main differences between Java and C++?

关注我
===

本人目前为后台开发工程师，主要关注 Python 爬虫，后台开发等相关技术。

**原创博客主要内容：**

*   笔试面试复习知识点手册
    
*   Leetcode 算法题解析（前 150 题）
    
*   剑指 offer 算法题解析
    
*   Python 爬虫相关实战
    
*   后台开发相关实战
    

**同步更新以下几大博客：**

*   Csdn：
    
    http://blog.csdn.net/qqxx6661
    
    拥有专栏：
    
    Leetcode 题解（Java/Python）、Python 爬虫开发
    
*   知乎：
    
    https://www.zhihu.com/people/yang-zhen-dong-1/
    
    拥有专栏：
    
    码农面试助攻手册
    
*   掘金：
    
    https://juejin.im/user/5b48015ce51d45191462ba55
    
*   简书：
    
    https://www.jianshu.com/u/b5f225ca2376
    
*   个人公众号：Rude3Knife
    
    ![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl30Us0VWia6glRcD20yyZPSRHL9ibpUHDbibyPDtd7DKoMOVU2W0HichHqQ0Q/640?wx_fmt=png)