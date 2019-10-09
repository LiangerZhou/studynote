> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s/jIGHy62sGdpGseuf7fK0mg

一、MySQL 的复制原理以及流程


---------------------

(1) 复制基本原理流程

1. 主：binlog 线程——记录下所有改变了数据库数据的语句，放进 master 上的 binlog 中；

2. 从：io 线程——在使用 start slave 之后，负责从 master 上拉取 binlog 内容，放进 自己的 relay log 中；

3. 从：sql 执行线程——执行 relay log 中的语句；

(2)MySQL 复制的线程有几个及之间的关联

MySQL 的复制是基于如下 3 个线程的交互（ 多线程复制里面应该是 4 类线程）：

1. Master 上面的 binlog dump 线程，该线程负责将 master 的 binlog event 传到 slave；

2. Slave 上面的 IO 线程，该线程负责接收 Master 传过来的 binlog，并写入 relay log；

3. Slave 上面的 SQL 线程，该线程负责读取 relay log 并执行；

4. 如果是多线程复制，无论是 5.6 库级别的假多线程还是 MariaDB 或者 5.7 的真正的多线程复制， SQL 线程只做 coordinator，只负责把 relay log 中的 binlog 读出来然后交给 worker 线程， woker 线程负责具体 binlog event 的执行；

(3)MySQL 如何保证复制过程中数据一致性及减少数据同步延时

一致性主要有以下几个方面：

1. 在 MySQL5.5 以及之前， slave 的 SQL 线程执行的 relay log 的位置只能保存在文件（ relay-log.info）里面，并且该文件默认每执行 10000 次事务做一次同步到磁盘， 这意味着 slave 意外 crash 重启时， SQL 线程执行到的位置和数据库的数据是不一致的，将导致复制报错，如果不重搭复制，则有可能会导致数据不一致。MySQL 5.6 引入参数 relay_log_info_repository，将该参数设置为 TABLE 时， MySQL 将 SQL 线程执行到的位置存到 mysql.slave_relay_log_info 表，这样更新该表的位置和 SQL 线程执行的用户事务绑定成一个事务，这样 slave 意外宕机后， slave 通过 innodb 的崩溃恢复可以把 SQL 线程执行到的位置和用户事务恢复到一致性的状态。

2. MySQL 5.6 引入 GTID 复制，每个 GTID 对应的事务在每个实例上面最多执行一次， 这极大地提高了复制的数据一致性；

3. MySQL 5.5 引入半同步复制， 用户安装半同步复制插件并且开启参数后，设置超时时间，可保证在超时时间内如果 binlog 不传到 slave 上面，那么用户提交事务时不会返回，直到超时后切成异步复制，但是如果切成异步之前用户线程提交时在 master 上面等待的时候，事务已经提交，该事务对 master 上面的其他 session 是可见的，如果这时 master 宕机，那么到 slave 上面该事务又不可见了，该问题直到 5.7 才解决；

4. MySQL 5.7 引入无损半同步复制，引入参 rpl_semi_sync_master_wait_point，该参数默认为 after_sync，指的是在切成半同步之前，事务不提交，而是接收到 slave 的 ACK 确认之后才提交该事务，从此，复制真正可以做到无损的了。

5. 可以再说一下 5.7 的无损复制情况下， master 意外宕机，重启后发现有 binlog 没传到 slave 上面，这部分 binlog 怎么办？？？分 2 种情况讨论， 1 宕机时已经切成异步了， 2 是宕机时还没切成异步？？？这个怎么判断宕机时有没有切成异步呢？？？分别怎么处理？？？

延时性：

5.5 是单线程复制， 5.6 是多库复制（对于单库或者单表的并发操作是没用的）， 5.7 是真正意义的多线程复制，它的原理是基于 group commit， 只要 master 上面的事务是 group commit 的，那 slave 上面也可以通过多个 worker 线程去并发执行。和 MairaDB10.0.0.5 引入多线程复制的原理基本一样。

(4) 工作遇到的复制 bug 的解决方法

5.6 的多库复制有时候自己会停止，我们写了一个脚本重新 start slave; 待补充…

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyrpiaOwibUqyH2GtIcIuicb3m8Y4tqynNU5LpKRYbevs8xNM5IuucG8u86Ggajk1ia3Lc2aGkoUpU5Rhw/640?wx_fmt=png)

二、MySQL 中 myisam 与 innodb 的区别，至少 5 点


----------------------------------------

(1) 问 5 点不同

