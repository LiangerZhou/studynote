> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s/gQnuynv6XPD_aeIBQBeI2Q

点击上方 " 张狗蛋的技术之路 "，选择 “置顶或者星标”

   你的关注意义重大!  

Redis 是一个开源的 key-value 存储系统，它使用六种底层数据结构构建了包含字符串对象、列表对象、哈希对象、集合对象和有序集合对象的对象系统。今天我们就通过 12 张图来全面了解一下它的数据结构和对象系统的实现原理。

本文的内容如下：

*   首先介绍六种基础数据结构：动态字符串，链表，字典，跳跃表，整数集合和压缩列表。
    
*   其次介绍 Redis 的对象系统中的字符串对象（String）、列表对象（List）、哈希对象（Hash）、集合对象（Set）和有序集合对象（ZSet）。
    
*   最后介绍 Redis 的键空间和过期键 (expire) 实现。
    

### 数据结构

#### 简单动态字符串

Redis 使用动态字符串 SDS 来表示字符串值。下图展示了一个值为 Redis 的 SDS 结构 :

*   len: 表示字符串的真正长度（不包含 NULL 结束符在内）。
    
*   alloc: 表示字符串的最大容量（不包含最后多余的那个字节）。
    
*   flags: 总是占用一个字节。其中的最低 3 个 bit 用来表示 header 的类型。
    
*   buf: 字符数组。 ![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4Kf0FdBt48Pz2rsW0BIswVaL5takFv71ibrkUmfH9yMdF575Xh0rkZlwA/640?wx_fmt=png)
    

SDS 的结构可以减少修改字符串时带来的内存重分配的次数，这依赖于内存预分配和惰性空间释放两大机制。

当 SDS 需要被修改，并且要对 SDS 进行空间扩展时，Redis 不仅会为 SDS 分配修改所必须要的空间，还会为 SDS 分配**额外的未使用的空间**。

*   如果修改后， SDS 的长度 (也就是 len 属性的值) 将小于 1MB ，那么 Redis 预分配和 len 属性相同大小的未使用空间。
    
*   如果修改后， SDS 的长度将大于 1MB ，那么 Redis 会分配 1MB 的未使用空间。
    

比如说，进行修改后 SDS 的 len 长度为 20 字节，小于 1MB，那么 Redis 会预先再分配 20 字节的空间， SDS 的 buf 数组的实际长度 (除去最后一字节) 变为 20 + 20 = 40 字节。当 SDS 的 len 长度大于 1MB 时，则只会再多分配 1MB 的空间。

类似的，当 SDS 缩短其保存的字符串长度时，并不会立即释放多出来的字节，而是等待之后使用。

#### 链表

链表在 Redis 中的应用非常广泛，比如列表对象的底层实现之一就是链表。除了链表对象外，发布和订阅、慢查询、监视器等功能也用到了链表。

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4K7WN6ZeiamiaicETD9oBsysPP44wkU2ZjxHXoTlcibFic0jRibscic5yuTLdgg/640?wx_fmt=png)

Redis 的链表是双向链表，示意图如上图所示。链表是最为常见的数据结构，这里就不在细说。

Redis 的链表结构的 dup 、 free 和 match 成员属性是用于实现多态链表所需的类型特定函数：

*   dup 函数用于复制链表节点所保存的值，用于深度拷贝。
    
*   free 函数用于释放链表节点所保存的值。
    
*   match 函数则用于对比链表节点所保存的值和另一个输入值是否相等。
    

#### 字典

字典被广泛用于实现 Redis 的各种功能，包括键空间和哈希对象。其示意图如下所示。

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4KZuQd3n2lpkAYMwhYjOf8M4G6DHnbVOorBhbJqhNL0WfBicJXHibtiaQBg/640?wx_fmt=png)

Redis 使用 MurmurHash2 算法来计算键的哈希值，并且使用链地址法来解决键冲突，被分配到同一个索引的多个键值对会连接成一个单向链表。

#### 跳跃表

