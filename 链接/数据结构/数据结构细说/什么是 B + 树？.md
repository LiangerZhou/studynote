> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653191027&idx=1&sn=4ba22e3ec8bd149f69fc0aba72e4347e&chksm=8c9909a9bbee80bfa1d8497ff0525df130414c1731b5aa5287bf16ea1cf86c8d8e6f20782184&scene=21#wechat_redirect

在上一篇漫画中，我们介绍了 B - 树的原理和应用，没看过的小伙伴们可以点击下面的链接：  

[漫画：什么是 B - 树？](http://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653190965&idx=1&sn=53f78fa037386f85531832cd5322d2a0&chksm=8c9909efbbee80f90512f0c36356c31cc74c388c46388dc2317d43c8f8597298f233ca9c29e9&scene=21#wechat_redirect)  

这一次我们来介绍 B + 树。

—————————————————

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWnUZWGqFibB67D1jJSrCib7dbKwb9alIrbd1EGrtCMP5cApLl7fOfu8dg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWxXe8URSjDot9qRMPfwUgLR0vYIEXPZm2PtyKhW5cDX0YYwiatwwicPDQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWI1nOoibYtF5g3ViaasktduzvaQa82RYIYKK7PpjR9GowH1d9ngRQLDpA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWDGkGmp0pbWZHy0ARgq5mFnEU43icOKl2WTkmRTt26hsOxSvr040ISOQ/0?wx_fmt=jpeg)

**一个 m 阶的 B 树具有如下几个特征：**

1. 根结点至少有两个子女。

2. 每个中间节点都包含 k-1 个元素和 k 个孩子，其中 m/2 <= k <= m

3. 每一个叶子节点都包含 k-1 个元素，其中 m/2 <= k <= m

4. 所有的叶子结点都位于同一层。

5. 每个节点中的元素从小到大排列，节点当中 k-1 个元素正好是 k 个孩子包含的元素的值域分划。

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWlS1Ga5NPKfWy0oMOdwic51e1GmB6Ly86xtnHJuOvPojiaZiazfn3G8o9g/0?wx_fmt=jpeg)

**一个 m 阶的 B + 树具有如下几个特征：**

1. 有 k 个子树的中间节点包含有 k 个元素（B 树中是 k-1 个元素），每个元素不保存数据，只用来索引，所有数据都保存在叶子节点。

2. 所有的叶子结点中包含了全部元素的信息，及指向含这些元素记录的指针，且叶子结点本身依关键字的大小自小而大顺序链接。

3. 所有的中间节点元素都同时存在于子节点，在子节点元素中是最大（或最小）元素。

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWvGPhcLC5KUR6nS0y43UkBickpRqNDHoCyeKmNDcpwxgteSsyrdJSxibQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWlKtp41Tb329jCECIe2a05icnlBlVOTCdeQKNP6BPS8mtksdLStWIqoQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWUVnCwEHicib0SMjaQxF08mpdE5k0MkQKGF4aRjvPJFPibficAvhDVI28eA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWdKrHQez446RaDLFZ9GzkcdduW75BlwD4YicSn9vDVianRuJrdK1x3xBQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWlENKtSK6Hw1giabCJm8ictbI6RcYpe2ibQ5bptEiakbyJ9aPh2tQyozbicQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWTKSPRlWicrkEvJVcPibE9wGyjJzfbntOSCTdg2B23fpLFmwx8uQibW3nw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWkfCibcqSxZANC3vkHibXasqAibGY15qqc1QicpyboHorSpep9XZnaDIefg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWyqIwczSBNzHJF9UkBKaPJFva7z1zA9OlpVXac0xFiar3eapQFhfZnCg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pW9yjIQHedIcYbWGTdknjMb4k2YCJbu4R0oenib3aHKKmNLrNHFVHFjHA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pW1mop77hmW0euicbCO0vyA4DPwMy4UbBvFWiaQiabibXkKLAgaicpwUicESYA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWI9NoahgCbKFYcQZ0x3sMMTzzpOUx4WuHl6PaoNeicczBT9xxRvaqjpw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWu9JxaBSILSojEUdby9NHbApkSfJhCcqTR0zwma0p6CPLF6vTBFtGEg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWibeDfY4E8p2UFFm3UnBC02mwAI0ic7LvYGddoC1NT7E96XlKM5yDI9fg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWfgialxT2Lk6EKIW2keLbSCQI9oibwvnJthSGahclps7c1u0RtqzJ5UNA/0?wx_fmt=jpeg)

B - 树中的卫星数据（Satellite Information）：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pW7cPy9dgkyNkgRmSSR0n0Hueuc6xiatV8xjV3csnrLKQtibsbAzHODq6g/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWWUQgyrJINKO4tNI5R3XRdNAIYViaIS1icuUzYibtok7mPokibTYlc5iaYwg/0?wx_fmt=jpeg)

B + 树中的卫星数据（Satellite Information）：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWHh99F2iakg9snMXq2riaQvI96ZBKq8ibOyVABr12HichuYYBgDAU6JibmuQ/0?wx_fmt=jpeg)

