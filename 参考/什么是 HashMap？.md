> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653191907&idx=1&sn=876860c5a9a6710ead5dd8de37403ffc&chksm=8c990c39bbee852f71c9dfc587fd70d10b0eab1cca17123c0a68bf1e16d46d71717712b91509&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrEuiawAiaywBA7HecrWicr59BLv09zN29R71DQrtOxIvzVAWiaVzM70HdGxEvJYZiauxu60OZDaeHPInw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrEuiawAiaywBA7HecrWicr59BEia7lduHtWcaf16ZHYhQqUeia7zNOZjgCbHYYibvG9iaSib7TO9cGmEWhjg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrEuiawAiaywBA7HecrWicr59BEicNJsaM4wGiauNEvojsqZYJDxXxFiaJcumFAEmfbZPzD05pAiaJcryPtQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrEuiawAiaywBA7HecrWicr59B3pOymnSAc3Jvr21ibM6XndN0mbAKnz6A07fghmiaRSgqZTLic2qzyXGUw/0?wx_fmt=jpeg)

————————————

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqqUO6KYNSJy5OOBA9TZjKt0RC8QnoOahl9MSGG01mQk35laaRwmIT4A8IWHnmFFy7vKNicXoJDGibA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqqUO6KYNSJy5OOBA9TZjKtSKFImWLnRGUNI1Ct4FRoC8ZsX0wflMBqEjJqFdof43317OiaGicydjkA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrEuiawAiaywBA7HecrWicr59BxyfO1KEQ6toqFjc1aXzcyYCxRM3hD7vKn2nBS8r4zUVCk7W8ibvt6aA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrEuiawAiaywBA7HecrWicr59B6ibnn7icfY5lZibfzXa7EUeZIDAAdVIXjQXicicTKnFRFdz41T7C8gAia7kQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrEuiawAiaywBA7HecrWicr59BDVBB2FsNMTKtfqQRIIKweVw3N8akB7CzRBPvR5XNxRwFjTmlbuJRUw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrEuiawAiaywBA7HecrWicr59BAGt3ZSHct37bUVBMSdpEb2S7zVyxxoWRWO9z8ud10UOB9xEpJ4acog/0?wx_fmt=jpeg)

众所周知，HashMap 是一个用于存储 Key-Value 键值对的集合，每一个键值对也叫做 **Entry**。这些个键值对（Entry）分散存储在一个数组当中，这个数组就是 HashMap 的主干。

HashMap 数组每一个元素的初始值都是 Null。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrEuiawAiaywBA7HecrWicr59BcnqexdaBJWTRg49BUllBvVYfOC2Swkhe8PIKGa7gGpWYibxhCG5g1hQ/0?wx_fmt=png)

对于 HashMap，我们最常使用的是两个方法：**Get** 和 **Put**。

**1.Put 方法的原理**

调用 Put 方法的时候发生了什么呢？

比如调用 hashMap.put("apple", 0) ，插入一个 Key 为 “apple" 的元素。这时候我们需要利用一个哈希函数来确定 Entry 的插入位置（index）：

**index =  Hash（“apple”）**

假定最后计算出的 index 是 2，那么结果如下：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2jxYh5D3F0avyBppia3BKHO3EU1HqbdThUMS3H9ejwb7ibu4bUiaOnsXqQ/0?wx_fmt=png)

但是，因为 HashMap 的长度是有限的，当插入的 Entry 越来越多时，再完美的 Hash 函数也难免会出现 index 冲突的情况。比如下面这样：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2q93ezXuibE4fQjQpnDMTVlQgSFJmfsYypdxVibAcQeeLsHUCUd4m7tlg/0?wx_fmt=png)

这时候该怎么办呢？我们可以利用**链表**来解决。

HashMap 数组的每一个元素不止是一个 Entry 对象，也是一个链表的头节点。每一个 Entry 对象通过 Next 指针指向它的下一个 Entry 节点。当新来的 Entry 映射到冲突的数组位置时，只需要插入到对应的链表即可：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm29aBmKLDfHETNia2Lzpuia9tm9IDX5XXue0nGoSgFpUGT0crAS45zICQQ/0?wx_fmt=png)

需要注意的是，新来的 Entry 节点插入链表时，使用的是 “头插法”。至于为什么不插入链表尾部，后面会有解释。

2.Get 方法的原理

使用 Get 方法根据 Key 来查找 Value 的时候，发生了什么呢？

首先会把输入的 Key 做一次 Hash 映射，得到对应的 index：

index =  Hash（“apple”）

由于刚才所说的 Hash 冲突，同一个位置有可能匹配到多个 Entry，这时候就需要顺着对应链表的头节点，一个一个向下来查找。假设我们要查找的 Key 是 “apple”：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2ibC2cqrGyDNL5ms64Wqia4POibCU97PUNZiaSe2ectdgrBsgkGzKEnFOJw/0?wx_fmt=png)

第一步，我们查看的是头节点 Entry6，Entry6 的 Key 是 banana，显然不是我们要找的结果。

第二步，我们查看的是 Next 节点 Entry1，Entry1 的 Key 是 apple，正是我们要找的结果。

