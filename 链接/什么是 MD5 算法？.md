> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653191503&idx=1&sn=b18bd0458bf884bcb5d01f1cf2ca8301&chksm=8c990f95bbee8683fcfa9e972fd887cb1e50328ab4d8bd1f6a68ea90de6c67f46e50847e36fb&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpdtcHLBZicEaicasEJJIQD9ZlfFgY5ElQ6ec4ggz7vq6xG2ZZQkWRIY4qOAyg2rH7GLyqHn8KS3Rkw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpdtcHLBZicEaicasEJJIQD9ZQ0hDSIxYibs19v1eIG5OsyOw32zu2KzsmSjBzICCVtbzr7FKeSgicXzw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpdtcHLBZicEaicasEJJIQD9ZAUuE9mePE2mk9RGsGucuMxY7sznGKYrKmiaHnCQkZ6WanLibB4HfuKgw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpdtcHLBZicEaicasEJJIQD9Zd0rjlCbr1JRYQN6Wh8hibL9pHA4AKiaGHcq4Xoq4bLJsPI0XvoozC6icA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpdtcHLBZicEaicasEJJIQD9Z3rdnEHGOwwMbw4nnpthNGHpIPFuibRH1Wlx9xg3oTF2cXc0iaxnZgUOg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpdtcHLBZicEaicasEJJIQD9ZwS2oPc29y5ntl4bNbnOA1KvVQn0ibMnZvW3bsUzAficyROChRhnlpicPg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpdtcHLBZicEaicasEJJIQD9Zx5D5vgXXr2UZiciaVtFHua9557Wu5n7VibhuJZJacyaOFFO8YUvLsotDw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpdtcHLBZicEaicasEJJIQD9ZvIRrOmqhNclLcS9E5iaucVFE9SqCZ6fVUbKvSOt21ay5DlVEev8IPJA/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGobnFbtnWRKAZ2tKQIxHLrqUHlcbChfM762DyVpUkAiaHvS5gTAPIfxXqwHGCa15v68QOzLdKs7exQ/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGobnFbtnWRKAZ2tKQIxHLrq20UJwITULYbD5TfP2xaicMcGcJ69SZd78cDiaS8ClK637mPiay7q4icuKA/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGobnFbtnWRKAZ2tKQIxHLrq2O2nibBN1RxuuuDczg3ia4glyePM5HpFMw7QNQibffa4hVicLOicraxbaBw/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGobnFbtnWRKAZ2tKQIxHLrqLhLgJA1fKcQrhqoJES0aNyOr0q5CxJicgiaIoJydVtr29FtWdqFjP1UA/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGobnFbtnWRKAZ2tKQIxHLrq47WiajVWUbMAVsU2WCBiaQGSdIaELxpVEQjsTEUdZum3LGVgB8JvEWOQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGobnFbtnWRKAZ2tKQIxHLrqCuoVwPMKhtzUfc45A3GGdhHvsybHKic9zEw0kJ7ucZ4a42N8rPE47tw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGobnFbtnWRKAZ2tKQIxHLrqeOGn3HOyaeRsrYXQw5lrFT5qibo7ncw4W5W1JveqburPIDR7pqCQfgQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGobnFbtnWRKAZ2tKQIxHLrqUhIVIwnBOJibGjib7gb4xJRv670kulU9rJibQbhsWtz5caIj7s4AVUUcg/0?wx_fmt=jpeg)

摘要哈希生成的正确姿势是什么样呢？分三步：

1. 收集相关业务参数，在这里是金额和目标账户。当然，实际应用中的参数肯定比这多得多，这里只是做了简化。  

2. 按照规则，把参数名和参数值拼接成一个字符串，同时把给定的**密钥**也拼接起来。之所以需要密钥，是因为攻击者也可能获知拼接规则。

3. 利用 MD5 算法，从原文生成哈希值。MD5 生成的哈希值是 128 位的二进制数，也就是 32 位的十六进制数。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGpwdK0XlkibxX7C9agObr8D1uCniacdDYA980ITXunhLq8s903bNTjTXaT2Uvrtq2kcqMdnvAS27hEQ/0?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGpwdK0XlkibxX7C9agObr8D1fsgc0lxt81JwHdfawCAqx24tzhrSqanFrA0U8DlibqQ6wib4P3VsxzdA/0?wx_fmt=jpeg)

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGpwdK0XlkibxX7C9agObr8D1TXDwpib2Af1KLIaNibFA9dCLfK0icgP6YPnpibWHMewIXbaEAShjE4Uiaew/0?wx_fmt=png)

第三方支付平台如何验证请求的签名？同样分三步：

1. 发送方和请求方约定相同的字符串拼接规则，约定相同的密钥。  

2. 第三方平台接到支付请求，按规则拼接业务参数和密钥，利用 MD5 算法生成 Sign。

