
# Java 类加载机制

## 一、类加载过程

类从被加载到虚拟机内存中开始，到卸载出内存为止，它的整个生命周期分为7个阶段，**加载(Loading)、验证(Verification)、准备(Preparation)、解析(Resolution)、初始化(Initialization)、使用(Using)、卸载(Unloading)。**

其中**验证、准备、解析**三个部分统称为**链接**。

其中类加载的过程包括了**加载、验证、准备、解析、初始化**五个阶段。在这五个阶段中，加载、验证、准备和初始化这四个阶段发生的顺序是确定的，而解析阶段则不一定，它在某些情况下可以在初始化阶段之后开始，这是为了支持Java语言的运行时绑定（也成为动态绑定或晚期绑定）。另外注意这里的几个阶段是按顺序开始，而不是按顺序进行或完成，因为这些阶段通常都是互相交叉地混合进行的，通常在一个阶段执行的过程中调用或激活另一个阶段。

这里简要说明下Java中的绑定：绑定指的是把一个方法的调用与方法所在的类(方法主体)关联起来，对java来说，绑定分为静态绑定和动态绑定：

- 静态绑定：即前期绑定。在程序执行前方法已经被绑定，此时由编译器或其它连接程序实现。针对java，简单的可以理解为程序编译期的绑定。java当中的方法只有final，static，private和构造方法是前期绑定的。
- 动态绑定：即晚期绑定，也叫运行时绑定。在运行时根据具体对象的类型进行绑定。在java中，几乎所有的方法都是后期绑定的。

JVM 将类加载过程分为三个步骤：加载（Load），链接（Link）和初始化 (Initialize)

