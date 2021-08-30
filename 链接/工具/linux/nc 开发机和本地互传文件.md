# nc 命令

## 用nc从mac向开发机传文件的方法

* STEP 1：开发机上执行 nc -l -p {PORT} > {FILE_NAME}

> nc -l 8088 > deployConf.py

* STEP 2：MAC上执行 nc {开发机地址} {PORT} < {FILE_NAME}

> nc  10.100.87.59 8088 < ./deployConf.py

## 用nc从开发机传文件到mac的方法

* STEP 1：开发机上执行 nc -l -p {PORT} < {FILE_NAME}

> nc -l 8088 <xxx.tar.gz

* STEP 2：MAC上执行 nc {开发机地址} {PORT} > {FILE_NAME}

> nc -l 8088 >xxx.tar.gz
