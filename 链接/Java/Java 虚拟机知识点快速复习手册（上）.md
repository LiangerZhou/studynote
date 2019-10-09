> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483795&idx=1&sn=4f41e144656b6b6ab6089cd558f6f5ab&chksm=fbdb1812ccac9104e425b3984659ac422afbf0505268645be65935c33bad808d4571dfed5d1f&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_jpg/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdjI4mdtX0ZhvkAgYWhCb0jffEq5ljFn3ZDRIZpHYT13rjuAllWicmhTw/640?wx_fmt=jpeg)在这里插入图片描述

前言
==

本文快速回顾了常考的的知识点，用作面试复习，事半功倍。

上篇主要内容为：虚拟机数据区域，垃圾回收

下篇主要内容为：类加载机制

面试知识点复习手册
=========

**全复习手册文章导航**

点击公众号下方技术推文——面试冲刺

**已发布知识点复习手册**

*   [Java 基础知识点面试手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=1&sn=de2751593468f470902b698c19f8987f&chksm=fbdb18d3ccac91c56939e55cd1f0ca1b4753fd178d229440ecbf89752f5e0d518acd453e2497&scene=21#wechat_redirect)  
    
*   [Java 基础知识点面试手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=2&sn=5610afab774b4114110c993fd0fdc43d&chksm=fbdb18d3ccac91c5e3d2978e3a780b14d09e97c997a5c50410d1adb1930da76f40238d8f409d&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483743&idx=1&sn=cc38aab9429905ddc757b529a386d1dd&chksm=fbdb18deccac91c8d0be8b3ae0e4266bb08ead73a2e57f2c977705e7b26ceaaec7aff5d5c67c&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（中）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483753&idx=1&sn=74b8180dc1a1804c355174ed34e6e33d&chksm=fbdb18e8ccac91fe3ce31ed9713bf23598e7f98dcb6d5a22b79aa92b1c773134bfebe71fbbca&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483762&idx=1&sn=1f121db6552a2e77d53c500fa812fc6c&chksm=fbdb18f3ccac91e58229dd3efd09c876722d58863c2b6ff6d444b0825a955a776ced947d8470&scene=21#wechat_redirect)  
    
*   [Redis 基础知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483768&idx=1&sn=ea83244e4b9f1d6f912ca0aadab74466&chksm=fbdb18f9ccac91efe9e32704ac3d69cf1ad390ddae0f169c118ea8b9da91c6e4e6e849677a6d&scene=21#wechat_redirect)  
    
*   [Redis 基础知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483773&idx=1&sn=6bbd589e174b5d6f8bb3d6b242eb6132&chksm=fbdb18fcccac91eaa8c9d941c1d3f8d2f3874841c417d30e3ccd185b1494d51ea2fdf384c876&scene=21#wechat_redirect)  
    
*   [Java 并发知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483778&idx=1&sn=124096cdc14958b8cdae04b805d00fdc&chksm=fbdb1803ccac9115e967cd538a8008a19dacea286a632284d2e8765c6b8bd48f4f235caddd29&scene=21#wechat_redirect)
    
*   [Java 并发知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483782&idx=1&sn=a0cd333ce6a32fa3f29cfae5fb6fe7e1&chksm=fbdb1807ccac9111003947936006f02972b45a6a6592f6d107f4dceb6b19c82fdff25b3ff355&scene=21#wechat_redirect)  
    
*   [双非硕士的春招秋招经验总结——对校招，复习以及面试心态的理解](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483669&idx=1&sn=9d45d0a80c55c2b81611e150b059fb2f&chksm=fbdb1894ccac9182a43949d445accee91afab50f27c11906ae3d3121e24908469424d0726369&scene=21#wechat_redirect)
    
*   ...... 等（请查看全复习手册导航）
    

参考
==

本文内容参考自 CyC2018 的 Github 仓库：CS-Notes

https://github.com/CyC2018/CS-Notes/

有删减，修改，补充额外增加内容

其他参考文章：

*   微信文章：精华：Java 开发岗面试知识点解析
    
*   http://how2j.cn/k/j2se-interview/j2se-interview-java/624.html
    

本作品采用知识共享署名 - 非商业性使用 4.0 国际许可协议进行许可。

数据区域
====

**此为 1.6 图例：**

https://blog.csdn.net/o_nianchenzi_o/article/details/78629929

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZd37WqtgicfUUrVBZktObT6jn72rgVbOBcia46dibVicibT1CgVYFx29eoQnw/640?wx_fmt=png)在这里插入图片描述

程序计数器
-----

记录正在执行的虚拟机字节码指令的地址（如果正在执行的是本地方法则为空）。

虚拟机栈
----

**存放局部变量**，由声明在某方法，或某代码段里（比如 for 循环），执行到它的时候在栈中开辟内存，当局部变量一但脱离作用域，内存立即释放

每个 Java 方法在执行的同时会创建一个栈帧用于存储局部变量表、操作数栈、常量池引用等信息。每一个方法从调用直至执行完成的过程，就对应着一个栈帧在 Java 虚拟机栈中入栈和出栈的过程。

可以通过 -Xss 这个虚拟机参数来指定一个程序的 Java 虚拟机栈内存大小：

```
1java -Xss=512M HackTheJava


```

该区域可能抛出以下异常：

*   当线程请求的栈深度超过最大值，会抛出 StackOverflowError 异常；
    
*   栈进行动态扩展时如果无法申请到足够内存，会抛出 OutOfMemoryError 异常。
    

本地方法栈
-----

本地方法不是用 Java 实现，对待这些方法需要特别处理。

堆
-

所有对象实例都在这里分配内存。

因此虚拟机把 Java 堆分成以下三块：

*   新生代（Young Generation）
    
*   老年代（Old Generation）
    
*   永久代（Permanent Generation）（JDK1.7 永久代才在堆中，1.6 中被称为运行时常量池。JDK 1.8 之后，取消了永久代, 变为虚拟机之外的元空间）
    

**关于元空间详细说明，请看 Java - 基础面试题整合版 - java8 的新特性**

可以通过 -Xms 和 -Xmx 两个虚拟机参数来指定一个程序的 Java 堆内存大小，第一个参数设置初始值，第二个参数设置最大值。

```
1java -Xms=1M -Xmx=2M HackTheJava


```

默认空余堆内存**小于 40% 时**，JVM 就会增大堆直到 - Xmx 的最大限制；

空余堆内存**大于 70% 时**，JVM 会减少堆直到 -Xms 的最小限制；

服务器一般设置 - Xms、-Xmx **相等**以避免在每次 GC 后调整堆的大小。

方法区
---

**用于存放已被加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。**

和 Java 堆一样不需要连续的内存，并且可以动态扩展，动态扩展失败一样会抛出 OutOfMemoryError 异常。

对这块区域进行垃圾回收的主要目标**是对常量池的回收和对类的卸载**，但是一般比较难实现。

**JDK 1.7 之前，HotSpot 虚拟机把它当成永久代来进行垃圾回收，JDK 1.8 之后，取消了永久代，用 metaspace（元数据）区替代。**

运行时常量池
------

运行时常量池是方法区的一部分。

**Class 文件中的常量池（编译器生成的各种字面量和符号引用）会在类加载后被放入这个区域。**

除了在编译期生成的常量，还允许动态生成，例如 String 类的 intern()。这部分常量也会被放入运行时常量池。

直接内存
----

在 JDK 1.4 中新加入了 NIO （传统的 IO 又称 BIO，即阻塞式 IO，NIO 就是非阻塞 IO 了。AIO 就是异步 IO）类，它可以使用 Native 函数库直接分配堆外内存，然后通过一个存储在 Java 堆里的 DirectByteBuffer 对象作为这块内存的引用进行操作。这样能在一些场景中显著提高性能，因为避免了在 Java 堆和 Native 堆中来回复制数据。

垃圾回收
====

程序计数器、虚拟机栈和本地方法栈这三个区域属于线程私有的，只存在于线程的生命周期内，**线程结束之后也会消失，因此不需要对这三个区域进行垃圾回收**。

**垃圾回收主要是针对 Java 堆和方法区进行。**

判断一个对象是否可回收
-----------

### 1. 引用计数算法

给对象添加一个引用计数器，当对象增加一个引用时计数器加 1，引用失效时计数器减 1。引用计数为 0 的对象可被回收。

两个对象出现循环引用的情况下，此时引用计数器永远不为 0，导致无法对它们进行回收。

```
 1public class ReferenceCountingGC {
 2    public Object instance = null;
 3
 4    public static void main(String[] args) {
 5        ReferenceCountingGC objectA = new ReferenceCountingGC();
 6        ReferenceCountingGC objectB = new ReferenceCountingGC();
 7        objectA.instance = objectB;
 8        objectB.instance = objectA;
 9    }
10}


```

**正因为循环引用的存在，因此 Java 虚拟机不适用引用计数算法。**

### 2. 可达性分析算法

通过 **GC Roots** 作为起始点进行搜索，能够到达到的对象都是存活的，不可达的对象可被回收。

在 Java 中 GC Roots 一般包含以下内容：

*   **虚拟机栈**中引用的对象
    
*   **本地方法栈**中引用的对象
    
*   方法区中**类静态属性**引用的对象
    
*   方法区中的**常量**引用的对象
    

### 3. 引用类型

无论是通过引用计算算法判断对象的引用数量，还是通过可达性分析算法判断对象的引用链是否可达，判定对象是否可被回收都与引用有关。

Java 具有四种强度不同的引用类型。

**（一）强引用**

被强引用关联的对象**不会被垃圾收集器回收。**

**使用 new 一个新对象的方式来创建强引用。**

```
1Object obj = new Object();


```

**（二）软引用**

被软引用关联的对象，只有在**内存不够的情况下才会被回收。**

**使用 SoftReference 类来创建软引用。**

**（三）弱引用**

被弱引用关联的对象**一定会被垃圾收集器回收，也就是说它只能存活到下一次垃圾收集发生之前。**

*   **使用 WeakReference 类来实现弱引用。**
    
    WeakHashMap 的 Entry 继承自 WeakReference，**主要用来实现缓存。**
    
    Tomcat 中的 ConcurrentCache 就使用了 WeakHashMap 来实现缓存功能。ConcurrentCache 采取的是分代缓存，经常使用的对象放入 eden 中，而不常用的对象放入 longterm。
    
    eden 使用 ConcurrentHashMap 实现，longterm 使用 WeakHashMap，保证了不常使用的对象容易被回收。
    

**（四）虚引用**

又称为幽灵引用或者幻影引用。一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用取得一个对象实例。

**为一个对象设置虚引用关联的唯一目的就是能在这个对象被收集器回收时收到一个系统通知。**

使用 PhantomReference 来实现虚引用。

### 4. 方法区的回收

因为方法区主要存放永久代对象，而永久代对象的回收率比新生代差很多，因此在方法区上进行回收性价比不高。

**主要是对常量池的回收和对类的卸载。**

类的卸载条件很多，需要满足以下三个条件，并且**满足了也不一定会被卸载**：

*   **该类所有的实例都已经被回收**，也就是 Java 堆中不存在该类的任何实例。
    
*   加载该类的 **ClassLoader 已经被回收**。
    
*   该类对应的 java.lang.Class 对象没有在任何地方被引用，也就**无法在任何地方通过反射访问该类方法**。
    

> 可以通过 -Xnoclassgc 参数来控制是否对类进行卸载。

**在大量使用反射、动态代理、CGLib 等 ByteCode 框架、动态生成 JSP 以及 OSGi 这类频繁自定义 ClassLoader 的场景都需要虚拟机具备类卸载功能，以保证不会出现内存溢出。**

### 5. finalize()

finalize() 类似 C++ 的析构函数，用来做关闭外部资源等工作。但是 try-finally 等方式可以做的更好，并且该方法运行代价高昂，不确定性大，无法保证各个对象的调用顺序，因此最好不要使用。

当一个对象可被回收时，如果需要执行该对象的 finalize() 方法，那么就有可能通过在该方法中让对象重新被引用，从而实现自救。

垃圾收集算法
------

### 1. 标记 - 清除

将需要回收的对象进行标记，然后清理掉被标记的对象。

不足：

*   标记和清除过程效率都不高；
    
*   **会产生大量不连续的内存碎片，导致无法给大对象分配内存。**
    

### 2. 标记 - 整理

让所有存活的对象都向一端移动，然后直接清理掉端边界以外的内存。

### 3. 复制

将内存划分为大小相等的两块，每次只使用其中一块，当这一块内存用完了就将还存活的对象复制到另一块上面，然后再把使用过的内存空间进行一次清理。

主要不足是只使用了内存的一半。

**现在的商业虚拟机都采用复制算法**来回收**新生代**，但是并不是将内存划分为大小相等的两块，而是分为一块较大的 Eden 空间和两块较小的 Survior 空间，**每次使用 Eden 空间和其中一块 Survivor。**

在回收时，将 Eden 和 Survivor 中还存活着的对象一次性复制到另一块 Survivor 空间上，**最后清理 Eden 和使用过的那一块 Survivor**。

HotSpot 虚拟机的 Eden 和 Survivor 的大小比例默认为 8:1（8:1:1），保证了内存的利用率达到 90 %。(**证明了只往 eden 区放新对象**)

如果每次回收有多于 10% 的对象存活，那么一块 Survivor 空间就不够用了，此时需要依赖于老年代进行分配担保，也就是借用老年代的空间存储放不下的对象。

### 4. 分代收集

**现在的商业虚拟机采用分代收集算法，它根据对象存活周期将内存划分为几块，不同块采用适当的收集算法。**

一般将 Java 堆分为新生代和老年代。

*   新生代使用：复制算法
    
*   老年代使用：标记 - 清理 或者 标记 - 整理 算法
    

垃圾收集器
-----

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdNvPicu9IbrsXrBX3AetmcJdnQic4UM8tQ0EftRgS3P3HvxYPks8CtgXQ/640?wx_fmt=png)在这里插入图片描述

以上是 HotSpot 虚拟机中的 7 个垃圾收集器，连线表示垃圾收集器可以配合使用。

### 1. Serial 收集器

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdxFQZwLcg6TBq2ovHibwXuT88yaFnT3OCnLc8NJRvq94jibeLkIJFrgsw/640?wx_fmt=png)在这里插入图片描述

Serial 翻译为串行，可以理解为垃圾收集和用户程序交替执行，这意味着在执行垃圾收集的时候**需要停顿用户程序**。除了 CMS 和 G1 之外，其它收集器都是以串行的方式执行。**CMS 和 G1 可以使得垃圾收集和用户程序同时执行，被称为并发执行。**

它是单线程的收集器，只会使用一个线程进行垃圾收集工作。

**它的优点是简单高效，对于单个 CPU 环境来说，由于没有线程交互的开销，因此拥有最高的单线程收集效率。**

它是 **Client 模式下**的默认新生代收集器，因为在用户的桌面应用场景下，分配给虚拟机管理的内存一般来说不会很大。Serial 收集器收集几十兆甚至一两百兆的新生代停顿时间可以控制在一百多毫秒以内，只要不是太频繁，这点停顿是可以接受的。

### 2. ParNew 收集器

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdmQ62smBdLoztYna0jTctdCkyUHEOTdVq2JARMVyCVLkZ1yrSDC8kVA/640?wx_fmt=png)在这里插入图片描述

它是 Serial 收集器的多线程版本。

是 Server 模式下的虚拟机首选新生代收集器，除了性能原因外，主要是因为除了 Serial 收集器，只有它能与 CMS 收集器配合工作。

默认开始的线程数量与 CPU 数量相同，可以使用 -XX:ParallelGCThreads 参数来设置线程数。

### 3. Parallel Scavenge 收集器

与 ParNew 一样是多线程收集器。

其它收集器关注点是尽可能缩短垃圾收集时用户线程的停顿时间，而它的目标是达到一个可控制的吞吐量，它被称为 “吞吐量优先” 收集器。这里的吞吐量指 CPU 用于运行用户代码的时间占总时间的比值。

停顿时间越短就越适合需要与用户交互的程序，良好的响应速度能提升用户体验。而高吞吐量则可以高效率地利用 CPU 时间，尽快完成程序的运算任务，主要适合在后台运算而不需要太多交互的任务。

缩短停顿时间是以牺牲吞吐量和新生代空间来换取的：新生代空间变小，垃圾回收变得频繁，导致吞吐量下降。

可以通过一个开关参数打卡 GC 自适应的调节策略（GC Ergonomics），就不需要手工指定新生代的大小（-Xmn）、Eden 和 Survivor 区的比例、晋升老年代对象年龄等细节参数了。虚拟机会根据当前系统的运行情况收集性能监控信息，动态调整这些参数以提供最合适的停顿时间或者最大的吞吐量，这种方式称为 。

### 4. Serial Old 收集器

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdSlgiaxwaSibsUtNhFFtB5CvEAdibJ84XXEURmrPjBaf9b0D9CiapGkOOiaw/640?wx_fmt=png)在这里插入图片描述

是 Serial 收集器的老年代版本，也是给 Client 模式下的虚拟机使用。如果用在 Server 模式下，它有两大用途：

*   在 JDK 1.5 以及之前版本（Parallel Old 诞生以前）中与 Parallel Scavenge 收集器搭配使用。
    
*   作为 CMS 收集器的后备预案，在并发收集发生 Concurrent Mode Failure 时使用。
    

### 5. Parallel Old 收集器

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdauoniaYkFvagXdt0icd4B9wf3ohjJXicQib11U75ibZ2af2DgNDicxFTHlpQ/640?wx_fmt=png)在这里插入图片描述

是 Parallel Scavenge 收集器的老年代版本。

在注重吞吐量以及 CPU 资源敏感的场合，都可以优先考虑 Parallel Scavenge 加 Parallel Old 收集器。

### 6. CMS 收集器

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdPKLicrSRf1v2FPK1NW2fI9g37rLx3fpRuibyRLmLCNdWUWVcqiaMIJ7Dw/640?wx_fmt=png)在这里插入图片描述

