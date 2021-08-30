# ADB 常见错误

错误一

**在 cmd 中执行 “adb”，提示：adb 不是内部或外部命令，也不是可运行的程序。**

这意味着没有配置好 adb 环境。

解决办法：  

1、如果是 win10 系统的环境变量配置打开 path，

添加如下：

%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools;

（注意要先点击新建再分别在两行创建，并且不需要分号）

2、如果是 win7 系统打开 path，

添加如下：

%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools;

(直接粘贴到 path 变量值后面即可)

具体可参照：[速看，APP 测试之 ADB 最全指南！](https://mp.weixin.qq.com/s?__biz=MzI5MTg1NjA4Nw==&mid=2247485426&idx=1&sn=ac3831e1b064bee3cb1668d5deb866a9&scene=21#wechat_redirect)进行工具下载及安装。

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaWTYqvwR5sOmGE1FJK4b9qPGXlafBfwvZib6o8KLY22cXdIYpYQAyw41gTxI0gVpLuGUDbj2EfdVw/640?wx_fmt=png)

  

错误二

**在 cmd 中执行 “adb shell”，提示：error: device not found（****没有设备信息）**

 意思是没有发现设备。

解决方法：

1、如果用手机设备测试，请用 USB 数据线与电脑连接，同时安装好手机驱动（比如 91 助手、豌豆荚）；

2、如果采用模拟器测试，应该先启动 eclipse，然后设置好模拟器，并进入模拟器测试状态，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98ia89M3ED6icTTll2ygtQAE30ZFyhy2aLtibdKk7Gvibq3ZlBuricuHLSslicFCg7kwqK13Ske8rUtuCkqA/640?wx_fmt=png)

  

错误三

**执行 “adb shell”，提示：error: more than one device and emulator.**

> **第一种情况：确实用多个设备或者模拟器**
> 
> 错误说明是有一个以上的设备和仿真器，这是因为启用了模拟器，同时也将手机通过 USB 线连接到了电脑，所以这里存在两个设备。这种情况下，需要指定连接某一个设备或者模拟器。
> 
> 问题定位及解决办法：  
> 
> 1、获取模拟器或设备列表
> 
> 命令：adb devices
> 
> 效果如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaWTYqvwR5sOmGE1FJK4b9qtZkfUTOnUGnqIPibS2cj2Xh81iaT20WiaeeJDO7EVoDF0feV41dNrlDNQ/640?wx_fmt=png)

> 2、指定 device 来执行 adb shell
> 
> 命令：adb -s devicename shell
> 
> 在多个 device 的时候，执行 adb 命令一般都需要用参数 - s 指定 device。
> 
> 如卸载 emulator-5554 上的包 com.soft.camera，命令如下:
> 
> adb -s emulator-5554 uninstall com.soft.camera
> 
> 通过 monkey 测试:
> 
> adb -s emulator-5554 shell monkey -v -p com.tencent.WeChat 500

> **第二种情况：确实有一个设备或者模拟器**
> 
> 之所以显示有多个，是因为此设备用过两次，使用命令 adb devices 会看到这个设备有两种状：offline 和 device，有以下两种解决办法：
> 
> 1、重启 adb.exe 服务
> 
> 一般办法：在命令窗口输入 adb start-server 重新启动 adb.exe 服务；
> 
> 2、终极方法，结束 adb 进程
> 
> 如果上述方法不可以，终极方法：打开任务栏，选择 “进程” 选项卡，找到 adb.exe 进程，结束之，重新启动就 OK 啦（或者在命令窗口输入命令 adb kill-server ）。

  

错误四

**执行 “adb devices”，显示设备：unauthorized(未授权）.**

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaWTYqvwR5sOmGE1FJK4b9qXgnRBcibN0KZbOS2vYVErpJyzS4KcibTAWNvEWnXuMTiay9RIwZ5rgduA/640?wx_fmt=png)

> 原因：是因为在安卓端，尚未允许计算机对设备进行调试。
> 
> 解决办法：  
> 
> 1、手动点亮手机屏幕，会有相应的授权提示，在提示上，勾选 “一律允许”，并点击确定即可。
> 
> 如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaWTYqvwR5sOmGE1FJK4b9qZkRrGZmWscBFB4OnhJRGHR6EBfmt28t4vv696jYkCDiaArMGJP17KYQ/640?wx_fmt=png)

> 2、在手机屏幕上确认之后，再次输入指令：adb devices ，即可成功连接设备，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaWTYqvwR5sOmGE1FJK4b9qPGt8UAGqoaOxGnePgB8EHHncuVrF06wocR2D3ykCmwfu3hTfunDPnw/640?wx_fmt=png)  

  

错误五

**在 cmd 中输入：****adb -s HT21JV204550 shell monkey -v -p com.tencent.WeChat 500**

**，在手机上通过 monkey 测试出现：****No activities found to run, monkey aborted.**

> 意思是：没有找到要运行的 activities，monkey 中止。

> 解决措施：
> 
> 1、通过模拟环境测试，Eclipse 设置如下：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98ia89M3ED6icTTll2ygtQAE30hEQ4PSMF7hCAmWf89Twev8FTJiba4RugZZib9axHdTUoGYAcK89bibBibg/640?wx_fmt=png)

> 2、通过手机测试，应该将 “Target” 选项卡中由原来的 “Automatic” 项改为“Manual”，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98ia89M3ED6icTTll2ygtQAE30M79Licmu6sibTfddUWQGWWKsNF4ljsib0FG7Hks4VzkEM4cib6holed84g/640?wx_fmt=png)

> 3、然后选择 “Target” 选项卡的前面一个选项卡“Android”，点击下面的“Run”，执行 Run 后结果如下：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98ia89M3ED6icTTll2ygtQAE30GufVoOXI6l0icvP8Csf5KZL0Qibeh5WictGOBia3YRkDERw3W3cmmCkSFg/640?wx_fmt=png)

> 4、点击 OK，程序会将 APK 写入手机，这时就可以在 cmd 中输入命令：adb -s HT21JV204550 shell monkey -v -p com.tencent.WeChat 500 ，通过在真实手机环境下进行测试了。

  

错误六

**显示：****adb 端口被占用.**

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaWTYqvwR5sOmGE1FJK4b9q7OeXK3jq0rq5IERgoKHmicTLaSv9Oumicia6jwA2p2TZLayOKZD5BFIBw/640?wx_fmt=png)

> 解决办法：  
> 1、使用 netstat -ano | findstr 5037 查看 5037 端口对应的进程号（因为 5037 是 adb 的默认端口号）；
> 
> 2、taskkill  /f  /pid 进程号即可杀死，如下图所示，杀死 PID 为 9516 的进程：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaWTYqvwR5sOmGE1FJK4b9q54HPKomwibhTUsHb46cBQk2hl2DTSh5oUnUy2sxDriaYEDTQX6lT71zg/640?wx_fmt=png)
