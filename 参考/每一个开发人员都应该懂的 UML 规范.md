> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s/KR2HCcCoIc-gSDLZ69azYw

点击上方**蓝色字体**，选择 “设置星标”

优质文章，第一时间送达

![](https://mmbiz.qpic.cn/mmbiz_jpg/ow6przZuPIENb0m5iawutIf90N2Ub3dcPuP2KXHJvaR1Fv2FnicTuOy3KcHuIEJbd9lUyOibeXqW8tEhoJGL98qOw/640?)

在团队协作过程中最常见的就是开会、开会最常用的就是图，而图中最常见的就是流程图、时序图、类图，这三个图可以清楚的描述你想解释的内容。学好类图不仅仅能帮助自己更清楚的梳理业务，还能提高开会效率。但是话说回来，你是否真的看懂别人画的图了？或者你真的会画吗？今天就和大家一起学习一下怎么画类图。

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclNJLQ4EcQlSadpkyhRUD6Op1iauWmATtfFrFAhUywPIlyabcUOc1fIuAUHZeMnJoAu0e8rmAGMM8UQ/640?wx_fmt=png)

上图是我模拟出来的一个场景和大家具体说一下类图究竟应该怎么画才对。图中讲的是这样一个「故事」：

一个公司下面有很多部门，公司和部门是不可分割的。

码农属于一个部门，但是如果部门解散了，码农依然依然是码农。

码农无时无刻在用手机，只有工作时间才会使用 Mac。

Mac 是一种电脑，电脑是 “可计算处理器” 的一种实现。

下面我们就逐一介绍下这些关系。

**泛化关系 (generalization)**

泛化关系为 is-a 的关系；两个对象之间如果可以用 is-a 来表示，就是泛化关系。

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclNJLQ4EcQlSadpkyhRUD6OpxJzrJrcUryhxrqtYBuwm91wkGUxwyQjFwhQ3TlpNPzyHcTXcw8H3FA/640?wx_fmt=png)

泛化关系用一条带空心箭头的直接表示。如图为例， Mac 和电脑就是泛化关系，通常在程序里面泛化表现为继承于非抽象类。

**实现关系 (realization)**

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclNJLQ4EcQlSadpkyhRUD6OpYzw2icvrIzl257eI4DUuKWPIrU7SYNjTkjicHJ93fcumibnLrtYDFhU2Q/640?wx_fmt=png)

实现关系用一条带空心箭头的虚线表示。如图为例，电脑和可计算处理器就是实现关系，通常程序里面实现关系表现为继承抽象类。比如我们平时写的 Readable,Printable 等接口的实现。

**聚合关系 (aggregation)**

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclNJLQ4EcQlSadpkyhRUD6OpZEZYDovIXTbQh47lZuiaDziafpu8D2ebzWnyQcuRhcRZAI6lLvibWqiaGQ/640?wx_fmt=png)

聚合关系用一条带空心菱形箭头的直线表示，如图码农和部门就是聚合关系。与组合关系不同的是，整体和部分不是强依赖的，即使整体不存在了，部分仍然存在。如图为例， 部门撤销了，码农换一个工作还是码农嘛。

**组合关系 (composition)**

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclNJLQ4EcQlSadpkyhRUD6OplPuCLQZvXwn0FKme51SYbFVIicK4OwdhSdzkQs5zPWCR64Ouic1HSJ3g/640?wx_fmt=png)

组合关系用一条带实心菱形箭头直线表示，与聚合关系一样，组合关系同样表示整体由部分构成的关系，不同之处在于整体和部分是强依赖关系，如果整体不存在了，部分也不复存在。如图为例，如果公司倒闭了，那么何来部门呢？

**关联关系 (association)**

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclNJLQ4EcQlSadpkyhRUD6OpsD0C94n08IdFPxL4D87ogHSxxZF4Nh2iaRAkpicvlSWRiajicrPzgQV85w/640?wx_fmt=png)

关联关系通常用一条直线表示，当然如果需要标明方向可以添加箭头。它是描书不同的类对象之间的关系，通常不会随着状态的变化而变化，可以理解为被关联者属于关联者的一部分。如图为例，手机就是码农的一部分，不会因为他上班与否而变化所属关系。通常情况在程序里面以类变量的方式表现。

