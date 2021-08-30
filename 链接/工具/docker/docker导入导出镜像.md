# docker导入导出镜像

* docker save images_name：
将一个镜像导出为文件，再使用docker load命令将文件导入为一个镜像，会保存该镜像的的所有历史记录。比docker export命令导出的文件大，很好理解，因为会保存镜像的所有历史记录。

* docker export container_id：
将一个容器导出为文件，再使用docker import 命令将容器导入成为一个新的镜像，但是相比docker save命令，容器文件会丢失所有元数据和历史记录，仅保存容器当时的状态，相当于虚拟机快照。
