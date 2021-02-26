# FastDFS搭建

FastDFS 是用 c 语言编写的一款开源的分布式文件系统。FastDFS 为互联网量身定制，充分考虑了冗余备份、负载均衡、线性扩容等机制，并注重高可用、高性能等指标，使用 FastDFS 很容易搭建一套高性能的文件服务器集群提供文件上传、下载等服务。

FastDFS 架构包括 Tracker server 和 Storage server。客户端请求 Tracker server 进行文件上传、下载，通过 Tracker server 调度最终由 Storage server 完成文件上传和下载。

Tracker server 作用是负载均衡和调度，通过 Tracker server 在文件上传时可以根据一些策略找到 Storage server 提供文件上传服务。可以将 tracker 称为追踪服务器或调度服务器。

Storage server 作用是文件存储，客户端上传的文件最终存储在 Storage 服务器上，Storageserver 没有实现自己的文件系统而是利用操作系统 的文件系统来管理文件。可以将 storage 称为存储服务器。

![https://oscimg.oschina.net/oscnet/64d3a09cfd91b322e8d42c8e591ac566dcc.jpg](https://oscimg.oschina.net/oscnet/64d3a09cfd91b322e8d42c8e591ac566dcc.jpg)

服务端两个角色：

Tracker：管理集群，tracker 也可以实现集群。每个 tracker 节点地位平等。收集 Storage 集群的状态。

Storage：实际保存文件 Storage 分为多个组，每个组之间保存的文件是不同的。每个组内部可以有多个成员，

组成员内部保存的内容是一样的，组成员的地位是一致的，没有主从的概念。

## 1. 拉取镜像

```shell
[root@VM_108_39_centos fastdfs]# docker pull delron/fastdfs
Using default tag: latest
Trying to pull repository docker.io/delron/fastdfs ...
sha256:9583cb80170c153bc12615fd077fe364a8fd5a95194b7cf9a8a32d2c11f8a49d: Pulling from docker.io/delron/fastdfs
469cfcc7a4b3: Pull complete
4b4f08bd0171: Pull complete
95eef9978b96: Pull complete
aff83d00c747: Pull complete
1e95dffa1075: Pull complete
f114184ac28c: Pull complete
649b2ad6afe2: Pull complete
8ab2127a38c5: Pull complete
4d12f9bd27c7: Pull complete
bfc05d82f0a6: Pull complete
76f2a6d84a19: Pull complete
89bd9c4e6fea: Pull complete
6c06548e40ac: Pull complete
11186700b494: Pull complete
Digest: sha256:9583cb80170c153bc12615fd077fe364a8fd5a95194b7cf9a8a32d2c11f8a49d
Status: Downloaded newer image for docker.io/delron/fastdfs:latest

```

## 2. 构建 tracker 容器

```shell
[root@VM_108_39_centos tracker]# docker run -d --network=host --name tracker -v /docker/fastdfs/tracker:/var/fdfs delron/fastdfs tracker

```

## 3. 构建 storage 容器

```shell
docker run -d --network=host --name storage -e TRACKER_SERVER=192.168.1.56:22122 -v /docker/fastdfs/storage:/var/fdfs -e GROUP_NAME=group1 delron/fastdfs storage

```

TRACKER_SERVER=192.168.1.56:22122 替换 192.168.1.56 为你的 ip

## 4. 更改端口（默认的端口为 8888）

进入容器：

```shell
[root@VM_108_39_centos storage]# docker exec -it storage  /bin/bash

```

修改 storage 服务的 http 端口为 91

```shell
[root@VM_108_39_centos nginx-1.12.2]# vi /etc/fdfs/storage.conf
​
# the port of the web server on this storage server
http.server_port=91

```

修改 Nginx 监听的端口为 91：

```shell
[root@VM_108_39_centos nginx-1.12.2]# vi /usr/local/nginx/conf/nginx.conf

```

![https://oscimg.oschina.net/oscnet/5c4c78ce1f9de2ec6aac9b6deaea17f624e.jpg](https://oscimg.oschina.net/oscnet/5c4c78ce1f9de2ec6aac9b6deaea17f624e.jpg)

5、重启 storage

```shell
[root@VM_108_39_centos storage]# docker restart storage
​
storage
​
[root@VM_108_39_centos storage]#
​
```

6、测试 (这里用的 springboot)

下载项目 [https://gitee.com/suzhe/springboot-item.git](https://gitee.com/suzhe/springboot-item.git)

修改 application.yml, 将 192.168.1.56 替换成你的服务器 ip，如下

```shell
server:
  port: 8082
spring:
  application:
    name: upload-service
  servlet:
    multipart:
      max-file-size: 5MB # 限制文件上传的大小
fdfs:
  so-timeout: 1501 # 超时时间
  connect-timeout: 601 # 连接超时时间
  thumb-image: # 缩略图
    width: 60
    height: 60
  tracker-list: # tracker地址
    - 192.168.1.56:22122
​
image:
  adress: http://192.168.1.56:91/

```

启动 FastdfsApplication 类，访问 [http://localhost:8082/upload.html](http://localhost:8082/upload.html)

上传图片进行测试。

![test](https://oscimg.oschina.net/oscnet/d7ee7e9fb29232a9c8b56664b092675117c.jpg)

查看返回的图片地址：

[http://192.168.1.56:91/group1/M00/00/00/CmlsJ1wbUAyALxfhAAFywONGqMo423.jpg](http://192.168.1.56:91/group1/M00/00/00/CmlsJ1wbUAyALxfhAAFywONGqMo423.jpg)

group1：文件上传后所在的 storage 组名称，在文件上传成功后有 storage 服务器返回，需要客户端自行保存。

M00：storage 配置的虚拟路径，与磁盘选项 store_path * 对应。如果配置了

store_path0 则是 M00，如果配置了 store_path1 则是 M01，以此类推。

00：数据两级目录：storage 服务器在每个虚拟磁盘路径下创建的两级目录，用于存储数据

文件。

CmlsJ1wbUAyALxfhAAFywONGqMo423：与文件上传时不同。是由存储服务器根据特定信息生成，文件名包含：源存储服务器 IP 地址、文件创建时间戳、文件大小、随机数和文件拓展名等信息。
