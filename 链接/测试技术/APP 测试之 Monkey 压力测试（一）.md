# Monkey

（一）Monkey 简介

Monkey 意指猴子，顽皮淘气。所以 Monkey 测试，顾名思义也就像猴子一样在软件上乱敲按键，猴子什么都不懂，就爱捣乱。Monkey 原理也是类似，通过向系统发送伪随机的用户事件流（如按键输入、触摸屏输入、滑动 Trackball、手势输入等操作），来对设备上的程序进行压力测试，检测程序多久的时间会发生异常。

Monkey 包括许多选项，它们大致分为四大类：

（1）基本配置选项，如设置尝试的事件数量;

（2）运行约束选项，如设置只对单独的一个包进行测试;

（3）事件类型和频率;

（4）调试选项;

（二）Money 原理

在 Monkey 运行的时候，它生成事件，并把它们发给系统。同时，Monkey 还对测试中的系统进行监测，对下列三种情况进行特殊处理（自动停止）：

(1) 如果限定了 Monkey 运行在一个或几个特定的包上，那么它会监测试图转到其它包的操作，并对其进行阻止；  

(2) 如果应用程序崩溃或接收到任何失控异常，Monkey 将停止并报错；

(3)如果应用程序产生了应用程序不响应 (application not responding) 的错误，Monkey 将会停止并报错；

按照选定的不同级别的反馈信息，在 Monkey 中还可以看到其执行过程报告和生成的事件。  

(1)Monkey 程序由 Android 系统自带，使用 Java 语言写成，在 Android 文件系统中的存放路径是：  

/system/framework/monkey.jar；

(2)Monkey.jar 程序是由一个名为 “monkey” 的 Shell 脚本来启动执行，shell 脚本在 Android 文件系统中的存放路径是：/system/bin/monkey；

(3) 通过在 cmd 窗口中执行: adb shell monkey ｛+ 命令参数｝来进行 Monkey 测试;

（三）Monkey 命令详解

关于环境，我们已经在之前搭建好了，还没搭建好的小可爱请参照：[速看，APP 测试之 ADB 最全指南](https://mp.weixin.qq.com/s?__biz=MzI5MTg1NjA4Nw==&mid=2247485426&idx=1&sn=ac3831e1b064bee3cb1668d5deb866a9&scene=21#wechat_redirect)， 根据文章介绍下载对应工具并完成工具安装，熟悉操作环境。  

查看 Monkey

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98g9Mzh6lycZml79q1WMJzlGnaF2XFWaqSZHBWcNgfmn0jzKQWIfiaNotlqQNuIt0FgPOicmSZOsQ5nw/640?wx_fmt=png)

在 system 的 bin 目录下可以看到 Monkey

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98g9Mzh6lycZml79q1WMJzlGh5dhbODeDyibxyjyk4cf7qJ3YVWgAUUpmxpwuFu0kQOR8vicJvBBOeHg/640?wx_fmt=png)

需要知道待测试 app 的包名，可以通过使用 “uiautomatorviewer.bat” 工具来获取，也可以直接询问提供 app 的开发小哥哥，或者直接使用 adb 命令获取包名。

以下简单介绍两种通过 adb 命令获取包名的方法。

方法一：首先要先打开手机中需要获取包名的 app，然后分别输入命令即可。

`//获取APP包名方法一`

adb shell

dumpsys activity | grep mFocusedActivity

如图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98g9Mzh6lycZml79q1WMJzlGykg8y6XUgXd6giaILpCfUjbh7R8t1Jj3wJdyC6icZMjMHjbZsU421GPQ/640?wx_fmt=png)

方法二：查看设备中所有的包，在 cmd 窗口中执行以下命令：

`//获取APP包名方法二`

adb shell  

 cd data/data

  ls

之后输入一些 Monkey 命令，就可以开始测试。

`//``获取Monkey命令自带的帮助，在cmd中执行命令：` 

adb shell monkey –help

`//边测试边打印log`

adb shell monkey -p com.tencent.WeChat –v 20000|logcat -v time>D:\log\1.txt

`说明：``用Monkey 测试时，为了方便分析问题，可以在命令monkey命令后面加上 |logcat -v time，这样就能边测试边打印Log并存到电脑本地`，但是以上组合在 monkey 测试完成后，logcat 仍然在执行，测试时需注意手动结束。

`//指定一个包``让Monkey程序模拟100次随机用户事件`

adb shell monkey -p com.tencent.WeChat 100

`说明：`参数 -p 用于约束限制，用此参数指定一个或多个包（即 App）。指定包之后，Monkey 将只允许系统启动指定的 APP；如果不指定包，Monkey 将允许系统启动设备中的所有 APP，com.tencent.WeChat 为包名，100 是事件计数。

`//指定多个包`

adb shell monkey -p com.tencent.WeChat -p com.tencent.QQ -p com.tencent.QQBrowser 100

`//不指定包`

adb shell monkey  100

`说明：`Monkey 随机启动 APP 并发送 100 个随机事件。

`//指定日志级别Level 0`

adb shell monkey -p com.tencent.WeChat –v 100  

`说明：日志级别``用于指定反馈信息级别（信息级别就是日志的详细程度），日志级别 Level 0 `，仅提供启动提示、测试完成和最终结果等少量信息。

