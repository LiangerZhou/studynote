# Http 和 Https 区别

## 1. http 与 https概念

**HTTP**（HyperText Transfer Protocol：超文本传输协议）是一种用于分布式、协作式和超媒体信息系统的应用层协议。 简单来说就是一种发布和接收 HTML 页面的方法，被用于在 Web 浏览器和网站服务器之间传递信息。

HTTP 默认工作在 TCP 协议 80 端口，用户访问网站 `http://` 打头的都是标准 HTTP 服务。

HTTP 协议以明文方式发送内容，不提供任何方式的数据加密，如果攻击者截取了 Web 浏览器和网站服务器之间的传输报文，就可以直接读懂其中的信息，因此，HTTP 协议不适合传输一些敏感信息，比如：信用卡号、密码等支付信息。

**HTTPS**（Hypertext Transfer Protocol Secure：超文本传输安全协议）是一种透过计算机网络进行安全通信的传输协议。HTTPS 经由 HTTP 进行通信，但利用 SSL/TLS 来加密数据包。HTTPS 开发的主要目的，是提供对网站服务器的身份认证，保护交换数据的隐私与完整性。

HTTPS 默认工作在 TCP 协议 443 端口，它的工作流程一般如以下方式：

1、TCP 三次同步握手
2、客户端验证服务器数字证书
3、DH 算法协商对称加密算法的密钥、hash 算法的密钥
4、SSL 安全加密隧道协商完成
5、网页以加密的方式传输，用协商的对称加密算法和密钥加密，保证数据机密性；用协商的 hash 算法进行数据完整性保护，保证数据不被篡改。

https不是一种新协议，它是由http+SSL的结合体，由之前的http—–>tcp，改为http——>SSL—–>tcp。

## 2. HTTP 与 HTTPS 区别

* **https比http更安全**

  HTTP 明文传输，数据都是未加密的，安全性较差，HTTPS（SSL+HTTP） 数据传输过程是加密的，安全性较好。

* **https使用需要CA证书，大部分需要付费**
  
  使用 HTTPS 协议需要到 CA（Certificate Authority，数字证书认证机构） 申请证书，一般免费证书较少，因而需要一定费用。证书颁发机构如：Symantec、Comodo、GoDaddy 和 GlobalSign 等。
HTTP 页面响应速度比 HTTPS 快，主要是因为 HTTP 使用 TCP 三次握手建立连接，客户端和服务器需要交换 3 个包，而 HTTPS 除了 TCP 的三个包，还要加上 ssl 握手需要的 9 个包，所以一共是 12 个包。

* **端口不一样**
  
  http 和 https 使用的是完全不同的连接方式，用的端口也不一样，前者是 80，后者是 443。

* **https耗费资源更高**
  
  HTTPS 其实就是建构在 SSL/TLS 之上的 HTTP 协议，所以，要比较 HTTPS 比 HTTP 要更耗费服务器资源。

## 3. TCP三次握手

在 TCP/IP 协议中，TCP 协议通过三次握手建立一个可靠的连接。

