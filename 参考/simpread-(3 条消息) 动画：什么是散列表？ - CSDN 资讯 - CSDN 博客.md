> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://csdnnews.blog.csdn.net/article/details/88637952

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_gif/Pn4Sm0RsAug5zOzy32A3RIVhRwowK5ogg1hJ631uGyu9zOMKfTddDnSrsxicbCQNm59Qeo3lDYCvF70I9ibGvA9g/640?wx_fmt=gif)

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_jpg/Pn4Sm0RsAuhP0W8cvXIj7XxmpmfMYLYnaC5U7Le7lWClty0TIN4zy31UnmVjxHXiadicibWKic3C3Rq7K60yz3OTUQ/640?wx_fmt=jpeg)  

作者 | 程序员小吴

责编 | 郭芮

散列表（Hash table，也叫哈希表），是根据键（Key）而直接访问在内存存储位置的数据结构。也就是说，它通过计算一个关于键值的函数，将所需查询的数据映射到表中一个位置来访问记录，这加快了查找速度。这个映射函数称做散列函数，存放记录的数组称做散列表。

**![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_png/Pn4Sm0RsAuhSvZMAt2zKcxGQN3l1NV4LXSAriayI15u06ibNNlXzIcor2tTtgJBKFxkIicJ8tiaRKRaictbrQEssdSg/640?wx_fmt=png)**

**散列函数**

散列函数，顾名思义，它是一个函数。如果把它定义成 hash(key) ，其中 key 表示元素的键值，则 hash(key) 的值表示经过散列函数计算得到的散列值。

**散列函数的特点：**

1、确定性。如果两个散列值是不相同的（根据同一函数），那么这两个散列值的原始输入也是不相同的。

2、散列碰撞（collision）。散列函数的输入和输出不是唯一对应关系的，如果两个散列值相同，两个输入值很可能是相同的，但也可能不同。

3、不可逆性。一个哈希值对应无数个明文，理论上你并不知道哪个是。

> “船长，如果一样东西你知道在哪里，还算不算丢了。”
> 
> “不算。”
> 
> “好的，那您的酒壶没有丢。”

4、混淆特性。输入一些数据计算出散列值，然后部分改变输入值，一个具有强混淆特性的散列函数会产生一个完全不同的散列值。

**![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_png/Pn4Sm0RsAuhSvZMAt2zKcxGQN3l1NV4LwYnW1VvkaHWiaL6W1Mr1yiaNLQpxwhyqice9F1yJzMHticssPX515qyvog/640?wx_fmt=png)**

**常见的散列函数**

**1. MD5**

MD5 即 Message-Digest Algorithm 5（信息 - 摘要算法 5），用于确保信息传输完整一致。是计算机广泛使用的杂凑算法之一，主流编程语言普遍已有 MD5 实现。

将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5 的前身有 MD2 、MD3 和 MD4 。

MD5 是输入不定长度信息，输出固定长度 128-bits 的算法。经过程序流程，生成四个 32 位数据，最后联合起来成为一个 128-bits 散列。

基本方式为，求余、取余、调整长度、与链接变量进行循环运算，得出结果。

MD5 计算广泛应用于错误检查。在一些 BitTorrent 下载中，软件通过计算 MD5 来检验下载到的碎片的完整性。

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_gif/D67peceibeISZg79Zp1WzclUJUjcLKFusLHsGlvmDOLpPibhNo5Ygk2ic3EPG6IuKMCIAFibzAlE6kB1lD2JGaPgeg/640?wx_fmt=gif)

MD5 校验

**2. SHA-1**

SHA-1（Secure Hash Algorithm 1，中文名：安全散列算法 1）是一种密码散列函数，SHA-1 可以生成一个被称为消息摘要的 160 位（20 字节）散列值，散列值通常的呈现形式为 40 个十六进制数。

SHA-1 曾经在许多安全协议中广为使用，包括 TLS 和 SSL、PGP、SSH、S/MIME 和 IPsec，曾被视为是 MD5 的后继者。

**![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_png/Pn4Sm0RsAuhSvZMAt2zKcxGQN3l1NV4Lb4ybNEVGnaAvEDwENKzW27LUKFDGZPKcBneWwTaTpaJyG2C3em7libQ/640?wx_fmt=png)**

**散列冲突**

理想中的一个散列函数，希望达到：

> 如果 key1 ≠ key2，那 hash(key1) ≠ hash(key2)。

这种效果，然而在真实的情况下，要想找到一个不同的 key 对应的散列值都不一样的散列函数，几乎是不可能的，即使是 MD5 或者 由美国国家安全局设计的 SHA-1 算法也无法实现。

**事实上，再好的散列函数都无法避免散列冲突。**为什么呢？这涉及到数学中比较好理解的一个原理：抽屉原理。

抽屉原理：桌上有十个苹果，要把这十个苹果放到九个抽屉里，无论怎样放，我们会发现至少会有一个抽屉里面至少放两个苹果。这一现象就是我们所说的 “抽屉原理”。

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_gif/D67peceibeISZg79Zp1WzclUJUjcLKFusEHWLSDFYxapBJ4k4KvNoPKw7MGvibrPrLXGbkuL87HWk6IWibZNKAd9g/640?wx_fmt=gif)