**依赖关系 (dependency)**

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclNJLQ4EcQlSadpkyhRUD6OpJTTj8aDlECCsj3HPUYwN2ZlDh671apveSialmBGPvkicdLoTkQa37W4A/640?wx_fmt=png)

依赖关系是用一套带箭头的虚线表示，他通常描述一个对象在运行期间会用到另一个对象的关系。如图为例码农只有在工作的时候才会用到 Mac 电脑，所以这种依赖关系是依赖于运行状态的。通常情况下是在程序里面通过构造函数、形参等体现。

**回顾总结**

到这里我们再次回顾一下开篇的图片是不是更加理解里面的连线了？所以我们在绘图过程中一定要非常注意这个线和箭。

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclNJLQ4EcQlSadpkyhRUD6Op1iauWmATtfFrFAhUywPIlyabcUOc1fIuAUHZeMnJoAu0e8rmAGMM8UQ/640?wx_fmt=png)

不过话说回来，死记硬背这些东西确实很难，于是我自己想了一个打油诗，帮助自己来记忆，你也可以来试一下。

实箭泛化虚实现

虚线依赖实关联

空菱聚合实组合

项目沟通图常见

所以你学会了吗？

「小匠粉丝群」，小匠创建了一个「知识星球」里面有很多关于技术、求职、面试的困惑和经验，有兴趣的可以加入支持一下小匠。

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclMrG80ic9ibTDEEMsgiak3kDxEX6mkkP0Tic94FOc78zfthFu0Mbqvic65vmdLdib6R9EagMrjeb8TzlWwQ/640?wx_fmt=png)

往期精彩回顾

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclNpr4X0q31Ikh53jibntBeVJIfpYYMVeqHuiad8VoRicBXQCquxSDtKkke42s2LgFgrKzRbDhvpia6DRQ/640?wx_fmt=png)

1、[阿里社招面试指南](http://mp.weixin.qq.com/s?__biz=MzIyNzc1ODQ0MQ==&mid=2247483982&idx=1&sn=9639482044268f9639d13eaa503f98c1&chksm=e85d1a48df2a935eae354105da5d925bef6ec97e7e135f9f4bb6e63fd69e776ea9cdb2f243a7&scene=21#wechat_redirect)

2、[阿里应届生面试指南](http://mp.weixin.qq.com/s?__biz=MzIyNzc1ODQ0MQ==&mid=2247484046&idx=1&sn=c216d3f47742a462b139c45370aaf93c&chksm=e85d1a88df2a939e32bf11617bbb5b46300886d031d81e36b894b1b64af6275b62e3ab367d35&scene=21#wechat_redirect)

3、[探寻线程池是如何工作的](http://mp.weixin.qq.com/s?__biz=MzIyNzc1ODQ0MQ==&mid=2247484016&idx=1&sn=47d0849b1c2df456f26ff1f8747d77b5&chksm=e85d1a76df2a936064a61dbae1ad5dcc70f0aec770b65413f4634b42893862b9daacaf44024e&scene=21#wechat_redirect)

4、[跳槽的必备条件是有一份好的简历](http://mp.weixin.qq.com/s?__biz=MzIyNzc1ODQ0MQ==&mid=2247484021&idx=1&sn=a6df49fc0c74f250242fe450ceadbd7f&chksm=e85d1a73df2a936592c1d4d1206ae81d441a57b0cf56dabe093a2cb6cf83bdd06672cafd1cab&scene=21#wechat_redirect)

5、[不是所有的 Github 都适合写在简历上](http://mp.weixin.qq.com/s?__biz=MzIyNzc1ODQ0MQ==&mid=2247484035&idx=1&sn=fe938c697f4400bd0e132dde5c003938&chksm=e85d1a85df2a93938764547d20e0202b85799311fccb47ef77fa16381fdd56ad281a2dee417e&scene=21#wechat_redirect)

6、[所没有项目经验找工作处处碰壁怎么办](http://mp.weixin.qq.com/s?__biz=MzIyNzc1ODQ0MQ==&mid=2247484077&idx=1&sn=4c96929345b9bb589852a4b5ea80bfaa&chksm=e85d1aabdf2a93bd3554d4f22865b59d1fc6d5210c870bb820906af90ea5b4e26d483586356e&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_png/7B8iaauAfclMIicccCe5oibicrcfichSYlsfNU7qGLmopbr9Kkibc1g3YfkZ5ek6VhXgcyoEvJ9v09TS01s0aHibBWSWg/640?wx_fmt=png)