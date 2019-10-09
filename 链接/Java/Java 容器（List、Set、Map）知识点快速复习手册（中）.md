> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483753&idx=1&sn=74b8180dc1a1804c355174ed34e6e33d&chksm=fbdb18e8ccac91fe3ce31ed9713bf23598e7f98dcb6d5a22b79aa92b1c773134bfebe71fbbca&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58J6Nf4P8fEtUQ6Nx6icjefhyLcQMOyKnRicFWbcloNoiaB5HWiaTDD56CuQ/640?wx_fmt=png)

前言
==

本文快速回顾了 Java 中容器的知识点，用作面试复习，事半功倍。

上篇：主要为容器概览，容器中用到的设计模式，List 源码

**中篇**：Map 源码

下篇：Set 源码，容器总结

其它知识点复习手册  

------------

*   [Java 基础知识点面试手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=1&sn=de2751593468f470902b698c19f8987f&chksm=fbdb18d3ccac91c56939e55cd1f0ca1b4753fd178d229440ecbf89752f5e0d518acd453e2497&scene=21#wechat_redirect)
    
*   [Java 基础知识点面试手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=2&sn=5610afab774b4114110c993fd0fdc43d&chksm=fbdb18d3ccac91c5e3d2978e3a780b14d09e97c997a5c50410d1adb1930da76f40238d8f409d&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483743&idx=1&sn=cc38aab9429905ddc757b529a386d1dd&chksm=fbdb18deccac91c8d0be8b3ae0e4266bb08ead73a2e57f2c977705e7b26ceaaec7aff5d5c67c&scene=21#wechat_redirect)
    

HashMap
-------

http://wiki.jikexueyuan.com/project/java-collection/hashmap.html

源码分析：

https://segmentfault.com/a/1190000014293372

### 关键词

*   初始容量 16
    
*   扩容是 2 倍，加载因子 0.75
    
*   头插法
    
*   0 桶存放 null
    
*   从 JDK 1.8 开始，一个桶存储的链表长度大于 8 时会将链表转换为红黑树（前提：键值对要超过 64 个）
    
*   自动地将传入的**容量转换为 2 的幂次方**
    

*   **保证运算速度**：确保**用位运算代替模运算**来计算桶下标。hash& (length-1) 运算等价于对 length 取模。
    
*   **hash 均匀分布**：数据在数组上分布就比较均匀，并且能够利用全部二进制位，也就是说**碰撞的几率小**，
    

*   table 数组 + Entry
    
    [] 链表（散列表），红黑树
*   扩容操作需要把键值对重新插入新的 table 中，**重新计算所有 key 有特殊机制（JDK1.8 后）**
    

### 存储结构

hashMap 的一个内部类 Node：

```
1static class Node<K,V> implements Map.Entry<K,V> {
2        final int hash;
3        final K key;
4        V value;
5        Node<K,V> next; //链表结构，存储下一个元素


```

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58Fv4SAupC51DTLSJfOpgbUibhjFibFrQjDOsRiaHB9cq5zicauhy8G31bMw/640?wx_fmt=png)在这里插入图片描述

Node 内部包含了一个 Entry 类型的数组 table，数组中的每个位置被当成一个桶。

```
1transient Entry[] table;


```

Entry 存储着键值对。它包含了四个字段，从 next 字段我们可以看出 Entry 是一个链表。即数组中的每个位置被当成一个桶，一个桶存放一个链表。

HashMap 使用拉链法来解决冲突，同一个链表中存放哈希值相同的 Entry。

```
 1static class Entry<K,V> implements Map.Entry<K,V> {
 2    final K key;
 3    V value;
 4    Entry<K,V> next;
 5    int hash;
 6
 7    Entry(int h, K k, V v, Entry<K,V> n) {
 8        value = v;
 9        next = n;
10        key = k;
11        hash = h;
12    }
13
14    public final K getKey() {
15        return key;
16    }
17
18    public final V getValue() {
19        return value;
20    }
21
22    public final V setValue(V newValue) {
23        V oldValue = value;
24        value = newValue;
25        return oldValue;
26    }
27
28    public final boolean equals(Object o) {
29        if (!(o instanceof Map.Entry))
30            return false;
31        Map.Entry e = (Map.Entry)o;
32        Object k1 = getKey();
33        Object k2 = e.getKey();
34        if (k1 == k2 || (k1 != null && k1.equals(k2))) {
35            Object v1 = getValue();
36            Object v2 = e.getValue();
37            if (v1 == v2 || (v1 != null && v1.equals(v2)))
38                return true;
39        }
40        return false;
41    }
42
43    public final int hashCode() {
44        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
45    }
46
47    public final String toString() {
48        return getKey() + "=" + getValue();
49    }
50}


```

### 构造器

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58yJqoYlUnibpVyc8LyyVWWZibc9GBVrIsqhrzRMqbJyjHHpERUYxkwTyQ/640?wx_fmt=png)在这里插入图片描述

