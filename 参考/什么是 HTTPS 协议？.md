> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://zhuanlan.zhihu.com/p/57142784 ![](https://pic3.zhimg.com/v2-524646215b79a0e7267c8a7d46e9fd16_b.jpg)![](https://pic3.zhimg.com/v2-524646215b79a0e7267c8a7d46e9fd16_r.jpg)![](https://pic4.zhimg.com/v2-c4b9146fe6f03d833b7fe8df1f1c2cc7_b.jpg)![](https://pic4.zhimg.com/v2-c4b9146fe6f03d833b7fe8df1f1c2cc7_r.jpg)![](https://pic2.zhimg.com/v2-f7fcd95013789480db11fd1b7eb55509_b.jpg)![](https://pic2.zhimg.com/v2-f7fcd95013789480db11fd1b7eb55509_r.jpg)![](https://pic2.zhimg.com/v2-4eef519c8cea5fff0da282fb9d4d223d_b.jpg)![](https://pic2.zhimg.com/v2-4eef519c8cea5fff0da282fb9d4d223d_r.jpg)![](https://pic1.zhimg.com/v2-fb3b0913369f6e1743d6d1de84301d7c_b.jpg)![](https://pic1.zhimg.com/v2-fb3b0913369f6e1743d6d1de84301d7c_r.jpg)![](https://pic2.zhimg.com/v2-65926f942d62ccf1b3edd764eabe8c39_b.jpg)![](https://pic2.zhimg.com/v2-65926f942d62ccf1b3edd764eabe8c39_r.jpg)![](https://pic2.zhimg.com/v2-10efce49f484837df7c304c6570aeec9_b.jpg)![](https://pic2.zhimg.com/v2-10efce49f484837df7c304c6570aeec9_r.jpg)![](https://pic3.zhimg.com/v2-bdeb151822932f73e724b6cf27425bf2_b.jpg)![](https://pic3.zhimg.com/v2-bdeb151822932f73e724b6cf27425bf2_r.jpg)![](https://pic4.zhimg.com/v2-1c5163717af4472622bf367a73dfa11f_b.jpg)![](https://pic4.zhimg.com/v2-1c5163717af4472622bf367a73dfa11f_r.jpg)

什么是 HTTP 协议？

HTTP 协议全称 Hyper Text Transfer Protocol，翻译过来就是超文本传输协议，位于 TCP/IP 四层模型当中的应用层。

