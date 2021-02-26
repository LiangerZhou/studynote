> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s/uf2DQLifmIAyS2o2dtHsnA

点击蓝色 “五分钟学算法” 关注我哟

加个 “星标”，一起学算法

![](https://mmbiz.qpic.cn/mmbiz_jpg/D67peceibeIRZZgKcxWEqHa5fibgU8cXApJr3dzIrM34tZibymrVBPY1EnIfaAVAc6KP5q3GYCE2yzVZ96G4wHw3Q/640?wx_fmt=jpeg)

### RSA 算法历史

RSA 是 1977 年由罗纳德 · 李维斯特（**R**on Rivest）、阿迪 · 萨莫尔（Adi **S**hamir）和伦纳德 · 阿德曼（Leonard **A**dleman）一起提出的。

当时他们三人都在麻省理工学院工作。RSA 就是他们三人姓氏开头字母拼在一起组成的。

但实际上，在 1973 年，在英国政府通讯总部工作的数学家克利福德 · 柯克斯（Clifford Cocks）在一个内部文件中提出了一个相同的算法，但他的发现被列入机密，一直到 1997 年才被发表。

### RSA 算法基础知识

#### 密码学知识

**明文：**是指没有加密的文字（或者字符串）。

**加密：**是以某种特殊的算法改变原有的信息数据，使得未授权的用户即使获得了已加密的信息，但因不知解密的方法，仍然无法了解信息的内容。

**密文：**密文是对明文进行加密后的报文。

**对称加密：**对称是指，在对明文进行加密，对密文执行解密加密过程采用相同的规则（通常将双方采用的规则称为 "密钥"）。

例如：  
  （1）甲方选择某一种加密规则，对信息进行加密；  
  （2）乙方使用同一种规则，对信息进行解密。

**非对称加密：**非对称加密是指通信双方采用不同的密钥进行加密解密。

例如：  
  （1）乙方生成两把密钥（公钥和私钥）。公钥是公开的，任何人都可以获得，私钥则是保密的。  
  （2）甲方获取乙方的公钥，然后用它对信息加密。  
  （3）乙方得到加密后的信息，用私钥解密。

#### 数学知识

**互质关系**：如果两个正整数，除了数字 1 之外没有其他公因子，我们称这两个数是互质关系。

**同余定理：**给定一个正整数 m，如果两个整数 a 和 b 满足 a-b 能够被 m 整除，即 (a-b)/m 得到一个整数，那么就称整数 a 与 b 对模 m 同余，记作 a ≡ b(mod m)。

### RSA 算法流程

**（1）选择两个不相等的质数 p 和 q**

例如：选择两个不等质数分别为 61 和 53 （实际应用中选择的质数都相当大）。

**（2）计算 p 和 q 的乘积 n**

n = p*q = 61 * 53 = 3233。

**（3）计算 n 的欧拉函数 φ(n)**

φ(3233) = φ(61 * 53) = φ(61) * φ(53)。

由于 61 为质数，因此 φ(61) = 61 - 1 = 60。同理 φ(53) = 53  - 1 = 52。则 φ(3233) = 60 * 52 = 3120。

**（4）随机选择一个整数 e ，条件是 1<e < φ(n)，且 e 与 φ(n) 互质**

随机选择一个质数 e，保证 1< e < 3120，这里选择 e = 17。

**（5）计算 e 对于φ(n) 的模反元素 d**  
e * d ≡ 1 (mod φ(n))。其中 e = 17，φ(n) = 3120。

设 e*d 是φ(n) 的 k 的整数倍，余数为 1。则上式可以转化为：  
17 * d = 3120k + 1。继续转化得到：  
17 * d + 3120y = 1。其中 y = -k。

其中，对于 d 的求解转化为二元一次方程求解。此方程可以使用扩展欧几里得方法进行求解。通过辗转相除法计算出一组解为 (2753,-15)。

解得 **d = 2753**。

**（6）将 n 和 e 封装成公钥，n 和 d 封装成私钥**

加密公钥为 (3233,17)，私钥为 (3233,2753)。

### RSA 算法分析

那么 RSA 算法是如何保证安全性的呢？

在 RSA 算法中 n 与 e 是公开的，那么破解 RSA 加密的步骤即为通过 n 与 e 计算出私钥 d 的值。

> （1）ed ≡ 1 (mod φ(n))。只有知道 e 和 φ(n)，才能算出 d 。  
> （2）φ(n) = (p-1)(q-1)。只有知道 p 和 q ，才能算出 φ(n)。

由此得出密码破解的实质问题是：从 p * q 的值 n，去求出 (p-1) 和 (q-1)。换句话说，只要求出 p 和 q 的值，我们就能求出 d 的值而得到私钥。但是，当 p 和 q 是是很大的质数时，从它们的积 p * q 去分解因子 p 和 q ，这是一个公认的数学难题。

比如当 p * q 大到 1024 位时，迄今为止还没有人能够利用任何计算工具去完成分解因子的任务。

虽然理论上 RSA 是可以破解的，但是随着密钥长度增加，破解的代价是不可接受的。

因此，只要密钥长度足够长，用 RSA 加密的信息实际上是不能被解破的。目前被破解的最长 RSA 密钥就是 768 位。

### RSA 算法总结

RSA 的安全性依赖于大数分解，因此 RSA 算法加密安全性较高。但是，RSA 算法为保证安全性，会大大提升密钥长度，导致运算速度变慢。这导致它在大量数据加密时并不适用。

END
---

![](https://mmbiz.qpic.cn/mmbiz_png/D67peceibeIQrf3Wy2pLes0oPQtUxKUIdtt56ibb4y4naISicG33fpPYbBAgibHjbUUdRhC788KlzX0AzMpKrj5s1A/640?wx_fmt=png)
----------------------------------------------------------------------------------------------------------------------------------------------

**** 原 创 热 文** 推 荐 **

☞[毕业十年后，我忍不住出了一份程序员的高考试卷](http://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247485538&idx=1&sn=03adf88e7fe8bb7a6df9c1f77b2651b4&chksm=fa0e67e3cd79eef576563214c99121e5dbfc6332a95e45eade3735efc1472ba4c27be086b9bd&scene=21#wechat_redirect)

[☞](http://mp.weixin.qq.com/s?__biz=MzA5MzY4NTQwMA==&mid=2651011536&idx=3&sn=fd5ff61a84fe2180a7c21d15acf71659&chksm=8bad8a27bcda03319570ae23d05392d287390a51ca94014422dd199450b25f43467aaaedb59e&scene=21#wechat_redirect)[一道腾讯面试题：厉害了我的杯](http://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247484557&idx=1&sn=739d80488fe1169a9c9ca26ecfcdfba6&chksm=fa0e6b0ccd79e21a1c2b0d99db69f6206cddddfe2367742e9de1d7d17ec35a5ce29fa4e30d63&scene=21#wechat_redirect)  

[☞](http://mp.weixin.qq.com/s?__biz=MzA5MjcxNjc2Ng==&mid=2650560116&idx=1&sn=f9e86fa3e7b15624177b29ef1a785be4&chksm=88601dc5bf1794d30fd595d413491da189584b44fd2b2e5e40c665e58760e162616585811647&scene=21#wechat_redirect)[十大经典排序算法动画与解析，看我就够了！（配代码完全版）](http://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247484184&idx=1&sn=62965b401aa42107b3c17d1d8ea17454&chksm=fa0e6c99cd79e58f298e9026f677f912bd8c8e55edb48fc509b2b5834f05e529a9b47d59d202&scene=21#wechat_redirect)

[☞](http://mp.weixin.qq.com/s?__biz=MzA5MjcxNjc2Ng==&mid=2650560116&idx=1&sn=f9e86fa3e7b15624177b29ef1a785be4&chksm=88601dc5bf1794d30fd595d413491da189584b44fd2b2e5e40c665e58760e162616585811647&scene=21#wechat_redirect)[这或许是东半球分析十大排序算法最好的一篇文章](http://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247485556&idx=1&sn=344738dd74b211e091f8f3477bdf91ee&chksm=fa0e67f5cd79eee3139d4667f3b94fa9618067efc45a797b69b41105a7f313654d0e86949607&scene=21#wechat_redirect)

[☞](http://mp.weixin.qq.com/s?__biz=MzA5MjcxNjc2Ng==&mid=2650560116&idx=1&sn=f9e86fa3e7b15624177b29ef1a785be4&chksm=88601dc5bf1794d30fd595d413491da189584b44fd2b2e5e40c665e58760e162616585811647&scene=21#wechat_redirect)[面试官，我会写二分查找法！对，没有 bug 的那种！](http://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247485240&idx=1&sn=fbccc747b2e8558c6478171709005ff9&chksm=fa0e68b9cd79e1af5ab68d42adea0b24c7d4867069091df9e95b6f20ab57b69c4e38994a36be&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_png/Pn4Sm0RsAuhpplm16ibb8iaib7RoGQ5iaHEdvAd0o9e1LlUGA2k0Yib222agOxzweXhahA9GuzJcGBg0dA4DzlibxRqw/640?wx_fmt=png)

你点的每个 “在看”，我都认真当成了喜欢