构造时就会调用 tableSizeFor()：返回一个大于输入参数且最近的 2 的整数次幂。

```
1static final int tableSizeFor(int cap) {
2    int n = cap - 1;
3    n |= n >>> 1;
4    n |= n >>> 2;
5    n |= n >>> 4;
6    n |= n >>> 8;
7    n |= n >>> 16;
8    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
9}


```

### 拉链法

应该注意到链表的插入是以**头插法**方式进行的

```
1HashMap<String, String> map = new HashMap<>();
2map.put("K1", "V1");
3map.put("K2", "V2");
4map.put("K3", "V3");


```

*   新建一个 HashMap，默认大小为 16；
    
*   插入 <K1,V1> 键值对，先计算 K1 的 hashCode 为 115，使用除留余数法得到所在的桶下标 115%16=3。
    
*   插入 <K2,V2> 键值对，先计算 K2 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6。
    
*   插入 <K3,V3> 键值对，先计算 K3 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6，插在 <K2,V2> 前面。
    

查找需要分成两步进行：

*   计算键值对所在的桶；
    
*   在链表上顺序查找，时间复杂度显然和链表的长度成正比。
    

### put 操作

*   当我们 put 的时候，如果 key 存在了，那么新的 value 会代替旧的 value
    
*   如果 key **存在的情况下，该方法返回的是旧的 value，**
    
*   如果 key **不存在，那么返回 null。**
    

```
 1public V put(K key, V value) {
 2    if (table == EMPTY_TABLE) {
 3        inflateTable(threshold);
 4    }
 5    // 键为 null 单独处理
 6    if (key == null)
 7        return putForNullKey(value);
 8    int hash = hash(key);
 9    // 确定桶下标
10    int i = indexFor(hash, table.length);
11    // 先找出是否已经存在键为 key 的键值对，如果存在的话就更新这个键值对的值为 value
12    for (Entry<K,V> e = table[i]; e != null; e = e.next) {
13        Object k;
14        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
15            V oldValue = e.value;
16            e.value = value;
17            e.recordAccess(this);
18            return oldValue;
19        }
20    }
21
22    modCount++;
23    // 插入新键值对
24    addEntry(hash, key, value, i);
25    return null;
26}


```

HashMap 允许插入键为 null 的键值对。但是因为无法调用 null 的 hashCode() 方法，也就无法确定该键值对的桶下标，只能通过强制指定一个桶下标来存放。HashMap 使用第 0 个桶存放键为 null 的键值对。

```
 1private V putForNullKey(V value) {
 2    for (Entry<K,V> e = table[0]; e != null; e = e.next) {
 3        if (e.key == null) {
 4            V oldValue = e.value;
 5            e.value = value;
 6            e.recordAccess(this);
 7            return oldValue;
 8        }
 9    }
10    modCount++;
11    addEntry(0, null, value, 0);
12    return null;
13}


```

使用链表的头插法，也就是新的键值对插在链表的头部，而不是链表的尾部。

