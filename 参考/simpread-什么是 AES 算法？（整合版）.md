> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653191726&idx=1&sn=c7856fe211471d01e9afdfea4a7f6b87&chksm=8c990cf4bbee85e28bb2ea63cb1f767dee4702ca8b9ef23db3467558a4b27ff5b6c1893c8771&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqOEJSOuHdBr383pNVb4zMW3k2B4FnA6r63MEDnWKp41aBa0TU4dRj3ALtW1jEH8ckGv3zoF5grMg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpHEMyjvSDmgMGP6aM8631VWSJ1Y22HALY9MamXQIDosyzyTyYiaDKm75A8Lic3SCXoM9G24VNBrdNw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpHEMyjvSDmgMGP6aM8631VGm2A2IeuVtQfVvkml9Wrxcicg8QBzuML9wGYiaf1bqpTEq7z6PMLu26Q/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpHEMyjvSDmgMGP6aM8631VM7uElvABuJ4ZYCu0nopnPe6kpJhl3QRDwXjdf5tVJicIGV3KsJycgWw/0?wx_fmt=jpeg)

假设有一个发送方在向接收方发送消息。如果没有任何加密算法，接收方发送的是一个明文消息：“我是小灰”

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGpHEMyjvSDmgMGP6aM8631Vk5VicicpcNpIRicCtxOJhG2iaRZ2CCOIkW9R10F3Aia5BwP7DOEJMicqG8iaw/0?wx_fmt=png)

如果消息被中间人截获到，即使中间人无法篡改消息，也可以窥探到消息的内容，从而暴露了通信双方的私密。

因此我们不再直接传送明文，而改用对称加密的方式传输密文，画风就变成了下面这样：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGpHEMyjvSDmgMGP6aM8631VTibZf2qFyPS20gbr7q8LBx5PZ4iaE2gVEFrQzsBx1SYaosltXD5icPLzw/0?wx_fmt=png)

具体工作的步骤如下：

1. 发送方利用密钥 123456，加密明文 “我是小灰”，加密结果为 TNYRvx+SNjZwEK+ZXFEcDw==。

2. 发送方把加密后的内容 TNYRvx+SNjZwEK+ZXFEcDw== 传输给接收方。

3. 接收方收到密文 TNYRvx+SNjZwEK+ZXFEcDw==，利用密钥 123456 还原为明文 “我是小灰”。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9hKcjZZzEIZUfPqNQ2j2ibLBsHwG1QH1iaghuX5XAE9uXFQ9PfIljHRaQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9TQ14Ee8DryejddfSNxZZfCE1pGo7a9NHSpcAEy3GDibGJywaSDMTNxA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9mdTf7fdE3sVeMvYk1dQpFeicXExSjuvVvVppSglqMsTuCVHNJDL9ScQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9tbJHujicC89sB17coiadcmc2PwG3f5Kt4p6bia9OFicsQpFcFYvjLFHaxQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9icHBAe61xum2oJx4Ih6w7UqmmiajrkV4fXdxp5K2gca7w2GMbXcbyxqw/0?wx_fmt=jpeg)

**1. 密钥**

密钥是 AES 算法实现加密和解密的根本。对称加密算法之所以对称，是因为这类算法对明文的加密和解密需要使用**同一个密钥**。

AES 支持三种长度的密钥：

**128 位，192 位，256 位**  

平时大家所说的 AES128，AES192，AES256，实际上就是指的 AES 算法对不同长度密钥的使用。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9L0fPUCp6oJArTLOmVribFYYk8x4VicAp5vGgicmAbLK8olFebqMD8CVuA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9O1xC7ERibicHeDR4CTbdxe7H3SoWZB3DDAlw970cCZ4mFw8VVInrjOew/0?wx_fmt=jpeg)

**2. 填充**

要想了解填充的概念，我们先要了解 AES 的**分组加密**特性。

什么是分组加密呢？我们来看看下面这张图：

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9s6YXrfM0MVs0ib83ibiaiapVenHnokj0dDEoRXK7crrUvsG4ibMiamUUYETw/0?wx_fmt=png)

