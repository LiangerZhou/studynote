> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://blog.csdn.net/qqxx6661/article/details/86599974 [](http://creativecommons.org/licenses/by-sa/4.0/)版权声明：本文为博主原创文章，遵循 [CC 4.0 BY-SA](http://creativecommons.org/licenses/by-sa/4.0/) 版权协议，转载请附上原文出处链接和本声明。 本文链接：[https://blog.csdn.net/qqxx6661/article/details/86599974](https://blog.csdn.net/qqxx6661/article/details/86599974)

![](https://img-blog.csdnimg.cn/20190122194748864.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

前言
==

本文快速回顾了 Java 中容器的知识点，用作面试复习，事半功倍。

其它知识点复习手册
---------

* [Java 基础知识点面试手册](https://blog.csdn.net/qqxx6661/article/details/86572953)
* [快速梳理 23 种常用的设计模式](https://blog.csdn.net/qqxx6661/article/details/84194122)
* [Redis 基础知识点面试手册](https://blog.csdn.net/qqxx6661/article/details/83718153)

概览
==

容器主要包括 Collection 和 Map 两种，Collection 又包含了 List、Set 以及 Queue。

Collection
----------

![](https://img-blog.csdnimg.cn/20190122191848295.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20190122191856348.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

**数组和集合的区别:**

* 长度
* 数组的长度固定
* 集合的长度可变
* 内容
* 数组存储的是同一种类型的元素
* 集合可以存储不同类型的元素 (但是一般我们不这样干…)
* 元素的数据类型
* 数组可以存储**基本数据类型**, 也可以存储**引用类型**
* **集合只能存储引用类型** (若存储的是简单的 int，它会自动装箱成 Integer)

### 1. Set（元素不可重复）

* HashSet：基于 HashMap 实现，支持快速查找，**但不支持有序性操作**。

* TreeSet：**基于红黑树实现，支持有序性操作**，但是查找效率不如 HashSet，**HashSet 查找时间复杂度为 O(1)，TreeSet 则为 O(logN)；**

* LinkedHashSet：具有 HashSet 的查找效率，**且内部使用链表维护元素的插入顺序**。


### 2. List（有序 (存储顺序和取出顺序一致)，可重复）

* ArrayList：基于动态数组实现，支持随机访问；

* Vector：和 ArrayList 类似，但它是线程安全的；

* LinkedList：基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList 还可以用作栈、队列和双向队列。


### 3. Queue

* LinkedList：可以用它来支持双向队列；

* PriorityQueue：基于堆结构实现，可以用它来实现优先队列。


Map
---

![](https://img-blog.csdnimg.cn/2019012219190829.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

* HashMap：基于哈希实现；

* HashTable：和 HashMap 类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入 HashTable 并且不会导致数据不一致。它是遗留类，**不应该去使用它**。

* ConcurrentHashMap：支持线程安全，并且 ConcurrentHashMap 的效率会更高，因为 ConcurrentHashMap 引入了分段锁。

* LinkedHashMap：**使用链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。**

* TreeMap：基于红黑树实现。


Fail-Fast 机制和 Fail-Safe 机制
--------------------------

[https://blog.csdn.net/Kato_op/article/details/80356618](https://blog.csdn.net/Kato_op/article/details/80356618)

### Fail-Fast

Fail-fast 机制是 java 集合 (Collection) 中的一种错误机制。 当多个线程对同一个集合的内容进行操作时，就可能会产生 fail-fast 事件。

* 迭代器在遍历时直接访问集合中的内容, 并且在遍历过程中使用一个 modCount 变量,

* 集合中在被遍历期间如果内容发生变化 (**增删改**), 就会改变 modCount 的值,

* 每当迭代器使用 hashNext()/next() 遍历下一个元素之前, 都会执行 checkForComodification() 方法检测，modCount 变量和 expectedmodCount 值是否相等,

* 如果相等就返回遍历, 否则抛出异常, 终止遍历.


**注意**，如果集合发生变化时修改 modCount 值, 刚好有设置为了 expectedmodCount 值, 则异常不会抛出.(比如删除了数据, 再添加一条数据)

**所以，一般来说，存在非同步的并发修改时，不可能作出任何坚决的保证。**

迭代器的快速失败行为**应该仅用于检测程序错误, 而不是用他来同步。**

**java.util 包下的集合类都是 Fail-Fast 机制的, 不能在多线程下发生并发修改 (迭代过程中被修改).**

### Fail-Safe

采用安全失败（Fail-Safe）机制的集合容器, 在遍历时不是直接在集合内容上访问的，**而是先 copy 原有集合内容, 在拷贝的集合上进行遍历**。

**原理:**

由于迭代时是对原集合的拷贝的值进行遍历, 所以在遍历过程中对原集合所作的修改并不能被迭代器检测到, 所以不会出发 ConcurrentModificationException

**缺点:**

**迭代器并不能访问到修改后的内容** (简单来说就是, 迭代器遍历的是开始遍历那一刻拿到的集合拷贝, 在遍历期间原集合发生的修改迭代器是不知道的)

**使用场景:**

**java.util.concurrent 包下的容器都是 Fail-Safe 的, 可以在多线程下并发使用, 并发修改**

容器中使用的设计模式
==========

迭代器模式
-----

![](https://img-blog.csdnimg.cn/20190122191938366.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

* Iterator 它是在 ArrayList 等集合的内部类的方式实现

Collection 实现了 Iterable 接口，其中的 iterator() 方法能够产生一个 Iterator 对象，通过这个对象就可以迭代遍历 Collection 中的元素。

从 JDK 1.5 之后可以使用 foreach 方法来遍历实现了 Iterable 接口的聚合对象。

```
1
2
3
4
5
6
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
for (String item : list) {
System.out.println(item);
}


```

适配器模式
-----

适配器模式解释：[https://www.jianshu.com/p/93821721bf08](https://www.jianshu.com/p/93821721bf08)

java.util.Arrays#asList() 可以把数组类型转换为 List 类型。

```
1
2
@SafeVarargs
public static <T> List<T> asList(T... a)


```

如果要将数组类型转换为 List 类型，应该注意的是 asList() 的参数为泛型的变长参数，因此不能使用基本类型数组作为参数，**只能使用相应的包装类型数组。**

```
1
2
Integer[] arr = {1, 2, 3};
List list = Arrays.asList(arr);


```

也可以使用以下方式生成 List。

```
1
List list = Arrays.asList(1,2,3);


```

源码分析
====

ArrayList
---------

### 关键词

* 默认大小为 10
* 扩容 1.5 倍，加载因子为 0.5
* 基于动态数组实现
* 删除元素时不会减少容量，若希望减少容量则调用 trimToSize()
* 它不是线程安全的
* 它能存放 null 值。
* 扩容操作需要调用 Arrays.copyOf() 把原数组整个复制到新数组
* 删除需要调用 System.arraycopy() 将 index+1 后面的元素都复制到 index 位置上，复制的代价很高。
- 序列化：只序列化数组中有元素填充那部分内容

### 概览

![](https://img-blog.csdnimg.cn/20190122191949757.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

实现了 RandomAccess 接口，因此支持随机访问。这是理所当然的，因为 ArrayList 是基于数组实现的。

```
1
2
public class ArrayList<E> extends AbstractList<E>
implements List<E>, RandomAccess, Cloneable, java.io.Serializable


```

### 扩容

如果不够时，需要使用 grow() 方法进行扩容，新容量的大小为 `oldCapacity + (oldCapacity >> 1)`，也就是**旧容量的 1.5 倍。**

**扩容操作需要调用 `Arrays.copyOf()` 把原数组整个复制到新数组**中

因此最好在创建 ArrayList **对象时就指定大概的容量大小，减少扩容操作的次数。**

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
public boolean add(E e) {
ensureCapacityInternal(size + 1);// Increments modCount!!
elementData[size++] = e;
return true;
}

private void ensureCapacityInternal(int minCapacity) {
if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
}
ensureExplicitCapacity(minCapacity);
}

private void ensureExplicitCapacity(int minCapacity) {
modCount++;
// overflow-conscious code
if (minCapacity - elementData.length > 0)
grow(minCapacity);
}

private void grow(int minCapacity) {
// overflow-conscious code
int oldCapacity = elementData.length;
int newCapacity = oldCapacity + (oldCapacity >> 1);
if (newCapacity - minCapacity < 0)
newCapacity = minCapacity;
if (newCapacity - MAX_ARRAY_SIZE > 0)
newCapacity = hugeCapacity(minCapacity);
// minCapacity is usually close to size, so this is a win:
elementData = Arrays.copyOf(elementData, newCapacity);
}


```

### 加入元素：add

**add(E e)**

首先去检查一下数组的容量是否足够

* 足够：直接添加
* 不足够：扩容

**扩容到原来的 1.5 倍，第一次扩容后，如果容量还是小于 minCapacity，就将容量扩充为 minCapacity。**

**add(int index, E element)**

步骤：

* 检查角标
* 空间检查，如果有需要进行扩容
* 插入元素

### 删除元素：remove

步骤：

* 检查角标
* 删除元素
* 计算出需要移动的个数，并移动
* 设置为 null，让 GC 回收（所以说不是立刻回收，而是等待 GC 回收）

```
1
2
3
4
5
6
7
8
9
10
public E remove(int index) {
rangeCheck(index);
modCount++;
E oldValue = elementData(index);
int numMoved = size - index - 1;
if (numMoved > 0)
System.arraycopy(elementData, index+1, elementData, index, numMoved);
elementData[--size] = null; // clear to let GC do its work
return oldValue;
}


```

需要调用 System.arraycopy() 将 index+1 后面的元素都复制到 index 位置上，**复制的代价很高。**

### 复制数组：System.arraycopy()

看到 arraycopy()，我们可以发现：该方法是由 C/C++ 来编写的

![](https://img-blog.csdnimg.cn/20190122192005438.png)

### Fail-Fast

modCount 用来记录 ArrayList 结构发生变化的次数。结构发生变化是指添加或者删除至少一个元素的所有操作，或者是调整内部数组的大小，仅仅只是设置元素的值不算结构发生变化。

在进行序列化或者迭代等操作时，需要比较操作前后 modCount 是否改变，如果改变了需要抛出 ConcurrentModificationException。

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
private void writeObject(java.io.ObjectOutputStream s)
throws java.io.IOException{
// Write out element count, and any hidden stuff
int expectedModCount = modCount;
s.defaultWriteObject();

// Write out size as capacity for behavioural compatibility with clone()
s.writeInt(size);

// Write out all elements in the proper order.
for (int i=0; i<size; i++) {
s.writeObject(elementData[i]);
}

if (modCount != expectedModCount) {
throw new ConcurrentModificationException();
}
}


```

### 构造器

ArrayList 提供了三种方式的构造器：

* public ArrayList() 可以构造一个默认初始容量为 10 的空列表；
* public ArrayList(int initialCapacity) 构造一个指定初始容量的空列表；
* public ArrayList(Collection<? extends E> c) 构造一个包含指定 collection 的元素的列表，这些元素按照该 collection 的迭代器返回它们的顺序排列的。

### 序列化

**补充：transient 讲解**

[http://www.importnew.com/21517.html](http://www.importnew.com/21517.html)

> 你只需要实现 Serilizable 接口，将不需要序列化的属性前添加关键字 transient，序列化对象的时候，这个属性就不会序列化到指定的目的地中。

ArrayList 基于数组实现，并且具有动态扩容特性，因此保存元素的数组不一定都会被使用，那么就没必要全部进行序列化。

保存元素的数组 elementData 使用 transient 修饰，**该关键字声明数组默认不会被序列化**。

```
1
transient Object[] elementData; // non-private to simplify nested class access


```

ArrayList 实现了 writeObject() 和 readObject() **来控制只序列化数组中有元素填充那部分内容**。

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
private void readObject(java.io.ObjectInputStream s)
throws java.io.IOException, ClassNotFoundException {
elementData = EMPTY_ELEMENTDATA;

// Read in size, and any hidden stuff
s.defaultReadObject();

// Read in capacity
s.readInt(); // ignored

if (size > 0) {
// be like clone(), allocate array based upon size not capacity
ensureCapacityInternal(size);

Object[] a = elementData;
// Read in all elements in the proper order.
for (int i=0; i<size; i++) {
a[i] = s.readObject();
}
}
}


```

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
private void writeObject(java.io.ObjectOutputStream s)
throws java.io.IOException{
// Write out element count, and any hidden stuff
int expectedModCount = modCount;
s.defaultWriteObject();

// Write out size as capacity for behavioural compatibility with clone()
s.writeInt(size);

// Write out all elements in the proper order.
for (int i=0; i<size; i++) {
s.writeObject(elementData[i]);
}

if (modCount != expectedModCount) {
throw new ConcurrentModificationException();
}
}


```

序列化时需要使用 ObjectOutputStream 的 writeObject() 将对象转换为字节流并输出。**而 writeObject() 方法在传入的对象存在 writeObject() 的时候会去反射调用该对象的 writeObject() 来实现序列化**。反序列化使用的是 ObjectInputStream 的 readObject() 方法，原理类似。

```
1
2
3
ArrayList list = new ArrayList();
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
oos.writeObject(list);


```

Vector
------

### 关键词

* 默认大小为 10（与 Arraylist 相同）
* 扩容 2 倍，加载因子是 1（Arraylist 是扩容 1.5 倍，加载因子为 0.5）
* 其它几乎与 ArrayList 完全相同，唯一的区别在于 Vector 是同步的，因此开销就比 ArrayList 要大，访问速度更慢。
* 使用了 synchronized 进行同步
* Vector 是 jdk1.2 的类了，比较老旧的一个集合类。应使用 JUC 的 CopyOnWriteArrayList 代替

### 替代方案

可以使用 `Collections.synchronizedList();` 得到一个线程安全的 ArrayList。

```
1
2
List<String> list = new ArrayList<>();
List<String> synList = Collections.synchronizedList(list);


```

也可以使用 concurrent 并发包下的 CopyOnWriteArrayList 类。

```
1
List<String> list = new CopyOnWriteArrayList<>();


```

CopyOnWriteArrayList
--------------------

### 关键词

* 写操作在一个复制的数组上进行，读操作还是在原始数组中进行，读写分离，互不影响。
* 写操作需要加锁，防止并发写入时导致写入数据丢失。
* 写操作结束之后需要把原始数组指向新的复制数组。
* **适用于读操作远大于写操作的场景。**

### 读写分离

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
public boolean add(E e) {
final ReentrantLock lock = this.lock;
lock.lock();
try {
Object[] elements = getArray();
int len = elements.length;
Object[] newElements = Arrays.copyOf(elements, len + 1);
newElements[len] = e;
setArray(newElements);
return true;
} finally {
lock.unlock();
}
}

final void setArray(Object[] a) {
array = a;
}


```

```
1
2
3
4
@SuppressWarnings("unchecked")
private E get(Object[] a, int index) {
return (E) a[index];
}


```

### 适用场景

CopyOnWriteArrayList 在写操作的同时允许读操作，大大提高了读操作的性能，因此很适合读多写少的应用场景。

### 缺陷

* 内存占用：在写操作时需要复制一个新的数组，**使得内存占用为原来的两倍左右；**
* 数据不一致：**读操作不能读取实时性的数据，因为部分写操作的数据还未同步到读数组中**。

所以 CopyOnWriteArrayList **不适合内存敏感以及对实时性要求很高的场景。**

LinkedList
----------

### 关键词

* **双向**链表
* 默认大小为 10
* 带 Head 和 Tail 指针
* Node 存储节点信息

### 概览

![](https://img-blog.csdnimg.cn/201901221920269.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

基于双向链表实现，内部使用 Node 来存储链表节点信息。

```
1
2
3
4
5
private static class Node<E> {
E item;
Node<E> next;
Node<E> prev;
}


```

每个链表存储了 Head 和 Tail 指针：

```
1
2
transient Node<E> first;
transient Node<E> last;


```

![](https://img-blog.csdnimg.cn/20190122192035923.png)

### ArrayList 与 LinkedList 比较

* ArrayList 基于**动态数组**实现，LinkedList 基于**双向链表**实现；
* **ArrayList 支持随机访问，LinkedList 不支持；**
* **LinkedList 在任意位置添加删除元素更快。**

### 删除元素：remove

![](https://img-blog.csdnimg.cn/2019012219204374.png)

### 获取元素：get

* 下标小于长度的一半，从头遍历
* 反之，从尾部遍历

### 替换元素：set

set 方法和 get 方法其实差不多，根据下标来判断是从头遍历还是从尾遍历

### 其他方法

**LinkedList 实现了 Deque 接口，因此，我们可以操作 LinkedList 像操作队列和栈一样**

LinkedList 的方法比 ArrayList 的方法多太多了，这里我就不一一说明了。具体可参考：

* [https://blog.csdn.net/panweiwei1994/article/details/77110354](https://blog.csdn.net/panweiwei1994/article/details/77110354)
* [https://zhuanlan.zhihu.com/p/24730576](https://zhuanlan.zhihu.com/p/24730576)
* [https://zhuanlan.zhihu.com/p/28373321](https://zhuanlan.zhihu.com/p/28373321)

HashMap
-------

[http://wiki.jikexueyuan.com/project/java-collection/hashmap.html](http://wiki.jikexueyuan.com/project/java-collection/hashmap.html)

源码分析：[https://segmentfault.com/a/1190000014293372](https://segmentfault.com/a/1190000014293372)

### 关键词

* 初始容量 16
* 扩容是 2 倍，加载因子 0.75
* 头插法
* 0 桶存放 null
* 从 JDK 1.8 开始，一个桶存储的链表长度大于 8 时会将链表转换为红黑树（前提：键值对要超过 64 个）
* 自动地将传入的**容量转换为 2 的幂次方**
* **保证运算速度**：确保**用位运算代替模运算**来计算桶下标。hash& (length-1) 运算等价于对 length 取模。
* **hash 均匀分布**：数据在数组上分布就比较均匀，并且能够利用全部二进制位，也就是说**碰撞的几率小**，
* table 数组 + Entry<K,V>[] 链表（散列表），红黑树
* 扩容操作需要把键值对重新插入新的 table 中，**重新计算所有 key 有特殊机制（JDK1.8 后）**

### 存储结构

hashMap 的一个内部类 Node：

```
1
2
3
4
5
static class Node<K,V> implements Map.Entry<K,V> {
final int hash;
final K key;
V value;
Node<K,V> next; //链表结构，存储下一个元素


```

![](https://img-blog.csdnimg.cn/20190122192056320.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

Node 内部包含了一个 Entry 类型的数组 table，数组中的每个位置被当成一个桶。

```
1
transient Entry[] table;


```

Entry 存储着键值对。它包含了四个字段，从 next 字段我们可以看出 Entry 是一个链表。即数组中的每个位置被当成一个桶，一个桶存放一个链表。

HashMap 使用拉链法来解决冲突，同一个链表中存放哈希值相同的 Entry。

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
static class Entry<K,V> implements Map.Entry<K,V> {
final K key;
V value;
Entry<K,V> next;
int hash;

Entry(int h, K k, V v, Entry<K,V> n) {
value = v;
next = n;
key = k;
hash = h;
}

public final K getKey() {
return key;
}

public final V getValue() {
return value;
}

public final V setValue(V newValue) {
V oldValue = value;
value = newValue;
return oldValue;
}

public final boolean equals(Object o) {
if (!(o instanceof Map.Entry))
return false;
Map.Entry e = (Map.Entry)o;
Object k1 = getKey();
Object k2 = e.getKey();
if (k1 == k2 || (k1 != null && k1.equals(k2))) {
Object v1 = getValue();
Object v2 = e.getValue();
if (v1 == v2 || (v1 != null && v1.equals(v2)))
return true;
}
return false;
}

public final int hashCode() {
return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
}

public final String toString() {
return getKey() + "=" + getValue();
}
}


```

### 构造器

![](https://img-blog.csdnimg.cn/20190122192106584.png)

构造时就会调用 tableSizeFor()：返回一个大于输入参数且最近的 2 的整数次幂。

```
1
2
3
4
5
6
7
8
9
static final int tableSizeFor(int cap) {
int n = cap - 1;
n |= n >>> 1;
n |= n >>> 2;
n |= n >>> 4;
n |= n >>> 8;
n |= n >>> 16;
return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}


```

### 拉链法

应该注意到链表的插入是以**头插法**方式进行的

```
1
2
3
4
HashMap<String, String> map = new HashMap<>();
map.put("K1", "V1");
map.put("K2", "V2");
map.put("K3", "V3");


```

* 新建一个 HashMap，默认大小为 16；
* 插入 <K1,V1> 键值对，先计算 K1 的 hashCode 为 115，使用除留余数法得到所在的桶下标 115%16=3。
* 插入 <K2,V2> 键值对，先计算 K2 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6。
* 插入 <K3,V3> 键值对，先计算 K3 的 hashCode 为 118，使用除留余数法得到所在的桶下标 118%16=6，插在 <K2,V2> 前面。

查找需要分成两步进行：

* 计算键值对所在的桶；
* 在链表上顺序查找，时间复杂度显然和链表的长度成正比。

### put 操作

* 当我们 put 的时候，如果 key 存在了，那么新的 value 会代替旧的 value
* 如果 key **存在的情况下，该方法返回的是旧的 value，**
* 如果 key **不存在，那么返回 null。**

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
public V put(K key, V value) {
if (table == EMPTY_TABLE) {
inflateTable(threshold);
}
// 键为 null 单独处理
if (key == null)
return putForNullKey(value);
int hash = hash(key);
// 确定桶下标
int i = indexFor(hash, table.length);
// 先找出是否已经存在键为 key 的键值对，如果存在的话就更新这个键值对的值为 value
for (Entry<K,V> e = table[i]; e != null; e = e.next) {
Object k;
if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
V oldValue = e.value;
e.value = value;
e.recordAccess(this);
return oldValue;
}
}

modCount++;
// 插入新键值对
addEntry(hash, key, value, i);
return null;
}


```

HashMap 允许插入键为 null 的键值对。但是因为无法调用 null 的 hashCode() 方法，也就无法确定该键值对的桶下标，只能通过强制指定一个桶下标来存放。HashMap 使用第 0 个桶存放键为 null 的键值对。

```
1
2
3
4
5
6
7
8
9
10
11
12
13
private V putForNullKey(V value) {
for (Entry<K,V> e = table[0]; e != null; e = e.next) {
if (e.key == null) {
V oldValue = e.value;
e.value = value;
e.recordAccess(this);
return oldValue;
}
}
modCount++;
addEntry(0, null, value, 0);
return null;
}


```

使用链表的头插法，也就是新的键值对插在链表的头部，而不是链表的尾部。

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
void addEntry(int hash, K key, V value, int bucketIndex) {
if ((size >= threshold) && (null != table[bucketIndex])) {
resize(2 * table.length);
hash = (null != key) ? hash(key) : 0;
bucketIndex = indexFor(hash, table.length);
}

createEntry(hash, key, value, bucketIndex);
}

void createEntry(int hash, K key, V value, int bucketIndex) {
Entry<K,V> e = table[bucketIndex];
// 头插法，链表头部指向新的键值对
table[bucketIndex] = new Entry<>(hash, key, value, e);
size++;
}


```

```
1
2
3
4
5
6
Entry(int h, K k, V v, Entry<K,V> n) {
value = v;
next = n;
key = k;
hash = h;
}


```

#### 补充：hashmap 里 hash 方法的高位优化：

[https://www.cnblogs.com/liujinhong/p/6576543.html](https://www.cnblogs.com/liujinhong/p/6576543.html)

[https://note.youdao.com/yws/res/18743/50AADC7BB42845B29CDA293FC409250C?ynotemdtimestamp=1548155508277](https://note.youdao.com/yws/res/18743/50AADC7BB42845B29CDA293FC409250C?ynotemdtimestamp=1548155508277)

设计者将 key 的哈希值的高位也做了运算 (**与高 16 位做异或运算，使得在做 & 运算时，此时的低位实际上是高位与低位的结合**)，这就增加了随机性，减少了碰撞冲突的可能性！

**为何要这么做？**

**table 的长度都是 2 的幂，因此 index 仅与 hash 值的低 n 位有关，hash 值的高位都被与操作置为 0 了。**

这样做很容易产生碰撞。**设计者权衡了 speed, utility, and quality，将高 16 位与低 16 位异或来减少这种影响**。设计者考虑到现在的 hashCode 分布的已经很不错了，而且当发生较大碰撞时也用树形存储降低了冲突。仅仅异或一下，既减少了系统的开销，也不会造成的因为高位没有参与下标的计算 (table 长度比较小时)，从而引起的碰撞。

### 确定桶下标

很多操作都需要先确定一个键值对所在的桶下标。

```
1
2
int hash = hash(key);
int i = indexFor(hash, table.length);


```

**4.1 计算 hash 值**

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
final int hash(Object k) {
int h = hashSeed;
if (0 != h && k instanceof String) {
return sun.misc.Hashing.stringHash32((String) k);
}

h ^= k.hashCode();

// This function ensures that hashCodes that differ only by
// constant multiples at each bit position have a bounded
// number of collisions (approximately 8 at default load factor).
h ^= (h >>> 20) ^ (h >>> 12);
return h ^ (h >>> 7) ^ (h >>> 4);
}


```

```
1
2
3
public final int hashCode() {
return Objects.hashCode(key) ^ Objects.hashCode(value);
}


```

**4.2 取模**

令 x = 1<<\4，即 \x 为 2 的 4 次方，它具有以下性质：

```
1
2
x : 00010000
x-1 : 00001111


```

令一个数 y 与 x-1 做与运算，可以去除 y 位级表示的第 4 位以上数：

```
1
2
3
y : 10110010
x-1 : 00001111
y&(x-1) : 00000010


```

这个性质和 y 对 x 取模效果是一样的：

```
1
2
3
y : 10110010
x : 00010000
y%x : 00000010


```

我们知道，位运算的代价比求模运算小的多，因此在进行这种计算时用位运算的话能带来更高的性能。

确定桶下标的最后一步是将 key 的 hash 值对桶个数取模：hash%capacity，如果能保证 capacity 为 2 的 n 次方，那么就可以将这个操作转换为位运算。

```
1
2
3
static int indexFor(int h, int length) {
return h & (length-1);
}


```

**当 length 总是 2 的 n 次方时，h& (length-1) 运算等价于对 length 取模，也就是 h%length**，但是 & 比 % 具有更高的效率。这看上去很简单，其实比较有玄机的，我们举个例子来说明：

| h & (table.length-1) | hash |     | table.length-1 |        |
| -------------------- | ---- | --- | -------------- | ------ |
| 8 & (15-1)：         | 0100 | &   | 1110           | = 0100 |
| 9 & (15-1)：         | 0101 | &   | 1110           | = 0100 |
| 8 & (16-1)：         | 0100 | &   | 1111           | = 0100 |
| 9 & (16-1)：         | 0101 | &   | 1111           | = 0101 |

* 从上面的例子中可以看出：当它们和 15-1（1110）“与” 的时候，**8 和 9 产生了相同的结果**，也就是说它们会定位到数组中的同一个位置上去，这就**产生了碰撞**，8 和 9 会被放到数组中的同一个位置上形成链表，那么查询的时候就需要遍历这个链 表，得到 8 或者 9，这样就降低了查询的效率。

* 同时，我们也可以发现，当数组长度为 15 的时候，hash 值会与 15-1（1110）进行 “与”，那么最后一位永远是 0，而 0001，0011，0101，1001，1011，0111，1101 **这几个位置永远都不能存放元素了**，**空间浪费相当大**，数组可以使用的位置比数组长度小了很多，这意味着进一步增加了碰撞的几率。

* 而当数组长度为 16 时，即为 2 的 n 次方时，2n-1 得到的二进制数的每个位上的值都为 1，**这使得在低位上 & 时，得到的和原 hash 的低位相同**，加之 **hash(int h) 方法对 key 的 hashCode 的进一步优化**，加入了高位计算，就使得只有相同的 hash 值的两个值才会被放到数组中的同一个位置上形成链表。


所以说，当数组长度为 2 的 n 次幂的时候，**不同的 key 算得得 index 相同的几率较小，那么数据在数组上分布就比较均匀，也就是说碰撞的几率小**

### 扩容 - 基本原理

设 HashMap 的 table 长度为 M，需要存储的键值对数量为 N，如果哈希函数满足均匀性的要求，那么每条链表的长度大约为 N/M，因此平均查找次数的复杂度为 O(N/M)。

为了让查找的成本降低，应该尽可能使得 N/M 尽可能小，因此需要保证 M 尽可能大，也就是说 table 要尽可能大。HashMap 采用动态扩容来根据当前的 N 值来调整 M 值，使得空间效率和时间效率都能得到保证。

和扩容相关的参数主要有：capacity、size、threshold 和 load_factor。

|    参数    | 含义                                                                        |
| :--------: | :-------------------------------------------------------------------------- |
|  capacity  | table 的容量大小，默认为 16。需要注意的是 capacity 必须保证为 2 的 n 次方。 |
|    size    | 键值对数量。                                                                |
| threshold  | size 的临界值，当 size 大于等于 threshold 就必须进行扩容操作。              |
| loadFactor | 装载因子，table 能够使用的比例，threshold = capacity * loadFactor。         |

```
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
static final int DEFAULT_INITIAL_CAPACITY = 16;

static final int MAXIMUM_CAPACITY = 1 << 30;

static final float DEFAULT_LOAD_FACTOR = 0.75f;

transient Entry[] table;

transient int size;

int threshold;

final float loadFactor;

transient int modCount;


```

从下面的添加元素代码中可以看出，当需要扩容时，令 capacity 为原来的两倍。

```
1
2
3
4
5
6
void addEntry(int hash, K key, V value, int bucketIndex) {
Entry<K,V> e = table[bucketIndex];
table[bucketIndex] = new Entry<>(hash, key, value, e);
if (size++ >= threshold)
resize(2 * table.length);
}


```

扩容使用 resize() 实现，需要注意的是，扩容操作同样需要把 oldTable 的所有键值对重新插入 newTable 中，因此这一步是很费时的。

```java
void resize(int newCapacity) {
Entry[] oldTable = table;
int oldCapacity = oldTable.length;
if (oldCapacity == MAXIMUM_CAPACITY) {
threshold = Integer.MAX_VALUE;
return;
}
Entry[] newTable = new Entry[newCapacity];
transfer(newTable);
table = newTable;
threshold = (int)(newCapacity * loadFactor);
}

void transfer(Entry[] newTable) {
Entry[] src = table;
int newCapacity = newTable.length;
for (int j = 0; j < src.length; j++) {
Entry<K,V> e = src[j];
if (e != null) {
src[j] = null;
do {
Entry<K,V> next = e.next;
int i = indexFor(e.hash, newCapacity);
e.next = newTable[i];
newTable[i] = e;
e = next;
} while (e != null);
}
}
}


```

### 扩容 - 重新计算桶下标

Rehash 优化：[https://my.oschina.net/u/3568600/blog/1933764](https://my.oschina.net/u/3568600/blog/1933764)

**在进行扩容时，需要把键值对重新放到对应的桶上。HashMap 使用了一个特殊的机制，可以降低重新计算桶下标的操作。**

假设原数组长度 capacity 为 16，扩容之后 new capacity 为 32：

```
capacity : 00010000
new capacity : 00100000


```

对于一个 Key，

* 它的哈希值如果在第 5 位上为 0，那么取模得到的结果和之前一样；
* 如果为 1，那么得到的结果为原来的结果 +16。

**总结：**

经过 rehash 之后，元素的位置要么是在原位置，要么是在原位置再移动 2 次幂的位置

因此，我们在扩充 HashMap 的时候，不需要像 JDK1.7 的实现那样重新计算 hash，只需要看看原来的 hash 值新增的那个 bit 是 1 还是 0 就好了，是 0 的话索引没变，是 1 的话索引变成 “原索引 + oldCap”，可以看看下图为 16 扩充为 32 的 resize 示意图：

![](https://img-blog.csdnimg.cn/20190122192145386.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

### 计算数组容量

HashMap 构造函数允许用户传入的容量不是 2 的 n 次方，因为它可以自动地将传入的容量转换为 2 的 n 次方。

先考虑如何求一个数的掩码，对于 10010000，它的掩码为 11111111，可以使用以下方法得到：

```
mask |= mask >> 111011000
mask |= mask >> 211111110
mask |= mask >> 411111111


```

mask+1 是大于原始数字的最小的 2 的 n 次方。

```
num 10010000
mask+1 100000000


```

以下是 HashMap 中计算数组容量的代码：

```
static final int tableSizeFor(int cap) {
int n = cap - 1;
n |= n >>> 1;
n |= n >>> 2;
n |= n >>> 4;
n |= n >>> 8;
n |= n >>> 16;
return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}


```

### 链表转红黑树

**并不是桶子上有 8 位元素的时候它就能变成红黑树，它得同时满足我们的键值对大于 64 才行的**

这是为了避免在哈希表建立初期，多个键值对恰好被放入了同一个链表中而导致不必要的转化。

HashTable
---------

### 关键词：

* Hashtable 的迭代器不是 fail-fast，HashMap 的迭代器是 fail-fast 迭代器。
* Hashtable 的 key 和 value 都不允许为 null，HashMap 可以插入键为 null 的 Entry。
* HashTable 使用 synchronized 来进行同步。
* 基于 Dictionary 类（遗留类）
* HashMap 不能保证随着时间的推移 Map 中的元素次序是不变的。

### HashMap 与 HashTable

![](https://img-blog.csdnimg.cn/20190122192155107.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

* HashTable 基于 Dictionary 类（遗留类），而 HashMap 是基于 AbstractMap。
* Dictionary 是任何可将键映射到相应值的类的**抽象父类**，
* 而 AbstractMap 是**基于 Map 接口的实现**，它以最大限度地减少实现此接口所需的工作。
* HashMap 的 key 和 value 都允许为 null，**而 Hashtable 的 key 和 value 都不允许为 null**
* HashMap 的迭代器是 fail-fast 迭代器，**而 Hashtable 的 enumerator 迭代器不是 fail-fast 的。**
* 由于 Hashtable 是线程安全的也是 synchronized，**所以在单线程环境下它比 HashMap 要慢。**
* Hashtable 中的几乎所有的 public 的方法都是 synchronized 的，而有些方法也是在内部通过 synchronized 代码块来实现。
* 但是在 Collections 类中存在一个静态方法：synchronizedMap()，该方法创建了一个线程安全的 Map 对象，并把它作为一个封装的对象来返回。
* **也可以使用 ConcurrentHashMap，它是 HashTable 的替代，而且比 HashTable 可扩展性更好**

ConcurrentHashMap
-----------------

谈谈 ConcurrentHashMap1.7 和 1.8 的不同实现：

[http://www.importnew.com/23610.html](http://www.importnew.com/23610.html)

详细源码分析（还未细看）：

[https://blog.csdn.net/yan_wenliang/article/details/51029372](https://blog.csdn.net/yan_wenliang/article/details/51029372)

[https://segmentfault.com/a/1190000014380257](https://segmentfault.com/a/1190000014380257)

**主要针对 jdk1.7 的实现来介绍**

### 关键词

* key 和 value **都不允许为 null**
* Hashtable 是**将所有的方法进行同步**，效率低下。而 ConcurrentHashMap 通过**部分锁定 + CAS 算法**来进行实现线程安全的
* get 方法是**非阻塞，无锁的**。重写 Node 类，通过 volatile 修饰 next 来实现每次获取都是最新设置的值
* 在高并发环境下，统计数据 (计算 size… 等等) 其实是无意义的，因为在下一时刻 size 值就变化了。
* 实现形式不同：
* 1.7：Segment + HashEntry 的方式进行实现
* 1.8：与 HashMap 相同（散列表（数组 + 链表）+ 红黑树）采用 Node 数组 + CAS + Synchronized 来保证并发安全进行实现

### 存储结构

#### jdk1.7

jdk1.7 中采用 Segment + HashEntry 的方式进行实现

![](https://img-blog.csdnimg.cn/20190122185049974.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

**Segment：其继承于 ReentrantLock 类，从而使得 Segment 对象可以充当锁的角色。**

Segment 中包含 HashBucket 的数组，其可以守护其包含的若干个桶。

```
static final class HashEntry<K,V> {
final int hash;
final K key;
volatile V value;
volatile HashEntry<K,V> next;
}


```

ConcurrentHashMap 采用了分段锁，**每个分段锁维护着几个桶，多个线程可以同时访问不同分段锁上的桶，从而使其并发度更高（并发度就是 Segment 的个数）。**

#### jdk1.8

![](https://img-blog.csdnimg.cn/20190122185500569.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

* **JDK 1.7 使用分段锁机制**来实现并发更新操作，核心类为 Segment，它继承自重入锁 ReentrantLock，并发程度与 Segment 数量相等。

* **JDK 1.8 使用了 CAS 操作**来支持更高的并发度，在 CAS 操作失败时使用内置锁 synchronized。

* **并且 JDK 1.8 的实现也在链表过长时会转换为红黑树。**


1.8 中放弃了 Segment 臃肿的设计，取而代之的是采用 Node 数组 + CAS + Synchronized 来保证并发安全进行实现

### 添加元素：put

![](https://img-blog.csdnimg.cn/20190122192207539.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

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

![](https://img-blog.csdnimg.cn/20190122192215385.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

**为什么用这么方式删除呢，细心的同学会发现上面定义的 HashEntry 的 key 和 next 都是 final 类型的，所以不能改变 next 的指向，所以又复制了一份指向删除的结点的 next。**

### Collections.synchronizedMap() 与 ConcurrentHashMap 的区别

参考：[https://blog.csdn.net/lanxiangru/article/details/53495854](https://blog.csdn.net/lanxiangru/article/details/53495854)

* Collections.synchronizedMap() 和 Hashtable 一样，实现上在调用 map 所有方法时，都对整个 map 进行同步，**而 ConcurrentHashMap 的实现却更加精细，它对 map 中的所有桶加了锁**，**同步操作精确控制到桶，所以，即使在遍历 map 时，其他线程试图对 map 进行数据修改，也不会抛出 ConcurrentModificationException。**
* ConcurrentHashMap 从类的命名就能看出，它是个 HashMap。而 **Collections.synchronizedMap() 可以接收任意 Map 实例**，实现 Map 的同步。比如 TreeMap。

### 总结

ConcurrentHashMap 的高并发性主要来自于三个方面：

* 用**分离锁**实现多个线程间的更深层次的共享访问。
* 用 **HashEntery 对象的不变性**来降低执行读操作的线程在遍历链表期间对加锁的需求。
* 通过对同一个 Volatile 变量的写 / 读访问，协调不同线程间读 / 写操作的内存可见性。

LinkedHashMap
-------------

[http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap.html](http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap.html)

[https://segmentfault.com/a/1190000014319445](https://segmentfault.com/a/1190000014319445)

### 关键词

* 允许使用 null 值和 null 键
* 此实现**不是同步的**（linkedlist，lilnkedhashset 也不是同步的）
* 维护着一个**运行于所有条目的双向链表**。定义了迭代顺序，该迭代顺序可以是**插入顺序**或者是**访问顺序**。
* **初始容量对遍历没有影响**：遍历的双向链表，而不是散列表
* 在访问顺序的情况下，使用 get 方法也是结构性的修改（会导致 Fail-Fast）

### 概论

![](https://img-blog.csdnimg.cn/20190122192225937.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20190122192233204.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

### 成员变量

**该 Entry 除了保存当前对象的引用外，还保存了其上一个元素 before 和下一个元素 after 的引用，从而在哈希表的基础上又构成了双向链接列表。**

```
/**
* LinkedHashMap的Entry元素。
* 继承HashMap的Entry元素，又保存了其上一个元素before和下一个元素after的引用。
 */
static class Entry<K,V> extends HashMap.Node<K,V> {
Entry<K,V> before, after;
Entry(int hash, K key, V value, Node<K,V> next) {
super(hash, key, value, next);
}
}


```

### 构造器

![](https://img-blog.csdnimg.cn/20190122192238538.png)

* 通过源代码可以看出，在 LinkedHashMap 的构造方法中，实际调用了父类 HashMap 的相关构造方法来构造一个底层存放的 table 数组，但额外可以增加 accessOrder 这个参数，如果不设置

* **默认为 false，代表按照插入顺序进行迭代；**
* 当然可以显式设置为 true，代表以访问顺序进行迭代。
* 在构建新节点时，构建的是 LinkedHashMap.Entry 不再是 Node.


### 获取元素：get

LinkedHashMap 重写了父类 HashMap 的 get 方法，实际在调用父类 getEntry() 方法取得查找的元素后，再判断当排序模式 accessOrder 为 true 时，记录访问顺序，将最新访问的元素添加到双向链表的表头，并从原来的位置删除。

**由于的链表的增加、删除操作是常量级的，故并不会带来性能的损失。**

### 遍历元素

**为啥注释说：初始容量对遍历没有影响？**

**因为它遍历的是 LinkedHashMap 内部维护的一个双向链表，而不是散列表 (当然了，链表双向链表的元素都来源于散列表)**

### LinkedHashMap 应用

[http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap-lrucache.html](http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap-lrucache.html)

#### LRU 最近最少使用（访问顺序）

用这个类有两大好处：

* **它本身已经实现了按照访问顺序或插入顺序的存储**
* LinkedHashMap **本身有 removeEldestEntry 方法用于判断是否需要移除最不常读取的数**，但是，原始方法默认不需要移除，我们需要 override 这样一个方法。

**Java 里面实现 LRU 缓存通常有两种选择：**

* 使用 LinkedHashMap
* 自己设计数据结构，使用链表 + HashMap

**以下是使用 LinkedHashMap 实现的一个 LRU 缓存：**

* 设定最大缓存空间 MAX_ENTRIES 为 3；
* 使用 LinkedHashMap 的构造函数将 accessOrder 设置为 true，开启 LRU 顺序；
* 覆盖 removeEldestEntry() 方法实现，在节点多于 MAX_ENTRIES 就会将最近最久未使用的数据移除。

```
class LRUCache<K, V> extends LinkedHashMap<K, V> {
private static final int MAX_ENTRIES = 3;

protected boolean removeEldestEntry(Map.Entry eldest) {
return size() > MAX_ENTRIES;
}

LRUCache() {
super(MAX_ENTRIES, 0.75f, true);
}
}


```

```java
public static void main(String[] args) {
LRUCache<Integer, String> cache = new LRUCache<>();
cache.put(1, "a");
cache.put(2, "b");
cache.put(3, "c");
cache.get(1);
cache.put(4, "d");
System.out.println(cache.keySet());
}


```

```java
[3, 1, 4]


```

**实现详细代码请参考文章：补充知识点 - 缓存**

#### FIFO（插入顺序）

还可以在**插入顺序**的 LinkedHashMap 直接重写下 removeEldestEntry 方法即可轻松实现一个 FIFO 缓存

TreeMap
-------

### 关键词

* **红黑树**
* **非同步**
* **key 不能为 null**
* 实现了 NavigableMap 接口，而 NavigableMap 接口继承着 SortedMap 接口，是有序的（HahMap 是 Key 无序的）
* TreeMap 的基本操作 containsKey、get、put 和 remove 的时间复杂度是 log(n) 。
* 适用于**查找性能要求不那么高，反而对有序性要求比较高的应用场景**
* 使用 Comparator 或者 Comparable 来比较 key 是否相等与排序的问题

### 概览

![](https://img-blog.csdnimg.cn/20190122192247720.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

### 获取元素：get

详细看：

[https://segmentfault.com/a/1190000014345983#articleHeader4](https://segmentfault.com/a/1190000014345983#articleHeader4)

总结：

* 如果在构造方法中传递了 Comparator 对象，那么就会以 Comparator 对象的方法进行比较。否则，则使用 Comparable 的 compareTo(T o) 方法来比较。
* 值得说明的是：如果使用的是 compareTo(T o) 方法来比较，key 一定是不能为 null，并且得实现了 Comparable 接口的。
* 即使是传入了 Comparator 对象，不用 compareTo(T o) 方法来比较，key 也是不能为 null 的

### 删除元素：remove

**删除节点并且平衡红黑树**

HashSet
-------

[http://wiki.jikexueyuan.com/project/java-collection/hashset.html](http://wiki.jikexueyuan.com/project/java-collection/hashset.html)

[https://segmentfault.com/a/1190000014391402](https://segmentfault.com/a/1190000014391402)

### 关键词：

* 默认容量 16，扩容两倍，加载因子 0.75

* 允许元素为 null

* 实现 Set 接口

* 不保证迭代顺序

* 非同步

* 初始容量非常影响迭代性能

* 底层实际上是一个 HashMap 实例

> public HashSet() {map = new HashMap<>();}


如果添加的是在 HashSet 中不存在的，则返回 true；如果添加的元素已经存在，返回 false。

**对于 HashSet 中保存的对象，请注意正确重写其 equals 和 hashCode 方法，以保证放入的对象的唯一性。**

### HashSet 和 HashMap 的区别

重要：

**1. HashMap 中使用键对象来计算 hashcode 值**

**2. HashSet 使用成员对象来计算 hashcode 值，对于两个对象来说 hashcode 可能相同，所以 equals() 方法用来判断对象的相等性，如果两个对象不同的话，那么返回 false**

![](https://img-blog.csdnimg.cn/20190122192302879.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

TreeSet
-------

### 关键词

* 实现 NavigableSet 接口
* 可以实现排序功能
* 底层实际上是一个 TreeMap 实例
* 非同步
* 不允许为 null

LinkedHashSet
-------------

### 关键词

* 迭代是有序的
* 允许为 null
* 底层实际上是一个 HashMap + 双向链表实例 (其实就是 LinkedHashMap)
* 非同步
* 性能比 HashSet 差一丢丢，因为要维护一个双向链表
* **初始容量与迭代无关（与 LinkedHashMap 相同）**，因为 LinkedHashSet 迭代的是双向链表

总结 Set
------

HashSet：

* 无序，允许为 null，底层是 HashMap(散列表 + 红黑树)，非线程同步

TreeSet：

* 有序，不允许为 null，底层是 TreeMap(红黑树), 非线程同步

LinkedHashSet：

* 迭代有序，允许为 null，底层是 HashMap + 双向链表，非线程同步

WeekHashMap
-----------

### 存储结构

WeakHashMap 的 Entry 继承自 WeakReference，被 WeakReference 关联的**对象在下一次垃圾回收时会被回收**。

WeakHashMap 主要用来实现缓存，通过使用 WeakHashMap 来引用缓存对象，由 JVM 对这部分缓存进行回收。

```
1
private static class Entry<K,V> extends WeakReference<Object> implements Map.Entry<K,V>


```

### ConcurrentCache

Tomcat 中的 ConcurrentCache 使用了 WeakHashMap 来实现缓存功能。

ConcurrentCache 采取的是分代缓存：

* 经常使用的对象放入 eden 中，eden 使用 ConcurrentHashMap 实现，不用担心会被回收（伊甸园）；
* 不常用的对象放入 longterm，longterm 使用 WeakHashMap 实现，这些老对象会被垃圾收集器回收。
* 当调用 get() 方法时，会先从 eden 区获取，如果没有找到的话再到 longterm 获取，当从 longterm 获取到就把对象放入 eden 中，从而保证经常被访问的节点不容易被回收。
* 当调用 put() 方法时，如果 eden 的大小超过了 size，那么就将 eden 中的所有对象都放入 longterm 中，利用虚拟机回收掉一部分不经常使用的对象。

```java
public final class ConcurrentCache<K, V> {

private final int size;

private final Map<K, V> eden;

private final Map<K, V> longterm;

public ConcurrentCache(int size) {
this.size = size;
this.eden = new ConcurrentHashMap<>(size);
this.longterm = new WeakHashMap<>(size);
}

public V get(K k) {
V v = this.eden.get(k);
if (v == null) {
v = this.longterm.get(k);
if (v != null)
this.eden.put(k, v);
}
return v;
}

public void put(K k, V v) {
if (this.eden.size() >= size) {
this.longterm.putAll(this.eden);
this.eden.clear();
}
this.eden.put(k, v);
}
}


```

常见问题总结
======

Enumeration 和 Iterator 接口的区别
----------------------------

Iterator 替代了 Enumeration，Enumeration 是一个旧的迭代器了。

与 Enumeration 相比，Iterator 更加安全，因为当一个集合正在被遍历的时候，**它会阻止其它线程去修改集合。**

区别有三点：

* Iterator 的方法名比 Enumeration 更科学
* **Iterator 有 fail-fast 机制，比 Enumeration 更安全**
* Iterator 能够删除元素，Enumeration 并不能删除元素

ListIterator 有什么特点
------------------

* ListIterator 继承了 Iterator 接口，它用于遍历 List 集合的元素。
* ListIterator 可以实现双向遍历, 添加元素，设置元素

![](https://img-blog.csdnimg.cn/20190122192315721.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

与 Java 集合框架相关的有哪些最好的实践
----------------------

如果是单列的集合，我们考虑用 Collection 下的子接口 ArrayList 和 Set。

如果是映射，我们就考虑使用 Map

* 是否需要同步：去找线程安全的集合类使用

* 迭代时是否需要有序 (插入顺序有序)：去找 Linked 双向列表结构的

* 是否需要排序 (自然顺序或者手动排序)：去找 Tree 红黑树类型的 (JDK1.8)

* 估算存放集合的数据量有多大，无论是 List 还是 Map，它们实现动态增长，都是有性能消耗的。在初始集合的时候给出一个合理的容量会减少动态增长时的消耗

* 使用泛型，避免在运行时出现 ClassCastException

* 尽可能使用 Collections 工具类，或者获取只读、同步或空的集合，而非编写自己的实现。它将会提供代码重用性，它有着更好的稳定性和可维护性


参考
==

* [https://github.com/CyC2018/CS-Notes/blob/master/docs/notes/Java 容器. md](https://github.com/CyC2018/CS-Notes/blob/master/docs/notes/Java%20%E5%AE%B9%E5%99%A8.md)
* Eckel B. Java 编程思想 [M]. 机械工业出版社, 2002.
* [Java Collection Framework](https://www.w3resource.com/java-tutorial/java-collections.php)
* [Iterator 模式](https://openhome.cc/Gossip/DesignPattern/IteratorPattern.htm)
* [Java 8 系列之重新认识 HashMap](https://tech.meituan.com/java_hashmap.html)
* [What is difference between HashMap and Hashtable in Java?](http://javarevisited.blogspot.hk/2010/10/difference-between-hashmap-and.html)
* [Java 集合之 HashMap](http://www.zhangchangle.com/2018/02/07/Java%E9%9B%86%E5%90%88%E4%B9%8BHashMap/)
* [The principle of ConcurrentHashMap analysis](http://www.programering.com/a/MDO3QDNwATM.html)
* [探索 ConcurrentHashMap 高并发性的实现机制](https://www.ibm.com/developerworks/cn/java/java-lo-concurrenthashmap/)
* [HashMap 相关面试题及其解答](https://www.jianshu.com/p/75adf47958a7)
* [Java 集合细节（二）：asList 的缺陷](http://wiki.jikexueyuan.com/project/java-enhancement/java-thirtysix.html)
* [Java Collection Framework – The LinkedList Class](http://javaconceptoftheday.com/java-collection-framework-linkedlist-class/)

关注我
===

本人目前为后台开发工程师，主要关注 Python 爬虫，后台开发等相关技术。

**原创博客主要内容：**

* 笔试面试复习知识点手册
* Leetcode 算法题解析（前 150 题）
* 剑指 offer 算法题解析
* Python 爬虫相关实战
* 后台开发相关实战

**同步更新以下几大博客：**

* Csdn：

[http://blog.csdn.net/qqxx6661](http://blog.csdn.net/qqxx6661)

拥有专栏：Leetcode 题解（Java/Python）、Python 爬虫开发

* 知乎：

[https://www.zhihu.com/people/yang-zhen-dong-1/](https://www.zhihu.com/people/yang-zhen-dong-1/)

拥有专栏：码农面试助攻手册

* 掘金：

[https://juejin.im/user/5b48015ce51d45191462ba55](https://juejin.im/user/5b48015ce51d45191462ba55)

* 简书：

[https://www.jianshu.com/u/b5f225ca2376](https://www.jianshu.com/u/b5f225ca2376)

* 个人公众号：Rude3Knife

![](https://img-blog.csdnimg.cn/20190108201638256.png)