```
 1void addEntry(int hash, K key, V value, int bucketIndex) {
 2    if ((size >= threshold) && (null != table[bucketIndex])) {
 3        resize(2 * table.length);
 4        hash = (null != key) ? hash(key) : 0;
 5        bucketIndex = indexFor(hash, table.length);
 6    }
 7
 8    createEntry(hash, key, value, bucketIndex);
 9}
10
11void createEntry(int hash, K key, V value, int bucketIndex) {
12    Entry<K,V> e = table[bucketIndex];
13    // 头插法，链表头部指向新的键值对
14    table[bucketIndex] = new Entry<>(hash, key, value, e);
15    size++;
16}


```

```
1Entry(int h, K k, V v, Entry<K,V> n) {
2    value = v;
3    next = n;
4    key = k;
5    hash = h;
6}


```

#### 补充：hashmap 里 hash 方法的高位优化：

https://www.cnblogs.com/liujinhong/p/6576543.html

https://note.youdao.com/yws/res/18743/50AADC7BB42845B29CDA293FC409250C?ynotemdtimestamp=1548155508277

设计者将 key 的哈希值的高位也做了运算 (**与高 16 位做异或运算，使得在做 & 运算时，此时的低位实际上是高位与低位的结合**)，这就增加了随机性，减少了碰撞冲突的可能性！

**为何要这么做？**

**table 的长度都是 2 的幂，因此 index 仅与 hash 值的低 n 位有关，hash 值的高位都被与操作置为 0 了。**

这样做很容易产生碰撞。**设计者权衡了 speed, utility, and quality，将高 16 位与低 16 位异或来减少这种影响**。设计者考虑到现在的 hashCode 分布的已经很不错了，而且当发生较大碰撞时也用树形存储降低了冲突。仅仅异或一下，既减少了系统的开销，也不会造成的因为高位没有参与下标的计算 (table 长度比较小时)，从而引起的碰撞。

### 确定桶下标

很多操作都需要先确定一个键值对所在的桶下标。

```
1int hash = hash(key);
2int i = indexFor(hash, table.length);


```

**4.1 计算 hash 值**

```
 1final int hash(Object k) {
 2    int h = hashSeed;
 3    if (0 != h && k instanceof String) {
 4        return sun.misc.Hashing.stringHash32((String) k);
 5    }
 6
 7    h ^= k.hashCode();
 8
 9    // This function ensures that hashCodes that differ only by
10    // constant multiples at each bit position have a bounded
11    // number of collisions (approximately 8 at default load factor).
12    h ^= (h >>> 20) ^ (h >>> 12);
13    return h ^ (h >>> 7) ^ (h >>> 4);
14}


```

```
1public final int hashCode() {
2    return Objects.hashCode(key) ^ Objects.hashCode(value);
3}


```

**4.2 取模**

令 x = 1<\<\4，即 \x 为 2 的 4 次方，它具有以下性质：

```
1x   : 00010000
2x-1 : 00001111


```

令一个数 y 与 x-1 做与运算，可以去除 y 位级表示的第 4 位以上数：

```
1y       : 10110010
2x-1     : 00001111
3y&(x-1) : 00000010


```

这个性质和 y 对 x 取模效果是一样的：

```
1y   : 10110010
2x   : 00010000
3y%x : 00000010


```

我们知道，位运算的代价比求模运算小的多，因此在进行这种计算时用位运算的话能带来更高的性能。

确定桶下标的最后一步是将 key 的 hash 值对桶个数取模：hash%capacity，如果能保证 capacity 为 2 的 n 次方，那么就可以将这个操作转换为位运算。

```
1static int indexFor(int h, int length) {
2    return h & (length-1);
3}


```

**当 length 总是 2 的 n 次方时，h& (length-1) 运算等价于对 length 取模，也就是 h%length**，但是 & 比 % 具有更高的效率。这看上去很简单，其实比较有玄机的，我们举个例子来说明：

<table>

*   从上面的例子中可以看出：当它们和 15-1（1110）“与” 的时候，**8 和 9 产生了相同的结果**，也就是说它们会定位到数组中的同一个位置上去，这就**产生了碰撞**，8 和 9 会被放到数组中的同一个位置上形成链表，那么查询的时候就需要遍历这个链 表，得到 8 或者 9，这样就降低了查询的效率。
    