3. 用第三方平台自己生成的 Sign 和请求发送过来的 Sign 做对比，如果两个 Sign 值一模一样，则签名无误，如果两个 Sign 值不同，则信息做了篡改。这个过程叫做**验签**。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrE3Scnpyu8ibBP5FIP8NkdN1lewmk75F7xwsywUb6qkjHPU6GKeKP32HYv4n6BCib8KofvNS3ZwaLg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrE3Scnpyu8ibBP5FIP8NkdNjhY63tP7ribFtqialh9J4pibakt3GxVGnXuuuDVrmxWVAADXk6p2UMygg/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGrE3Scnpyu8ibBP5FIP8NkdNO2qwhxhO1G0Z6dfltGx4CE7VXqicIUS4Q1riaEliahclxAGnRb5y2Aibrw/0?wx_fmt=jpeg)

**MD5 算法底层原理：**

简单概括起来，MD5 算法的过程分为四步：**处理原文**，**设置初始值**，**循环加工，拼接结果**。

**第一步: 处理原文**

首先，我们计算出原文长度 (bit) 对 512 求余的结果，如果不等于 448，就需要填充原文使得原文对 512 求余的结果等于 448。填充的方法是第一位填充 1，其余位填充 0。填充完后，信息的长度就是 512*N+448。

之后，用剩余的位置（512-448=64 位）记录原文的真正长度，把长度的二进制值补在最后。这样处理后的信息长度就是 512*(N+1)。  

**第二步: 设置初始值**

MD5 的哈希结果长度为 128 位，按每 32 位分成一组共 4 组。这 4 组结果是由 4 个初始值 A、B、C、D 经过不断演变得到。MD5 的官方实现中，A、B、C、D 的初始值如下（16 进制）：

A=0x01234567

B=0x89ABCDEF

C=0xFEDCBA98

D=0x76543210

**第三步: 循环加工**

  
这一步是最复杂的一步，我们看看下面这张图，此图代表了单次 A,B,C,D 值演变的流程。

