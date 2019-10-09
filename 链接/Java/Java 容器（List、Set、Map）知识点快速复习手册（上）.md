> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483743&idx=1&sn=cc38aab9429905ddc757b529a386d1dd&chksm=fbdb18deccac91c8d0be8b3ae0e4266bb08ead73a2e57f2c977705e7b26ceaaec7aff5d5c67c&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFl35gARpP3GLUcfRIdSrOeiaKGd5J8TOws6WyN4ZE3EUNzemTLh1XOoDw/640?wx_fmt=png)

前言
==

本文快速回顾了 Java 中容器的知识点，用作面试复习，事半功倍。

上篇：主要为容器概览，容器中用到的设计模式，List 源码

中篇：Map 源码

下篇：Set 源码，容器总结

其它知识点复习手册
---------

*   [Java 基础知识点面试手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=1&sn=de2751593468f470902b698c19f8987f&chksm=fbdb18d3ccac91c56939e55cd1f0ca1b4753fd178d229440ecbf89752f5e0d518acd453e2497&scene=21#wechat_redirect)
    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
*   [Java 基础知识点面试手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=2&sn=5610afab774b4114110c993fd0fdc43d&chksm=fbdb18d3ccac91c5e3d2978e3a780b14d09e97c997a5c50410d1adb1930da76f40238d8f409d&scene=21#wechat_redirect)
    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    

概览
==

容器主要包括 Collection 和 Map 两种，Collection 又包含了 List、Set 以及 Queue。

Collection
----------

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFlZfYEmUPFYE55rHQKUPib3ZsYT0DQlASwFYeGhsBzicOr3keJia149afhQ/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFlXaZmAjpYuiciakj9PUfY3aqdTCbtCBl9mkZ9NZCWFACK7bkcSII8wHtw/640?wx_fmt=png)

**数组和集合的区别:**

*   长度
    

*   数组的长度固定
    
*   集合的长度可变
    

*   内容
    

*   数组存储的是同一种类型的元素
    
*   集合可以存储不同类型的元素 (但是一般我们不这样干..)
    

*   元素的数据类型
    

*   数组可以存储**基本数据类型**, 也可以存储**引用类型**
    
*   **集合只能存储引用类型** (若存储的是简单的 int，它会自动装箱成 Integer)
    

### 1. Set（元素不可重复）

*   HashSet：基于 HashMap 实现，支持快速查找，**但不支持有序性操作**。
    
*   TreeSet：**基于红黑树实现，支持有序性操作**，但是查找效率不如 HashSet，**HashSet 查找时间复杂度为 O(1)，TreeSet 则为 O(logN)；**
    
*   LinkedHashSet：具有 HashSet 的查找效率，**且内部使用链表维护元素的插入顺序**。
    

### 2. List（有序 (存储顺序和取出顺序一致)，可重复）

*   ArrayList：基于动态数组实现，支持随机访问；
    
*   Vector：和 ArrayList 类似，但它是线程安全的；
    
*   LinkedList：基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList 还可以用作栈、队列和双向队列。
    

### 3. Queue

*   LinkedList：可以用它来支持双向队列；
    
*   PriorityQueue：基于堆结构实现，可以用它来实现优先队列。
    

Map
---

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFlPDbQUNJwYsLvic2K5la8xZeUOBibgFKDPOFh2RVZTVNtez5nQgcgQmIQ/640?wx_fmt=png)

*   HashMap：基于哈希实现；
    
*   HashTable：和 HashMap 类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入 HashTable 并且不会导致数据不一致。它是遗留类，**不应该去使用它**。
    
*   ConcurrentHashMap：支持线程安全，并且 ConcurrentHashMap 的效率会更高，因为 ConcurrentHashMap 引入了分段锁。
    
*   LinkedHashMap：**使用链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。**
    
*   TreeMap：基于红黑树实现。
    

Fail-Fast 机制和 Fail-Safe 机制
--------------------------

https://blog.csdn.net/Kato_op/article/details/80356618

### Fail-Fast

Fail-fast 机制是 java 集合 (Collection) 中的一种错误机制。 当多个线程对同一个集合的内容进行操作时，就可能会产生 fail-fast 事件。

*   迭代器在遍历时直接访问集合中的内容, 并且在遍历过程中使用一个 modCount 变量,
    
*   集合中在被遍历期间如果内容发生变化 (**增删改**), 就会改变 modCount 的值,
    
*   每当迭代器使用 hashNext()/next() 遍历下一个元素之前, 都会执行 checkForComodification() 方法检测，modCount 变量和 expectedmodCount 值是否相等,
    
*   如果相等就返回遍历, 否则抛出异常, 终止遍历.
    

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

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFl6GIlcIbj6OEIRzxftmO4QicMzMrb8oYCf8B9ibKGUwNbkqI2Xx6WqBrQ/640?wx_fmt=png)