*   同时，我们也可以发现，当数组长度为 15 的时候，hash 值会与 15-1（1110）进行 “与”，那么最后一位永远是 0，而 0001，0011，0101，1001，1011，0111，1101 **这几个位置永远都不能存放元素了**，**空间浪费相当大**，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率。
    
*   而当数组长度为 16 时，即为 2 的 n 次方时，2n-1 得到的二进制数的每个位上的值都为 1，**这使得在低位上 & 时，得到的和原 hash 的低位相同**，加之 **hash(int h) 方法对 key 的 hashCode 的进一步优化**，加入了高位计算，就使得只有相同的 hash 值的两个值才会被放到数组中的同一个位置上形成链表。
    

所以说，当数组长度为 2 的 n 次幂的时候，**不同的 key 算得得 index 相同的几率较小，那么数据在数组上分布就比较均匀，也就是说碰撞的几率小**

### 扩容 - 基本原理

设 HashMap 的 table 长度为 M，需要存储的键值对数量为 N，如果哈希函数满足均匀性的要求，那么每条链表的长度大约为 N/M，因此平均查找次数的复杂度为 O(N/M)。

为了让查找的成本降低，应该尽可能使得 N/M 尽可能小，因此需要保证 M 尽可能大，也就是说 table 要尽可能大。HashMap 采用动态扩容来根据当前的 N 值来调整 M 值，使得空间效率和时间效率都能得到保证。

和扩容相关的参数主要有：capacity、size、threshold 和 load_factor。

| 参数 | 含义 |
| --- | --- |
| capacity | table 的容量大小，默认为 16。需要注意的是 capacity 必须保证为 2 的 n 次方。 |
| size | 键值对数量。 |
| threshold | size 的临界值，当 size 大于等于 threshold 就必须进行扩容操作。 |
| loadFactor | 装载因子，table 能够使用的比例，threshold = capacity * loadFactor。 |

```
 1static final int DEFAULT_INITIAL_CAPACITY = 16;
 2
 3static final int MAXIMUM_CAPACITY = 1 << 30;
 4
 5static final float DEFAULT_LOAD_FACTOR = 0.75f;
 6
 7transient Entry[] table;
 8
 9transient int size;
10
11int threshold;
12
13final float loadFactor;
14
15transient int modCount;


```

从下面的添加元素代码中可以看出，当需要扩容时，令 capacity 为原来的两倍。

```
1void addEntry(int hash, K key, V value, int bucketIndex) {
2    Entry<K,V> e = table[bucketIndex];
3    table[bucketIndex] = new Entry<>(hash, key, value, e);
4    if (size++ >= threshold)
5        resize(2 * table.length);
6}


```

扩容使用 resize() 实现，需要注意的是，扩容操作同样需要把 oldTable 的所有键值对重新插入 newTable 中，因此这一步是很费时的。

```
 1void resize(int newCapacity) {
 2    Entry[] oldTable = table;
 3    int oldCapacity = oldTable.length;
 4    if (oldCapacity == MAXIMUM_CAPACITY) {
 5        threshold = Integer.MAX_VALUE;
 6        return;
 7    }
 8    Entry[] newTable = new Entry[newCapacity];
 9    transfer(newTable);
10    table = newTable;
11    threshold = (int)(newCapacity * loadFactor);
12}
13
14void transfer(Entry[] newTable) {
15    Entry[] src = table;
16    int newCapacity = newTable.length;
17    for (int j = 0; j < src.length; j++) {
18        Entry<K,V> e = src[j];
19        if (e != null) {
20            src[j] = null;
21            do {
22                Entry<K,V> next = e.next;
23                int i = indexFor(e.hash, newCapacity);
24                e.next = newTable[i];
25                newTable[i] = e;
26                e = next;
27            } while (e != null);
28        }
29    }
30}


```

### 扩容 - 重新计算桶下标

Rehash 优化：https://my.oschina.net/u/3568600/blog/1933764