1.InnoDB 支持事物，而 MyISAM 不支持事物

2.InnoDB 支持行级锁，而 MyISAM 支持表级锁

3.InnoDB 支持 MVCC, 而 MyISAM 不支持

4.InnoDB 支持外键，而 MyISAM 不支持

5.InnoDB 不支持全文索引，而 MyISAM 支持。

6.InnoDB 不能通过直接拷贝表文件的方法拷贝表到另外一台机器， myisam 支持

7.InnoDB 表支持多种行格式， myisam 不支持

8.InnoDB 是索引组织表， myisam 是堆表

(2)innodb 引擎的 4 大特性

1. 插入缓冲（insert buffer)

2. 二次写 (double write)

3. 自适应哈希索引 (ahi)

4. 预读 (read ahead)

(3) 各种不同 mysql 版本的 Innodb 的改进

MySQL5.6 下 Innodb 引擎的主要改进：

1.online DDL

2. memcached NoSQL 接口

3.transportable tablespace（ alter table discard/import tablespace）

4.MySQL 正常关闭时，可以 dump 出 buffer pool 的（ space， page_no），重启时 reload，加快预热速度

5. 索引和表的统计信息持久化到 mysql.innodb_table_stats 和 mysql.innodb_index_stats，可提供稳定的执行计划

6.Compressed row format 支持压缩表

MySQL 5.7 innodb 引擎主要改进

1. 修改 varchar 字段长度有时可以使用 online DDL

2. Buffer pool 支持在线改变大小

3.Buffer pool 支持导出部分比例

4. 支持新建 innodb tablespace，并可以在其中创建多张表

5. 磁盘临时表采用 innodb 存储，并且存储在 innodb temp tablespace 里面，以前是 myisam 存储

6. 透明表空间压缩功能

(4)2 者 select count(*) 哪个更快，为什么

myisam 更快，因为 myisam 内部维护了一个计数器，可以直接调取。

(5)2 者的索引的实现方式

都是 B + 树索引， Innodb 是索引组织表， myisam 是堆表， 索引组织表和堆表的区别要熟悉

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyrpiaOwibUqyH2GtIcIuicb3m8j8RzCrOcwzfgHS1oPpia7icCAqjX4u8yN0jeSBfwc3x7PXNaxUrtXjBQ/640?wx_fmt=png)

三、MySQL 中 varchar 与 char 的区别以及 varchar(50) 中的 50 代表的涵义


----------------------------------------------------------

(1)varchar 与 char 的区别

在单字节字符集下， char（N） 在内部存储的时候总是定长， 而且没有变长字段长度列表中。在多字节字符集下面， char(N) 如果存储的字节数超过 N，那么 char（N）将和 varchar（N）没有区别。在多字节字符集下面，如果存储的字节数少于 N，那么存储 N 个字节，后面补空格，补到 N 字节长度。都存储变长的数据和变长字段长度列表。varchar(N) 无论是什么字节字符集，都是变长的，即都存储变长数据和变长字段长度列表

(2)varchar(50) 中 50 的涵义

最多存放 50 个字符，varchar(50)和 (200) 存储 hello 所占空间一样，但后者在排序时会消耗更多内存，因为 order by col 采用 fixed_length 计算 col 长度(memory 引擎也一样)。在早期 MySQL 版本中， 50 代表字节数，现在代表字符数。

(3)int（20）中 20 的涵义

是指显示字符的长度

不影响内部存储，只是影响带 zerofill 定义的 int 时，前面补多少个 0，易于报表展示

(4)mysql 为什么这么设计

对大多数应用没有意义，只是规定一些工具用来显示字符的个数；int(1) 和 int(20) 存储和计算均一样；

四、innodb 的事务与日志的实现方式


------------------------

(1) 有多少种日志

redo 和 undo

(2) 日志的存放形式

redo：在页修改的时候，先写到 redo log buffer 里面， 然后写到 redo log 的文件系统缓存里面 (fwrite)，然后再同步到磁盘文件（ fsync）。

Undo：在 MySQL5.5 之前， undo 只能存放在 ibdata * 文件里面， 5.6 之后，可以通过设置 innodb_undo_tablespaces 参数把 undo log 存放在 ibdata * 之外。

(3) 事务是如何通过日志来实现的，说得越深入越好

基本流程如下：

