# JAVA 基础

## 前言

本文快速回顾了 Java 中最基础的知识点，用作面试前突击复习，事半功倍。

此为上篇，包括内容：基础知识点，集合类  

## 基础知识点

### 1. 面向对象的特性

答：封装、继承和多态。

多态分为**编译时多态和运行时多态。**

* 编译时多态：**方法的重载**

* 运行时多态：指程序中定义的对象引用所指向的具体类型在运行期间才确定。

**运行时多态**有三个**条件**：

多态的存在有三个前提:

* 要有继承关系

* 子类要重写父类的方法

* 父类引用指向子类对,

父类 Animal

```java
class Animal {
    int num = 10;
    static int age = 20;
    public void eat() {
        System.out.println("动物吃饭");
        }
    public static void sleep() {
        System.out.println("动物在睡觉");
        }
    public void run(){
        System.out.println("动物在奔跑");
       }
}

```

子类 Cat

```java
class Cat extends Animal {
    int num = 80;
    static int age = 90;
     String name = "tomCat";
    public void eat() {
        System.out.println("猫吃饭");
    }
    public static void sleep() {
        System.out.println("猫在睡觉");
    }
    public void catchMouse() {
        System.out.println("猫在抓老鼠");
    }
}

```

测试类 Demo_Test1

```java
class Demo_Test1 {
    public static void main(String[] args) {
    Animal am = new Cat();
    am.eat();
    am.sleep();
    am.run();
    //am.catchMouse();这里先注释掉，等会会说明
     //System.out.println(am.name);//这里先注释，待会说明
    System.out.println(am.num);
    System.out.println(am.age);
    }
}

```

以上的三段代码充分体现了多态的三个前提，即：

```java
1、存在继承关系

Cat类继承了Animal类

2、子类要重写父类的方法

子类重写(override)了父类的两个成员方法eat()，sleep()。其中eat()是非静态的，sleep()是静态的（static）。

3、父类数据类型的引用指向子类对象。

```

如果再深究一点呢，我们可以看看上面测试类的输出结果，或许对多态会有更深层次的认识。猜一猜上面  
的结果是什么。

可以看出来

子类 Cat 重写了父类 Animal 的非静态成员方法 am.eat(); 的输出结果为：猫吃饭。

子类重写了父类 (Animal) 的静态成员方法 am.sleep(); 的输出结果为：动物在睡觉

未被子类（Cat）重写的父类（Animal）方法 am.run() 输出结果为：动物在奔跑

**那么我们可以根据以上情况总结出多态成员访问的特点：**

```java
成员变量
- 编译看左边(父类),运行看左边(父类)
成员方法
- 编译看左边(父类)，运行看右边(子类)。动态绑定
静态方法
- 编译看左边(父类)，运行看左边(父类)。

(静态和类相关，算不上重写，所以，访问还是左边的)
只有非静态的成员方法,编译看左边,运行看右边

```

**那么多态有什么弊端呢？**

不能使用子类特有的成员属性和子类特有的成员方法。

参考：[https://www.zhihu.com/question/30082151](https://www.zhihu.com/question/30082151)

很明显，执行强转语句 Cat ct = (Cat)am; 之后，ct 就指向最开始在堆内存中创建的那个 Cat 类型的对象了。

**这就是多态的魅力吧，虽然它有缺点，但是它确实十分灵活，减少多余对象的创建，不用说为了使用子类的某个方法又去重新再堆内存中开辟一个新的子类对象。**

### 2. 数据类型

String 是类类型，不是基本类型。

基本类型 有八种：整型 （4 种）字符型 （1 种）浮点型 （2 种）布尔型（1 种）

![整型数据类型](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl30uG9wzO3ZuMy1PqB3o4qicT8PerdDXQdyoC3IkoBpsOlTiaAtVuOnKicvg/640?wx_fmt=png)

![浮点型数据类型](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl300z95CxL5WVjicOrcSJZaicK5NfNgfmRfkFWjCvxGPFeL4ejUgH7l8tLQ/640?wx_fmt=png)

缓存池

```java
public class Main_1 {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);//true，缓存池
        System.out.println(e == f);//false，不在缓存池
        System.out.println(c == (a + b));//true
        System.out.println(c.equals(a + b));//true
        System.out.println(g == (a + b));//true
        System.out.println(g.equals(a + b));//false
        System.out.println(g.equals(a + h));//true
    }

```

**使用 == 的情况：**

*   如果比较 Integer 变量，**默认比较的是地址值。**
    
*   特例：如果比较的**某一边有操作表达式 (例如 a+b)**，那么比较的是**具体数值**
    

**使用 equals() 的情况：**

*   无论是 Integer 还是 Long 中的 equals() **默认比较的是数值**。
    
*   **特例：Long 的 equals() 方法，JDK 的默认实现：会判断是否是 Long 类型**
    

**new Integer(123) 与 Integer.valueOf(123) 的区别在于：**

*   new Integer(123) **每次都会新建一个对象**
    
*   Integer.valueOf(123) **会使用缓存池中的对象**，多次调用会取得同一个对象的引用。
    

**valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容。**

```
1public static Integer valueOf(int i) {
2    if (i >= IntegerCache.low && i <= IntegerCache.high)
3        return IntegerCache.cache[i + (-IntegerCache.low)];
4    return new Integer(i);
5}


```

**基本类型对应的缓冲池如下：**

*   boolean values true and false
    
*   all byte values
    
*   short values between -128 and 127
    
*   int values between -128 and 127
    
*   char in the range \u0000 to \u007F
    

String
------

### 概览

**String 被声明为 final，因此它不可被继承。**

在 Java 8 中，String 内部使用 char 数组存储数据。

```
1public final class String
2    implements java.io.Serializable, Comparable<String>, CharSequence {
3    /** The value is used for character storage. */
4    private final char value[];
5}


```

在 Java 9 之后，String 类的实现改用 byte 数组存储字符串，同时使用 `coder` 来标识使用了哪种编码。

```
1public final class String
2    implements java.io.Serializable, Comparable<String>, CharSequence {
3    /** The value is used for character storage. */
4    private final byte[] value;
5
6    /** The identifier of the encoding used to encode the bytes in {@code value}. */
7    private final byte coder;
8}


```

### 不可变的好处

**1. 可以缓存 hash 值**

因为 String 的 hash 值经常被使用，例如 String 用做 HashMap 的 key。不可变的特性可以使得 hash 值也不可变，因此只需要进行一次计算。

**2. String Pool 的需要**

如果一个 String 对象已经被创建过了，那么就会从 String Pool 中取得引用。只有 String 是不可变的，才可能使用 String Pool。

**注意：不是 string 创建后就默认进入池的，请看下方 intern()**

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl30TgA8bWISiaczzxXyG0iaEyib4WibicI2UmdJbItNQuf9Cicgpe8qhMxxkJ4w/640?wx_fmt=png)在这里插入图片描述

