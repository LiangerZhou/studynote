> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653192000&idx=1&sn=118cee6d1c67e7b8e4f762af3e61643e&chksm=8c990d9abbee848c739aeaf25893ae4382eca90642f65fc9b8eb76d58d6e7adebe65da03f80d&scene=21#wechat_redirect

上一期我们介绍了 HashMap 的基本原理，没看过的小伙伴们可以点击下面的链接：  

[漫画：什么是 HashMap？](http://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653191907&idx=1&sn=876860c5a9a6710ead5dd8de37403ffc&chksm=8c990c39bbee852f71c9dfc587fd70d10b0eab1cca17123c0a68bf1e16d46d71717712b91509&scene=21#wechat_redirect)  

这一期我们来讲解高并发环境下，HashMap 可能出现的致命问题。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGq6WIag0AtLD9rkIxx4b6hTTQjn7jELbPHdW2DPeBeveVURNms9mOTkdBicj3IU3IwXpMaMO9hiat9A/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGq6WIag0AtLD9rkIxx4b6hT7W4e800gre6dt2lNSyPSo3dKjANxxIl0qe3sLyhRywDm9LtEfXiaD5A/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGq6WIag0AtLD9rkIxx4b6hTknJ0NibtU4qiawHE0cf1SVnjKfEl7L1ZIibenV4v8eKUHyJhzvDrNBefQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGq6WIag0AtLD9rkIxx4b6hTBSbV3Mx9toL1lpuhMXWpp8SW5h95Lia9kvUaNBSRqHqlv9v2ibh604dQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGq6WIag0AtLD9rkIxx4b6hT653dDnAqpy9kgcLgaeAiaABwMHCzEhqYzIUJoo3mCgABDeuic6NLqszw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGq6WIag0AtLD9rkIxx4b6hTeXNo9pciby7bzP827LzBCECCicbmydibhSLxrBQo86Wz7Hmb9mCKRAGbg/0?wx_fmt=jpeg)

HashMap 的容量是有限的。当经过多次元素插入，使得 HashMap 达到一定饱和度时，Key 映射位置发生冲突的几率会逐渐提高。

这时候，HashMap 需要扩展它的长度，也就是进行 **Resize**。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGq6WIag0AtLD9rkIxx4b6hTcdyITy7BLOKwx92UkoDsKiaicHkhDHOO4UfLOOQOwQj1CYN3qPsHQrWw/0?wx_fmt=png)

影响发生 Resize 的因素有两个：

**1.Capacity**

HashMap 的当前长度。上一期曾经说过，HashMap 的长度是 2 的幂。

**2.LoadFactor**

HashMap 负载因子，默认值为 0.75f。

衡量 HashMap 是否进行 Resize 的条件如下：

**HashMap.Size   >=  Capacity * L****oadFactor**  

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGq6WIag0AtLD9rkIxx4b6hTIcb52kGKJwHZSwddr4xe5hibodMN0DdnQKiaxkDibibHiaaT4aBvRicsJhbA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGq6WIag0AtLD9rkIxx4b6hTchr12qEPJN8NePQwW7IQeyEoDHKgaBPZVVKzgNckMLq6e5X9RnBQTQ/0?wx_fmt=jpeg)

**1. 扩容**

创建一个新的 Entry 空数组，长度是原数组的 2 倍。

**2.ReHash**

遍历原 Entry 数组，把所有的 Entry 重新 Hash 到新数组。为什么要重新 Hash 呢？因为长度扩大以后，Hash 的规则也随之改变。

让我们回顾一下 Hash 公式：

index =  HashCode（**Key**） &  （**Length **- 1） 

当原数组长度为 8 时，Hash 运算是和 111B 做与运算；新数组长度为 16，Hash 运算是和 1111B 做与运算。Hash 结果显然不同。  

**Resize 前的 HashMap：**

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUkXq6HjQRm9T7AnUJSPvIcAMvJT4bOx5nFFicF2UNUNqxpicDlc3OdyrA/0?wx_fmt=png)

**Resize 后的 HashMap：**

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUHP4iaclQzbfBRN6HiaM1cgb9NHE8FmicnS9O8OhZIUlmphWCtJI9D8heA/0?wx_fmt=png)

**ReHash 的 Java 代码如下：**

```
/**
 * Transfers all entries from current table to newTable.
 */
void transfer(Entry[] newTable, boolean rehash) {
    int newCapacity = newTable.length;
    for (Entry<K,V> e : table) {
        while(null != e) {
            Entry<K,V> next = e.next;
            if (rehash) {
                e.hash = null == e.key ? 0 : hash(e.key);
            }
            int i = indexFor(e.hash, newCapacity);
            e.next = newTable[i];
            newTable[i] = e;
            e = next;
        }
    }
}

```

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUYtSzzL57RTqko7GKjrm9HHCmUuGbNxPMMibsF5O1zCOwPkMvIpiaFjrg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUXAn07MGO3eL2oj8ehODPMymaC2masb6qOPqgNQmeicyOS0xM4ZSbk3A/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUZyXCJp4dNa9SE0btJ4xhLGK1VkBAFACnXhNQRyQnIsea33FibkhdZPg/0?wx_fmt=jpeg)

**注意：下面的内容十分烧脑，请小伙伴们坐稳扶好。**

假设一个 HashMap 已经到了 Resize 的临界点。此时有两个线程 A 和 B，在同一时刻对 HashMap 进行 Put 操作：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMU2t0XU4TyA5XicIsmVUDzREkp0zVDUJrXu2vaTxWibRMzR5H4SqEu7p0A/0?wx_fmt=png)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUhxYDxjq4m7bohYNNGkovXrEMg7FxQw53zOicDQ4ZlwWOmxRk9oUtgjQ/0?wx_fmt=png)

