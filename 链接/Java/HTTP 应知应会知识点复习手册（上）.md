> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483812&idx=1&sn=a0f74e7ea7672b53b55ae20706b318ab&chksm=fbdb1825ccac913381547a47beb4acf7f895200d758413e1bfd168c098bbc0f0b1e83f04c60f&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYibW8N5Fot1d0eXxgP9MGTjEJAB56Ophuia968fR3ezPGHz2L36u6LRUA/640?wx_fmt=png)image

前言
==

本文快速回顾了常考的的知识点，用作面试复习，事半功倍。

**上篇主要内容：** 状态码、Http1.0/1.1/2.0、Https、GET 和 POST

**下篇主要内容：** Web 攻击技术、HTTP 基础概念、HTTP Header 详解、HTTP 应用

面试知识点复习手册
=========

**全复习手册文章导航**

点击公众号下方：技术推文——面试冲刺

**已发布知识点复习手册**

*   [Java 基础知识点面试手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=1&sn=de2751593468f470902b698c19f8987f&chksm=fbdb18d3ccac91c56939e55cd1f0ca1b4753fd178d229440ecbf89752f5e0d518acd453e2497&scene=21#wechat_redirect)  
    
*   [Java 基础知识点面试手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=2&sn=5610afab774b4114110c993fd0fdc43d&chksm=fbdb18d3ccac91c5e3d2978e3a780b14d09e97c997a5c50410d1adb1930da76f40238d8f409d&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483743&idx=1&sn=cc38aab9429905ddc757b529a386d1dd&chksm=fbdb18deccac91c8d0be8b3ae0e4266bb08ead73a2e57f2c977705e7b26ceaaec7aff5d5c67c&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（中）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483753&idx=1&sn=74b8180dc1a1804c355174ed34e6e33d&chksm=fbdb18e8ccac91fe3ce31ed9713bf23598e7f98dcb6d5a22b79aa92b1c773134bfebe71fbbca&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483762&idx=1&sn=1f121db6552a2e77d53c500fa812fc6c&chksm=fbdb18f3ccac91e58229dd3efd09c876722d58863c2b6ff6d444b0825a955a776ced947d8470&scene=21#wechat_redirect)  
    
*   [Redis 基础知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483768&idx=1&sn=ea83244e4b9f1d6f912ca0aadab74466&chksm=fbdb18f9ccac91efe9e32704ac3d69cf1ad390ddae0f169c118ea8b9da91c6e4e6e849677a6d&scene=21#wechat_redirect)  
    
*   [Redis 基础知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483773&idx=1&sn=6bbd589e174b5d6f8bb3d6b242eb6132&chksm=fbdb18fcccac91eaa8c9d941c1d3f8d2f3874841c417d30e3ccd185b1494d51ea2fdf384c876&scene=21#wechat_redirect)  
    
*   [Java 并发知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483778&idx=1&sn=124096cdc14958b8cdae04b805d00fdc&chksm=fbdb1803ccac9115e967cd538a8008a19dacea286a632284d2e8765c6b8bd48f4f235caddd29&scene=21#wechat_redirect)
    
*   [Java 并发知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483782&idx=1&sn=a0cd333ce6a32fa3f29cfae5fb6fe7e1&chksm=fbdb1807ccac9111003947936006f02972b45a6a6592f6d107f4dceb6b19c82fdff25b3ff355&scene=21#wechat_redirect)  
    
*   [Java 虚拟机知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483795&idx=1&sn=4f41e144656b6b6ab6089cd558f6f5ab&chksm=fbdb1812ccac9104e425b3984659ac422afbf0505268645be65935c33bad808d4571dfed5d1f&scene=21#wechat_redirect)  
    
*   [Java 虚拟机知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483801&idx=1&sn=97db60faa634ff18e335ba1f0851969c&chksm=fbdb1818ccac910e956fac57c4cd2b6c1906152aece1f5720aaf52f483b48d31b9506c3d34e5&scene=21#wechat_redirect)  
    
*   [阿里巴巴 Java 开发手册阅读笔记](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483796&idx=1&sn=b06e84f587a61f0f827c038d6e48de0e&chksm=fbdb1815ccac91032530f28473fc219bec68759b3fb54d7b21507238b6478b270f0f78b9d915&scene=21#wechat_redirect)  
    
*   [双非硕士的春招秋招经验总结——对校招，复习以及面试心态的理解](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483669&idx=1&sn=9d45d0a80c55c2b81611e150b059fb2f&chksm=fbdb1894ccac9182a43949d445accee91afab50f27c11906ae3d3121e24908469424d0726369&scene=21#wechat_redirect)
    
*   ...... 等（请查看全复习手册导航）
    

本文参考
====

本文内容主要参考来自 CyC2018 的 Github 仓库：CS-Notes

有删减，修改，补充额外增加内容

本作品采用知识共享署名 - 非商业性使用 4.0 国际许可协议进行许可。

状态码
===

有拓展参考：

https://zhuanlan.zhihu.com/p/34648453

| 状态码 | 类别 | 原因短语 |
| --- | --- | --- |
| 1XX | Informational（信息性状态码） | 接收的请求正在处理 |
| 2XX | Success（成功状态码） | 请求正常处理完毕 |
| 3XX | Redirection（重定向状态码） | 需要进行附加操作以完成请求 |
| 4XX | Client Error（客户端错误状态码） | 服务器无法处理请求 |
| 5XX | Server Error（服务器错误状态码） | 服务器处理请求出错 |

1XX 信息
------

*   **100 Continue** ：表明到目前为止都很正常，客户端可以继续发送请求或者忽略这个响应。
    
*   **101 Switching Protocols 协议升级**：请求者要求服务器切换协议，服务器确认并准备切换
    

*   主要用于 websocket：表示服务端接受 WebSocket 协议的客户端连接
    
*   也可以用于 http2 的升级。
    

2XX 成功
------

*   **200 OK**
    
*   **204 No Content** ：请求已经成功处理，但是返回的响应报文不包含实体的主体部分。一般在只需要从客户端往服务器发送信息，而不需要返回数据时使用。
    
*   **206 Partial Content** ：表示客户端进行了范围请求。响应报文包含由 Content-Range 指定范围的实体内容。
    

3XX 重定向
-------

*   **301 Moved Permanently** ：永久性重定向
    
*   **302 Found** ：临时性重定向
    
*   **303 See Other** ：和 302 有着相同的功能，但是 303 明确**要求客户端应该采用 GET 方法获取资源。**
    

*   注：虽然 HTTP 协议规定 301、302 状态下重定向时不允许把 POST 方法改成 GET 方法，但是大多数浏览器都会在 301、302 和 303 状态下的重定向把 POST 方法改成 GET 方法。
    

*   **304 Not Modified** ：如果请求报文首部包含一些条件，例如：If-Match，If-Modified-Since，If-None-Match，If-Range，If-Unmodified-Since，如果不满足条件，则服务器会返回 304 状态码。
    
    浏览器缓存分为强制缓存和协商缓存，**优先读取强制缓存**。
    
    强制缓存分为 expires 和 cache-control：
    
    协商缓存包括 etag 和 last-modified：
    
    **如果 Last-Modified 和 ETag 同时被使用，则要求它们的验证都必须通过才会返回 304，若其中某个验证没通过，则服务器会按常规返回资源实体及 200 状态码。**
    
    **协商缓存与强制缓存的区别在于强制缓存不需要访问服务器，返回结果是 200，协商缓存需要访问服务器，命中协商缓存的话，返回结果是 304。**
    
    步骤：客户端发送附带条件的请求时（if-matched,if-modified-since,if-none-match,if-range,if-unmodified-since 任一个）服务器端允许请求访问资源，但因发生请求未满足条件的情况后，直接返回 304Modified（服务器端资源未改变，可直接使用客户端未过期的缓存）。
    
    补充网页：expires/cache-control/last-modified/etag 详解以及解释为何应 chrome 该显示 304 却显示 200：  
    http://www.cnblogs.com/vajoy/p/5341664.html
    

*   last-modified 的设置标准是**资源的上次修改时间**
    
*   etag 是为了应对资源修改时间可能很频繁的情况出现的，是基于资源的内容计算出来的值，**因此优先级也较高。**
    
*   expires 是**一个特定的时间**，是比较旧的标准。
    
*   cache-control 通常是**一个具体的时间长度**，比较新，**优先级也比较高。**
    

*   **307 Temporary Redirect** ：临时重定向，与 302 的含义类似，但是 307 要求浏览器**不允许把重定向请求的 POST 方法改成 GET 方法。**
    
    关于 303 和 307：https://blog.csdn.net/liuxingen/article/details/51511034
    
    **303、307 其实就是把原来 301、302 不” 合法” 的处理动作给” 合法化”，因为发现大家都不太遵守，所以干脆就增加一条规定。**
    
    额外功能：也用于 hsts 跳转。hsts 全称 **HTTP 严格传输安全**（HTTP Strict Transport Security，縮寫：HSTS）
    

*   功能是要求浏览器下次访问该站点时使用 https 来访问，而不再需要先是 http 再转 https。**这样可以避免 ssl 剥离攻击**：即攻击者在用户使用 http 访问的过程中进行攻击，对服务器冒充自己是用户，在攻击者和服务器中使用 https 访问，在用户和服务器中使用 http 访问。具体使用方法是在服务器响应头中添加 Strict-Transport-Security，可以设置 max-age。
    

4XX 客户端错误
---------

*   **400 Bad Request** ：请求报文中存在语法错误。提交 json 时，如果 json 格式有问题，接收端接收 json，也会出现 400 bad request。比如常见的 json 串，数组不应该有 ", 但是有" 了。
    
*   **401 Unauthorized** ：该状态码表示发送的请求需要有认证信息（BASIC 认证、DIGEST 认证）。如果之前已进行过一次请求，则表示用户认证失败。
    
*   **403 Forbidden** ：请求被拒绝，服务器端没有必要给出拒绝的详细理由。
    
*   **404 Not Found**
    
*   **405 method not allowed**  
    问题原因：请求的方式（get、post、delete）方法与后台规定的方式不符合。比如： 后台方法规定的请求方式只接受 get，如果用 post 请求，就会出现 405 method not allowed 的提示
    
*   **408 请求超时**
    

5XX 服务器错误
---------

*   **500： Internal Server Error** ：服务器正在执行请求时发生错误。
    
*   **502：Bad Gateway**：进程响应的内容是 nginx 无法理解的响应
    
*   **503 Service Unavilable** ：服务器暂时处于超负载或正在进行停机维护，现在无法处理请求。（瞬时请求量过大）
    
*   **504：Gateway Time-out**：进程阻塞超过 nginx 的时间阈值返回 504
    
*   505：不支持该 http 版本
    

Http1.0/1.1/2.0
===============

参考：

1.  https://mp.weixin.qq.com/s/GICbiyJpINrHZ41u_4zT-A
    
2.  https://github.com/CyC2018/Interview-Notebook/blob/master/notes/HTTP.md
    

1.1 相比 1.0
----------

### 长连接和流水线（Pipelining）处理

HTTP 1.1 支持长连接（PersistentConnection）和管线化（Pipelining）处理，在一个 TCP 连接上可以传送多个 HTTP 请求和响应，减少了建立和关闭连接的消耗和延迟。

如果要断开 TCP 连接，需要由客户端或者服务器端提出断开，使用 Connection : close

在 HTTP1.1 中默认开启 Connection： keep-alive，一定程度上弥补了 HTTP1.0 每次请求都要创建连接的缺点。

### Host 头处理 / 虚拟主机

在 HTTP1.0 中认为每台服务器都绑定一个唯一的 IP 地址，因此，请求消息中的 URL 并没有传递主机名（hostname）。但随着虚拟主机技术的发展，在一台物理服务器上可以存在多个虚拟主机（Multi-homed Web Servers），并且它们共享一个 IP 地址。HTTP1.1 的请求消息和响应消息都应支持 Host 头域，且请求消息中如果没有 Host 头域会报告一个错误（400 Bad Request）。（Host 头域指定请求资源的 Intenet 主机和端口号，必须表示请求 url 的原始服务器或网关的位置。）

*   **在 http 1.1 中不能缺失 host 字段, 如果缺失, 服务器返回 400 bad request**，http1.1 中不能缺失 host 字段，但 host 字段可以是空值。
    
*   在 http 1.0 中可以缺失 host 字段。
    

### 支持分块传输编码

HTTP1.0 中，存在一些浪费带宽的现象，例如客户端只是需要某个对象的一部分，而服务器却将整个对象送过来了，并且不支持断点续传功能，HTTP1.1 则在请求头引入了 range 头域，它允许只请求资源的某个部分，即**返回码是 206（Partial Content）**，这样就方便了开发者自由的选择以便于充分利用带宽和连接。

另一种解释：可以把数据分割成多块，让浏览器逐步显示页面。

### 错误通知的管理 / 新增状态码

在 HTTP1.1 中**新增了 24 个错误状态响应码**，如：

*   409（Conflict）表示请求的资源与资源的当前状态发生冲突；
    
*   410（Gone）表示服务器上的某个资源被永久性的删除。
    

### 缓存处理（协商缓存）

在 HTTP1.0 中主要使用 header 里的 If-Modified-Since,Expires 来做为缓存判断的标准。

HTTP1.1 则引入了更多的缓存控制策略例如 Entity tag，If-Unmodified-Since, If-Match, If-None-Match 等更多可供选择的缓存头来控制缓存策略。

新增缓存处理指令 max-age

### 支持同时打开多个 TCP 连接

### 新增状态码 100

2.0 相比 1.1
----------

https://mp.weixin.qq.com/s/NMhNVDP47npMqx5ruVy43w

**HTTP/1.x 缺陷**

HTTP/1.x 实现简单是以牺牲性能为代价的：

*   客户端需要使用多个连接才能实现并发和缩短延迟；
    
*   不会压缩请求和响应首部，从而导致不必要的网络流量；
    
*   不支持有效的资源优先级，致使底层 TCP 连接的利用率低下。
    

### 二进制分帧层

HTTP/2.0 将报文分成 HEADERS 帧和 DATA 帧，它们都是二进制格式的。

在通信过程中，只会有一个 TCP 连接存在，它承载了任意数量的双向数据流（Stream）。

*   一个数据流（Stream）都有一个唯一标识符和可选的优先级信息，用于承载双向信息。
    
*   消息（Message）是与逻辑请求或响应对应的完整的一系列帧。
    
*   帧（Frame）是最小的通信单位，来自不同数据流的帧可以交错发送，然后再根据每个帧头的数据流标识符重新组装。
    

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYqq2trNAflaOkqfLZD7YItb3syRtujPibeHAZGaicGefvYOibFf4ybunoQ/640?wx_fmt=png)在这里插入图片描述

**和 1.1 区别在于：**

*   HTTP1.x 的解析是**基于文本**。基于文本协议的格式解析存在天然缺陷，文本的表现形式有多样性，要做到健壮性考虑的场景必然很多
    
*   二进制则不同，只认 0 和 1 的组合。基于这种考虑 HTTP2.0 的协议解析决定**采用二进制格式，实现方便且健壮。**
    

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYiafzNEic4jOAQFxnbStGQMaW4ukP8SfaxBJjac9Gkq0uZdmhSZNyibiasA/640?wx_fmt=png)在这里插入图片描述![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYGPl8HKmpXPhSSoySSSV5ic0gOsPCs54swA2pKeTV7KhgpxeE3Me6XUQ/640?wx_fmt=png)在这里插入图片描述

#### 二进制分帧：多路复用（MultiPlexing）

即连接共享，即每一个 request 都是是用作连接共享机制的。一个 request 对应一个 id，这样**一个连接上可以有多个 request**，每个连接的 request 可以随机的混杂在一起，接收方可以根据 request 的 id 将 request 再归属到各自不同的服务端请求里面。

*   **单连接多资源的方式，减少服务端的链接压力, 内存占用更少, 连接吞吐量更大；**
    
*   **由于减少 TCP 慢启动时间，提高传输的速度。**
    

#### HTTP2.0 的多路复用和 HTTP1.X 中的长连接复用有什么区别？

关键点：**一个是串行，一个是并行，一个阻塞不影响其他 request。**

### header 压缩

如上文中所言，对前面提到过 HTTP1.x 的 header 带有大量信息，而且每次都要重复发送，**HTTP2.0 使用 encoder 来减少需要传输的 header 大小**，通讯双方各自 cache 一份 header fields 表，既避免了重复 header 的传输，又减小了需要传输的大小。

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYibeiartqWLicvJKyF7gUh1Z1RiaRhpOZfVP4BlVO3fQF9TtcR96Nw44ibRA/640?wx_fmt=png)在这里插入图片描述![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYllUm1ibtuEE76icfotqeZDTiau3g7V17Jez9J0KjHAqvNevd87lj5rvHQ/640?wx_fmt=png)在这里插入图片描述

### 服务端推送（server push）

同 SPDY 一样，HTTP2.0 也具有 server push 功能。

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsY77BZ6HEALhXBCjDmyRibQ6Ov3icjuicVMXAaBEJ4JDbibiaQEN09SdDvTwg/640?wx_fmt=png)在这里插入图片描述![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYkPcsfoTCiaEqiaVs6sgCZ0JssQwRlERpgYFLXr9nncdeZN1ElR4pnHBg/640?wx_fmt=png)在这里插入图片描述

SPYD 相比 1.1
-----------

### 多路复用

针对 HTTP 高延迟的问题，SPDY 优雅的采取了多路复用（multiplexing）。多路复用通过多个请求 stream 共享一个 tcp 连接的方式，解决了 HOL blocking 的问题，降低了延迟同时提高了带宽的利用率。

### 请求优先级（request prioritization）

多路复用带来一个新的问题是，在连接共享的基础之上有可能会导致关键请求被阻塞。SPDY 允许给每个 request 设置优先级，这样重要的请求就会优先得到响应。比如浏览器加载首页，首页的 html 内容应该优先展示，之后才是各种静态资源文件，脚本文件等加载，这样可以保证用户能第一时间看到网页内容。

### header 压缩

前面提到 HTTP1.x 的 header 很多时候都是重复多余的。选择合适的压缩算法可以减小包的大小和数量。

### 服务端推送（server push）

采用了 SPDY 的网页，例如我的网页有一个 sytle.css 的请求，在客户端收到 sytle.css 数据的同时，服务端会将 sytle.js 的文件推送给客户端，当客户端再次尝试获取 sytle.js 时就可以直接从缓存中获取到，不用再发请求了。

### 基于 HTTPS 的加密协议传输

大大提高了传输数据的可靠性。

HTTP2.0 和 SPDY 的区别
------------------

*   HTTP2.0 支持明文 HTTP 传输，而 SPDY 强制使用 HTTPS
    
*   HTTP2.0 消息头的压缩算法采用 HPACK
    

*   http://http2.github.io/http2-spec/compression.html
    

*   SPDY 消息头的压缩算法采用 DEFLATE
    

*   http://zh.wikipedia.org/wiki/DEFLATE
    

HTTPs
=====

HTTPS 和 HTTP 的区别主要如下：
---------------------

1、https 协议需要到 ca 申请证书，一般免费证书较少，因而**需要一定费用**。

2、http 是超文本传输协议，信息是**明文传输**，https 则是具有安全性的 **ssl 加密传输协议**。

3、用的**端口也不一样**，前者是 80，后者是 443。

4、http 的连接很简单，是无状态的；HTTPS 协议是由 SSL+HTTP 协议构建的可进行**加密传输、身份认证、完整性保护**的网络协议，比 http 协议安全。  
　　  
　　

HTTP 有以下安全性问题：
--------------

*   内容可能会被窃听；
    
*   通信方的身份有可能遭遇伪装；
    
*   报文有可能遭篡改。
    

**HTTPs 并不是新协议，而是让 HTTP 先和 SSL（Secure Sockets Layer）通信，再由 SSL 和 TCP 通信。也就是说 HTTPs 使用了隧道进行通信。**

隧道：它是将原始 IP 包（其报头包含原始发送者和最终目的地）封装在另一个数据包（称为封装的 IP 包）的数据净荷中进行传输。使用隧道的原因是**在不兼容的网络上传输数据，或在不安全网络上提供一个安全路径。**

通过使用 SSL，HTTPs 具有了：

**加密（防窃听）、认证（防伪装）和完整性保护（防篡改）**

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsY4fIVGXdUk9L8kP4WkFibbpmYz2xrwB4WCNSwvyqbmiaw7k0ANGiactQjg/640?wx_fmt=png)在这里插入图片描述

HTTPs 认证
--------

**请看下面加黑字体是重点：**

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYBIfj2NwDSjIO3HTpqKUETdDpDDLf9y2uyicW1obTmGTjDfsqAFmjUVg/640?wx_fmt=png)在这里插入图片描述

*   服务方 S 向第三方机构 CA **提交公钥**、组织信息、个人信息 (域名) 等信息并申请认证；
    
*   CA 通过线上、线下等多种手段验证申请者提供信息的真实性，如组织是否存在、企业是否合法，是否拥有域名的所有权等；
    
*   如信息审核通过，CA 会向申请者签发认证文件 - 证书。  
    签名的产生算法：首先，**使用散列函数计算公开的明文信息的信息摘要，**然后，采用 CA 的**私钥对信息摘要进行签名；**
    

客户端：

*   客户端 C 向服务器 S 发出请求时，**S 返回证书文件；**
    
*   客户端 C 读取证书中的相关的明文信息，**采用相同的散列函数计算得到信息摘要**，然后，**利用对应 CA 的公钥解密签名数据**，
    
*   **对比证书的信息摘要（明文的信息摘要和签名解密后的一致）**，如果一致，则可以确认证书的合法性，即公钥合法；
    
*   客户端然后验证证书相关的域名信息、有效时间等信息；
    
*   客户端会**内置信任 CA 的证书**信息 (**包含公钥**)，如果 CA 不被信任，则找不到对应 CA 的证书，证书也会被判定非法。
    

在这个过程注意几点：

*   1. 申请证书不需要提供私钥，确保私钥永远只能服务器掌握；
    
*   2. 证书的合法性仍然依赖于非对称加密算法，证书主要是增加了服务器信息以及签名；
    
*   3. 内置 CA 对应的证书称为根证书，颁发者和使用者相同，自己为自己签名，即自签名证书；
    
*   4. 证书 = 网站公钥 + 申请者与颁发者信息 + 签名；
    

HTTPs 认证后的传输
------------

HTTPs 采用混合的加密机制，使用公开密钥加密用于传输对称密钥来保证安全性，之后使用对称密钥加密进行通信来保证效率。（下图中的 Session Key 就是对称密钥）

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYQTohpsNkxyToOUpbuCkMZpDRGS9teXESST93PMoE712myLmYIjpd7w/640?wx_fmt=png)在这里插入图片描述

完整性保护
-----

SSL 提供报文摘要功能来进行完整性保护。

HTTP 也提供了 MD5 报文摘要功能，但是却不是安全的。例如报文内容被篡改之后，同时重新计算 MD5 的值，通信接收方是无法意识到发生篡改。

HTTPs 的报文摘要功能之所以安全，是因为它结合了加密和认证这两个操作。试想一下，加密之后的报文，遭到篡改之后，也很难重新计算报文摘要，因为无法轻易获取明文。

HTTPs 的缺点
---------

*   因为需要进行加密解密等过程，因此速度会更慢；
    
*   需要支付证书授权的高费用。
    

GET 和 POST 的区别
==============

### 作用

GET 用于**获取资源**，而 POST 用于**传输实体主体。**

### 参数

*   GET 的传参方式相比于 POST 安全性较差，因为 GET 传的参数在 URL 中是可见的，可能会泄露私密信息。
    
*   并且 GET 只支持 **ASCII 字符**，因此 GET 的参数中如果存在**中文等字符就需要先进行编码**，例如中文会转换为 %E4%B8%AD%E6%96%87，而空格会转换为 %20。POST 支持标准字符集。
    

```
GET /test/demo_form.asp?name1=value1&name2=value2 HTTP/1.1

POST /test/demo_form.asp HTTP/1.1
Host: w3schools.com
name1=value1&name2=value2


```

*   **不能因为 POST 参数存储在实体主体中就认为它的安全性更高，因为照样可以通过一些抓包工具（Fiddler）查看。**
    

### 安全

**安全的 HTTP 方法不会改变服务器状态，也就是说它只是可读的。GET 方法是安全的，而 POST 却不是**

因为 POST 的目的是传送实体主体内容，这个内容可能是用户上传的表单数据，上传成功之后，服务器可能把这个数据存储到数据库中，因此状态也就发生了改变。

安全的方法除了 GET 之外还有：HEAD、OPTIONS。

不安全的方法除了 POST 之外还有 PUT、DELETE。

### 幂等性

幂等的 HTTP 方法，同样的请求被执行一次与连续执行多次的效果是一样的，服务器的状态也是一样的。

**GET，HEAD，PUT 和 DELETE 等方法都是幂等的，**

而 POST 方法不是。**所有的安全方法也都是幂等的。**

### 可缓存

*   请求报文的 HTTP 方法本身是可缓存的，包括 GET 和 HEAD
    
*   但是 PUT 和 DELETE 不可缓存，**POST 在多数情况下不可缓存的。**
    

### XMLHttpRequest

为了阐述 POST 和 GET 的另一个区别，需要先了解 XMLHttpRequest：

> XMLHttpRequest 是一个 API，它为客户端提供了在客户端和服务器之间传输数据的功能。它提供了一个通过 URL 来获取数据的简单方式，并且不会使整个页面刷新。这使得网页只更新一部分页面而不会打扰到用户。XMLHttpRequest 在 AJAX 中被大量使用。

在使用 XMLHttpRequest 的 POST 方法时，浏览器**会先发送 Header 再发送 Data**。

> 但并不是所有浏览器会这么做，例如火狐就不会。**而 GET 方法 Header 和 Data 会一起发送。**

关注我
===

我是蛮三刀把刀，目前为后台开发工程师。主要关注后台开发，网络安全，Python 爬虫等技术。

来微信和我聊聊：yangzd1102

Github：https://github.com/qqxx6661

### 原创博客主要内容

*   笔试面试复习知识点手册
    
*   Leetcode 算法题解析（前 150 题）
    
*   剑指 offer 算法题解析
    
*   Python 爬虫相关技术分析和实战
    
*   后台开发相关技术分析和实战
    

**同步更新以下博客**

**1. Csdn**

http://blog.csdn.net/qqxx6661

拥有专栏：Leetcode 题解（Java/Python）、Python 爬虫开发、面试助攻手册

**2. 知乎**

https://www.zhihu.com/people/yang-zhen-dong-1/

拥有专栏：码农面试助攻手册

**3. 掘金**

https://juejin.im/user/5b48015ce51d45191462ba55

**4. 简书**

https://www.jianshu.com/u/b5f225ca2376

### 个人公众号：Rude3Knife

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raajkpsKgSljhNG4kC9wMsYvVenkuvQHYWEnLqjRB0bxAdCkETqDt4P9kPgdxcKvgD8CsQhY4ibR3A/640?wx_fmt=png)个人公众号：Rude3Knife

**如果文章对你有帮助，不妨收藏起来并转发给您的朋友们~**