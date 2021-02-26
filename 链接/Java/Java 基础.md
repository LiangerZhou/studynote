# JAVA 基础

## 前言

本文包括内容：基础知识点，集合类  

## 1. 基础知识点

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

* 如果比较 Integer 变量，**默认比较的是地址值。**

* 特例：如果比较的**某一边有操作表达式 (例如 a+b)**，那么比较的是**具体数值**

**使用 equals() 的情况：**

* 无论是 Integer 还是 Long 中的 equals() **默认比较的是数值**。

* **特例：Long 的 equals() 方法，JDK 的默认实现：会判断是否是 Long 类型**

**new Integer(123) 与 Integer.valueOf(123) 的区别在于：**

* new Integer(123) **每次都会新建一个对象**

* Integer.valueOf(123) **会使用缓存池中的对象**，多次调用会取得同一个对象的引用。

**valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容。**

```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

**基本类型对应的缓冲池如下：**

* boolean values true and false

* all byte values

* short values between -128 and 127

* int values between -128 and 127

* char in the range \u0000 to \u007F

## 2. String

### 1. 概览

**String 被声明为 final，因此它不可被继承。**

在 Java 8 中，String 内部使用 char 数组存储数据。

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];
}
```

在 Java 9 之后，String 类的实现改用 byte 数组存储字符串，同时使用 `coder` 来标识使用了哪种编码。

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final byte[] value;

    /** The identifier of the encoding used to encode the bytes in {@code value}. */
    private final byte coder;
}
```

### 2. 不可变的好处

* **1. 可以缓存 hash 值**

因为 String 的 hash 值经常被使用，例如 String 用做 HashMap 的 key。不可变的特性可以使得 hash 值也不可变，因此只需要进行一次计算。

* **2. String Pool 的需要**

如果一个 String 对象已经被创建过了，那么就会从 String Pool 中取得引用。只有 String 是不可变的，才可能使用 String Pool。
**注意：不是 string 创建后就默认进入池的，请看下方 intern()**

![string](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl30TgA8bWISiaczzxXyG0iaEyib4WibicI2UmdJbItNQuf9Cicgpe8qhMxxkJ4w/640?wx_fmt=png)

* **3. 安全性**

String 经常作为参数，String 不可变性可以保证参数不可变。例如在作为网络连接参数的情况下如果 String 是可变的，那么在网络连接过程中，String 被改变，改变 String 对象的那一方以为现在连接的是其它主机，而实际情况却不一定是。

* **4. 线程安全**

String 不可变性天生具备线程安全，可以在多个线程中安全地使用。

[Program Creek : Why String is immutable in Java?](https://www.programcreek.com/2013/04/why-string-is-immutable-in-java/)

### 3. String, StringBuffer and StringBuilder

* **1. 可变性**
  * String 不可变

  * StringBuffer 和 StringBuilder 可变

* **2. 线程安全**

  * String 不可变，因此是线程安全的

  * StringBuilder 不是线程安全的

  * StringBuffer 是线程安全的，内部使用 synchronized 进行同步

**对于三者使用的总结：**

1. 如果要操作少量的数据用 String

2. **单线程**操作字符串缓冲区下操作大量数据 StringBuilder

3. **多线程**操作字符串缓冲区下操作大量数据 StringBuffer

### 4. String 和 StringBuffer 的区别

String 是 immutable 的, 其内容一旦创建好之后，就不可以发生改变。

StringBuffer 是可以变长的，内容也可以发生改变  
改变的原理是 StringBuffer 内部采用了字符数组存放数据，在需要增加长度的时候，创建新的数组，并且把原来的数据复制到新的数组这样的办法来实现。
[https://blog.csdn.net/yeweiyang16/article/details/51755552](https://blog.csdn.net/yeweiyang16/article/details/51755552)
**初始化：可以指定给对象的实体的初始容量为参数字符串 s 的长度额外再加 16 个字符**

**扩容：尝试将新容量扩为大小变成原容量的 2 倍 + 2，然后 if 判断一下 容量如果不够，直接扩充到需要的容量大小。**

[StackOverflow : String, StringBuffer, and StringBuilder](https://stackoverflow.com/questions/2971315/string-stringbuffer-and-stringbuilder)

### 5. String Pool

字符串常量池（String Pool）保存着所有字符串字面量（literal strings），这些字面量在编译时期就确定。不仅如此，还可以使用 String 的 intern() 方法在运行过程中将字符串添加到 String Pool 中。

当一个字符串调用 intern() 方法时，如果 String Pool 中已经存在一个字符串和该字符串值相等（使用 equals() 方法进行确定），那么就会返回 String Pool 中字符串的引用；否则，就会在 String Pool 中添加一个新的字符串，并返回这个新字符串的引用。

下面示例中，s1 和 s2 采用 new String() 的方式新建了两个不同字符串，而 s3 和 s4 是通过 s1.intern() 方法取得一个字符串引用。intern() 首先把 s1 引用的字符串放到 String Pool 中，然后返回这个字符串引用。因此 s3 和 s4 引用的是同一个字符串。

```java
String s1 = new String("aaa");
String s2 = new String("aaa");
System.out.println(s1 == s2);           // false
String s3 = s1.intern();
String s4 = s1.intern();
System.out.println(s3 == s4);           // true
```

如果是采用 "bbb" 这种字面量的形式创建字符串，会自动地将字符串放入 String Pool 中。

```java
String s5 = "bbb";
String s6 = "bbb";
System.out.println(s5 == s6);  // true
```

在 Java 7 之前，String Pool 被放在运行时常量池中，它属于永久代。而在 Java 7，String Pool 被移到堆中。这是因为永久代的空间有限，在大量使用字符串的场景下会导致 OutOfMemoryError 错误。

* [StackOverflow : What is String interning?](https://stackoverflow.com/questions/10578984/what-is-java-string-interning)

* [深入解析 String#intern](https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html)

### 6. new String("abc")

使用这种方式一共会创建两个字符串对象（前提是 String Pool 中还没有 "abc" 字符串对象）。

* "abc" 属于字符串字面量，因此编译时期会在 String Pool 中创建一个字符串对象，指向这个 "abc" 字符串字面量；

* 而使用 new 的方式会在堆中创建一个字符串对象。

创建一个测试类，其 main 方法中使用这种方式来创建字符串对象。

```java
public class NewStringTest {
    public static void main(String[] args) {
        String s = new String("abc");
    }
}
```

使用 javap -verbose 进行反编译，得到以下内容：

```java
// ...
Constant pool:
// ...
   #2 = Class              #18            // javalang/String
   #3 = String             #19            // abc
// ...
  #18 = Utf8               java/lang/String
  #19 = Utf8               abc
// ...

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=2, args_size=1
         0: new           #2                  // class java/lang/String
         3: dup
         4: ldc           #3                  // String abc
         6: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
         9: astore_1
// ...

