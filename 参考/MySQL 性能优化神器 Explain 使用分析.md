> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://segmentfault.com/a/1190000008131735

简介
--

MySQL 提供了一个 EXPLAIN 命令, 它可以对 `SELECT` 语句进行分析, 并输出 `SELECT` 执行的详细信息, 以供开发人员针对性优化.  
EXPLAIN 命令用法十分简单, 在 SELECT 语句前加上 Explain 就可以了, 例如:

```
EXPLAIN SELECT * from user_info WHERE  id < 300;

```

准备
--

为了接下来方便演示 EXPLAIN 的使用, 首先我们需要建立两个测试用的表, 并添加相应的数据:

```
CREATE TABLE `user_info` (
  `id`   BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL DEFAULT '',
  `age`  INT(11)              DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name_index` (`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8

INSERT INTO user_info (name, age) VALUES ('xys', 20);
INSERT INTO user_info (name, age) VALUES ('a', 21);
INSERT INTO user_info (name, age) VALUES ('b', 23);
INSERT INTO user_info (name, age) VALUES ('c', 50);
INSERT INTO user_info (name, age) VALUES ('d', 15);
INSERT INTO user_info (name, age) VALUES ('e', 20);
INSERT INTO user_info (name, age) VALUES ('f', 21);
INSERT INTO user_info (name, age) VALUES ('g', 23);
INSERT INTO user_info (name, age) VALUES ('h', 50);
INSERT INTO user_info (name, age) VALUES ('i', 15);

```

```
CREATE TABLE `order_info` (
  `id`           BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `user_id`      BIGINT(20)           DEFAULT NULL,
  `product_name` VARCHAR(50) NOT NULL DEFAULT '',
  `productor`    VARCHAR(30)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_product_detail_index` (`user_id`, `product_name`, `productor`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8

INSERT INTO order_info (user_id, product_name, productor) VALUES (1, 'p1', 'WHH');
INSERT INTO order_info (user_id, product_name, productor) VALUES (1, 'p2', 'WL');
INSERT INTO order_info (user_id, product_name, productor) VALUES (1, 'p1', 'DX');
INSERT INTO order_info (user_id, product_name, productor) VALUES (2, 'p1', 'WHH');
INSERT INTO order_info (user_id, product_name, productor) VALUES (2, 'p5', 'WL');
INSERT INTO order_info (user_id, product_name, productor) VALUES (3, 'p3', 'MA');
INSERT INTO order_info (user_id, product_name, productor) VALUES (4, 'p1', 'WHH');
INSERT INTO order_info (user_id, product_name, productor) VALUES (6, 'p1', 'WHH');
INSERT INTO order_info (user_id, product_name, productor) VALUES (9, 'p8', 'TE');

```

EXPLAIN 输出格式
------------

EXPLAIN 命令的输出内容大致如下:

```
mysql> explain select * from user_info where id = 2\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: user_info
   partitions: NULL
         type: const
possible_keys: PRIMARY
          key: PRIMARY
      key_len: 8
          ref: const
         rows: 1
     filtered: 100.00
        Extra: NULL
1 row in set, 1 warning (0.00 sec)

```

各列的含义如下:

*   id: SELECT 查询的标识符. 每个 SELECT 都会自动分配一个唯一的标识符.
    
*   select_type: SELECT 查询的类型.
    
*   table: 查询的是哪个表
    
*   partitions: 匹配的分区
    
*   type: join 类型
    
*   possible_keys: 此次查询中可能选用的索引
    
*   key: 此次查询中确切使用到的索引.
    
*   ref: 哪个字段或常数与 key 一起被使用
    
*   rows: 显示此查询一共扫描了多少行. 这个是一个估计值.
    
*   filtered: 表示此查询条件所过滤的数据的百分比
    
*   extra: 额外的信息
    

接下来我们来重点看一下比较重要的几个字段.

### select_type

`select_type` 表示了查询的类型, 它的常用取值有:

*   SIMPLE, 表示此查询不包含 UNION 查询或子查询
    
*   PRIMARY, 表示此查询是最外层的查询
    
*   UNION, 表示此查询是 UNION 的第二或随后的查询
    
*   DEPENDENT UNION, UNION 中的第二个或后面的查询语句, 取决于外面的查询
    
*   UNION RESULT, UNION 的结果
    
*   SUBQUERY, 子查询中的第一个 SELECT
    
*   DEPENDENT SUBQUERY: 子查询中的第一个 SELECT, 取决于外面的查询. 即子查询依赖于外层查询的结果.
    

最常见的查询类别应该是 `SIMPLE` 了, 比如当我们的查询没有子查询, 也没有 UNION 查询时, 那么通常就是 `SIMPLE` 类型, 例如:

```
mysql> explain select * from user_info where id = 2\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: user_info
   partitions: NULL
         type: const
possible_keys: PRIMARY
          key: PRIMARY
      key_len: 8
          ref: const
         rows: 1
     filtered: 100.00
        Extra: NULL
1 row in set, 1 warning (0.00 sec)

```

如果我们使用了 UNION 查询, 那么 EXPLAIN 输出 的结果类似如下:

```
mysql> EXPLAIN (SELECT * FROM user_info  WHERE id IN (1, 2, 3))
    -> UNION
    -> (SELECT * FROM user_info WHERE id IN (3, 4, 5));
+
| id | select_type  | table      | partitions | type  | possible_keys | key     | key_len | ref  | rows | filtered | Extra           |
+
|  1 | PRIMARY      | user_info  | NULL       | range | PRIMARY       | PRIMARY | 8       | NULL |    3 |   100.00 | Using where     |
|  2 | UNION        | user_info  | NULL       | range | PRIMARY       | PRIMARY | 8       | NULL |    3 |   100.00 | Using where     |
| NULL | UNION RESULT | <union1,2> | NULL       | ALL   | NULL          | NULL    | NULL    | NULL | NULL |     NULL | Using temporary |
+
3 rows in set, 1 warning (0.00 sec)

```

### table

表示查询涉及的表或衍生表

### type

`type` 字段比较重要, 它提供了判断查询是否高效的重要依据依据. 通过 `type` 字段, 我们判断此次查询是 `全表扫描` 还是 `索引扫描` 等.

#### type 常用类型

type 常用的取值有:

*   `system`: 表中只有一条数据. 这个类型是特殊的 `const` 类型.
    
*   `const`: 针对主键或唯一索引的等值查询扫描, 最多只返回一行数据. const 查询速度非常快, 因为它仅仅读取一次即可.  
    例如下面的这个查询, 它使用了主键索引, 因此 `type` 就是 `const` 类型的.
    

```
mysql> explain select * from user_info where id = 2\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: user_info
   partitions: NULL
         type: const
possible_keys: PRIMARY
          key: PRIMARY
      key_len: 8
          ref: const
         rows: 1
     filtered: 100.00
        Extra: NULL
1 row in set, 1 warning (0.00 sec)

```

*   `eq_ref`: 此类型通常出现在多表的 join 查询, 表示对于前表的每一个结果, 都只能匹配到后表的一行结果. 并且查询的比较操作通常是 `=`, 查询效率较高. 例如:
    

```
mysql> EXPLAIN SELECT * FROM user_info, order_info WHERE user_info.id = order_info.user_id\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: order_info
   partitions: NULL
         type: index
possible_keys: user_product_detail_index
          key: user_product_detail_index
      key_len: 314
          ref: NULL
         rows: 9
     filtered: 100.00
        Extra: Using where; Using index
*************************** 2. row ***************************
           id: 1
  select_type: SIMPLE
        table: user_info
   partitions: NULL
         type: eq_ref
possible_keys: PRIMARY
          key: PRIMARY
      key_len: 8
          ref: test.order_info.user_id
         rows: 1
     filtered: 100.00
        Extra: NULL
2 rows in set, 1 warning (0.00 sec)

```

*   `ref`: 此类型通常出现在多表的 join 查询, 针对于非唯一或非主键索引, 或者是使用了 `最左前缀` 规则索引的查询.  
    例如下面这个例子中, 就使用到了 `ref` 类型的查询:
    

```
mysql> EXPLAIN SELECT * FROM user_info, order_info WHERE user_info.id = order_info.user_id AND order_info.user_id = 5\G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: user_info
   partitions: NULL
         type: const
possible_keys: PRIMARY
          key: PRIMARY
      key_len: 8
          ref: const
         rows: 1
     filtered: 100.00
        Extra: NULL
*************************** 2. row ***************************
           id: 1
  select_type: SIMPLE
        table: order_info
   partitions: NULL
         type: ref
possible_keys: user_product_detail_index
          key: user_product_detail_index
      key_len: 9
          ref: const
         rows: 1
     filtered: 100.00
        Extra: Using index
2 rows in set, 1 warning (0.01 sec)

```

*   `range`: 表示使用索引范围查询, 通过索引字段范围获取表中部分数据记录. 这个类型通常出现在 =, <>,>, >=, <, <=, IS NULL, <=>, BETWEEN, IN() 操作中.  
    当 `type` 是 `range` 时, 那么 EXPLAIN 输出的 `ref` 字段为 NULL, 并且 `key_len` 字段是此次查询中使用到的索引的最长的那个.
    

例如下面的例子就是一个范围查询:

```
mysql> EXPLAIN SELECT *
    ->         FROM user_info
    ->         WHERE id BETWEEN 2 AND 8 \G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: user_info
   partitions: NULL
         type: range
possible_keys: PRIMARY
          key: PRIMARY
      key_len: 8
          ref: NULL
         rows: 7
     filtered: 100.00
        Extra: Using where
1 row in set, 1 warning (0.00 sec)

```

*   `index`: 表示全索引扫描 (full index scan), 和 ALL 类型类似, 只不过 ALL 类型是全表扫描, 而 index 类型则仅仅扫描所有的索引, 而不扫描数据.  
    `index` 类型通常出现在: 所要查询的数据直接在索引树中就可以获取到, 而不需要扫描数据. 当是这种情况时, Extra 字段 会显示 `Using index`.
    

例如:

```
mysql> EXPLAIN SELECT name FROM  user_info \G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: user_info
   partitions: NULL
         type: index
possible_keys: NULL
          key: name_index
      key_len: 152
          ref: NULL
         rows: 10
     filtered: 100.00
        Extra: Using index
1 row in set, 1 warning (0.00 sec)

```

上面的例子中, 我们查询的 name 字段恰好是一个索引, 因此我们直接从索引中获取数据就可以满足查询的需求了, 而不需要查询表中的数据. 因此这样的情况下, type 的值是 `index`, 并且 Extra 的值是 `Using index`.

*   ALL: 表示全表扫描, 这个类型的查询是性能最差的查询之一. 通常来说, 我们的查询不应该出现 ALL 类型的查询, 因为这样的查询在数据量大的情况下, 对数据库的性能是巨大的灾难. 如一个查询是 ALL 类型查询, 那么一般来说可以对相应的字段添加索引来避免.  
    下面是一个全表扫描的例子, 可以看到, 在全表扫描时, possible_keys 和 key 字段都是 NULL, 表示没有使用到索引, 并且 rows 十分巨大, 因此整个查询效率是十分低下的.
    

```
mysql> EXPLAIN SELECT age FROM  user_info WHERE age = 20 \G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: user_info
   partitions: NULL
         type: ALL
possible_keys: NULL
          key: NULL
      key_len: NULL
          ref: NULL
         rows: 10
     filtered: 10.00
        Extra: Using where
1 row in set, 1 warning (0.00 sec)

```

#### type 类型的性能比较

通常来说, 不同的 type 类型的性能关系如下:  
`ALL < index < range ~ index_merge < ref < eq_ref < const < system`  
`ALL` 类型因为是全表扫描, 因此在相同的查询条件下, 它是速度最慢的.  
而 `index` 类型的查询虽然不是全表扫描, 但是它扫描了所有的索引, 因此比 ALL 类型的稍快.  
后面的几种类型都是利用了索引来查询数据, 因此可以过滤部分或大部分数据, 因此查询效率就比较高了.

### possible_keys

`possible_keys` 表示 MySQL 在查询时, 能够使用到的索引. 注意, 即使有些索引在 `possible_keys` 中出现, 但是并不表示此索引会真正地被 MySQL 使用到. MySQL 在查询时具体使用了哪些索引, 由 `key` 字段决定.

### key

此字段是 MySQL 在当前查询时所真正使用到的索引.

### key_len

表示查询优化器使用了索引的字节数. 这个字段可以评估组合索引是否完全被使用, 或只有最左部分字段被使用到.  
key_len 的计算规则如下:

*   字符串
    
    *   char(n): n 字节长度
        
    *   varchar(n): 如果是 utf8 编码, 则是 3 _n + 2 字节; 如果是 utf8mb4 编码, 则是 4_ n + 2 字节.
        
*   数值类型:
    
    *   TINYINT: 1 字节
        
    *   SMALLINT: 2 字节
        
    *   MEDIUMINT: 3 字节
        
    *   INT: 4 字节
        
    *   BIGINT: 8 字节
        
*   时间类型
    
    *   DATE: 3 字节
        
    *   TIMESTAMP: 4 字节
        
    *   DATETIME: 8 字节
        
*   字段属性: NULL 属性 占用一个字节. 如果一个字段是 NOT NULL 的, 则没有此属性.
    

我们来举两个简单的栗子:

```
mysql> EXPLAIN SELECT * FROM order_info WHERE user_id < 3 AND product_name = 'p1' AND productor = 'WHH' \G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: order_info
   partitions: NULL
         type: range
possible_keys: user_product_detail_index
          key: user_product_detail_index
      key_len: 9
          ref: NULL
         rows: 5
     filtered: 11.11
        Extra: Using where; Using index
1 row in set, 1 warning (0.00 sec)

```

上面的例子是从表 order_info 中查询指定的内容, 而我们从此表的建表语句中可以知道, 表 `order_info` 有一个联合索引:

```
KEY `user_product_detail_index` (`user_id`, `product_name`, `productor`)

```

不过此查询语句 `WHERE user_id < 3 AND product_name = 'p1' AND productor = 'WHH'` 中, 因为先进行 user_id 的范围查询, 而根据 `最左前缀匹配` 原则, 当遇到范围查询时, 就停止索引的匹配, 因此实际上我们使用到的索引的字段只有 `user_id`, 因此在 `EXPLAIN` 中, 显示的 key_len 为 9. 因为 user_id 字段是 BIGINT, 占用 8 字节, 而 NULL 属性占用一个字节, 因此总共是 9 个字节. 若我们将 user_id 字段改为 `BIGINT(20) NOT NULL DEFAULT '0'`, 则 key_length 应该是 8.

上面因为 `最左前缀匹配` 原则, 我们的查询仅仅使用到了联合索引的 `user_id` 字段, 因此效率不算高.

接下来我们来看一下下一个例子:

```
mysql> EXPLAIN SELECT * FROM order_info WHERE user_id = 1 AND product_name = 'p1' \G;
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: order_info
   partitions: NULL
         type: ref
possible_keys: user_product_detail_index
          key: user_product_detail_index
      key_len: 161
          ref: const,const
         rows: 2
     filtered: 100.00
        Extra: Using index
1 row in set, 1 warning (0.00 sec)

```

这次的查询中, 我们没有使用到范围查询, key_len 的值为 161. 为什么呢? 因为我们的查询条件 `WHERE user_id = 1 AND product_name = 'p1'` 中, 仅仅使用到了联合索引中的前两个字段, 因此 `keyLen(user_id) + keyLen(product_name) = 9 + 50 * 3 + 2 = 161`

### rows

rows 也是一个重要的字段. MySQL 查询优化器根据统计信息, 估算 SQL 要查找到结果集需要扫描读取的数据行数.  
这个值非常直观显示 SQL 的效率好坏, 原则上 rows 越少越好.

### Extra

EXplain 中的很多额外的信息会在 Extra 字段显示, 常见的有以下几种内容:

*   Using filesort  
    当 Extra 中有 `Using filesort` 时, 表示 MySQL 需额外的排序操作, 不能通过索引顺序达到排序效果. 一般有 `Using filesort`, 都建议优化去掉, 因为这样的查询 CPU 资源消耗大.
    

例如下面的例子:

```
mysql> EXPLAIN SELECT * FROM order_info ORDER BY product_name \G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: order_info
   partitions: NULL
         type: index
possible_keys: NULL
          key: user_product_detail_index
      key_len: 253
          ref: NULL
         rows: 9
     filtered: 100.00
        Extra: Using index; Using filesort
1 row in set, 1 warning (0.00 sec)

```

我们的索引是

```
KEY `user_product_detail_index` (`user_id`, `product_name`, `productor`)

```

但是上面的查询中根据 `product_name` 来排序, 因此不能使用索引进行优化, 进而会产生 `Using filesort`.  
如果我们将排序依据改为 `ORDER BY user_id, product_name`, 那么就不会出现 `Using filesort` 了. 例如:

```
mysql> EXPLAIN SELECT * FROM order_info ORDER BY user_id, product_name \G
*************************** 1. row ***************************
           id: 1
  select_type: SIMPLE
        table: order_info
   partitions: NULL
         type: index
possible_keys: NULL
          key: user_product_detail_index
      key_len: 253
          ref: NULL
         rows: 9
     filtered: 100.00
        Extra: Using index
1 row in set, 1 warning (0.00 sec)

```

*   Using index  
    "覆盖索引扫描", 表示查询在索引树中就可查找所需数据, 不用扫描表数据文件, 往往说明性能不错
    
*   Using temporary  
    查询有使用临时表, 一般出现于排序, 分组和多表 join 的情况, 查询效率不高, 建议优化.