**3. 安全性**

String 经常作为参数，String 不可变性可以保证参数不可变。例如在作为网络连接参数的情况下如果 String 是可变的，那么在网络连接过程中，String 被改变，改变 String 对象的那一方以为现在连接的是其它主机，而实际情况却不一定是。

**4. 线程安全**

String 不可变性天生具备线程安全，可以在多个线程中安全地使用。

Program Creek : Why String is immutable in Java?

### String, StringBuffer and StringBuilder

**1. 可变性**

*   String 不可变
    
*   StringBuffer 和 StringBuilder 可变
    

**2. 线程安全**

*   String 不可变，因此是线程安全的
    
*   StringBuilder 不是线程安全的
    
*   StringBuffer 是线程安全的，内部使用 synchronized 进行同步
    

**对于三者使用的总结：**

1. 如果要操作少量的数据用 String

2. **单线程**操作字符串缓冲区下操作大量数据 StringBuilder

3. **多线程**操作字符串缓冲区下操作大量数据 StringBuffer

### String 和 StringBuffer 的区别？

String 是 immutable 的, 其内容一旦创建好之后，就不可以发生改变。

StringBuffer 是可以变长的，内容也可以发生改变  
改变的原理是 StringBuffer 内部采用了字符数组存放数据，在需要增加长度的时候，创建新的数组，并且把原来的数据复制到新的数组这样的办法来实现。

https://blog.csdn.net/yeweiyang16/article/details/51755552

**初始化：可以指定给对象的实体的初始容量为参数字符串 s 的长度额外再加 16 个字符**

**扩容：尝试将新容量扩为大小变成原容量的 1 倍 + 2，然后 if 判断一下 容量如果不够，直接扩充到需要的容量大小。**

StackOverflow : String, StringBuffer, and StringBuilder

### String Pool

字符串常量池（String Pool）保存着所有字符串字面量（literal strings），这些字面量在编译时期就确定。不仅如此，还可以使用 String 的 intern() 方法在运行过程中将字符串添加到 String Pool 中。

当一个字符串调用 intern() 方法时，如果 String Pool 中已经存在一个字符串和该字符串值相等（使用 equals() 方法进行确定），那么就会返回 String Pool 中字符串的引用；否则，就会在 String Pool 中添加一个新的字符串，并返回这个新字符串的引用。

下面示例中，s1 和 s2 采用 new String() 的方式新建了两个不同字符串，而 s3 和 s4 是通过 s1.intern() 方法取得一个字符串引用。intern() 首先把 s1 引用的字符串放到 String Pool 中，然后返回这个字符串引用。因此 s3 和 s4 引用的是同一个字符串。

```
1String s1 = new String("aaa");
2String s2 = new String("aaa");
3System.out.println(s1 == s2);           // false
4String s3 = s1.intern();
5String s4 = s1.intern();
6System.out.println(s3 == s4);           // true


```

如果是采用 "bbb" 这种字面量的形式创建字符串，会自动地将字符串放入 String Pool 中。

```
1String s5 = "bbb";
2String s6 = "bbb";
3System.out.println(s5 == s6);  // true


```

在 Java 7 之前，String Pool 被放在运行时常量池中，它属于永久代。而在 Java 7，String Pool 被移到堆中。这是因为永久代的空间有限，在大量使用字符串的场景下会导致 OutOfMemoryError 错误。

*   StackOverflow : What is String interning?
    
*   深入解析 String#intern
    

### new String("abc")

使用这种方式一共会创建两个字符串对象（前提是 String Pool 中还没有 "abc" 字符串对象）。

*   "abc" 属于字符串字面量，因此编译时期会在 String Pool 中创建一个字符串对象，指向这个 "abc" 字符串字面量；
    
*   而使用 new 的方式会在堆中创建一个字符串对象。
    

创建一个测试类，其 main 方法中使用这种方式来创建字符串对象。

```
1public class NewStringTest {
2    public static void main(String[] args) {
3        String s = new String("abc");
4    }
5}


```

使用 javap -verbose 进行反编译，得到以下内容：

```
 1// ...
 2Constant pool:
 3// ...
 4   #2 = Class              #18            // java/lang/String
 5   #3 = String             #19            // abc
 6// ...
 7  #18 = Utf8               java/lang/String
 8  #19 = Utf8               abc
 9// ...
10
11  public static void main(java.lang.String[]);
12    descriptor: ([Ljava/lang/String;)V
13    flags: ACC_PUBLIC, ACC_STATIC
14    Code:
15      stack=3, locals=2, args_size=1
16         0: new           #2                  // class java/lang/String
17         3: dup
18         4: ldc           #3                  // String abc
19         6: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
20         9: astore_1
21// ...


```

在 Constant Pool 中，#19 存储这字符串字面量 "abc"，#3 是 String Pool 的字符串对象，它指向 #19 这个字符串字面量。在 main 方法中，0: 行使用 new #2 在堆中创建一个字符串对象，并且使用 ldc #3 将 String Pool 中的字符串对象作为 String 构造函数的参数。

以下是 String 构造函数的源码，可以看到，在将一个字符串对象作为另一个字符串对象的构造函数参数时，并不会完全复制 value 数组内容，而是都会指向同一个 value 数组。

```
1public String(String original) {
2    this.value = original.value;
3    this.hash = original.hash;
4}


```

运算
--

### 参数传递

参数传递
----

**Java 的参数是以值传递的形式传入方法中，而不是引用传递。**

以下代码中 Dog dog 的 dog 是一个指针，存储的是对象的地址。在将一个参数传入一个方法时，本质上是将对象的地址以值的方式传递到形参中。因此在方法中使指针引用其它对象，那么这两个指针此时指向的是完全不同的对象，在一方改变其所指向对象的内容时对另一方没有影响。

```
 1public class Dog {
 2
 3    String name;
 4
 5    Dog(String name) {
 6        this.name = name;
 7    }
 8
 9    String getName() {
10        return this.name;
11    }
12
13    void setName(String name) {
14        this.name = name;
15    }
16
17    String getObjectAddress() {
18        return super.toString();
19    }
20}


```

