> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483762&idx=1&sn=1f121db6552a2e77d53c500fa812fc6c&chksm=fbdb18f3ccac91e58229dd3efd09c876722d58863c2b6ff6d444b0825a955a776ced947d8470&scene=21#wechat_redirect

前言
==

本文快速回顾了 Java 中容器的知识点，用作面试复习，事半功倍。

上篇：容器概览，容器中用到的设计模式，List 源码

中篇：Map 源码

**下篇**：Set 源码，容器总结

其它知识点复习手册
---------

*   [Java 基础知识点面试手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=1&sn=de2751593468f470902b698c19f8987f&chksm=fbdb18d3ccac91c56939e55cd1f0ca1b4753fd178d229440ecbf89752f5e0d518acd453e2497&scene=21#wechat_redirect)  
    
*   [Java 基础知识点面试手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=2&sn=5610afab774b4114110c993fd0fdc43d&chksm=fbdb18d3ccac91c5e3d2978e3a780b14d09e97c997a5c50410d1adb1930da76f40238d8f409d&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483743&idx=1&sn=cc38aab9429905ddc757b529a386d1dd&chksm=fbdb18deccac91c8d0be8b3ae0e4266bb08ead73a2e57f2c977705e7b26ceaaec7aff5d5c67c&scene=21#wechat_redirect)
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（中）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483753&idx=1&sn=74b8180dc1a1804c355174ed34e6e33d&chksm=fbdb18e8ccac91fe3ce31ed9713bf23598e7f98dcb6d5a22b79aa92b1c773134bfebe71fbbca&scene=21#wechat_redirect)
    

HashSet
-------

http://wiki.jikexueyuan.com/project/java-collection/hashset.html

https://segmentfault.com/a/1190000014391402

### 关键词：

*   默认容量 16，扩容两倍，加载因子 0.75
    
*   允许元素为 null
    
*   实现 Set 接口
    
*   不保证迭代顺序
    
*   非同步
    
*   初始容量非常影响迭代性能
    
*   底层实际上是一个 HashMap 实例
    
    > public HashSet() {map = new HashMap<>();}
    

如果添加的是在 HashSet 中不存在的，则返回 true；如果添加的元素已经存在，返回 false。

**对于 HashSet 中保存的对象，请注意正确重写其 equals 和 hashCode 方法，以保证放入的对象的唯一性。**

### HashSet 和 HashMap 的区别

重要：

**1. HashMap 中使用键对象来计算 hashcode 值**

**2. HashSet 使用成员对象来计算 hashcode 值，对于两个对象来说 hashcode 可能相同，所以 equals() 方法用来判断对象的相等性，如果两个对象不同的话，那么返回 false**

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZS0sGamWPFLLRhR5jT8vuo52nTSMGicIsMokytqHoLwYRW8UjYcTrgiank4ryUWkERTSWtWiaTX8ehw/640?wx_fmt=png)在这里插入图片描述

TreeSet
-------

### 关键词

*   实现 NavigableSet 接口
    
*   可以实现排序功能
    
*   底层实际上是一个 TreeMap 实例
    
*   非同步
    
*   不允许为 null
    

LinkedHashSet
-------------

### 关键词

*   迭代是有序的
    
*   允许为 null
    
*   底层实际上是一个 HashMap + 双向链表实例 (其实就是 LinkedHashMap)
    
*   非同步
    
*   性能比 HashSet 差一丢丢，因为要维护一个双向链表
    
*   **初始容量与迭代无关（与 LinkedHashMap 相同）**，因为 LinkedHashSet 迭代的是双向链表
    

总结 Set
------

HashSet：

*   无序，允许为 null，底层是 HashMap(散列表 + 红黑树)，非线程同步
    

TreeSet：

*   有序，不允许为 null，底层是 TreeMap(红黑树), 非线程同步
    

LinkedHashSet：

*   迭代有序，允许为 null，底层是 HashMap + 双向链表，非线程同步
    

WeekHashMap
-----------

### 存储结构

WeakHashMap 的 Entry 继承自 WeakReference，被 WeakReference 关联的**对象在下一次垃圾回收时会被回收**。

WeakHashMap 主要用来实现缓存，通过使用 WeakHashMap 来引用缓存对象，由 JVM 对这部分缓存进行回收。

```
1private static class Entry<K,V> extends WeakReference<Object> implements Map.Entry<K,V>
2


```

### ConcurrentCache

Tomcat 中的 ConcurrentCache 使用了 WeakHashMap 来实现缓存功能。

ConcurrentCache 采取的是分代缓存：

*   经常使用的对象放入 eden 中，eden 使用 ConcurrentHashMap 实现，不用担心会被回收（伊甸园）；
    
*   不常用的对象放入 longterm，longterm 使用 WeakHashMap 实现，这些老对象会被垃圾收集器回收。
    
*   当调用 get() 方法时，会先从 eden 区获取，如果没有找到的话再到 longterm 获取，当从 longterm 获取到就把对象放入 eden 中，从而保证经常被访问的节点不容易被回收。
    
*   当调用 put() 方法时，如果 eden 的大小超过了 size，那么就将 eden 中的所有对象都放入 longterm 中，利用虚拟机回收掉一部分不经常使用的对象。
    

```
 1public final class ConcurrentCache<K, V> {
 2
 3    private final int size;
 4
 5    private final Map<K, V> eden;
 6
 7    private final Map<K, V> longterm;
 8
 9    public ConcurrentCache(int size) {
10        this.size = size;
11        this.eden = new ConcurrentHashMap<>(size);
12        this.longterm = new WeakHashMap<>(size);
13    }
14
15    public V get(K k) {
16        V v = this.eden.get(k);
17        if (v == null) {
18            v = this.longterm.get(k);
19            if (v != null)
20                this.eden.put(k, v);
21        }
22        return v;
23    }
24
25    public void put(K k, V v) {
26        if (this.eden.size() >= size) {
27            this.longterm.putAll(this.eden);
28            this.eden.clear();
29        }
30        this.eden.put(k, v);
31    }
32}


```

常见问题总结
======

Enumeration 和 Iterator 接口的区别
----------------------------

Iterator 替代了 Enumeration，Enumeration 是一个旧的迭代器了。

与 Enumeration 相比，Iterator 更加安全，因为当一个集合正在被遍历的时候，**它会阻止其它线程去修改集合。**

区别有三点：

*   Iterator 的方法名比 Enumeration 更科学
    
*   **Iterator 有 fail-fast 机制，比 Enumeration 更安全**
    
*   Iterator 能够删除元素，Enumeration 并不能删除元素
    

ListIterator 有什么特点
------------------

*   ListIterator 继承了 Iterator 接口，它用于遍历 List 集合的元素。
    
*   ListIterator 可以实现双向遍历, 添加元素，设置元素
    

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZS0sGamWPFLLRhR5jT8vuoxWnL0I8YSWYpDiaLLrsJSXfV7UpJGPE8rufYtE0wbVzCngicBXOMiarfA/640?wx_fmt=png)在这里插入图片描述

