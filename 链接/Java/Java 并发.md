# Java并发

## 1. 前言

本文快速回顾了常考的的知识点，用作面试复习，事半功倍。

## 2. 线程状态转换

![线程状态转换](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoLgKMNyv6ibkghozqnOBlc6PlpwhYDHQk8zKIRTatE6IAwCrQj9SQtQibg/640?wx_fmt=png)

### 新建（New）

创建后尚未启动。

### 可运行（Runnable）

可能正在运行，也可能正在等待 CPU 时间片。

包含了操作系统线程状态中的 Running 和 Ready。

### 阻塞（Blocking）

等待获取一个排它锁，如果其线程释放了锁就会结束此状态。

### 无限期等待（Waiting）

等待其它线程显式地唤醒，否则不会被分配 CPU 时间片。

| 进入方法 | 退出方法 |
| --- | --- |
| 没有设置 Timeout 参数的 Object.wait() 方法 | Object.notify() / Object.notifyAll() |
| 没有设置 Timeout 参数的 Thread.join() 方法 | 被调用的线程执行完毕 |
| LockSupport.park() 方法 | - |

### 限期等待（Timed Waiting）

无需等待其它线程显式地唤醒，在一定时间之后会被系统自动唤醒。

调用 Thread.sleep() 方法使线程进入限期等待状态时，常常用 “使一个线程睡眠” 进行描述。

调用 Object.wait() 方法使线程进入限期等待或者无限期等待时，常常用 “挂起一个线程” 进行描述。

睡眠和挂起是用来描述行为，而阻塞和等待用来描述状态。

> **阻塞和等待的区别**
> 阻塞是被动的，它是在等待获取一个排它锁**；
> 而等待是主动的，通过调用 Thread.sleep() 和 Object.wait() 等方法进入。

| 进入方法 | 退出方法 |
| --- | --- |
| Thread.sleep() 方法 | 时间结束 |
| 设置了 Timeout 参数的 Object.wait() 方法 | 时间结束 / Object.notify() / Object.notifyAll() |
| 设置了 Timeout 参数的 Thread.join() 方法 | 时间结束 / 被调用的线程执行完毕 |
| LockSupport.parkNanos() 方法 | - |
| LockSupport.parkUntil() 方法 | - |

### 死亡（Terminated）

可以是线程结束任务之后自己结束，或者产生了异常而结束。

## 3. 使用线程

有三种使用线程的方法：

* 继承 Thread 类

* 实现 Runnable 接口

* 实现 Callable 接口

实现 Runnable 和 Callable 接口的类只能当做一个可以在线程中运行的任务，不是真正意义上的线程，因此最后还需要通过 Thread 来调用。可以说任务是通过线程驱动从而执行的。

### 1. 继承 Thread 类

**同样也是需要实现 run() 方法，并且最后也是调用 start() 方法来启动线程。**

```java
public class MyThread extends Thread {
    public void run() { //此处实现不需要写@override
        // ...
    }
}

```

```java
public static void main(String[] args) {
    MyThread mt = new MyThread();
    mt.start();
}

```

### 2. 实现 Runnable 接口

**需要实现 run() 方法。**

**通过 Thread 调用 start() 方法来启动线程。**

```java
public class MyRunnable implements Runnable {
    @override
    public void run() {
        // ...
    }
}


```

```java
public static void main(String[] args) {
    MyRunnable instance = new MyRunnable();
    Thread thread = new Thread(instance);
    thread.start();
}


```

### 3. 实现 Callable 接口

Callable 就是 Runnable 的扩展。

与 Runnable 相比，Callable 可以有返回值，返回值通过 FutureTask 进行封装。

```java
public class MyCallable implements Callable<Integer> {
    public Integer call() {
        return 123;
    }
}

```

Future 和 Callable 结合使用

```java
public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService exec = Executors.newCachedThreadPool();
    MyCallable mc = new MyCallable();
    Future<Integer> result = exec.submit(mc);
    exec.shutdown();

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e1) {
        e1.printStackTrace();
    }

    System.out.println("主线程在执行任务");

    try {
        System.out.println("mc运行结果"+result.get());
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }

    System.out.println("所有任务执行完毕");
    }
}

```

FutureTask 和 Callable 结合使用

```java
public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService exec = Executors.newCachedThreadPool();
    MyCallable mc = new MyCallable();
    FutureTask<Integer> ft = new FutureTask<>(mc);
    exec.submit(ft);
    exec.shutdown();

    //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
    /*MyCallable mc = new MyCallable();
    FutureTask<Integer> ft = new FutureTask<>(mc);
    Thread thread = new Thread(ft);
    thread.start();*/

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e1) {
        e1.printStackTrace();
    }

    System.out.println("主线程在执行任务");

    try {
        System.out.println("task运行结果"+ft.get());
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }

    System.out.println("所有任务执行完毕");

}

```

### 4. 其他方法

严格说不能算方法，只能算实现方式：

* 匿名内部类

* 线程池

> **实现接口 VS 继承 Thread**

**实现接口会更好一些**，因为：

* Java 不支持多重继承，因此继承了 Thread 类就无法继承其它类，但是可以实现多个接口；

* 类可能只要求可执行就行，继承整个 Thread 类开销过大。

* 代码可以被多线程共享，数据独立，很容易实现资源共享

> start 和 run 有什么区别？

