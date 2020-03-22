> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://www.jianshu.com/p/d7b9c468f20d

[![](https://upload.jianshu.io/users/upload_avatars/68057/92692f6928d7?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96/format/webp)](https://www.jianshu.com/u/7de1e161ab09)

12015.12.31 11:17:55 字数 457 阅读 69,185

一. 创建用户
-------

### 命令:

```
CREATE USER 'username'@'host' IDENTIFIED BY 'password';


```

### 说明：

*   username：你将创建的用户名
*   host：指定该用户在哪个主机上可以登陆，如果是本地用户可用 localhost，如果想让该用户可以**从任意远程主机登陆**，可以使用通配符`%`
*   password：该用户的登陆密码，密码可以为空，如果为空则该用户可以不需要密码登陆服务器

### 例子：

```
CREATE USER 'dog'@'localhost' IDENTIFIED BY '123456';
CREATE USER 'pig'@'192.168.1.101_' IDENDIFIED BY '123456';
CREATE USER 'pig'@'%' IDENTIFIED BY '123456';
CREATE USER 'pig'@'%' IDENTIFIED BY '';
CREATE USER 'pig'@'%';


```

二. 授权:
------

### 命令:

```
GRANT privileges ON databasename.tablename TO 'username'@'host'


```

### 说明:

*   privileges：用户的操作权限，如`SELECT`，`INSERT`，`UPDATE`等，如果要授予所的权限则使用`ALL`
*   databasename：数据库名
*   tablename：表名，如果要授予该用户对所有数据库和表的相应操作权限则可用`*`表示，如`*.*`

### 例子:

```
GRANT SELECT, INSERT ON test.user TO 'pig'@'%';
GRANT ALL ON *.* TO 'pig'@'%';


```

### 注意:

用以上命令授权的用户不能给其它用户授权，如果想让该用户可以授权，用以下命令:

```
GRANT privileges ON databasename.tablename TO 'username'@'host' WITH GRANT OPTION;


```

三. 设置与更改用户密码
------------

### 命令:

```
SET PASSWORD FOR 'username'@'host' = PASSWORD('newpassword');


```

如果是当前登陆用户用:

```
SET PASSWORD = PASSWORD("newpassword");


```

### 例子:

```
SET PASSWORD FOR 'pig'@'%' = PASSWORD("123456");


```

四. 撤销用户权限
---------

### 命令:

```
REVOKE privilege ON databasename.tablename FROM 'username'@'host';


```

说明:
---

privilege, databasename, tablename：同授权部分

### 例子:

```
REVOKE SELECT ON *.* FROM 'pig'@'%';


```

### 注意:

假如你在给用户`'pig'@'%'`授权的时候是这样的（或类似的）：`GRANT SELECT ON test.user TO 'pig'@'%'`，则在使用`REVOKE SELECT ON *.* FROM 'pig'@'%';`命令并不能撤销该用户对 test 数据库中 user 表的`SELECT` 操作。相反，如果授权使用的是`GRANT SELECT ON *.* TO 'pig'@'%';`则`REVOKE SELECT ON test.user FROM 'pig'@'%';`命令也不能撤销该用户对 test 数据库中 user 表的`Select`权限。

具体信息可以用命令`SHOW GRANTS FOR 'pig'@'%';` 查看。

五. 删除用户
-------

### 命令:

```
DROP USER 'username'@'host';


```

* * *

如果觉得有用，欢迎关注我的微信，有问题可以直接交流：

![](https://www.github.com/hoxis/token4md/raw/master/wechat-qcode.jpg)

你的关注是对我最大的鼓励！

"小礼物走一走，来简书关注我"

还没有人赞赏，支持一下

[![](https://upload.jianshu.io/users/upload_avatars/68057/92692f6928d7?imageMogr2/auto-orient/strip|imageView2/1/w/100/h/100/format/webp)](https://www.jianshu.com/u/7de1e161ab09)

[hoxis](https://www.jianshu.com/u/7de1e161ab09 "hoxis") 微信关注「不正经程序员」，技术干货，效率工具，读书分享！

总资产 187 (约 18.01 元) 共写了 16.3W 字获得 1,040 个赞共 624 个粉丝

### 被以下专题收入，发现更多相似内容

### 推荐阅读[更多精彩内容](https://www.jianshu.com/)

*   一. 创建用户 命令: 说明： username：你将创建的用户名 host：指定该用户在哪个主机上可以登陆，如果...
    
*   http://www.jb51.net/article/31850.htm
    
*   创建用户: 命令: CREATE USER 'username'@'host' IDENTIFIED BY 'pas...
    
*   这个话题总在身边响起，因为我们即将面临选择了，去工作？做什么？ 我的选择是哪个工资高做哪个， 技术工程师 (java...
    
    [![](https://upload.jianshu.io/users/upload_avatars/3944205/bad1d4b6-ba24-42e6-8683-19898a7b9fd9.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/48/h/48/format/webp)幻影翔](https://www.jianshu.com/u/55ada657d31e)阅读 0
    
*   理财就是如何有效地管理现金流 自控力和生活质量及其成功成正比关系 抵御诱惑的一个有效方法就是转移注意力 自控 自制...