CMS（Concurrent Mark Sweep），Mark Sweep 指的是**标记 - 清除算法**。

特点：**并发收集、低停顿。并发指的是用户线程和 GC 线程同时运行。**

分为以下四个流程：

*   初始标记：仅仅只是标记一下 GC Roots 能直接关联到的对象，速度很快，需要停顿。
    
*   并发标记：进行 GC Roots Tracing 的过程，它在整个回收过程中耗时最长，不需要停顿。
    
*   重新标记：为了修正并发标记期间因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，需要停顿。
    
*   并发清除：不需要停顿。
    

在整个过程中耗时最长的并发标记和并发清除过程中，收集器线程都可以与用户线程一起工作，不需要进行停顿。

具有以下缺点：

*   **吞吐量低**：低停顿时间是以牺牲吞吐量为代价的，导致 CPU 利用率不够高。
    
*   **无法处理浮动垃圾**，可能出现 Concurrent Mode Failure。浮动垃圾是指并发清除阶段由于用户线程继续运行而产生的垃圾，这部分垃圾只能到下一次 GC 时才能进行回收。由于浮动垃圾的存在，因此需要预留出一部分内存，意味着 CMS 收集不能像其它收集器那样等待老年代快满的时候再回收。可以使用 -XX:CMSInitiatingOccupancyFraction 来改变触发 CMS 收集器工作的内存占用百分，如果这个值设置的太大，导致预留的内存不够存放浮动垃圾，就会出现 Concurrent Mode Failure，这时虚拟机将临时启用 Serial Old 来替代 CMS。
    