抽屉原理

对于散列表而言，无论设置的存储区域（n）有多大，当需要存储的数据大于 n 时，那么必然会存在哈希值相同的情况。这就是所谓的散列冲突。

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_gif/D67peceibeISZg79Zp1WzclUJUjcLKFusFwuoIj4iblVS9ud4dMzME0tvfzpCwgGKn9aHBShmgpEnajbvibe0kT6A/640?wx_fmt=gif)

散列冲突

那应该如何解决散列冲突问题呢？常用的散列冲突解决方法有两类，开放寻址法（open addressing）和链表法（chaining）。

**开放寻址法**

定义：将散列函数扩展定义成探查序列，即每个关键字有一个探查序列 h(k,0)、h(k,1)、…、h(k,m-1)，这个探查序列一定是 0….m-1 的一个排列（一定要包含散列表全部的下标，不然可能会发生虽然散列表没满，但是元素不能插入的情况），如果给定一个关键字 k，首先会看 h(k,0) 是否为空，如果为空，则插入；如果不为空，则看 h(k,1) 是否为空，以此类推。

开放寻址法是一种解决碰撞的方法，对于开放寻址冲突解决方法，比较经典的有线性探测方法（Linear Probing）、二次探测（Quadratic probing）和 双重散列（Double hashing）等方法。

**线性探测方法**

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_gif/D67peceibeISZg79Zp1WzclUJUjcLKFusRgxUzvsicUbZrJG1yeDHribO1W15B5FJEibicxN8LdOpkwEZS438ev6uRg/640?wx_fmt=gif)

开放寻址法之线性探测方法

当我们往散列表中插入数据时，如果某个数据经过散列函数散列之后，存储位置已经被占用了，我们就从当前位置开始，依次往后查找，看是否有空闲位置，直到找到为止。

以上图为例，散列表的大小为 8 ，黄色区域表示空闲位置，橙色区域表示已经存储了数据。目前散列表中已经存储了 4 个元素。此时元素 7777777  经过 Hash 算法之后，被散列到位置下标为 7 的位置，但是这个位置已经有数据了，所以就产生了冲突。

于是按顺序地往后一个一个找，看有没有空闲的位置，此时，运气很好正巧在下一个位置就有空闲位置，将其插入，完成了数据存储。

线性探测法一个很大的弊端就是当散列表中插入的数据越来越多时，散列冲突发生的可能性就会越来越大，空闲位置会越来越少，线性探测的时间就会越来越久。极端情况下，需要从头到尾探测整个散列表，所以最坏情况下的时间复杂度为 O(n)。

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_gif/D67peceibeISZg79Zp1WzclUJUjcLKFusUflr2nzy94V1zoKnyZNicbTy2cRQmwosverW83VAI3fID0ibicuIDMzUA/640?wx_fmt=gif)

开放寻址法之线性探测方法的弊端

**二次探测方法**

二次探测是二次方探测法的简称。顾名思义，使用二次探测进行探测的步长变成了原来的 “二次方”，也就是说，它探测的下标序列为 hash(key)+0，hash(key)+1^2 或 [hash(key)-1^2]，hash(key)+2^2 或 [hash(key)-2^2]。

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_gif/D67peceibeISZg79Zp1WzclUJUjcLKFusjTV2CIiaNZ9oGcz3cSVFuDibEkrsPNH46Ze8zXyibyX44x9JekeTlmYFw/640?wx_fmt=gif)

二次探测方法

以上图为例，散列表的大小为 8 ，黄色区域表示空闲位置，橙色区域表示已经存储了数据。目前散列表中已经存储了 7 个元素。此时元素 7777777  经过 Hash 算法之后，被散列到位置下标为 7 的位置，但是这个位置已经有数据了，所以就产生了冲突。

按照二次探测方法的操作，有冲突就先 + 1^2，8 这个位置有值，冲突；变为 - 1^2，6 这个位置有值，还是有冲突；于是 - 2^2， 3 这个位置是空闲的，插入。

**双重散列方法**

所谓双重散列，意思就是不仅要使用一个散列函数，而是使用一组散列函数 hash1(key)，hash2(key)，hash3(key)。。。。。。先用第一个散列函数，如果计算得到的存储位置已经被占用，再用第二个散列函数，依次类推，直到找到空闲的存储位置。

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_gif/D67peceibeISZg79Zp1WzclUJUjcLKFusoKZb0rprfKu4UicAUbcHZRHjDvheXjWibqv8sF6QHz46fNbKc4Dwl7WQ/640?wx_fmt=gif)

双重散列方法

以上图为例，散列表的大小为 8 ，黄色区域表示空闲位置，橙色区域表示已经存储了数据。目前散列表中已经存储了 7 个元素。此时元素 7777777  经过 Hash 算法之后，被散列到位置下标为 7 的位置，但是这个位置已经有数据了，所以就产生了冲突。

此时，再将数据进行一次哈希算法处理，经过另外的 Hash 算法之后，被散列到位置下标为 3 的位置，完成操作。