因为事务在修改页时，要先记 undo，在记 undo 之前要记 undo 的 redo， 然后修改数据页，再记数据页修改的 redo。Redo（里面包括 undo 的修改） 一定要比数据页先持久化到磁盘。当事务需要回滚时，因为有 undo，可以把数据页回滚到前镜像的

状态，崩溃恢复时，如果 redo log 中事务没有对应的 commit 记录，那么需要用 undo 把该事务的修改回滚到事务开始之前。如果有 commit 记录，就用 redo 前滚到该事务完成时并提交掉。

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyrpiaOwibUqyH2GtIcIuicb3m8qgZrtysbphrMT2zvyMw0jlRxibzWnwDHDl41icjMBIKcCBnEBTog9ymQ/640?wx_fmt=png)

五、MySQL binlog 的几种日志录入格式以及区别


--------------------------------

(1) 各种日志格式的涵义

1.Statement：每一条会修改数据的 sql 都会记录在 binlog 中。

优点：不需要记录每一行的变化，减少了 binlog 日志量，节约了 IO，提高性能。(相比 row 能节约多少性能 与日志量，这个取决于应用的 SQL 情况，正常同一条记录修改或者插入 row 格式所产生的日志量还小于 Statement 产生的日志量，

但是考虑到如果带条 件的 update 操作，以及整表删除，alter 表等操作，ROW 格式会产生大量日志，因此在考虑是否使用 ROW 格式日志时应该跟据应用的实际情况，其所 产生的日志量会增加多少，以及带来的 IO 性能问题。)

缺点：由于记录的只是执行语句，为了这些语句能在 slave 上正确运行，因此还必须记录每条语句在执行的时候的 一些相关信息，以保证所有语句能在 slave 得到和在 master 端执行时候相同 的结果。另外 mysql 的复制,

像一些特定函数功能，slave 可与 master 上要保持一致会有很多相关问题 (如 sleep() 函数， last_insert_id()，以及 user-defined functions(udf)会出现问题).

使用以下函数的语句也无法被复制：

* LOAD_FILE()

* UUID()

* USER()

* FOUND_ROWS()

* SYSDATE() (除非启动时启用了 --sysdate-is-now 选项)

同时在 INSERT ...SELECT 会产生比 RBR 更多的行级锁

2.Row: 不记录 sql 语句上下文相关信息，仅保存哪条记录被修改。

优点：binlog 中可以不记录执行的 sql 语句的上下文相关的信息，仅需要记录那一条记录被修改成什么了。所以 rowlevel 的日志内容会非常清楚的记录下 每一行数据修改的细节。而且不会出现某些特定情况下的存储过程，或 function，以及 trigger 的调用和触发无法被正确复制的问题

缺点: 所有的执行的语句当记录到日志中的时候，都将以每行记录的修改来记录，这样可能会产生大量的日志内容, 比 如一条 update 语句，修改多条记录，则 binlog 中每一条修改都会有记录，这样造成 binlog 日志量会很大，特别是当执行 alter table 之类的语句的时候，由于表结构修改，每条记录都发生改变，那么该表每一条记录都会记录到日志中。

3.Mixedlevel: 是以上两种 level 的混合使用，一般的语句修改使用 statment 格式保存 binlog，如一些函数，statement 无法完成主从复制的操作，则 采用 row 格式保存 binlog,MySQL 会根据执行的每一条具体的 sql 语句来区分对待记录的日志形式，

也就是在 Statement 和 Row 之间选择 一种. 新版本的 MySQL 中队 row level 模式也被做了优化，并不是所有的修改都会以 row level 来记录，像遇到表结构变更的时候就会以 statement 模式来记录。至于 update 或者 delete 等修改数据的语句，还是会记录所有行的变更。

(2) 适用场景

在一条 SQL 操作了多行数据时， statement 更节省空间， row 更占用空间。但是 row 模式更可靠。

(3) 结合第一个问题，每一种日志格式在复制中的优劣

Statement 可能占用空间会相对小一些，传送到 slave 的时间可能也短，但是没有 row 模式的可靠。Row 模式在操作多行数据时更占用空间， 但是可靠。

六、下 MySQL 数据库 cpu 飙升到 500% 的话他怎么处理？


---------------------------------------