```
 1public class PassByValueExample {
 2    public static void main(String[] args) {
 3        Dog dog = new Dog("A");
 4        System.out.println(dog.getObjectAddress()); // Dog@4554617c
 5        func(dog);
 6        System.out.println(dog.getObjectAddress()); // Dog@4554617c
 7        System.out.println(dog.getName());          // A
 8    }
 9
10    private static void func(Dog dog) {
11        System.out.println(dog.getObjectAddress()); // Dog@4554617c
12        dog = new Dog("B");
13        System.out.println(dog.getObjectAddress()); // Dog@74a14482
14        System.out.println(dog.getName());          // B
15    }
16}


```

如果在方法中改变对象的字段值会改变原对象该字段值，因为改变的是同一个地址指向的内容。

```
 1class PassByValueExample {
 2    public static void main(String[] args) {
 3        Dog dog = new Dog("A");
 4        func(dog);
 5        System.out.println(dog.getName());          // B
 6    }
 7
 8    private static void func(Dog dog) {
 9        dog.setName("B");
10    }
11}


```

StackOverflow: Is Java “pass-by-reference” or “pass-by-value”?

### float 与 double

Java 不能隐式执行向下转型，因为这会使得精度降低。

1.1 字面量属于 double 类型，不能直接将 1.1 直接赋值给 float 变量，因为这是向下转型。Java 不能隐式执行向下转型，因为这会使得精度降低。

```
1// float f = 1.1;


```

1.1f 字面量才是 float 类型。

```
1float f = 1.1f;


```

### 隐式类型转换

因为字面量 1 是 int 类型，它比 short 类型精度要高，因此不能隐式地将 int 类型下转型为 short 类型。

```
1short s1 = 1;
2// s1 = s1 + 1;


```

但是使用 += 运算符可以执行隐式类型转换。

```
1s1 += 1;


```

上面的语句相当于将 s1 + 1 的计算结果进行了向下转型：

```
1s1 = (short) (s1 + 1);


```

StackOverflow : Why don't Java's +=, -=, *=, /= compound assignment operators require casting?

### switch

从 Java 7 开始，可以在 switch 条件判断语句中使用 String 对象。

```
1String s = "a";
2switch (s) {
3    case "a":
4        System.out.println("aaa");
5        break;
6    case "b":
7        System.out.println("bbb");
8        break;
9}


```

switch 不支持 long，是因为 switch 的设计初衷是对那些只有少数的几个值进行等值判断，如果值过于复杂，那么还是用 if 比较合适。

继承
--

### 访问权限

Java 中有三个访问权限修饰符：private、protected 以及 public，**如果不加访问修饰符，表示包级可见。**

可以对类或类中的成员（字段以及方法）加上访问修饰符。

*   类可见表示其它类可以用这个类创建实例对象。
    
*   成员可见表示其它类可以用这个类的实例对象访问到该成员；
    

**protected 用于修饰成员，表示在继承体系中成员对于子类可见**，但是这个访问修饰符对于类没有意义。

如果**子类的方法重写了父类的方法，那么子类中该方法的访问级别不允许低于父类的访问级别**：这是为了确保可以使用父类实例的地方都可以使用子类实例，也就是确保满足里氏替换原则。

**字段决不能是公有的，因为这么做的话就失去了对这个字段修改行为的控制，客户端可以对其随意修改。**

例如下面的例子中，AccessExample 拥有 id 共有字段，如果在某个时刻，我们想要使用 int 去存储 id 字段，那么就需要去修改所有的客户端代码。

```
1public class AccessExample {
2    public String id;
3}


```

可以使用公有的 getter 和 setter 方法来替换公有字段，这样的话就可以控制对字段的修改行为。

```
 1public class AccessExample {
 2
 3    private int id;
 4
 5    public String getId() {
 6        return id + "";
 7    }
 8
 9    public void setId(String id) {
10        this.id = Integer.valueOf(id);
11    }
12}


```

但是也有例外，如果是包级私有的类或者私有的嵌套类，那么直接暴露成员不会有特别大的影响。

```
 1public class AccessWithInnerClassExample {
 2    private class InnerClass {
 3        int x;
 4    }
 5
 6    private InnerClass innerClass;
 7
 8    public AccessWithInnerClassExample() {
 9        innerClass = new InnerClass();
10    }
11
12    public int getValue() {
13        return innerClass.x;  // 直接访问
14    }
15}


```

### 抽象类与接口

**1. 抽象类**

抽象类和普通类最大的区别是：

**抽象类不能被实例化，需要继承抽象类才能实例化其子类**。

```
 1public abstract class AbstractClassExample {
 2
 3    protected int x;
 4    private int y;
 5
 6    public abstract void func1();
 7
 8    public void func2() {
 9        System.out.println("func2");
10    }
11}


```

```
1public class AbstractExtendClassExample extends AbstractClassExample {
2    @Override
3    public void func1() {
4        System.out.println("func1");
5    }
6}


```

**2. 接口**

接口是抽象类的延伸，在 Java 8 之前，它可以看成是一个完全抽象的类，也就是说它不能有任何的方法实现。

**从 Java 8 开始，接口也可以拥有默认的方法实现**，这是因为不支持默认方法的接口的维护成本太高了。

**在 Java 8 之前，如果一个接口想要添加新的方法，那么要修改所有实现了该接口的类。**

**接口的成员（字段 + 方法）默认都是 public 的，并且不允许定义为 private 或者 protected。**

**接口的字段默认都是 static 和 final 的。**

```
 1public interface InterfaceExample {
 2    void func1();
 3
 4    default void func2(){
 5        System.out.println("func2");
 6    }
 7
 8    int x = 123;
 9    // int y;               // Variable 'y' might not have been initialized
10    public int z = 0;       // Modifier 'public' is redundant for interface fields
11    // private int k = 0;   // Modifier 'private' not allowed here
12    // protected int l = 0; // Modifier 'protected' not allowed here
13    // private void fun3(); // Modifier 'private' not allowed here
14}


```

```
1public class InterfaceImplementExample implements InterfaceExample {
2    @Override
3    public void func1() {
4        System.out.println("func1");
5    }
6}


```

**3. 比较**

*   **从设计层面上看，抽象类提供了一种 IS-A 关系，那么就必须满足里式替换原则，即子类对象必须能够替换掉所有父类对象。而接口更像是一种 LIKE-A 关系，它只是提供一种方法实现契约，并不要求接口和实现接口的类具有 IS-A 关系。**
    
*   从使用上来看，一个类可以实现多个接口，但是不能继承多个抽象类。
    