AES 算法在对明文加密的时候，并不是把整个明文一股脑加密成一整段密文，而是把明文拆分成一个个独立的明文块，每一个明文块长度 128bit。

这些明文块经过 AES 加密器的复杂处理，生成一个个独立的密文块，这些密文块拼接在一起，就是最终的 AES 加密结果。

但是这里涉及到一个问题：

假如一段明文长度是 192bit，如果按每 128bit 一个明文块来拆分的话，第二个明文块只有 64bit，不足 128bit。这时候怎么办呢？就需要对明文块进行**填充**（Padding）。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9Qv5QNHcicySgRu2mLqkibXdPVQ6FRoesPl9fjQq0uj8rN7OGYtiaicia34Q/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9WVh0EGv0FpJKf5L2UUIYuDicky4TnbicibVo0LeibiaKfxeMPGdnePFsQIw/0?wx_fmt=jpeg)

```
NoPadding：

不做任何填充，但是要求明文必须是16字节的整数倍。

```

```
PKCS5Padding（默认）：

如果明文块少于16个字节（128bit），在明文块末尾补足相应数量的字符，且每个字节的值等于缺少的字符数。

比如明文：{1,2,3,4,5,a,b,c,d,e},缺少6个字节，则补全为{1,2,3,4,5,a,b,c,d,e,6,6,6,6,6,6}



```

```
ISO10126Padding：

如果明文块少于16个字节（128bit），在明文块末尾补足相应数量的字节，最后一个字符值等于缺少的字符数，其他字符填充随机数。

比如明文：{1,2,3,4,5,a,b,c,d,e},缺少6个字节，则可能补全为{1,2,3,4,5,a,b,c,d,e,5,c,3,G,$,6}



```

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9KeVMqN5lgHbWKAUVot5n6eQibwa7FcwnoicD6CJsbz9x7NltG33uI1MQ/0?wx_fmt=jpeg)

**3. 模式**

AES 的工作模式，体现在把明文块加密成密文块的处理过程中。AES 加密算法提供了五种不同的工作模式：

ECB、CBC、CTR、CFB、OFB

模式之间的主题思想是近似的，在处理细节上有一些差别。我们这一期只介绍各个模式的基本定义。

****ECB 模式（默认）：****

电码本模式    Electronic Codebook Book

****CBC 模式：****

密码分组链接模式    Cipher Block Chaining

**CTR 模式：**

计算器模式    Counter

**CFB 模式：**

密码反馈模式    Cipher FeedBack

**OFB 模式：**

输出反馈模式    Output FeedBack

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9icPMDIFaShUKHZE7Fkuo0sRm7c8cDDZFap5mnOuWfhV9xhNvZtGicCuA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9YbnOT3aMSlSqpJ8jvJ62K9Ehf86nQgJZRhS16VXroib2HRW0KxSIQDA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9qeMf5pJlQO59zgqnt9BuQLyIbPmDcr6Jr6ibMVX6pEMd184PSBZoOsg/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGqEJxHy5wyDyWjwH7tTCHY9lx86MlXfU0SYnVNPtNGgMm9vZOe4GJy3kGta3vPco5wNvvuwh90nug/0?wx_fmt=png)

1. kgen.init 传入的第一个参数 128 决定了密钥的长度是 **128bit**。

2. Cipher.getInstance("AES/CBC/NoPadding") 决定了 AES 选择的填充方式是 **NoPadding**，工作模式是 **CBC** 模式。

几点补充：

1. 我们在调用封装好的 AES 算法时，表面上使用的 Key 并不是真正用于 AES 加密解密的密钥，而是用于生成真正密钥的 “种子”。

2. 填充明文时，如果明文长度原本就是 16 字节的整数倍，那么除了 NoPadding 以外，其他的填充方式都会填充一组额外的 16 字节明文块。

以上就是 AES 的基本概念。但我们是有追求的程序员，不能知其然不知其所以然。下面来给大家讲一讲 AES 算法的**底层原理**。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2TkciawvdtNbGDOqljxC3wNES7a9cVsl4kicZpNhkfxCTveCcCtLu0vXpSA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2Tkvib62uqFWbdXvnwITJBUNHNdCzFsFEmvZ6a1L2mtiauiatJFIPZvQuHibA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2Tk4rGdtAnwFo8aPXXq7lf9EGM0aRLmcic9eiaiaYrXic07PblLQxN55GJaWQ/0?wx_fmt=png)