当 cpu 飙升到 500% 时，先用操作系统命令 top 命令观察是不是 mysqld 占用导致的，如果不是，找出占用高的进程，并进行相关处理。如果是 mysqld 造成的， show processlist，看看里面跑的 session 情况，是不是有消耗资源的 sql 在运行。找出消耗高的 sql，看看执行计划是否准确， index 是否缺失，或者实在是数据量太大造成。一般来说，肯定要 kill 掉这些线程 (同时观察 cpu 使用率是否下降)，等进行相应的调整(比如说加索引、改 sql、改内存参数) 之后，再重新跑这些 SQL。也有可能是每个 sql 消耗资源并不多，但是突然之间，有大量的 session 连进来导致 cpu 飙升，这种情况就需要跟应用一起来分析为何连接数会激增，再做出相应的调整，比如说限制连接数等

七、sql 优化


------------

(1)、explain 出来的各种 item 的意义

id: 每个被独立执行的操作的标志，表示对象被操作的顺序。一般来说， id 值大，先被执行；如果 id 值相同，则顺序从上到下。

select_type：查询中每个 select 子句的类型。

table: 名字，被操作的对象名称，通常的表名 (或者别名)，但是也有其他格式。

partitions: 匹配的分区信息。

type:join 类型。

possible_keys：列出可能会用到的索引。

key: 实际用到的索引。

key_len: 用到的索引键的平均长度，单位为字节。

ref: 表示本行被操作的对象的参照对象，可能是一个常量用 const 表示，也可能是其他表的

key 指向的对象，比如说驱动表的连接列。

rows: 估计每次需要扫描的行数。

filtered:rows*filtered/100 表示该步骤最后得到的行数 (估计值)。

extra: 重要的补充信息。

(2)、profile 的意义以及使用场景

Profile 用来分析 sql 性能的消耗分布情况。当用 explain 无法解决慢 SQL 的时候，需要用 profile 来对 sql 进行更细致的分析，找出 sql 所花的时间大部分消耗在哪个部分，确认 sql 的性能瓶颈。

(3)、explain 中的索引问题

Explain 结果中，一般来说，要看到尽量用 index(type 为 const、 ref 等， key 列有值)，避免使用全表扫描 (type 显式为 ALL)。比如说有 where 条件且选择性不错的列，需要建立索引。

被驱动表的连接列，也需要建立索引。被驱动表的连接列也可能会跟 where 条件列一起建立联合索引。当有排序或者 group by 的需求时，也可以考虑建立索引来达到直接排序和汇总的需求。

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyrpiaOwibUqyH2GtIcIuicb3m8gVn3bHb71MfLxkwXLDyDk4k9JAAbVZK9bkcQTCdaJKS0hSPxXLNU9A/640?wx_fmt=png)

八、备份计划，mysqldump 以及 xtranbackup 的实现原理


-----------------------------------------

(1) 备份计划

视库的大小来定，一般来说 100G 内的库，可以考虑使用 mysqldump 来做，因为 mysqldump 更加轻巧灵活，备份时间选在业务低峰期，可以每天进行都进行全量备份 (mysqldump 备份

出来的文件比较小，压缩之后更小)。100G 以上的库，可以考虑用 xtranbackup 来做，备份速度明显要比 mysqldump 要快。一般是选择一周一个全备，其余每天进行增量备份，备份时间为业务低峰期。

(2) 备份恢复时间

物理备份恢复快，逻辑备份恢复慢

这里跟机器，尤其是硬盘的速率有关系，以下列举几个仅供参考

20G 的 2 分钟（mysqldump）

80G 的 30 分钟 (mysqldump)

111G 的 30 分钟（mysqldump)

288G 的 3 小时（xtra)

3T 的 4 小时（xtra)

逻辑导入时间一般是备份时间的 5 倍以上

(3) 备份恢复失败如何处理

首先在恢复之前就应该做足准备工作，避免恢复的时候出错。比如说备份之后的有效性检查、权限检查、空间检查等。如果万一报错，再根据报错的提示来进行相应的调整。

(4)mysqldump 和 xtrabackup 实现原理

mysqldump

mysqldump 属于逻辑备份。加入 --single-transaction 选项可以进行一致性备份。后台进程会先设置 session 的事务隔离级别为 RR(SET SESSION TRANSACTION ISOLATION LEVELREPEATABLE READ)，之后显式开启一个事务 (START TRANSACTION /*!40100 WITH CONSISTENTSNAPSHOT */)，这样就保证了该事务里读到的数据都是事务事务时候的快照。之后再把表的数据读取出来。如果加上 --master-data=1 的话，在刚开始的时候还会加一个数据库的读锁 (FLUSH TABLES WITH READ LOCK), 等开启事务后，再记录下数据库此时 binlog 的位置 (showmaster status)，马上解锁，再读取表的数据。等所有的数据都已经导完，就可以结束事务