此时达到 Resize 条件，两个线程各自进行 Rezie 的第一步，也就是扩容：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUWPfWOqDJYr1XnIGshVnDHiaxF6R5uwIyqaQvguUDTVGD8vM904ZKiaVA/0?wx_fmt=png)

这时候，两个线程都走到了 ReHash 的步骤。让我们回顾一下 ReHash 的代码：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUMibL0qESXDy11ciaiafQgNUPib69Hibq65aEXOp6TicmFxdzASuMg0ZPTuzQ/0?wx_fmt=png)

假如此时线程 B 遍历到 Entry3 对象，刚执行完红框里的这行代码，线程就被挂起。对于线程 B 来说：

**e = Entry3**

**next = Entry2**

这时候线程 A 畅通无阻地进行着 Rehash，当 ReHash 完成后，结果如下（图中的 e 和 next，代表线程 B 的两个引用）：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUX6mkOibHI08IeDMrpwIkzze064vN3QSkzM4NiaB97oU4QjCr72dB82WQ/0?wx_fmt=png)

直到这一步，看起来没什么毛病。接下来线程 B 恢复，继续执行属于它自己的 ReHash。线程 B 刚才的状态是：

**e = Entry3**

**next = Entry2**

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMURm5txVYto7KeMEqBCFMFnrvMWUKsGJ9iaYdtubnYVQLLicKH7pXEwgfQ/0?wx_fmt=png)

当执行到上面这一行时，显然 i = 3，因为刚才线程 A 对于 Entry3 的 hash 结果也是 3。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUmlYiaesq6RXwHgUe4OlHgdfkNTWkQjdBDBf7YCkoI2b2HNdiayT3ho5A/0?wx_fmt=png)

我们继续执行到这两行，Entry3 放入了线程 B 的数组下标为 3 的位置，并且 **e 指向了 Entry2**。此时 e 和 next 的指向如下：

**e = Entry2**

**next = Entry2**

整体情况如图所示：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUbEswmkI5IB9pWN1icQicgc9OI43DDFDpfJYNyBoSAoSWhTd8wXgo672A/0?wx_fmt=png)

接着是新一轮循环，又执行到红框内的代码行：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUMibL0qESXDy11ciaiafQgNUPib69Hibq65aEXOp6TicmFxdzASuMg0ZPTuzQ/0?wx_fmt=png)

**e = Entry2**

**next = Entry3**

整体情况如图所示：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUa9lFFgXjyXyjjCaqLr1flwSfhibGxHTmicqF3tAbP8mcV3AkESNfGUmA/0?wx_fmt=png)

接下来执行下面的三行，用头插法把 Entry2 插入到了线程 B 的数组的头结点：  

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMU8LrBibB3knlnicvzdZiaZBVBQ46JR6RcpoAZcWcFhSian7OYnyvPkKtiafg/0?wx_fmt=png)

整体情况如图所示：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUKUia9GibVO9yGymsvgyGau4u3RBCyyLNJiaPodqXic9HQTQ3OWfeRW9ElA/0?wx_fmt=png)

第三次循环开始，又执行到红框的代码：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUMibL0qESXDy11ciaiafQgNUPib69Hibq65aEXOp6TicmFxdzASuMg0ZPTuzQ/0?wx_fmt=png)

**e = Entry3**

**next = Entry3.next = null**

最后一步，当我们执行下面这一行的时候，见证奇迹的时刻来临了：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUFVwF96zYkrqqctnicPEhNsJfChKFib2KiaLLxCpLTueCzKlSnUic9jXhGg/0?wx_fmt=png)

**newTable[i] =** Entry2****

**e = Entry3**

******Entry2.next = Entry3******

**Entry3.next = Entry2**

**链表出现了环形！**

整体情况如图所示：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUngRBAIib2JtvLxZmJ1RczUPBUyzhj29NQYTExc08dofojLdwVkYYQPA/0?wx_fmt=png)

此时，问题还没有直接产生。当调用 Get 查找一个不存在的 Key，而这个 Key 的 Hash 结果恰好等于 3 的时候，由于位置 3 带有环形链表，所以程序将会进入**死循环**！

这种情况，不禁让人联想到一道经典的面试题：

[漫画算法：如何判断链表有环？](http://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653189798&idx=1&sn=c35c259d0a4a26a2ee6205ad90d0b2e1&chksm=8c99047cbbee8d6a452fbb171133551553a825c83fb8b0cc66210dcda842c61157a07baaeb6b&scene=21#wechat_redirect)  

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUBac5jppEHIvMtAstEdQWibjBRiaQ03jhiacP4EF4sHemPBMVn6KDkU5LQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUhqrw7MUwUsjL4J7AKmG2mMazYcpjXOeJW4K5icZ0COBJ3xdiaArd37Zw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUsKeUc80Yzl2vav6DmwLf6G71af1gGZVIZ8D7r2F7wUG2wicKZUEvmdA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUFSj6giboBgVia8pGYKiaGWvfTLhyfkmibMl2mPvIGG2YnOdQASNYF2JWXQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGplAChQkeJdasPqCPaoiaFMUkL0tqUtblia2BeaWKNmHMkHBEx1BCkkTR8QraSGw7BkG23dTCNWbbvg/0?wx_fmt=jpeg)

**1.Hashmap 在插入元素过多的时候需要进行 Resize，Resize 的条件是**

****HashMap.Size   >=  Capacity * L****oadFactor。****

****2.**Hashmap 的** Resize 包含扩容和 ReHash 两个步骤，ReHash 在并发的情况下可能会形成链表环。****

—————END—————
