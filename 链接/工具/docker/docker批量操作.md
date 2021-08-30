### 启动所有容器

> docker start $(docker ps -a | awk '{ print $1}' | tail -n +2)

### 关闭所有容器

> docker stop $(docker ps -a | awk '{ print $1}' | tail -n +2)

### 删除所有容器

> docker rm $(docker ps -a | awk '{ print $1}' | tail -n +2)

### 删除所有镜像

> docker rmi $(docker p_w_picpaths | awk '{print $3}' |tail -n +2)

### 重启所有容器

> docker restart $(docker ps -a | awk '{ print $1}' | tail -n +2)