*   Iterator 它是在 ArrayList 等集合的内部类的方式实现
    

Collection 实现了 Iterable 接口，其中的 iterator() 方法能够产生一个 Iterator 对象，通过这个对象就可以迭代遍历 Collection 中的元素。

从 JDK 1.5 之后可以使用 foreach 方法来遍历实现了 Iterable 接口的聚合对象。

```
List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
for (String item : list) {
    System.out.println(item);
}

```

适配器模式
-----

适配器模式解释：https://www.jianshu.com/p/93821721bf08

java.util.Arrays#asList() 可以把数组类型转换为 List 类型。

```
@SafeVarargs
public static <T> List<T> asList(T... a)

```

如果要将数组类型转换为 List 类型，应该注意的是 asList() 的参数为泛型的变长参数，因此不能使用基本类型数组作为参数，**只能使用相应的包装类型数组。**

```
Integer[] arr = {1, 2, 3};
List list = Arrays.asList(arr);

```

也可以使用以下方式生成 List。

```
List list = Arrays.asList(1,2,3);

```

源码分析
====

ArrayList
---------

### 关键词

*   默认大小为 10
    
*   扩容 1.5 倍，加载因子为 0.5
    
*   基于动态数组实现
    
*   删除元素时不会减少容量，若希望减少容量则调用 trimToSize()
    
*   它不是线程安全的
    
*   它能存放 null 值。
    
*   扩容操作需要调用 Arrays.copyOf() 把原数组整个复制到新数组
    
*   删除需要调用 System.arraycopy() 将 index+1 后面的元素都复制到 index 位置上，复制的代价很高。 - 序列化：只序列化数组中有元素填充那部分内容
    

### 概览

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFlTiake3QPuRhicPQ0fnajpB7GkJzkr528QWfpBM2JqkicGFEiaSxPSUCSpA/640?wx_fmt=png)

实现了 RandomAccess 接口，因此支持随机访问。这是理所当然的，因为 ArrayList 是基于数组实现的。

```
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable

```

### 扩容

如果不够时，需要使用 grow() 方法进行扩容，新容量的大小为 `oldCapacity+(oldCapacity>>1)`，也就是**旧容量的 1.5 倍。**

**扩容操作需要调用 `Arrays.copyOf()` 把原数组整个复制到新数组**中

因此最好在创建 ArrayList **对象时就指定大概的容量大小，减少扩容操作的次数。**

```
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
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

*   足够：直接添加
    
*   不足够：扩容
    

**扩容到原来的 1.5 倍，第一次扩容后，如果容量还是小于 minCapacity，就将容量扩充为 minCapacity。**

**add(int index, E element)**

步骤：

*   检查角标
    
*   空间检查，如果有需要进行扩容
    
*   插入元素
    

### 删除元素：remove

步骤：

*   检查角标
    
*   删除元素
    
*   计算出需要移动的个数，并移动
    
*   设置为 null，让 GC 回收（所以说不是立刻回收，而是等待 GC 回收）
    

```
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

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFlt77fBH9cjdoLWD8zJDkIiaPBaibrlI1HkJ8uhn2LRoLl4PaNgKPVvZ8w/640?wx_fmt=png)

### Fail-Fast

modCount 用来记录 ArrayList 结构发生变化的次数。结构发生变化是指添加或者删除至少一个元素的所有操作，或者是调整内部数组的大小，仅仅只是设置元素的值不算结构发生变化。

在进行序列化或者迭代等操作时，需要比较操作前后 modCount 是否改变，如果改变了需要抛出 ConcurrentModificationException。

```
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

*   public ArrayList() 可以构造一个默认初始容量为 10 的空列表；
    
*   public ArrayList(int initialCapacity) 构造一个指定初始容量的空列表；
    
*   public ArrayList(Collection c) 构造一个包含指定 collection 的元素的列表，这些元素按照该 collection 的迭代器返回它们的顺序排列的。
    

### 序列化

**补充：transient 讲解**

http://www.importnew.com/21517.html

> 你只需要实现 Serilizable 接口，将不需要序列化的属性前添加关键字 transient，序列化对象的时候，这个属性就不会序列化到指定的目的地中。

ArrayList 基于数组实现，并且具有动态扩容特性，因此保存元素的数组不一定都会被使用，那么就没必要全部进行序列化。

保存元素的数组 elementData 使用 transient 修饰，**该关键字声明数组默认不会被序列化**。

```
transient Object[] elementData; // non-private to simplify nested class access

```

ArrayList 实现了 writeObject() 和 readObject() **来控制只序列化数组中有元素填充那部分内容**。

```
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
ArrayList list = new ArrayList();
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
oos.writeObject(list);

