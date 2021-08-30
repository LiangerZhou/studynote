# Monkey(二)

通过 [APP 测试之 Monkey 测试（一）](https://mp.weixin.qq.com/s?__biz=MzI5MTg1NjA4Nw==&mid=2247485462&idx=1&sn=460376ec8687e8c5adadcc9811b9ab32&scene=21#wechat_redirect)，我们了解了 Monkey 是什么，Monkey 是如何实现对 APP 进行压力测试，也熟悉了 Monkey 基本的命令，今天将在之前的基础上进行补充和拓展，一起深入接触并掌握 Monkey，这之后，我们还将总结 APP 测试常见问题，不要错过哦！  

Monkey 命令组合及规范

Monkey 参数的约束限制规范：

1. 一个 -p 选项只能用于一个包，指定多个包，需要使用多个 -p 选项；  

2.-s <seed> 伪随机数生成器的 seed 值，如果用相同的 seed 值再次运行 monkey，它将生成相同的事件序列，对 9 个事件分配相同的百分比；

3.-c 用此参数指定一个或多个类别，同样，需要指定多个类别就需要多个 - c 参数；常见的类别有 Intent.category.LAUNCHER、Intent.category.MONKEY；

4.-v 命令行的每一个 - v 将增加反馈信息的级别：

Level 0 为一个 - v 的命令，除了启动的提示、测试完成和最终结果之外，提供较少的信息 ;  
Level 1 为两个 - v 的命令，提供较为详细的测试信息，如逐个发送到 Activity 的事件 ；  
Level 2 为三个 - v 的命令，提供更加详细的测试信息，如测试中被选中或未被选中的 Activity；

常见命令组合：  

1.monkey -p com.package -v 500 ：简单的输出测试的信息；

2.monkey -p com.package -v -v -v 500  ：以深度为三级输出测试信息；

3.monkey -p com.package --port 端口号 -v ：为测试分配一个专用的端口号，不过这个命令只能输出跳转的信息及有错误时输出信息；

4.monkey -p com.package -s 数字 -v 500 ：为随机数的事件序列定一个值，若出现问题下次可以重复同样的系列进行排错；

5.monkey -p com.package -v --throttle 3000 500 

: 为每一次执行一次有效的事件后休眠 3000 毫秒；

Monkey 测试参数建议

间隔时间：500 毫秒；  

种子数：随机；

遇到错误：不停止；

执行时长：每机型不小于 12 小时或点击次数：100 万次；

机型覆盖建议：覆盖高中低端机型

不同芯片平台（高通、海思、MTK 等）

不同分辨率（480*800 以上主流分辨率）

不同安卓版本（安卓 4.0 以上主流安卓版本）；

Monkey 参考命令

adb shell monkey -p com.tencent.XXX(替换包名) --throttle 500 --ignore-crashes --ignore-timeouts --ignore-security-exceptions --ignore-native-crashes --monitor-native-crashes -v -v -v 1000000>d:\monkeyScreenLog.log  

测试可以发现的问题

Android 平台应用程序可能产生以下四种 Crash：

App 层（JAVA 应用程序）：

1、Force Close Crash

2、ANR Crash

Native 层 (本地框架)：

3、Tombstone Crash(Native Crash)

Kernel 层 (LINUX 内核空间)：

4、Kernel Panic

Monkey 工具九个事件

`//Monkey工具随机事件类型（seed值）`

[--pct-touch PERCENT]  -0. 触摸事件（down-up 事件）；  

[--pct-motion PERCENT] -1. 动作事件（down-up 事件和伪随机事件组成）；

[--pct-trackball PERCENT] -2. 轨迹事件（随机移动、有时伴随点击）；

[--pct-nav PERCENT] -3. 基本导航事件（导航事件来自方向输入设备的 up、down、left、right 组成）；

[--pct-majornav PERCENT] -4. 主要导航事件（引发图形界面动作，如回退、菜单按键）；

[--pct-syskeys PERCENT] -5. 系统按键事件（这些按键通常被保留，由系统使用，如 Home、Back、Start Call、End Call 及音量控制键）；

[--pct-appswitch PERCENT] -6. 调整启动 Acticity 百分比（在随机间隔里，Monkey 将执行一个 startActivity() 调用，作为最大程度覆盖包中全部 Activity 的一种方法，从一个 Activity 跳转到另一个 Activity）；

[--pct-flip PERCENT]    -7. 调整键盘翻转事件的百分比

[--pct-anyevent PERCENT]-8. 其它类型事件（它包罗了所有其它类型的事件，如：按键、不常用的设备按钮等）

记录 monkey log 的值

adb shell monkey -p com.xxx.xxx 1000 > C:\monkey.txt

说明：  

1.ctrl + c 退出 shell 模式 然后 C:\XXX\XXXX>adb shell monkey -p 包名 -v 300 >e:\text.txt 注：进入 adb shell 后就相当于进入 linux 的 root 下面，没有权限在里面创建文件~

adb remount 获取 root 权限。

 1. 数字对应下面百分比对应的数字，比如下图中 0：15.0%，表示分配 --pct-touch 事件 15%，测试 100 次分配 15 次测试 down-up

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98ia89M3ED6icTTll2ygtQAE30wylAowakN7iaeJKb6oicqNnxxXRZIH9BceSbRAXOdjWeAROVW83qSDEg/640?wx_fmt=png)