*   **接口的字段只能是 static 和 final 类型的**，而抽象类的字段没有这种限制。
    
*   **接口的成员只能是 public 的**，而抽象类的成员可以有多种访问权限。
    

**4. 使用选择**

使用接口：

*   需要让不相关的类都实现一个方法，例如不相关的类都可以实现 Compareable 接口中的 compareTo() 方法；
    
*   需要使用多重继承。
    

使用抽象类：

*   需要在几个相关的类中共享代码。
    
*   需要能控制继承来的成员的访问权限，而不是都为 public。
    
*   需要继承非静态 static 和非常量 final 字段。
    

**在很多情况下，接口优先于抽象类，因为接口没有抽象类严格的类层次结构要求，可以灵活地为一个类添加行为。并且从 Java 8 开始，接口也可以有默认的方法实现，使得修改接口的成本也变的很低。**

*   深入理解 abstract class 和 interface
    
*   When to Use Abstract Class and Interface
    

### super

*   **访问父类的构造函数**：可以使用 super() 函数访问父类的构造函数，从而委托父类完成一些初始化的工作。
    
*   **访问父类的成员**：如果子类重写了父类的中某个方法的实现，可以通过使用 super 关键字来引用父类的方法实现。
    

Using the Keyword super

### 继承相关小问题

#### 接口是否可继承接口?

可以，比如 List 就继承了接口 Collection

#### 抽象类是否可实现 (implements) 接口?

可以，比如 MouseAdapter 鼠标监听适配器 是一个抽象类，并且实现了 MouseListener 接口

#### 抽象类是否可继承实体类（concrete class）？

可以，所有抽象类，都继承了 Object

Object 通用方法
-----------

经典：

https://fangjian0423.github.io/2016/03/12/java-Object-method/

### 概览

```
 1public native int hashCode()
 2
 3public boolean equals(Object obj)
 4
 5protected native Object clone() throws CloneNotSupportedException
 6
 7public String toString()
 8
 9public final native Class<?> getClass()
10
11protected void finalize() throws Throwable {}
12
13public final native void notify()
14
15public final native void notifyAll()
16
17public final native void wait(long timeout) throws InterruptedException
18
19public final void wait(long timeout, int nanos) throws InterruptedException
20
21public final void wait() throws InterruptedException
22


```

*   getClass 方法
    

*   返回当前运行时对象的 Class 对象
    

*   hashCode 方法
    

*   该方法返回对象的哈希码，主要使用在哈希表中，比如 JDK 中的 HashMap。（文章中有对 hashcode 的详细解释）
    

*   equals 方法
    

*   如果重写了 equals 方法，通常有必要重写 hashCode 方法，这点已经在 hashCode 方法中说明了。
    

*   clone 方法
    

*   创建并返回当前对象的一份拷贝。**Object 本身没有实现 Cloneable 接口**，所以不重写 clone 方法并且进行调用的话会发生 CloneNotSupportedException 异常。
    
*   使用 clone() 方法来拷贝一个对象即复杂又有风险，它会抛出异常，并且还需要类型转换。Effective Java 书上讲到，最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象。
    

*   toString 方法
    

*   Object 对象的默认实现，即输出类的名字 @实例的哈希码的 16 进制。
    

*   notify 方法：
    

*   唤醒一个在此对象监视器上等待的线程 (监视器相当于就是锁的概念)。
    

*   notifyAll 方法
    

*   跟 notify 一样，唯一的区别就是会唤醒在此对象监视器上等待的所有线程，而不是一个线程。
    

*   wait(long timeout) throws InterruptedException 方法
    

*   wait 方法会让当前线程等待直到另外一个线程调用对象的 notify 或 notifyAll 方法，或者超过参数设置的 timeout 超时时间。
    

*   wait(long timeout, int nanos) throws InterruptedException 方法
    

*   跟 wait(long timeout) 方法类似，**多了一个 nanos 参数，这个参数表示额外时间**（以毫微秒为单位，范围是 0-999999）。 所以超时的时间还需要加上 nanos 毫秒。
    

*   wait() throws InterruptedException 方法
    
    需要注意的是 wait(0, 0) 和 wait(0) 效果是一样的，即一直等待。
    

*   跟之前的 2 个 wait 方法一样，只不过该方法一直等待，没有超时时间这个概念。
    

*   finalize 方法
    

*   该方法的作用是实例被垃圾回收器回收的时候触发的操作，就好比 “死前的最后一波挣扎”。
    

**补充：什么是 Native Method**

简单地讲，一个 Native Method 就是一个 java 调用非 java 代码的接口。一个 Native Method 是这样一个 java 的方法：该方法的实现由非 java 语言实现，比如 C。这个特征并非 java 所特有，很多其它的编程语言都有这一机制，比如在 C＋＋中，你可以用 extern "C" 告知 C＋＋编译器去调用一个 C 的函数。

### equals()

**1. 等价关系**

Ⅰ 自反性

```
1x.equals(x); // true


```

Ⅱ 对称性

```
1x.equals(y) == y.equals(x); // true


```

Ⅲ 传递性

```
1if (x.equals(y) && y.equals(z))
2    x.equals(z); // true;


```

Ⅳ 一致性

多次调用 equals() 方法结果不变

```
1x.equals(y) == x.equals(y); // true


```

Ⅴ 与 null 的比较

对任何不是 null 的对象 x 调用 x.equals(null) 结果都为 false

```
1x.equals(null); // false;


```

**2. 等价与相等**

*   对于基本类型，== 判断两个值是否相等，基本类型没有 equals() 方法。
    
*   对于引用类型，== 判断两个变量是否引用同一个对象，而 equals() 判断引用的对象是否等价。
    

```
1Integer x = new Integer(1);
2Integer y = new Integer(1);
3System.out.println(x.equals(y)); // true
4System.out.println(x == y);      // false


```

**3. 实现**

*   检查是否为同一个对象的引用，如果是直接返回 true；
    
*   检查是否是同一个类型，如果不是，直接返回 false；
    
*   将 Object 对象进行转型；
    
*   判断每个关键域是否相等。
    

```
 1public class EqualExample {
 2
 3    private int x;
 4    private int y;
 5    private int z;
 6
 7    public EqualExample(int x, int y, int z) {
 8        this.x = x;
 9        this.y = y;
10        this.z = z;
11    }
12
13    @Override
14    public boolean equals(Object o) {
15        if (this == o) return true;
16        if (o == null || getClass() != o.getClass()) return false;
17
18        EqualExample that = (EqualExample) o;
19
20        if (x != that.x) return false;
21        if (y != that.y) return false;
22        return z == that.z;
23    }
24}


```