Xtrabackup:

xtrabackup 属于物理备份，直接拷贝表空间文件，同时不断扫描产生的 redo 日志并保存下来。最后完成 innodb 的备份后，会做一个 flush engine logs 的操作 (老版本在有 bug，在 5.6 上不做此操作会丢数据)，确保所有的 redo log 都已经落盘 (涉及到事务的两阶段提交

概念，因为 xtrabackup 并不拷贝 binlog，所以必须保证所有的 redo log 都落盘，否则可能会丢最后一组提交事务的数据)。这个时间点就是 innodb 完成备份的时间点，数据文件虽然不是一致性的，但是有这段时间的 redo 就可以让数据文件达到一致性 (恢复的时候做的事

情)。然后还需要 flush tables with read lock，把 myisam 等其他引擎的表给备份出来，备份完后解锁。这样就做到了完美的热备。

九、mysqldump 中备份出来的 sql，如果我想 sql 文件中，一行只有一个 insert....value() 的话，怎么办？如果备份需要带上 master 的复制点信息怎么办？


--------------------------------------------------------------------------------------------------

```
--skip-extended-insert
[root@helei-zhuanshu ~]# mysqldump -uroot -p helei --skip-extended-insert
Enter password:
  KEY `idx_c1` (`c1`),
  KEY `idx_c2` (`c2`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `helei`
--
LOCK TABLES `helei` WRITE;
/*!40000 ALTER TABLE `helei` DISABLE KEYS */;
INSERT INTO `helei` VALUES (1,32,37,38,'2016-10-18 06:19:24','susususususususususususu');
INSERT INTO `helei` VALUES (2,37,46,21,'2016-10-18 06:19:24','susususususu');
INSERT INTO `helei` VALUES (3,21,5,14,'2016-10-18 06:19:24','susu');



```

十、500 台 db，在最快时间之内重启


------------------------

可以使用批量 ssh 工具 pssh 来对需要重启的机器执行重启命令。也可以使用 salt（前提是客户端有安装 salt）或者 ansible（ ansible 只需要 ssh 免登通了就行）等多线程工具同时操作多台服务器

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyrpiaOwibUqyH2GtIcIuicb3m8G45GlLlB3YNE8mPsgOpkrNspKR1pUmlMAWr3qokmhgV1P9t7LQticAw/640?wx_fmt=png)

十一、innodb 的读写参数优化


---------------------

(1) 读取参数

```
global buffer 以及 local buffer；
Global buffer：
Innodb_buffer_pool_size
innodb_log_buffer_size
innodb_additional_mem_pool_size
local buffer(下面的都是 server 层的 session 变量，不是 innodb 的)：
Read_buffer_size
Join_buffer_size
Sort_buffer_size
Key_buffer_size
Binlog_cache_size



```

(2) 写入参数

```
innodb_flush_log_at_trx_commit
innodb_buffer_pool_size
insert_buffer_size
innodb_double_write
innodb_write_io_thread
innodb_flush_method



```

(3) 与 IO 相关的参数

```
innodb_write_io_threads = 8
innodb_read_io_threads = 8
innodb_thread_concurrency = 0
Sync_binlog
Innodb_flush_log_at_trx_commit
Innodb_lru_scan_depth
Innodb_io_capacity
Innodb_io_capacity_max
innodb_log_buffer_size
innodb_max_dirty_pages_pct



```

(4) 缓存参数以及缓存的适用场景

query cache/query_cache_type

并不是所有表都适合使用 query cache。造成 query cache 失效的原因主要是相应的 table 发生了变更

第一个：读操作多的话看看比例，简单来说，如果是用户清单表，或者说是数据比例比较固定，比如说商品列表，是可以打开的，前提是这些库比较集中，数据库中的实务比较小。

第二个：我们 “行骗” 的时候，比如说我们竞标的时候压测，把 query cache 打开，还是能收到 qps 激增的效果，当然前提示前端的连接池什么的都配置一样。大部分情况下如果写入的居多，访问量并不多，那么就不要打开，例如社交网站的，10% 的人产生内容，其余的 90% 都在消费，打开还是效果很好的，但是你如果是 qq 消息，或者聊天，那就很要命。

第三个：小网站或者没有高并发的无所谓，高并发下，会看到 很多 qcache 锁 等待，所以一般高并发下，不建议打开 query cache