在这里我们重新梳理一下：

1. 把明文按照 128bit 拆分成若干个明文块。

2. 按照选择的填充方式来填充最后一个明文块。

3. 每一个明文块利用 AES 加密器和密钥，加密成密文块。

4. 拼接所有的密文块，成为最终的密文结果。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2Tk2HRZYI8qBKrVL6qK86Mfiac0AQWxuey1MMMVxNohlGkmCP7Hj0zVGkQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2TkSUlVBVKkbtC7YEmJFVLIkR5NvaJuIHoicUQibZVBoqgRr1NibyRZBF1AQ/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2TkCxNgL3oMSx6U42xYZf9J24RUG7VnLcqCsYf5E6jEHI3KesYCfpIvHQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGqxl1Ghldfn3LNfddWrVv65tf1laTMS3kibhtr88dhXeW8VzJLzsB58PNqGzlSSukDCLCrdBE5aXzA/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2Tk4GYhxIZibXhbEEuZpgQb2dT2TDruBD2kstbeM7T3eYU59cKvHCufCoA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2Tka272Wf0wRwrNvOmoPe2Poy2WGPOMjOOibPrHCazmTSicOCibJ25GKPXdw/0?wx_fmt=jpeg)

具体分成多少轮呢？

初始轮（Initial Round）  1 次  

普通轮（Rounds）          N 次

最终轮（Final Round）   1 次

上一期我们提到，AES 的 Key 支持三种长度：AES128，AES192，AES256。Key 的长度决定了 AES 加密的轮数。

除去初始轮，各种 Key 长度对应的轮数如下：

AES128：10 轮

AES192：12 轮

AES256：14 轮

不同阶段的 Round 有不同的处理步骤。

初始轮只有一个步骤：

加轮密钥（AddRoundKey）

普通轮有四个步骤：

字节代替（SubBytes）  

行移位（ShiftRows）

列混淆（MixColumns）

加轮密钥（AddRoundKey）

最终轮有三个步骤：

字节代替（SubBytes）  

行移位（ShiftRows）

加轮密钥（AddRoundKey）

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2TkfgTb1lxFibqQSI9KWH8DmxDndvCDKXUFia5kDJ8zzyrOrJkKic2FZwJLA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2Tku0rnmSZicMyQfcM7CIYcqG5olwEAO90DmxlmLfGVGgX6KcGEaY3TBbA/0?wx_fmt=jpeg)

**1. 字节替代（SubBytes）**

**![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2Tk8sGPZZLKDBNkibEyB4ELB8HoMiaQiccPye2ghOxwiccc4icl2Je8UFK99zg/0?wx_fmt=png)**

首先需要说明的是，16 字节的明文块在每一个处理步骤中都被排列成 4X4 的二维数组。

所谓字节替代，就是把明文块的每一个字节都替代成另外一个字节。替代的依据是什么呢？依据一个被称为 **S 盒**（Subtitution Box）的 16X16 大小的二维常量数组。

假设明文块当中 a[2,2] = 5B（一个字节是两位 16 进制），那么输出值 b[2,2] = S[5][11]。

**2. 行移位（ShiftRows）**

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2Tk90hy8SPSTJSmb1aRUt214ibxBfZIE7abzN2Eo8oYVmStH2wLss1Uhew/0?wx_fmt=png)

这一步很简单，就像图中所描述的：

第一行不变

第二行循环左移 **1** 个字节

第三行循环左移 **2** 个字节

第四行循环左移 **3** 个字节

**3. 列混淆（MixColumns）**

**![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2Tk9icvPrRjVib0syMpaQT4UxTl8ialwXuo5hibLibicKstoxzHoXCrjiaXViaE7Q/0?wx_fmt=png)**

这一步，输入数组的每一列要和一个名为修补矩阵（fixed matrix）的二维常量数组做矩阵相乘，得到对应的输出列。

