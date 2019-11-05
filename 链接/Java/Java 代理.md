> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://segmentfault.com/a/1190000011291179

# Java 代理

## 一、代理模式介绍

[代理模式](https://zh.wikipedia.org/wiki/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F)是一种设计模式，提供了对目标对象额外的访问方式，即通过代理对象访问目标对象，这样可以在不修改原目标对象的前提下，提供额外的功能操作，扩展目标对象的功能。

简言之，代理模式就是设置一个中间代理来控制访问原目标对象，以达到增强原对象的功能和简化访问方式。

> 代理模式 UML 类图

![代理模式 ](https://segmentfault.com/img/remote/1460000011291184)

举个例子，我们生活中经常到火车站去买车票，但是人一多的话，就会非常拥挤，于是就有了代售点，我们能从代售点买车票了。这其中就是代理模式的体现，代售点代理了火车站对象，提供购买车票的方法。

## 二、静态代理

这种代理方式需要代理对象和目标对象实现一样的接口。

优点：可以在不修改目标对象的前提下扩展目标对象的功能。

缺点：

1. 冗余。由于代理对象要实现与目标对象一致的接口，会产生过多的代理类。
2. 不易维护。一旦接口增加方法，目标对象与代理对象都要进行修改。

> 举例：保存用户功能的静态代理实现

* 接口类： IUserDao

```java
package com.proxy;

public interface IUserDao {
  public void save();
}

```

* 目标对象：UserDao

```java
package com.proxy;

public class UserDao implements IUserDao{

  @Override
  public void save() {
    System.out.println("保存数据");
  }
}

```

* 静态代理对象：UserDapProxy **_需要实现 IUserDao 接口！_**

```java
package com.proxy;

public class UserDaoProxy implements IUserDao{

  private IUserDao target;
  public UserDaoProxy(IUserDao target) {
    this.target = target;
  }
  
  @Override
  public void save() {
    System.out.println("开启事务");//扩展了额外功能
    target.save();
    System.out.println("提交事务");
  }
}


```

* 测试类：TestProxy

```java
package com.proxy;

import org.junit.Test;

public class StaticUserProxy {
  @Test
  public void testStaticProxy(){
    //目标对象
    IUserDao target = new UserDao();
    //代理对象
    UserDaoProxy proxy = new UserDaoProxy(target);
    proxy.save();
  }
}

```

* 输出结果

```java
开启事务
保存数据
提交事务


```

## 三、动态代理

动态代理利用了 [JDK API](http://tool.oschina.net/uploads/apidocs/jdk-zh/)，动态地在内存中构建代理对象，从而实现对目标对象的代理功能。动态代理又被称为 JDK 代理或接口代理。

静态代理与动态代理的区别主要在：

* 静态代理在编译时就已经实现，编译完成后代理类是一个实际的 class 文件
* 动态代理是在运行时动态生成的，即编译完成后没有实际的 class 文件，而是在运行时动态生成类字节码，并加载到 JVM 中

**特点：**  
动态代理对象不需要实现接口，但是要求目标对象必须实现接口，否则不能使用动态代理。

JDK 中生成代理对象主要涉及的类有

* [java.lang.reflect Proxy](http://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/reflect/Proxy.html)，主要方法为

```java
static Object  newProxyInstance(ClassLoader loader, //指定当前目标对象使用类加载器，用的最多
 Class<?>[] interfaces,  //目标对象实现的接口的类型
 InvocationHandler h  //事件处理器
)
//返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序。

/**
 * loader:　　一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
 * interfaces:　　一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
 * h:　　一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
 * /

```

* [java.lang.reflect InvocationHandler](http://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/reflect/InvocationHandler.html)，主要方法为

```java
 Object  invoke(Object proxy, Method method, Object[] args)
/** 在代理实例上处理方法调用并返回结果。
 * 每一个动态代理类都必须要实现 InvocationHandler 这个接口，并且每个代理类的实例都关联到了一个 handler，当我们通过代理对象调用一个方法的时候，这个方法的调用就会被转发为由 InvocationHandler 这个接口的invoke 方法来进行调用。InvocationHandler 这个接口的唯一一个方法 invoke 方法：这个方法一共接受三个参数：
 * proxy:　　指代我们所代理的那个真实对象
 * method:　　指代的是我们所要调用真实对象的某个方法的Method对象
 * args:　　指代的是调用真实对象某个方法时接受的参数
*/

```

> 举例1：保存用户功能的动态代理实现

* 接口类： IUserDao

```java
package com.proxy;

public interface IUserDao {
  public void save();
}

```

* 目标对象：UserDao，实现IUserDao接口

```java
package com.proxy;

public class UserDao implements IUserDao{

  @Override
  public void save() {
    System.out.println("保存数据");
  }
}

```

* 动态代理对象：UserProxyFactory， 前面说过，每一个动态代理类都必须要实现 InvocationHandler 这个接口，因此我们这个动态代理类也不例外

```java
package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory implements InvocationHandler{

  private Object target;// 维护一个目标对象,这个就是我们要代理的真实对象
  
  // 构造方法，给我们要代理的真实对象赋初值
  public ProxyFactory(Object target) {
    this.target = target;
  }

  // 为目标对象生成代理对象
  public Object getProxyInstance() {
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
      new InvocationHandler() {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开启事务");

        // 执行目标对象方法
        Object returnValue = method.invoke(target, args);

        System.out.println("提交事务");
        return null;
        }
      });
  }
}

```

* 测试类：TestProxy

```java
package com.proxy;

import org.junit.Test;

public class TestProxy {

  @Test
  public void testDynamicProxy (){
    IUserDao target = new UserDao();
    System.out.println(target.getClass());  //输出目标对象信息
    IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
    System.out.println(proxy.getClass());  //输出代理对象信息
    proxy.save();  //执行代理方法
  }
}

```

* 输出结果

```java
class com.proxy.UserDao
class com.sun.proxy.$Proxy4
开启事务
保存数据
提交事务

```

> 举例2：保存用户功能的动态代理实现

首先我们定义了一个 Subject 类型的接口，为其声明了两个方法：

```java
public interface Subject
{
    public void rent();

    public void hello(String str);
}

```

接着，定义了一个类来实现这个接口，这个类就是我们的真实对象，RealSubject 类：

```java
public class RealSubject implements Subject
{
    @Override
    public void rent()
    {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str)
    {
        System.out.println("hello: " + str);
    }
}

```

下一步，我们就要定义一个动态代理类了，前面说个，每一个动态代理类都必须要实现 InvocationHandler 这个接口，因此我们这个动态代理类也不例外：

```java
public class DynamicProxy implements InvocationHandler
{
    //　这个就是我们要代理的真实对象
    private Object subject;

    //    构造方法，给我们要代理的真实对象赋初值
    public DynamicProxy(Object subject)
    {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args)
            throws Throwable
    {
        //　　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before rent house");

        System.out.println("Method:" + method);

        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);

        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after rent house");

        return null;
    }

}

```

最后，来看看我们的 Client 类：

```java
public class Client
{
    public static void main(String[] args)
    {
        //    我们要代理的真实对象
        Subject realSubject = new RealSubject();

        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(realSubject);

        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        Subject subject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);

        System.out.println(subject.getClass().getName());
        subject.rent();
        subject.hello("world");
    }
}

```

我们先来看看控制台的输出：

```java
$Proxy0

before rent house
Method:public abstract void com.xiaoluo.dynamicproxy.Subject.rent()
I want to rent my house
after rent house

before rent house
Method:public abstract void com.xiaoluo.dynamicproxy.Subject.hello(java.lang.String)
hello: world
after rent house

```

我们首先来看看 $Proxy0 这东西，我们看到，这个东西是由 System.out.println(subject.getClass().getName()); 这条语句打印出来的，那么为什么我们返回的这个代理对象的类名是这样的呢？

```java
Subject subject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);

```

可能我以为返回的这个代理对象会是 Subject 类型的对象，或者是 InvocationHandler 的对象，结果却不是，首先我们解释一下 **为什么我们这里可以将其转化为 Subject 类型的对象？** 原因就是在 newProxyInstance 这个方法的第二个参数上，我们给这个代理对象提供了一组什么接口，那么我这个代理对象就会实现了这组接口，这个时候我们当然可以将这个代理对象强制类型转化为这组接口中的任意一个，因为这里的接口是 Subject 类型，所以就可以将其转化为 Subject 类型了。

**同时我们一定要记住，通过 Proxy.newProxyInstance 创建的代理对象是在 jvm 运行时动态生成的一个对象，它并不是我们的 InvocationHandler 类型，也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，以 $ 开头，proxy 为中，最后一个数字表示对象的标号**。

接着我们来看看这两句

subject.rent();  
subject.hello("world");

这里是通过代理对象来调用实现的那种接口中的方法，这个时候程序就会跳转到由这个代理对象关联到的 handler 中的 invoke 方法去执行，而我们的这个 handler 对象又接受了一个 RealSubject 类型的参数，表示我要代理的就是这个真实对象，所以此时就会调用 handler 中的 invoke 方法去执行：

```java
public Object invoke(Object object, Method method, Object[] args)
            throws Throwable
    {
        //　　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before rent house");

        System.out.println("Method:" + method);

        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);

        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after rent house");

        return null;
    }

```

我们看到，在真正通过代理对象来调用真实对象的方法的时候，我们可以在该方法前后添加自己的一些操作，同时我们看到我们的这个 method 对象是这样的：

```java
public abstract void com.xiaoluo.dynamicproxy.Subject.rent()

public abstract void com.xiaoluo.dynamicproxy.Subject.hello(java.lang.String)

```

正好就是我们的 Subject 接口中的两个方法，这也就证明了当我通过代理对象来调用方法的时候，起实际就是委托由其关联到的 handler 对象的 invoke 方法中来调用，并不是自己来真实调用，而是通过代理的方式来调用的。

这就是我们的 java 动态代理机制

## 四、cglib 代理

> cglib is a powerful, high performance and quality Code Generation Library. It can extend JAVA classes and implement interfaces at runtime.

[cglib](https://github.com/cglib/cglib) (Code Generation Library) 是一个第三方代码生成类库，运行时在内存中动态生成一个子类对象从而实现对目标对象功能的扩展。

>**cglib 特点**

* JDK 的动态代理有一个限制，就是使用动态代理的对象必须实现一个或多个接口。  
  如果想代理没有实现接口的类，就可以使用 CGLIB 实现。
* CGLIB 是一个强大的高性能的代码生成包，它可以在运行期扩展 Java 类与实现 Java 接口。  
  它广泛的被许多 AOP 的框架使用，例如 Spring AOP 和 dynaop，为他们提供方法的 interception（拦截）。
* CGLIB 包的底层是通过使用一个小而快的字节码处理框架 ASM，来转换字节码并生成新的类。  
  不鼓励直接使用 ASM，因为它需要你对 JVM 内部结构包括 class 文件的格式和指令集都很熟悉。

cglib 与动态代理最大的**区别**就是

* 使用动态代理的对象必须实现一个或多个接口
* 使用 cglib 代理的对象则无需实现接口，达到代理类无侵入。

使用 cglib 需要引入 [cglib 的 jar 包](https://repo1.maven.org/maven2/cglib/cglib/3.2.5/cglib-3.2.5.jar)，如果你已经有 spring-core 的 jar 包，则无需引入，因为 spring 中包含了 cglib。

* cglib 的 Maven 坐标

```java
<dependency>
  <groupId>cglib</groupId>
  <artifactId>cglib</artifactId>
  <version>3.2.5</version>
</dependency>

```

> 举例：保存用户功能的动态代理实现

* 目标对象：UserDao

```java
package com.cglib;

public class UserDao{

  public void save() {
    System.out.println("保存数据");
  }
}

```

* 代理对象：ProxyFactory

```java
package com.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactory implements MethodInterceptor{

  private Object target;//维护一个目标对象
  public ProxyFactory(Object target) {
    this.target = target;
  }
  
  //为目标对象生成代理对象
  public Object getProxyInstance() {
    //工具类
    Enhancer en = new Enhancer();
    //设置父类
    en.setSuperclass(target.getClass());
    //设置回调函数
    en.setCallback(this);
    //创建子类对象代理
    return en.create();
  }

  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
    System.out.println("开启事务");
    // 执行目标对象的方法
    Object returnValue = method.invoke(target, args);
    System.out.println("关闭事务");
    return null;
  }
}

```

* 测试类：TestProxy

```java
package com.cglib;

import org.junit.Test;

public class TestProxy {

  @Test
  public void testCglibProxy(){
    //目标对象
    UserDao target = new UserDao();
    System.out.println(target.getClass());
    //代理对象
    UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
    System.out.println(proxy.getClass());
    //执行代理对象方法
    proxy.save();
  }
}


```

* 输出结果

```java
class com.cglib.UserDao
class com.cglib.UserDao$$EnhancerByCGLIB$$552188b6
开启事务
保存数据
关闭事务

```

## 五、总结

1. 静态代理实现较简单，只要代理对象对目标对象进行包装，即可实现增强功能，但静态代理只能为一个目标对象服务，如果目标对象过多，则会产生很多代理类。
2. JDK 动态代理需要目标对象实现业务接口，代理类只需实现 InvocationHandler 接口。
3. 动态代理生成的类为 lass com.sun.proxy.$Proxy4，cglib 代理生成的类为 class com.cglib.UserDao$$EnhancerByCGLIB$$552188b6。
4. 静态代理在编译时产生 class 字节码文件，可以直接使用，效率高。
5. 动态代理必须实现 InvocationHandler 接口，通过反射代理方法，比较消耗系统性能，但可以减少代理类的数量，使用更灵活。
6. cglib 代理无需实现接口，通过生成类字节码实现代理，比反射稍快，不存在性能问题，但 cglib 会继承目标对象，需要重写方法，所以目标对象不能为 final 类。

## 六、相关资料

代理模式相关知识

* [代理模式及 Java 实现动态代理](http://www.jianshu.com/p/6f6bb2f0ece9)
* [设计模式读书笔记 - 代理模式](http://blog.csdn.net/chenssy/article/details/11179815)
* [JDK 动态代理实现原理](http://rejoy.iteye.com/blog/1627405)
* [Java 动态代理机制分析及扩展](https://www.ibm.com/developerworks/cn/java/j-lo-proxy1/)
* [Java 代理 (jdk 静态代理、动态代理和 cglib 动态代理)](http://www.cnblogs.com/fillPv/p/5939277.html)
* [AOP 的底层实现 - CGLIB 动态代理和 JDK 动态代理](http://blog.csdn.net/dreamrealised/article/details/12885739)
* [深入浅出 CGlib - 打造无入侵的类代理](http://llying.iteye.com/blog/220452)
* [Spring AOP 实现原理与 CGLIB 应用](https://www.ibm.com/developerworks/cn/java/j-lo-springaopcglib/)

UML 相关知识

* [博客 - UML 类图与类的关系详解](https://yq.aliyun.com/articles/75556)
* [goole 图书 -《UML 建模实例详解》](https://books.google.com.hk/books?id=dU--JuYudxgC&pg=PA18&lpg=PA18&dq=uml%E8%AF%A6%E8%A7%A3&source=bl&ots=ay5tHhDK0l&sig=_Fj_cEmG9ZSN5S3HHl1SrNcmcKw&hl=zh-CN&sa=X&ved=0ahUKEwiR14Kg45LVAhXL44MKHRZHD3Y4ChDoAQg3MAQ#v=onepage&q=uml%E8%AF%A6%E8%A7%A3&f=false)