![](http://mmbiz.qpic.cn/mmbiz_png/NtO5sialJZGrE3Scnpyu8ibBP5FIP8NkdNK3MV6UZX1vl4icAvNjicyAKjib1ia11DBILjV8O7nVIcoluQ5oyLqCNJMw/0?wx_fmt=png)

图中，A，B，C，D 就是哈希值的四个分组。每一次循环都会让旧的 ABCD 产生新的 ABCD。一共进行多少次循环呢？由处理后的原文长度决定。

假设处理后的原文长度是 M

主循环次数 = **M / 512**  

每个主循环中包含 **512 / 32 * 4 = 64** 次 子循环。

上面这张图所表达的就是**单次子循环**的流程。

下面对图中其他元素一一解释：

**1. 绿色 F**

图中的绿色 F，代表非线性函数。官方 MD5 所用到的函数有四种：

F(X, Y, Z) =(X&Y) | ((~X) & Z)

G(X, Y, Z) =(X&Z) | (Y & (~Z))

H(X, Y, Z) =X^Y^Z

I(X, Y, Z)=Y^(X|(~Z))

在主循环下面 64 次子循环中，F、G、H、I 交替使用，第一个 16 次使用 F，第二个 16 次使用 G，第三个 16 次使用 H，第四个 16 次使用 I。

**2. 红色 “田” 字**

很简单，红色的田字代表相加的意思。

**3.Mi**

Mi 是第一步处理后的原文。在第一步中，处理后原文的长度是 512 的整数倍。把原文的每 512 位再分成 16 等份，命名为 M0~M15，每一等份长度 32。在 64 次子循环中，每 16 次循环，都会交替用到 M1~M16 之一。

**4.Ki**

一个常量，在 64 次子循环中，每一次用到的常量都是不同的。

**5. 黄色的 <<<S**

左移 S 位，S 的值也是常量。

“流水线” 的最后，让计算的结果和 B 相加，取代原先的 B。新 ABCD 的产生可以归纳为：

新 A = 原 d

新 B = b+((a+F(b,c,d)+Mj+Ki)<<<s)

新 C = 原 b

新 D = 原 c

总结一下主循环中的 64 次子循环，可以归纳为下面的四部分：

       **第一轮：**

       FF(a,b,c,d,M0,7,0xd76aa478）     s[0]=7,   K[0] = 0xd76aa478

　　FF(a,b,c,d,M1,12,0xe8c7b756）   s[1]=12,  K[1] = 0xe8c7b756

　　FF(a,b,c,d,M2,17,0x242070db)

　　FF(a,b,c,d,M3,22,0xc1bdceee)

　　FF(a,b,c,d,M4,7,0xf57c0faf)

　　FF(a,b,c,d,M5,12,0x4787c62a)

　　FF(a,b,c,d,M6,17,0xa8304613）

　　FF(a,b,c,d,M7,22,0xfd469501）

　　FF(a,b,c,d,M8,7,0x698098d8）

　　FF(a,b,c,d,M9,12,0x8b44f7af)

　　FF(a,b,c,d,M10,17,0xffff5bb1）

　　FF(a,b,c,d,M11,22,0x895cd7be)

　　FF(a,b,c,d,M12,7,0x6b901122）

　　FF(a,b,c,d,M13,12,0xfd987193）

　　FF(a,b,c,d,M14,17, 0xa679438e)

　　FF(a,b,c,d,M15,22,0x49b40821）

　　**第二轮：**

　　GG(a,b,c,d,M1,5,0xf61e2562）

　　GG(a,b,c,d,M6,9,0xc040b340）

　　GG(a,b,c,d,M11,14,0x265e5a51）

　　GG(a,b,c,d,M0,20,0xe9b6c7aa)

　　GG(a,b,c,d,M5,5,0xd62f105d)

　　GG(a,b,c,d,M10,9,0x02441453）

　　GG(a,b,c,d,M15,14,0xd8a1e681）

　　GG(a,b,c,d,M4,20,0xe7d3fbc8）

　　GG(a,b,c,d,M9,5,0x21e1cde6）

　　GG(a,b,c,d,M14,9,0xc33707d6）

　　GG(a,b,c,d,M3,14,0xf4d50d87）

　　GG(a,b,c,d,M8,20,0x455a14ed)

　　GG(a,b,c,d,M13,5,0xa9e3e905）

　　GG(a,b,c,d,M2,9,0xfcefa3f8）

　　GG(a,b,c,d,M7,14,0x676f02d9）

　　GG(a,b,c,d,M12,20,0x8d2a4c8a)

　　**第三轮：**

　　HH(a,b,c,d,M5,4,0xfffa3942）

　　HH(a,b,c,d,M8,11,0x8771f681）

　　HH(a,b,c,d,M11,16,0x6d9d6122）

　　HH(a,b,c,d,M14,23,0xfde5380c)

　　HH(a,b,c,d,M1,4,0xa4beea44）

　　HH(a,b,c,d,M4,11,0x4bdecfa9）

　　HH(a,b,c,d,M7,16,0xf6bb4b60）

　　HH(a,b,c,d,M10,23,0xbebfbc70）

　　HH(a,b,c,d,M13,4,0x289b7ec6）

　　HH(a,b,c,d,M0,11,0xeaa127fa)

　　HH(a,b,c,d,M3,16,0xd4ef3085）

　　HH(a,b,c,d,M6,23,0x04881d05）

　　HH(a,b,c,d,M9,4,0xd9d4d039）

　　HH(a,b,c,d,M12,11,0xe6db99e5）

　　HH(a,b,c,d,M15,16,0x1fa27cf8）

　　HH(a,b,c,d,M2,23,0xc4ac5665）

　　**第四轮：**

　　Ⅱ（a,b,c,d,M0,6,0xf4292244）

　　Ⅱ（a,b,c,d,M7,10,0x432aff97）

　　Ⅱ（a,b,c,d,M14,15,0xab9423a7）

　　Ⅱ（a,b,c,d,M5,21,0xfc93a039）

　　Ⅱ（a,b,c,d,M12,6,0x655b59c3）

　　Ⅱ（a,b,c,d,M3,10,0x8f0ccc92）

　　Ⅱ（a,b,c,d,M10,15,0xffeff47d)

　　Ⅱ（a,b,c,d,M1,21,0x85845dd1）

　　Ⅱ（a,b,c,d,M8,6,0x6fa87e4f)

　　Ⅱ（a,b,c,d,M15,10,0xfe2ce6e0)

　　Ⅱ（a,b,c,d,M6,15,0xa3014314）

　　Ⅱ（a,b,c,d,M13,21,0x4e0811a1）

　　Ⅱ（a,b,c,d,M4,6,0xf7537e82）

　　Ⅱ（a,b,c,d,M11,10,0xbd3af235）

　　Ⅱ（a,b,c,d,M2,15,0x2ad7d2bb)

　　Ⅱ（a,b,c,d,M9,21,0xeb86d391）

**第四步: 拼接结果**

这一步就很简单了，把循环加工最终产生的 A，B，C，D 四个值拼接在一起，转换成字符串即可。

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGr5x46g5CKgCZe1AQNib7BUHiaJumV8yz1t5MctBWet51wH5mLG3j2NZEBqvsjUfaK6Ebu4sRvjFBcQ/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGr5x46g5CKgCZe1AQNib7BUH1yTThVOy1V3o2HqNTN5FusWnibHMLNV6TiaVmcfGWdt51q7xHDypRFug/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGr5x46g5CKgCZe1AQNib7BUHLwhZgxdtLEsib8CHF0xicdkMznQrganmEoCLypP6wcicvxlyIicQt8TqMw/0?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGr5x46g5CKgCZe1AQNib7BUHczpnDfGbOSGogdw5ZHYKwTJo8YmPCSS4HQBjWdCibGqeqEJFn4c5Mkw/0?wx_fmt=jpeg)

—————END—————

喜欢本文的朋友们，欢迎长按下图关注订阅号**梦见**，收看更多精彩内容

![](http://mmbiz.qpic.cn/mmbiz_jpg/NtO5sialJZGoBj18gILw2hefgpNaCia1eRhNCzRx29e1DpVhicyenCic4RQibDTbzySoqqpOrmBxu7KlLZM73YDDPJg/0?wx_fmt=jpeg)