详细解释：[https://blog.csdn.net/lai_li/article/details/53070141?locationNum=13&fps=1](https://blog.csdn.net/lai_li/article/details/53070141?locationNum=13&fps=1)

> start 方法：

* 通过该方法启动线程的同时也创建了一个线程，真正实现了多线程。**无需等待 run() 方法中的代码执行完毕，就可以接着执行下面的代码**。

* 此时 start() 的这个线程处于就绪状态，当得到 CPU 的时间片后就会执行其中的 run() 方法。这个 run() 方法包含了要执行的这个线程的内容，run() 方法运行结束，此线程也就终止了。

> run 方法：

* **通过 run 方法启动线程其实就是调用一个类中的方法，当作普通的方法的方式调用。并没有创建一个线程，程序中依旧只有一个主线程**，必须等到 run() 方法里面的代码执行完毕，才会继续执行下面的代码，这样就没有达到写线程的目的。

> **线程代码示例**

```java
 package cn.thread.test;

 /*
  * 设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1。写出程序。
  */
 public class ThreadTest1 {

     private int j;

    public static void main(String[] args) {
        ThreadTest1 tt = new ThreadTest1();
        Inc inc = tt.new Inc();
        Dec dec = tt.new Dec();

        Thread t1 = new Thread(inc);
        Thread t2 = new Thread(dec);
        Thread t3 = new Thread(inc);
        Thread t4 = new Thread(dec);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName()+"inc:"+j);
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName()+"dec:"+j);
    }

    class Inc implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                inc();
            }
        }
    }

    class Dec extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                dec();
            }
        }
    }
}


```

## 4. 基础线程机制

### 1. Executor 线程池

[https://segmentfault.com/a/1190000014741369#articleHeader3](https://segmentfault.com/a/1190000014741369#articleHeader3)

**Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。异步是指多个任务的执行互不干扰，不需要进行同步操作。**

* 当前线程池大小 ：表示线程池中实际工作者线程的数量；

* 最大线程池大小 （maxinumPoolSize）：表示线程池中允许存在的工作者线程的数量上限；

* 核心线程大小 （corePoolSize ）：表示一个不大于最大线程池大小的工作者线程数量上限。

**如果运行的线程少于 corePoolSize，则 Executor 始终首选添加新的线程，而不进行排队；**

**如果运行的线程等于或者多于 corePoolSize，则 Executor 始终首选将请求加入队列，而不是添加新线程；**

**如果无法将请求加入队列，即队列已经满了，则创建新的线程，除非创建此线程超出 maxinumPoolSize， 在这种情况下，任务将被拒绝。**

> **不用线程池的弊端**

* **线程生命周期的开销非常高**。每个线程都有自己的生命周期，创建和销毁线程所花费的时间和资源可能比处理客户端的任务花费的时间和资源更多，并且还会有某些空闲线程也会占用资源。

* **程序的稳定性和健壮性会下降**，每个请求开一个线程。如果受到了恶意攻击或者请求过多 (内存不足)，程序很容易就奔溃掉了。

### 2. ThreadPoolExecutor 类

实现了 Executor 接口，是用的最多的线程池，下面是已经默认实现的三种：

* newCachedThreadPool：**一个任务创建一个线程；**

> 非常有弹性的线程池，对于新的任务，如果此时线程池里没有空闲线程，线程池会毫不犹豫的创建一条新的线程去处理这个任务。

```java
public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 5; i++) {
        executorService.execute(new MyRunnable());
    }
    executorService.shutdown();
}

```

* newFixedThreadPool：**所有任务只能使用固定大小的线程；**

> 一个固定线程数的线程池，它将返回一个 corePoolSize 和 maximumPoolSize 相等的线程池。

* SingleThreadExecutor：**相当于大小为 1 的 FixedThreadPool。**

> **ThreadPoolExecutor 提供了 shutdown() 和 shutdownNow() 两个方法来关闭线程池**

**区别：**

* 调用 shutdown() 后，线程池状态立刻变为 SHUTDOWN，而调用 shutdownNow()，线程池状态立刻变为 STOP。

* shutdown() 等待任务执行完才中断线程，而 shutdownNow() 不等任务执行完就中断了线程。

### 3. ScheduledThreadPoolExecutor 类

相当于提供了**延迟和周期执行功能**的 ThreadPoolExecutor 类

### 4. Daemon 守护线程

守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。

**当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。**

main() 属于非守护线程，垃圾回收是守护线程。

**使用 setDaemon() 方法将一个线程设置为守护线程。**

```java
public static void main(String[] args) {
    Thread thread = new Thread(new MyRunnable());
    thread.setDaemon(true);
}

```

* 使用守护线程不要访问共享资源 (数据库、文件等)，因为它可能会在任何时候就挂掉了。

* 守护线程中产生的新线程也是守护线程

### 5. sleep()

Thread.sleep(millisec) 方法会休眠当前正在执行的线程，millisec 单位为毫秒。

sleep() 可能会抛出 InterruptedException，因为异常不能跨线程传播回 main() 中，因此必须在本地进行处理。线程中抛出的其它异常也同样需要在本地进行处理。

```java
public void run() {
    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

```

### 6. yield()

对静态方法 Thread.yield() 的调用声明了当前线程已经完成了生命周期中最重要的部分，可以切换给其它线程来执行。**该方法只是对线程调度器的一个建议**，而且也只是建议具有相同优先级的其它线程可以运行。

```java
public void run() {
    Thread.yield();
}

```

### 7. 中断

一个线程执行完毕之后会自动结束，如果在运行过程中发生异常也会提前结束。

**现在已经没有强制线程终止的方法了！**。

Stop 方法太暴力了，不安全，所以被设置过时了。

interrupt()：报出 InterruptedException

[https://segmentfault.com/a/1190000014463417#articleHeader9](https://segmentfault.com/a/1190000014463417#articleHeader9)

> **要注意的是：interrupt 不会真正停止一个线程，它仅仅是给这个线程发了一个信号告诉它，它应该要结束了 (明白这一点非常重要！)**

调用 interrupt() 并不是要真正终止掉当前线程，**仅仅是设置了一个中断标志**。这个中断标志可以给我们用来判断什么时候该干什么活！什么时候中断由我们自己来决定，这样就可以安全地终止线程了！

**通过调用一个线程的 interrupt() 来中断该线程，可以中断处于：**

* 阻塞

* 限期等待

* 无限期等待状态

那么就会抛出 InterruptedException，从而提前结束该线程。

**但是不能中断 I/O 阻塞和 synchronized 锁阻塞。**

对于以下代码，在 main() 中启动一个线程之后再中断它，由于线程中调用了 Thread.sleep() 方法，因此会抛出一个 InterruptedException，从而提前结束线程，不执行之后的语句。

```java
public class InterruptExample {

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

```

```java
public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new MyThread1();
    thread1.start();
    thread1.interrupt();
    System.out.println("Main run");
}

```

```java
Main run
java.lang.InterruptedException: sleep interrupted
    at java.lang.Thread.sleep(Native Method)
    at InterruptExample.lambda$main$0(InterruptExample.java:5)
    at InterruptExample$$Lambda$1/713338599.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)

```

### 8. interrupted() 和 isInterrupted()

interrupt 线程中断还有另外两个方法 (**检查该线程是否被中断**)：

* 静态方法 interrupted()--> 会清除中断标志位

* 实例方法 isInterrupted()--> 不会清除中断标志位

如果一个线程的 run() 方法执行一个**无限循环（不属于阻塞、限期等待、非限期等待），例如 while（True）**，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，那么调用线程的 interrupt() 方法就**无法使线程提前结束**。

然而，**但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。**

```java
 Thread t1 = new Thread( new Runnable(){
     public void run(){
        // 若未发生中断，就正常执行任务
        while(!Thread.currentThread.isInterrupted()){
            // 正常任务代码……
        }
        // 中断的处理代码……
        doSomething();
    }
} ).start();

```

### 9. Executor 线程池的中断操作

* 调用 Executor 的 shutdown() 方法会**等待线程都执行完毕之后再关闭**

* 但是如果调用的是 shutdownNow() 方法，**则相当于调用每个线程的 interrupt() 方法。**

以下使用 Lambda 创建线程，相当于创建了一个匿名内部线程。

```java
public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(() -> {
        try {
            Thread.sleep(2000);
            System.out.println("Thread run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });
    executorService.shutdownNow();
    System.out.println("Main run");
}

```

```java
Main run
java.lang.InterruptedException: sleep interrupted
    at java.lang.Thread.sleep(Native Method)
    at ExecutorInterruptExample.lambda$main$0(ExecutorInterruptExample.java:9)
    at ExecutorInterruptExample$$Lambda$1/1160460865.run(Unknown Source)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
    at java.lang.Thread.run(Thread.java:745)

```

**如果只想中断 Executor 中的一个线程**，可以通过使用 submit() 方法来提交一个线程，它会返回一个 Future 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。

```java
Future<?> future = executorService.submit(() -> {
    // ..
});
future.cancel(true);

```

## 5. 互斥同步

* JVM 实现的 synchronized

* JDK 实现的 ReentrantLock。

### 1. 可重入与不可重入锁

[https://blog.csdn.net/u012545728/article/details/80843595](https://blog.csdn.net/u012545728/article/details/80843595)

### 2. 不可重入锁

所谓不可重入锁，即若当前线程执行某个方法已经获取了该锁，那么在方法中尝试再次获取锁时，就会获取不到被阻塞。

```java
public class Count{
    Lock lock = new Lock();
    public void print(){
        lock.lock();
        doAdd();
        lock.unlock();
    }
    public void doAdd(){
        lock.lock();
        //do something
        lock.unlock();
    }
}

```

### 3. 可重入锁

所谓可重入，意味着线程可以进入它已经拥有的锁的同步代码块儿

> 我们设计两个线程调用 print() 方法，第一个线程调用 print() 方法获取锁，进入 lock() 方法，由于初始 lockedBy 是 null，所以不会进入 while 而挂起当前线程，而是是增量 lockedCount 并记录 lockBy 为第一个线程。接着第一个线程进入 doAdd() 方法，由于同一进程，所以不会进入 while 而挂起，接着增量 lockedCount，当第二个线程尝试 lock，由于 isLocked=true, 所以他不会获取该锁，直到第一个线程调用两次 unlock() 将 lockCount 递减为 0，才将标记为 isLocked 设置为 false。

可重入锁的概念和设计思想大体如此，Java 中的可重入锁 ReentrantLock 设计思路也是这样

> **synchronized 和 ReentrantLock 都是可重入锁**

### 4. synchronized

> **1. 同步一个代码块**

```java
public void func () {
    synchronized (this) {
        // ...
    }
}

```

它只作用于同一个对象，**如果调用两个不同对象上的同步代码块**，就不会进行同步。

> **2. 同步一个方法**

```java
public synchronized void func () {
    // ...
}

```

它和同步代码块一样，**只作用于同一个对象。**

> **3. 同步一个类**

```java
public void func() {
    synchronized (SynchronizedExample.class) {
        // ...
    }
}

```

**作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也需要进行同步。**

> **4. 同步一个静态方法**

```java
public synchronized static void fun() {
    // ...
}

```

**作用于整个类。**

> **释放锁的时机**

* 当方法 (代码块) 执行完毕后会自动释放锁，不需要做任何的操作。

* 当一个线程执行的代码出现异常时，其所持有的锁会自动释放。

### 5. Lock

**有 ReentrantLock 和 ReentrantReadWriteLock，后者分为读锁和写锁，读锁允许并发访问共享资源。**

```java
public class LockExample {

    private Lock lock = new ReentrantLock();

    public void func() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        } finally {
            lock.unlock(); // 确保释放锁，从而避免发生死锁。
        }
    }
}

```

```java
public static void main(String[] args) {
    LockExample lockExample = new LockExample();
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(() -> lockExample.func());
    executorService.execute(() -> lockExample.func());
}

```

```java
10 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9

```

ReentrantLock 是 **java.util.concurrent（J.U.C）包中的锁**，相比于 synchronized，它多了以下高级功能：

>**1. 等待可中断**

**当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情**，可中断特性对处理执行时间非常长的同步块很有帮助。

>**2. 可实现公平锁**

公平锁是指多个线程在等待同一个锁时，**必须按照申请锁的时间顺序来依次获得锁**；而非公平锁则不保证这一点，在锁被释放时，任何一个等待锁的线程都有机会获得锁。

* synchronized 中的锁是非公平的

* **ReentrantLock 默认情况下也是非公平的，但可以通过带布尔值的构造函数要求使用公平锁。**

>**3. 锁绑定多个条件**

* synchronized 中，锁对象的 wait() 和 notify() 或 notifyAll() 方法可以实现一个隐含的条件，如果要和多于一个的条件关联的时候，就不得不额外地添加一个锁

* 而 ReentrantLock 则无须这样做，只需要多次调用 newCondition() 方法即可。

### 6. ReentrantReadWriteLock

我们知道 synchronized 内置锁和 ReentrantLock 都是**互斥锁** (一次只能有一个线程进入到临界区 (被锁定的区域)

ReentrantReadWriteLock 优点：

* 在**读取数据**的时候，**可以多个线程同时进入到到临界区** (被锁定的区域)

* 在写数据的时候，无论是读线程还是写线程都是互斥的

* 如果读的线程比写的线程要多很多的话，那可以考虑使用它。它使用 state 的变量高 16 位是读锁，低 16 位是写锁

* 写锁可以降级为读锁，读锁不能升级为写锁

> **synchronized 和 ReentrantLock 比较**
>
> **1. 锁的实现**

synchronized 是 JVM 实现的，而 ReentrantLock 是 JDK 实现的。

> **2. 性能**

新版本 Java 对 synchronized 进行了很多优化，例如自旋锁等，synchronized 与 ReentrantLock 大致相同。

> **3. 等待可中断**

ReentrantLock 可中断，而 synchronized 不行。

> **4. 公平锁**

* 公平锁能保证：老的线程（234）排队使用锁，新线程仍然排队使用锁（2345）。

* 非公平锁保证：老的线程（234）排队使用锁；**但是无法保证新线程 5 抢占已经在排队的线程的锁（正好在 1 释放锁的时候抢占到了锁，没有进入排队队列）**。

synchronized 中的锁是非公平的，ReentrantLock 默认情况下也是非公平的，但是也可以是公平的。

> **5. 锁绑定多个条件**

一个 ReentrantLock 可以同时绑定多个 Condition 对象。

### 7. 使用选择

除非需要使用 ReentrantLock 的高级功能，否则优先使用 synchronized。

* synchronized 好用，简单，性能不差

* **没有使用到 Lock 显式锁的特性就不要使用 Lock 锁了。**

* synchronized 是 JVM 实现的一种锁机制，JVM 原生地支持它，而 ReentrantLock 不是所有的 JDK 版本都支持。

* 并且使用 synchronized 不用担心没有释放锁而导致死锁问题，因为 **JVM 会确保锁的释放**。

## 6. 线程之间的协作

当多个线程可以一起工作去解决某个问题时，**如果某些部分必须在其它部分之前完成，那么就需要对线程进行协调。**

### 1. join()

在线程中调用另一个线程的 join() 方法，会将当前线程挂起，而不是忙等待， 直到目标线程结束。

对于以下代码，虽然 b 线程先启动，但是因为在 b 线程中调用了 a 线程的 join() 方法，因此 b 线程会等待 a 线程结束才继续执行，因此最后能够保证 a 线程的输出先与 b 线程的输出。

### 2. wait() notify() notifyAll()

调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。

**它们都属于 Object 的一部分，而不属于 Thread。**

**只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateExeception。**

使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的同步方法或者同步控制块中，那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。

> **wait() 和 sleep() 的区别**

1. **wait() 是 Object 的方法，而 sleep() 是 Thread 的静态方法；**

2. **wait() 会释放锁，sleep() 不会。**

> **await() signal() signalAll()**

java.util.concurrent 类库中提供了 Condition 类来实现线程之间的协调，可以在 Condition 上调用 await() 方法使线程等待，其它线程调用 signal() 或 signalAll() 方法唤醒等待的线程。

**相比于 wait() 这种等待方式，await() 可以指定等待的条件，因此更加灵活。**

**使用 Lock 来获取一个 Condition 对象。**

```java
public class AwaitSignalExample {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void before() {
        lock.lock();
        try {
            System.out.println("before");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void after() {
        lock.lock();
        try {
            condition.await();
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

```

```java
public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    AwaitSignalExample example = new AwaitSignalExample();
    executorService.execute(() -> example.after());
    executorService.execute(() -> example.before());
}

```

```java
before
after

```

## 7. J.U.C

### 1. AQS

从整体来看，concurrent 包的实现示意图如下：

![https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoLNdlCfFANGZCzJul3bDKVucpkZmFrKvvWyfllKXmtleqUEWyoV0BCKQ/640?wx_fmt=png](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoLNdlCfFANGZCzJul3bDKVucpkZmFrKvvWyfllKXmtleqUEWyoV0BCKQ/640?wx_fmt=png)在这里插入图片描述

[https://segmentfault.com/a/1190000014595928](https://segmentfault.com/a/1190000014595928)

java.util.concurrent（J.U.C）大大提高了并发性能，AQS 被认为是 J.U.C 的核心。

AbstractQueuedSynchronizer 简称为 AQS：**AQS 定义了一套多线程访问共享资源的同步器框架，许多同步类实现都依赖于它，我们 Lock 之类的两个常见的锁都是基于它来实现的。**

* AQS 可以给我们实现锁的框架

* 内部实现的关键是：先进先出的队列、state 状态

* 定义了内部类 ConditionObject

* 拥有两种线程模式：

* 独占模式

* 共享模式

* 在 LOCK 包中的相关锁 (常用的有 ReentrantLock、 ReadWriteLock) 都是基于 AQS 来构建

* 一般我们叫 AQS 为同步器

#### AQS 实现特点

* 同步状态

* 使用 volatile 修饰实现线程可见性

* 修改 state 状态值时使用 CAS 算法来实现

* 先进先出队列

#### CountdownLatch

**维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，那些因为调用 await() 方法而在等待的线程就会被唤醒。**

使用说明：

* count 初始化 CountDownLatch，然后需要等待的线程调用 await 方法。await 方法会一直受阻塞直到 count=0。

* 而其它线程完成自己的操作后，调用 countDown() 使计数器 count 减 1。

* 当 count 减到 0 时，所有在等待的线程均会被释放

说白了就是通过 count 变量来控制等待，如果 count 值为 0 了 (其他线程的任务都完成了)，那就可以继续执行。

![https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoL2hD3veDkKzWwkujVHfLajELiaa2TOeFiaM3aruWutjFrY158PKxIjX9w/640?wx_fmt=png](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoL2hD3veDkKzWwkujVHfLajELiaa2TOeFiaM3aruWutjFrY158PKxIjX9w/640?wx_fmt=png)在这里插入图片描述

```java
public class CountdownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.print("run..");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end");
        executorService.shutdown();
    }
}

run..run..run..run..run..run..run..run..run..run..end


```

#### CyclicBarrier

**CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。**

和 CountdownLatch 相似，都是通过维护计数器来实现的。但是它的计数器是递增的，每次执行 await() 方法之后，计数器会加 1，直到计数器的值和设置的值相等，等待的所有线程才会继续执行。

**CyclicBarrier 可以被重用 (对比于 CountDownLatch 是不能重用的),CyclicBarrier 的计数器通过调用 reset() 方法可以循环使用，所以它才叫做循环屏障。**

![https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoLWia710glWhbLhCvTS2vTxm8e9PZ8VPuodic64NZ7WfOyb8bSZKJBTuiaA/640?wx_fmt=png](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoLWia710glWhbLhCvTS2vTxm8e9PZ8VPuodic64NZ7WfOyb8bSZKJBTuiaA/640?wx_fmt=png)在这里插入图片描述

```java
public CyclicBarrier(int parties, Runnable barrierAction) {
    if (parties <= 0) throw new IllegalArgumentException();
    this.parties = parties;
    this.count = parties;
    this.barrierCommand = barrierAction;
}

public CyclicBarrier(int parties) {
    this(parties, null);
}

```

```java
public class CyclicBarrierExample {
    public static void main(String[] args) {
        final int totalThread = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.print("before..");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.print("after..");
            });
        }
        executorService.shutdown();
    }
}
before..before..before..before..before..before..before..before..before..before..after..after..after..after..after..after..after..after..after..after..

```

#### Semaphore

Semaphore 就是操作系统中的信号量，可以控制对互斥资源的访问线程数。

* 当调用 acquire() 方法时，会消费一个许可证。如果没有许可证了，会阻塞起来

* 当调用 release() 方法时，会添加一个许可证。

* 这些 "许可证" 的个数其实就是一个 count 变量罢了~

![https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoL3mMZwlBnSxicic00XqyHduMyoM7Nd0rXWhr0UkPIEVx6GMXdMzl6yfhg/640?wx_fmt=png](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoL3mMZwlBnSxicic00XqyHduMyoM7Nd0rXWhr0UkPIEVx6GMXdMzl6yfhg/640?wx_fmt=png) 在这里插入图片描述

```java
public class SemaphoreExample {
    public static void main(String[] args) {
        final int clientCount = 3;
        final int totalRequestCount = 10;
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.print(semaphore.availablePermits() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }
}

```

### 2. J.U.C - 其它组件

#### 1. FutureTask

在介绍 Callable 时我们知道它可以有返回值，返回值通过 Future 进行封装。

FutureTask 实现了 RunnableFuture 接口，该接口继承自 Runnable 和 Future 接口，这使得 FutureTask 既可以当做一个任务执行，也可以有返回值。

```java
public class FutureTask<V> implements RunnableFuture<V>

```

```java
public interface RunnableFuture<V> extends Runnable, Future<V>

```

**当一个计算任务需要执行很长时间，那么就可以用 FutureTask 来封装这个任务，用一个线程去执行该任务，然后其它线程继续执行其它任务。当需要该任务的计算结果时，再通过 FutureTask 的 get() 方法获取。**

```java
public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(10);
                    result += i;
                }
                return result;
            }
        });

        Thread computeThread = new Thread(futureTask);
        computeThread.start();

        Thread otherThread = new Thread(() -> {
            System.out.println("other task is running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        otherThread.start();
        System.out.println(futureTask.get());
    }
}

```

```java
other task is running...
4950

```

#### 2. BlockingQueue

java.util.concurrent.BlockingQueue 接口有以下阻塞队列的实现：

* **FIFO 队列** ：LinkedBlockingQueue、ArrayListBlockingQueue（固定长度）

* **优先级队列（每个元素都有优先级）** ：PriorityBlockingQueue

提供了阻塞的 take() 和 put() 方法：如果队列为空 take() 将阻塞，直到队列中有内容；如果队列为满 put() 将阻塞，指到队列有空闲位置。

> **使用 BlockingQueue 实现生产者消费者问题**

```java
public class ProducerConsumer {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    private static class Producer extends Thread {
        @Override
        public void run() {
            try {
                queue.put("product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("produce..");
        }
    }

    private static class Consumer extends Thread {

        @Override
        public void run() {
            try {
                String product = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("consume..");
        }
    }
}

```

```java
public static void main(String[] args) {
    for (int i = 0; i < 2; i++) {
        Producer producer = new Producer();
        producer.start();
    }
    for (int i = 0; i < 5; i++) {
        Consumer consumer = new Consumer();
        consumer.start();
    }
    for (int i = 0; i < 3; i++) {
        Producer producer = new Producer();
        producer.start();
    }
}

```

```java
produce..produce..consume..consume..produce..consume..produce..consume..produce..consume..

```

#### 3. ArrayBlockingQueue, LinkedBlockingQueue, ConcurrentLinkedQueue

> 都是线程安全的，不然叫什么并发类呢

ArrayBlockingQueue, LinkedBlockingQueue 继承自 BlockingQueue, 他们的特点就是 Blocking, Blocking 特有的方法就是 take() 和 put(), 这两个方法是阻塞方法, 每当队列容量满的时候, put() 方法就会进入 wait, 直到队列空出来, 而每当队列为空时, take() 就会进入等待, 直到队列有元素可以 take()

**ArrayBlockingQueue, LinkedBlockingQueue 区别在于：**

> 链表和数组性质决定的

* ArrayBlockingQueue 必须指定容量,

* 公平读取: ArrayBlockingQueue 可以指定 fair 变量, 如果 fair 为 true, 则会保持 take() 或者 put() 操作时线程的 block 顺序, 先 block 的线程先 take() 或 put(), fair 由内部变量 ReentrantLock 保证

**ConcurrentLinkedQueue** 通过 CAS 操作实现了**无锁的 poll() 和 offer()**,

* 他的容量是动态的,

* 由于无锁, 所以在 poll() 或者 offer() 的时候 head 与 tail 可能会改变, 所以它会持续的判断 head 与 tail 是否改变来保证操作正确性, 如果改变, 则会重新选择 head 与 tail.

* 而由于无锁的特性, 他的元素更新与 size 变量更新无法做到原子 (实际上它没有 size 变量), 所以他的 size() 是通过遍历 queue 来获得的, 在效率上是 O(n), 而且无法保证准确性, 因为遍历的时候有可能 queue size 发生了改变。

#### 4. ForkJoin

除了 ScheduledThreadPoolExecutor 和 ThreadPoolExecutor 类线程池以外，还有一个是 JDK1.7 新增的线程池：ForkJoinPool 线程池

主要用于并行计算中，和 MapReduce 原理类似，都是把大的计算任务拆分成多个小任务并行计算。

```java
public class ForkJoinExample extends RecursiveTask<Integer> {
    private final int threhold = 5;
    private int first;
    private int last;

    public ForkJoinExample(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threhold) {
            // 任务足够小则直接计算
            for (int i = first; i <= last; i++) {
                result += i;
            }
        } else {
            // 拆分成小任务
            int middle = first + (last - first) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(first, middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle + 1, last);
            leftTask.fork();
            rightTask.fork();
            result = leftTask.join() + rightTask.join();
        }
        return result;
    }
}

```

```java
public static void main(String[] args) throws ExecutionException, InterruptedException {
    ForkJoinExample example = new ForkJoinExample(1, 10000);
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    Future result = forkJoinPool.submit(example);
    System.out.println(result.get());
}

```

**ForkJoin 使用 ForkJoinPool 来启动，它是一个特殊的线程池，线程数量取决于 CPU 核数。**

```java
public class ForkJoinPool extends AbstractExecutorService

```

ForkJoinPool 实现了工作窃取算法来提高 CPU 的利用率。每个线程都维护了一个双端队列，用来存储需要执行的任务。工作窃取算法允许空闲的线程从其它线程的双端队列中窃取一个任务来执行。**窃取的任务必须是最晚的任务，避免和队列所属线程发生竞争**。例如下图中，Thread2 从 Thread1 的队列中拿出最晚的 Task1 任务，Thread1 会拿出 Task2 来执行，这样就避免发生竞争。**但是如果队列中只有一个任务时还是会发生竞争。**

![https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoLVZicYnWuCb1hKwE9OtFic1BQDcia4zrv8ND8e19gQAZFf7ssB41f2vGGw/640?wx_fmt=png](https://mmbiz.qpic.cn/mmbiz_png/qm3R3LeH8rYKtkxtWsWRBHyvibbuLwjoLVZicYnWuCb1hKwE9OtFic1BQDcia4zrv8ND8e19gQAZFf7ssB41f2vGGw/640?wx_fmt=png)

## 8. 线程不安全示例

如果多个线程对同一个共享数据进行访问而不采取同步操作的话，那么操作的结果是不一致的。

以下代码演示了 1000 个线程同时对 cnt 执行自增操作，操作结束之后它的值有可能小于 1000。

```java
public class ThreadUnsafeExample {

    private int cnt = 0;

    public void add() {
        cnt++;
    }

    public int get() {
        return cnt;
    }
}

```

```java
public static void main(String[] args) throws InterruptedException {
    final int threadSize = 1000;
    ThreadUnsafeExample example = new ThreadUnsafeExample();
    final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < threadSize; i++) {
        executorService.execute(() -> {
            example.add();
            countDownLatch.countDown();
        });
    }
    countDownLatch.await();
    executorService.shutdown();
    System.out.println(example.get());
}

```

```java
997

```

## 9. Java 内存模型

**Java 内存模型试图屏蔽各种硬件和操作系统的内存访问差异，以实现让 Java 程序在各种平台下都能达到一致的内存访问效果。**

### 1. 主内存与工作内存

处理器上的寄存器的读写的速度比内存快几个数量级，为了解决这种速度矛盾，在它们之间加入了高速缓存。

加入高速缓存带来了一个新的问题：缓存一致性。如果多个缓存共享同一块主内存区域，那么多个缓存的数据可能会不一致，需要一些协议来解决这个问题。

![内存](https://img-blog.csdnimg.cn/20190201144151328.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

所有的变量都存储在主内存中，每个线程还有自己的工作内存，工作内存存储在高速缓存或者寄存器中，保存了该线程使用的变量的主内存副本拷贝。

线程只能直接操作工作内存中的变量，不同线程之间的变量值传递需要通过主内存来完成。

![内存](https://img-blog.csdnimg.cn/20190201144158698.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

### 2. 内存间交互操作

Java 内存模型定义了 8 个操作来完成**主内存和工作内存的交互操作**。

![内存交互](https://img-blog.csdnimg.cn/2019020114420688.png)

* read：把一个变量的值从主内存传输到工作内存中
* load：在 read 之后执行，把 read 得到的值放入工作内存的变量副本中
* use：把工作内存中一个变量的值传递给执行引擎
* assign：把一个从执行引擎接收到的值赋给工作内存的变量
* store：把工作内存的一个变量的值传送到主内存中
* write：在 store 之后执行，把 store 得到的值放入主内存的变量中
* lock：作用于主内存的变量
* unlock

### 3. 内存模型三大特性

#### 1. 原子性

Java 内存模型保证了 read、load、use、assign、store、write、lock 和 unlock 操作具有原子性

Java 内存模型保证了 read、load、use、assign、store、write、lock 和 unlock 操作具有原子性，例如对一个 int 类型的变量执行 assign 赋值操作，这个操作就是原子性的。但是 Java 内存模型允许虚拟机将没有被 volatile 修饰的 64 位数据（long，double）的读写操作划分为两次 32 位的操作来进行，即 load、store、read 和 write 操作可以不具备原子性。

有一个错误认识就是，int 等原子性的类型在多线程环境中不会出现线程安全问题。前面的线程不安全示例代码中，cnt 属于 int 类型变量，1000 个线程对它进行自增操作之后，得到的值为 997 而不是 1000。

##### 原子类

![原子类](https://img-blog.csdnimg.cn/20190201144229174.png)

使用 AtomicInteger 重写之前线程不安全的代码之后得到以下线程安全实现：

```java
public class AtomicExample {
    private AtomicInteger cnt = new AtomicInteger();

    public void add() {
        cnt.incrementAndGet();
    }

    public int get() {
        return cnt.get();
    }
}

```

```java
public static void main(String[] args) throws InterruptedException {
    final int threadSize = 1000;
    AtomicExample example = new AtomicExample(); // 只修改这条语句
    final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < threadSize; i++) {
        executorService.execute(() -> {
            example.add();
            countDownLatch.countDown();
        });
    }
    countDownLatch.await();
    executorService.shutdown();
    System.out.println(example.get());
}

```

```java
1000

```

##### synchronized

除了使用原子类之外，也可以使用 synchronized 互斥锁来保证操作的原子性。它对应的内存间交互操作为：lock 和 unlock，在虚拟机实现上对应的字节码指令为 monitorenter 和 monitorexit。

```java

public class AtomicSynchronizedExample {
    private int cnt = 0;

    public synchronized void add() {
        cnt++;
    }

    public synchronized int get() {
        return cnt;
    }
}

```

```java
public static void main(String[] args) throws InterruptedException {
    final int threadSize = 1000;
    AtomicSynchronizedExample example = new AtomicSynchronizedExample();
    final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < threadSize; i++) {
        executorService.execute(() -> {
            example.add();
            countDownLatch.countDown();
        });
    }
    countDownLatch.await();
    executorService.shutdown();
    System.out.println(example.get());
}

```

```java
1000

```

#### 2. 可见性

**可见性指当一个线程修改了共享变量的值，其它线程能够立即得知这个修改**。

Java 内存模型是通过**在变量修改后将新值同步回主内存**，**在变量读取前从主内存刷新变量值**来实现可见性的。

主要有有三种实现可见性的方式：

* **volatile**：仅仅用来保证该变量对所有线程的可见性，但不保证原子性。
* **synchronized，对一个变量执行 unlock 操作之前，必须把变量值同步回主内存。**
* **final**，被 final 关键字修饰的字段在构造器中一旦初始化完成，并且没有发生 this 逃逸（其它线程通过 this 引用访问到初始化了一半的对象），那么其它线程就能看见 final 字段的值。

#### 3. 有序性

有序性是指：**在本线程内观察，所有操作都是有序的**。**在一个线程观察另一个线程，所有操作都是无序的，无序是因为发生了指令重排序。**

在 Java 内存模型中，允许编译器和处理器对指令进行重排序，重排序过程不会影响到单线程程序的执行，却会影响到多线程并发执行的正确性。

* **volatile 关键字通过添加内存屏障的方式来禁止指令重排，即重排序时不能把后面的指令放到内存屏障之前。**

* **可以通过 synchronized 来保证有序性，它保证每个时刻只有一个线程执行同步代码，相当于是让线程顺序执行同步代码。**

##### happens-before

[https://blog.csdn.net/qq_30137611/article/details/78146864](https://blog.csdn.net/qq_30137611/article/details/78146864)

**happens-before 是判断数据是否存在竞争、线程是否安全的重要依据**
定义：如果操作 A happens-before 于 操作 B，那么就可以确定，操作 B 执行完之后，j 的值一定为 1；因为 happens-before 关系可以向程序员保证：在操作 B 执行之前，操作 A 的执行后的影响 [或者说结果]（修改 i 的值）操作 B 是可以观察到的 [或者说可见的]

这里列举几个常见的 Java“天然的”happens-before 关系

① **程序顺序规则**： 一个线程中的每个操作，happens-before 于该线程中的任意后续操作（也就是说你写的操作，如果是单线程执行，那么前面的操作 [程序逻辑上的前] 就会 happens-before 于后面的操作）  
这里的影响指修改了 i 变量的值

② **监视器锁规则**： 对一个锁的解锁，happens-before 于随后对这个锁的加锁

③ **volatile 变量规则**： 对一个 volatile 域的写，happens-before 于任意后续对这个 volatile 域的读

④ 传递性：如果 A happens-before B, 且 B happens-before C, 那么 A happens-before C

![happens-before](https://img-blog.csdnimg.cn/20190201144253282.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

#### 总结

* 保证原子性的操作：
  * read、load、assign、use、store 和 write（自身具有原子性）
  * 原子类
  * synchronized 锁
* 保证可见性：
  * volatile
  * synchronized 锁
  * final
* 保证有序性 (重排序导致无序) 的操作：
  * volatile
  * synchronized 锁

## 10. 先行发生原则

上面提到了可以用 volatile 和 synchronized 来保证有序性。**除此之外，JVM 还规定了先行发生原则，让一个操作无需控制就能先于另一个操作完成。**

主要有以下这些原则：

### 1. 单一线程原则

> Single Thread rule

在一个线程内，在程序前面的操作先行发生于后面的操作。

### 2. 管程锁定规则

> Monitor Lock Rule

一个 unlock 操作先行发生于后面对同一个锁的 lock 操作。

### 3. volatile 变量规则

> Volatile Variable Rule

对一个 volatile 变量的写操作先行发生于后面对这个变量的读操作。

### 4. 线程启动规则

> Thread Start Rule

Thread 对象的 start() 方法调用先行发生于此线程的每一个动作。

### 5. 线程加入规则

> Thread Join Rule

Thread 对象的结束先行发生于 join() 方法返回。

### 6. 线程中断规则

> Thread Interruption Rule

对线程 interrupt() 方法的调用先行发生于被中断线程的代码检测到中断事件的发生，可以通过 interrupted() 方法检测到是否有中断发生。

### 7. 对象终结规则

> Finalizer Rule

一个对象的初始化完成（构造函数执行结束）先行发生于它的 finalize() 方法的开始。

### 8. 传递性

> Transitivity

如果操作 A 先行发生于操作 B，操作 B 先行发生于操作 C，那么操作 A 先行发生于操作 C。

## 11. ThreadLocal/Volatile/Synchronized/Atomic 横向对比

[https://blog.csdn.net/u010687392/article/details/50549236](https://blog.csdn.net/u010687392/article/details/50549236)

## 12. Atomic 原子性

内部实现**采用 Lock-Free 算法替代锁，加上原子操作指令实现并发情况下资源的安全、完整、一致性**，而关于 Lock-Free 算法，则是一种新的策略替代锁来保证资源在并发时的完整性的，Lock-Free 的实现有三步：

```txt
1、循环（for(;;)、while）
2、CAS（CompareAndSet）
3、回退（return、break）

```

## 13. volatile 可见性 有序性

[https://www.jianshu.com/p/195ae7c77afe](https://www.jianshu.com/p/195ae7c77afe)
**通过关键字 sychronize 可以防止多个线程进入同一段代码，在某些特定场景中，volatile 相当于一个轻量级的 sychronize，因为不会引起线程的上下文切换**

为何具有可见性

* 对于普通变量

  * 读操作会优先读取工作内存的数据，如果工作内存中不存在，则**从主内存中拷贝一份数据到工作内存中**；
  * 写操作只会修改工作内存的副本数据，这种情况下，**其它线程就无法读取变量的最新值**。

* 对于 volatile 变量

  * **读操作**时 JMM 会把**工作内存中对应的值设为无效**，**要求线程从主内存中读取数据**；
  * **写操作**时 JMM 会把**工作内存中对应的数据刷新到主内存中**，这种情况下，其它线程就可以读取变量的最新值。

为何具有有序性（内存屏障）

内存屏障，又称内存栅栏，是一个 CPU 指令。在程序运行时，为了提高执行性能，编译器和处理器会对指令进行重排序

**JMM 为了保证在不同的编译器和 CPU 上有相同的结果**，通过插入特定类型的内存屏障来**禁止特定类型的编译器重排序和处理器重排序**，插入一条内存屏障会告诉编译器和 CPU：不管什么指令都不能和这条 Memory Barrier 指令重排序。

### 满足下面的条件才应该使用 volatile 修饰变量

一般来说，volatile 大多**用于标志位上 (判断操作),**

* 修改变量时**不依赖变量的当前值** (因为 volatile 是不保证原子性的)
* 该变量不会纳入到不变性条件中 (**该变量是可变的**)
* 在访问变量的时候**不需要加锁** (加锁就没必要使用 volatile 这种轻量级同步机制了)

## 14. synchronized 全能

但是由于操作上的优势，只需要简单的声明一下即可，而且**被它声明的代码块也是具有操作的原子性。**

ThreadLocal
-----------

ThreadLocal 提供了线程的局部变量，每个线程都可以通过 set() 和 get() 来对这个局部变量进行操作，但不会和其他线程的局部变量进行冲突，实现了线程的数据隔离。

而 ThreadLocal 的设计，**并不是解决资源共享的问题**，而是用来**提供线程内的局部变量**，这样每个线程都自己管理自己的局部变量，别的线程操作的数据不会对我产生影响，相当于封装在 Thread 内部了，供线程自己管理。

### 用法

它有三个暴露的方法，set、get、remove。

### 内部实现

*   **每个 Thread 维护着一个 ThreadLocalMap 的引用**
*   ThreadLocalMap 是 ThreadLocal 的内部类，用 Entry 来进行存储
*   调用 ThreadLocal 的 set() 方法时，实际上就是往 ThreadLocalMap 设置值，key 是 ThreadLocal 对象，值是传递进来的对象
*   调用 ThreadLocal 的 get() 方法时，实际上就是往 ThreadLocalMap 获取值，key 是 ThreadLocal 对象
*   **ThreadLocal 本身并不存储值，它只是作为一个 key 来让线程从 ThreadLocalMap 获取 value**。

![](https://img-blog.csdnimg.cn/20190201144305647.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

### 内存泄漏

如果 ThreadLocal 不设为 static 的，由于 Thread 的生命周期不可预知，这就导致了当系统 gc 时将会回收它，而 **ThreadLocal 对象被回收了，此时它对应 key 必定为 null，这就导致了该 key 对应得 value 拿不出来了**，**而 value 之前被 Thread 所引用，所以就存在 key 为 null、value 存在强引用导致这个 Entry 回收不了，从而导致内存泄露。**

避免内存泄露的方法:

*   ThreadLocal **要设为 static 静态的**
*   **必须手动 remove 掉该 ThreadLocal 的值**，这样 Entry 就能够在系统 gc 的时候正常回收，而关于 ThreadLocalMap 的回收，会在当前 Thread 销毁之后进行回收。

### 使用场景

*   管理数据库的 Connection

threadLocal 能够实现当前线程的操作都是用同一个 Connection，保证了事务！

*   避免一些参数传递

总结
--

**关于 Volatile 关键字具有可见性，但不具有操作的原子性，而 synchronized 比 volatile 对资源的消耗稍微大点，但可以保证变量操作的原子性，保证变量的一致性，最佳实践则是二者结合一起使用。**

1、synchronized：解决多线程资源共享的问题，同步机制采用了 “以时间换空间” 的方式：**访问串行化，对象共享化**。同步机制是提供一份变量，让所有线程都可以访问。

2、对于 Atomic 的出现，是**通过原子操作指令 + Lock-Free** 完成，从而实现**非阻塞式的并发问题**。

3、对于 Volatile，为多线程资源共享问题解决了部分需求，在非依赖自身的操作的情况下，**对变量的改变将对任何线程可见。**

4、对于 ThreadLocal 的出现，并不是解决多线程资源共享的问题，而是用来提供线程内的局部变量，省去参数传递这个不必要的麻烦，ThreadLocal 采用了 “以空间换时间” 的方式：**访问并行化，对象独享化**。ThreadLocal 是为每一个线程都提供了一份独有的变量，各个线程互不影响。

线程安全类
=====

> 等待 IO 的方式：阻塞，非阻塞
> 
> 获得通知的方式：异步，非异步

多个线程不管以何种方式访问某个类，并且在主调代码中不需要进行同步，都能表现正确的行为。

**线程安全有以下几种实现方式：**

不可变
---

不可变（Immutable）的对象一定是线程安全的，不需要再采取任何的线程安全保障措施。只要一个不可变的对象被正确地构建出来，永远也不会看到它在多个线程之中处于不一致的状态。多线程环境下，应当尽量使对象成为不可变，来满足线程安全。

不可变的类型：

*   final 关键字修饰的基本数据类型
*   String
*   枚举类型
*   Number 部分子类，如 Long 和 Double 等数值包装类型，BigInteger 和 BigDecimal 等大数据类型。但同为 Number 的原子类 AtomicInteger 和 AtomicLong 则是可变的。

对于集合类型，可以使用 Collections.unmodifiableXXX() 方法来获取一个不可变的集合。

```
public class ImmutableExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);
        unmodifiableMap.put("a", 1);
    }
}


```

```
Exception in thread "main" java.lang.UnsupportedOperationException
    at java.util.Collections$UnmodifiableMap.put(Collections.java:1457)
    at ImmutableExample.main(ImmutableExample.java:9)


```

Collections.unmodifiableXXX() 先对原始的集合进行拷贝，需要对集合进行修改的方法都直接抛出异常。

```
public V put(K key, V value) {
    throw new UnsupportedOperationException();
}


```

互斥同步
----

synchronized 和 ReentrantLock。

非阻塞同步
-----

互斥同步最主要的问题就是进行线程阻塞和唤醒所带来的性能问题，因此这种同步也称为阻塞同步（Blocking Synchronization）。

从处理问题的方式上说，互斥同步**属于一种悲观的并发策略**，总是认为只要不去做正确的同步措施（例如加锁），那就肯定会出现问题。

随着硬件指令集的发展，我们有了另外一个选择：基于冲突检测的**乐观并发策略**，通俗地说，就是先进行操作，如果没有其他线程争用共享数据，那操作就成功了；**如果共享数据有争用，产生了冲突，那就再采取其他的补偿措施（最常见的补偿措施就是不断地重试，直到成功为止）**，这种乐观的并发策略的许多实现都不需要把线程挂起，因此这种同步操作称为非阻塞同步（Non-Blocking Synchronization）。

乐观锁需要**操作**和**冲突检测**这两个步骤具备**原子性**，这里就不能再使用互斥同步来保证了，只能靠硬件来完成。

### 1. CAS

**硬件支持的原子性操作最典型的是：比较并交换（Compare-and-Swap，CAS）。**

CAS 指令需要有 3 个操作数，分别是：

*   内存位置（在 Java 中可以简单理解为变量的内存地址，用 V 表示）
*   旧的预期值（用 A 表示）
*   新值（用 B 表示）。

**当且仅当预期值 A 和内存值 V 相同时，将内存值 V 修改为 B，否则什么都不做。**

**但是无论是否更新了 V 的值，都会返回 V 的旧值，上述的处理过程是一个原子操作。**

当多个线程尝试使用 CAS 同时更新同一个变量时，只有其中一个线程能更新变量的值 (A 和内存值 V 相同时，将内存值 V 修改为 B)，**而其它线程都失败，失败的线程并不会被挂起，而是被告知这次竞争中失败，并可以再次尝试 (否则什么都不做)**

> J.U.C 包里面的整数原子类 AtomicInteger，其中的 compareAndSet() 和 getAndIncrement() 等方法都使用了 Unsafe 类的 CAS 操作。

### 2. AtomicInteger

J.U.C 包里面的整数原子类 AtomicInteger 的方法调用了 Unsafe 类的 CAS 操作。

以下代码使用了 AtomicInteger 执行了自增的操作。

```
private AtomicInteger cnt = new AtomicInteger();

public void add() {
    cnt.incrementAndGet();
}


```

以下代码是 incrementAndGet() 的源码，它调用了 Unsafe 的 getAndAddInt() 。

```
public final int incrementAndGet() {
    return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
}


```

以下代码是 getAndAddInt() 源码，var1 指示对象内存地址，var2 指示该字段相对对象内存地址的偏移，var4 指示操作需要加的数值，这里为 1。通过 getIntVolatile(var1, var2) 得到旧的预期值，通过调用 compareAndSwapInt() 来进行 CAS 比较，如果该字段内存地址中的值等于 var5，那么就更新内存地址为 var1+var2 的变量为 var5+var4。

可以看到 getAndAddInt() 在一个循环中进行，发生冲突的做法是不断的进行重试。

```
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        var5 = this.getIntVolatile(var1, var2);
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

    return var5;
}


```

### 3. ABA

如果一个变量初次读取的时候是 A 值，它的值被改成了 B，后来又被改回为 A，那 CAS 操作就会误认为它从来没有被改变过。

J.U.C 包提供了一个带有标记的原子引用类 “AtomicStampedReference” 来解决这个问题，**它可以通过控制变量值的版本来保证 CAS 的正确性**。大部分情况下 ABA 问题不会影响程序并发的正确性，如果需要解决 ABA 问题，改用传统的互斥同步可能会比原子类更高效。

无同步方案
-----

要保证线程安全，并不是一定就要进行同步。如果一个方法本来就不涉及共享数据，那它自然就无须任何同步措施去保证正确性。

### 1. 栈封闭

多个线程访问同一个方法的局部变量时，不会出现线程安全问题，因为局部变量存储在虚拟机栈中，属于线程私有的。

```
public class StackClosedExample {
    public void add100() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            cnt++;
        }
        System.out.println(cnt);
    }
}


```

```
public static void main(String[] args) {
    StackClosedExample example = new StackClosedExample();
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(() -> example.add100());
    executorService.execute(() -> example.add100());
    executorService.shutdown();
}


```

```
100
100


```

### 2. 线程本地存储（Thread Local Storage）

如果一段代码中所需要的数据必须与其他代码共享，那就看看这些共享数据的代码是否能保证在同一个线程中执行。如果能保证，我们就可以把共享数据的可见范围限制在同一个线程之内，这样，无须同步也能保证线程之间不出现数据争用的问题。

符合这种特点的应用并不少见，大部分使用消费队列的架构模式（如 “生产者 - 消费者” 模式）都会将产品的消费过程尽量在一个线程中消费完。其中最重要的一个应用实例就是经典 Web 交互模型中的“一个请求对应一个服务器线程”（Thread-per-Request）的处理方式，这种处理方式的广泛应用使得很多 Web 服务端应用都可以使用线程本地存储来解决线程安全问题。

可以使用 java.lang.ThreadLocal 类来实现线程本地存储功能。

对于以下代码，thread1 中设置 threadLocal 为 1，而 thread2 设置 threadLocal 为 2。过了一段时间之后，thread1 读取 threadLocal 依然是 1，不受 thread2 的影响。

```
public class ThreadLocalExample {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        Thread thread1 = new Thread(() -> {
            threadLocal.set(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
            threadLocal.remove();
        });
        Thread thread2 = new Thread(() -> {
            threadLocal.set(2);
            threadLocal.remove();
        });
        thread1.start();
        thread2.start();
    }
}


```

```
1


```

为了理解 ThreadLocal，先看以下代码：

```
public class ThreadLocalExample1 {
    public static void main(String[] args) {
        ThreadLocal threadLocal1 = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        Thread thread1 = new Thread(() -> {
            threadLocal1.set(1);
            threadLocal2.set(1);
        });
        Thread thread2 = new Thread(() -> {
            threadLocal1.set(2);
            threadLocal2.set(2);
        });
        thread1.start();
        thread2.start();
    }
}


```

每个 Thread 都有一个 ThreadLocal.ThreadLocalMap 对象。

```
/* ThreadLocal values pertaining to this thread. This map is maintained
 * by the ThreadLocal class. */
ThreadLocal.ThreadLocalMap threadLocals = null;


```

当调用一个 ThreadLocal 的 set(T value) 方法时，先得到当前线程的 ThreadLocalMap 对象，然后将 ThreadLocal->value 键值对插入到该 Map 中。

```
public void set(T value) {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
}


```

get() 方法类似。

```
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}


```

ThreadLocal 从理论上讲并不是用来解决多线程并发问题的，因为根本不存在多线程竞争。

在一些场景 (尤其是使用线程池) 下，由于 ThreadLocal.ThreadLocalMap 的底层数据结构导致 ThreadLocal 有内存泄漏的情况，应该尽可能在每次使用 ThreadLocal 后手动调用 remove()，以避免出现 ThreadLocal 经典的内存泄漏甚至是造成自身业务混乱的风险。

### 3. 可重入代码（Reentrant Code）

这种代码也叫做纯代码（Pure Code），可以在代码执行的任何时刻中断它，转而去执行另外一段代码（包括递归调用它本身），而在控制权返回后，原来的程序不会出现任何错误。

可重入代码有一些共同的特征，例如不依赖存储在堆上的数据和公用的系统资源、用到的状态量都由参数中传入、不调用非可重入的方法等。

锁优化
===

这里的锁优化主要是指**虚拟机对 synchronized 的优化。**

**锁竞争是 kernal mode 下的，会经过 user mode(用户态) 到 kernal mode(内核态) 的切换，是比较花时间的。**

自旋锁
---

自旋锁的思想是让一个线程在请求一个共享数据的锁时执行忙循环（自旋）一段时间，如果在这段时间内能获得锁，就可以避免进入阻塞状态。

它只适用于**共享数据的锁定状态很短的场景**。

**自旋次数的默认值是 10 次**，用户可以使用虚拟机参数 -XX:PreBlockSpin 来更改。

**在 JDK 1.6 中引入了自适应的自旋锁**。自适应意味着自旋的次数不再固定了，而是由**前一次在同一个锁上的自旋次数**及**锁的拥有者的状态来决定。**

锁消除
---

锁消除是**指对于被检测出不可能存在竞争的共享数据的锁进行消除**。检测到某段代码是线程安全的 (言外之意：无锁也是安全的)，JVM 会安全地原有的锁消除掉！

**逃逸分析**: 如果堆上的共享数据**不可能逃逸出去被其它线程访问到**，那么就可以把它们当成私有数据对待，也就可以将它们上的锁进行消除。

对于一些看起来没有加锁的代码，其实隐式的加了很多锁。例如下面的字符串拼接代码就隐式加了锁：

```
public static String concatString(String s1, String s2, String s3) {
    return s1 + s2 + s3;
}


```

String 是一个不可变的类，Javac 编译器会对 String 的拼接自动优化。在 JDK 1.5 之前，会转化为 StringBuffer 对象的连续 append() 操作，在 JDK 1.5 及以后的版本中，会转化为 StringBuilder 对象的连续 append() 操作，即上面的代码可能会变成下面的样子：

```
public static String concatString(String s1, String s2, String s3) {
    StringBuffer sb = new StringBuffer();
    sb.append(s1);
    sb.append(s2);
    sb.append(s3);
    return sb.toString();
}


```

虚拟机观察变量 sb，很快就会发现它的动态作用域被限制在 concatString() 方法内部。也就是说，sb 的所有引用永远不会 “逃逸” 到 concatString() 方法之外，其他线程无法访问到它。因此，虽然这里有锁，但是可以被安全地消除掉。

锁粗化
---

如果一系列的连续操作都对同一个对象反复加锁和解锁，频繁的加锁操作就会导致性能损耗。

上一节的示例代码中连续的 append() 方法就属于这类情况。**如果虚拟机探测到由这样的一串零碎的操作都对同一个对象加锁，将会把加锁的范围扩展（粗化）到整个操作序列的外部**。对于上一节的示例代码就是扩展到第一个 append() 操作之前直至最后一个 append() 操作之后，这样只需要加锁一次就可以了。

但是如果一系列的连续操作都对同一个对象反复加锁和解锁，甚至加锁操作是出现在循环体中的，频繁地进行互斥同步操作也会导致不必要的性能损耗。

偏向锁
---

**总结：在无竞争环境下，把整个同步都消除，CAS 也不做。**

**偏向锁的思想是偏向于让第一个获取锁对象的线程，这个线程在之后获取该锁就不再需要进行同步操作，甚至连 CAS 操作也不再需要。**

可以使用 -XX:+UseBiasedLocking=true 开启偏向锁，不过在 JDK 1.6 中它是默认开启的。

当锁对象第一次被线程获得的时候，进入偏向状态，标记为 1 01。同时使用 CAS 操作将线程 ID 记录到 Mark Word 中，**如果 CAS 操作成功，这个线程以后每次进入这个锁相关的同步块就不需要再进行任何同步操作。**

**当有另外一个线程去尝试获取这个锁对象时，偏向状态就宣告结束，此时撤销偏向（Revoke Bias）后恢复到未锁定状态或者轻量级锁状态。**

轻量级锁
----

**轻量级锁是相对于传统的重量级锁而言**，它使用 CAS 操作来避免重量级锁使用互斥量的开销。对于绝大部分的锁，在整个同步周期内都是不存在竞争的，因此也就不需要都使用互斥量进行同步，**可以先采用 CAS 操作进行同步，如果 CAS 失败了再改用互斥量进行同步。（乐观锁）**

JDK 1.6 引入了**偏向锁**和**轻量级锁**，从而让锁拥有了四个状态：**无锁状态（unlocked）、偏向锁状态（biasble）、轻量级锁状态（lightweight locked）和重量级锁状态（inflated）。**

如果 CAS 操作失败了，虚拟机首先会检查对象的 Mark Word 是否指向当前线程的虚拟机栈，如果是的话说明当前线程已经拥有了这个锁对象，那就可以直接进入同步块继续执行，否则说明这个锁对象已经被其他线程线程抢占了。如果有两条以上的线程争用同一个锁，**那轻量级锁就不再有效，要膨胀为重量级锁。**

**但如果存在锁竞争，除了互斥量的开销外，还额外发生了 CAS 操作，因此在有竞争的情况下，轻量级锁会比传统的重量级锁更慢。**

**简单来说：如果发现同步周期内都是不存在竞争，JVM 会使用 CAS 操作来替代操作系统互斥量。这个优化就被叫做轻量级锁。**

多线程开发良好的实践
==========

*   **缩小同步范围**，例如对于 synchronized，应该尽量使用同步块而不是同步方法。
    
*   **多用同步类少用 wait() 和 notify()**, 多用 CountDownLatch, Semaphore, CyclicBarrier 和 Exchanger 这些同步类。他们简化了编码操作，而用 wait() 和 notify() 很难实现对复杂控制流的控制。其次，这些类是由最好的企业编写和维护，在后续的 JDK 中它们还会不断优化和完善，使用这些更高等级的同步工具你的程序可以不费吹灰之力获得优化。
    
*   **多用并发集合少用同步集合。**
    
*   使用**本地变量 ThreadLocal 和不可变类**来保证线程安全。
    
*   **使用线程池**而不是直接创建 Thread 对象，这是因为创建线程代价很高，线程池可以有效地利用有限的线程来启动任务。
    
*   使用 **BlockingQueue** 实现生产者消费者问题。
    

补充经典并发集合和同步集合参考
---------------

[https://www.cnblogs.com/suneryong/p/6726413.html](https://www.cnblogs.com/suneryong/p/6726413.html)

不管是同步集合还是并发集合他们都支持线程安全，他们之间主要的区别体现在**性能和可扩展性**，还有他们如何实现的线程安全。同步 HashMap, Hashtable, HashSet, Vector, ArrayList 相比他们并发的实现（比如：ConcurrentHashMap, CopyOnWriteArrayList, CopyOnWriteHashSet）会慢得多。造成如此慢的主要原因是锁， 同步集合会把整个 Map 或 List 锁起来，而并发集合不会。**并发集合实现线程安全是通过使用先进的和成熟的技术像锁剥离。比如 ConcurrentHashMap 会把整个 Map 划分成几个片段，只对相关的几个片段上锁，同时允许多线程访问其他未上锁的片段。**

> java.util.concurrent 包中包含的并发集合类如下：

```
ConcurrentHashMap

CopyOnWriteArrayList

CopyOnWriteArraySet


```

对象的发布与逸出
========

*   发布 (publish) 使对象能够在当前作用域之外的代码中使用
*   逸出 (escape) 当某个不应该发布的对象被发布了

常见逸出的有下面几种方式：

*   静态域逸出
*   public 修饰的 get 方法
*   方法参数传递
*   隐式的 this

具体解释见：[https://segmentfault.com/a/1190000014546223#articleHeader4](https://segmentfault.com/a/1190000014546223#articleHeader4)

**安全发布对象有几种常见的方式：**

*   在静态域中直接初始化 ： public static Person = new Person();
    *   静态初始化由 JVM 在类的初始化阶段就执行了，JVM 内部存在着同步机制，致使这种方式我们可以安全发布对象
*   对应的引用保存到 volatile 或者 AtomicReferance 引用中
    *   保证了该对象的引用的可见性和原子性
*   由 final 修饰
    *   该对象是**不可变的**
*   由锁来保护
    *   发布和使用的时候都需要**加锁**

Java 线程锁
========

[https://segmentfault.com/a/1190000014747667#articleHeader5](https://segmentfault.com/a/1190000014747667#articleHeader5)

避免死锁的方法
-------

### 固定锁顺序避免死锁

上面 transferMoney() 发生死锁的原因是因为加锁顺序不一致而出现的~

*   如果所有线程以固定的顺序来获得锁，那么程序中就不会出现锁顺序死锁问题！

例子中，改造为得到对应的 hash 值来固定加锁的顺序，这样我们就不会发生死锁的问题了！

### 开放调用避免死锁

如果在调用某个方法时不需要持有锁，那么这种调用被称为开放调用！

### 使用定时锁

使用显式 Lock 锁，在获取锁时使用 tryLock() 方法。当等待超过时限的时候，tryLock() 不会一直等待，而是返回错误信息。

关注我
===

我是蛮三刀把刀，目前为后台开发工程师。主要关注后台开发，网络安全，Python 爬虫等技术。

来微信和我聊聊：yangzd1102

Github：[https://github.com/qqxx6661](https://github.com/qqxx6661)

### 原创博客主要内容

*   笔试面试复习知识点手册
*   Leetcode 算法题解析（前 150 题）
*   剑指 offer 算法题解析
*   Python 爬虫相关技术分析和实战
*   后台开发相关技术分析和实战

**同步更新以下博客**

**1. Csdn**

[http://blog.csdn.net/qqxx6661](http://blog.csdn.net/qqxx6661)

拥有专栏：Leetcode 题解（Java/Python）、Python 爬虫开发

**2. 知乎**

[https://www.zhihu.com/people/yang-zhen-dong-1/](https://www.zhihu.com/people/yang-zhen-dong-1/)

拥有专栏：码农面试助攻手册

**3. 掘金**

[https://juejin.im/user/5b48015ce51d45191462ba55](https://juejin.im/user/5b48015ce51d45191462ba55)

**4. 简书**

[https://www.jianshu.com/u/b5f225ca2376](https://www.jianshu.com/u/b5f225ca2376)

### 个人项目：电商价格监控网站

本人长期维护的个人项目，完全免费，请大家多多支持。

**实现功能**

*   **京东商品监控**：设置商品 ID 和预期价格，当商品价格【低于】设定的预期价格后自动发送邮件提醒用户。(一小时以内)
*   **京东品类商品监控**：用户订阅特定品类后，该类降价幅度大于 7 折的【自营商品】会被选出并发送邮件提醒用户。
*   **品类商品浏览，商品历史价格曲线，商品历史最高最低价**
*   持续更新中…

**网站地址**

[https://pricemonitor.online/](https://pricemonitor.online/)

### 个人公众号：Rude3Knife

![](https://img-blog.csdnimg.cn/20190108201638256.png)

**如果文章对你有帮助，不妨收藏起来并转发给您的朋友们~**