*   **标记 - 清除算法导致的空间碎片**，往往出现老年代空间剩余，但无法找到足够大连续空间来分配当前对象，不得不提前触发一次 Full GC。
    

### 7. G1 收集器

G1（Garbage-First），它是一款**面向服务端应用的垃圾收集器**，在多 CPU 和大内存的场景下有很好的性能。**HotSpot 开发团队赋予它的使命是未来可以替换掉 CMS 收集器**。

Java 堆被分为新生代、老年代和永久代，其它收集器进行收集的范围都是整个新生代或者老生代，**而 G1 可以直接对新生代和永久代一起回收。**

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdrNXZxL6Iu1OZfX7GxvPyLUmXmKaNMCWYgu9FFoVAk90wiaUEhb0LicNg/640?wx_fmt=png)在这里插入图片描述

**G1 把堆划分成多个大小相等的独立区域（Region），新生代和老年代不再物理隔离。**

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdUPoNkk2DLJk5pqbPzdQBdxDfwRnGHS9Nebaib3L6T0v1MA0oOjpSmFw/640?wx_fmt=png)在这里插入图片描述

通过引入 Region 的概念，从而将原来的一整块内存空间划分成多个的小空间，使得每个小空间可以单独进行垃圾回收。这种划分方法带来了很大的灵活性，使得可预测的停顿时间模型成为可能。通过记录每个 Region 垃圾回收时间以及回收所获得的空间（这两个值是通过过去回收的经验获得），并维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的 Region。