```

在 Constant Pool 中，#19 存储这字符串字面量 "abc"，#3 是 String Pool 的字符串对象，它指向 #19 这个字符串字面量。在 main 方法中，0: 行使用 new #2 在堆中创建一个字符串对象，并且使用 ldc #3 将 String Pool 中的字符串对象作为 String 构造函数的参数。

以下是 String 构造函数的源码，可以看到，在将一个字符串对象作为另一个字符串对象的构造函数参数时，并不会完全复制 value 数组内容，而是都会指向同一个 value 数组。

```java
public String(String original) {
    this.value = original.value;
    this.hash = original.hash;
}
```

## 3. 运算

### 1. 参数传递

**Java 的参数是以值传递的形式传入方法中，而不是引用传递。**

以下代码中 Dog dog 的 dog 是一个指针，存储的是对象的地址。在将一个参数传入一个方法时，本质上是将对象的地址以值的方式传递到形参中。因此在方法中使指针引用其它对象，那么这两个指针此时指向的是完全不同的对象，在一方改变其所指向对象的内容时对另一方没有影响。

```java
public class Dog {

    String name;

    Dog(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getObjectAddress() {
        return super.toString();
    }
}
```

```java
public class PassByValueExample {
    public static void main(String[] args) {
        Dog dog = new Dog("A");
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        func(dog);
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        System.out.println(dog.getName());          // A
    }

    private static void func(Dog dog) {
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        dog = new Dog("B");
        System.out.println(dog.getObjectAddress()); // Dog@74a14482
        System.out.println(dog.getName());          // B
    }
}
```

如果在方法中改变对象的字段值会改变原对象该字段值，因为改变的是同一个地址指向的内容。

```java
class PassByValueExample {
    public static void main(String[] args) {
        Dog dog = new Dog("A");
        func(dog);
        System.out.println(dog.getName());          // B
    }

    private static void func(Dog dog) {
        dog.setName("B");
    }
}
```

[StackOverflow: Is Java “pass-by-reference” or “pass-by-value”?](https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value)

### 2. float 与 double

Java 不能隐式执行向下转型，因为这会使得精度降低。

1.1 字面量属于 double 类型，不能直接将 1.1 直接赋值给 float 变量，因为这是向下转型。Java 不能隐式执行向下转型，因为这会使得精度降低。

```java
// float f = 1.1;
```

1.1f 字面量才是 float 类型。

```java
float f = 1.1f;
```

### 3.隐式类型转换

因为字面量 1 是 int 类型，它比 short 类型精度要高，因此不能隐式地将 int 类型下转型为 short 类型。

```java
short s1 = 1;
// s1 = s1 + 1;
```

但是使用 += 运算符可以执行隐式类型转换。

```java
s1 += 1;
```

上面的语句相当于将 s1 + 1 的计算结果进行了向下转型：

```java
s1 = (short) (s1 + 1);
```

[StackOverflow : Why don't Java's +=, -=, *=, /= compound assignment operators require casting?](https://stackoverflow.com/questions/8710619/why-dont-javas-compound-assignment-operators-require-casting)

### 4. switch

从 Java 7 开始，可以在 switch 条件判断语句中使用 String 对象。

```java
String s = "a";
switch (s) {
    case "a":
        System.out.println("aaa");
        break;
    case "b":
        System.out.println("bbb");
        break;
}
```

switch 不支持 long，是因为 switch 的设计初衷是对那些只有少数的几个值进行等值判断，如果值过于复杂，那么还是用 if 比较合适。

## 4. 继承

### 1. 访问权限

Java 中有三个访问权限修饰符：private、protected 以及 public，**如果不加访问修饰符，表示包级可见。**

可以对类或类中的成员（字段以及方法）加上访问修饰符。

* 类可见表示其它类可以用这个类创建实例对象。

* 成员可见表示其它类可以用这个类的实例对象访问到该成员；

**protected 用于修饰成员，表示在继承体系中成员对于子类可见**，但是这个访问修饰符对于类没有意义。

如果**子类的方法重写了父类的方法，那么子类中该方法的访问级别不允许低于父类的访问级别**：这是为了确保可以使用父类实例的地方都可以使用子类实例，也就是确保满足里氏替换原则。

**字段决不能是公有的，因为这么做的话就失去了对这个字段修改行为的控制，客户端可以对其随意修改。**

例如下面的例子中，AccessExample 拥有 id 共有字段，如果在某个时刻，我们想要使用 int 去存储 id 字段，那么就需要去修改所有的客户端代码。

```java
public class AccessExample {
    public String id;
}
```

可以使用公有的 getter 和 setter 方法来替换公有字段，这样的话就可以控制对字段的修改行为。

```java
public class AccessExample {

    private int id;

    public String getId() {
        return id + "";
    }

    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }
}
```

但是也有例外，如果是包级私有的类或者私有的嵌套类，那么直接暴露成员不会有特别大的影响。

```java
public class AccessWithInnerClassExample {
    private class InnerClass {
        int x;
    }

    private InnerClass innerClass;

    public AccessWithInnerClassExample() {
        innerClass = new InnerClass();
    }

    public int getValue() {
        return innerClass.x;  // 直接访问
    }
}
```

### 2. 抽象类与接口

* **1. 抽象类**

抽象类和普通类最大的区别是：

**抽象类不能被实例化，需要继承抽象类才能实例化其子类**。

```java
public abstract class AbstractClassExample {

    protected int x;
    private int y;

    public abstract void func1();

    public void func2() {
        System.out.println("func2");
    }
}
```

```java
public class AbstractExtendClassExample extends AbstractClassExample {
    @Override
    public void func1() {
        System.out.println("func1");
    }
}
```

* **2. 接口**

接口是抽象类的延伸，在 Java 8 之前，它可以看成是一个完全抽象的类，也就是说它不能有任何的方法实现。

**从 Java 8 开始，接口也可以拥有默认的方法实现**，这是因为不支持默认方法的接口的维护成本太高了。

**在 Java 8 之前，如果一个接口想要添加新的方法，那么要修改所有实现了该接口的类。**

**接口的成员（字段 + 方法）默认都是 public 的，并且不允许定义为 private 或者 protected。**

**接口的字段默认都是 static 和 final 的。**

```java
public interface InterfaceExample {
    void func1();

    default void func2(){
        System.out.println("func2");
    }

    int x = 123;
    // int y;               / Variable 'y' might not have been initialized
    public int z = 0;       // Modifier 'public' is redundant for interface fields
    // private int k = 0;   // Modifier 'private' not allowed here
    // protected int l = 0; // Modifier 'protected' not allowed here
    // private void fun3(); // Modifier 'private' not allowed here
}
```

```java
public class InterfaceImplementExample implements InterfaceExample {
    @Override
    public void func1() {
        System.out.println("func1");
    }
}
```

* **3. 比较**

  * **从设计层面上看，抽象类提供了一种 IS-A 关系，那么就必须满足里式替换原则，即子类对象必须能够替换掉所有父类对象。而接口更像是一种 LIKE-A 关系，它只是提供一种方法实现契约，并不要求接口和实现接口的类具有 IS-A 关系。**

  * 从使用上来看，一个类可以实现多个接口，但是不能继承多个抽象类。

  * **接口的字段只能是 static 和 final 类型的**，而抽象类的字段没有这种限制。

  * **接口的成员只能是 public 的**，而抽象类的成员可以有多种访问权限。

* **4. 使用选择**

  * 使用接口：

    * 需要让不相关的类都实现一个方法，例如不相关的类都可以实现 Compareable 接口中的 compareTo() 方法；

    * 需要使用多重继承。

  * 使用抽象类：

    * 需要在几个相关的类中共享代码。

    * 需要能控制继承来的成员的访问权限，而不是都为 public。

    * 需要继承非静态 static 和非常量 final 字段。

**在很多情况下，接口优先于抽象类，因为接口没有抽象类严格的类层次结构要求，可以灵活地为一个类添加行为。并且从 Java 8 开始，接口也可以有默认的方法实现，使得修改接口的成本也变的很低。**

* [深入理解 abstract class 和 interface](https://www.ibm.com/developerworks/cn/java/l-javainterface-abstract/)

* [When to Use Abstract Class and Interface](https://dzone.com/articles/when-to-use-abstract-class-and-intreface)

### 3. super

* **访问父类的构造函数**：可以使用 super() 函数访问父类的构造函数，从而委托父类完成一些初始化的工作。

* **访问父类的成员**：如果子类重写了父类的中某个方法的实现，可以通过使用 super 关键字来引用父类的方法实现。

[Using the Keyword super](https://docs.oracle.com/javase/tutorial/java/IandI/super.html)

### 4. 继承相关小问题

* **接口是否可继承接口?**

    可以，比如 List 就继承了接口 Collection

* **抽象类是否可实现 (implements) 接口?**

    可以，比如 MouseAdapter 鼠标监听适配器 是一个抽象类，并且实现了 MouseListener 接口

* **抽象类是否可继承实体类（concrete class）？**

    可以，所有抽象类，都继承了 Object

## 5. Object 通用方法

经典：
[https://fangjian0423.github.io/2016/03/12/java-Object-method/](https://fangjian0423.github.io/2016/03/12/java-Object-method/)

### 1. 总览

```java
public native int hashCode()