![TCP三次握手](http://ww1.sinaimg.cn/large/bb854e66ly1g9msf84d53j20ld0cu75b.jpg)

> 第一次握手：客户端尝试连接服务器，向服务器发送 syn 包（同步序列编号 Synchronize Sequence Numbers），syn=j，客户端进入 SYN_SEND 状态等待服务器确认
> 第二次握手：服务器接收客户端 syn 包并确认（ack=j+1），同时向客户端发送一个 SYN 包（syn=k），即 SYN+ACK 包，此时服务器进入 SYN_RECV 状态
> 第三次握手：第三次握手：客户端收到服务器的 SYN+ACK 包，向服务器发送确认包 ACK(ack=k+1），此包发送完毕，客户端和服务器进入 ESTABLISHED 状态，完成三次握手

## 4. HTTP 的工作流程

建立TCP/IP连接，客户端与服务器通过Socket三次握手进行连接

客户端向服务端发起HTTP请求（例如：POST/login.html http/1.1）

客户端发送请求头信息，请求内容，最后会发送一空白行，标示客户端请求完毕

服务器做出应答，表示对于客户端请求的应答，例如：HTTP/1.1 200 OK

服务器向客户端发送应答头信息

服务器向客户端发送请求头信息后，也会发送一空白行，标示应答头信息发送完毕，接着就以Content-type要求的数据格式发送数据给客户端

服务端关闭TCP连接，如果服务器或者客户端增Connection:keep-alive就表示客户端与服务器端继续保存连接，在下次请求时可以继续使用这次的连接

## 5. HTTPS 的工作原理

![HTTPS 的工作原理](http://ww1.sinaimg.cn/large/bb854e66ly1g9mss1n1p0j20lw0jdjrj.jpg)

* 1、客户端发起 HTTPS 请求
  
这个没什么好说的，就是用户在浏览器里输入一个 https 网址，然后连接到 server 的 443 端口。

* 2、服务端的配置

采用 HTTPS 协议的服务器必须要有一套数字证书，可以自己制作，也可以向组织申请，区别就是自己颁发的证书需要客户端验证通过，才可以继续访问，而使用受信任的公司申请的证书则不会弹出提示页面 (startssl 就是个不错的选择，有 1 年的免费服务)。

这套证书其实就是一对公钥和私钥，如果对公钥和私钥不太理解，可以想象成一把钥匙和一个锁头，只是全世界只有你一个人有这把钥匙，你可以把锁头给别人，别人可以用这个锁把重要的东西锁起来，然后发给你，因为只有你一个人有这把钥匙，所以只有你才能看到被这把锁锁起来的东西。

* 3、传送证书

这个证书其实就是公钥，只是包含了很多信息，如证书的颁发机构，过期时间等等。

* 4、客户端解析证书

这部分工作是有客户端的 TLS 来完成的，首先会验证公钥是否有效，比如颁发机构，过期时间等等，如果发现异常，则会弹出一个警告框，提示证书存在问题。

如果证书没有问题，那么就生成一个随机值，然后用证书对该随机值进行加密，就好像上面说的，把随机值用锁头锁起来，这样除非有钥匙，不然看不到被锁住的内容。

* 5、传送加密信息

这部分传送的是用证书加密后的随机值，目的就是让服务端得到这个随机值，以后客户端和服务端的通信就可以通过这个随机值来进行加密解密了。

* 6、服务端解密信息

服务端用私钥解密后，得到了客户端传过来的随机值 (私钥)，然后把内容通过该值进行对称加密，所谓对称加密就是，将信息和私钥通过某种算法混合在一起，这样除非知道私钥，不然无法获取内容，而正好客户端和服务端都知道这个私钥，所以只要加密算法够彪悍，私钥够复杂，数据就够安全。

* 7、传输加密后的信息

这部分信息是服务段用私钥加密后的信息，可以在客户端被还原。

* 8、客户端解密信息

客户端用之前生成的私钥解密服务段传过来的信息，于是获取了解密后的内容，整个过程第三方即使监听到了数据，也束手无策。

## 6. https缺点

https虽然安全性比http高出很多但是也有一些缺点

* 握手阶段费时
因为SSL的缘故，HTTPS协议握手阶段比较费时，会使页面的加载时间延长近50%；

* SSL证书需要花钱
便宜没好货，好货不便宜；

* HTTPS连接缓存不如HTTP高效
HTTPS连接缓存不如HTTP高效，会增加数据开销和功耗，甚至已有的安全措施也会因此而受到影响；

* SSL证书通常需要绑定IP
SSL证书通常需要绑定IP，不能在同一IP上绑定多个域名，IPv4资源不可能支撑这个消耗。

* 有局限性
HTTPS协议的加密范围也比较有限，在黑客攻击、拒绝服务攻击、服务器劫持等方面几乎起不到什么作用。最关键的，SSL证书的信用链体系并不安全，特别是在某些国家可以控制CA根证书的情况下，中间人攻击一样可行。

## 7. SSL简介

SSL是Netscape公司所提出的安全保密协议，在浏览器(如Internet Explorer、Netscape Navigator)和Web服务器(如Netscape的Netscape Enterprise Server、ColdFusion Server等等)之间构造安全通道来进行数据传输，SSL运行在TCP/IP层之上、应用层之下，为应用程序提供加密数据通道，它采用了RC4、MD5以及RSA等加密算法，使用40 位的密钥，适用于商业信息的加密。同时，Netscape公司相应开发了HTTPS协议并内置于其浏览器中，HTTPS实际上就是SSL over HTTP，它使用默认端口443，而不是像HTTP那样使用端口80来和TCP/IP进行通信。HTTPS协议使用SSL在发送方把原始数据进行加密，然后在接受方进行解密，加密和解密需要发送方和接受方通过交换共知的密钥来实现，因此，所传送的数据不容易被网络黑客截获和解密。然而，加密和解密过程需要耗费系统大量的开销，严重降低机器的性能，相关测试数据表明使用HTTPS协议传输数据的工作效率只有使用HTTP协议传输的十 分之一。假如为了安全保密，将一个网站所有的Web应用都启用SSL技术来加密，并使用HTTPS协议进行传输，那么该网站的性能和效率将会大大降低，而 且没有这个必要，因为一般来说并不是所有数据都要求那么高的安全保密级别，所以，我们只需对那些涉及机密数据的交互处理使用HTTPS协议，这样就做到鱼与熊掌兼得。总之不需要用https的地方,就尽量不要用。