**4. 两个对象值相同 (x.equals(y) == true)，但却可有不同的 hash code，这句话对不对?**

**因为 hashCode() 方法和 equals() 方法都可以通过自定义类重写**，是可以做到 equals 相同，但是 hashCode 不同的

但是，在 Object 类的 equals() 方法中有这么一段话

翻译如下：

通常来讲，在重写这个方法的时候，也需要对 hashCode 方法进行重写，  
以此来保证这两个方法的一致性——  
当 equals 返回 true 的时候，这两个对象一定有相同的 hashcode.

### hashCode()

hashCode() 返回散列值，而 equals() 是用来判断两个对象是否等价。等价的两个对象散列值一定相同，但是散列值相同的两个对象不一定等价。

**在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象散列值也相等。**

理想的散列函数应当具有均匀性，即不相等的对象应当均匀分布到所有可能的散列值上。这就要求了散列函数要把所有域的值都考虑进来。可以将每个域都当成 R 进制的某一位，然后组成一个 R 进制的整数。R 一般取 31，因为它是一个奇素数，如果是偶数的话，当出现乘法溢出，信息就会丢失，因为与 2 相乘相当于向左移一位。

一个数与 31 相乘可以转换成移位和减法：`31*x == (x<<5)-x`，编译器会自动进行这个优化。

```
1@Override
2public int hashCode() {
3    int result = 17;
4    result = 31 * result + x;
5    result = 31 * result + y;
6    result = 31 * result + z;
7    return result;
8}


```

### toString()

默认返回 ToStringExample@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示。

```
1public class ToStringExample {
2
3    private int number;
4
5    public ToStringExample(int number) {
6        this.number = number;
7    }
8}


```

```
1ToStringExample example = new ToStringExample(123);
2System.out.println(example.toString());


```

```
1ToStringExample@4554617c


```

### clone()

**1. cloneable**

clone() 是 Object 的 protected 方法，它不是 public，一个类不显式去重写 clone()，其它类就不能直接去调用该类实例的 clone() 方法。

```
1public class CloneExample {
2    private int a;
3    private int b;
4}


```

```
1CloneExample e1 = new CloneExample();
2// CloneExample e2 = e1.clone(); // 'clone()' has protected access in 'java.lang.Object'


```

重写 clone() 得到以下实现：

```
1public class CloneExample {
2    private int a;
3    private int b;
4
5    @Override
6    public CloneExample clone() throws CloneNotSupportedException {
7        return (CloneExample)super.clone();
8    }
9}


```

```
1CloneExample e1 = new CloneExample();
2try {
3    CloneExample e2 = e1.clone();
4} catch (CloneNotSupportedException e) {
5    e.printStackTrace();
6}


```

```
1java.lang.CloneNotSupportedException: CloneExample


```

以上抛出了 CloneNotSupportedException，这是因为 CloneExample 没有实现 Cloneable 接口。

应该注意的是，clone() 方法并不是 Cloneable 接口的方法，而是 Object 的一个 protected 方法。Cloneable 接口只是规定，如果一个类没有实现 Cloneable 接口又调用了 clone() 方法，就会抛出 CloneNotSupportedException。

```
1public class CloneExample implements Cloneable {
2    private int a;
3    private int b;
4
5    @Override
6    public Object clone() throws CloneNotSupportedException {
7        return super.clone();
8    }
9}


```

**2. 浅拷贝**

拷贝对象和原始对象的引用类型引用同一个对象。

```
 1public class ShallowCloneExample implements Cloneable {
 2
 3    private int[] arr;
 4
 5    public ShallowCloneExample() {
 6        arr = new int[10];
 7        for (int i = 0; i < arr.length; i++) {
 8            arr[i] = i;
 9        }
10    }
11
12    public void set(int index, int value) {
13        arr[index] = value;
14    }
15
16    public int get(int index) {
17        return arr[index];
18    }
19
20    @Override
21    protected ShallowCloneExample clone() throws CloneNotSupportedException {
22        return (ShallowCloneExample) super.clone();
23    }
24}


```

```
1ShallowCloneExample e1 = new ShallowCloneExample();
2ShallowCloneExample e2 = null;
3try {
4    e2 = e1.clone();
5} catch (CloneNotSupportedException e) {
6    e.printStackTrace();
7}
8e1.set(2, 222);
9System.out.println(e2.get(2)); // 222


```

**3. 深拷贝**

拷贝对象和原始对象的引用类型引用不同对象。

```
 1public class DeepCloneExample implements Cloneable {
 2
 3    private int[] arr;
 4
 5    public DeepCloneExample() {
 6        arr = new int[10];
 7        for (int i = 0; i < arr.length; i++) {
 8            arr[i] = i;
 9        }
10    }
11
12    public void set(int index, int value) {
13        arr[index] = value;
14    }
15
16    public int get(int index) {
17        return arr[index];
18    }
19
20    @Override
21    protected DeepCloneExample clone() throws CloneNotSupportedException {
22        DeepCloneExample result = (DeepCloneExample) super.clone();
23        result.arr = new int[arr.length];
24        for (int i = 0; i < arr.length; i++) {
25            result.arr[i] = arr[i];
26        }
27        return result;
28    }
29}


```

```
1DeepCloneExample e1 = new DeepCloneExample();
2DeepCloneExample e2 = null;
3try {
4    e2 = e1.clone();
5} catch (CloneNotSupportedException e) {
6    e.printStackTrace();
7}
8e1.set(2, 222);
9System.out.println(e2.get(2)); // 2


```

**4. clone() 的替代方案**

使用 clone() 方法来拷贝一个对象即复杂又有风险，它会抛出异常，并且还需要类型转换。Effective Java 书上讲到，最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象。

```
 1public class CloneConstructorExample {
 2
 3    private int[] arr;
 4
 5    public CloneConstructorExample() {
 6        arr = new int[10];
 7        for (int i = 0; i < arr.length; i++) {
 8            arr[i] = i;
 9        }
10    }
11
12    public CloneConstructorExample(CloneConstructorExample original) {
13        arr = new int[original.arr.length];
14        for (int i = 0; i < original.arr.length; i++) {
15            arr[i] = original.arr[i];
16        }
17    }
18
19    public void set(int index, int value) {
20        arr[index] = value;
21    }
22
23    public int get(int index) {
24        return arr[index];
25    }
26}


```

```
1CloneConstructorExample e1 = new CloneConstructorExample();
2CloneConstructorExample e2 = new CloneConstructorExample(e1);
3e1.set(2, 222);
4System.out.println(e2.get(2)); // 2


```