![](https://pic2.zhimg.com/v2-9ba73cb74c5122a63b761dafc6f2b535_b.jpg)![](https://pic2.zhimg.com/80/v2-9ba73cb74c5122a63b761dafc6f2b535_hd.jpg)

HTTP 协议通过请求 / 响应的方式，在客户端和服务端之间进行通信。

![](https://pic2.zhimg.com/v2-673b31f4fcac9f3a0cc68f49047c1cc5_b.jpg)![](https://pic2.zhimg.com/v2-673b31f4fcac9f3a0cc68f49047c1cc5_r.jpg)

这一切看起来很美好，但是 HTTP 协议有一个致命的缺点：**不够安全**。

HTTP 协议的信息传输完全以明文方式，不做任何加密，相当于是在网络上 “裸奔”。这样会导致什么问题呢？让我们打一个比方：

小灰是客户端，小灰的同事小红是服务端，有一天小灰试图给小红发送请求。

![](https://pic1.zhimg.com/v2-5d6f3dd19432efca1239da14a03db72c_b.jpg)![](https://pic1.zhimg.com/v2-5d6f3dd19432efca1239da14a03db72c_r.jpg)

但是，由于传输信息是明文，这个信息有可能被某个中间人恶意截获甚至篡改。这种行为叫做**中间人攻击**。

![](https://pic3.zhimg.com/v2-962a13238153eb14a3a6db2def609456_b.jpg)![](https://pic3.zhimg.com/v2-962a13238153eb14a3a6db2def609456_r.jpg)![](https://pic2.zhimg.com/v2-7799e14e9991e2e1a7e83483c6da5d75_b.jpg)![](https://pic2.zhimg.com/v2-7799e14e9991e2e1a7e83483c6da5d75_r.jpg)![](https://pic3.zhimg.com/v2-2b7a98cdea1aa4a33731c8681760cdd2_b.jpg)![](https://pic3.zhimg.com/v2-2b7a98cdea1aa4a33731c8681760cdd2_r.jpg)

如何进行加密呢？

小灰和小红可以事先约定一种**对称加密**方式，并且约定一个随机生成的密钥。后续的通信中，信息发送方都使用密钥对信息加密，而信息接收方通过同样的密钥对信息解密。

![](https://pic3.zhimg.com/v2-f44db3085c661cd5ed65ec091333e45e_b.jpg)![](https://pic3.zhimg.com/v2-f44db3085c661cd5ed65ec091333e45e_r.jpg)![](https://pic4.zhimg.com/v2-b9b8b0d8bd2faff248b085a34629a28f_b.jpg)![](https://pic4.zhimg.com/v2-b9b8b0d8bd2faff248b085a34629a28f_r.jpg)

这样做是不是就绝对安全了呢？并不是。

虽然我们在后续的通信中对明文进行了加密，但是第一次约定加密方式和密钥的通信仍然是明文，如果第一次通信就已经被拦截了，那么密钥就会泄露给中间人，中间人仍然可以解密后续所有的通信内容。

![](https://pic4.zhimg.com/v2-aff24615cc6e366c27166f222aafb2eb_b.jpg)![](https://pic4.zhimg.com/v2-aff24615cc6e366c27166f222aafb2eb_r.jpg)

这可怎么办呢？别担心，我们可以使用**非对称加密**，为密钥的传输做一层额外的保护。

非对称加密的一组秘钥对中，包含一个公钥和一个私钥。明文既可以用公钥加密，用私钥解密；也可以用私钥加密，用公钥解密。

在小灰和小红建立通信的时候，小红首先把自己的公钥 Key1 发给小灰：

![](https://pic2.zhimg.com/v2-f3ab1daafe9daa179a15d447467c7aed_b.jpg)![](https://pic2.zhimg.com/v2-f3ab1daafe9daa179a15d447467c7aed_r.jpg)

收到小红的公钥以后，小灰自己生成一个用于对称加密的密钥 Key2，并且用刚才接收的公钥 Key1 对 Key2 进行加密（这里有点绕），发送给小红：

![](https://pic2.zhimg.com/v2-d2daf8ba5615dc9d427a6fe3d20d1031_b.jpg)![](https://pic2.zhimg.com/v2-d2daf8ba5615dc9d427a6fe3d20d1031_r.jpg)

小红利用自己非对称加密的私钥，解开了公钥 Key1 的加密，获得了 Key2 的内容。从此以后，两人就可以利用 Key2 进行对称加密的通信了。

![](https://pic4.zhimg.com/v2-d3ecc2fa78ccaed37c478b4055198ceb_b.jpg)![](https://pic4.zhimg.com/v2-d3ecc2fa78ccaed37c478b4055198ceb_r.jpg)

在通信过程中，即使中间人在一开始就截获了公钥 Key1，由于不知道私钥是什么，也无从解密。

![](https://pic4.zhimg.com/v2-30c40e1ed1390d623cdb51725834e1fb_b.jpg)![](https://pic4.zhimg.com/v2-30c40e1ed1390d623cdb51725834e1fb_r.jpg)![](https://pic1.zhimg.com/v2-b7b095e69390bb8d0dd3d50826d25444_b.jpg)![](https://pic1.zhimg.com/v2-b7b095e69390bb8d0dd3d50826d25444_r.jpg)

是什么坏主意呢？中间人虽然不知道小红的私钥是什么，但是在截获了小红的公钥 Key1 之后，却可以偷天换日，自己另外生成一对公钥私钥，把自己的公钥 Key3 发送给小灰。

![](https://pic3.zhimg.com/v2-9a458296959c052b026560a5892368f2_b.jpg)![](https://pic3.zhimg.com/v2-9a458296959c052b026560a5892368f2_r.jpg)

小灰不知道公钥被偷偷换过，以为 Key3 就是小红的公钥。于是按照先前的流程，用 Key3 加密了自己生成的对称加密密钥 Key2，发送给小红。

这一次通信再次被中间人截获，中间人先用自己的私钥解开了 Key3 的加密，获得 Key2，然后再用当初小红发来的 Key1 重新加密，再发给小红。

![](https://pic2.zhimg.com/v2-5123c1a68be44a178c6f6c1543dfc3f1_b.jpg)![](https://pic2.zhimg.com/v2-5123c1a68be44a178c6f6c1543dfc3f1_r.jpg)

这样一来，两个人后续的通信尽管用 Key2 做了对称加密，但是中间人已经掌握了 Key2，所以可以轻松进行解密。

![](https://pic4.zhimg.com/v2-172b8cc90f0013804ac20e3f33da8967_b.jpg)![](https://pic4.zhimg.com/v2-172b8cc90f0013804ac20e3f33da8967_r.jpg)![](https://pic4.zhimg.com/v2-b6ef53ea1cea40c4e080030bbcd98fdf_b.jpg)![](https://pic4.zhimg.com/v2-b6ef53ea1cea40c4e080030bbcd98fdf_r.jpg)

是什么解决方案呢？难道再把公钥进行一次加密吗？这样只会陷入鸡生蛋蛋生鸡，永无止境的困局。

这时候，我们有必要引入第三方，一个权威的证书颁发机构（CA）来解决。

到底什么是证书呢？证书包含如下信息：

![](https://pic4.zhimg.com/v2-d67c298621ff7b0993bf40fada27f573_b.jpg)![](data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='411' height='283'></svg>)

为了便于说明，我们这里做了简化，只列出了一些关键信息。至于这些证书信息的用处，我们看看具体的通信流程就能够弄明白了。

流程如下：

1. 作为服务端的小红，首先把自己的公钥发给证书颁发机构，向证书颁发机构申请证书。

![](https://pic3.zhimg.com/v2-e94e6b7dc1376c799ae3e1aadc469ea2_b.jpg)![](https://pic3.zhimg.com/v2-e94e6b7dc1376c799ae3e1aadc469ea2_r.jpg)

2. 证书颁发机构自己也有一对公钥私钥。机构利用自己的私钥来加密 Key1，并且通过服务端网址等信息生成一个证书签名，证书签名同样经过机构的私钥加密。证书制作完成后，机构把证书发送给了服务端小红。

![](https://pic3.zhimg.com/v2-4a394ede38b526839ce8ada8643934c6_b.jpg)![](https://pic3.zhimg.com/v2-4a394ede38b526839ce8ada8643934c6_r.jpg)

3. 当小灰向小红请求通信的时候，小红不再直接返回自己的公钥，而是把自己申请的证书返回给小灰。

![](https://pic3.zhimg.com/v2-35e555a8c74fbddc32ff610819480936_b.jpg)![](https://pic3.zhimg.com/v2-35e555a8c74fbddc32ff610819480936_r.jpg)

4. 小灰收到证书以后，要做的第一件事情是验证证书的真伪。需要说明的是，**各大浏览器和操作系统已经维护了所有权威证书机构的名称和公钥**。所以小灰只需要知道是哪个机构颁布的证书，就可以从本地找到对应的机构公钥，解密出证书签名。

接下来，小灰按照同样的签名规则，自己也生成一个证书签名，如果两个签名一致，说明证书是有效的。

验证成功后，小灰就可以放心地再次利用机构公钥，解密出服务端小红的公钥 Key1。

![](https://pic3.zhimg.com/v2-123b8861cdefd9a322760ec7591f1cde_b.jpg)![](https://pic3.zhimg.com/v2-123b8861cdefd9a322760ec7591f1cde_r.jpg)

5. 像之前一样，小灰生成自己的对称加密密钥 Key2，并且用服务端公钥 Key1 加密 Key2，发送给小红。

![](https://pic3.zhimg.com/v2-e8288a959d6fbd5019fa91c0e0b370f2_b.jpg)![](https://pic3.zhimg.com/v2-e8288a959d6fbd5019fa91c0e0b370f2_r.jpg)

6. 最后，小红用自己的私钥解开加密，得到对称加密密钥 Key2。于是两人开始用 Key2 进行对称加密的通信。

![](https://pic1.zhimg.com/v2-4e1c4a6836c8a4b0c7511a0d21710664_b.jpg)![](https://pic1.zhimg.com/v2-4e1c4a6836c8a4b0c7511a0d21710664_r.jpg)

在这样的流程下，我们不妨想一想，中间人是否还具有使坏的空间呢？

![](https://pic3.zhimg.com/v2-98502876ee22cbe643e3cd2a6ad74c2e_b.jpg)![](https://pic3.zhimg.com/v2-98502876ee22cbe643e3cd2a6ad74c2e_r.jpg)![](https://pic4.zhimg.com/v2-761d4917160cc15f06056f7043a84307_b.jpg)![](https://pic4.zhimg.com/v2-761d4917160cc15f06056f7043a84307_r.jpg)![](https://pic4.zhimg.com/v2-abd130a15c5ba261a42f93add277948f_b.jpg)![](https://pic4.zhimg.com/v2-abd130a15c5ba261a42f93add277948f_r.jpg)![](https://pic1.zhimg.com/v2-0473074a3253131c0ad3ef4902cb2fb8_b.jpg)![](https://pic1.zhimg.com/v2-0473074a3253131c0ad3ef4902cb2fb8_r.jpg)![](https://pic3.zhimg.com/v2-0d5de173c1283fc80fa06e7a55140706_b.jpg)![](https://pic3.zhimg.com/v2-0d5de173c1283fc80fa06e7a55140706_r.jpg)![](https://pic4.zhimg.com/v2-6340df8970e6f498c68e629537cfa45b_b.jpg)![](https://pic4.zhimg.com/v2-6340df8970e6f498c68e629537cfa45b_r.jpg)![](https://pic1.zhimg.com/v2-3db9b326b9d31745229a26a877a1308c_b.jpg)![](data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='276' height='412'></svg>)

注：最新推出的 TLS 协议，是 SSL 3.0 协议的升级版，和 SSL 协议的大体原理是相同的。

![](https://pic4.zhimg.com/v2-0a821a606e8d1bbc6bd67114d043cd97_b.jpg)![](https://pic4.zhimg.com/v2-0a821a606e8d1bbc6bd67114d043cd97_r.jpg)![](https://pic4.zhimg.com/v2-9b71efa009a28c86e798a1117c8823db_b.jpg)![](https://pic4.zhimg.com/v2-9b71efa009a28c86e798a1117c8823db_r.jpg)写下你的评论...  

厉害啊，老哥

文章里有一些硬伤，首先，签名验签不同于加密解密，只有基于 RSA 的签名算法可以近似看作是相同的，更广泛使用的 DSA 算法和 ECDSA 都不是这样的，文章里的很多描述都需要修改。除此之外，第四点第二段里，“小灰” 只有 CA 的公钥，是无法生成证书签名的。验证过程也不是这样，“小灰” 只需要根据公钥验证证书的签名即可。

厉害啊，老哥

o

怎么小灰的画风变丑了…

你好优秀啊~~

[赞][赞][赞] 厉害

怀念以前的小灰

文章里有一些硬伤，首先，签名验签不同于加密解密，只有基于 RSA 的签名算法可以近似看作是相同的，更广泛使用的 DSA 算法和 ECDSA 都不是这样的，文章里的很多描述都需要修改。除此之外，第四点第二段里，“小灰” 只有 CA 的公钥，是无法生成证书签名的。验证过程也不是这样，“小灰” 只需要根据公钥验证证书的签名即可。

不愧是支付宝的老哥，关注一下

对，第 4 点应该是通过 CA 的公钥解密数字签名，得到服务器信息的摘要，和原本的信息，再对原本的信息 hash，和摘要对比来进行校验

这货不是小灰，小灰一定是被中间人绑架了

多半是

还易容了 (⁎⁍̴̛ᴗ⁍̴̛⁎)

高产似。。。。

我居然硬生生看懂了

简单明了，感谢作者。

人物好可爱

优秀学生代表

证书的签名不是机构私钥加密的么？怎么用机构公钥解得出来

非对称加密，就是公钥加密用私钥解，私钥加密用公钥解咯

那攻击的一方不也能用公钥解密吗？

通俗易懂，多谢老师！三克油歪瑞码器

怎么小灰换人了？

图样图森破，如果中间人是假 CA 一样能截获和伪造通信数据。要做到真正安全，必须有线下约定，比如客户端预先内置公钥或证书签名。

以上级 ca 来保证下级 ca 真实可靠，root ca 预先内置

问题就出在 root ca，中间层将自己证书内置到 root ca，签发假证书。像 Charles 抓包等都是这么做的。

厉害👍

终于搞懂了，谢谢