`//指定日志级别Level 1`

adb shell monkey -p com.tencent.WeChat –v -v 100  

`说明：`日志级别 Level 1，提供较为详细的日志，包括每个发送到 Activity 的事件信息。 

`//指定日志级别Level 2`

adb shell monkey -p com.tencent.WeChat –v -v -v 100  

`说明：`日志级别 Level 2，提供最详细的日志，包括了测试中选中 / 未选中的 Activity 信息。

`//指定用户操作（即事件）间的时延`

adb shell monkey -p com.tencent.WeChat –throttle 3000 100  

`说明：throttle单位是毫秒。`

`//即使app崩溃，Monkey依然继续发送事件，直到事件数目达到目标值为止`

adb shell monkey -p com.tencent.WeChat  --ignore-crashes 1000  

`说明：``用于指定当应用程序崩溃时（Force& Close错误），Monkey是否停止运行。如果使用--``ignore-``crashes``参数，即使应用程序崩溃，Monkey依然会发送事件，直到事件计数达到1000为止。`

`//即使APP发生ANR，Monkey依然继续发送事件，直到事件数目达到目标值为止`

adb shell monkey -p com.tencent.WeChat  --ignore-timeouts 1000  

`说明：用于指定当应用程序发生ANR（Application No Responding）错误时，Monkey是否停止运行``如果使用--ignore-timeouts``参数，`即使应用程序发生 ANR 错误，Monkey 依然会发送事件，直到事件计数完成。

`//APP发生``许可证书错误时，Monkey依``然继续发送事件，直到事件数目达到目标值为止`

adb shell monkey -p com.tencent.WeChat --ignore-security-exceptions 1000  

`说明：`用于指定当应用程序发生许可错误时（如证书许可，网络许可等），Monkey 是否停止运行。如果使用 --ignore-security-exceptions 参数，即使应用程序发生许可错误，Monkey 依然会发送事件，直到事件计数完成。

`//A``PP发生错误时，应用程序停止运行并保持在当前状态`

adb shell monkey -p com.tencent.WeChat  --kill-process-after-error 1000  

说明：用于指定当应用程序发生错误时，是否停止其运行。如果使用 --kill-process-after-error 参数，当应用程序发生错误时，应用程序停止运行并保持在当前状态（注意：系统并不会结束该应用程序的进程）。

`//``监视并报告Android系统本地代码的崩溃事件`

adb shell monkey -p com.tencent.WeChat  --monitor-native-crashes 1000  

`//调整触摸事件的百分比`

adb shell monkey -p com.tencent.WeChat --pct-touch 10 1000  

说明：--pct-｛+ 事件类别｝｛+ 事件类别百分比｝用于指定每种类别事件的百分比（在 Monkey 事件序列中，该类事件数目占总事件数目的百分比），--pct-touch ｛+ 百分比｝用于调整触摸事件的百分比 (触摸事件是一个 down-up 事件，它发生在屏幕上的某单一位置)。

`//``调整动作事件的百分比`

adb shell monkey -p com.tencent.WeChat --pct-motion 20 1000  

说明：调整动作事件的百分比 (动作事件由屏幕上某处的一个 down 事件、一系列的伪随机事件和一个 up 事件组成)。

`//``调整轨迹事件的百分比`

adb shell monkey -p com.tencent.WeChat --pct-trackball 30 1000  

说明：调整轨迹事件的百分比 (轨迹事件由一个或几个随机的移动组成，有时还伴随有点击)。

`//``调整“基本”导航事件的百分比`

adb shell monkey -p com.tencent.WeChat --pct-nav 40 1000  

说明：调整 “基本” 导航事件的百分比 (导航事件由来自方向输入设备的 up/down/left/right 组成)。

`//调整主要导航事件的百分比`

adb shell monkey -p com.tencent.WeChat --pct-majornav 50 1000  

说明：调整主要导航事件的百分比 (这些导航事件通常引发图形界面中的动作，如：5-way 键盘的中间按键、回退按键、菜单按键)。

`//``调整系统按键事件的百分比`

adb shell monkey -p com.tencent.WeChat --pct-syskeys 60 1000  

说明：调整系统按键事件的百分比 (这些按键通常被保留，由系统使用，如 Home、Back、Start Call、End Call 及音量控制键)

`//调整启动Activity的百分比`

adb shell monkey -p com.tencent.WeChat --pct-anyevent 100 1000  

说明：调整其它类型事件的百分比，它包罗了所有其它类型的事件，如：按键、其它不常用的设备按钮等。

`//``指定多个类型事件的百分比`

adb shell monkey -p com.tencent.WeChat --pct-anyevent 50 --pct-appswitch 50 1000  

注意：各事件类型的百分比总数不能超过 100%。

例如：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98g9Mzh6lycZml79q1WMJzlGzHmvcYU7IvYs97yIsCM5h0unAPZIQvX8BTfT3Jq1Sb2QeOPicXt028Q/640?wx_fmt=png)  

Monkey 测试完成：  

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98g9Mzh6lycZml79q1WMJzlGibXiakIczLyf0iaxpXWLxiaE4lnDumiazeVsgHzrOibtWRnzPmADS1qO2kHg/640?wx_fmt=png)
