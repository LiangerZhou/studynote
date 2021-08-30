# ADB

今天介绍 ADB 环境搭建及常用的 ADB 命令

ADB 全称 Android Debug Bridge, 是 android sdk 里的一个工具, 目的是起到调试桥的作用，用这个工具可以直接操作管理 android 模拟器或者真实的 andriod 设备 (手机)，是客户端测试常用的辅助工具。

借助 adb 工具，我们可以管理设备或手机模拟器的状态，还可以进行很多终端操作，如安装软件、卸载软件、系统升级、运行 shell 命令等，让用户在电脑上对手机进行全面的操作、记录终端操作日志。

而且 adb 可以跨平台，也就意味着在不仅在 windows，在 mac、linux 平台都可以来使用 adb 命令，我们只需要下载配置对应平台软件包即可，可以说给我们测试 APP 帮助大大滴有，我们今天主要介绍在 windows 平台下操作 android 设备。

ADB 作为一个客户端 / 服务器架构的命令行工具，主要由 3 个部分组成。  

（1）Adb clent（客户端）：可以通过它对 Android 应用进行安装、卸载及调试。

（2）adb service（服务器）：管理客户端到 Android 设备上 abd 后台进程的连接。  

（3）adb daemon（守护进程）：运行在 Android 设备上的 adb 后台进程。

JDK 以及 ADB 环境搭建

在使用 ADB 命令之前我们需要准备好相关的环境，以下介绍环境搭建，包括 JDK 环境搭建以及 ADB 环境搭建，已经搭建好的小可爱可略过。

安装及配置 JDK 环境：安装 JDK - 配置环境变量 - 检查是否安装成功。

**一、下载安装 JDK**

1. 找到与电脑的系统位数对应的 JDK 版本安装包（官网下载地址：https://www.oracle.com/technetwork/java/javase/downloads/index.html，选取自己电脑所对应 32 位或 64 位版本，也可以在微信公众号后台回复：20191004）

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3X7Vu8a8RJjfXGDglc83X8u2MkSECUzvMCjC92LmCvqaUM2yrQpvZ4Q/640?wx_fmt=png)

coco 准备的是 JDK 1.7  

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3vqKtuvxJSHkeiaT2KyZKyt3byiaHSqnFKmJ28t1DRkicibBWibIibyoyDW9A/640?wx_fmt=png)

2. 右键选择 “打开”> 点击“下一步”

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr32MvruORWD7Vw3eQFjZ4pLic3pdGKWEb4NdBTs4xpyl6UKd5kZchjo3w/640?wx_fmt=png)

3. 默认安装到 C 盘

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3ByqOsLTlbZKM6pewV55U0zp1n0jalTsiaaulepfsVq9Q8eAfMwC8chA/640?wx_fmt=png)

4. 默默等待 JDK 安装，出现此页表示安装成功。 

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3Q3KzW73DBTNukzv91VGvmcgDqzEjjic53bGvmkbfNgVGYreF15dpldA/640?wx_fmt=png)

5. 配置环境变量

（1）去到配置环境变量的位置：计算机 > 控制面板 > 系统 > 高级系统设置 > 环境变量：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3UBkHkLkiabhdl03RzU0icB7DTIicmctzxNC6QWS38VQ1Q090acVcPylKw/640?wx_fmt=png)

（2）找到自己的 JKD 安装位置： 

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3Bp4tOlR4L4AxyL4xnun9bNmbE5GOvz47uUDRiblynJZWwTvqX2IEwYQ/640?wx_fmt=png)

（3）新建 JAVA_HOME 系统变量：选择 “系统变量” 下方的“新建”，填写：

变量名：JAVA_HOME

变量值：C:\Program Files\Java\jdk1.7.0_55（直接复制路径）

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3ibibY9cCu3XaB0IN30fdCMQkwrDwBmeRczBtwV17zgEhmW0140B7RBiaw/640?wx_fmt=png)

（4）寻找 path 变量进行编辑：在变量值最后输入 %JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

（注意看原来 Path 的变量值末尾有没有; 号，如果没有, 先输入英文的; 号再输入上面的代码）

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3xWG9pWl8lqHJicMoIeMibFlBPibEgUvbByBlTlzPFVkMUWTXeOLur6X6w/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3cIhia3TP21nfhN8EgmmF1lx76QicVtoIKqicgKIZCGLMlF728sXicbrkiaQ/640?wx_fmt=png)