Redis 使用跳跃表作为有序集合对象的底层实现之一。它以有序的方式在层次化的链表中保存元素， 效率和平衡树媲美 —— 查找、删除、添加等操作都可以在对数期望时间下完成， 并且比起平衡树来说， 跳跃表的实现要简单直观得多。

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4KMia0qgwlxrOCW3OhUsA5WtQRXmuIwZxxntlHO3qL2iaia2GQia6ibPqIEqw/640?wx_fmt=png)

跳表的示意图如上图所示，这里只简单说一下它的核心思想，并不进行详细的解释。

如示意图所示，zskiplistNode 是跳跃表的节点，其 ele 是保持的元素值，score 是分值，节点按照其 score 值进行有序排列，而 level 数组就是其所谓的层次化链表的体现。

每个 node 的 level 数组大小都不同， level 数组中的值是指向下一个 node 的指针和 跨度值 (span)，跨度值是两个节点的 score 的差值。越高层的 level 数组值的跨度值就越大，底层的 level 数组值的跨度值越小。

level 数组就像是不同刻度的尺子。度量长度时，先用大刻度估计范围，再不断地用缩小刻度，进行精确逼近。

当在跳跃表中查询一个元素值时，都先从第一个节点的最顶层的 level 开始。比如说，在上图的跳表中查询 o2 元素时，先从 o1 的节点开始，因为 zskiplist 的 header 指针指向它。

先从其 level[3] 开始查询，发现其跨度是 2，o1 节点的 score 是 1.0，所以加起来为 3.0，大于 o2 的 score 值 2.0。所以，我们可以知道 o2 节点在 o1 和 o3 节点之间。这时，就改用小刻度的尺子了。就用 level[1] 的指针，顺利找到 o2 节点。

#### 整数集合

整数集合 intset 是集合对象的底层实现之一，当一个集合只包含整数值元素，并且这个集合的元素数量不多时， Redis 就会使用整数集合作为集合对象的底层实现。

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4KAlsicNIBBIE6AUia4OD8hFKxFM2uwnhoKeZy9MufM8JkLII5efo33Psg/640?wx_fmt=png)

如上图所示，整数集合的 encoding 表示它的类型，有 int16t，int32t 或者 int64_t。其每个元素都是 contents 数组的一个数组项，各个项在数组中按值的大小从小到大有序的排列，并且数组中不包含任何重复项。length 属性就是整数集合包含的元素数量。

#### 压缩列表

压缩队列 ziplist 是列表对象和哈希对象的底层实现之一。当满足一定条件时，列表对象和哈希对象都会以压缩队列为底层实现。

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4K7hk8jibDZpkibGW85zM2KtCOd9FgYAe2dTYE1kvFiau0Tfcric1DtpulkQ/640?wx_fmt=png)

压缩队列是 Redis 为了节约内存而开发的，是由一系列特殊编码的连续内存块组成的顺序型数据结构。它的属性值有：

*   zlbytes : 长度为 4 字节，记录整个压缩数组的内存字节数。
    
*   zltail : 长度为 4 字节，记录压缩队列表尾节点距离压缩队列的起始地址有多少字节，通过该属性可以直接确定尾节点的地址。
    
*   zllen : 长度为 2 字节，包含的节点数。当属性值小于 INT16_MAX 时，该值就是节点总数，否则需要遍历整个队列才能确定总数。
    
*   zlend : 长度为 1 字节，特殊值，用于标记压缩队列的末端。
    

中间每个节点 entry 由三部分组成：

*   previous_entry_length : 压缩列表中前一个节点的长度，和当前的地址进行指针运算，计算出前一个节点的起始地址。
    
*   encoding： 节点保存数据的类型和长度
    
*   content ：节点值，可以为一个字节数组或者整数。
    

### 对象

上面介绍了 6 种底层数据结构，Redis 并没有直接使用这些数据结构来实现键值数据库，而是基于这些数据结构创建了一个对象系统，这个系统包含字符串对象、列表对象、哈希对象、集合对象和有序集合这五种类型的对象，每个对象都使用到了至少一种前边讲的底层数据结构。

Redis 根据不同的使用场景和内容大小来判断对象使用哪种数据结构，从而优化对象在不同场景下的使用效率和内存占用。

Redis 的 redisObject 结构的定义如下所示。

