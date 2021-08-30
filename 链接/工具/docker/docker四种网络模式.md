# docker有四种网络模式

* host模式，使用docker run时使用--net=host指定docker使用的网络实际上和宿主机一样，在容器内看到的网卡ip是宿主机上的ip
* container模式，使用--net=container:container_id/container_name多个容器使用共同的网络，看到的ip是一样的
* none模式，使用--net=none指定这种模式下，不会配置任何网络
* bridge模式，使用--net=bridge指定默认模式，不用指定默认就是这种网络模式。这种模式会为每个容器分配一个独立的Network Namespace。类似于vmware的nat网络模式。同一个宿主机上的所有容器会在同一个网段下，相互之间是可以通信的。