（5）新建 CLASSPATH 变量：选择 “系统变量” 下方的 “新建”，填写> 点击“确定”：

变量名：CLASSPATH

变量值：

.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3cr4OT4ibmyP3DyeMxe4QAywlG9mzLhmMUjogDHrhPAyDbX7NzDEOCRw/640?wx_fmt=png)

（6）变量值填写： 

.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar（注意最前面有一点），系统变量配置完毕，点击 “确定”>“确定” 即可。

6. 检验是否配置成功 运行 cmd 输入 java -version （java 和 -version 之间有空格）

若如图所示显示版本信息则说明安装和配置成功，恭喜你成功安装 JDK！

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3SYkyhsmy5evJV7W8K6ibvKHkh1QyWf3hooCIDa0wHpniaicBo1J6U3tJw/640?wx_fmt=png)

**二、安装 ADB 及配置环境**

安装及配置 ADB 环境：下载 ADB 工具 - 配置环境变量 - 检查是否安装成功（ADB 工具可在微信公众号后台回复：20191004 进行下载）

1. 下载后将 platform-tools.zip 解压，如下：

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3Mdgxdb9Eg3oI0jImOddU8qh0SZL8icKuKZ8mj3fWIH49D5qkkQ7GoLQ/640?wx_fmt=png)

2. 在系统变量中新建 ADB

变量名：ADB

变量值：E:\platform-tools\platform-tools（直接复制你的 ADB 解压路径）

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3YBGRd9Dnq1o9yONSaaVrzY71YZ5PGPiaGNwJ9wF7kegqncpD06kr5bg/640?wx_fmt=png)

3. 在 Path 变量中追加一句;%ADB%，之后点击确定保存即可。

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3wrVNlthceumgEyl04zwjHkUzaMw35euwYpribaiaOw29uYC77fg1jNMA/640?wx_fmt=png)

4. 检验是否配置成功 ：运行 cmd 输入 adb ，恭喜安装成功！  

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr3oqpNV6AgWvsPibTjI5tBJvibn7WO9NhE45jvK6qrbicRx6GtFluo9VM7Q/640?wx_fmt=png)

5. 自己的安卓手机通过 USB 线连接电脑：

(1) 确保手机已经通过 USB 线连接到电脑；

(2) 通过驱动软件，驱动精灵 / 驱动人生等，将 android 手机驱动安装好；

(3) 进入到手机，找到设置 > 关于手机 > 点击版本号 > 将对应的开发者选项打开（注意：由于手机型号不同，如小米，魅族，华为可能打开方式稍微有差异，如果不清楚，百度搜索手机型号对应的开发者选项）；

(4) 退出到上一级菜单，进入到开发者选项，找到 USB 调试模拟器将其打开；

(5) 最后，一般会出来一个确认调试的对话框，也需要勾选上，不然 adb 命令用不了；

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr35Eb2FyqX273gscFMpBdVaMFvA9BGSeSJSUs1zfsmtynhZmzMicnjzBw/640?wx_fmt=png)

(6) 在 cmd 运行窗口输入命令：“adb devices”，显示已连接的设备信息。