```
typedef struct redisObject {
    unsigned type:4;
    unsigned encoding:4;
    unsigned lru:LRU_BITS; 
    int refcount;
    void *ptr;
} robj;

```

其中 type 是对象类型，包括 REDISSTRING, REDISLIST, REDISHASH, REDISSET 和 REDIS_ZSET。

encoding 是指对象使用的数据结构，全集如下。![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4KegTFRXVdfxhRWD8dUaF6CiaqQWv2auZTicZccbTfNGYRfRmocG0vG19g/640?wx_fmt=png)

#### 字符串对象

我们首先来看字符串对象的实现，如下图所示。

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4K4VbCwcMULCWXceHhYsAal3gW02ZEwgBFpjL7Robetav7WUAEzTEeFw/640?wx_fmt=png)

如果一个字符串对象保存的是一个字符串值，并且长度大于 32 字节，那么该字符串对象将使用 SDS 进行保存，并将对象的编码设置为 raw，如图的上半部分所示。如果字符串的长度小于 32 字节，那么字符串对象将使用 embstr 编码方式来保存。

embstr 编码是专门用于保存短字符串的一种优化编码方式，这个编码的组成和 raw 编码一致，都使用 redisObject 结构和 sdshdr 结构来保存字符串，如上图的下半部所示。

但是 raw 编码会调用两次内存分配来分别创建上述两个结构，而 embstr 则通过一次内存分配来分配一块连续的空间，空间中一次包含两个结构。

embstr 只需一次内存分配，而且在同一块连续的内存中，更好的利用缓存带来的优势，但是 embstr 是只读的，不能进行修改，当一个 embstr 编码的字符串对象进行 append 操作时， redis 会现将其转变为 raw 编码再进行操作。

#### 列表对象

列表对象的编码可以是 ziplist 或 linkedlist。其示意图如下所示。

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4KNo4Bg3V03Aicpj6eoTmoHeKC5T7dIN5mleQ6zd9iccNtgXgq9k1NOlSA/640?wx_fmt=png)

当列表对象可以同时满足以下两个条件时，列表对象使用 ziplist 编码：

*   列表对象保存的所有字符串元素的长度都小于 64 字节。
    
*   列表对象保存的元素数量数量小于 512 个。
    

不能满足这两个条件的列表对象需要使用 linkedlist 编码或者转换为 linkedlist 编码。

#### 哈希对象

哈希对象的编码可以使用 ziplist 或 dict。其示意图如下所示。

当哈希对象使用压缩队列作为底层实现时，程序将键值对紧挨着插入到压缩队列中，保存键的节点在前，保存值的节点在后。如下图的上半部分所示，该哈希有两个键值对，分别是 name:Tom 和 age:25。

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4KsMS3sicVtrYlfc8cq5BVsETBNv46YPwlpBxPVz1RjaPH2PJYU7eSRLQ/640?wx_fmt=png)

当哈希对象可以同时满足以下两个条件时，哈希对象使用 ziplist 编码:

*   哈希对象保存的所有键值对的键和值的字符串长度都小于 64 字节。
    
*   哈希对象保存的键值对数量小于 512 个。
    

不能满足这两个条件的哈希对象需要使用 dict 编码或者转换为 dict 编码。

#### 集合对象

集合对象的编码可以使用 intset 或者 dict。

intset 编码的集合对象使用整数集合最为底层实现，所有元素都被保存在整数集合里边。

而使用 dict 进行编码时，字典的每一个键都是一个字符串对象，每个字符串对象就是一个集合元素，而字典的值全部都被设置为 NULL。如下图所示。

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btACSy8FDqq42CkrEgpGcz4KX8LrI8uRu9dOwYiaHhjPHm5scZ3V4RNs68MWtf6lBvP1hOPADv7Niclw/640?wx_fmt=png)

当集合对象可以同时满足以下两个条件时，对象使用 intset 编码:

*   集合对象保存的所有元素都是整数值。
    
*   集合对象保存的元素数量不超过 512 个。
    

否则使用 dict 进行编码。

#### 有序集合对象

有序集合的编码可以为 ziplist 或者 skiplist。

