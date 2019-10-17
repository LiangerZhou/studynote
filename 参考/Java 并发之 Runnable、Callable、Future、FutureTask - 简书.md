> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://www.jianshu.com/p/cf12d4244171

Runnable
--------

```
public interface Runnable {
    public abstract void run();
}


```

Runnable 的代码非常简单，它是一个接口且只有一个 run()，创建一个类实现它，把一些费时操作写在其中，然后使用某个线程去执行该 Runnable 实现类即可实现多线程。

Callable
--------

```
public interface Callable<V> {
    V call() throws Exception;
}


```

Callable 的代码也非常简单，不同的是它是一个泛型接口，call() 函数返回的类型就是创建 Callable 传进来的 V 类型。  
学习 Callable 对比着 Runnable，这样就很快能理解它。Callable 与 Runnable 的功能大致相似，Callable 功能强大一些，就是被线程执行后，可以返回值，并且能抛出异常。

Future
------

### Future 实现代码

```
public interface Future<V> {

    boolean cancel(boolean mayInterruptIfRunning);

    boolean isCancelled();

    boolean isDone();
   
    V get() throws InterruptedException, ExecutionException;

    V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}


```

Future 是一个接口，定义了 Future 对于具体的 Runnable 或者 Callable 任务的执行结果进行取消、查询任务是否被取消，查询是否完成、获取结果。

### Future 基本用法：

```
class MyCallable implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("做一些耗时的任务...");
        Thread.sleep(5000);
        return "OK";
    }
}

public class FutureSimpleDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        
        System.out.println("dosomething...");
        
        System.out.println("得到异步任务返回结果：" + future.get());
        System.out.println("Completed!");
    }
}


```

上面是 Future 基本用法的代码以及并运行，我们可以知道：

1.  线程是属于异步计算模型，所以你不可能直接从别的线程中得到方法返回值。 这时候，Future 就出场了。
2.  Futrue 可以监视目标线程调用 call 的情况，当你调用 Future 的 get() 方法以获得结果时，当前线程就开始阻塞，直接 call 方法结束返回结果。
3.  Future 引用对象指向的实际是 FutureTask。

也就是说，总结一句话，Future 可以得到别的线程任务方法的返回值。

FutureTask
----------

### FutureTask 继承结构

FutureTask 的父类是 RunnableFuture，而 RunnableFuture 继承了 Runnbale 和 Futrue 这两个接口

```
public class FutureTask<V> implements RunnableFuture<V> 


```

```
public interface RunnableFuture<V> extends Runnable, Future<V> 


```

### FutureTask 构造方法

```
public FutureTask(Callable<V> callable) {
        if (callable == null)
            throw new NullPointerException();
        this.callable = callable;
        this.state = NEW;       
}


```

```
public FutureTask(Runnable runnable, V result) {
        this.callable = Executors.callable(runnable, result);
        this.state = NEW;       
}


```

在这里我们可以了解到：

1.  FutureTask 最终都是执行 Callable 类型的任务。
2.  如果构造函数参数是 Runnable，会被 Executors.callable 方法转换为 Callable 类型。

接下来我们看看 Executors.callable 方法代码

```
public static <T> Callable<T> callable(Runnable task, T result) {
        if (task == null)
            throw new NullPointerException();
        return new RunnableAdapter<T>(task, result);
}


```

代码很简单，直接返回一个 RunnableAdapter 实例。

接下来我们看看 RunnableAdapter 方法代码

```
    
    static final class RunnableAdapter<T> implements Callable<T> {
        final Runnable task;
        final T result;
        RunnableAdapter(Runnable task, T result) {
            this.task = task;
            this.result = result;
        }
        public T call() {
            task.run();
            return result;
        }
    }


```

可以了解到：

1.  RunnableAdapter 是 FutureTask 的一个静态内部类并且实现了 Callable，也就是说 RunnableAdapter 是 Callable 子类。
2.  call 方法实现代码是，执行 Runnable 的 run 方法，并返回构造 FutureTask 传入 result 参数。

### FutureTask 基本用法

```
public class CallableAndFuture {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(3000);
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


```

### FutureTask 总结

FutureTask 实现了两个接口，Runnable 和 Future，所以它既可以作为 Runnable 被线程执行，又可以作为 Future 得到 Callable 的返回值，那么这个组合的使用有什么好处呢？假设有一个很费时逻辑需要计算并且返回这个值，同时这个值不是马上需要，那么就可以使用这个组合，用另一个线程去计算返回值，而当前线程在使用这个返回值之前可以做其它的操作，等到需要这个返回值时，再通过 Future 得到！

### 注意：

通过 Executor 执行线程任务都是以 Callable 形式，如果传入 Runnable 都会转化为 Callable。

通过 new Thread(runnable)，只能是 Runnable 子类形式。