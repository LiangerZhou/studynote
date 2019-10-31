> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://www.cnblogs.com/xiaoluo501395377/p/3383130.html

在学习 Spring 的时候，我们知道 Spring 主要有两大思想，一个是 IoC，另一个就是 AOP，对于 IoC，依赖注入就不用多说了，而对于 Spring 的核心 AOP 来说，我们不但要知道怎么通过 AOP 来满足的我们的功能，我们更需要学习的是其底层是怎么样的一个原理，而 AOP 的原理就是 java 的动态代理机制，所以本篇随笔就是对 java 的动态机制进行一个回顾。

在 java 的动态代理机制中，有两个重要的类或接口，一个是 InvocationHandler(Interface)、另一个则是 Proxy(Class)，这一个类和接口是实现我们动态代理所必须用到的。首先我们先来看看 java 的 API 帮助文档是怎么样对这两个类进行描述的：

InvocationHandler:

```
InvocationHandler is the interface implemented by the invocation handler of a proxy instance. 

Each proxy instance has an associated invocation handler. When a method is invoked on a proxy instance, the method invocation is encoded and dispatched to the invoke method of its invocation handler.

```

每一个动态代理类都必须要实现 InvocationHandler 这个接口，并且每个代理类的实例都关联到了一个 handler，当我们通过代理对象调用一个方法的时候，这个方法的调用就会被转发为由 InvocationHandler 这个接口的 invoke 方法来进行调用。我们来看看 InvocationHandler 这个接口的唯一一个方法 invoke 方法：

```
Object invoke(Object proxy, Method method, Object[] args) throws Throwable

```

我们看到这个方法一共接受三个参数，那么这三个参数分别代表什么呢？

```
Object invoke(Object proxy, Method method, Object[] args) throws Throwable

proxy:　　指代我们所代理的那个真实对象
method:　　指代的是我们所要调用真实对象的某个方法的Method对象
args:　　指代的是调用真实对象某个方法时接受的参数

```

如果不是很明白，等下通过一个实例会对这几个参数进行更深的讲解。

接下来我们来看看 Proxy 这个类：

```
Proxy provides static methods for creating dynamic proxy classes and instances, and it is also the superclass of all dynamic proxy classes created by those methods. 

```

Proxy 这个类的作用就是用来动态创建一个代理对象的类，它提供了许多的方法，但是我们用的最多的就是 newProxyInstance 这个方法：

```
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,  InvocationHandler h)  throws IllegalArgumentException

```

```
Returns an instance of a proxy class for the specified interfaces that dispatches method invocations to the specified invocation handler.

```

这个方法的作用就是得到一个动态的代理对象，其接收三个参数，我们来看看这三个参数所代表的含义：

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException

loader:　　一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载

interfaces:　　一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了

h:　　一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上

```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

好了，在介绍完这两个接口 (类) 以后，我们来通过一个实例来看看我们的动态代理模式是什么样的：

首先我们定义了一个 Subject 类型的接口，为其声明了两个方法：

```
public interface Subject
{
    public void rent();
    
    public void hello(String str);
}

```

接着，定义了一个类来实现这个接口，这个类就是我们的真实对象，RealSubject 类：

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
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

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

下一步，我们就要定义一个动态代理类了，前面说个，每一个动态代理类都必须要实现 InvocationHandler 这个接口，因此我们这个动态代理类也不例外：

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
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

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

最后，来看看我们的 Client 类：

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
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

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

我们先来看看控制台的输出：

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
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

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

我们首先来看看 $Proxy0 这东西，我们看到，这个东西是由 System.out.println(subject.getClass().getName()); 这条语句打印出来的，那么为什么我们返回的这个代理对象的类名是这样的呢？

```
Subject subject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);

```

可能我以为返回的这个代理对象会是 Subject 类型的对象，或者是 InvocationHandler 的对象，结果却不是，首先我们解释一下**为什么我们这里可以将其转化为 Subject 类型的对象？**原因就是在 newProxyInstance 这个方法的第二个参数上，我们给这个代理对象提供了一组什么接口，那么我这个代理对象就会实现了这组接口，这个时候我们当然可以将这个代理对象强制类型转化为这组接口中的任意一个，因为这里的接口是 Subject 类型，所以就可以将其转化为 Subject 类型了。

**同时我们一定要记住，通过 Proxy.newProxyInstance 创建的代理对象是在 jvm 运行时动态生成的一个对象，它并不是我们的 InvocationHandler 类型，也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，以 $ 开头，proxy 为中，最后一个数字表示对象的标号**。

接着我们来看看这两句 

subject.rent();  
subject.hello("world");

这里是通过代理对象来调用实现的那种接口中的方法，这个时候程序就会跳转到由这个代理对象关联到的 handler 中的 invoke 方法去执行，而我们的这个 handler 对象又接受了一个 RealSubject 类型的参数，表示我要代理的就是这个真实对象，所以此时就会调用 handler 中的 invoke 方法去执行：

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
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

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

我们看到，在真正通过代理对象来调用真实对象的方法的时候，我们可以在该方法前后添加自己的一些操作，同时我们看到我们的这个 method 对象是这样的：

```
public abstract void com.xiaoluo.dynamicproxy.Subject.rent()

public abstract void com.xiaoluo.dynamicproxy.Subject.hello(java.lang.String)

```

正好就是我们的 Subject 接口中的两个方法，这也就证明了当我通过代理对象来调用方法的时候，起实际就是委托由其关联到的 handler 对象的 invoke 方法中来调用，并不是自己来真实调用，而是通过代理的方式来调用的。

这就是我们的 java 动态代理机制

本篇随笔详细的讲解了 java 中的动态代理机制，这个知识点非常非常的重要，包括我们 Spring 的 AOP 其就是通过动态代理的机制实现的，所以我们必须要好好的理解动态代理的机制。

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
您可以通过点击 右下角 的按钮 来对文章内容作出评价, 也可以通过左下方的 关注按钮 来关注我的博客的最新动态。 

如果文章内容对您有帮助, 不要忘记点击右下角的 推荐按钮 来支持一下哦   

如果您对文章内容有任何疑问, 可以通过评论或发邮件的方式联系我: 501395377@qq.com  / lzp501395377@gmail.com

如果需要转载，请注明出处，谢谢！！

```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")