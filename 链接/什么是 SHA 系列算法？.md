> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653191642&idx=1&sn=47c6340a6664af2f62e6c580528ea6f6&chksm=8c990f00bbee861698daa51b0622e581b3279c0759a16330fe538de745cb6abed8288b0bd778&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrUU6tFVDricuLHjkpQstLGibw05zJmNicJ4pOnFoSy4cib7CZdjw7sicLnBuicu0Ric1RtGWM7QeOweqTibg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrUU6tFVDricuLHjkpQstLGibwAzmCkseiaFcPZFaHA4vqUoAvA2I101U8D78VSkx9NYw0DKHmo6gzrw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtCwvQnJVYv7xiaX8Soib0vPmaMFLOIEyIcfXradlVpOaMAv27z0c9pCCg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtTzTGs0WTK80hvIQiauO4OiaOnby0ianLsXStykGvodiaic60WjNzLR8n2WQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQt8ica0g0doTa660nXA0Tf8C2QbARjxAw0IWATVACYYewN401haLe29bQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtscrFmaJQV2RpvDnhiamU7BrrbtpRUibFutMo9icsvpZpQIVPG4qHibDRpg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQt6yxs8d7auDwmUiaI0A1Ywq2tM2ryTMjicj1DagVhf723JrbRKaNibJtuQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtecicgOnlec7YiaHvOvX4OGg0TnEBdWZA11KhicZicKosyp2Twdc1Vqm1LA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtzSdHNrQqINpjfIQmQ0sLKzv6tbiat1LJRhjXHc4bPLiaUkI0vU3zRRrA/0?wx_fmt=jpeg)

**SHA-1**  

SHA-1 算法可以从明文生成 160bit 的信息摘要，示例如下：

给定明文：      abcd

SHA-1 摘要：   81FE8BFE87576C3ECB22426F8E57847382917ACF

SHA-1 与 MD5 的主要区别是什么呢？

**1. 摘要长度不同**。

MD5 的摘要的长度尽 128bit，SHA-1 摘要长度 160bit。多出 32bit 意味着什么呢？不同明文的碰撞几率降低了 2^32 = 324294967296 倍。

**2. 性能略有差别**

SHA-1 生成摘要的性能比 MD5 略低。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtvfBIPCP2uwnsUicSde1gGlhkr30bicBVEjIVtf7QjulMLMqDKvVG4DKw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtF8lcgcvVbTtx3PZgnecRQmQktWA6gSiaA66ibuhFPSyfJfKmibicGF6E1g/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtjlcUMg1p3b6Nlqibb4Lq0jbiclxYlaAqJhpxVeeTicR6NVHdJ7xWBTf1w/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtam4AvPxt7Nc68lHFocwtVb144np4kyFJ20LRAxlGhy84OlIRQibqSEA/0?wx_fmt=jpeg)

**SHA-2**  

SHA-2 是一系列 SHA 算法变体的总称，其中包含如下子版本：

SHA-256：可以生成长度 256bit 的信息摘要。

SHA-224：SHA-256 的 “阉割版”，可以生成长度 224bit 的信息摘要。

SHA-512：可以生成长度 512bit 的信息摘要。

SHA-384：SHA-512 的 “阉割版”，可以生成长度 384bit 的信息摘要。

显然，信息摘要越长，发生碰撞的几率就越低，破解的难度就越大。但同时，耗费的性能和占用的空间也就越高。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtpL4Nq1diabicV8TUTZrYz9Y9W6qk1LXpRRmQwicfsqufV9wxJ9mibqU9Nw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtE4GbAeK5zxo7R64zEsoXnA2kQibLiaic6xVqu5k2iboHybeCxUxRPvaHYQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtzzIYMAvJaZrOhrzTibIft3EJJuX4IryMrcJ4ftHdmttaRvVPD08XIug/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtYITdbRTfWriacImqw8LUkkSWuuKp7IXjyYAQGjXI8CzicNTIVCnPAqaw/0?wx_fmt=jpeg)

明文：        abcd

MD5 摘要：

e2fc714c4727ee9395f324cd2e7f331f