之所以把 Entry6 放在头节点，是因为 HashMap 的发明者认为，**后插入的 Entry 被查找的可能性更大**。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2CbhVDcDrRueK3DPdouHZ4DS7kAMu5S670iaIyqvwJxPWX9hInMly0IA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2LdeclTgJO0iceUmBXJfnyruWJqgZxqXXNaRAEbU1YXQ0tZiaAibM7kpuQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2MIicE18BusiaQ71BFuEh1YiaG7SFXDFxgEoSpfRia70FHaq3MU99ZteP0Q/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2ial1Nlu9LvrJlcyIgsGKqOMib1DhB6hKNvKPicYmnBsCAJeQdCibBADJWQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2DKkI3TwcaWrJ8uV0SmMkl5uEzr7tjWwzMkkeWDXhzRBU0ClIqUmXpw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2ial1Nlu9LvrJlcyIgsGKqOMib1DhB6hKNvKPicYmnBsCAJeQdCibBADJWQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2XibTUFockwC0vsmEDjclv78nY6RRLu1zPmrHricicB96gDvibeI9nttyew/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2ial1Nlu9LvrJlcyIgsGKqOMib1DhB6hKNvKPicYmnBsCAJeQdCibBADJWQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2iaWyialPsp9zYOATdE4LQFDZbe8CKXcEXDjXBaGDzWDf1gcGpQ3X6QWA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2Q5CTxcxmEypgk8okzrPtu6nFyEFhD2lVus0vnFCUH8XnyRZuuT1Ypg/0?wx_fmt=jpeg)

————————————

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2A4A6WZSfbP1ibtZkVxhHPp8hP5YUfqdZzj4doicOSWQDbWaPsdhnPuhw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2lTnLAfeImsqjibPSX1qH6xzwHJnTjwL8vVU6nfCW3DJFCB5ibf8DtW8Q/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2T4ycWIsQ5UP5gcbYkB3e6Ecnp18Q6cmcfCpO0q1oTL2iaWndp2gJ3Lg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2QGjssOAR3yHYQbfcEqR6EAvwSt1YsYVIpiafDOqZ6xW3Hfq7PTicf0fg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2y2qatibxzWNmUBGSoHcEH9k8GibtOQibfnKP53M8TmB0RNJyMbv9GeV0Q/0?wx_fmt=jpeg)

之前说过，从 Key 映射到 HashMap 数组的对应位置，会用到一个 Hash 函数：

**index =  Hash（“apple”）**

如何实现一个尽量均匀分布的 Hash 函数呢？我们通过利用 Key 的 HashCode 值来做某种运算。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2G40K3Fe6uPae3ALBSLa1KBea1W8z0CKtDSJrDlq6nCorCqC8AUTEAQ/0?wx_fmt=jpeg)

**index =  HashCode（**Key**） % Length ?**

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2H5wBeibohUbV9QLryqy5yqKestmYeStqvIrYFTS9nbwKTR9FXgGGtuA/0?wx_fmt=jpeg)

如何进行位运算呢？有如下的公式（Length 是 HashMap 的长度）：

**index =  HashCode（**Key**） &  （**Length **- 1） **

下面我们以值为 “book” 的 Key 来演示整个过程：

1. 计算 book 的 hashcode，结果为十进制的 3029737，二进制的 101110001110101110 1001。

2. 假定 HashMap 长度是默认的 16，计算 Length-1 的结果为十进制的 15，二进制的 1111。

3. 把以上两个结果做**与运算**，101110001110101110 1001 & 1111 = 1001，十进制是 9，所以 index=9。

可以说，Hash 算法最终得到的 index 结果，完全取决于 Key 的 Hashcode 值的最后几位。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm20ZL5eRA8Ccib9sib2dQTm9QF65Ns0qZ1tU0NV1YUwEt6Da00gHsiaK8aw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2aCXAbxfcCMh3uTGib5OiaPX7zicdAgno4Hkpzjg5f69bk4Pe7qyyeKtsw/0?wx_fmt=jpeg)

假设 HashMap 的长度是 10，重复刚才的运算步骤：  

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm227KadcVO2ibiazRxxfp8HGrEpmvbVljnC3nO3iage9Tv4KMQLKroXxDiaA/0?wx_fmt=png)

单独看这个结果，表面上并没有问题。我们再来尝试一个新的 HashCode  101110001110101110 **1011** ：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2zBoG2JPLmRReaoxuCglHllvcWdnSyeFU888xyVtOyMSFgdcxynHHpg/0?wx_fmt=png)

让我们再换一个 HashCode 101110001110101110 **1111 **试试  ：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2HsZ2y78wmAMQ36W6VQevTrx8jppNpj5esxCDqmCy5jPT6V1Ya94KjQ/0?wx_fmt=png)

是的，虽然 HashCode 的倒数第二第三位从 0 变成了 1，但是运算的结果都是 1001。也就是说，当 HashMap 长度为 10 的时候，有些 index 结果的出现几率会更大，而有些 index 结果永远不会出现（比如 0111）！

这样，显然不符合 Hash 算法均匀分布的原则。

反观长度 16 或者其他 2 的幂，Length-1 的值是所有二进制位全为 1，这种情况下，index 的结果等同于 HashCode 后几位的值。只要输入的 HashCode 本身分布均匀，Hash 算法的结果就是均匀的。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2FqQicXzQPo8oW5FIY3CcX5unAAR5GPpNPkibNNfVCX9L6qIArAL2KpdA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoXt6UNkvyibQ8ufeF48pvm2ZicHCia0v40CMKcqxXAQGkwWppJLZYEv6SRSwoxx8aVibHbaCa3MIXicBA/0?wx_fmt=jpeg)

—————END—————