事实上，不管采用哪种探测方法，只要当散列表中空闲位置不多的时候，散列冲突的概率就会大大提高。为了尽可能保证散列表的操作效率，一般情况下，需要尽可能保证散列表中有一定比例的空闲槽位。

一般使用加载因子（load factor）来表示空位的多少。

加载因子是表示 Hsah 表中元素的填满的程度，若加载因子越大，则填满的元素越多, 这样的好处是：空间利用率高了, 但冲突的机会加大了。反之, 加载因子越小, 填满的元素越少, 好处是冲突的机会减小了，但空间浪费多了。

**链表法**

链表法是一种更加常用的散列冲突解决办法，相比开放寻址法，它要简单很多。如下动图所示，在散列表中，每个位置对应一条链表，所有散列值相同的元素都放到相同位置对应的链表中。

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_gif/D67peceibeISZg79Zp1WzclUJUjcLKFusNtvzBbQphYqtql3d6S9n7U3RWnUWvDqcCvclwW1UeD7ElU1zIPQzOw/640?wx_fmt=gif)

链表法

> 作者：程序员小吴，哈工大学渣，目前正在学算法，开源项目 「 LeetCodeAnimation 」5500star，GitHub Trending 榜连续一月第一。运营个人微信号五分钟学算法，一起学习，一起进步！
> 
> 声明：本文为作者投稿，版权归其个人所有。

* * *

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_jpg/Pn4Sm0RsAuha00icpm13kvibNc1ebfcQjpgySe2fkicMtiaqgjmTjGspENfMVYJx6URDTibSr3uAEQ867w9ajGwicwag/640?wx_fmt=jpeg)

**** 热 文** 推 荐 **

[315 后，等待失业的程序员](https://blog.csdn.net/csdnsevenn/article/details/88630558)  

[简说 Python 生态系统的 14 年演变](https://blog.csdn.net/csdnnews/article/details/88630431)  

[Facebook 洗白？欲打造以隐私为中心的社交平台！](https://blog.csdn.net/csdnnews/article/details/88630430)

[京东强推 995 工作制，中国式变态加班何时休？](https://blog.csdn.net/csdnsevenn/article/details/88609312)

[☞ 再不编程就老了！05 后比特币专家准备赚个 134,000,000 元！](http://mp.weixin.qq.com/s?__biz=Mzg3MDA4NDkxMQ==&mid=2247483728&idx=1&sn=34b9ae80938286e69bc9e8c904d331ea&chksm=ce927910f9e5f0063bcdb6fcfd32f5d0795dfed13741a46406d60f5f6d5d503fe9435a5954fb&scene=21#wechat_redirect)

[☞ 大数据背后的无奈与焦虑：“128 元连衣裙” 划分矮穷挫与白富美？](http://mp.weixin.qq.com/s?__biz=MzA3MjY1MTQwNQ==&mid=2649826545&idx=1&sn=00eef4520ffdca0f5bdffe57832bf735&chksm=871e8313b0690a0562b578b020f422afcc8a9e6e2bf1f913a096ac09af85fefbc755637662a4&scene=21#wechat_redirect)

[☞ 麦克阿瑟奖得主 Dawn Song：区块链能保密和保护隐私？图样图森破！](http://mp.weixin.qq.com/s?__biz=MzU2MTE1NDk2Mg==&mid=2247493304&idx=1&sn=9f65f7410fa450e9e8856a2ffdb415f2&chksm=fc7fb845cb0831530e253fa5438e231ed51c5c443f7398d1461166dc153ea04a7fc054e19971&scene=21#wechat_redirect)

[☞ Pig 变飞机？AI 为什么这么蠢 | Adversarial Attack](http://mp.weixin.qq.com/s?__biz=MzI0ODcxODk5OA==&mid=2247503510&idx=1&sn=137c8f897839b811fdc320392084421c&chksm=e99efd6fdee97479bd6c23ed5f61a26f9d8d25d2acbe936dd1b02aff5cdc8df693aac842d51d&scene=21#wechat_redirect)

[☞ 教训！学 Python 没找对路到底有多惨？](http://mp.weixin.qq.com/s?__biz=MzA5MjcxNjc2Ng==&mid=2650559599&idx=3&sn=64abcd514a2909a65c4c7856476581d6&chksm=88601fdebf1796c8c23470316bbe8e933f41545dc905ce2327e97ddc5e91434cdc1585c68d28&scene=21#wechat_redirect)

System.out.println("点个在看吧！");  
console.log(" 点个在看吧！");  
print(" 点个在看吧！");  
printf(" 点个在看吧！\n");  
cout << " 点个在看吧！" << endl;  
Console.WriteLine(" 点个在看吧！");  
Response.Write(" 点个在看吧！");  
alert(" 点个在看吧！")  
echo " 点个在看吧！"

![](https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_png/Pn4Sm0RsAujO0pvtNCLzZCiaWxGBfq2xaPwze1NRLTSQZYbzWNnTJwDwsReHiam91Wojzvw3RLibjicWkLWJjicgsvw/640?wx_fmt=png)喜欢就点击 “好看” 吧！