**每个 Region 都有一个 Remembered Set，用来记录该 Region 对象的引用对象所在的 Region。通过使用 Remembered Set，在做可达性分析的时候就可以避免全堆扫描**。

如果不计算维护 Remembered Set 的操作，**G1 收集器的运作大致可划分为以下几个步骤**：

*   初始标记
    
*   并发标记
    
*   最终标记：为了修正在并发标记期间因用户程序继续运作而导致标记产生变动的那一部分标记记录，虚拟机将这段时间对象变化记录在线程的 Remembered Set Logs 里面，最终标记阶段需要把 Remembered Set Logs 的数据合并到 Remembered Set 中。这阶段需要停顿线程，但是可并行执行。
    
*   筛选回收：首先对各个 Region 中的回收价值和成本进行排序，根据用户所期望的 GC 停顿是时间来制定回收计划。此阶段其实也可以做到与用户程序一起并发执行，但是因为只回收一部分 Region，时间是用户可控制的，而且停顿用户线程将大幅度提高收集效率。
    

具备如下特点：

*   **空间整合：整体来看是基于 “标记 - 整理” 算法实现的收集器**，从局部（两个 Region 之间）上来看是基于 “复制” 算法实现的，这意味着运行期间不会产生内存空间碎片。
    
*   **可预测的停顿**：能让使用者明确指定在一个长度为 M 毫秒的时间片段内，消耗在 GC 上的时间不得超过 N 毫秒。
    