关键字
---

### final

*   修饰类：表示该类不能被继承
    
*   修饰方法：表示该方法不能被重写
    
*   修饰变量：表示该变量只能被赋值一次
    
    声明数据为常量，可以是编译时常量，也可以是在运行时被初始化后不能被改变的常量。
    

*   对于基本类型，final 使数值不变；
    
*   对于引用类型，final 使引用不变，表示该引用只有一次指向对象的机会，也就不能引用其它对象，但是被引用的对象本身是可以修改的。
    

#### finally

finally 是用于异常处理的场面，无论是否有异常抛出，都会执行

#### finalize

finalize 是 Object 的方法，所有类都继承了该方法。 当一个对象满足垃圾回收的条件，并且被回收的时候，其 finalize() 方法就会被调用

### static

**1. 静态变量**

*   静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它；静态变量在内存中只存在一份。
    
*   实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。
    

```
 1public class A {
 2    private int x;         // 实例变量
 3    private static int y;  // 静态变量
 4
 5    public static void main(String[] args) {
 6        // int x = A.x;  // Non-static field 'x' cannot be referenced from a static context
 7        A a = new A();
 8        int x = a.x;
 9        int y = A.y;
10    }
11}


```

**2. 静态方法**

静态方法在类加载的时候就存在了，它不依赖于任何实例。**所以静态方法必须有实现，也就是说它不能是抽象方法（abstract）。**

```
1public abstract class A {
2    public static void func1(){
3    }
4    // public abstract static void func2();  // Illegal combination of modifiers: 'abstract' and 'static'
5}


```

只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字。

```
 1public class A {
 2    private static int x;
 3    private int y;
 4
 5    public static void func1(){
 6        int a = x;
 7        // int b = y;  // Non-static field 'y' cannot be referenced from a static context
 8        // int b = this.y;     // 'A.this' cannot be referenced from a static context
 9    }
10}


```

**3. 静态语句块**

**静态语句块在类初始化时运行一次。**

```
 1public class A {
 2    static {
 3        System.out.println("123");
 4    }
 5
 6    public static void main(String[] args) {
 7        A a1 = new A();
 8        A a2 = new A();
 9    }
10}


```

```
1123


```

**4. 静态内部类**

**非静态内部类依赖于外部类的实例，而静态内部类不需要。**

当一个内部类没有使用 static 修饰的时候，是不能直接使用内部类创建对象，须要先使用外部类对象. new 内部类对象及 (外部类对象. new 内部类（）)

而静态内部类只需要`new OuterClass.InnerClass();`

```
 1public class OuterClass {
 2    class InnerClass {
 3    }
 4
 5    static class StaticInnerClass {
 6    }
 7
 8    public static void main(String[] args) {
 9        // InnerClass innerClass = new InnerClass(); // 'OuterClass.this' cannot be referenced from a static context
10        OuterClass outerClass = new OuterClass();
11        InnerClass innerClass = outerClass.new InnerClass();
12        StaticInnerClass staticInnerClass = new StaticInnerClass();
13    }
14}


```

静态内部类不能访问外部类的非静态的变量和方法。

**5. 静态导包**

在使用静态变量和方法时不用再指明 ClassName，从而简化代码，但可读性大大降低。

```
1import static com.xxx.ClassName.*


```

**6. 初始化顺序**

存在继承的情况下，初始化顺序为：

*   父类（静态变量、静态语句块）
    
*   子类（静态变量、静态语句块）
    
*   父类（实例变量、普通语句块）
    
*   父类（构造函数）
    
*   子类（实例变量、普通语句块）
    
*   子类（构造函数）
    

反射
--

每个类都有一个  **Class**  对象，包含了与类有关的信息。当编译一个新类时，会产生一个同名的 .class 文件，该文件内容保存着 Class 对象。

类加载相当于 Class 对象的加载，类在第一次使用时才动态加载到 JVM 中。也可以使用 `Class.forName("com.mysql.jdbc.Driver")` 这种方式来控制类的加载，该方法会返回一个 Class 对象。

反射可以提供运行时的类信息，并且这个类可以在运行时才加载进来，甚至在编译时期该类的 .class 不存在也可以加载进来。

Class 和 java.lang.reflect 一起对反射提供了支持，java.lang.reflect 类库主要包含了以下三个类：

*   **Field** ：可以使用 get() 和 set() 方法读取和修改 Field 对象关联的字段；
    
*   **Method** ：可以使用 invoke() 方法调用与 Method 对象关联的方法；
    
*   **Constructor** ：可以用 Constructor 创建新的对象。
    

**反射的优点：**

*   **可扩展性**  ：应用程序可以利用全限定名创建可扩展对象的实例，来使用来自外部的用户自定义类。
    
*   **类浏览器和可视化开发环境**  ：一个类浏览器需要可以枚举类的成员。可视化开发环境（如 IDE）可以从利用反射中可用的类型信息中受益，以帮助程序员编写正确的代码。
    
*   **调试器和测试工具**  ： 调试器需要能够检查一个类里的私有成员。测试工具可以利用反射来自动地调用类里定义的可被发现的 API 定义，以确保一组测试中有较高的代码覆盖率。
    

**反射的缺点：**

尽管反射非常强大，但也不能滥用。如果一个功能可以不用反射完成，那么最好就不用。在我们使用反射技术时，下面几条内容应该牢记于心。

*   **性能开销**  ：反射涉及了动态类型的解析，所以 JVM 无法对这些代码进行优化。因此，反射操作的效率要比那些非反射操作低得多。我们应该避免在经常被执行的代码或对性能要求很高的程序中使用反射。
    
*   **安全限制**  ：使用反射技术要求程序必须在一个没有安全限制的环境中运行。如果一个程序必须在有安全限制的环境中运行，如 Applet，那么这就是个问题了。
    
*   **内部暴露**  ：由于反射允许代码执行一些在正常情况下不被允许的操作（比如访问私有的属性和方法），所以使用反射可能会导致意料之外的副作用，这可能导致代码功能失调并破坏可移植性。反射代码破坏了抽象性，因此当平台发生改变的时候，代码的行为就有可能也随着变化。
    
*   Trail: The Reflection API
    
*   深入解析 Java 反射（1）- 基础
    

异常
--

运行时异常：