**在进行扩容时，需要把键值对重新放到对应的桶上。HashMap 使用了一个特殊的机制，可以降低重新计算桶下标的操作。**

假设原数组长度 capacity 为 16，扩容之后 new capacity 为 32：

```
1capacity     : 00010000
2new capacity : 00100000


```

对于一个 Key，

*   它的哈希值如果在第 5 位上为 0，那么取模得到的结果和之前一样；
    
*   如果为 1，那么得到的结果为原来的结果 +16。
    

**总结：**

经过 rehash 之后，元素的位置要么是在原位置，要么是在原位置再移动 2 次幂的位置

因此，我们在扩充 HashMap 的时候，不需要像 JDK1.7 的实现那样重新计算 hash，只需要看看原来的 hash 值新增的那个 bit 是 1 还是 0 就好了，是 0 的话索引没变，是 1 的话索引变成 “原索引 + oldCap”，可以看看下图为 16 扩充为 32 的 resize 示意图：

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58SEAaa216bl40lcgSMjfvsKROSdvacJgRMIk8mtYSJhwU3A7bH5VicQA/640?wx_fmt=png)在这里插入图片描述

### 计算数组容量

HashMap 构造函数允许用户传入的容量不是 2 的 n 次方，因为它可以自动地将传入的容量转换为 2 的 n 次方。

先考虑如何求一个数的掩码，对于 10010000，它的掩码为 11111111，可以使用以下方法得到：

```
1mask |= mask >> 1    11011000
2mask |= mask >> 2    11111110
3mask |= mask >> 4    11111111
4


```

mask+1 是大于原始数字的最小的 2 的 n 次方。

```
1num     10010000
2mask+1 100000000


```

以下是 HashMap 中计算数组容量的代码：

```
1static final int tableSizeFor(int cap) {
2    int n = cap - 1;
3    n |= n >>> 1;
4    n |= n >>> 2;
5    n |= n >>> 4;
6    n |= n >>> 8;
7    n |= n >>> 16;
8    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
9}


```

### 链表转红黑树

**并不是桶子上有 8 位元素的时候它就能变成红黑树，它得同时满足我们的键值对大于 64 才行的**

这是为了避免在哈希表建立初期，多个键值对恰好被放入了同一个链表中而导致不必要的转化。

HashTable
---------

### 关键词：

*   Hashtable 的迭代器不是 fail-fast，HashMap 的迭代器是 fail-fast 迭代器。
    
*   Hashtable 的 key 和 value 都不允许为 null，HashMap 可以插入键为 null 的 Entry。
    
*   HashTable 使用 synchronized 来进行同步。
    
*   基于 Dictionary 类（遗留类）
    
*   HashMap 不能保证随着时间的推移 Map 中的元素次序是不变的。
    

### HashMap 与 HashTable

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58cXkwQHXTRpiaqIdWHUQzJLmOneOHYVREibwrACOLQGac5cCNp9yL9oaQ/640?wx_fmt=png)在这里插入图片描述

*   HashTable 基于 Dictionary 类（遗留类），而 HashMap 是基于 AbstractMap。
    

*   Dictionary 是任何可将键映射到相应值的类的**抽象父类**，
    
*   而 AbstractMap 是**基于 Map 接口的实现**，它以最大限度地减少实现此接口所需的工作。
    

*   HashMap 的 key 和 value 都允许为 null，**而 Hashtable 的 key 和 value 都不允许为 null**
    
*   HashMap 的迭代器是 fail-fast 迭代器，**而 Hashtable 的 enumerator 迭代器不是 fail-fast 的。**
    
*   由于 Hashtable 是线程安全的也是 synchronized，**所以在单线程环境下它比 HashMap 要慢。**
    
*   Hashtable 中的几乎所有的 public 的方法都是 synchronized 的，而有些方法也是在内部通过 synchronized 代码块来实现。
    

*   但是在 Collections 类中存在一个静态方法：synchronizedMap()，该方法创建了一个线程安全的 Map 对象，并把它作为一个封装的对象来返回。
    