3. 如果在 monkey 参数中不指定上述参数，这些动作都是随机分配的，9 个动作其每个动作分配的百分比之和为 100%，我们可以通过添加命令选项来控制每个事件的百分比，进而可以将操作限制在一定的范围内。

示例：我们先来看一下不加动作百分比控制，系统默认分配事件百分比的情况

命令：adb shell monkey -v -p com.tencent.WeChat 500

再看一下指定事件，控制事件百分比之后的情况

命令：adb shell monkey -v -p com.tencent.WeChat  --pct-anyevent 100 500

说明：--pct-anyevent 100 表明 pct-anyevent 所代表的事件的百分比为 100%

运行结果如下：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98ia89M3ED6icTTll2ygtQAE30dviacMz27lRwGLqnuUaVlvVjRmUsQVyxCcxXCpjhoKiad8OpzyDPXKeA/640?wx_fmt=png)

Monkey 日志定位问题

1 . 典型 Monkey 测试日志文件输出，如下：
--------------------------

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98g9Mzh6lycZml79q1WMJzlGjCvk4iaYjict8VDnphic8dReZ2mBGhVia2YDuScaF4RhkFNxKZNJKPZpUg/640?wx_fmt=png)

`//主要Log文件说明`

anr 目录：从手机 / data/anr 导出的日志，保存发生 anr crash 时的相关信息；

dontpanic 目录：从手机 / data/dontpanic / 导出的日志，保存发生 Kernel Panic 时的相关信息；

Tombstone 目录：从手机 / data/tombstones / 导出的日志，保存发生 Tombstone Crash 时的错误信息；

dropbox 目录：从手机 / data/tombstones / 导出的日志，经过 dropbox 服务截取的部分 tombstones 错误信息；

BugReportLog.log：通过 adb shell bugreport 命令提取的系统各种信息；

MonkeyScreenLog.Log：保存 Monkey 测试过程、应用层错误信息，发生 Native Crash 时，在此文件也会有记录；

2 . 通过日志定位问题步骤
--------------

（1） 在 MonkeyScreen.Log 日志文件搜索关键词 “Fatal”、“Crash”、“ANR” 定位到发生 Crash 的详细堆栈信息，或分析发生 Crash 前后的日志事件；

如图：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98g9Mzh6lycZml79q1WMJzlGZAQ349TtTR6cZ0cJEtiaTqBVKLmH4dgcibibZwxPcUaBY4akI6f4OLzvg/640?wx_fmt=png)

（2）检查 dropbox 目录下是否有相关 crash 日志信息，主要关注是否有以下 4 类 crash 错误信息：data_app_wtf，data_app_anr，data_app_crash，system_server_watchdog，如图：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98g9Mzh6lycZml79q1WMJzlGLILPlIiaWx3GL5cBQPrGc0bKnlvNyfzicf8iaKV4AkdycPib4Lm6QHcEfw/640?wx_fmt=png)

（3） 检查 tombstone 目录是否有生成日志，有的话说明发生过 native crash，如图：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98g9Mzh6lycZml79q1WMJzlGzCUusfib6q0dTCRib89UzpOxAJtEOE0tzjIfyn9pGoWiaia7oqAibuktpgA/640?wx_fmt=png)

（4）通过 anr 目录中的日志文件或 BugReport.log 日志文件，进一步分析问题原因；

（5）通过上述日志信息，结合代码分析或通过搜索引擎寻找对应问题的可能错误，定位解决问题。

Monkey 后台运行

作用：使得 android 系统的设备脱离 PC，独立运行 monkey 和记录 logcat 和 monkey 日志。

方法如下：

1. 连接设备和 PC；

2 .adb shell 命令进入命令行模式；

3. 输入 logcat 命令 (日志文件的地址使用> /sdcard/logcat_x.log)，之后回车，

如：logcat -v time >/sdcard/logcat_x.log

4 . 输入 monkey 命令 (日志文件的地址使用> /sdcard/monkey_x.txt)，回车；

如：monkey -p packageName --throttle 500 -v -v -v 1000000 >/sdcard/monkey.log 对于其余的 monkey 命令的参数可以自己添加

5 . 此时 monkey 开始运行，拔掉设备和 PC 的数据线；

6 . 同时需确认 logcat 和 monkey 的日志记录 ok（重新连接设备和 PC，通过 adb shell，进入 sdcard，使用 ls –l 查看，logcat 和 monkey 日志文件的大小不断变化即 ok）。