有序集合使用 ziplist 编码时，每个集合元素使用两个紧挨在一起的压缩列表节点表示，前一个节点是元素的值，第二个节点是元素的分值，也就是排序比较的数值。

压缩列表内的集合元素按照分值从小到大进行排序，如下图上半部分所示。

有序集合使用 skiplist 编码时使用 zset 结构作为底层实现，一个 zet 结构同时包含一个字典和一个跳跃表。

其中，跳跃表按照分值从小到大保存所有元素，每个跳跃表节点保存一个元素，其 score 值是元素的分值。而字典则创建一个一个从成员到分值的映射，字典的键是集合成员的值，字典的值是集合成员的分值。通过字典可以在 O(1) 复杂度查找给定成员的分值。如下图所示。

跳跃表和字典中的集合元素值对象都是共享的，所以不会额外消耗内存。

![](https://mmbiz.qpic.cn/mmbiz_jpg/q3blumn0btACSy8FDqq42CkrEgpGcz4KiaamgBzo59rB0az72v42owkMOJuqiaDcCqDCTmyNhibJlIoeFu3NXtP5Q/640?wx_fmt=jpeg)

当有序集合对象可以同时满足以下两个条件时，对象使用 ziplist 编码：

*   有序集合保存的元素数量少于 128 个；
    
*   有序集合保存的所有元素的长度都小于 64 字节。
    

否则使用 skiplist 编码。

### 数据库键空间

Redis 服务器都有多个 Redis 数据库，每个 Redis 数据都有自己独立的键值空间。每个 Redis 数据库使用 dict 保存数据库中所有的键值对。

![](https://mmbiz.qpic.cn/mmbiz_jpg/q3blumn0btACSy8FDqq42CkrEgpGcz4KzjpZgH58Dkic8GibfdiaU502fTZCo2VxUwicUH0ogkmiab8YYzKM70uUIyQ/640?wx_fmt=jpeg)

键空间的键也就是数据库的键，每个键都是一个字符串对象，而值对象可能为字符串对象、列表对象、哈希表对象、集合对象和有序集合对象中的一种对象。

除了键空间，Redis 也使用 dict 结构来保存键的过期时间，其键是键空间中的键值，而值是过期时间，如上图所示。

通过过期字典，Redis 可以直接判断一个键是否过期，首先查看该键是否存在于过期字典，如果存在，则比较该键的过期时间和当前服务器时间戳，如果大于，则该键过期，否则未过期。

- 关注我

![](https://mmbiz.qpic.cn/mmbiz_png/q3blumn0btA7ZBKA159GkOAxGqxBrYE0RFLwvXwDTrEPRHNFnqAwMEb2d49r3QYzSg4v2viajpzaeajE7xVa97w/640?wx_fmt=png)

推荐阅读：

[基于 Redis 和 Lua 的分布式限流](http://mp.weixin.qq.com/s?__biz=MzU2MDYwMDMzNQ==&mid=2247483788&idx=1&sn=e47726a14f622fc0015d9628838ad2fe&chksm=fc04c5eccb734cfad8de3b624e2e26f2ac6a6790508b212146ff9e1d97d5026870b1e5af021a&scene=21#wechat_redirect)

[分布式数据缓存中的一致性哈希算法](http://mp.weixin.qq.com/s?__biz=MzU2MDYwMDMzNQ==&mid=2247483830&idx=1&sn=1d1930e49f1f61e67090b3fe8c84e624&chksm=fc04c5d6cb734cc00b426a7d5f2352756ca97b728e74479d0ce75c42ee32e61d1013d9b6becc&scene=21#wechat_redirect)  

[Redis Cluster 的数据分片机制](http://mp.weixin.qq.com/s?__biz=MzU2MDYwMDMzNQ==&mid=2247483837&idx=1&sn=aedde5f908f47cb18123bbcbbebb2337&chksm=fc04c5ddcb734ccbcb2d9e4e13ceb7937b98eeeb5413447d540d1d5519305a1e041a92c93aae&scene=21#wechat_redirect)  

 感谢搓一下” 在**看** “ ![](https://res.wx.qq.com/mpres/htmledition/images/icon/common/emotion_panel/smiley/smiley_84.png)