*   **也可以使用 ConcurrentHashMap，它是 HashTable 的替代，而且比 HashTable 可扩展性更好**
    

ConcurrentHashMap
-----------------

谈谈 ConcurrentHashMap1.7 和 1.8 的不同实现：

http://www.importnew.com/23610.html

详细源码分析（还未细看）：

https://blog.csdn.net/yan_wenliang/article/details/51029372

https://segmentfault.com/a/1190000014380257

**主要针对 jdk1.7 的实现来介绍**

### 关键词

*   key 和 value **都不允许为 null**
    
*   Hashtable 是**将所有的方法进行同步**，效率低下。而 ConcurrentHashMap 通过**部分锁定 + CAS 算法**来进行实现线程安全的
    
*   get 方法是**非阻塞，无锁的**。重写 Node 类，通过 volatile 修饰 next 来实现每次获取都是最新设置的值
    
*   在高并发环境下，统计数据 (计算 size… 等等) 其实是无意义的，因为在下一时刻 size 值就变化了。
    
*   实现形式不同：
    

*   1.7：Segment + HashEntry 的方式进行实现
    
*   1.8：与 HashMap 相同（散列表（数组 + 链表）+ 红黑树）采用 Node 数组 + CAS + Synchronized 来保证并发安全进行实现
    

### 存储结构

#### jdk1.7

jdk1.7 中采用 Segment + HashEntry 的方式进行实现

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58HrXf8PW0gYA2cgXuIjOtxLODJhBMcumokb3Cj2RtfYjdJ6xmY2AdkQ/640?wx_fmt=png)在这里插入图片描述

**Segment：其继承于 ReentrantLock 类，从而使得 Segment 对象可以充当锁的角色。**

Segment 中包含 HashBucket 的数组，其可以守护其包含的若干个桶。

```
1static final class HashEntry<K,V> {
2    final int hash;
3    final K key;
4    volatile V value;
5    volatile HashEntry<K,V> next;
6}


```

ConcurrentHashMap 采用了分段锁，**每个分段锁维护着几个桶，多个线程可以同时访问不同分段锁上的桶，从而使其并发度更高（并发度就是 Segment 的个数）。**

#### jdk1.8

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58UvWiccibYBH7xRJe6icgPhzf7DwSyU6I58K2qTeUNGNvCdrL6jYiaww7fA/640?wx_fmt=png)在这里插入图片描述

*   **JDK 1.7 使用分段锁机制**来实现并发更新操作，核心类为 Segment，它继承自重入锁 ReentrantLock，并发程度与 Segment 数量相等。
    
*   **JDK 1.8 使用了 CAS 操作**来支持更高的并发度，在 CAS 操作失败时使用内置锁 synchronized。
    
*   **并且 JDK 1.8 的实现也在链表过长时会转换为红黑树。**
    

1.8 中放弃了 Segment 臃肿的设计，取而代之的是采用 Node 数组 + CAS + Synchronized 来保证并发安全进行实现

### 添加元素：put

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58mI4sdCA0UAajkZwebFdcW4SxLwP2ROB3fC9gzy2n3OicicYiaTv6A8juA/640?wx_fmt=png)在这里插入图片描述

只让一个线程对散列表进行初始化！

### 获取元素：get

从顶部注释我们可以读到，get 方法是不用加锁的，是非阻塞的。

Node 节点是重写的，设置了 volatile 关键字修饰，致使它每次获取的都是最新设置的值

### 获取大小：size

**每个 Segment 维护了一个 count 变量来统计该 Segment 中的键值对个数。**

**在执行 size 操作时，需要遍历所有 Segment 然后把 count 累计起来。**

ConcurrentHashMap 在执行 size 操作时先尝试不加锁，如果连续两次不加锁操作得到的结果一致，那么可以认为这个结果是正确的。

尝试次数使用 RETRIES_BEFORE_LOCK 定义，该值为 2，retries 初始值为 -1，因此尝试次数为 3。

如果尝试的次数超过 3 次，就需要对每个 Segment 加锁。