```

Vector
------

### 关键词

*   默认大小为 10（与 Arraylist 相同）
    
*   扩容 2 倍，加载因子是 1（Arraylist 是扩容 1.5 倍，加载因子为 0.5）
    
*   其它几乎与 ArrayList 完全相同，唯一的区别在于 Vector 是同步的，因此开销就比 ArrayList 要大，访问速度更慢。
    
*   使用了 synchronized 进行同步
    
*   Vector 是 jdk1.2 的类了，比较老旧的一个集合类。应使用 JUC 的 CopyOnWriteArrayList 代替
    

### 替代方案

可以使用 `Collections.synchronizedList();` 得到一个线程安全的 ArrayList。

```
List<String> list = new ArrayList<>();
List<String> synList = Collections.synchronizedList(list);

```

也可以使用 concurrent 并发包下的 CopyOnWriteArrayList 类。

```
List<String> list = new CopyOnWriteArrayList<>();

```

CopyOnWriteArrayList
--------------------

### 关键词

*   写操作在一个复制的数组上进行，读操作还是在原始数组中进行，读写分离，互不影响。
    
*   写操作需要加锁，防止并发写入时导致写入数据丢失。
    
*   写操作结束之后需要把原始数组指向新的复制数组。
    
*   **适用于读操作远大于写操作的场景。**
    

### 读写分离

```
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
@SuppressWarnings("unchecked")
private E get(Object[] a, int index) {
    return (E) a[index];
}

```

### 适用场景

CopyOnWriteArrayList 在写操作的同时允许读操作，大大提高了读操作的性能，因此很适合读多写少的应用场景。

### 缺陷

*   内存占用：在写操作时需要复制一个新的数组，**使得内存占用为原来的两倍左右；**
    
*   数据不一致：**读操作不能读取实时性的数据，因为部分写操作的数据还未同步到读数组中**。
    

所以 CopyOnWriteArrayList **不适合内存敏感以及对实时性要求很高的场景。**

LinkedList
----------

### 关键词

*   **双向**链表
    
*   默认大小为 10
    
*   带 Head 和 Tail 指针
    
*   Node 存储节点信息
    

### 概览

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFlQlM4oYlKQ2JQXGCzzhaVoibdjPUmVldnQldHVW4ViciavH8gPt1aucj7w/640?wx_fmt=png)

基于双向链表实现，内部使用 Node 来存储链表节点信息。

```
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}

```

每个链表存储了 Head 和 Tail 指针：

```
transient Node<E> first;
transient Node<E> last;

```

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFlOIyGYOb1UVhtOSlvI2QgU13O12khlyyYG5OOEOmKJ6JHH1EMiaFrYjA/640?wx_fmt=png)

### ArrayList 与 LinkedList 比较

*   ArrayList 基于**动态数组**实现，LinkedList 基于**双向链表**实现；
    
*   **ArrayList 支持随机访问，LinkedList 不支持；**
    
*   **LinkedList 在任意位置添加删除元素更快。**
    

### 删除元素：remove

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFlv47nzseEvLiaTUGibKekvQz20EN6d6h2UJdSfSL8NOBTXCbPmjf1yXJQ/640?wx_fmt=png)

### 获取元素：get

*   下标小于长度的一半，从头遍历
    
*   反之，从尾部遍历
    

### 替换元素：set

set 方法和 get 方法其实差不多，根据下标来判断是从头遍历还是从尾遍历

### 其他方法

**LinkedList 实现了 Deque 接口，因此，我们可以操作 LinkedList 像操作队列和栈一样**

LinkedList 的方法比 ArrayList 的方法多太多了，这里我就不一一说明了。具体可参考：

*   https://blog.csdn.net/panweiwei1994/article/details/77110354
    
*   https://zhuanlan.zhihu.com/p/24730576
    
*   https://zhuanlan.zhihu.com/p/28373321
    

参考
==

*   https://github.com/CyC2018/CS-Notes/blob/master/docs/notes/Java%20%E5%AE%B9%E5%99%A8.md
    
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

**原创博客主要内容：**

*   笔试面试复习知识点手册
    
*   Leetcode 算法题解析（前 150 题）
    
*   剑指 offer 算法题解析
    
*   Python 爬虫相关实战
    
*   后台开发相关实战
    

**同步更新以下几大博客：**

*   Csdn：
    

http://blog.csdn.net/qqxx6661

拥有专栏：Leetcode 题解（Java/Python）、Python 爬虫开发

*   知乎：
    

https://www.zhihu.com/people/yang-zhen-dong-1/

拥有专栏：码农面试助攻手册

*   掘金：
    

https://juejin.im/user/5b48015ce51d45191462ba55

*   简书：
    

https://www.jianshu.com/u/b5f225ca2376

*   个人公众号：Rude3Knife
    

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZLup4F2QYXlHTSz4PlcMFlpZ6G0eYKhjvIHlZicTX0A36atnShSR6WAwhia5DiacEIprzErticdnk0mg/640?wx_fmt=png)