public boolean equals(Object obj)

protected native Object clone() throws CloneNotSupportedException

public String toString()

public final native Class<?> getClass()

protected void finalize() throws Throwable {}

public final native void notify()

public final native void notifyAll()

public final native void wait(long timeout) throws InterruptedException

public final void wait(long timeout, int nanos) throws InterruptedException

public final void wait() throws InterruptedException

```

* getClass 方法
    返回当前运行时对象的 Class 对象

* hashCode 方法

    该方法返回对象的哈希码，主要使用在哈希表中，比如 JDK 中的 HashMap。（文章中有对 hashcode 的详细解释）

* equals 方法

  如果重写了 equals 方法，通常有必要重写 hashCode 方法，这点已经在 hashCode 方法中说明了。

* clone 方法

  创建并返回当前对象的一份拷贝。**Object 本身没有实现 Cloneable 接口**，所以不重写 clone 方法并且进行调用的话会发生 CloneNotSupportedException 异常。

  使用 clone() 方法来拷贝一个对象即复杂又有风险，它会抛出异常，并且还需要类型转换。Effective Java 书上讲到，最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象。

* toString 方法

  Object 对象的默认实现，即输出类的名字 @实例的哈希码的 16 进制。

* notify 方法：

  唤醒一个在此对象监视器上等待的线程 (监视器相当于就是锁的概念)。

* notifyAll 方法

  跟 notify 一样，唯一的区别就是会唤醒在此对象监视器上等待的所有线程，而不是一个线程。

* wait(long timeout) throws InterruptedException 方法

  wait 方法会让当前线程等待直到另外一个线程调用对象的 notify 或 notifyAll 方法，或者超过参数设置的 timeout 超时时间。

* wait(long timeout, int nanos) throws InterruptedException 方法

  跟 wait(long timeout) 方法类似，**多了一个 nanos 参数，这个参数表示额外时间**（以毫微秒为单位，范围是 0-999999）。 所以超时的时间还需要加上 nanos 毫秒。

* wait() throws InterruptedException 方法
  
  需要注意的是 wait(0, 0) 和 wait(0) 效果是一样的，即一直等待。

  跟之前的 2 个 wait 方法一样，只不过该方法一直等待，没有超时时间这个概念。

* finalize 方法

  该方法的作用是实例被垃圾回收器回收的时候触发的操作，就好比 “死前的最后一波挣扎”。

### 2. **补充：什么是 Native Method**

简单地讲，一个 Native Method 就是一个 java 调用非 java 代码的接口。一个 Native Method 是这样一个 java 的方法：该方法的实现由非 java 语言实现，比如 C。这个特征并非 java 所特有，很多其它的编程语言都有这一机制，比如在 C＋＋中，你可以用 extern "C" 告知 C＋＋编译器去调用一个 C 的函数。

### 3. equals()

* **1. 等价关系**

    Ⅰ 自反性

    ```java
    x.equals(x); // true
    ```

    Ⅱ 对称性

    ```java
    x.equals(y) == y.equals(x); // true
    ```

    Ⅲ 传递性

    ```java
    if (x.equals(y) && y.equals(z))
        x.equals(z); // true;
    ```

    Ⅳ 一致性

    多次调用 equals() 方法结果不变

    ```java
    x.equals(y) == x.equals(y); // true
    ```

    Ⅴ 与 null 的比较

    对任何不是 null 的对象 x 调用 x.equals(null) 结果都为 false

    ```java
    x.equals(null); // false;
    ```

* **2. 等价与相等**

  * 对于基本类型，== 判断两个值是否相等，基本类型没有 equals() 方法。

  * 对于引用类型，== 判断两个变量是否引用同一个对象，而 equals() 判断引用的对象是否等价。

    ```java
    Integer x = new Integer(1);
    Integer y = new Integer(1);
    System.out.println(x.equals(y)); // true
    System.out.println(x == y);      // false

    ```

* **3. 实现**

  * 检查是否为同一个对象的引用，如果是直接返回 true；

  * 检查是否是同一个类型，如果不是，直接返回 false；

  * 将 Object 对象进行转型；

  * 判断每个关键域是否相等。

    ```java
    public class EqualExample {

        private int x;
        private int y;
        private int z;

        public EqualExample(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EqualExample that = (EqualExample) o;

            if (x != that.x) return false;
            if (y != that.y) return false;
            return z == that.z;
        }
    }
    ```
  4. **两个对象值相同 (x.equals(y) == true)，但却可有不同的 hashcode，这句话对不对?**

​    **因为 hashCode() 方法和 equals() 方法都可以通过自定义类重写**，是可以做到 equals 相同，但是 hashCode 不同的

​    但是，在 Object 类的 equals() 方法中有这么一段话

​    翻译如下：

​    通常来讲，在重写这个方法的时候，也需要对 hashCode 方法进行重写，  
​    以此来保证这两个方法的一致性——  
​    当 equals 返回 true 的时候，这两个对象一定有相同的 hashcode.

### 4. hashCode()

hashCode() 返回散列值，而 equals() 是用来判断两个对象是否等价。等价的两个对象散列值一定相同，但是散列值相同的两个对象不一定等价。

**在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象散列值也相等。**

理想的散列函数应当具有均匀性，即不相等的对象应当均匀分布到所有可能的散列值上。这就要求了散列函数要把所有域的值都考虑进来。可以将每个域都当成 R 进制的某一位，然后组成一个 R 进制的整数。R 一般取 31，因为它是一个奇素数，如果是偶数的话，当出现乘法溢出，信息就会丢失，因为与 2 相乘相当于向左移一位。

一个数与 31 相乘可以转换成移位和减法：`31*x == (x<<5)-x`，编译器会自动进行这个优化。

```java
@Override
public int hashCode() {
    int result = 17;
    result = 31 * result + x;
    result = 31 * result + y;
    result = 31 * result + z;
    return result;
}
```




### 5. toString()

默认返回 ToStringExample@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示。

```java
public class ToStringExample {

    private int number;