### 删除元素：remove

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58odP5gvrHIaFibEbtc9yNuQHeaeoBBynicTHmiaTOLBzQaKudNGQknKSGA/640?wx_fmt=png)在这里插入图片描述

**为什么用这么方式删除呢，细心的同学会发现上面定义的 HashEntry 的 key 和 next 都是 final 类型的，所以不能改变 next 的指向，所以又复制了一份指向删除的结点的 next。**

### Collections.synchronizedMap() 与 ConcurrentHashMap 的区别

参考：https://blog.csdn.net/lanxiangru/article/details/53495854

*   Collections.synchronizedMap() 和 Hashtable 一样，实现上在调用 map 所有方法时，都对整个 map 进行同步，**而 ConcurrentHashMap 的实现却更加精细，它对 map 中的所有桶加了锁**，**同步操作精确控制到桶，所以，即使在遍历 map 时，其他线程试图对 map 进行数据修改，也不会抛出 ConcurrentModificationException。**
    
*   ConcurrentHashMap 从类的命名就能看出，它是个 HashMap。而 **Collections.synchronizedMap() 可以接收任意 Map 实例**，实现 Map 的同步。比如 TreeMap。
    

### 总结

ConcurrentHashMap 的高并发性主要来自于三个方面：

*   用**分离锁**实现多个线程间的更深层次的共享访问。
    
*   用 **HashEntery 对象的不变性**来降低执行读操作的线程在遍历链表期间对加锁的需求。
    
*   通过对同一个 Volatile 变量的写 / 读访问，协调不同线程间读 / 写操作的内存可见性。
    

LinkedHashMap
-------------

http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap.html

https://segmentfault.com/a/1190000014319445

### 关键词

*   允许使用 null 值和 null 键
    
*   此实现**不是同步的**（linkedlist，lilnkedhashset 也不是同步的）
    
*   维护着一个**运行于所有条目的双向链表**。定义了迭代顺序，该迭代顺序可以是**插入顺序**或者是**访问顺序**。
    
*   **初始容量对遍历没有影响**：遍历的双向链表，而不是散列表
    
*   在访问顺序的情况下，使用 get 方法也是结构性的修改（会导致 Fail-Fast）
    

### 概论

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58OOmicDOc2YM1fffBEFb50I62g468TwQhzyQncK8mvR1uK733Sn2YIEw/640?wx_fmt=png)在这里插入图片描述![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58SyUS9O5mV32YkBSicjWwMlcGDhU9pGxJ5rRUy9IoOcb0JJo9Rib2SfTA/640?wx_fmt=png)在这里插入图片描述

### 成员变量

**该 Entry 除了保存当前对象的引用外，还保存了其上一个元素 before 和下一个元素 after 的引用，从而在哈希表的基础上又构成了双向链接列表。**

```
 1/**
 2* LinkedHashMap的Entry元素。
 3* 继承HashMap的Entry元素，又保存了其上一个元素before和下一个元素after的引用。
 4 */
 5static class Entry<K,V> extends HashMap.Node<K,V> {
 6        Entry<K,V> before, after;
 7        Entry(int hash, K key, V value, Node<K,V> next) {
 8            super(hash, key, value, next);
 9        }
10    }


```

### 构造器

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58VkbD1PzrRQaksYYf0hFvCSZ8LEE3MqSzkLy0VkSt824sYpETwR1O6A/640?wx_fmt=png)在这里插入图片描述

*   通过源代码可以看出，在 LinkedHashMap 的构造方法中，实际调用了父类 HashMap 的相关构造方法来构造一个底层存放的 table 数组，但额外可以增加 accessOrder 这个参数，如果不设置
    

*   **默认为 false，代表按照插入顺序进行迭代；**
    
*   当然可以显式设置为 true，代表以访问顺序进行迭代。
    

*   在构建新节点时，构建的是 LinkedHashMap.Entry 不再是 Node.
    

### 获取元素：get