SHA-256 摘要：

88d4266fd4e6338d13b845fcf289579d209c897823b9217da3e161936f031589

合成摘要：

e2fc714c4727ee93209c897823b9217da3e161936f031589

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtNJfMsvJicrNwPXBDuqur7xvlM68MsM71Nn5TickgS43dLOibpRrS5upAQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtYZviapNpDzce9pAzBz3dalf5JkdHW0icocFqic7Qiby0DfrNthpIkoVafQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQt50mZ1SC0N8cdC35Pia0O9mhXon5kAVla2vQICtjswUQbQ0N7le0KiaeQ/0?wx_fmt=jpeg)

我们先来回顾一下 MD5 算法的核心过程，没看过的小伙伴们可以点击这个链接：

[漫画：什么是 MD5 算法？](http://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653191503&idx=1&sn=b18bd0458bf884bcb5d01f1cf2ca8301&chksm=8c990f95bbee8683fcfa9e972fd887cb1e50328ab4d8bd1f6a68ea90de6c67f46e50847e36fb&scene=21#wechat_redirect)

简而言之，MD5 把 128bit 的信息摘要分成 A，B，C，D 四段（Words），每段 32bit，在循环过程中交替运算 A，B，C，D，最终组成 128bit 的摘要结果。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrE3Scnpyu8ibBP5FIP8NkdNK3MV6UZX1vl4icAvNjicyAKjib1ia11DBILjV8O7nVIcoluQ5oyLqCNJMw/0?wx_fmt=png)

再看一下 SHA-1 算法，核心过程大同小异，主要的不同点是把 160bit 的信息摘要分成了 A，B，C，D，E 五段。

![](https://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGolTkibOn5N02BRAw7BGXvQtyfm2FWJvCOGC8NsFZ6ibL20ic8OncjUYibKMrG61dbLA9eSBzPJ29HCsQ/0?wx_fmt=png)

再看一下 SHA-2 系列算法，核心过程更复杂一些，把信息摘要分成了 A，B，C，D，E，F，G，H 八段。

![](https://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGolTkibOn5N02BRAw7BGXvQt688FmfKqa8wlreBWkz7Mup8d3cgKyJod4Vibtq4UDFkuNC7cbkvHG8g/0?wx_fmt=png)

其中 SHA-256 的每一段摘要长度是 32bit，SHA-512 的每一段摘要长度是 64bit。SHA-224 和 SHA-384 则是在前两者生成结果的基础上做出裁剪。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGolTkibOn5N02BRAw7BGXvQthMfvDoQWHS9pb9YAwcsUR7dqK0cHFgZdVPyCLK4YKB7lSWseMP9xAA/0?wx_fmt=jpeg)

几点补充：

SHA 家族的最新成员 SHA-3 已经于 2015 年问世。关于 SHA-3 的细节，有兴趣的小伙伴们可以查询资料进一步学习。

—————END—————

继续给大家介绍一个免费的专栏《增长黑盒》，作者是 Alan 和 Yolo，成长中的增长爱好者，皆转行自生物科技行业，毕业于伦敦帝国理工。

以下为《增长黑盒》的专栏介绍：

1. 分享各种增长黑客书籍 / 文章的笔记和分析

2. 介绍评测各类增长黑客的工具及技巧

3. 介绍中外创业公司增长案例

4. 介绍增长黑客们如何用技术变现

5. 思考并实践增长黑客策略，写成日志

关注小专栏平台公众号，在小专栏平台回复 “独立黑盒” 即可获得免费订阅地址

![](https://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGqOEJSOuHdBr383pNVb4zMWliaHuJ5pvfV758SpFClEo4F9M1Hkjeibqwa4mTDCLO8SFXBHtdM0cvNA/0?wx_fmt=png)

喜欢本文的朋友们，欢迎长按下图关注订阅号**梦见**，收看更多精彩内容

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoBj18gILw2hefgpNaCia1eRhNCzRx29e1DpVhicyenCic4RQibDTbzySoqqpOrmBxu7KlLZM73YDDPJg/0?wx_fmt=jpeg)