> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://segmentfault.com/a/1190000005644111

只有一台 linux 主机，但却想要搭建一套 zookeeper 集群的环境。
可以使用伪集群模式来搭建。
伪集群模式本质上就是在一个 linux 操作系统里面启动多个 zookeeper 实例。
这些不同的实例使用不同的端口，配置文件以及数据目录。

## 创建独立的目录

创建三个目录，隔离开 3 个 zookeeper 实例的数据文件，配置文件：

```
[beanlam@localhost ~]$ mkdir zk1
[beanlam@localhost ~]$ mkdir zk2
[beanlam@localhost ~]$ mkdir zk3
```

然后，再分别为每个目录创建一个数据目录，用来存放数据以及 id 文件

```
[beanlam@localhost ~]$ mkdir zk1/data
[beanlam@localhost ~]$ mkdir zk2/data
[beanlam@localhost ~]$ mkdir zk3/data
```

## 指定 id

zookeeper 启动的时候，会在它的数据目录下寻找 id 文件，以便知道它自己在集群中的编号。

```
[beanlam@localhost ~]$ echo 1 > zk1/data/myid
[beanlam@localhost ~]$ echo 2 > zk2/data/myid
[beanlam@localhost ~]$ echo 3 > zk3/data/myid
```

## 修改配置文件

这 3 个实例，每个实例都会使用不同的配置文件启动。
配置示例如下：

```
# The number of milliseconds of each tick
tickTime=2000

# The number of ticks that the initial
# synchronization phase can take
initLimit=10

# The number of ticks that can pass between
# sending a request and getting an acknowledgement
syncLimit=5

# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just
# example sakes.
dataDir=/home/beanlam/zk1/data

# the port at which the clients will connect
clientPort=2181

server.1=127.0.0.1:2222:2223
server.2=127.0.0.1:3333:3334
server.3=127.0.0.1:4444:4445
```

这是第一个实例的配置，z1.cfg。把这份配置文件放置在 zk1 / 目录下。
同理，第二个和第三个实例的配置分别为 z2.cfg 和 z3.cfg。和第一个实例一样，放在相同的位置。
唯一不同的是，clientPort 必须修改一下，z1.cfg 为 2181，z2.cfg 和 z3.cfg 不能也是 2181，必须彼此不同，比如 2182 或者 2183。

配置文件最底下有一个 server.n 的配置项，这里配置了两个端口，却一种第一个用于集群间实例的通信，第二个用于 leader 选举。
至于 2181，用于监听客户端的连接。

## 启动和连接

按照以下方式，依次启动 3 个实例：

```
[beanlam@localhost ~]$ cd zk1
[beanlam@localhost zk1]$ ~/zookeeper-3.4.8/bin/zkServer.sh start-foreground ./z1.cfg
```

启动第一个和第二个实例的时候会有报错信息，因为其它实例还没启动完全，连接无法建立的原因，可以直接忽略。
启动完 3 个实例后，会发现其中有一个是 leader，另外两个是 follower。可观察输出信息。

接下来启动一个客户端去进行连接：

```
[beanlam@localhost ~]$ ~/zookeeper-3.4.8/bin/zkCli.sh -server 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183
```

可以看到，客户端连接上了刚才启动的三个实例中的其中一个。

![](https://segmentfault.com/img/remote/1460000018839155)