```
1NullPointerException 空指针异常
2ArithmeticException 算术异常，比如除数为零
3ClassCastException 类型转换异常
4ConcurrentModificationException同步修改异常，遍历一个集合的时候，删除集合的元素，就会抛出该异常 
5IndexOutOfBoundsException 数组下标越界异常
6NegativeArraySizeException 为数组分配的空间是负数异常


```

**一般异常又叫做可查异常（受检异常），在编译过程中，必须进行处理，要么捕捉，要么通过 throws 抛出去.**

```
1比如FileNotFoundException


```

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl30vOKqIdadR4Z13d5ibBQUhAD4yNT1MhxvZBxOqN4DHHdKCbxkR20jULA/640?wx_fmt=png)在这里插入图片描述

### Error 和 Exception 有什么区别?

Error 和 Exception 都实现了 Throwable 接口

Error 指的是 JVM 层面的错误，比如内存不足 OutOfMemoryError

Exception 指的是代码逻辑的异常，比如下标越界 OutOfIndexException

泛型
--

```
1public class Box<T> {
2    // T stands for "Type"
3    private T t;
4    public void set(T t) { this.t = t; }
5    public T get() { return t; }
6}


```

*   Java 泛型详解
    
*   10 道 Java 泛型面试题
    

零散知识点
-----

### & 和 && 的区别

& 有两个作用，分别是 位与 和 逻辑与

&& 就是逻辑与

**长路与 &**：两侧，都会被运算

**短路与 &&**：只要第一个是 false，第二个就不进行运算了

### heap 和 stack 有什么区别

heap: 堆

stack: 栈

*   存放的内容不一样：
    

*   heap: 是存放对象的
    
*   stack: 是存放基本类型 (int, float, boolean 等等)、引用 (对象地址)、方法调用
    

*   存取方式不一样：
    

*   heap: 是自动增加大小的，所以不需要指定大小，但是存取相对较慢
    
*   stack: 先入后出的顺序，并且存取速度比较快
    

### 获取长度

数组获取长度的手段是 .length 属性

String 获取长度的手段是 length() 方法

集合获取长度的手段是 size() 方法

文件获取长度的手段是 length() 方法

### Set 里的元素是不能重复的，那么用什么方法来区分重复与否呢

以 HashSet 为例，判断重复的逻辑是：

1.  首先看 hashcode 是否相同，如果不同，就是不重复的
    
2.  如果 hashcode 一样，再比较 equals，如果不同，就是不重复的，否则就是重复的。
    

http://how2j.cn/k/collection/collection-hashcode/371.html#step2530

### 构造函数器 / Constructor 是否可被 override? 是否可以继承 String 类?

子类不能继承父类的构造方法，所以就不存在重写父类的构造方法。

String 是 final 修饰的，所以不能够被继承

### try catch finally 执行顺序

try 里的 return 和 finally 里的 return 都会支持，但是当前方法**只会采纳 finally 中 return 的值**

https://www.cnblogs.com/superFish2016/p/6687549.html

**总结以上测试：**

1、finally 语句总会执行

2、如果 try、catch 中有 return 语句，finally 中没有 return，那么**在 finally 中去修改除了包装类型和静态变量、全局变量以外的数据**都不会对 try、catch 中返回的变量有任何的影响（包装类型、静态变量会改变、全局变量）。**但是修改包装类型和静态变量、全局变量，会改变变量的值。**

3、尽量不要在 finally 中使用 return 语句，如果使用的话，会忽略 try、catch 中的返回语句，也会忽略 try、catch 中的异常，屏蔽了错误的发生。

4、finally 中避免再次抛出异常，一旦 finally 中发生异常，代码执行将会抛出 finally 中的异常信息，try、catch 中的异常将被忽略。

**所以在实际项目中，finally 常常是用来关闭流或者数据库资源的，并不额外做其他操作。**

### char 型变量中能不能存贮一个中文汉字? 为什么?

char 是 16 位的，占两个字节

汉字通常使用 GBK 或者 UNICODE 编码，也是使用两个字节

所以可以存放汉字

### 一个 ".java" 源文件中是否可以包括多个类（不是内部类）？有什么限制？

可以包括多个类，但是只能出现一个 public 修饰的类，但是可以出现多个非 public 修饰的类。

### java 中有几种类型的流？

Java 中所有的流都是基于字节流，所以最基本的流是

```
 1输入输出字节流
 2
 3InputStream
 4
 5OutputStream
 6
 7在字节流的基础上，封装了字符流
 8
 9Reader
10
11Writer
12
13进一步，又封装了缓存流
14
15BufferedReader
16
17PrintWriter
18
19以及数据流
20
21DataInputStream
22
23DataOutputStream
24
25对象流
26
27ObjectInputStream
28
29ObjectOutputStream
30
31以及一些其他的奇奇怪怪的流 ~~~


```

### 什么是 java 序列化，如何实现 java 序列化？

序列化指的是把一个 Java 对象，通过某种介质进行传输，比如 Socket 输入输出流，或者保存在一个文件里。

实现 java 序列化的手段是让该类实现接口 Serializable，这个接口是一个标识性接口，没有任何方法，仅仅用于表示该类可以序列化。

**JAVA 序列化 ID 问题**

https://blog.csdn.net/qq_35370263/article/details/79482993

虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化 ID 是否一致（就是 private static final long serialVersionUID = 1L）。清单 1 中，虽然两个类的功能代码完全一致，但是序列化 ID 不同，他们无法相互序列化和反序列化。

### 在 JAVA 中，如何跳出当前的多重嵌套循环？

```
 1public class HelloWorld {
 2    public static void main(String[] args) {
 3
 4        //打印单数    
 5        outloop: //outloop这个标示是可以自定义的比如outloop1,ol2,out5
 6        for (int i = 0; i < 10; i++) {
 7
 8            for (int j = 0; j < 10; j++) {
 9                System.out.println(i+":"+j);
10                if(0==j%2) 
11                    break outloop; //如果是双数，结束外部循环
12            }
13
14        }
15
16    }
17}


```

### Anonymous Inner Class (匿名内部类) 是否可以 extends(继承) 其它类，是否可以 implements(实现)interface(接口)?

匿名内部类本质上就是在继承其他类，实现其他接口

如例:  
匿名类 1，就是继承了 Thread  
匿名类 2 ，就是实现了 Runnable 接口

```
 1package j2se;
 2
 3public class HelloWorld {
 4
 5    public static void main(String[] args) {
 6
 7        // 匿名类1
 8        new Thread() {
 9            public void run() {
10
11            }
12        };
13
14        // 匿名类2
15        new Runnable() {
16            public void run() {
17
18            }
19        };
20
21    }
22}


```