更详细内容请参考：Getting Started with the G1 Garbage Collector

### 8. 比较

| 收集器 | 串行 / 并行 / 并发 | 新生代 / 老年代 | 收集算法 | 目标 | 适用场景 |
| --- | --- | --- | --- | --- | --- |
| **Serial** | 串行 | 新生代 | 复制 | 响应速度优先 | 单 CPU 环境下的 Client 模式 |
| **Serial Old** | 串行 | 老年代 | 标记 - 整理 | 响应速度优先 | 单 CPU 环境下的 Client 模式、CMS 的后备预案 |
| **ParNew** | 串行 + 并行 | 新生代 | 复制算法 | 响应速度优先 | 多 CPU 环境时在 Server 模式下与 CMS 配合 |
| **Parallel Scavenge** | 串行 + 并行 | 新生代 | 复制算法 | 吞吐量优先 | 在后台运算而不需要太多交互的任务 |
| **Parallel Old** | 串行 + 并行 | 老年代 | 标记 - 整理 | 吞吐量优先 | 在后台运算而不需要太多交互的任务 |
| **CMS** | 并行 + 并发 | 老年代 | 标记 - 清除 | 响应速度优先 | 集中在互联网站或 B/S 系统服务端上的 Java 应用 |
| **G1** | 并行 + 并发 | 新生代 + 老年代 | 标记 - 整理 + 复制算法 | 响应速度优先 | 面向服务端应用，将来替换 CMS |

