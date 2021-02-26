> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653192736&idx=1&sn=24d4054b062e28db9e54c735aafe2407&chksm=8c99f0fabbee79ecfd9198aa89bc78084e9b7db056078982975d8910c12b5d3dd1d16c2509c3&scene=21#wechat_redirect

点击上方 “程序员小灰”，选择 “置顶公众号”

有趣有内涵的文章第一时间送达！

上一期为大家讲解的 CAS 机制的基本概念，没看过的小伙伴们可以点击下面的链接：  

[漫画：什么是 CAS 机制？](http://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653192625&idx=1&sn=cbabbd806e4874e8793332724ca9d454&chksm=8c99f36bbbee7a7d169581dedbe09658d0b0edb62d2cbc9ba4c40f706cb678c7d8c768afb666&scene=21#wechat_redirect)  

这一期我们来深入介绍之前遗留的两个问题：

1.  **Java 当中 CAS 的底层实现**
    
2.  **CAS 的 ABA 问题和解决方法**
    

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9Cg5vrD4Uibgglx2XX8jAsnG7twUo3VGnNfPHaPnYXOgEbXGHraPKLyw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9icpZ5nvlPc5XM0kWJca2I7WOxdSiaXrIicVbvFdkck7a4cXiaq6T7kwr7w/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9wqfy6vr1AuMUjqAnouXcbicpNdHcAU2sW4bwibAic5EQhrb2KSR0UNvYw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9UmlmZTfWX3jkARWG81kA25j0ia4mhy7aOnQQ9NejlNoap2rF0Qmib6AA/0?wx_fmt=jpeg)

首先看一看 AtomicInteger 当中常用的自增方法 **incrementAndGet：**

```
public final int incrementAndGet() {
    for (;;) {
        int current = get();
        int next = current + 1;
        if (compareAndSet(current, next))
            return next;
    }
}


```

```
private volatile int value;

```

```
public final int get() {
    return value;
}

这段代码是一个无限循环，也就是CAS的自旋。循环体当中做了三件事：
1.获取当前值。
2.当前值+1，计算出目标值。
3.进行CAS操作，如果成功则跳出循环，如果失败则重复上述步骤。

```

这里需要注意的重点是 get 方法，这个方法的作用是获取变量的当前值。

如何保证获得的当前值是内存中的最新值呢？很简单，用 **volatile** 关键字来保证。有关 volatile 关键字的知识，我们之前有介绍过，这里就不详细阐述了。  

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao943ibVPoH3C0ACYnhg3yj5De07dn9GtLqPfnkf2fOoBra6obS1fJcpyQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9q6IhRhVK0fL2wt5marGsQOblrwsvEZM8vbyos3YglU2xzyWETvT2MQ/0?wx_fmt=jpeg)

```
接下来看一看compareAndSet方法的实现，以及方法所依赖对象的来历：

```

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao93wFspiarcfLJ4S8ePEricsPGfRaX0YPUEqPPicLyh9TodUmdjOjMw4AvQ/0?wx_fmt=png)

compareAndSet 方法的实现很简单，只有一行代码。这里涉及到两个重要的对象，一个是 **unsafe**，一个是 **valueOffset**。

什么是 unsafe 呢？Java 语言不像 C，C++ 那样可以直接访问底层操作系统，但是 JVM 为我们提供了一个后门，这个后门就是 unsafe。unsafe 为我们提供了**硬件级别的原子操作**。

至于 valueOffset 对象，是通过 unsafe.objectFieldOffset 方法得到，所代表的是 **AtomicInteger 对象 value 成员变量在内存中的偏移量**。我们可以简单地把 valueOffset 理解为 value 变量的内存地址。

我们在上一期说过，CAS 机制当中使用了 3 个基本操作数：**内存地址 V，旧的预期值 A，要修改的新值 B**。

而 unsafe 的 compareAndSwapInt 方法参数包括了这三个基本元素：valueOffset 参数代表了 V，expect 参数代表了 A，update 参数代表了 B。

正是 unsafe 的 compareAndSwapInt 方法保证了 Compare 和 Swap 操作之间的原子性操作。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9SprMxU23ux41BBmpMGgkCWBhmlscgoaaFFjlbaDDW9RuibAE0icDOlZg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9zAWIVrhvu1dcU6bQrxVo36r7g78Mh18ueJyT6WNo5QwH55b2TFeJww/0?wx_fmt=jpeg)

什么是 ABA 呢？假设内存中有一个值为 A 的变量，存储在地址 V 当中。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao94GicKoQDYsoBF6YBibSbbGZeYtl8I7HEfDYWsKL03jiajskeM6mKv3kkw/0?wx_fmt=png)

此时有三个线程想使用 CAS 的方式更新这个变量值，每个线程的执行时间有略微的偏差。线程 1 和线程 2 已经获得当前值，线程 3 还未获得当前值。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9K6T5eHYNzYTALBrKo3sl3IddSsF4ESdKLvGBN0YIl1z8iaYUpN9Bg6Q/0?wx_fmt=png)

接下来，线程 1 先一步执行成功，把当前值成功从 A 更新为 B；同时线程 2 因为某种原因被阻塞住，没有做更新操作；线程 3 在线程 1 更新之后，获得了当前值 B。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9fzZzfFS1QCqKJdHKeibtEJkrstnia2VNUYbHx1k0gQak1UDtj5E1rI9w/0?wx_fmt=png)

再之后，线程 2 仍然处于阻塞状态，线程 3 继续执行，成功把当前值从 B 更新成了 A。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9yhAYp0850BgwYk59z8oD6zWOianOuHe0mbYFZ51dJEwhgdBic15I6m4w/0?wx_fmt=png)

