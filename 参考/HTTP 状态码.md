> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://github.com/reng99/blogs/issues/23

[![](https://camo.githubusercontent.com/7e0b94b06af01d2b5cd294fac0316cbb994494e0/68747470733a2f2f696d672d66652e676777616e2e636f6d2f696d616765732f39633663383238643437643532622d383030783430302e6a7067)](https://camo.githubusercontent.com/7e0b94b06af01d2b5cd294fac0316cbb994494e0/68747470733a2f2f696d672d66652e676777616e2e636f6d2f696d616765732f39633663383238643437643532622d383030783430302e6a7067)

作为一个 web 开发工程师，我们平时都会和诸如`200, 304, 404, 501`等状态码打交道，那么它们是什么意思呢？今天，我们来聊聊~

### 什么是 HTTP 状态码

**HTTP 状态码**是服务端返回给客户端（因为这里是 web 开发，这里的客户端指浏览器客户端）的`3位数字代码`。

这些状态码相当于浏览器和服务器之间的对话信息。它们相互沟通两者之间的事情是正常运行了还是运行失败了或者发生了一些其他的事情（如 Continue）。了解状态码有助于你快速的诊断错误，减少网站的停机时间等等。

### 状态码分类

状态码共分为五类，以 1-5 数字开头进行标识，如下：

*   **1xxs - 信息性**：服务器正在处理请求。
*   **2xxs - 成功信息**：请求已经完成，服务器向浏览器提供了预期的响应。
*   **3xxs –重定向**：你的请求被重定向到了其他地方。服务器收到了请求，但是有某种重定向。
*   **4xxs – 客户端错误**：客户端发生错误，导致服务器无法处理请求。
*   **5xxs – 服务端错误**：客户端发出了有效的请求，但是服务器未能正确处理请求。

> 备注：3xxs 类中的 304 是个奇葩，其不属于重定向信息提示，这个后面会讲到

**HTTP 状态码**大体的内容已经了解了，但是在具体的工作中，要用到具体的状态码，我们下面来展开说明下各自的一些状态码和工作中常用到的那些状态码🐱

#### 1xxs 状态码

*   **100 Continue**：表明目前为止，所有的请求内容都是可行的，客户端应该继续请求，如果完成，则忽略它。
*   **101 Switching Protocol**：该状态码是响应客户端`Upgrade`标头发送的，并且指示服务器也正在切换协议。
*   **103 Early Hints**：主要用于与`Link`链接头一起使用，以允许用户代理在服务器仍在准备响应时开始预加载资源。

> 备注：在 web 开发的工作中，我们都会使用封装好的库进行接口请求，而且浏览器的控制台网络中也不会出现这类状态码的提示（我没看到过😢），所以这一大类基本不会接触到，了解一下即可。

#### 2xxs 状态码

*   **200 OK**：请求成功。成功的含义取决于 HTTP 方法：
    *   `GET`：资源已被提取并在消息正文中传输。
    *   `HEAD`：实体标头位于消息正文中。
    *   `POST`：描述动作结果的资源在消息体中传输。
    *   `TRACE`：消息正文包含服务器收到的请求信息。（方法不安全，一般不用）

说到了 HTTP 的方法，可以戳 [HTTP 请求方法](https://www.runoob.com/http/http-methods.html)这个解析教程来了解一下。

*   **201 Created**：请求已经成功，并因此创建了一个新的资源。这通常是在`PUT`或`POST`请求之后发送的响应。
*   **202 Accepted**：请求已经接收到，但是没有响应，没有结果。意味着不会有一个异步的响应去表明当前请求的结果，预期另外的进程和服务去处理请求，或者批处理。
*   **204 No Content**：服务器成功处理了请求，但不需要返回任何实体内容，并且希望返回更新了的元信息。遇到`复杂请求`时候，浏览器会发送一个`OPTION`方法进行预处理返回响应。

关于复杂请求和简单请求，可以参考这篇文章 [CORS 非简单请求](https://mabiao8023.github.io/2018/03/30/CORS%E9%9D%9E%E7%AE%80%E5%8D%95%E8%AF%B7%E6%B1%82/)。

*   **205 Reset Content**：服务器已经成功处理了请求，但是没有返回任何内容。与 204 响应不同，返回此状态码的响应要求请求者重置文档视图。

> 备注：使用的最多的 2xxs 状态码是 200 和 204，在遇到 204 状态码的时候，要注意一下自己发的请求是不是复杂请求。如果是复杂请求，那么在得到 204 返回时，浏览器有没有接受了这个请求的返回，如果没有，要叫后端搞下相关配置了。

#### 3xxs 状态码

上文已经提到过，这一大类是提示`重定向`，可是有一个奇葩 --304，它并不是表示重定向的信息提示，而是表示`资源未被更改`。至于为什么会被放在这个分类里面，真不知道～（看官知道的话补充下啦）👏

*   **301 Moved Permanently**：被请求的资源已`永久`移动到新位置，并且将来任何对此资源的引用都应该使用响应返回的若干个 URI 之一。
*   **302 Found(Previously "Moved temporarily")**：请求的资源现在`临时`从不同的 URI 响应请求。由于这样的重定向是临时的，客户端应当继续向原有地址发送以后的请求。只有在`Cache-Control`或`Expires`中进行了指定的情况下，这个响应才是可缓存的。
*   **303 See Other**：对当前的请求的响应可以在另一个 URI 上被找到，而且客户端应该采用`GET`的方式访问那个链接。这个方法的存在主要是为了允许由脚本激活的 POST 请求输出重定向到一个新的资源。
*   **304 Not Modified**：如果客户端发送了一个带条件的 GET 请求且该请求已被允许，而文档的内容（自上次访问以来或者根据请求的条件）并没有改变，则服务器应当返回这个状态码。304 响应禁止包含消息体，因此始终以消息头后的第一个空行结尾。请求的时候一般结合`If-Modified-Since`头部使用。
*   **307 Temporary Redirect**：307 的意义如上 302。与历史上 302 不同的是`在重新发出原始请求时不允许更改请求方法`。比如，使用 POST 请求始终就该用 POST 请求。

> 备注：307 和 303 已经替代了历史上的 302 状态码，现在看到的临时重定向的状态码是 307。详细内容可到维基百科上查看。

#### 4xxs 状态码

*   **401 Unauthorized**：这意味着你的登录凭证无效。服务器不知道你是谁，这时，你需要尝试重新登录。
*   **403 Forbidden**：服务器已经理解请求，但是拒绝执行它。与 401 不同，403 知道是你登录了，但是还是拒绝了你。
*   **404 Not Found**：请求失败，你请求所希望得到的资源未在服务器上发现。
*   **405 Method Not Allowed**：请求行中指定的请求方法不能被用于请求相应的资源。
*   **410 Gone**：被请求的资源在服务器上已经不再可用，而且没有任何已知的转发地址。
*   **422 Unprocessable Entity**：请求格式良好，但是由于语义错误而无妨遵循。这时候要检查下自己的传参格式语义有没有正确了。
*   **429 Too Many Requests**：用户在给定的时间内发送了太多请求（“限制请求速率”）。在 DDOS 攻击中就可以使用到了。

> 备注：这里要注意的是 422，别请求链接一出错，就屁颠屁颠的找后端，先看下后端给过来的 API 文档中，要传的字段是否都准确跟上了。😂

#### 5xxs 状态码

*   **500 Internal Server Error**：服务器内部错误，服务器遇到了不知道如何处理的情况。比如后端同学写错了 model 啥的～
*   **503 Service Unavailable**：服务器没有准备好处理请求。常见的原因是服务器因维护或重载而停机。
*   **504 Gateway Timeout**：网关超时，服务器未能快速的做出反应。请求接口返回 pedding 时间过长基本就是这个问题了，囧。

> 备注：遇到这类的问题，去问后端同学吧。语气好点啦，毕竟大家都是为了生活😄

### 后绪

以上就是今天整理的内容。嗯～，对了，各个浏览器对此的支持度`very good`。更加详细内容啥的，可以直接戳我下面的参考。在日常的 web 工作中，明白 HTTP 状态码是一个必备的活，起码在出错的时候，知道浏览器和服务器的交流障碍在哪里啦～

### 参考

*   [HTTP Status Codes](https://moz.com/learn/seo/http-status-codes)
*   [HTTP Explained: The HTTP Request Status Code Guide (Complete)](https://www.clickminded.com/http-status-codes/)
*   [HTTP 响应代码](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status)