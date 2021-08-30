# nc

全名叫 netcat，它可以用来完成很多的网络功能，譬如端口扫描、建立 TCP/UDP 连接，数据传输、网络调试等等，因此，它也常被称为网络工具的 **瑞士军刀** 。

使用方式

我们可以这样来使用它：

···
nc [-46DdhklnrStUuvzC] [-i interval] [-p source_port] [-s source_ip_address] [-T ToS] [-w timeout] [-X proxy_protocol] [-x proxy_address[:port]] [hostname] [port[s]]
···

常用选项：

*   -4：只使用 IPv4 地址
*   -6：只使用 IPv6 地址
*   -l：启动本地监听
*   -n：不使用 DNS 解析
*   -p：指定源端口
*   -s：指定源 IP 地址
*   -u：使用 UDP，默认是 TCP
*   -v：显示详细信息
*   -w：设定超时时间（只适合用在 Client 端）
*   -d：禁止从标准输入读取数据，也就是客户端输入数据不会发送到服务端
*   -k：让服务端保持连接，不断开

Linux 系统默认没有安装 nc，可以用下面的方法安装：

···
# centos
yum install nc
# ubuntu
apt-get install netcat
···

建立 C/S 聊天室[#](#3144547620)
--------------------------

nc 的本质是在两台机器之间建立连接，之后就可以基于这个连接做很多事情，数据传输是其中一个最为基本的。我们下面就使用 nc 来建立一个 C/S 的聊天室。

模拟 Server 端：

···
# -v ：输出详细信息
# -l ：指定监听地址和端口
nc -v -l 127.0.0.1 6000
···

模拟 Client 端：

···
# -p : 指定源端口
nc -v -p 5000 localhost 6000
···

之后，Client 和 Server 端就可以相互聊天了。

Client：

···
# nc -v -p 5000 localhost 6000
nc: connect to localhost port 6000 (tcp) failed: Connection refused
Connection to localhost 6000 port [tcp/x11] succeeded!
Hi, server
Hi, client
···

Server：

···
# nc -v -l 127.0.0.1 6000
Listening on [127.0.0.1] (family 0, port 6000)
Connection from [127.0.0.1] port 6000 [tcp/x11] accepted (family 2, sport 5000)
Hi, server
Hi, client
···

发送文件[#](#974107512)
-------------------

nc 不仅可以发送消息，还可发送文件。

假设服务端有一个 out.txt 的空文件，而客户端有一个 in.txt 文件，含有数据：`hello server`。

Server 端接收文件：

···
nc localhost 6000 > out.txt
···

Client 端发送文件：

···
nc localhost 6000 < in.txt
···

之后，我们可以看到 Server 端的 out.txt 文件中已经有数据了：

···
# cat out.txt
hello server
···

除了可以发送文件，nc 也可以发送目录，只需要将目录压缩发送即可。

支持 UDP 和 IPv6 连接[#](#1568205053)
--------------------------------

nc 默认使用 TCP 和 IPv4 协议建立连接，我们可以使用参数 `-u` 和 `-6` 参数来分别使用建立 UDP 和 IPv6 连接。

Server 端：

···
nc -u -6 -l localhost 6000
···

Client 端：

···
nc -u -6 localhost 6000
···

再开另一个终端，用 `lsof` 验证：

···
# lsof -Pni | grep nc
nc       1966            root    3u  IPv6  19317      0t0  UDP *:6000
nc       1976            root    3u  IPv6  19330      0t0  UDP [::1]:49900->[::1]:6000
···

可以看到，Client 端和 Server 端都显示使用了 UDP 和 IPv6 协议。

端口扫描[#](#1938767151)
--------------------

端口扫描是一个非常重要的功能，很多时候系统管理员会通过扫描服务器上端口，来识别系统中漏洞，nc 工具提供了非常方便的操作：

···
nc -vz 192.168.0.117 1-100
···

这条命令扫描 192.168.1.3 上 1-100 端口区间，有哪些端口是开放的。

···
# nc -vz 192.168.0.117 1-100
...
nc: connect to 192.168.0.117 port 21 (tcp) failed: Connection refused
Connection to 192.168.0.117 22 port [tcp/ssh] succeeded!
nc: connect to 192.168.0.117 port 23 (tcp) failed: Connection refused
...
···

可以看到，只有 22 号端口是开放的。

如果我们想扫描多个服务器上的多个端口是否开放，可以写一个脚本来完成，比如：

首先，用一个 `sip.txt` 保存所有服务器的地址：

···
# cat sip.txt
192.168.1.2
192.168.1.3
192.168.1.4
···

再用一个 `port.txt`保存要扫描的端口号：

···
# cat port.txt
···

然后，写一个脚本 `portscan.sh` 来遍历这个文件。

···
# vim portscan.sh
#!/bin/sh
for server in `more sip.txt`
do
for port in `more port.txt`
do
nc -zv $server $port
echo ""
done
done
···

给这个脚本赋予可执行权限：

···
chmod +x portscan.sh
···

运行这个脚本就可以自动扫描多个服务器的多个端口是否开放了。

···
# sh portscan.sh
Connection to 192.168.1.2 22 port [tcp/ssh] succeeded!
Connection to 192.168.1.2 80 port [tcp/http] succeeded!

Connection to 192.168.1.3 22 port [tcp/ssh] succeeded!
Connection to 192.168.1.3 80 port [tcp/http] succeeded!

Connection to 192.168.1.4 22 port [tcp/ssh] succeeded!
Connection to 192.168.1.4 80 port [tcp/http] succeeded!
···

总结[#](#1127726618)
------------------

nc 通过在两台机器之间建立连接来完成很多网络功能，数据传输、网络连接、端口扫描等，也有助于我们进行网络调试，排查网络故障。

后台回复 “加群”，带你进入高手如云交流群

> 我的公众号 **「Linux 云计算网络」(id: cloud_dev)** ，号内有 **10T** 书籍和视频资源，后台回复 **「1024」** 即可领取，分享的内容包括但不限于 Linux、网络、云计算虚拟化、容器 Docker、OpenStack、Kubernetes、工具、SDN、OVS、DPDK、Go、Python、C/C++ 编程技术等内容，欢迎大家关注。

> ![](https://img2018.cnblogs.com/blog/431521/201904/431521-20190415111037844-1058636985.png)