    public ToStringExample(int number) {
        this.number = number;
    }
}
```

```java
ToStringExample example = new ToStringExample(123);
System.out.println(example.toString());
```

```java
ToStringExample@4554617c
```

### 6. clone()

* **1. cloneable**

clone() 是 Object 的 protected 方法，它不是 public，一个类不显式去重写 clone()，其它类就不能直接去调用该类实例的 clone() 方法。

```java
public class CloneExample {
    private int a;
    private int b;
}
```

```java
CloneExample e1 = new CloneExample();
// CloneExample e2 = e1.clone(); // 'clone()' has protected access in 'java.lang.Object'
```

重写 clone() 得到以下实现：

```java
public class CloneExample {
    private int a;
    private int b;

    @Override
    public CloneExample clone() throws CloneNotSupportedException {
        return (CloneExample)super.clone();
    }
}
```

```java
CloneExample e1 = new CloneExample();
try {
    CloneExample e2 = e1.clone();
} catch (CloneNotSupportedException e) {
    e.printStackTrace();
}
```

```java
java.lang.CloneNotSupportedException: CloneExample
```

以上抛出了 CloneNotSupportedException，这是因为 CloneExample 没有实现 Cloneable 接口。

应该注意的是，clone() 方法并不是 Cloneable 接口的方法，而是 Object 的一个 protected 方法。Cloneable 接口只是规定，如果一个类没有实现 Cloneable 接口又调用了 clone() 方法，就会抛出 CloneNotSupportedException。

```java
public class CloneExample implements Cloneable {
    private int a;
    private int b;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

* **2. 浅拷贝**

拷贝对象和原始对象的引用类型引用同一个对象。

```java
public class ShallowCloneExample implements Cloneable {

    private int[] arr;

    public ShallowCloneExample() {
        arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }

    @Override
    protected ShallowCloneExample clone() throws CloneNotSupportedException {
        return (ShallowCloneExample) super.clone();
    }
}
```

```java
ShallowCloneExample e1 = new ShallowCloneExample();
ShallowCloneExample e2 = null;
try {
    e2 = e1.clone();
} catch (CloneNotSupportedException e) {
    e.printStackTrace();
}
e1.set(2, 222);
System.out.println(e2.get(2)); // 222
```

* **3. 深拷贝**

拷贝对象和原始对象的引用类型引用不同对象。

```java
public class DeepCloneExample implements Cloneable {

    private int[] arr;

    public DeepCloneExample() {
        arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }

    @Override
    protected DeepCloneExample clone() throws CloneNotSupportedException {
        DeepCloneExample result = (DeepCloneExample) super.clone();
        result.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result.arr[i] = arr[i];
        }
        return result;
    }
}
```

```java
DeepCloneExample e1 = new DeepCloneExample();
DeepCloneExample e2 = null;
try {
    e2 = e1.clone();
} catch (CloneNotSupportedException e) {
    e.printStackTrace();
}
e1.set(2, 222);
System.out.println(e2.get(2)); // 2
```

* **4. clone() 的替代方案**

使用 clone() 方法来拷贝一个对象即复杂又有风险，它会抛出异常，并且还需要类型转换。Effective Java 书上讲到，最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象。

```java
public class CloneConstructorExample {

    private int[] arr;

    public CloneConstructorExample() {
        arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public CloneConstructorExample(CloneConstructorExample original) {
        arr = new int[original.arr.length];
        for (int i = 0; i < original.arr.length; i++) {
            arr[i] = original.arr[i];
        }
    }
    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }
}
```

```java
CloneConstructorExample e1 = new CloneConstructorExample();
CloneConstructorExample e2 = new CloneConstructorExample(e1);
e1.set(2, 222);
System.out.println(e2.get(2)); // 2
```

## 6. 关键字

### 1. final

* 修饰类：表示该类不能被继承

* 修饰方法：表示该方法不能被重写

* 修饰变量：表示该变量只能被赋值一次