十二、你是如何监控你们的数据库的？你们的慢日志都是怎么查询的？


-----------------------------------

监控的工具有很多，例如 zabbix，lepus，我这里用的是 lepus

十三、你是否做过主从一致性校验，如果有，怎么做的，如果没有，你打算怎么做？


-----------------------------------------

主从一致性校验有多种工具 例如 checksum、mysqldiff、pt-table-checksum 等

十四、表中有大字段 X(例如：text 类型)，且字段 X 不会经常更新，以读为为主，请问您是选择拆成子表，还是继续放一起? 写出您这样选择的理由

答：拆带来的问题：连接消耗 + 存储拆分空间；不拆可能带来的问题：查询性能；

如果能容忍拆分带来的空间问题, 拆的话最好和经常要查询的表的主键在物理结构上放置在一起 (分区) 顺序 IO, 减少连接消耗, 最后这是一个文本列再加上一个全文索引来尽量抵消连接消耗

如果能容忍不拆分带来的查询性能损失的话: 上面的方案在某个极致条件下肯定会出现问题, 那么不拆就是最好的选择

十五、MySQL 中 InnoDB 引擎的行锁是通过加在什么上完成 (或称实现) 的？为什么是这样子的？


--------------------------------------------------------

答：InnoDB 是基于索引来完成行锁

例: select * from tab_with_index where id = 1 for update;

for update 可以根据条件来完成行锁锁定, 并且 id 是有索引键的列,

如果 id 不是索引键那么 InnoDB 将完成表锁,, 并发将无从谈起

十六、如何从 mysqldump 产生的全库备份中只恢复某一个库、某一张表？


------------------------------------------

```
全库备份
[root@HE1 ~]# mysqldump -uroot -p --single-transaction -A --master-data=2 >dump.sql
只还原erp库的内容
[root@HE1 ~]# mysql -uroot -pMANAGER erp --one-database <dump.sql

可以看出这里主要用到的参数是--one-database简写-o的参数，极大方便了我们的恢复灵活性
那么如何从全库备份中抽取某张表呢，全库恢复，再恢复某张表小库还可以，大库就很麻烦了，那我们可以利用正则表达式来进行快速抽取，具体实现方法如下：
 
从全库备份中抽取出t表的表结构
[root@HE1 ~]# sed -e'/./{H;$!d;}' -e 'x;/CREATE TABLE `t`/!d;q' dump.sql
 
DROP TABLE IF EXISTS`t`;
/*!40101 SET@saved_cs_client     =@@character_set_client */;
/*!40101 SETcharacter_set_client = utf8 */;
CREATE TABLE `t` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `age` tinyint(4) NOT NULL DEFAULT '0',
  `name` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDBAUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SETcharacter_set_client = @saved_cs_client */;
 
从全库备份中抽取出t表的内容
[root@HE1 ~]# grep'INSERT INTO `t`' dump.sql
INSERT INTO `t`VALUES (0,0,''),(1,0,'aa'),(2,0,'bbb'),(3,25,'helei');



```

十七、在当前的工作中，你碰到到的最大的 mysql db 问题以及如何解决的？


-------------------------------------------

可以选择一个处理过的比较棘手的案例，或者选择一个老师在课程上讲过的死锁的案例; 没有及时 Purge + insert 唯一索引造成的死锁：具体案例可以参考学委笔记。

十八、请简洁地描述下 MySQL 中 InnoDB 支持的四种事务隔离级别名称，以及逐级之间的区别？


------------------------------------------------------

(1) 事物的 4 种隔离级别

读未提交 (read uncommitted)

读已提交 (read committed)

可重复读 (repeatable read)

串行 (serializable)

(2) 不同级别的现象

Read Uncommitted: 可以读取其他 session 未提交的脏数据。

Read Committed: 允许不可重复读取，但不允许脏读取。提交后，其他会话可以看到提交的数据。

Repeatable Read: 禁止不可重复读取和脏读取、以及幻读 (innodb 独有)。

Serializable: 事务只能一个接着一个地执行，但不能并发执行。事务隔离级别最高。

不同的隔离级别有不同的现象，并有不同的锁定 / 并发机制，隔离级别越高，数据库的并发性就越差。

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyrpiaOwibUqyH2GtIcIuicb3m8E9u76cfXKCxh2HPENVlicCLmFXjBOCKXrkSbouAkKltDSddOMlHaB9g/640?wx_fmt=png)

↓↓↓Java 高级架构师课程免费体验课