最后，线程 2 终于恢复了运行状态，由于阻塞之前已经获得了 “当前值”A，并且经过 compare 检测，内存地址 V 中的实际值也是 A，所以成功把变量值 A 更新成了 B。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9etOR4Dze8OEs07wVGyRnVcSKgdng5LBhvddbYw7F37g9jPpt22YR6Q/0?wx_fmt=png)

这个过程中，线程 2 获取到的变量值 A 是一个旧值，尽管和当前的实际值相同，但内存地址 V 中的变量已经经历了 A->B->A 的改变。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9HGwsicasqOEucwxETTWBbgbn28WOKSdcSrElVdkJGjO8nOXIiaa6LSYg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9iaMEAibJvsN4K4hDQjr648XZleS3rZETwh44TsqlPic1IM7ibDcduaH6sw/0?wx_fmt=jpeg)

当我们举一个提款机的例子。假设有一个遵循 CAS 原理的提款机，小灰有 100 元存款，要用这个提款机来提款 50 元。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9fiaZte09biaGwHYgrggjVj89nI2ibkM8wP3icthsIWua5Je6P9wUnU9osA/0?wx_fmt=png)  

由于提款机硬件出了点小问题，小灰的提款操作被同时提交两次，开启了两个线程，两个线程都是获取当前值 100 元，要更新成 50 元。

理想情况下，应该一个线程更新成功，另一个线程更新失败，小灰的存款只被扣一次。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao905ibmQj2WZPtxiaica2Bwwj3rACxOwBR0MkrV9N8vcfGRCW06nogNvOeA/0?wx_fmt=png)

线程 1 首先执行成功，把余额从 100 改成 50。线程 2 因为某种原因阻塞了。**这时候，小灰的妈妈刚好给小灰汇款 50 元**。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9eeRfBhqgCEO0vT4P52daic6JeKdaaUA9EATFGza8Lhe84lBUJ2teGicA/0?wx_fmt=png)

线程 2 仍然是阻塞状态，线程 3 执行成功，把余额从 50 改成 100。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9slfzwbriaSvPe7cyRNgsMqBrfl72IqKNvKWRXQajVJNYPt1MZ1qSkXg/0?wx_fmt=png)

线程 2 恢复运行，由于阻塞之前已经获得了 “当前值”100，并且经过 compare 检测，此时存款实际值也是 100，所以成功把变量值 100 更新成了 50。  

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9E94ibQia1TjnvRd3k5hJUoCtIbw123FMXREUia1eZ6Paqq6ib7uS6hXPxQ/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9MgT91KOWHQrZpH7EyX42XC90YTC6m6rsVcFicFxssOXktRKoNAgyxGg/0?wx_fmt=jpeg)

这个举例改编自《java 特种兵》当中的一段例子。原本线程 2 应当提交失败，小灰的正确余额应该保持为 100 元，结果由于 ABA 问题提交成功了。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9xmsibiaIJuFma5Siapk8AaKian0wVMoMxJEFc3PwqdNo3GLhTicDZpFSWVA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9CGyRPq3bUNUBENKiapROc4AmThkru12pDr1RgNOAmicECz1nAkiaeG2Fg/0?wx_fmt=jpeg)

什么意思呢？真正要做到严谨的 CAS 机制，我们在 Compare 阶段不仅要比较期望值 A 和地址 V 中的实际值，还要比较变量的版本号是否一致。

我们仍然以最初的例子来说明一下，假设地址 V 中存储着变量值 A，当前版本号是 01。线程 1 获得了当前值 A 和版本号 01，想要更新为 B，但是被阻塞了。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9QSMicEwCKbPjM8qxGjMBNWhib7dickjBzOor0jxrACR7lxFbCGicCNa2icQ/0?wx_fmt=png)

这时候，内存地址 V 中的变量发生了多次改变，版本号提升为 03，但是变量值仍然是 A。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao98dl51JCoovprVgPbqqTnmf5iaouDuoOF0oK3c5rMlSxB9jf8ibpkOJ5A/0?wx_fmt=png)

随后线程 1 恢复运行，进行 Compare 操作。经过比较，线程 1 所获得的值和地址 V 的实际值都是 A，但是版本号不相等，所以这一次更新失败。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9jZn3EO5pviarZLKg9quqzATnj9slUCQ1Wyn0X25XdSY4wq2Tbr5r2wA/0?wx_fmt=png)

在 Java 当中，**AtomicStampedReference** 类就实现了用版本号做比较的 CAS 机制。  

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao98b2TbeHPsEjAuUiaLgmlxh027e3EibVtFE4TTdZuLMXU6NLJkmKtlPLA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrmUJCBnhEZeJKpYUYECao9WqicT2CorwERrrOMjnRaLsMg4bsHiaQD1fD6zmB9Qo2UXkI2UxQz8UPg/0?wx_fmt=jpeg)

**1. Java 语言 CAS 底层如何实现？**

利用 unsafe 提供了原子性操作方法。

**2. 什么是 ABA 问题？怎么解决？**

当一个值从 A 更新成 B，又更新会 A，普通 CAS 机制会误判通过检测。

利用版本号比较可以有效解决 ABA 问题。

—————END—————

喜欢本文的朋友们，欢迎长按下图关注订阅号**程序员小灰**，收看更多精彩内容

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoBj18gILw2hefgpNaCia1eRhNCzRx29e1DpVhicyenCic4RQibDTbzySoqqpOrmBxu7KlLZM73YDDPJg/640?wx_fmt=jpeg)