内存分配与回收策略
---------

对象的内存分配，也就是在堆上分配。主要分配在新生代的 Eden 区上，少数情况下也可能直接分配在老年代中。

### 1. Minor GC 和 Full GC

*   Minor GC：发生在新生代上，因为新生代对象存活时间很短，因此 Minor GC 会频繁执行，执行的速度一般也会比较快。
    
*   Full GC：发生在老年代上，老年代对象和新生代的相反，其存活时间长，因此 Full GC 很少执行，而且执行速度会比 Minor GC 慢很多。
    

### 2. 内存分配策略

**（一）对象优先在 Eden 分配**

**大多数情况下，对象在新生代 Eden 区分配，当 Eden 区空间不够时，发起 Minor GC。**

**（二）大对象直接进入老年代**

大对象是指需要连续内存空间的对象，最典型的大对象是那种很长的字符串以及数组。

经常出现大对象会提前触发垃圾收集以获取足够的连续空间分配给大对象。

-XX:PretenureSizeThreshold，**大于此值的对象直接在老年代分配，避免在 Eden 区和 Survivor 区之间的大量内存复制。**

**（三）长期存活的对象进入老年代**

为对象定义年龄计数器，对象在 Eden 出生并经过 Minor GC 依然存活，将移动到 Survivor 中，年龄就增加 1 岁，增加到一定年龄则移动到老年代中。**默认 15 岁后就到年老代**。