需要补充的是，在数据库的聚集索引（Clustered Index）中，叶子节点直接包含卫星数据。在非聚集索引（NonClustered Index）中，叶子节点带有指向卫星数据的指针。

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWlibEclqdicliczItOQ0BTCc94Tjx4hK3QOibTBYqTic9W99bfkZEepHhobg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pW97oNUTsZBesRIa9JMfgHPugQRiaXOR9ByFYawic5zp5O7ZIppicY9ibpaw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pW1O0oglp3SdXsN4iaKAuhQdpwkj9vMD4N5JjOgA4guCNPtmetOgiaP3Rg/0?wx_fmt=jpeg)

第一次磁盘 IO：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWALKA2cKa2Az6vFk9jcXkSCgXW23aECY3IM9qkZibEeJsW1133T32q0A/0?wx_fmt=jpeg)

第二次磁盘 IO：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWQ5xibk8HqcNj2Py6qSXaFSDFPicrtMlctR8ibwibp8NOHibY7UTYAGkXPNQ/0?wx_fmt=jpeg)

第三次磁盘 IO：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWfp6Vs4TiacndxRibfDKoqaDh702GicTJJYcQ7p0zrmIFavPGFibsLiaS5DA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWRUfNhz8DaSYPmp49KBibHYmiab2oPFqa27pnNH22N6UdicgpVUibx7cjKA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWHTfuPgD3Okks1JvaiafgwCoFISZs4ja9ILVUXsupMHSv7p6K5licH0Vw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWrR88bBnTTqgjtjxxsYUpDdg9ra3rchusBudFN9b11ncLHHG0OQw3mA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWt280MzTs4m5ZE1LXib1pW2Kdm9y7icjtTq8lHzot5SneAh4ubibzFibiawA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWBeRTP5g5InIURA5g5V8hcgzvkqowr3d7m0p3dkt19Mmic3QNtqtFyaA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWkpblZmEgJKVZZtkhrTlf8JYxS5ohjmoWhkIww4WFficHkENauhl7F4g/0?wx_fmt=jpeg)

**B - 树的范围查找过程**

自顶向下，查找到范围的下限（3）：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWRZKppF7LWMS5ibIvtRnIhgyp2Ufvg6iaGqaXiaPWLdA62yc7egmTPlnmg/0?wx_fmt=jpeg)

中序遍历到元素 6：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pW1Z3JotpdD0ibcfYg4hLwUyUmrN6ia4t7sVVh0yfpMmlib2XbQI6mVQRwA/0?wx_fmt=jpeg)

中序遍历到元素 8：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pW8F3jYDUmNbLyoBTKP5nDiblULjyWGpkicic7Icm5QvibzcfNfC05rvB6VA/0?wx_fmt=jpeg)

中序遍历到元素 9：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWEKahLygMy0dib0UU7MXs0PEicYFhicHSFTeiahGib5TCAWX9vFia94asDoyg/0?wx_fmt=jpeg)

中序遍历到元素 11，遍历结束：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWlVibahZaopYBD1QKLPk7pnCKbOeRmVa6iavA5o8O2GczP67oEu7rQqSg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWhp9SXcoMfE53EIEug81ibmJafmfTglXgwO7rwG9AZj9FY71icD6ujU5A/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pW9KrEfPDm82hWPBByicp4JGmktZeQlvr6nEF7BC812o9jQ4ktmMcoPuw/0?wx_fmt=jpeg)

**B + 树的范围查找过程**

自顶向下，查找到范围的下限（3）：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWhgC1HqVwF8WQYv7icZ0uFmibfDLRCLQiby5pfPLnJ1BYxeW9bfxEG8LPQ/0?wx_fmt=jpeg)

通过链表指针，遍历到元素 6, 8：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWQEQzMogDLI7WicDib78y1WDn4Jic4YN7m2FAEUyyfA82UVVdmSKQ5P6sw/0?wx_fmt=jpeg)

通过链表指针，遍历到元素 9, 11，遍历结束：

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWxkfoKkeDiasB0srl8Aa34QfPhHmeurELvHic0nFzUOHLvO7v2FUW1ZfQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWkGfwgBuz8sufnXHjjLZPibHy5LM0vKlzf39Z9uuQoiaOhQcUKickNSGdw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pW0HcK41YwayXk62aBHiba7tKN5B1CLEPTmU0ZvcpOiaalMXujvJicr7JqQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWkWdGj1PIO1ria6VjicQZO8O7vubRGQtLCyXveOFEKRUhD8FlyloX11pQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWD8dD8T7Gp6VsVJTFiaxGNFjhyYJL5nnPN7hJ3WumQPSJdXHwo4sosSg/0?wx_fmt=jpeg)

**B + 树的特征：**

1. 有 k 个子树的中间节点包含有 k 个元素（B 树中是 k-1 个元素），每个元素不保存数据，只用来索引，所有数据都保存在叶子节点。

2. 所有的叶子结点中包含了全部元素的信息，及指向含这些元素记录的指针，且叶子结点本身依关键字的大小自小而大顺序链接。

3. 所有的中间节点元素都同时存在于子节点，在子节点元素中是最大（或最小）元素。

**B + 树的优势：**

1. 单一节点存储更多的元素，使得查询的 IO 次数更少。

2. 所有查询都要查找到叶子节点，查询性能稳定。

3. 所有叶子节点形成有序链表，便于范围查询。

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrhjbBgkNEqGwLjaRu359pWGz1E1iciaq8bzs2miaDPcn7pibLThbjA5llpOjTh0DdyCQXT9g8evfibdPQ/0?wx_fmt=jpeg)

—————END—————
