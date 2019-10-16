> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s/U-0a4hGELrKAgCNjpvtx5w

前言


------

看了很多关于索引的博客，讲的大同小异。但是始终没有让我明白关于索引的一些概念，如 B-Tree 索引，Hash 索引，唯一索引…. 或许有很多人和我一样，没搞清楚概念就开始研究 B-Tree，B+Tree 等结构，导致在面试的时候答非所问！

索引是什么?


----------

索引是帮助 MySQL 高效获取数据的数据结构。

索引能干什么?


-----------

提高数据查询的效率。

索引：排好序的快速查找数据结构！索引会影响 where 后面的查找，和 order by 后面的排序。

索引的分类


---------

1. 从存储结构上来划分：BTree 索引（B-Tree 或 B+Tree 索引），Hash 索引，full-index 全文索引，R-Tree 索引。

2. 从应用层次来分：普通索引，唯一索引，复合索引。

3. 根据中数据的物理顺序与键值的逻辑（索引）顺序关系：聚集索引，非聚集索引。

1）中所描述的是索引存储时保存的形式，

2）是索引使用过程中进行的分类，两者是不同层次上的划分。不过平时讲的索引类型一般是指在应用层次的划分。

就像手机分类，安卓手机，IOS 手机 与 华为手机，苹果手机，OPPO 手机一样。

*   普通索引：即一个索引只包含单个列，一个表可以有多个单列索引
    
*   唯一索引：索引列的值必须唯一，但允许有空值
    
*   复合索引：即一个索引包含多个列
    
*   聚簇索引 (聚集索引)：并不是一种单独的索引类型，而是一种数据存储方式。具体细节取决于不同的实现，InnoDB 的聚簇索引其实就是在同一个结构中保存了 B-Tree 索引 (技术上来说是 B+Tree) 和数据行。
    
*   非聚簇索引：不是聚簇索引，就是非聚簇索引（认真脸）。
    

索引的底层实现


-----------

> mysql 默认存储引擎 innodb 只显式支持 B-Tree(从技术上来说是 B+Tree) 索引，对于频繁访问的表，innodb 会透明建立自适应 hash 索引，即在 B 树索引基础上建立 hash 索引，可以显著提高查找效率，对于客户端是透明的，不可控制的，隐式的。

不谈存储引擎，只讨论实现 (抽象)

Hash 索引

基于哈希表实现，只有精确匹配索引所有列的查询才有效，对于每一行数据，存储引擎都会对所有的索引列计算一个哈希码（hash code），并且 Hash 索引将所有的哈希码存储在索引中，同时在索引表中保存指向每个数据行的指针。

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyq7uRWyt3WTIH3yiaV5sFrj1B2QXrWwWR0TwLoOrBvNibBxFibZ6OnowBib9qzyWPyJeZUoy3lVFwy8Pg/640?wx_fmt=png)

B-Tree 能加快数据的访问速度，因为存储引擎不再需要进行全表扫描来获取数据，数据分布在各个节点之中。

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyq7uRWyt3WTIH3yiaV5sFrj1996Tyt503ibW60PeFzjIicwibvJWGFesweyMbI0mubDYFe3w7NXbVPicEQ/640?wx_fmt=png)

是 B-Tree 的改进版本，同时也是数据库索引索引所采用的存储结构。数据都在叶子节点上，并且增加了顺序访问指针，每个叶子节点都指向相邻的叶子节点的地址。相比 B-Tree 来说，进行范围查找时只需要查找两个节点，进行遍历即可。而 B-Tree 需要获取所有节点，相比之下 B+Tree 效率更高。

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyq7uRWyt3WTIH3yiaV5sFrj1UibzW0BtagibAaO0ayLGhOX4D0jCzkff0SHz4iab1wWmJCz5v6XiaicRqoQ/640?wx_fmt=png)

案例：假设有一张学生表，id 为主键

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyq7uRWyt3WTIH3yiaV5sFrj10L6vicOIBOUdKhU0EnQgicAibxkJwJedicnXP8PPXrA1EpvF7dXfHm0qcg/640?wx_fmt=png)

在 MyISAM 引擎中的实现（二级索引也是这样实现的）

![](https://mmbiz.qpic.cn/mmbiz_jpg/2LlmEpiamhyq7uRWyt3WTIH3yiaV5sFrj1NDSTIJe26nfiboHXoyibEzu7TmgkultcxDwNQxdk5vfd4MuntwWHicPnw/640?wx_fmt=jpeg)

在 InnoDB 中的实现

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyq7uRWyt3WTIH3yiaV5sFrj12IJUL39dic37H8zeUiaeuCb78z7sAfCCZUefJ9yKw75ftqIA68jGgiajw/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/2LlmEpiamhyq7uRWyt3WTIH3yiaV5sFrj1rXpwMUfmFwQZ9p9PEdOhSnWlC6qSKHAPyw6Ux0RKHMmgZROOO2f6dQ/640?wx_fmt=png)

问题


------

问：为什么索引结构默认使用 B-Tree，而不是 hash，二叉树，红黑树？

hash：虽然可以快速定位，但是没有顺序，IO 复杂度高。

二叉树：树的高度不均匀，不能自平衡，查找效率跟数据有关（树的高度），并且 IO 代价高。

红黑树：树的高度随着数据量增加而增加，IO 代价高。

问：为什么官方建议使用自增长主键作为索引。

结合 B+Tree 的特点，自增主键是连续的，在插入过程中尽量减少页分裂，即使要进行页分裂，也只会分裂很少一部分。并且能减少数据的移动，每次插入都是插入到最后。总之就是减少分裂和移动的频率。

插入连续的数据：

![](https://mmbiz.qpic.cn/mmbiz_gif/2LlmEpiamhyq7uRWyt3WTIH3yiaV5sFrj1iaKVfFd9AKaiczwYibeCe2cgzgiaaackkImBng1boF9q25djto2ewJDP7Q/640?wx_fmt=gif)

插入非连续的数据

![](https://mmbiz.qpic.cn/mmbiz_gif/2LlmEpiamhyq7uRWyt3WTIH3yiaV5sFrj1XsRFxobib4iavApDKZ8ArfUbtw7PibibvITBosdKhjtXccApibeNJ6KMa8Q/640?wx_fmt=gif)

↓↓↓Java 架构师技术分享免费体验课