-XX:MaxTenuringThreshold 用来定义年龄的阈值。

**（四）动态对象年龄判定**

虚拟机并不是永远地要求对象的年龄必须达到 MaxTenuringThreshold 才能晋升老年代，**如果在 Survivor 区中相同年龄所有对象大小的总和大于 Survivor 空间的一半**，则年龄大于或等于该年龄的对象可以直接进入老年代，无需等到 MaxTenuringThreshold 中要求的年龄。

**（五）空间分配担保**

在发生 Minor GC 之前，**虚拟机先检查老年代最大可用的连续空间是否大于新生代所有对象总空间**，如果条件成立的话，那么 Minor GC 可以确认是安全的；如果不成立的话虚拟机会查看 HandlePromotionFailure 设置值是否允许担保失败。

如果允许那么就会**继续检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小，如果大于，将尝试着进行一次 Minor GC**，尽管这次 Minor GC 是有风险的；

**如果小于，或者 HandlePromotionFailure 设置不允许冒险，那这时也要改为进行一次 Full GC。**

### 3. Minor GC、Full GC 的触发条件（如何避免 Full GC）

针对如上条件进行避免

https://blog.csdn.net/chenleixing/article/details/46706039/

对于 Minor GC，其触发条件非常简单，**当 Eden 区空间满时，就将触发一次 Minor GC**。而 Full GC 则相对复杂，有以下条件：

**（一）调用 System.gc()**

只是建议虚拟机执行 Full GC，但是虚拟机不一定真正去执行。不建议使用这种方式，而是让虚拟机管理内存。可通过 - XX:+ DisableExplicitGC 来禁止 RMI 调用 System.gc。

**（二）老年代空间不足**

老年代空间不足的常见场景为前文所讲的**大对象直接进入老年代、长期存活的对象进入老年代等。**

**（三）空间分配担保失败（不允许担保时）**

使用复制算法的 Minor GC 需要老年代的内存空间作担保，如果担保失败会执行一次 Full GC。具体内容请参考上面的第五小节。

**（四）统计得到的 Minor GC 晋升到旧生代的平均大小大于老年代的剩余空间（允许担保时）**

在进行 Minor GC 时，做了一个判断，如果之前统计所得到的 Minor GC 晋升到旧生代的平均大小大于旧生代的剩余空间，那么就直接触发 Full GC。  
例如程序第一次触发 Minor GC 后，有 6MB 的对象晋升到旧生代，那么当下一次 Minor GC 发生时，首先检查旧生代的剩余空间是否大于 6MB，如果小于 6MB，则执行 Full GC。

**（五）JDK 1.7 及以前的永久代空间不足**

当系统中要加载的类、反射的类和调用的方法较多时，Permanet Generation 可能会被占满，在**未配置为采用 CMS GC 的情况下也会执行 Full GC**。如果经过 Full GC 仍然回收不了，那么 JVM 会抛出如下错误信息：  
java.lang.OutOfMemoryError: PermGen space  
为避免 Perm Gen 占满造成 Full GC 现象，可采用的方法为**增大 Perm Gen 空间或转为使用 CMS GC**。

**（六）CMS GC 时出现 promotion failed 和 concurrent mode failure**

### GC 精简整理

#### 新生代

*   采取复制算法，在 Minor GC 之前，to survivor 区域保持清空
    
*   对象保存在 Eden 和 from survivor 区，minor GC 运行时，Eden 中的幸存对象会被复制到 to Survivor（同时对象年龄会增加 1）。
    
*   **而 from survivor 区中的幸存对象会考虑对象年龄**，如果年龄没达到阈值，对象依然复制到 to survivor 中。
    
