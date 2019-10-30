> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://www.cnblogs.com/sunniest/p/4574563.html

# JVM 结构探究 ----

## 1.JVM 结构示意图

![](https://images0.cnblogs.com/blog2015/694841/201506/132232520196087.jpg)

## 2.JVM 运行时数据区

**1) 程序计数器 (Program Counter Register)**

　　程序计数器是用于存储每个线程下一步将执行的 JVM 指令，如该方法为 native 的，则程序计数器中不存储任何信息

**2)JVM 栈 (JVM Stack)**

　　JVM 栈是线程私有的，每个线程创建的同时都会创建 JVM 栈，JVM 栈中存放的为当前线程中局部基本类型的变量（java 中定义的八种基本类型：boolean、char、byte、short、int、long、float、double）、部分的返回结果以及 Stack Frame，非基本类型的对象在 JVM 栈上仅存放一个指向堆上的地址

**3) 堆 (heap)**

　　它是 JVM 用来存储对象实例以及数组值的区域，可以认为 Java 中所有通过 new 创建的对象的内存都在此分配，Heap 中的对象的内存需要等待 GC 进行回收。

　　（1）堆是 JVM 中所有线程共享的，因此在其上进行对象内存的分配均需要进行加锁，这也导致了 new 对象的开销是比较大的

　　（2）Sun Hotspot JVM 为了提升对象内存分配的效率，对于所创建的线程都会分配一块独立的空间 TLAB（Thread Local Allocation Buffer），其大小由 JVM 根据运行的情况计算而得，在 TLAB 上分配对象时不需要加锁，因此 JVM 在给线程的对象分配内存时会尽量的在 TLAB 上分配，在这种情况下 JVM 中分配对象内存的性能和 C 基本是一样高效的，但如果对象过大的话则仍然是直接使用堆空间分配

　　（3）TLAB 仅作用于新生代的 Eden Space，因此在编写 Java 程序时，通常多个小的对象比大的对象分配起来更加高效。

**4) 方法区（Method Area）**

　　（1）在 Sun JDK 中这块区域对应的为 PermanetGeneration，又称为持久代。

　　（2）方法区域存放了所加载的类的信息（名称、修饰符等）、类中的静态变量、类中定义为 final 类型的常量、类中的 Field 信息、类中的方法信息，当开发人员在程序中通过 Class 对象中的 getName、isInterface 等方法来获取信息时，这些数据都来源于方法区域，同时方法区域也是全局共享的，在一定的条件下它也会被 GC，当方法区域需要使用的内存超过其允许的大小时，会抛出 OutOfMemory 的错误信息。

**5) 本地方法栈（Native Method Stacks）**

　　JVM 采用本地方法栈来支持 native 方法的执行，此区域用于存储每个 native 方法调用的状态。

**6) 运行时常量池（Runtime Constant Pool）**

　　存放的为类中的固定的常量信息、方法和 Field 的引用信息等，其空间从方法区域中分配。JVM 在加载类时会为每个 class 分配一个独立的常量池，但是运行时常量池中的字符串常量池是全局共享的。