> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483801&idx=1&sn=97db60faa634ff18e335ba1f0851969c&chksm=fbdb1818ccac910e956fac57c4cd2b6c1906152aece1f5720aaf52f483b48d31b9506c3d34e5&scene=21#wechat_redirect

![](https://mmbiz.qpic.cn/mmbiz_jpg/qm3R3LeH8rZibOVeek27ibUpOBgQwuV0yPA8MWFWlQgYXTqwBOaCiaIibgVcJA2IHCP4iarTnKMK0T4Hsh7cJQpyQDQ/640?wx_fmt=jpeg)

前言
==

本文快速回顾了常考的的知识点，用作面试复习，事半功倍。

上篇主要内容为：虚拟机数据区域，垃圾回收

下篇主要内容为：类加载机制

面试知识点复习手册
=========

**全复习手册文章导航**

点击公众号下方技术推文——面试冲刺

**已发布知识点复习手册**

*   [Java 基础知识点面试手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=1&sn=de2751593468f470902b698c19f8987f&chksm=fbdb18d3ccac91c56939e55cd1f0ca1b4753fd178d229440ecbf89752f5e0d518acd453e2497&scene=21#wechat_redirect)  
    
*   [Java 基础知识点面试手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483730&idx=2&sn=5610afab774b4114110c993fd0fdc43d&chksm=fbdb18d3ccac91c5e3d2978e3a780b14d09e97c997a5c50410d1adb1930da76f40238d8f409d&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483743&idx=1&sn=cc38aab9429905ddc757b529a386d1dd&chksm=fbdb18deccac91c8d0be8b3ae0e4266bb08ead73a2e57f2c977705e7b26ceaaec7aff5d5c67c&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（中）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483753&idx=1&sn=74b8180dc1a1804c355174ed34e6e33d&chksm=fbdb18e8ccac91fe3ce31ed9713bf23598e7f98dcb6d5a22b79aa92b1c773134bfebe71fbbca&scene=21#wechat_redirect)  
    
*   [Java 容器（List、Set、Map）知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483762&idx=1&sn=1f121db6552a2e77d53c500fa812fc6c&chksm=fbdb18f3ccac91e58229dd3efd09c876722d58863c2b6ff6d444b0825a955a776ced947d8470&scene=21#wechat_redirect)  
    
*   [Redis 基础知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483768&idx=1&sn=ea83244e4b9f1d6f912ca0aadab74466&chksm=fbdb18f9ccac91efe9e32704ac3d69cf1ad390ddae0f169c118ea8b9da91c6e4e6e849677a6d&scene=21#wechat_redirect)  
    
*   [Redis 基础知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483773&idx=1&sn=6bbd589e174b5d6f8bb3d6b242eb6132&chksm=fbdb18fcccac91eaa8c9d941c1d3f8d2f3874841c417d30e3ccd185b1494d51ea2fdf384c876&scene=21#wechat_redirect)  
    
*   [Java 并发知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483778&idx=1&sn=124096cdc14958b8cdae04b805d00fdc&chksm=fbdb1803ccac9115e967cd538a8008a19dacea286a632284d2e8765c6b8bd48f4f235caddd29&scene=21#wechat_redirect)
    
*   [Java 并发知识点快速复习手册（下）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483782&idx=1&sn=a0cd333ce6a32fa3f29cfae5fb6fe7e1&chksm=fbdb1807ccac9111003947936006f02972b45a6a6592f6d107f4dceb6b19c82fdff25b3ff355&scene=21#wechat_redirect)
    
*   [Java 虚拟机知识点快速复习手册（上）](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483795&idx=1&sn=4f41e144656b6b6ab6089cd558f6f5ab&chksm=fbdb1812ccac9104e425b3984659ac422afbf0505268645be65935c33bad808d4571dfed5d1f&scene=21#wechat_redirect)  
    
*   [双非硕士的春招秋招经验总结——对校招，复习以及面试心态的理解](http://mp.weixin.qq.com/s?__biz=MzU1NTA0NTEwMg==&mid=2247483669&idx=1&sn=9d45d0a80c55c2b81611e150b059fb2f&chksm=fbdb1894ccac9182a43949d445accee91afab50f27c11906ae3d3121e24908469424d0726369&scene=21#wechat_redirect)
    
*   ...... 等（请查看全复习手册导航）
    

参考
==

本文内容参考自 CyC2018 的 Github 仓库：CS-Notes

https://github.com/CyC2018/CS-Notes/

有删减，修改，补充额外增加内容

其他参考文章：

*   微信文章：精华：Java 开发岗面试知识点解析
    
*   http://how2j.cn/k/j2se-interview/j2se-interview-java/624.html
    

本作品采用知识共享署名 - 非商业性使用 4.0 国际许可协议进行许可。

类加载机制
=====

类是在运行期间动态加载的。

类的生命周期
------

包括以下 7 个阶段：

*   **加载（Loading）**
    
*   **验证（Verification）**
    
*   **准备（Preparation）**
    
*   **解析（Resolution）**
    
*   **初始化（Initialization）**
    
*   使用（Using）
    
*   卸载（Unloading）
    

**其中解析过程在某些情况下可以在初始化阶段之后再开始，这是为了支持 Java 的动态绑定。**

类加载过程
-----

包含了加载、验证、准备、解析和初始化这 5 个阶段。

### 1. 加载

**加载是类加载的一个阶段，注意不要混淆。**

加载过程完成以下三件事：

*   通过一个类的全限定名来**获取定义此类的二进制字节流。**
    
*   将这个字节流所代表的静态存储结构**转化为方法区的运行时存储结构。**
    
*   在内存中生成一个代表这个类的 Class 对象，作为方法区这个类的各种数据的访问入口。
    

其中二进制字节流可以从以下方式中获取：

*   从 ZIP 包读取，成为 JAR、EAR、WAR 格式的基础。
    
*   从网络中获取，最典型的应用是 Applet。
    
*   运行时计算生成，例如动态代理技术，在 java.lang.reflect.Proxy 使用 ProxyGenerator.generateProxyClass 的代理类的二进制字节流。
    
*   由其他文件生成，例如由 JSP 文件生成对应的 Class 类。
    

### 2. 验证

确保 Class 文件的字节流中包含的信息符合当前虚拟机的要求，并且**不会危害虚拟机自身的安全。**

### 3. 准备

准备阶段**为 static 修饰的变量分配内存并设置初始值**，使用的是**方法区**的内存。

实例变量不会在这阶段分配内存，它将会在对象实例化时随着对象一起分配在 Java 堆中。

> 初始值一般为 0 值，例如下面的类变量 value 被初始化为 0 而不是 123。

```
public static int value = 123;


```

> 如果类变量是常量，那么会按照表达式来进行初始化，而不是赋值为 0。

```
public static final int value = 123;


```

### 4. 解析

将常量池的符号引用替换为直接引用的过程。

其中解析过程在某些情况下可以在初始化阶段之后再开始，这是**为了支持 Java 的动态绑定**。

### 5. 初始化

初始化阶段才真正开始执行类中定义的 Java 程序代码。初始化阶段即虚拟机执行类构造器 <clinit>() 方法的过程。

**在准备阶段，类变量已经赋过一次系统要求的初始值，而在初始化阶段，根据程序员通过程序制定的主观计划去初始化类变量和其它资源。**

<clinit>() 方法具有以下特点：

*   是由编译器自动收集类中所有类变量的赋值动作和静态语句块中的语句合并产生的，编译器收集的顺序由语句在源文件中出现的顺序决定。特别注意的是，静态语句块只能访问到定义在它之前的类变量，定义在它之后的类变量只能赋值，不能访问。例如以下代码：
    

```
public class Test {
    static {
        i = 0;                // 给变量赋值可以正常编译通过
        System.out.print(i);  // 这句编译器会提示“非法向前引用”
    }
    static int i = 1;
}


```

*   与类的构造函数（或者说实例构造器 <init>()）不同，不需要显式的调用父类的构造器。虚拟机会自动保证在子类的 <clinit>() 方法运行之前，父类的 <clinit>() 方法已经执行结束。因此虚拟机中第一个执行 <clinit>() 方法的类肯定为 java.lang.Object。
    
*   由于父类的 <clinit>() 方法先执行，也就意味着父类中定义的静态语句块要优先于子类的变量赋值操作。例如以下代码：
    

```
static class Parent {
    public static int A = 1;
    static {
        A = 2;
    }
}

static class Sub extends Parent {
    public static int B = A;
}

public static void main(String[] args) {
     System.out.println(Sub.B);  // 2
}


```

*   <clinit>() 方法对于类或接口不是必须的，如果一个类中不包含静态语句块，也没有对类变量的赋值操作，编译器可以不为该类生成 <clinit>() 方法。
    
*   接口中不可以使用静态语句块，但仍然有类变量初始化的赋值操作，因此接口与类一样都会生成 <clinit>() 方法。但接口与类不同的是，执行接口的 <clinit>() 方法不需要先执行父接口的 <clinit>() 方法。只有当父接口中定义的变量使用时，父接口才会初始化。另外，接口的实现类在初始化时也一样不会执行接口的 <clinit>() 方法。
    
*   虚拟机会保证一个类的 <clinit>() 方法在多线程环境下被正确的加锁和同步，如果多个线程同时初始化一个类，只会有一个线程执行这个类的 <clinit>() 方法，其它线程都会阻塞等待，直到活动线程执行 <clinit>() 方法完毕。如果在一个类的 <clinit>() 方法中有耗时的操作，就可能造成多个线程阻塞，在实际过程中此种阻塞很隐蔽。
    

类初始化时机
------

### 主动引用

虚拟机规范中并没有强制约束何时进行加载，但是规范**严格规定了有且只有下列五种情况必须对类进行初始化**（**加载、验证、准备都会发生**）：

*   遇到 new、getstatic、putstatic、invokestatic 这四条字节码指令时，如果类没有进行过初始化，则必须先触发其初始化。最常见的生成这 4 条指令的场景是：
    

*   **new** 关键字实例化对象；
    
*   **读取或设置一个类的静态字段**（被 final 修饰、已在编译期把结果放入常量池的静态字段除外）的时候；
    
*   **调用类的静态方法**。
    

*   类进行**反射调用**的时候，如果**类没有进行初始化**，则需要先触发其初始化。
    
*   当初始化一个类的时候，如果**发现其父类还没有进行过初始化**，则需要先触发其父类的初始化。
    
*   当虚拟机启动时，用户需要指定一个要执行的主类（包含 main() 方法的那个类），虚拟机**会先初始化主类**；
    
*   当使用 JDK 1.7 的动态语言支持时，如果一个 java.lang.invoke.MethodHandle 实例最后的解析结果为 REF_getStatic, REF_putStatic, REF_invokeStatic 的方法句柄，并且这个**方法句柄所对应的类没有进行过初始化，则需要先触发其初始化**；
    

### 被动引用

以上 5 种场景中的行为称为对一个类进行主动引用。

**除此之外，所有引用类的方式都不会触发初始化**，称为被动引用。

被动引用的**常见例子包括：**

*   通过**子类引用父类的静态字段，不会导致子类初始化**。
    

```
System.out.println(SubClass.value); // value 字段在 SuperClass 中定义


```

*   通过**数组定义来引用类，不会触发此类的初始化**。该过程**会对数组类进行初始化**，数组类是一个由虚拟机自动生成的、直接继承自 Object 的子类，其中包含了数组的属性和方法。
    

```
SuperClass[] sca = new SuperClass[10];


```

*   使用常量：**常量**在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。
    

```
System.out.println(ConstClass.HELLOWORLD);


```

类加载器
----

实现类的加载动作。在 Java 虚拟机外部实现，以便**让应用程序自己决定如何去获取所需要的类。**

### 1. 类与类加载器

两个类相等：**类本身相等，并且还要使用同一个类加载器进行加载**。这是因为每一个类加载器都拥有一个独立的类名称空间。

这里的相等，包括类的

*   Class 对象的 equals() 方法
    
*   isAssignableFrom() 方法
    
*   isInstance() 方法的返回结果为 true
    
*   也包括使用 instanceof 关键字做对象所属关系判定结果为 true。
    

### 2. 类加载器分类

从 Java 虚拟机的角度来讲，只存在以下两种不同的类加载器：

*   **启动类加载器（Bootstrap ClassLoader）**，这个类加载器用 **C++ 实现**，是**虚拟机自身的一部分**；
    
*   **所有其他类的加载器**，这些类由 **Java 实现**，独立于虚拟机外部，并且全都继承自抽象类 java.lang.ClassLoader。
    

从 Java 开发人员的角度看，类加载器可以划分得更细致一些：

*   **启动类加载器（Bootstrap ClassLoader）**: 加载 \lib 目录下核心库
    
*   **扩展类加载器（Extension ClassLoader）**: 加载 \lib\ext 目录下扩展包
    
*   **应用程序类加载器（Application ClassLoader）**: 加载用户路径 (classpath) 上指定的类库。由于这个类加载器是 ClassLoader 中的 getSystemClassLoader() 方法的返回值，**因此一般称为系统类加载器**。  
    ，**如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。**
    

### 3. 双亲委派模型

**应用程序都是由三种类加载器相互配合进行加载的，如果有必要，还可以加入自己定义的类加载器。**

下图展示的类加载器之间的层次关系，称为类加载器的双亲委派模型（Parents Delegation Model）。

该模型**要求除了顶层的启动类加载器外，其余的类加载器都应有自己的父类加载器**。

这里类加载器之间的父子关系一般通过**组合（Composition）关系**来实现，而**不是通过继承**的关系实现。

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZibOVeek27ibUpOBgQwuV0yPH67xAKtcWd0betfwpIvia4T31dIMicCBvjoJvUfrgCasKxZ3DmdBdYqg/640?wx_fmt=png)在这里插入图片描述

（一）工作过程

**一个类加载器首先将类加载请求传送到父类加载器，只有当父类加载器无法完成类加载请求时才尝试加载。**

（二）好处

Java 类伴随其类加载器具备了带有优先级的层次关系，确保了在各种加载环境的加载顺序。    
**保证了运行的安全性，防止不可信类扮演可信任的类。**

> 例如 java.lang.Object 存放在 rt.jar 中，如果编写另外一个 java.lang.Object 的类并放到 ClassPath 中，程序可以编译通过。因为双亲委派模型的存在，所以在 rt.jar 中的 Object 比在 ClassPath 中的 Object 优先级更高，因为 rt.jar 中的 Object 使用的是启动类加载器，而 ClassPath 中的 Object 使用的是应用程序类加载器。正因为 rt.jar 中的 Object 优先级更高，因为程序中所有的 Object 都是这个 Object。

（三）实现

以下是抽象类 java.lang.ClassLoader 的代码片段，其中的 loadClass() 方法运行过程如下：

*   先检查类是否已经加载过，如果没有则让父类加载器去加载。当父类加载器加载失败时抛出 ClassNotFoundException，此时尝试自己去加载。
    

```
public abstract class ClassLoader {
    // The parent class loader for delegation
    private final ClassLoader parent;

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                try {
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    c = findClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
}


```

### 4. 自定义类加载器实现

**FileSystemClassLoader** 是自定义类加载器，继承自 java.lang.ClassLoader，用于加载文件系统上的类。它首先根据类的全名在文件系统上查找类的字节代码文件（.class 文件），然后读取该文件内容，最后通过 defineClass() 方法来把这些字节代码转换成 java.lang.Class 类的实例。

**loadClass() 实现了双亲委派模型的逻辑，因此自定义类加载器一般不去重写它，但是需要重写 findClass() 方法。**

```
public class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}


```

双亲委派的理解
-------

https://blog.csdn.net/inspiredbh/article/details/74889654

### 双亲委派模型的实现过程：

实现双亲委派模型的代码都集中在 java.lang.ClassLoader 的 loadClass() 方法中：

1. 当 Application ClassLoader 收到一个类加载请求时，他首先不会自己去尝试加载这个类，而是将这个请求委派给父类加载器 Extension ClassLoader 去完成。

2. 当 Extension ClassLoader 收到一个类加载请求时，他首先也不会自己去尝试加载这个类，而是将请求委派给父类加载器 Bootstrap ClassLoader 去完成。

3. 如果 Bootstrap ClassLoader 加载失败 (在 \lib 中未找到所需类)，就会让 Extension ClassLoader 尝试加载。

4. 如果 Extension ClassLoader 也加载失败，就会使用 Application ClassLoader 加载。

5. 如果 Application ClassLoader 也加载失败，就会使用**自定义加载器去尝试加载**。

6. 如果均加载失败，就会抛出 ClassNotFoundException 异常。

### 双亲委派模型的优点：

**保证了运行的安全性，防止不可信类扮演可信任的类。**

Class.forName() 的理解
-------------------

https://blog.csdn.net/fengyuzhengfan/article/details/38086743

**Class.forName(className) 可以简单的理解为：获得字符串参数中指定的类，并进行初始化操作。**

**Class.forName 的一个很常见的用法是在加载数据库驱动的时候。**

首先你要明白在 java 里面任何 class 都要装载在虚拟机上才能运行。

1.  forName 这句话就是**装载类用的** (new 是根据加载到内存中的类创建一个实例，要分清楚)。
    
2.  至于什么时候用，可以考虑一下这个问题，给你一个字符串变量，它代表一个类的包名和类名，你怎么实例化它？
    

```
A a = (A)Class.forName("pacage.A").newInstance();
这和 A a =new A();是一样的效果。


```

3.  jvm 在装载类时会执行类的静态代码段，要记住静态代码是和 class 绑定的，**class 装载成功就表示执行了你的静态代码了，而且以后不会再执行这段静态代码了。**
    
4.  动态加载和创建 Class 对象，**比如想根据用户输入的字符串来创建对象**
    

```
String str = 用户输入的字符串  
Class t = Class.forName(str);  
t.newInstance();


```

### newInstance() 方法和 new 关键字最主要的区别

*   一个是方法，一个是关键字
    
*   它们的区别在于创建对象的方式不一样，**前者是使用类加载机制，后者是创建一个新类**。
    
*   这主要考虑到软件的可伸缩、可扩展和可重用等软件设计思想。
    
*   newInstance: 弱类型。低效率。**只能调用无参构造**。
    
*   new: 强类型。相对高效。**能调用任何 public 构造**。
    

JVM 参数
======

GC 优化配置
-------

| 配置 | 描述 |
| --- | --- |
| -Xms | 初始化堆内存大小 |
| -Xmx | 堆内存最大值 |
| -Xmn | 新生代大小 |
| -XX:PermSize | 初始化永久代大小 |
| -XX:MaxPermSize | 永久代最大容量 |

GC 类型设置
-------

| 配置 | 描述 |
| --- | --- |
| -XX:+UseSerialGC | 串行垃圾回收器 |
| -XX:+UseParallelGC | 并行垃圾回收器 |
| -XX:+UseConcMarkSweepGC | 并发标记扫描垃圾回收器 |
| -XX:ParallelCMSThreads= | 并发标记扫描垃圾回收器 = 为使用的线程数量 |
| -XX:+UseG1GC | G1 垃圾回收器 |

```
java -Xmx12m -Xms3m -Xmn1m -XX:PermSize=20m -XX:MaxPermSize=20m -XX:+UseSerialGC -jar java-application.jar


```

关注我
===

我是蛮三刀把刀，目前为后台开发工程师。主要关注后台开发，网络安全，Python 爬虫等技术。

来微信和我聊聊：yangzd1102

Github：https://github.com/qqxx6661

### 原创博客主要内容

*   笔试面试复习知识点手册
    
*   Leetcode 算法题解析（前 150 题）
    
*   剑指 offer 算法题解析
    
*   Python 爬虫相关技术分析和实战
    
*   后台开发相关技术分析和实战
    

**同步更新以下博客**

**1. Csdn**

http://blog.csdn.net/qqxx6661

拥有专栏：Leetcode 题解（Java/Python）、Python 爬虫开发

**2. 知乎**

https://www.zhihu.com/people/yang-zhen-dong-1/

拥有专栏：码农面试助攻手册

**3. 掘金**

https://juejin.im/user/5b48015ce51d45191462ba55

**4. 简书**

https://www.jianshu.com/u/b5f225ca2376

### 个人公众号：Rude3Knife

![](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rZibOVeek27ibUpOBgQwuV0yP1sM1j7sByBnc6pdmM4GS1FuS3JV42ibCty8spAV5ic4PKENQmvcrbxibQ/640?wx_fmt=png)个人公众号：Rude3Knife

**如果文章对你有帮助，不妨收藏起来并转发给您的朋友们~**