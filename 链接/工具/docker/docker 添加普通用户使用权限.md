# Docker 添加普通用户使用权限

> sudo groupadd docker #添加docker用户组
>
> sudo gpasswd -a $USER docker #将登陆用户加入到docker用户组中
>
> newgrp docker #更新用户组