LinkedHashMap 重写了父类 HashMap 的 get 方法，实际在调用父类 getEntry() 方法取得查找的元素后，再判断当排序模式 accessOrder 为 true 时，记录访问顺序，将最新访问的元素添加到双向链表的表头，并从原来的位置删除。

**由于的链表的增加、删除操作是常量级的，故并不会带来性能的损失。**

### 遍历元素

**为啥注释说：初始容量对遍历没有影响？**

**因为它遍历的是 LinkedHashMap 内部维护的一个双向链表，而不是散列表 (当然了，链表双向链表的元素都来源于散列表)**

### LinkedHashMap 应用

http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap-lrucache.html

#### LRU 最近最少使用（访问顺序）

用这个类有两大好处：

*   **它本身已经实现了按照访问顺序或插入顺序的存储**
    
*   LinkedHashMap **本身有 removeEldestEntry 方法用于判断是否需要移除最不常读取的数**，但是，原始方法默认不需要移除，我们需要 override 这样一个方法。
    

**Java 里面实现 LRU 缓存通常有两种选择：**

*   使用 LinkedHashMap
    
*   自己设计数据结构，使用链表 + HashMap
    

**以下是使用 LinkedHashMap 实现的一个 LRU 缓存：**

*   设定最大缓存空间 MAX_ENTRIES  为 3；
    
*   使用 LinkedHashMap 的构造函数将 accessOrder 设置为 true，开启 LRU 顺序；
    
*   覆盖 removeEldestEntry() 方法实现，在节点多于 MAX_ENTRIES 就会将最近最久未使用的数据移除。
    

```
 1class LRUCache<K, V> extends LinkedHashMap<K, V> {
 2    private static final int MAX_ENTRIES = 3;
 3
 4    protected boolean removeEldestEntry(Map.Entry eldest) {
 5        return size() > MAX_ENTRIES;
 6    }
 7
 8    LRUCache() {
 9        super(MAX_ENTRIES, 0.75f, true);
10    }
11}


```

```
1public static void main(String[] args) {
2    LRUCache<Integer, String> cache = new LRUCache<>();
3    cache.put(1, "a");
4    cache.put(2, "b");
5    cache.put(3, "c");
6    cache.get(1);
7    cache.put(4, "d");
8    System.out.println(cache.keySet());
9}


```

```
1[3, 1, 4]


```

**实现详细代码请参考文章：补充知识点 - 缓存**

#### FIFO（插入顺序）

还可以在**插入顺序**的 LinkedHashMap 直接重写下 removeEldestEntry 方法即可轻松实现一个 FIFO 缓存

TreeMap
-------

### 关键词

*   **红黑树**
    
*   **非同步**
    
*   **key 不能为 null**
    
*   实现了 NavigableMap 接口，而 NavigableMap 接口继承着 SortedMap 接口，是有序的（HahMap 是 Key 无序的）
    
*   TreeMap 的基本操作 containsKey、get、put 和 remove 的时间复杂度是 log(n) 。
    
*   适用于**查找性能要求不那么高，反而对有序性要求比较高的应用场景**
    
*   使用 Comparator 或者 Comparable 来比较 key 是否相等与排序的问题
    

### 概览

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rbzVO03SXrUGWV36fJpuS58IT99EK1JPzibhPgaQNibGLTUcZ3NWk8CRyNn6ABibE3SMqTDoDAwebQUw/640?wx_fmt=png)在这里插入图片描述

### 获取元素：get

详细看：

https://segmentfault.com/a/1190000014345983#articleHeader4

总结：

*   如果在构造方法中传递了 Comparator 对象，那么就会以 Comparator 对象的方法进行比较。否则，则使用 Comparable 的 compareTo(T o) 方法来比较。
    
*   值得说明的是：如果使用的是 compareTo(T o) 方法来比较，key 一定是不能为 null，并且得实现了 Comparable 接口的。
    
*   即使是传入了 Comparator 对象，不用 compareTo(T o) 方法来比较，key 也是不能为 null 的
    

### 删除元素：remove

*   删除节点并且平衡红黑树
    

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

拥有专栏：Leetcode 题解（Java/Python）、Python 爬虫开发

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