![类加载过程](https://images0.cnblogs.com/blog2015/694841/201506/132053510352192.png)

### 1、 加载

  通过一个类的全限定名来获取定义此类的二进制字节流。
将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。
在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口。

>注意：JVM中的ClassLoader类加载器加载Class发生在此阶段

### 2、 链接

- 验证：确保被加载类信息符合 JVM 规范、没有安全方面的问题。

  - 2.1 文件格式的验证
  主要验证字节流是否符合Class文件格式的规范，如果符合则把字节流加载到方法区中进行存储。
文件头、主次版本验证等等

  - 2.2 元数据验证

    主要对字节码描述的信息进行语义分析，保证其描述符合Java语言的要求。

    类是否有父类
    是否继承了不允许被继承的类（final修饰过的类）
    如果这个类不是抽象类，是否实现其父类或接口中所有要求实现的方法
    类中的字段、方法是否与父类产生矛盾（如：覆盖父类final类型的字段，或者不符合个则的方法）

  - 2.3 字节码验证

    最复杂的一个阶段。主要目的是通过数据量和控制流分析，确定程序语义是合法的，符合逻辑的。
    保证被校验类的方法在运行时不会做出危害虚拟机安全的事件。

  - 2.4 符号引用验证

    符号引用中通过字符串描述的全限定名是否能找到对应的类。
    在指定类中是否存在符合方法的字段描述符以及简单名称所描述的方法和字段。
    符号引用中的类、字段、方法的访问性（private、protected、public、default）是否可被当前类访问。

- 准备：为类的静态变量分配内存，并将其初始化为默认值。

    准备阶段正式为类变量分配内存并设置初始值阶段。
    public static int value=123; 初始后为 value=0;
    对于static final类型，在准备阶段会被赋予正确的值
    public static final value=123;初始化为 value=123;

    如果是boolean值默认赋值为：false
    如果是对象引用默认赋值为：null
    ...

    >注意：
    只设置类中的静态变量（方法区中），不包括实例变量（堆内存中），实例变量是在对象实例化的时候初始化分配值的

- 解析：把虚拟机常量池中的符号引用转换为直接引用。
  
    解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程。
    符号引用：简单的理解就是字符串，比如引用一个类，java.util.ArrayList 这就是一个符号引用，字符串引用的对象不一定被加载。
    直接引用：指针或者地址偏移量。引用对象一定在内存（已经加载）。

### 3、 初始化

　　为类的静态变量赋予正确的初始值。

    执行类构造器<clinit>
    初始化静态变量、静态块中的数据等（一个类加载器只会初始化一次）
    子类的<clinit>调用前保证父类的<clinit>被调用
    注意：
    <clinit>是线程安全的，执行<clinit>的线程需要先获取锁才能进行初始化操作，保证只有一个线程能执行<clinit>(利用此特性可以实现线程安全的懒汉单例模式)。

>ps: 解析部分需要说明一下，Java 中，虚拟机会为每个加载的类维护一个常量池【不同于字符串常量池，这个常量池只是该类的字面值（例如类名、方法名）和符号引用的有序集合。 而字符串常量池，是整个 JVM 共享的】这些符号（如 int a = 5; 中的 a）就是符号引用，而解析过程就是把它转换成指向堆中的对象地址的相对地址。

**类的初始化步骤：**

1）如果这个类还没有被加载和链接，那先进行加载和链接

2）假如这个类存在直接父类，并且这个类还没有被初始化（注意：在一个类加载器中，类只能初始化一次），那就初始化直接的父类（不适用于接口）

3）如果类中存在 static 标识的块，那就依次执行这些初始化语句。

## 二、java.lang.ClassLoader 类介绍

java.lang.ClassLoader 类的基本职责就是根据一个指定的类的名称，找到或者生成其对应的字节代码，然后从这些字节代码中定义出一个 Java 类，即 java.lang.Class 类的一个实例。

ClassLoader 提供了一系列的方法，比较重要的方法如下：
![ClassLoader](https://images0.cnblogs.com/blog2015/694841/201506/132037411767454.png)

## 三、JVM 中类加载器的树状层次结构

**什么是classloader？**

- 1、ClassLoader是一个抽象类
- 2、ClassLoader的实例将读入Java字节码将类装载到JVM中
- 3、ClassLoader可以定制，满足不同的字节码流获取方式
- 4、ClassLoader负责类装载过程中的加载阶段。

Java 中的类加载器大致可以分成两类，一类是系统提供的，另外一类则是由 Java 应用开发人员编写的。

**引导类加载器（bootstrap class loader）：**

它用来加载 Java 的核心库 (jre/lib/rt.jar)，负责将 jdk 中 jre/lib 下面的核心类库或 -Xbootclasspath 选项指定的 jar 包加载到内存中，是用原生 C++ 代码来实现的，它并不继承自 java.lang.ClassLoader。由于引导类加载器涉及到虚拟机本地实现细节，开发者无法直接获取到启动类加载器的引用，所以不允许直接通过引用进行操作。

加载扩展类和应用程序类加载器，并指定他们的父类加载器，在 java 中获取不到。

**扩展类加载器（extensions class loader）：**

扩展类加载器是由Sun的 ExtClassLoader（sun.misc.Launcher$ExtClassLoader）实现的。它负责将jdk中(jre/lib/ext)或者由系统变量-Djava.ext.dir指定位置中的类库加载到内存中。Java 虚拟机的实现会提供一个扩展库目录。该类加载器在此目录里面查找并加载 Java 类。

开发者可以直接使用标准扩展类加载器。

**系统类加载器（system class loader）：**

系统类加载器是由 Sun的 AppClassLoader（sun.misc.Launcher$AppClassLoader）实现的。它负责将系统类路径java -classpath（CLASSPATH）或 -Djava.class.path变量所指的目录下的类库加载到内存中。一般来说，Java 应用的类都是由它来完成加载的。可以通过 ClassLoader.getSystemClassLoader() 来获取它。

开发者可以直接使用系统类加载器。

**自定义类加载器（custom class loader）：**

除了系统提供的类加载器以外，开发人员可以通过继承 java.lang.ClassLoader 类的方式实现自己的类加载器，以满足一些特殊的需求。

以下测试代码可以证明此层次结构

```java
public class testClassLoader {
    @Test
    public void test(){
        //application class loader
        System.out.println(ClassLoader.getSystemClassLoader());
        //extensions class loader
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        //bootstrap class loader
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }
}

```

输出为：

```java
sun.misc.Launcher$AppClassLoader@4daaf194
sun.misc.Launcher$AppClassLoader@11da5362
null
```

可以看出 ClassLoader 类是由 AppClassLoader 加载的。他的父亲是 ExtClassLoader，ExtClassLoader 的父亲无法获取是因为它是用 C++ 实现的。

## 四、双亲委派机制

　　某个特定的类加载器在接到加载类的请求时，首先将加载任务委托交给父类加载器，父类加载器又**将加载任务向上委托**，直到最父类加载器，如果最父类加载器可以完成类加载任务，就成功返回，如果不行就向下传递委托任务，由其子类加载器进行加载。

![双亲委派机制](http://ww1.sinaimg.cn/large/bb854e66gy1g8op5ywvl0j20hs0cdn1w.gif)

**双亲委派机制的好处：**
　　保证 java 核心库的安全性（例如：如果用户自己写了一个 java.lang.String 类就会因为双亲委派机制不能被加载，不会破坏原生的 String 类的加载）

1**破坏双亲委派模型**

- 案例一
双亲委派模型的问题：顶层ClassLoader，无法加载底层ClassLoader的类。
JDK的javax.xml.parsers包中定义了xml解析的类接口
Service Provider Interface SPI 位于rt.jar 即接口在启动ClassLoader中。而SPI的实现类，可能由第三方提供，AppClassLoader进行加载。
**解决思路**：可以在线程中放入底层的ClassLoader到Thread. setContextClassLoader()中，然后在顶层ClassLoader中使用Thread.getContextClassLoader()获得底层的ClassLoader进行加载第三方实现。

- 案例二
Tomcat中使用了自定ClassLoader，并且也破坏了双亲委托机制。
每个应用使用WebAppClassloader进行单独加载，他首先使用WebAppClassloader进行类加载，如果加载不了再委托父加载器去加载，这样可以保证每个应用中的类不冲突。每个tomcat中可以部署多个项目，每个项目中存在很多相同的class文件（很多相同的jar包），他们加载到jvm中可以做到互不干扰。
**代理模式**
与双亲委派机制相反，代理模式是先自己尝试加载，如果无法加载则向上传递。tomcat 就是代理模式。

- 案例三：
利用破坏双亲委派来java的类热部署实现（每次修改类文件，不需要重启服务）。
因为一个Class只能被一个ClassLoader加载一次，否则会报java.lang.LinkageError。当我们想要实现代码热部署时，可以每次都new一个自定义的ClassLoader来加载新的Class文件。JSP的实现动态修改就是使用此特性实现。

**Class加密实现思路**
ClassLoader加载.class文件的方式不仅限于从jar包中读取，还可以从种地方读取，因为ClassLoader加载时需要的是byte[]数组.

ClassLoader加载Class文件方式：

- 从本地系统中直接加载
- 通过网络下载.class文件
- 从zip，jar等归档文件中加载.class文件
- 从专有数据库中提取.class文件
- 将Java源文件动态编译为.class文件

**加密实现思路**：加载Class文件的方式灵活，我们可以自定义ClassLoader，把加密后的Class文件，在加载Class前先进行解密，然后在通过ClassLoader进行加载。

## 五、自定义类加载器

```java
public class MyClassLoader extends ClassLoader{

    private String rootPath;

    public MyClassLoader(String rootPath){
        this.rootPath = rootPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //check if the class have been loaded
        Class<?> c = findLoadedClass(name);
        if(c!=null){
            return c;
        }
        //load the class
        byte[] classData = getClassData(name);
        if(classData==null){
            throw new ClassNotFoundException();
        }
        else{
            c = defineClass(name,classData, 0, classData.length);
            return c;
        }
    }

    private byte[] getClassData(String className){
        String path = rootPath+"/"+className.replace('.', '/')+".class";

        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = new FileInputStream(path);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int temp = 0;
            while((temp = is.read(buffer))!=-1){
                bos.write(buffer,0,temp);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
```

**测试自定义的类加载器**
    创建一个测试类 HelloWorld

```java
package testOthers;

public class HelloWorld {

}
```

在 D 盘根目录创建一个 testOthers 文件夹，编译 HelloWorld.java，将得到的 class 文件放到 testOthers 文件夹下。

利用如下代码进行测试

```java
public class testMyClassLoader {
    @Test
    public void test() throws Exception{
        MyClassLoader loader = new MyClassLoader("D:");
        Class<?> c = loader.loadClass("testOthers.HelloWorld");
        System.out.println(c.getClassLoader());
    }
}
```

输出：

```java
test.ClassLoader.MyClassLoader@5b135d34
```

说明 HelloWorld 类是被我们的自定义类加载器 MyClassLoader 加载的

## 六、参考

1. [Java 类加载机制](https://www.cnblogs.com/sunniest/p/4574080.html)
2. [【深入Java虚拟机】之四：类加载机制](https://blog.csdn.net/ns_code/article/details/17881581)