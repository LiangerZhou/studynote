> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://cloud.tencent.com/developer/article/1510162

> 只有光头才能变强。 文本已收录至我的 GitHub 仓库，欢迎 Star：https://github.com/ZhongFuCheng3y/3y

JVM 在准备面试的时候就有看了，一直没时间写笔记。现在到了一家公司实习，闲的时候就写写，刷刷 JVM 博客，刷刷电子书。

学习 JVM 的目的也很简单：

*   能够知道 JVM 是什么，为我们干了什么，具体是怎么干的。能够理解到一些初学时不懂的东西
*   在面试的时候有谈资
*   能装逼

![][img-0]

(图片来源：https://zhuanlan.zhihu.com/p/25511795, 侵删)

> 声明：全文默认指的是 HotSpot VM

1.1 先来看看简单的 Java 程序
-------------------

现在我有一个 JavaBean：

```
public class Java3y {

    
    private String name;

    
    private int age;

       
}

```

一个测试类：

```
public class Java3yTest {

    public static void main(String[] args) {
        
        Java3y java3y = new Java3y();
        java3y.setName("Java3y");
        System.out.println(java3y);

    }
}

```

我们在初学的时候肯定用过 `javac`来编译 `.java`文件代码，用过 `java`命令来执行编译后生成的 `.class`文件。

Java 源文件：

在使用 IDE 点击运行的时候其实就是将这两个命令**结合**起来了 (编译并运行)，方便我们开发。

生成 class 文件

解析 class 文件得到结果

1.2 编译过程
--------

`.java`文件是由 Java 源码编译器 (上述所说的 **javac.exe**) 来完成，流程图如下所示：

Java 源码编译由以下**三个**过程组成：

*   分析和输入到符号表
*   注解处理
*   语义分析和生成 class 文件

### 1.2.1 编译时期 - 语法糖

> 语法糖可以看做是**编译器实现的一些 “小把戏”**，这些 “小把戏” 可能会使得**效率 “大提升”。**

最值得说明的就是**泛型**了，这个语法糖可以说我们是经常会使用到的！

*   泛型只会在 Java 源码中存在，**编译过后**会被替换为原来的原生类型（Raw Type，也称为裸类型）了。这个过程也被称为：**泛型擦除**。

有了泛型这颗语法糖以后：

*   代码更加简洁【不用强制转换】
*   程序更加健壮【只要编译时期没有警告，那么运行时期就不会出现 ClassCastException 异常】
*   可读性和稳定性【在编写集合的时候，就限定了类型】

了解泛型更多的知识：

*   https://segmentfault.com/a/1190000014120746

1.3JVM 实现跨平台
------------

至此，我们通过 `javac.exe`编译器编译我们的 `.java`源代码文件生成出 `.class`文件了！

这些 `.class`文件很明显是**不能直接运行**的，它不像 C 语言 (编译 cpp 后生成 exe 文件直接运行)

这些 `.class`文件是交**由 JVM 来解析运行**！

*   JVM 是运行在操作系统之上的，每个操作系统的指令是不同的，而 **JDK 是区分操作系统的**，只要你的本地系统装了 JDK，这个 JDK 就是能够和当前系统兼容的。
*   而 class 字节码运行在 JVM 之上，所以**不用关心 class 字节码是在哪个操作系统编译的**，只要符合 JVM 规范，那么，这个字节码文件就是可运行的。
*   所以 Java 就做到了跨平台 ---> 一次编译，到处运行！

1.4class 文件和 JVM 的恩怨情仇
----------------------

### 1.4.1 类的加载时机

现在我们例子中生成的两个 `.class`文件**都会直接被加载到 JVM 中吗**？？

虚拟机规范则是严格规定了有且只有 5 种情况必须**立即对类进行 “初始化”**(class 文件加载到 JVM 中)：

*   创建类的实例 (new 的方式)。访问某个类或接口的静态变量，或者对该静态变量赋值，调用类的静态方法
*   反射的方式
*   初始化某个类的子类，则其父类也会被初始化
*   Java 虚拟机启动时被标明为启动类的类，直接使用 java.exe 命令来运行某个主类（包含 main 方法的那个类）
*   当使用 JDK1.7 的动态语言支持时 (....)

所以说：

*   Java 类的加载是动态的，它并不会一次性将所有类全部加载后再运行，而是保证程序运行的基础类 (像是基类) 完全加载到 jvm 中，至于其他类，**则在需要的时候才加载**。这当然就是为了**节省内存开销**。

### 1.4.2 如何将类加载到 jvm

class 文件是通过**类的加载器**装载到 jvm 中的！

Java **默认有三种类加载器**：

各个加载器的工作责任：

*   1）Bootstrap ClassLoader：负责加载 $JAVA_HOME 中 jre/lib/**rt.jar** 里所有的 class，由 C++ 实现，不是 ClassLoader 子类
*   2）Extension ClassLoader：负责加载 java 平台中**扩展功能**的一些 jar 包，包括 $JAVA_HOME 中 jre/lib/ext/*.jar 或 - Djava.ext.dirs 指定目录下的 jar 包
*   3）App ClassLoader：负责记载 **classpath** 中指定的 jar 包及目录中 class

工作过程：

*   1、当 AppClassLoader 加载一个 class 时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器 ExtClassLoader 去完成。
*   2、当 ExtClassLoader 加载一个 class 时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给 BootStrapClassLoader 去完成。
*   3、如果 BootStrapClassLoader 加载失败（例如在 $JAVA_HOME/jre/lib 里未查找到该 class），会使用 ExtClassLoader 来尝试加载；
*   4、若 ExtClassLoader 也加载失败，则会使用 AppClassLoader 来加载
*   5、如果 AppClassLoader 也加载失败，则会报出异常 ClassNotFoundException

其实这就是所谓的**双亲委派模型**。简单来说：如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把**请求委托给父加载器去完成，依次向上**。

好处：

*   **防止内存中出现多份同样的字节码** (安全性角度)

特别说明：

*   类加载器在成功加载某个类之后，会把得到的 `java.lang.Class`类的实例缓存起来。下次再请求加载该类的时候，类加载器会直接使用缓存的类的实例，而**不会尝试再次加载**。

### 1.4.2 类加载详细过程

加载器加载到 jvm 中，接下来其实又分了**好几个步骤**：

*   加载，查找并加载类的二进制数据，在 Java 堆中也**创建一个 java.lang.Class 类的对象**。
*   连接，连接又包含三块内容：验证、准备、初始化。 - 1）验证，文件格式、元数据、字节码、符号引用验证； - 2）准备，为类的静态变量分配内存，并将其初始化为默认值； - 3）解析，把类中的符号引用转换为直接引用
*   初始化，为类的静态变量赋予正确的初始值。

### 1.4.3JIT 即时编辑器

一般我们可能会想：JVM 在加载了这些 class 文件以后，针对这些字节码，**逐条取出，逐条执行** --> 解析器解析。

但如果是这样的话，那就**太慢**了！

我们的 JVM 是这样实现的：

*   就是把这些 Java 字节码**重新编译优化**，生成机器码，让 CPU 直接执行。这样编出来的代码效率会更高。
*   编译也是要花费时间的，我们一般对**热点代码**做编译，**非热点代码直接解析**就好了。

> 热点代码解释：一、多次调用的方法。二、多次执行的循环体

使用热点探测来**检测是否为热点代码**，热点探测有两种方式：

*   采样
*   计数器

目前 HotSpot 使用的是**计数器的方式**，它为每个方法准备了两类计数器：

*   方法调用计数器（Invocation Counter）
*   回边计数器（Back EdgeCounter）。
*   在确定虚拟机运行参数的前提下，这两个计数器都有一个确定的阈值，**当计数器超过阈值溢出了，就会触发 JIT 编译**。

### 1.4.4 回到例子中

按我们程序来走，我们的 `Java3yTest.class`文件会被 AppClassLoader 加载器 (因为 ExtClassLoader 和 BootStrap 加载器都不会加载它[双亲委派模型]) 加载到 JVM 中。

随后发现了要使用 Java3y 这个类，我们的 `Java3y.class`文件会被 AppClassLoader 加载器 (因为 ExtClassLoader 和 BootStrap 加载器都不会加载它[双亲委派模型]) 加载到 JVM 中

详情参考：

*   浅解 JVM 加载 class 文件
    *   https://www.mrsssswan.club/2018/06/30/jvm-start1/
*   JVM 杂谈之 JIT
    *   https://zhuanlan.zhihu.com/p/28476709

扩展阅读：

*   深入探讨 Java 类加载器
    *   https://www.ibm.com/developerworks/cn/java/j-lo-classloader
*   深入浅出 JIT 编译器
    *   https://www.ibm.com/developerworks/cn/java/j-lo-just-in-time/
*   Java 类加载器（ClassLoader）的实际使用场景有哪些？
    *   https://www.zhihu.com/question/46719811

1.5 类加载完以后 JVM 干了什么？
--------------------

在类加载检查通过后，接下来虚拟机**将为新生对象分配内存**。

1.5.1JVM 的内存结构
--------------

首先我们来了解一下 JVM 的内存结构的怎么样的：

*   基于 jdk1.8 画的 JVM 的内存结构 ---> 我画得比较**细**。

![图片描述][1]

简单看了一下内存结构，简单看看每个区域究竟存储的是什么 (干的是什么)：

*   堆：**存放对象实例**，几乎所有的对象实例都在这里分配内存
*   虚拟机栈：虚拟机栈描述的是 **Java 方法执行的内存结构**：每个方法被执行的时候都会同时创建一个**栈帧**（Stack Frame）用于存储局部变量表、操作栈、动态链接、方法出口等信息
*   本地方法栈：本地方法栈则是为虚拟机使用到的 **Native 方法服务**。
*   方法区：存储已**被虚拟机加载的类元数据信息** (元空间)
*   程序计数器：当前线程所执行的字节码的**行号指示器**

1.5.2 例子中的流程
------------

我来**宏观简述**一下我们的例子中的工作流程：

*   1、通过 `java.exe`运行 `Java3yTest.class`，随后被加载到 JVM 中，**元空间存储着类的信息** (包括类的名称、方法信息、字段信息..)。
*   2、然后 JVM 找到 Java3yTest 的主函数入口 (main)，为 main 函数创建栈帧，开始执行 main 函数
*   3、main 函数的第一条命令是 `Java3yjava3y=newJava3y();`就是让 JVM 创建一个 Java3y 对象，但是这时候方法区中没有 Java3y 类的信息，所以 JVM 马上加载 Java3y 类，把 Java3y 类的类型信息放到方法区中 (元空间)
*   4、加载完 Java3y 类之后，Java 虚拟机做的第一件事情就是在堆区中为一个新的 Java3y 实例分配内存, 然后调用构造函数初始化 Java3y 实例，这个 **Java3y 实例持有着指向方法区的 Java3y 类的类型信息**（其中包含有方法表，java 动态绑定的底层实现）的引用
*   5、当使用 `java3y.setName("Java3y");`的时候，JVM **根据 java3y 引用找到 Java3y 对象**，然后根据 Java3y 对象持有的引用定位到方法区中 Java3y 类的类型信息的**方法表**，获得 `setName()`函数的字节码的地址
*   6、为 `setName()`函数创建栈帧，开始运行 `setName()`函数

从微观上其实还做了很多东西，正如上面所说的**类加载过程**（加载 -->连接 (验证，准备，解析)--> 初始化)，在类加载完之后 jvm **为其分配内存** (分配内存中也做了非常多的事)。由于这些步骤并不是一步一步往下走，会有很多的 “混沌 bootstrap” 的过程，所以很难描述清楚。

*   扩展阅读 (先有 Class 对象还是先有 Object)：
    *   https://www.zhihu.com/question/30301819

参考资料：

*   Java 程序编译和运行的过程
    *   http://www.cnblogs.com/qiumingcheng/p/5398610.html
*   Java JVM 运行机制及基本原理
    *   https://zhuanlan.zhihu.com/p/25713880

1.6 简单聊聊各种常量池
-------------

在写这篇文章的时候，原本以为我对 `Strings="aaa";`类似这些题目已经是不成问题了，直到我遇到了 `String.intern()`这样的方法与诸如 `Strings1=newString("1")+newString("2");`混合一起用的时候

*   我发现，我还是太年轻了。

首先我是先阅读了美团技术团队的这篇文章：

*   深入解析 String#intern
    *   https://tech.meituan.com/indepthunderstandingstringintern.html

嗯，然后就懵逼了。我摘抄一下他的例子：

```
public static void main(String[] args) {
    String s = new String("1");
    s.intern();
    String s2 = "1";
    System.out.println(s == s2);

    String s3 = new String("1") + new String("1");
    s3.intern();
    String s4 = "11";
    System.out.println(s3 == s4);
}

```

打印结果是

*   jdk7,8 下 false true

调换一下位置后：

```
public static void main(String[] args) {

    String s = new String("1");
    String s2 = "1";
    s.intern();
    System.out.println(s == s2);

    String s3 = new String("1") + new String("1");
    String s4 = "11";
    s3.intern();
    System.out.println(s3 == s4);
}

```

打印结果为：

*   jdk7,8 下 false false

文章中有很详细的解析，但我简单阅读了几次以后还是很懵逼。所以我知道了自己的知识点还存在漏洞，后面阅读了一下 R 大之前写过的文章：

*   请别再拿 “String s = new String("xyz"); 创建了多少个 String 实例” 来面试了吧
    *   http://rednaxelafx.iteye.com/blog/774673#comments

看完了之后，就更加懵逼了。

后来，在 zhihu 上看到了这个回答：

*   Java 中 new String("字面量") 中 "字面量" 是何时进入字符串常量池的?
    *   https://www.zhihu.com/question/55994121

结合网上资料和自己的思考，下面整理一下对常量池的理解~~

### 1.6.1 各个常量池的情况

针对于 jdk1.7 之后：

*   运行时常量池位于**堆中**
*   字符串常量池位于**堆中**

常量池存储的是：

*   字面量 (Literal)：文本字符串等 ----> 用双引号引起来的字符串字面量都会进这里面
*   符号引用 (Symbolic References)
*   类和接口的全限定名 (Full Qualified Name)
*   字段的名称和描述符 (Descriptor)
*   方法的名称和描述符

> 常量池（Constant Pool Table），用于存放编译期生成的各种字面量和符号引用，这部分内容将在**类加载后进入方法区的运行时常量池中存放** ---> 来源：深入理解 Java 虚拟机 JVM 高级特性与最佳实践（第二版）

现在我们的运行时常量池只是换了一个位置 (原本来方法区，现在在堆中), 但可以明确的是：**类加载后，常量池中的数据会在运行时常量池中存放**！

别人总结的常量池：

> 它是 Class 文件中的内容，还不是运行时的内容，不要理解它是个池子，其实就是 Class 文件中的字节码指令

字符串常量池：

> HotSpot VM 里，记录 interned string 的一个全局表叫做 StringTable，它本质上就是个 HashSet。注意**它只存储对 java.lang.String 实例的引用，而不存储 String 对象的内容**

**字符串常量池只存储引用，不存储内容**！

再来看一下我们的 intern 方法：

```
* When the intern method is invoked, if the pool already contains a
 * string equal to this {@code String} object as determined by
 * the {@link #equals(Object)} method, then the string from the pool is
 * returned. Otherwise, this {@code String} object is added to the
 * pool and a reference to this {@code String} object is returned.

```

*   **如果常量池中存在当前字符串，那么直接返回常量池中它的引用**。
*   **如果常量池中没有此字符串, 会将此字符串引用保存到常量池中后, 再直接返回该字符串的引用**！

### 1.6.2 解析题目

本来打算写注释的方式来解释的，但好像挺难说清楚的。我还是画图吧...

```
public static void main(String[] args) {

 
    String s = new String("1");

    s.intern();


    String s2 = "1";

    System.out.println(s == s2);
    System.out.println("-----------关注公众号：Java3y-------------");
}

```

第一句： `Strings=newString("1");`

第二句： `s.intern();`发现字符串常量池中已经存在 "1" 字符串对象，直接**返回字符串常量池中对堆的引用 (但没有接收)**--> 此时 s 引用还是指向着堆中的对象

第三句： `Strings2="1";`发现字符串常量池**已经保存了该对象的引用**了，直接返回字符串常量池对堆中字符串的引用

很容易看到，**两条引用是不一样的！所以返回 false**。

```
public static void main(String[] args) {

        System.out.println("-----------关注公众号：Java3y-------------");

        String s3 = new String("1") + new String("1");


        s3.intern();


        String s4 = "11";
        System.out.println(s3 == s4); 
    }

```

第一句： `Strings3=newString("1")+newString("1");`注意：此时 **"11" 对象并没有在字符串常量池中保存引用**。

第二句： `s3.intern();`发现 "11" 对象**并没有在字符串常量池中**，于是将 "11" 对象在字符串常量池中**保存当前字符串的引用**，并**返回**当前字符串的引用 (但没有接收)

第三句： `Strings4="11";`发现字符串常量池已经存在引用了，直接返回 (**拿到的也是与 s3 相同指向的引用**)

根据上述所说的：最后会返回 true~~~

如果还是不太清楚的同学，可以试着接收一下 `intern()`方法的返回值，再看看上述的图，应该就可以理解了。

下面的就由各位来做做，看是不是掌握了：

```
public static void main(String[] args) {

        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);
    }

```

还有：

```
public static void main(String[] args) {
        String s1 = new String("he") + new String("llo");
        String s2 = new String("h") + new String("ello");
        String s3 = s1.intern();
        String s4 = s2.intern();
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
    }

```

1.7GC 垃圾回收
----------

可以说 GC 垃圾回收是 JVM 中一个非常重要的知识点，应该非常详细去讲解的。但在我学习的途中，我已经发现了有很好的文章去讲解垃圾回收的了。

所以，这里我只简单介绍一下垃圾回收的东西，详细的可以到下面的面试题中查阅和最后给出相关的资料阅 读吧~

### 1.7.1JVM 垃圾回收简单介绍

在 C++ 中，我们知道创建出的对象是需要手动去 delete 掉的。我们 Java 程序运行在 JVM 中，JVM 可以帮我们 “自动” 回收不需要的对象，对我们来说是十分方便的。

虽然说 “自动” 回收了我们不需要的对象，但如果我们想变强，就要变秃.. 不对，就要去了解一下它究竟是怎么干的，理论的知识有哪些。

首先，JVM 回收的是**垃圾**，垃圾就是我们程序中已经是不需要的了。垃圾收集器在对堆进行回收前，第一件事情就是要确定这些对象之中哪些还 “存活” 着，**哪些已经 “死去”**。判断哪些对象 “死去” 常用有两种方式：

*   引用计数法 --> 这种难以解决对象之间的循环引用的问题
*   **可达性分析算法** --> 主流的 JVM 采用的是这种方式

现在已经可以判断哪些对象已经 “死去” 了，我们现在要对这些 “死去” 的对象进行回收，回收也有好几种算法：

*   标记 - 清除算法
*   复制算法
*   标记 - 整理算法
*   分代收集算法

(这些算法详情可看下面的面试题内容)~

无论是可达性分析算法，还是垃圾回收算法，JVM 使用的都是**准确式 GC**。JVM 是使用一组称为 **OopMap** 的数据结构，来存储所有的对象引用 (这样就不用遍历整个内存去查找了，空间换时间)。 并且不会将所有的指令都生成 OopMap，只会在**安全点**上生成 OopMap，在**安全区域**上开始 GC。

*   在 OopMap 的协助下，HotSpot 可以**快速且准确地**完成 GC Roots 枚举（可达性分析）。

上面所讲的垃圾收集算法只能算是**方法论**，落地实现的是**垃圾收集器**：

*   Serial 收集器
*   ParNew 收集器
*   Parallel Scavenge 收集器
*   Serial Old 收集器
*   Parallel Old 收集器
*   CMS 收集器
*   G1 收集器

上面这些收集器大部分是可以互相**组合使用**的

1.8JVM 参数与调优
------------

很多做过 JavaWeb 项目 (ssh/ssm) 这样的同学可能都会遇到过 OutOfMemory 这样的错误。一般解决起来也很方便，在启动的时候加个参数就行了。

上面也说了很多关于 JVM 的东西 --->JVM 对内存的划分啊，JVM 各种的垃圾收集器啊。

内存的分配的大小啊，使用哪个收集器啊，这些都可以由我们**根据需求，现实情况来指定**的，这里就不详细说了，等真正用到的时候才回来填坑吧~~~~

参考资料：

*   JVM 系列三: JVM 参数设置、分析
    *   http://www.cnblogs.com/redcreen/archive/2011/05/04/2037057.html

拿些常见的 JVM 面试题来做做，**加深一下理解和查缺补漏**：

*   1、详细 jvm 内存结构
*   2、讲讲什么情况下回出现内存溢出，内存泄漏？
*   3、说说 Java 线程栈
*   4、JVM 年轻代到年老代的晋升过程的判断条件是什么呢？
*   5、JVM 出现 fullGC 很频繁，怎么去线上排查问题？
*   6、类加载为什么要使用双亲委派模式，有没有什么场景是打破了这个模式？
*   7、类的实例化顺序
*   8、JVM 垃圾回收机制，何时触发 MinorGC 等操作
*   9、JVM 中一次完整的 GC 流程（从 ygc 到 fgc）是怎样的
*   10、各种回收器，各自优缺点，重点 CMS、G1
*   11、各种回收算法
*   12、OOM 错误，stackoverflow 错误，permgen space 错误

题目来源：

*   https://www.jianshu.com/p/a07d1d4004b0

2.1 详细 jvm 内存结构
---------------

根据 JVM 规范，JVM 内存共分为虚拟机栈、堆、方法区、程序计数器、本地方法栈五个部分。

具体**可能会**聊聊 jdk1.7 以前的 PermGen（永久代），替换成 Metaspace（元空间）

*   原本永久代存储的数据：符号引用 (Symbols) 转移到了 native heap；字面量 (interned strings) 转移到了 java heap；类的静态变量 (class statics) 转移到了 java heap
*   Metaspace（元空间）存储的是类的元数据信息（metadata）
*   元空间的本质和永久代类似，都是**对 JVM 规范中方法区的实现**。不过元空间与永久代之间最大的区别在于：**元空间并不在虚拟机中，而是使用本地内存**。
*   **替换的好处**：一、字符串存在永久代中，容易出现性能问题和内存溢出。二、永久代会为 GC 带来不必要的复杂度，并且回收效率偏低

图片来源：https://blog.csdn.net/tophawk/article/details/78704074

参考资料：

*   https://www.cnblogs.com/paddix/p/5309550.html

2.2 讲讲什么情况下回出现内存溢出，内存泄漏？
------------------------

内存泄漏的原因很简单：

*   **对象是可达的** (一直被引用)
*   但是对象**不会被使用**

常见的内存泄漏例子：

```
public static void main(String[] args) {

        Set set = new HashSet();

        for (int i = 0; i < 10; i++) {
            Object object = new Object();
            set.add(object);

            
            object = null;
        }

        
        System.out.println(set);
    }

```

解决这个内存泄漏问题也很简单，将 set 设置为 null，那就可以避免**上诉**内存泄漏问题了。其他内存泄漏得一步一步分析了。

内存泄漏参考资料：

*   https://www.ibm.com/developerworks/cn/java/l-JavaMemoryLeak/

内存溢出的原因：

*   内存泄露导致堆栈内存不断增大，从而引发内存溢出。
*   大量的 jar，class 文件加载，装载类的空间不够，溢出
*   操作大量的对象导致堆内存空间已经用满了，溢出
*   nio 直接操作内存，内存过大导致溢出

解决：

*   查看程序是否存在内存泄漏的问题
*   设置参数加大空间
*   代码中是否存在死循环或循环产生过多重复的对象实体、
*   查看是否使用了 nio 直接操作内存。

参考资料：

*   https://www.cnblogs.com/bingosblog/p/6661527.html
*   http://www.importnew.com/14604.html

2.3 说说线程栈
---------

> 这里的线程栈应该指的是虚拟机栈吧...

JVM 规范让**每个 Java 线程**拥有自己的**独立的 JVM 栈**，也就是 Java 方法的调用栈。

当方法调用的时候，会生成一个**栈帧**。栈帧是保存在虚拟机栈中的，栈帧存储了方法的**局部变量表、操作数栈**、动态连接和方法返回地址等信息

线程运行过程中，**只有一个栈帧是处于活跃状态**，称为 “当前活跃栈帧”，当前活动栈帧始终是虚拟机栈的**栈顶元素**。

通过 **jstack** 工具查看线程状态

参考资料：

*   http://wangwengcn.iteye.com/blog/1622195
*   https://www.cnblogs.com/Codenewbie/p/6184898.html
*   https://blog.csdn.net/u011734144/article/details/60965155

2.4JVM 年轻代到年老代的晋升过程的判断条件是什么呢？
-----------------------------

1.  部分对象会在 From 和 To 区域中复制来复制去, **如此交换 15 次** (由 JVM 参数 MaxTenuringThreshold 决定, 这个参数默认是 15), 最终如果还是存活, 就存入到老年代。
2.  如果**对象的大小大于 Eden 的二分之一会直接分配在 old**，如果 old 也分配不下，会做一次 majorGC，如果小于 eden 的一半但是没有足够的空间，就进行 minorgc 也就是新生代 GC。
3.  minor gc 后，survivor 仍然放不下，则放到老年代
4.  动态年龄判断 ，大于等于某个年龄的对象超过了 survivor 空间一半 ，大于等于某个年龄的对象直接进入老年代

2.5JVM 出现 fullGC 很频繁，怎么去线上排查问题
------------------------------

这题就依据 full GC 的触发条件来做：

*   如果有 perm gen 的话 (jdk1.8 就没了)，**要给 perm gen 分配空间，但没有足够的空间时**，会触发 full gc。 - 所以看看是不是 perm gen 区的值设置得太小了。
*   `System.gc()`方法的调用 - 这个一般没人去调用吧~~~
*   当**统计**得到的 Minor GC 晋升到旧生代的平均大小**大于老年代的剩余空间**，则会触发 full gc(这就可以从多个角度上看了) - 是不是**频繁创建了大对象 (也有可能 eden 区设置过小)**(大对象直接分配在老年代中，导致老年代空间不足 ---> 从而频繁 gc) - 是不是**老年代的空间设置过小了** (Minor GC 几个对象就大于老年代的剩余空间了)

2.6 类加载为什么要使用双亲委派模式，有没有什么场景是打破了这个模式？
------------------------------------

双亲委托模型的重要用途是为了解决类载入过程中的**安全性问题**。

*   假设有一个开发者自己编写了一个名为 `java.lang.Object`的类，想借此欺骗 JVM。现在他要使用自定义 `ClassLoader`来加载自己编写的 `java.lang.Object`类。
*   然而幸运的是，双亲委托模型不会让他成功。因为 JVM 会优先在 `BootstrapClassLoader`的路径下找到 `java.lang.Object`类，并载入它

Java 的类加载是否一定遵循双亲委托模型？

*   在实际开发中，我们可以**通过自定义 ClassLoader，并重写父类的 loadClass 方法**，来打破这一机制。
*   SPI 就是打破了双亲委托机制的 (SPI：服务提供发现)。SPI 资料：
    *   https://zhuanlan.zhihu.com/p/28909673 -https://www.cnblogs.com/huzi007/p/6679215.html - https://blog.csdn.net/sigangjun/article/details/79071850

参考资料：

*   https://blog.csdn.net/markzy/article/details/53192993

2.7 类的实例化顺序
-----------

*   1． 父类静态成员和静态初始化块 ，按在代码中出现的顺序依次执行
*   2． 子类静态成员和静态初始化块 ，按在代码中出现的顺序依次执行
*   3． 父类实例成员和实例初始化块 ，按在代码中出现的顺序依次执行
*   4． 父类构造方法
*   5． 子类实例成员和实例初始化块 ，按在代码中出现的顺序依次执行
*   6． 子类构造方法

检验一下是不是真懂了：

```
class Dervied extends Base {


    private String name = "Java3y";

    public Dervied() {
        tellName();
        printName();
    }

    public void tellName() {
        System.out.println("Dervied tell name: " + name);
    }

    public void printName() {
        System.out.println("Dervied print name: " + name);
    }

    public static void main(String[] args) {

        new Dervied();
    }
}

class Base {

    private String name = "公众号";

    public Base() {
        tellName();
        printName();
    }

    public void tellName() {
        System.out.println("Base tell name: " + name);
    }

    public void printName() {
        System.out.println("Base print name: " + name);
    }
}

```

输出数据：

```
Dervied tell name: null
Dervied print name: null
Dervied tell name: Java3y
Dervied print name: Java3y

```

第一次做错的同学点个赞，加个关注不过分吧 (hahaha

2.8JVM 垃圾回收机制，何时触发 MinorGC 等操作
------------------------------

当 young gen 中的 eden 区分配满的时候触发 MinorGC(新生代的空间不够放的时候).

2.9JVM 中一次完整的 GC 流程（从 ygc 到 fgc）是怎样的
------------------------------------

> YGC 和 FGC 是什么

*   YGC ：**对新生代堆进行 gc**。频率比较高，因为大部分对象的存活寿命较短，在新生代里被回收。性能耗费较小。
*   FGC ：**全堆范围的 gc**。默认堆空间使用到达 80%(可调整) 的时候会触发 fgc。以我们生产环境为例，一般比较少会触发 fgc，有时 10 天或一周左右会有一次。

> 什么时候执行 YGC 和 FGC

*   a.eden 空间不足, 执行 young gc
*   b.old 空间不足，perm 空间不足，调用方法 `System.gc()` ，ygc 时的悲观策略, dump live 的内存信息时 (jmap –dump:live)，都会执行 full gc

2.10 各种回收算法
-----------

GC 最基础的算法有三种：

*   标记 - 清除算法
*   复制算法
*   标记 - 压缩算法
*   我们常用的垃圾回收器一般都采用**分代收集算法** (其实就是组合上面的算法，不同的区域使用不同的算法)。

具体：

*   标记 - 清除算法，“标记 - 清除”（Mark-Sweep）算法，如它的名字一样，算法分为 “标记” 和“清除”两个阶段：首先标记出所有需要回收的对象，在标记完成后统一回收掉所有被标记的对象。
*   复制算法，“复制”（Copying）的收集算法，它将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已使用过的内存空间一次清理掉。
*   标记 - 压缩算法，标记过程仍然与 “标记 - 清除” 算法一样，但后续步骤不是直接对可回收对象进行清理，而是让所有存活的对象都向一端移动，然后直接清理掉端边界以外的内存
*   分代收集算法，“分代收集”（Generational Collection）算法，把 Java 堆分为新生代和老年代，这样就可以根据各个年代的特点采用最适当的收集算法。

2.11 各种回收器，各自优缺点，重点 CMS、G1
--------------------------

图来源于《深入理解 Java 虚拟机：JVM 高级特效与最佳实现》，图中**两个收集器之间有连线，说明它们可以配合使用**.

*   Serial 收集器，串行收集器是最古老，**最稳定以及效率高的收集器**，但可能会产生**较长的停顿**，只使用一个线程去回收。
*   ParNew 收集器，ParNew 收集器其实就是 Serial 收集器的**多线程版本**。
*   Parallel 收集器，Parallel Scavenge 收集器类似 ParNew 收集器，Parallel 收集器**更关注系统的吞吐量**。
*   Parallel Old 收集器，Parallel Old 是 Parallel Scavenge 收集器的老年代版本，使用多线程 “标记－整理” 算法
*   CMS 收集器，CMS（Concurrent Mark Sweep）收集器是一种以**获取最短回收停顿时间**为目标的收集器。它需要**消耗额外的 CPU 和内存资源**，在 CPU 和内存资源紧张，CPU 较少时，会加重系统负担。CMS **无法处理浮动垃圾**。CMS 的 “标记 - 清除” 算法，会导致大量**空间碎片的产生**。
*   G1 收集器，G1 (Garbage-First) 是一款面向服务器的垃圾收集器, 主要针对配备多颗处理器及大容量内存的机器. **以极高概率满足 GC 停顿时间要求的同时, 还具备高吞吐量性能特征**。

2.12stackoverflow 错误，permgen space 错误
-------------------------------------

stackoverflow 错误主要出现：

*   在虚拟机栈中 (线程请求的栈深度大于虚拟机栈锁允许的最大深度)

permgen space 错误 (针对 jdk 之前 1.7 版本)：

*   大量加载 class 文件
*   常量池内存溢出

总的来说，JVM 在初级的层面上还是偏理论多，可能要做具体的东西才会有更深的体会。这篇**主要是入个门吧**~

这篇文章懒懒散散也算把 JVM 比较重要的知识点理了一遍了，后面打算学学，写写 SpringCloud 的东西。

参考资料：

*   《深入理解 Java 虚拟机 JVM 高级特性与最佳实践（第二版）》
*   纯洁的微笑 jvm 专栏：https://zhuanlan.zhihu.com/p/25511795
*   SexyCode jvm 专栏：https://blog.csdn.net/column/details/15618.html?&page=1
*   javaGC 流程：https://blog.csdn.net/yangyang12345555/article/details/79257171

[img-0]:data:image/jpeg;base64,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