    声明数据为常量，可以是编译时常量，也可以是在运行时被初始化后不能被改变的常量。

* 对于基本类型，final 使数值不变；

* 对于引用类型，final 使引用不变，表示该引用只有一次指向对象的机会，也就不能引用其它对象，但是被引用的对象本身是可以修改的。

### 2. finally

finally 是用于异常处理的场面，无论是否有异常抛出，都会执行

### 3. finalize

finalize 是 Object 的方法，所有类都继承了该方法。 当一个对象满足垃圾回收的条件，并且被回收的时候，其 finalize() 方法就会被调用

### 4. static

* **1. 静态变量**

* 静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它；静态变量在内存中只存在一份。

* 实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。

```java
public class A {
    private int x;         // 实例变量
    private static int y;  // 静态变量

    public static void main(String[] args) {
        // int x = A.x;  // Non-static field 'x' cannot be referenced from a static context
        A a = new A();
        int x = a.x;
        int y = A.y;
    }
}
```

* **2. 静态方法**

静态方法在类加载的时候就存在了，它不依赖于任何实例。**所以静态方法必须有实现，也就是说它不能是抽象方法（abstract）。**

```java
public abstract class A {
    public static void func1(){
    }
    // public abstract static void func2();  // Illegal combination of modifiers: 'abstract' and 'static'
}
```

只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字。

```java
public class A {
    private static int x;
    private int y;

    public static void func1(){
        int a = x;
        // int b = y;  // Non-static field 'y' cannot be referenced from a static context
        // int b = this.y;     // 'A.this' cannot be referenced from a static context
    }
}
```

* **3. 静态语句块**

**静态语句块在类初始化时运行一次。**

```java
public class A {
    static {
        System.out.println("123");
    }

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
    }
}
```

```java
123
```

* **4. 静态内部类**

**非静态内部类依赖于外部类的实例，而静态内部类不需要。**

当一个内部类没有使用 static 修饰的时候，是不能直接使用内部类创建对象，须要先使用外部类对象. new 内部类对象及 (外部类对象. new 内部类（）)

而静态内部类只需要`new OuterClass.InnerClass();`

```java
public class OuterClass {
    class InnerClass {
    }

    static class StaticInnerClass {
    }

    public static void main(String[] args) {
        // InnerClass innerClass = new InnerClass(); // 'OuterClass.this' cannot be referenced from a static context
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }
}
```

静态内部类不能访问外部类的非静态的变量和方法。

* **5. 静态导包**

在使用静态变量和方法时不用再指明 ClassName，从而简化代码，但可读性大大降低。

```java
import static com.xxx.ClassName.*
```

* **6. 初始化顺序**

存在继承的情况下，初始化顺序为：

* 父类（静态变量、静态语句块）

* 子类（静态变量、静态语句块）

* 父类（实例变量、普通语句块）

* 父类（构造函数）

* 子类（实例变量、普通语句块）

* 子类（构造函数）

## 7. 反射

每个类都有一个  **Class**  对象，包含了与类有关的信息。当编译一个新类时，会产生一个同名的 .class 文件，该文件内容保存着 Class 对象。

类加载相当于 Class 对象的加载，类在第一次使用时才动态加载到 JVM 中。也可以使用 `Class.forName("com.mysql.jdbc.Driver")` 这种方式来控制类的加载，该方法会返回一个 Class 对象。

反射可以提供运行时的类信息，并且这个类可以在运行时才加载进来，甚至在编译时期该类的 .class 不存在也可以加载进来。

Class 和 java.lang.reflect 一起对反射提供了支持，java.lang.reflect 类库主要包含了以下三个类：

* **Field** ：可以使用 get() 和 set() 方法读取和修改 Field 对象关联的字段；

* **Method** ：可以使用 invoke() 方法调用与 Method 对象关联的方法；

* **Constructor** ：可以用 Constructor 创建新的对象。

**反射的优点：**

* **可扩展性**  ：应用程序可以利用全限定名创建可扩展对象的实例，来使用来自外部的用户自定义类。

* **类浏览器和可视化开发环境**  ：一个类浏览器需要可以枚举类的成员。可视化开发环境（如 IDE）可以从利用反射中可用的类型信息中受益，以帮助程序员编写正确的代码。

* **调试器和测试工具**  ： 调试器需要能够检查一个类里的私有成员。测试工具可以利用反射来自动地调用类里定义的可被发现的 API 定义，以确保一组测试中有较高的代码覆盖率。

**反射的缺点：**

尽管反射非常强大，但也不能滥用。如果一个功能可以不用反射完成，那么最好就不用。在我们使用反射技术时，下面几条内容应该牢记于心。

* **性能开销**  ：反射涉及了动态类型的解析，所以 JVM 无法对这些代码进行优化。因此，反射操作的效率要比那些非反射操作低得多。我们应该避免在经常被执行的代码或对性能要求很高的程序中使用反射。

* **安全限制**  ：使用反射技术要求程序必须在一个没有安全限制的环境中运行。如果一个程序必须在有安全限制的环境中运行，如 Applet，那么这就是个问题了。

* **内部暴露**  ：由于反射允许代码执行一些在正常情况下不被允许的操作（比如访问私有的属性和方法），所以使用反射可能会导致意料之外的副作用，这可能导致代码功能失调并破坏可移植性。反射代码破坏了抽象性，因此当平台发生改变的时候，代码的行为就有可能也随着变化。

* [Trail: The Reflection API](https://docs.oracle.com/javase/tutorial/reflect/index.html)

* [深入解析 Java 反射（1）- 基础](https://www.sczyh30.com/posts/Java/java-reflection-1/)

## 8. 异常

运行时异常：

```text
NullPointerException 空指针异常
ArithmeticException 算术异常，比如除数为零
ClassCastException 类型转换异常
ConcurrentModificationException 同步修改异常，遍历一个集合的时候，删除集合的元素，就会抛出该异常
IndexOutOfBoundsException 数组下标越界异常
NegativeArraySizeException 为数组分配的空间是负数异常
```

**一般异常又叫做可查异常（受检异常），在编译过程中，必须进行处理，要么捕捉，要么通过 throws 抛出去.**

```text
比如 FileNotFoundException
```

![异常类继承关系](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl30vOKqIdadR4Z13d5ibBQUhAD4yNT1MhxvZBxOqN4DHHdKCbxkR20jULA/640?wx_fmt=png)

### Error 和 Exception 有什么区别

Error 和 Exception 都实现了 Throwable 接口

Error 指的是 JVM 层面的错误，比如内存不足 OutOfMemoryError

Exception 指的是代码逻辑的异常，比如下标越界 OutOfIndexException

## 9.泛型

```java
public class Box<T> {
    // T stands for "Type"
    private T t;
    public void set(T t) { this.t = t; }
    public T get() { return t; }
}
```

* [Java 泛型详解](http://www.importnew.com/24029.html)

* [10 道 Java 泛型面试题](https://cloud.tencent.com/developer/article/1033693)

## 10. 零散知识点

### 1. & 和 && 的区别

& 有两个作用，分别是 位与 和 逻辑与

&& 就是逻辑与

**长路与 &**：两侧，都会被运算

**短路与 &&**：只要第一个是 false，第二个就不进行运算了

### 2. heap 和 stack 有什么区别

heap: 堆

stack: 栈

* 存放的内容不一样：

* heap: 是存放对象的

* stack: 是存放基本类型 (int, float, boolean 等等)、引用 (对象地址)、方法调用

* 存取方式不一样：

* heap: 是自动增加大小的，所以不需要指定大小，但是存取相对较慢

* stack: 先入后出的顺序，并且存取速度比较快

### 3. 获取长度

数组获取长度的手段是 .length 属性

String 获取长度的手段是 length() 方法

集合获取长度的手段是 size() 方法

文件获取长度的手段是 length() 方法

### Set 里的元素是不能重复的，那么用什么方法来区分重复与否呢

以 HashSet 为例，判断重复的逻辑是：

1. 首先看 hashcode 是否相同，如果不同，就是不重复的

2. 如果 hashcode 一样，再比较 equals，如果不同，就是不重复的，否则就是重复的。

[http://how2j.cn/k/collection/collection-hashcode/371.html#step2530](http://how2j.cn/k/collection/collection-hashcode/371.html#step2530)

### 4. 构造函数器 / Constructor 是否可被 override? 是否可以继承 String 类

子类不能继承父类的构造方法，所以就不存在重写父类的构造方法。

String 是 final 修饰的，所以不能够被继承

### 5. try catch finally 执行顺序

try 里的 return 和 finally 里的 return 都会支持，但是当前方法**只会采纳 finally 中 return 的值**

[https://www.cnblogs.com/superFish2016/p/6687549.html
](https://www.cnblogs.com/superFish2016/p/6687549.html
)

**总结以上测试：**

1、finally 语句总会执行

2、如果 try、catch 中有 return 语句，finally 中没有 return，那么**在 finally 中去修改除了包装类型和静态变量、全局变量以外的数据**都不会对 try、catch 中返回的变量有任何的影响（包装类型、静态变量会改变、全局变量）。**但是修改包装类型和静态变量、全局变量，会改变变量的值。**

3、尽量不要在 finally 中使用 return 语句，如果使用的话，会忽略 try、catch 中的返回语句，也会忽略 try、catch 中的异常，屏蔽了错误的发生。

4、finally 中避免再次抛出异常，一旦 finally 中发生异常，代码执行将会抛出 finally 中的异常信息，try、catch 中的异常将被忽略。

**所以在实际项目中，finally 常常是用来关闭流或者数据库资源的，并不额外做其他操作。**

### 6. char 型变量中能不能存贮一个中文汉字? 为什么

char 是 16 位的，占两个字节

汉字通常使用 GBK 或者 UNICODE 编码，也是使用两个字节

所以可以存放汉字

### 7. 一个 ".java" 源文件中是否可以包括多个类（不是内部类）？有什么限制

可以包括多个类，但是只能出现一个 public 修饰的类，但是可以出现多个非 public 修饰的类。

### 8. java 中有几种类型的流

Java 中所有的流都是基于字节流，所以最基本的流是

```java
//输入输出字节流
InputStream

OutputStream

//在字节流的基础上，封装了字符流

Reader

Writer

//进一步，又封装了缓存流

BufferedReader

PrintWriter

//以及数据流

DataInputStream

DataOutputStream

//对象流

ObjectInputStream

ObjectOutputStream

//以及一些其他的奇奇怪怪的流 ~~~

```

### 9.什么是 java 序列化，如何实现 java 序列化

序列化指的是把一个 Java 对象，通过某种介质进行传输，比如 Socket 输入输出流，或者保存在一个文件里。

实现 java 序列化的手段是让该类实现接口 Serializable，这个接口是一个标识性接口，没有任何方法，仅仅用于表示该类可以序列化。

* **JAVA 序列化 ID 问题**

[https://blog.csdn.net/qq_35370263/article/details/79482993](https://blog.csdn.net/qq_35370263/article/details/79482993)

虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化 ID 是否一致（就是 private static final long serialVersionUID = 1L）。清单 1 中，虽然两个类的功能代码完全一致，但是序列化 ID 不同，他们无法相互序列化和反序列化。

### 10. 在 JAVA 中，如何跳出当前的多重嵌套循环

```java
public class HelloWorld {
    public static void main(String[] args) {

        //打印单数
        outloop: //outloop这个标示是可以自定义的比如outloop1,ol2,out5
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {
                System.out.println(i+":"+j);
                if(0==j%2)
                    break outloop; //如果是双数，结束外部循环
            }

        }

    }
}
```

### 11. Anonymous Inner Class (匿名内部类) 是否可以 extends(继承) 其它类，是否可以 implements(实现)interface(接口)

匿名内部类本质上就是在继承其他类，实现其他接口

如例:  
匿名类 1，就是继承了 Thread  
匿名类 2 ，就是实现了 Runnable 接口

```java
package j2se;

public class HelloWorld {

    public static void main(String[] args) {

        // 匿名类1
        new Thread() {
            public void run() {

            }
        };

        // 匿名类2
        new Runnable() {
            public void run() {

            }
        };

    }
}
```

### 12.  内部类可以引用外部类的成员吗？有没有什么限制

可以使用

如果是非静态内部类，可是使用外部类的所有成员

如果是静态内部类，只能使用外部类的静态成员

### 13. Class.forName 的作用? 为什么要用

Class.forName 常见的场景是在数据库驱动初始化的时候调用。
**Class.forName 本身的意义是加载类到 JVM 中。 一旦一个类被加载到 JVM 中，它的静态属性就会被初始化，在初始化的过程中就会执行相关代码，从而达到 "加载驱动的效果"**

### 14. JDK 中常用的包有哪些

答：java.lang、java.util、java.io、java.net、java.sql。

## 11. 集合

![集合类](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZ638wmudiadnQFvfEXsyl303Kh9WxERrLdqOnEicRtGuYEjFgKYD8vRXGwqkh6jNF0d2SPicibsotLrQ/640?wx_fmt=png)

容器主要包括 Collection 和 Map 两种，Collection 又包含了 List、Set 以及 Queue。

### 1. Collection

#### 1. Set

* HashSet：基于哈希实现，支持快速查找，但不支持有序性操作，例如根据一个范围查找元素的操作。并且失去了元素的插入顺序信息，也就是说使用 Iterator 遍历 HashSet 得到的结果是不确定的；

* TreeSet：基于红黑树实现，支持有序性操作，但是查找效率不如 HashSet，HashSet 查找时间复杂度为 O(1)，TreeSet 则为 O(logN)；

* LinkedHashSet：具有 HashSet 的查找效率，且内部使用链表维护元素的插入顺序。

#### 2. List

* ArrayList：基于动态数组实现，支持随机访问；

* Vector：和 ArrayList 类似，但它是线程安全的；

* LinkedList：基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList 还可以用作栈、队列和双向队列。

#### 3. Queue

* LinkedList：可以用它来支持双向队列；

* PriorityQueue：基于堆结构实现，可以用它来实现优先队列。

> 优先队列是一种用来维护一组元素构成的结合 S 的数据结构，其中每个元素都有一个关键字 key，元素之间的比较都是通过 key 来比较的。优先队列包括最大优先队列和最小优先队列，优先队列的应用比较广泛，比如作业系统中的调度程序，当一个作业完成后，需要在所有等待调度的作业中选择一个优先级最高的作业来执行，并且也可以添加一个新的作业到作业的优先队列中。Java 中，PriorityQueue 的底层数据结构就是堆（默认是小堆）。

### 2. Map

* HashMap：基于哈希实现；

* HashTable：和 HashMap 类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入 HashTable 并且不会导致数据不一致。它是遗留类，不应该去使用它。现在可以使用 ConcurrentHashMap 来支持线程安全，并且 ConcurrentHashMap 的效率会更高，因为 ConcurrentHashMap 引入了分段锁。

* LinkedHashMap：使用链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。

* TreeMap：基于红黑树实现。

**Collection 和 Collections 的区别？**

Collection 是接口，是 List 和 Set 的父接口

Collections 是工具类，提供了排序，混淆等等很多实用方法

**List、Set 和 Map 的初始容量和加载因子**
答： 加载因子的系数小于等于 1，意指  即当 元素个数 超过 容量长度 * 加载因子的系数 时，进行扩容。

 1. List

  ArrayList 的初始容量是 10；加载因子为 0.5； 扩容增量：**原容量的 0.5 倍 +1**；一次扩容后长度为 16。

  Vector 初始容量为 10，加载因子是 1。扩容增量：**原容量的 1 倍**，如 Vector 的容量为 10，一次扩容后是容量为 20。

  1. Set

  HashSet，初始容量为 16，加载因子为 0.75； 扩容增量：**原容量的 1 倍**； 如 HashSet 的容量为 16，一次扩容后容量为 32

  1. Map

  HashMap，初始容量 16，加载因子为 0.75； 扩容增量：**原容量的 1 倍**； 如 HashMap 的容量为 16，一次扩容后容量为 32

**Comparable 接口和 Comparator 接口有什么区别？**

答：详细可以看：[https://blog.csdn.net/u011240877/article/details/53399019](https://blog.csdn.net/u011240877/article/details/53399019)

* 对于一些普通的数据类型（比如 String, Integer, Double…），**它们默认实现了 Comparable 接口，实现了 compareTo 方法，我们可以直接使用。**

* 而对于一些自定义类，它们可能在不同情况下需要实现不同的比较策略，我们可以新创建 Comparator 接口，然后使用特定的 Comparator 实现进行比较。

**对比：**

* Comparable 简单，但是如果需要重新定义比较类型时，需要修改源代码。

* Comparator 不需要修改源代码，自定义一个比较器，实现自定义的比较方法。

**Java 集合的快速失败机制 “fail-fast”**
答：它是 java 集合的一种错误检测机制，当多个线程对集合进行结构上的改变的操作时，有可能会产生 fail-fast 机制。

可以知道**在进行 add，remove，clear 等涉及到修改集合中的元素个数的操作时**，modCount 就会发生改变 (modCount ++), 所以当另一个线程(并发修改) 或者同一个线程遍历过程中，调用相关方法使集合的个数发生改变，就会使 modCount 发生变化

每当迭代器使用 hashNext()/next() 遍历下一个元素之前，都会检测 modCount 变量是否为 expectedmodCount 值，是的话就返回遍历；否则抛出异常，终止遍历。

解决办法：

1. 在遍历过程中，所有涉及到改变 modCount 值得地方全部加上 synchronized；

2. 使用 CopyOnWriteArrayList 来替换 ArrayList。

**java.util.concurrent 包中包含的并发集合类如下：**

详细：[http://raychase.iteye.com/blog/1998965](http://raychase.iteye.com/blog/1998965)

```java
ConcurrentHashMap

CopyOnWriteArrayList

CopyOnWriteArraySet

```

## 12. 高并发编程  

### 1. 多线程和单线程的区别和联系

答：

在单核 CPU 中，将 CPU 分为很小的时间片，在每一时刻只能有一个线程在执行，是一种微观上轮流占用 CPU 的机制。

多线程会存在线程上下文切换，会导致程序执行速度变慢，即采用一个拥有两个线程的进程执行所需要的时间比一个线程的进程执行两次所需要的时间要多一些。

结论：**即采用多线程不会提高程序的执行速度，反而会降低速度，但是对于用户来说，可以减少用户的响应时间。**

### 2. 如何指定多个线程的执行顺序

三种方式：[https://blog.csdn.net/difffate/article/details/63684290](https://blog.csdn.net/difffate/article/details/63684290)

1. **方式一**

解析：面试官会给你举个例子，如何让 10 个线程按照顺序打印 0123456789？（写代码实现）

答：  
设定一个 orderNum，每个线程执行结束之后，更新 orderNum，指明下一个要执行的线程。并且唤醒所有的等待线程。  
在每一个线程的开始，要 while 判断 orderNum 是否等于自己的要求值！！不是，则 wait，是则执行本线程。

1. **方式二**

通过主线程 Join()

1. **方式三**

通过线程执行时 Join()

### 3. 线程和进程的区别（必考）

答：

进程是一个 “执行中的程序”，是系统进行资源分配和调度的一个独立单位；

线程是进程的一个实体，一个进程中拥有多个线程，线程之间共享地址空间和其它资源（所以通信和同步等操作线程比进程更加容易）；

线程上下文的切换比进程上下文切换要快很多。

（1）进程切换时，涉及**到当前进程的 CPU 环境的保存和新被调度运行进程的 CPU 环境的设置**。

（2）线程切**换仅需要保存和设置少量的寄存器内容，不涉及存储管理方面的操作**。

### 4. 多线程产生死锁的 4 个必要条件

答：

* 资源互斥条件：一个资源每次只能被一个线程使用；

* 请求与保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放；

* 不剥夺条件：进程已经获得的资源，在未使用完之前，不能强行剥夺；

* 循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系。

### 5. sleep() 和 wait(n)、wait( ) 的区别

答：

sleep 方法：是 Thread 类的静态方法，当前线程将睡眠 n 毫秒，线程进入阻塞状态。当睡眠时间到了，会解除阻塞，进行运行状态，等待 CPU 的到来。**睡眠不释放锁（如果有的话）**；

wait 方法：是 Object 的方法，必须与 synchronized 关键字一起使用，线程进入阻塞状态，当 notify 或者 notifyall 被调用后，会解除阻塞。**但是，只有重新占用互斥锁之后才会进入可运行状态。睡眠时，释放互斥锁。**

### 6. synchronized 关键字

该关键字是一个几种锁的封装。详细请看 Java 虚拟机知识点面试手册。

### 7. volatile 关键字

经典：[https://www.jianshu.com/p/195ae7c77afe](https://www.jianshu.com/p/195ae7c77afe)

解析：关于指令重排序的问题，可以查阅 DCL 双检锁失效相关资料。

答：

通过关键字 sychronize 可以防止多个线程进入同一段代码，在某些特定场景中，volatile 相当于一个轻量级的 sychronize，因为不会引起线程的上下文切换。**该关键字可以保证可见性不保证原子性。**

功能：

主内存和工作内存，直接与主内存产生交互，进行读写操作，保证可见性；

禁止 JVM 进行的指令重排序。

### 8. ThreadLocal（线程局部变量）关键字

详解：[https://www.cnblogs.com/dolphin0520/p/3920407.html](https://www.cnblogs.com/dolphin0520/p/3920407.html)

答：

当使用 ThreadLocal 维护变量时，其为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立的改变自己的副本，而不会影响其他线程对应的副本。

ThreadLocal 内部实现机制：

每个线程内部都会维护一个类似 HashMap 的对象，称为 ThreadLocalMap，里边会包含若干了 Entry（K-V 键值对），相应的线程被称为这些 Entry 的属主线程；  
Entry 的 Key 是一个 ThreadLocal 实例，Value 是一个线程特有对象。

Entry 的作用即是：为其属主线程建立起一个 ThreadLocal 实例与一个线程特有对象之间的对应关系；

Entry 对 Key 的引用是弱引用；

Entry 对 Value 的引用是强引用。
**其中最重要的一个应用实例就是经典 Web 交互模型中的 “一个请求对应一个服务器线程”（Thread-per-Request）的处理方式**

### 9. Atomic 关键字

答：可以使基本数据类型以原子的方式实现自增自减等操作。

### 10. 内存泄漏和内存溢出

答：

### 11. 内存泄露的几种方式

1. 静态集合类像 HashMap、Vector 等的使用最容易出现内存泄露，这些静态变量的生命周期和应用程序一致，所有的对象 Object 也不能被释放，因为他们也将一直被 Vector 等应用着。

```java
Static Vector v = new Vector();
for (int i = 1; i<100; i++)
{
    Object o = new Object();
    v.add(o);
    o = null;
}

```

1. 各种连接，数据库连接，网络连接，IO 连接等没有显示调用 close 关闭，不被 GC 回收导致内存泄露。

1. 监听器的使用，在释放对象的同时没有相应删除监听器的时候也可能导致内存泄露。

概念：

* 内存溢出指的是内存不够用了；

* 内存泄漏是指对象可达，但是没用了，或者你把指向内存的引用给弄丢了。即本该被 GC 回收的对象并没有被回收；

* 内存泄露是导致内存溢出的原因之一；内存泄露积累起来将导致内存溢出。

## 13. Java 8

1. Java 语言的新特性

[https://blog.csdn.net/u014470581/article/details/54944384](https://blog.csdn.net/u014470581/article/details/54944384)

### 1. **Lambda 表达式**（也称为闭包）

1. 8 以前，开发者只能使用匿名内部类代替 Lambda 表达式，现在允许我们**将函数当成参数传递给某个方法**，或者把代码本身当做数据处理。

```java
Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
```

如果 Lambda 表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来，类似于 Java 中的函数体，例如：

```java
Arrays.asList( "a", "b", "d" ).forEach( e -> {
    System.out.print( e );
    System.out.print( e );
} );
```

Lambda 表达式有返回值，返回值的类型也由编译器推理得出。如果 Lambda 表达式中的语句块只有一行，则可以不用使用 return 语句，下列两个代码片段效果相同：

```java
Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
```

和

```java
Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
    int result = e1.compareTo( e2 );
    return result;
} );
```

### 2. 函数式接口

**指的是只有一个函数的接口**，java.lang.Runnable 和 java.util.concurrent.Callable 就是函数式接口的例子；

只要某个开发者在该接口中添加一个函数，则该接口就不再是函数式接口进而导致编译失败。为了克服这种代码层面的脆弱性，并显式说明某个接口是函数式接口，java8 提供了一个特殊的注解 @Functionallnterface 来标明该接口是一个函数式接口。

不过有一点需要注意，**默认方法和静态方法不会破坏函数式接口的定义**，因此如下的代码是合法的。

```java
@FunctionalInterface
public interface FunctionalDefaultMethods {
    void method();

    default void defaultMethod() {
    }
}
```

### 3. 接口的默认方法和静态方法

默认方法和抽象方法之间的区别在于抽象方法需要实现，而默认方法不需要。接口提供的默认方法会被接口的实现类继承或者覆写。（**换句话说，可以直接继承，也可以覆写该默认方法**）

静态方法：见上方链接

由于 JVM 上的默认方法的实现在**字节码层面提供了支持**，**因此效率非常高**。默认方法允许在**不打破现有继承体系的基础上改进接口**。该特性在官方库中的应用是：给 java.util.Collection 接口添加新方法，如 stream()、parallelStream()、forEach() 和 removeIf() 等等。

尽管默认方法有这么多好处，但在实际开发中应该谨慎使用：在复杂的继承体系中，默认方法**可能引起歧义和编译错误**。如果你想了解更多细节，可以参考官方文档。

### 4. 方法引用

不甚理解，见上方链接

### 5. 引入重复注解

注解有一个很大的限制是：在同一个地方不能多次使用同一个注解。Java 8 打破了这个限制，引入了重复注解的概念，允许在同一个地方多次使用同一个注解。

在 Java 8 中使用 @Repeatable 注解定义重复注解，实际上，这并不是语言层面的改进，而是编译器做的一个 trick，底层的技术仍然相同。

### 6. 更好的类型推断

Java 8 编译器在类型推断方面有很大的提升，在很多场景下编译器可以推导出某个参数的数据类型，从而使得代码更为简洁。

### 7. 注解的使用场景拓宽

**注解几乎可以使用在任何元素上**：局部变量、接口类型、超类和接口实现类，甚至可以用在函数的异常定义上。

Java 编译器的新特性

### 8. 参数名称

为了在运行时获得 Java 程序中方法的参数名称，老一辈的 Java 程序员必须使用不同方法，例如 Paranamer liberary。Java 8 终于将这个特性规范化，在语言层面（使用反射 API 和 Parameter.getName() 方法）和字节码层面（使用新的 javac 编译器以及 - parameters 参数）提供支持。

Java 官方库的新特性

### 9. HashMap/CurrentHashMap 等变化

HashMap 是数组 + 链表 + 红黑树（**JDK1.8 增加了红黑树部分**）实现。

### 10. Optional

在 Java 8 之前，Google Guava 引入了 Optionals 类来解决 NullPointerException，从而避免源码被各种 null 检查污染，以便开发者写出更加整洁的代码。Java 8 也将 Optional 加入了官方库。

接下来看一点使用 Optional 的例子：可能为空的值或者某个类型的值：

```java
Optional< String > fullName = Optional.ofNullable( null );
System.out.println( "Full Name is set? " + fullName.isPresent() );
System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );
System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
```

如果 Optional 实例持有一个非空值，则 isPresent() 方法返回 true，否则返回 false；orElseGet() 方法，Optional 实例持有 null，则可以接受一个 lambda 表达式生成的默认值；map() 方法可以将现有的 Opetional 实例的值转换成新的值；orElse() 方法与 orElseGet() 方法类似，但是在持有 null 的时候返回传入的默认值。

### 11. Streams

新增的 Stream API（java.util.stream）将生成环境的函数式编程引入了 Java 库中。

```java
// Calculate total points of all active tasks using sum()
final long totalPointsOfOpenTasks = tasks
    .stream()
    .filter( task -> task.getStatus() == Status.OPEN )
    .mapToInt( Task::getPoints )
    .sum();

System.out.println( "Total points: " + totalPointsOfOpenTasks );
```

### 12. Date/Time API

包含了所有关于日期、时间、时区、持续时间和时钟操作的类。（Java 8 的日期与时间问题解决方案）

新的 java.time 包包含了所有关于日期、时间、时区、Instant（跟日期类似但是精确到纳秒）、duration（持续时间）和时钟操作的类。

**这些类都是不可变的、线程安全的。**

### 13. Base64

对 Base64 编码的支持已经被加入到 Java 8 官方库中，这样不需要使用第三方库就可以进行 Base64 编码

### 14. 并行数组

Java8 版本新增了很多新的方法，用于支持并行数组处理。

最重要的方法是 parallelSort()，可以显著加快多核机器上的数组排序。

### 15. 并发性

基于新增的 lambda 表达式和 steam 特性，为 Java 8 中为 java.util.concurrent.ConcurrentHashMap 类添加了新的方法来**支持聚焦操作**；

另外，也为 java.util.concurrentForkJoinPool 类添加了新的方法来**支持通用线程池操作**

JVM 的新特性

### 16. JVM 内存管理方面，**由元空间代替了永久代。**

> 其实，移除永久代的工作从 JDK1.7 就开始了。JDK1.7 中，存储在永久代的部分数据就已经转移到了 Java Heap 或者是 Native Heap。但永久代仍存在于 JDK1.7 中，并没完全移除，譬如符号引用 (Symbols) 转移到了 native heap；字面量 (interned strings) 转移到了 java heap；类的静态变量 (class statics) 转移到了 java heap。

区别：

1. 元空间并不在虚拟机中，而是**使用本地内存**；

2. 默认情况下，元空间的大小**仅受本地内存限制**；

3. 也可以通过 -XX：MetaspaceSize 指定元空间大小。

**为什么要做这个转换？所以，最后给大家总结以下几点原因：**

* **字符串**存在永久代中，容易出现性能问题和内存溢出。

* **类及方法的信息等比较难确定其大小**，因此对于永久代的大小指定比较困难，太小容易出现永久代溢出，太大则容易导致老年代溢出。

* 永久代会**为 GC 带来不必要的复杂度**，并且回收效率偏低。

* Oracle 可能会将 HotSpot 与 JRockit 合二为一。

Java 7 和 8 的新特性（英文）

【**New highlights in Java SE 8**】

1. Lambda Expressions

2. Pipelines and Streams

3. Date and Time API

4. Default Methods

5. Type Annotations

6. Nashhorn JavaScript Engine

7. Concurrent Accumulators

8. Parallel operations

9. PermGen Error Removed

【**New highlights in Java SE 7**】

1. Strings in Switch Statement

2. Type Inference for Generic Instance Creation

3. Multiple Exception Handling

4. Support for Dynamic Languages

5. Try with Resources

6. Java nio Package

7. Binary Literals, Underscore in literals

8. [Diamond Syntax](http://www.importnew.com/19345.html)

* [Difference between Java 1.8 and Java 1.7?](https://www.selfgrowth.com/articles/difference-between-java-18-and-java-17)

* Java 8 特性

Java 与 C++ 的区别

* Java 是**纯粹的面向对象语言**，所有的对象都继承自 java.lang.Object，

* C++ 为了兼容 C 即**支持面向对象也支持面向过程**。

* Java 通过**虚拟机从而实现跨平台特性**，但是 C++ 依赖于特定的平台。

* Java **没有指针**，它的引用可以理解为安全指针，而 C++ 具有和 C 一样的指针。

* Java **支持自动垃圾回收**，而 C++ 需要手动回收。

* Java **不支持多重继承**，只能通过**实现多个接口**来达到相同目的，而 C++ 支持多重继承。

* Java **不支持操作符重载**，虽然可以对两个 String 对象支持加法运算，但是这是语言内置支持的操作，不属于操作符重载，而 C++ 可以。

* Java 的 **goto 是保留字，但是不可用**，C++ 可以使用 goto。

* Java **不支持条件编译**，C++ 通过 #ifdef #ifndef 等预处理命令从而实现条件编译。

[What are the main differences between Java and C++?](http://cs-fundamentals.com/tech-interview/java/differences-between-java-and-cpp.php)

## 参考

[http://blog.csdn.net/qqxx6661](http://blog.csdn.net/qqxx6661)