与 Java 集合框架相关的有哪些最好的实践
----------------------

如果是单列的集合，我们考虑用 Collection 下的子接口 ArrayList 和 Set。

如果是映射，我们就考虑使用 Map

*   是否需要同步：去找线程安全的集合类使用
    
*   迭代时是否需要有序 (插入顺序有序)：去找 Linked 双向列表结构的
    
*   是否需要排序 (自然顺序或者手动排序)：去找 Tree 红黑树类型的 (JDK1.8)
    
*   估算存放集合的数据量有多大，无论是 List 还是 Map，它们实现动态增长，都是有性能消耗的。在初始集合的时候给出一个合理的容量会减少动态增长时的消耗
    
*   使用泛型，避免在运行时出现 ClassCastException
    
*   尽可能使用 Collections 工具类，或者获取只读、同步或空的集合，而非编写自己的实现。它将会提供代码重用性，它有着更好的稳定性和可维护性
    

参考
==

*   https://github.com/CyC2018/CS-Notes/blob/master/docs/notes/Java%20%E5%AE%B9%E5%99%A8.md
    
*   公众号：Java3y
    
*   Eckel B. Java 编程思想 [M]. 机械工业出版社, 2002.
    
*   Java Collection Framework
    
*   Iterator 模式
    
*   Java 8 系列之重新认识 HashMap
    
*   What is difference between HashMap and Hashtable in Java?
    
*   Java 集合之 HashMap
    
*   The principle of ConcurrentHashMap analysis
    
*   探索 ConcurrentHashMap 高并发性的实现机制
    
*   HashMap 相关面试题及其解答
    
*   Java 集合细节（二）：asList 的缺陷
    
*   Java Collection Framework – The LinkedList Class
    

关注我
===

本人目前为后台开发工程师，主要关注 Python 爬虫，后台开发等相关技术。

### 原创博客主要内容

*   笔试面试复习知识点手册
    
*   Leetcode 算法题解析（前 150 题）
    
*   剑指 offer 算法题解析
    
*   Python 爬虫相关实战
    
*   后台开发相关实战
    

### 同步更新以下博客

**Csdn**

http://blog.csdn.net/qqxx6661

拥有专栏：Leetcode 题解（Java/Python）、爬虫开发

**知乎**

https://www.zhihu.com/people/yang-zhen-dong-1/

拥有专栏：码农面试助攻手册

**掘金**

https://juejin.im/user/5b48015ce51d45191462ba55

**简书**

https://www.jianshu.com/u/b5f225ca2376

### 电商价格监控网站

本人长期维护的个人项目，完全免费，请大家多多支持。

**实现功能**

*   **京东商品监控**：设置商品 ID 和预期价格，当商品价格【低于】设定的预期价格后自动发送邮件提醒用户。(一小时以内)
    
*   **京东品类商品监控**：用户订阅特定品类后，该类降价幅度大于 7 折的【自营商品】会被选出并发送邮件提醒用户。
    
*   品类商品浏览，商品历史价格曲线，商品历史最高最低价
    
*   持续更新中…
    

**网站地址**

https://pricemonitor.online/

### 个人公众号：Rude3Knife

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbVMtJ5I3ibmQPicV01U1zWxlkhnGRIytmOcUqrfcaMHTwy2qUEk4iaV5VvZTCNHV1UWkkp5UCMib4Yvg/640?wx_fmt=png)