![](https://mmbiz.qpic.cn/mmbiz_png/9RdLdzUL98iaX3ia4WkoOrxAzvLicgskQr39LLeCGyDQq7UK24Y5MoTnFE1nLZlzRX4XvBPwFgu8Nic3dIjST7bibhw/640?wx_fmt=png)

7. 使用安卓模拟器连接电脑

（1）Android 模拟器和真实的 android 设备连接是有区别的，如果使用的是 genymotion 模拟器，在模拟器已经设置了，所以 genymotion 模拟器会自动帮你加载连接 adb，我们直接通过 adb devices 命令就可以检测到。

![](https://mmbiz.qpic.cn/mmbiz_jpg/JbrCrRbE0AZzUEnYyE1YF6JiaRQa1ibjJoZJmib84cC1MCicnEFyMQ8973whdQlP4pYFvBdZNF8YRu00Gzb2OvuwMg/640?wx_fmt=jpeg)

(2) 如果使用的是夜神 / 海马玩等模拟器，不会自动帮你连接上，所以需要我们手动通过命令 adb connect 连接。如夜神就是 adb connect 127.0.0.1:62001

（这里的 127.0.0.1 表示本地的 ip 地址，任何电脑都有这个地址，62001 表示夜神模拟器的端口号）。  

(3) 如果用的是海马玩模拟器的话，那么端口号改成 26944 即可。

![](https://mmbiz.qpic.cn/mmbiz_png/JbrCrRbE0AZzUEnYyE1YF6JiaRQa1ibjJoMIS6micN1EB5o6Vjbm8Aa3Wqibjj56sd1hDuAiahGzic8HFzWd1foUeG9g/640?wx_fmt=png)

**ADB 常用命令**

注：adb 使用的端口号是 5037，以下总结工作中常用到的 adb 命令。

1. 查看帮助手册列出所有的选项说明及子命令：

adb help

2. 获取设备列表及设备状态：

adb devices  

3. 获取设备的状态，设备的状态有 device , offline , unknown3 种，其中 device：设备正常连接，offline：连接出现异常，设备无响应，unknown：没有连接设备。

adb get-state

4. 结束和启动 adb 服务：adb kill-server /adb start-server , 结束 adb 服务 / 启动 adb 服务，通常两个命令一起用，设备状态异常时使用 kill-server，运行 start-server 进行重启服务。

adb kill-server

adb start-server

5. 打印及清除系统日志：adb logcat , 打印 Android 的系统日志 ；adb logcat -c, 清除日志。  

adb logcat

adb logcat -c

6. 生成 bugreport 文件：adb bugreport , 打印 dumpsys、dumpstate、logcat 的输出，也是用于分析错误，输出比较多，建议重定向到一个文件中，如 adb bugreport > d:\bugreport.log

adb bugreport

7. 安装应用：adb install , 安装应用，adb install -r 重新安装。

adb install

adb install -r

8. 卸载应用：adb uninstall , 卸载应用，后面的参数是应用的包名，区别于 apk 文件名

adb uninstall 

9. 将 Android 设备上的文件或者文件夹复制到电脑本地：adb pull , 如复制 Sdcard 下的 pull.txt 文件到 D 盘：adb pull sdcard/pull.txt d:\，重命名：adb pull sdcard/pull.txt d:\rename.txt

adb pull 

10. 推送本地文件至 Android 设备：adb push , 如推送 D 盘下的 push.txt 至 Sdcard：adb push d:\push.txt sdcard/   注意 sdcard 后面的斜杠不能少。

adb push

11. 重启 Android 设备：adb reboot ,  adb reboot recovery，重启到 Recovery 界面；   adb reboot bootloader，重启到 bootloader 界面。

adb reboot

adb reboot recovery

adb reboot bootloader

12. 获取 root 权限：adb root , adb remount, 可以直接获取 root 权限，并挂载系统文件系统为可读写状态。

adb root

adb remount

13. 返回设备序列号 SN 值：

adb get-serialno

14. 获取设备的 ID：

adb get-product

15. 进入设备 shell：

adb shell

16. 列出所有的应用的包名：

adb shell pm list package

17. 截屏并保存至 sdcard 目录：

adb shell screencap -p /sdcard/screen.png 

18. 录制视频并保存至 sdcard：adb shell screenrecord sdcard/record.mp4, 执行命令后操作手机，ctrl + c 结束录制，录制结果保存至 sdcard：

adb shell screenrecord sdcard/record.mp4

19. 获取设备分辨率：adb shell wm size

adb shell wm size

20. 列出指定应用的 dump 信息：adb shell pm dump 包名

adb shell pm dump

21. 列出对应包名的 .apk 位置：adb shell pm path 包名

adb shell pm path 

22. 查看当前终端中的进程信息：adb shell ps

adb shell ps

23.monkey 测试：adb shell monkey –p 程序包 –v 测试次数 , 比如 “adb shell monkey –p com.htc.Weather –v 20000” 意思是对 com.htc.Weather 这个程序包单独进行一次 20000 次的 monkey 测试

adb shell monkey –p 程序包 –v 测试次数

24. 显示所有程序包：

adb shell ps | grep [process]

25. 根据进程 pid 或包名查看进程占用的内存

adb shell dumpsys meminfo<pid>

adb shell dumpsys meminfo<package_name>

以上这些 ADB 命令在 APP 面试中被问的可能性以及工作中要用的可能性不言而喻，大家多敲敲键盘就可以掌握了。