### 内部类可以引用外部类的成员吗？有没有什么限制？

可以使用

如果是非静态内部类，可是使用外部类的所有成员

如果是静态内部类，只能使用外部类的静态成员

### Class.forName 的作用? 为什么要用?

Class.forName 常见的场景是在数据库驱动初始化的时候调用。

**Class.forName 本身的意义是加载类到 JVM 中。 一旦一个类被加载到 JVM 中，它的静态属性就会被初始化，在初始化的过程中就会执行相关代码，从而达到 "加载驱动的效果"**

### JDK 中常用的包有哪些？

答：java.lang、java.util、java.io、java.net、java.sql。

集合
==

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl303Kh9WxERrLdqOnEicRtGuYEjFgKYD8vRXGwqkh6jNF0d2SPicibsotLrQ/640?wx_fmt=png)在这里插入图片描述

容器主要包括 Collection 和 Map 两种，Collection 又包含了 List、Set 以及 Queue。

Collection
----------

**Set**

*   HashSet：基于哈希实现，支持快速查找，但不支持有序性操作，例如根据一个范围查找元素的操作。并且失去了元素的插入顺序信息，也就是说使用 Iterator 遍历 HashSet 得到的结果是不确定的；
    
*   TreeSet：基于红黑树实现，支持有序性操作，但是查找效率不如 HashSet，HashSet 查找时间复杂度为 O(1)，TreeSet 则为 O(logN)；
    
*   LinkedHashSet：具有 HashSet 的查找效率，且内部使用链表维护元素的插入顺序。
    

**List**

*   ArrayList：基于动态数组实现，支持随机访问；
    
*   Vector：和 ArrayList 类似，但它是线程安全的；
    
*   LinkedList：基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList 还可以用作栈、队列和双向队列。
    

**Queue**

*   LinkedList：可以用它来支持双向队列；
    
*   PriorityQueue：基于堆结构实现，可以用它来实现优先队列。
    

> 　优先队列是一种用来维护一组元素构成的结合 S 的数据结构，其中每个元素都有一个关键字 key，元素之间的比较都是通过 key 来比较的。优先队列包括最大优先队列和最小优先队列，优先队列的应用比较广泛，比如作业系统中的调度程序，当一个作业完成后，需要在所有等待调度的作业中选择一个优先级最高的作业来执行，并且也可以添加一个新的作业到作业的优先队列中。Java 中，PriorityQueue 的底层数据结构就是堆（默认是小堆）。

Map
---

*   HashMap：基于哈希实现；
    
*   HashTable：和 HashMap 类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入 HashTable 并且不会导致数据不一致。它是遗留类，不应该去使用它。现在可以使用 ConcurrentHashMap 来支持线程安全，并且 ConcurrentHashMap 的效率会更高，因为 ConcurrentHashMap 引入了分段锁。
    
*   LinkedHashMap：使用链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。
    
*   TreeMap：基于红黑树实现。
    

Collection 和 Collections 的区别
----------------------------

Collection 是接口，是 List 和 Set 的父接口

Collections 是工具类，提供了排序，混淆等等很多实用方法

List、Set 和 Map 的初始容量和加载因子
-------------------------

答：  
加载因子的系数小于等于 1，意指  即当 元素个数 超过 容量长度 * 加载因子的系数 时，进行扩容。

1.  List
    

ArrayList 的初始容量是 10；加载因子为 0.5； 扩容增量：**原容量的 0.5 倍 +1**；一次扩容后长度为 16。

Vector 初始容量为 10，加载因子是 1。扩容增量：**原容量的 1 倍**，如 Vector 的容量为 10，一次扩容后是容量为 20。

2.  Set
    

HashSet，初始容量为 16，加载因子为 0.75； 扩容增量：**原容量的 1 倍**； 如 HashSet 的容量为 16，一次扩容后容量为 32

3.  Map
    

HashMap，初始容量 16，加载因子为 0.75； 扩容增量：**原容量的 1 倍**； 如 HashMap 的容量为 16，一次扩容后容量为 32

Comparable 接口和 Comparator 接口有什么区别？
----------------------------------

答：  
详细可以看：https://blog.csdn.net/u011240877/article/details/53399019

*   对于一些普通的数据类型（比如 String, Integer, Double…），**它们默认实现了 Comparable 接口，实现了 compareTo 方法，我们可以直接使用。**
    
*   而对于一些自定义类，它们可能在不同情况下需要实现不同的比较策略，我们可以新创建 Comparator 接口，然后使用特定的 Comparator 实现进行比较。
    

**对比：**

*   Comparable 简单，但是如果需要重新定义比较类型时，需要修改源代码。
    
*   Comparator 不需要修改源代码，自定义一个比较器，实现自定义的比较方法。
    

Java 集合的快速失败机制 “fail-fast”
--------------------------

答：

它是 java 集合的一种错误检测机制，当多个线程对集合进行结构上的改变的操作时，有可能会产生 fail-fast 机制。

可以知道**在进行 add，remove，clear 等涉及到修改集合中的元素个数的操作时**，modCount 就会发生改变 (modCount ++), 所以当另一个线程(并发修改) 或者同一个线程遍历过程中，调用相关方法使集合的个数发生改变，就会使 modCount 发生变化

每当迭代器使用 hashNext()/next() 遍历下一个元素之前，都会检测 modCount 变量是否为 expectedmodCount 值，是的话就返回遍历；否则抛出异常，终止遍历。

解决办法：

1.  在遍历过程中，所有涉及到改变 modCount 值得地方全部加上 synchronized；
    
2.  使用 CopyOnWriteArrayList 来替换 ArrayList。
    

**java.util.concurrent 包中包含的并发集合类如下：**

详细：http://raychase.iteye.com/blog/1998965

```
1ConcurrentHashMap
2
3CopyOnWriteArrayList
4
5CopyOnWriteArraySet

```

* * *

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
    
    拥有专栏：
    
    Leetcode 题解（Java/Python）、Python 爬虫开发
    
*   知乎：
    
    https://www.zhihu.com/people/yang-zhen-dong-1/
    
    拥有专栏：
    
    码农面试助攻手册
    
*   掘金：
    
    https://juejin.im/user/5b48015ce51d45191462ba55
    
*   简书：
    
    https://www.jianshu.com/u/b5f225ca2376
    
*   个人公众号：Rude3Knife
    
    ![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl30Us0VWia6glRcD20yyZPSRHL9ibpUHDbibyPDtd7DKoMOVU2W0HichHqQ0Q/640?wx_fmt=png)在这里插入图片描述