**4. 加轮密钥（AddRoundKey）**

**![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrdYqgrsc54ibVk2PxGfw2TkndvNIvptFBLjvtAiaBcf0FxqtCXy3AGicoZNzsic7wnXichJo1MwkL4dYA/0?wx_fmt=png)**

这一步是唯一利用到密钥的一步，128bit 的密钥也同样被排列成 4X4 的矩阵。

让输入数组的每一个字节 a[i,j] 与密钥对应位置的字节 k[i,j] 异或一次，就生成了输出值 b[i,j]。

需要补充一点，加密的每一轮所用到的密钥并不是相同的。这里涉及到一个概念：扩展密钥（KeyExpansions）。

**扩展密钥（KeyExpansions）**

AES 源代码中用长度 4 * 4 *（10+1） 字节的数组 W 来存储所有轮的密钥。W{0-15} 的值等同于原始密钥的值，用于为初始轮做处理。

后续每一个元素 W[i] 都是由 W[i-4] 和 W[i-1] 计算而来，直到数组 W 的所有元素都赋值完成。

W 数组当中，W{0-15} 用于初始轮的处理，W{16-31} 用于第 1 轮的处理，W{32-47} 用于第 2 轮的处理 ...... 一直到 W{160-175} 用于最终轮（第 10 轮）的处理。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp77X4s4J7NLiarnBjJpTibPxBgstMNrIT77hJia95sGicjFbeKdTzWQ4XJicAn8y6tsPPPcy2LAkMfibbw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp77X4s4J7NLiarnBjJpTibPxtCCnpia6grIt64pF8viaEpTSqBhnOGmJ7grU97PYchxzOXZsodTia4Ing/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp77X4s4J7NLiarnBjJpTibPx7KpkRWYXq3UAosks3vA9bm2VCpBWEvba248gsib5srV7WWrnyGLpaZQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp77X4s4J7NLiarnBjJpTibPxojTTeqC4YSBREWhBYjskia61BSz1c2I0sde5zW7bVCm5n3q4Otib8Kqw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp77X4s4J7NLiarnBjJpTibPxQfJAib4YxsqHqCKzAGvETzAhTXmABjl7yx0XAIy1ickiaRXb9qFIZerkA/0?wx_fmt=jpeg)

**1.ECB 模式**

ECB 模式（Electronic Codebook Book）是最简单的工作模式，在该模式下，每一个明文块的加密都是完全独立，互不干涉的。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp77X4s4J7NLiarnBjJpTibPxJeDMxH5wGCsSFTvo548AkX3Np1kXIE0Cm76vO7v8dFTqjQlkvVVjVw/0?wx_fmt=png)

这样的好处是什么呢？

1. 简单

2. 有利于并行计算

缺点同样也很明显：

相同的明文块经过加密会变成相同的密文块，因此安全性较差。

2.CBC 模式

CBC 模式（Cipher Block Chaining）引入了一个新的概念：初始向量 IV（Initialization Vector）。

IV 是做什么用的呢？它的作用和 MD5 的 “加盐” 有些类似，目的是防止同样的明文块始终加密成同样的密文块。  

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGp77X4s4J7NLiarnBjJpTibPxoSYw2ichRqLE2zwJE6rnwMHsAPR5OCVDtRpt0S6AsQ4ia358ffvz7tZw/0?wx_fmt=png)

从图中可以看出，CBC 模式在每一个明文块加密前会让明文块和一个值先做异或操作。IV 作为初始化变量，参与第一个明文块的异或，后续的每一个明文块和它**前一个明文块所加密出的密文块**相异或。

这样以来，相同的明文块加密出的密文块显然是不一样的。

CBC 模式的好处是什么呢？

安全性更高

坏处也很明显：

1. 无法并行计算，性能上不如 ECB

2. 引入初始化向量 IV，增加复杂度。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGp77X4s4J7NLiarnBjJpTibPxJvp2AWTtBQmCcdNAfQ6ShH99E9hPGkiadfmztbJ2EdNwWGGibyAexa5A/0?wx_fmt=jpeg)

—————END—————