*   如果对象达到阈值那么将被移到老年代。复制阶段完成后，Eden 和 From 幸存区中只保存死对象，可以视为清空。
    
*   **如果在复制过程中 to 幸存区被填满了**，**剩余的对象将被放到老年代**。
    
*   最后，From survivor 和 to survivor **会调换一下名字**，下次 Minor GC 时，To survivor 变为 From Survivor。
    

#### 年老和永久区垃圾收集

当年老代内存不足的话就会触发垃圾收集，这个回收叫做 FULL GC.

**默认是占用了 68% 后收集**，可用参数 - XX:CMSInitiatingOccupancyFraction=68 自行设置。

GC 调优
-----

话题很大，详细请参考：

https://blog.csdn.net/renfufei/article/details/56678064

### GC 调优三个维度

制定明确的 GC 性能指标。对所有性能监控和管理来说, 有三个维度是通用的:

#### Latency(响应时间 / 延迟)

**GC 的延迟指标由一般的延迟需求决定**。延迟指标通常如下所述:

*   所有交易必须在 10 秒内得到响应
    
*   90% 的订单付款操作必须在 3 秒以内处理完成
    
*   推荐商品必须在 100 ms 内展示到用户面前
    

有了正式的需求, 下一步就是检查暂停时间。有许多工具可以使用, 在接下来的 6. GC 调优 (工具篇) 中会进行详细的介绍, 在本节中我们通过查看 GC 日志, 检查一下 GC 暂停的时间。

#### Throughput(吞吐量)

吞吐量和延迟指标有很大区别。当然两者都是根据一般吞吐量需求而得出的。一般吞吐量需求 (Generic requirements for throughput) 类似这样:

*   解决方案每天必须处理 100 万个订单
    
*   解决方案必须支持 1000 个登录用户, 同时在 5-10 秒内执行某个操作: A、B 或 C
    
*   每周对所有客户进行统计, 时间不能超过 6 小时，时间窗口为每周日晚 12 点到次日 6 点之间。
    

和延迟需求类似, GC 调优也需要确定 GC 行为所消耗的总时间。每个系统能接受的时间不同, 一般来说, GC 占用的总时间比不能超过 10%。

#### Capacity(系统容量)

系统容量 (Capacity) 需求, **是在达成吞吐量和延迟指标的情况下, 对硬件环境的额外约束**。这类需求大多是来源于计算资源或者预算方面的原因。例如:

*   系统必须能部署到小于 512 MB 内存的 Android 设备上
    
*   系统必须部署在 Amazon EC2 实例上, 配置不得超过 c3.xlarge(4 核 8GB)。
    
*   每月的 Amazon EC2 账单不得超过 $12,000
    

### 几种 GC 调优工具

#### JMX API

JMX 是获取 JVM 内部运行时状态信息 的标准 API. 可以编写程序代码, 通过 JMX API 来访问本程序所在的 JVM，也可以通过 JMX 客户端执行 (远程) 访问。

最常见的 JMX 客户端是 **JConsole** 和 **JVisualVM** (可以安装各种插件, 十分强大)。两个工具都是标准 JDK 的一部分, 而且很容易使用. 如果使用的是 JDK 7u40 及更高版本, 还可以使用另一个工具: **Java Mission Control(大致翻译为 Java 控制中心, jmc.exe)。**

#### jstat

#### GC 日志 (GC logs)

等等

关注我
===

我是蛮三刀把刀，目前为后台开发工程师。主要关注后台开发，网络安全，Python 爬虫等技术。

来微信和我聊聊：yangzd1102

Github：https://github.com/qqxx6661

### 原创博客主要内容

*   笔试面试复习知识点手册
    
*   Leetcode 算法题解析（前 150 题）
    
*   剑指 offer 算法题解析
    
*   Python 爬虫相关技术分析和实战
    
*   后台开发相关技术分析和实战
    

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

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYylqXTG2tbHGVf4L113tZdUry5djGwNjWHw1JkgFKmfpoakuSDXibcduicBajV9FSt3OVOsra5wu1Q/640?wx_fmt=png)个人公众号：Rude3Knife

**如果文章对你有帮助，不妨收藏起来并转发给您的朋友们~**