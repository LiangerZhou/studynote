public class Singleton{
    public static void main() {
    
    }
}


/**
 * 懒汉，线程不安全
 * 由私有构造器和一个公有静态工厂方法构成，在工厂方法中对singleton进行null判断，如果是null就new一个出来，最后返回singleton对象
 * 这种方法可以实现延时加载（lazy landing），但是有一个致命弱点：线程不安全。如果有两条线程同时调用getSingleton()方法，就有很大可能导致重复创建对象。
 * Created by gongzi on 2017/2/13.
 */
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}


/**
 * 饿汉法就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建
 * 好处是编写简单，但是无法做到延迟创建对象
 * Created by gongzi on 2017/2/13.
 */
class HungerSingleton {
    private static HungerSingleton instance = new HungerSingleton();

    private HungerSingleton() {
    }

    public static HungerSingleton getInstance() {
        return instance;
    }
}


/**
 * 双重检查锁定
 * 这种写法考虑了线程安全，将对singleton的null判断以及new的部分使用synchronized进行加锁。
 * 同时，对singleton对象使用volatile关键字进行限制，保证其对所有线程的可见性，并且禁止对其进行指令重排序优化。
 * 在单例中new的情况非常少，绝大多数都是可以并行的读操作。因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，执行效率提高的目的也就达到了
 * 注意：双重检查锁法，不能在jdk1.5之前使用，
 * 而在Android平台上使用就比较放心了，一般Android都是jdk1.6以上，不仅修正了volatile的语义问题，还加入了不少锁优化，使得多线程同步的开销降低不少
 * Created by gongzi on 2017/2/13.
 */
class DoubleValidateSingleton {
    private volatile static DoubleValidateSingleton singleton;

    private DoubleValidateSingleton() {
    }

    public static DoubleValidateSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DoubleValidateSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleValidateSingleton();
                }
            }
        }
        return singleton;
    }
}



/**
 * 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，它跟第三种和第四种方式不同的是（很细微的差别）：
 * 第三种和第四种方式是只要Singleton类被装载了，那么instance就会被实例化（没有达到lazy loading效果），
 * 而这种方式是Singleton类被装载了，instance不一定被初始化。
 * 因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance。
 * 想象一下，如果实例化instance很消耗资源，我想让他延迟加载，另外一方面，我不希望在Singleton类加载时就实例化，
 * 因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化instance显然是不合适的。
 * 这个时候，这种方式相比第三和第四种方式就显得很合理。
 * Created by gongzi on 2017/2/13.
 */
class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {
    }

    public static final StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
}



/**
 * 这种方式是[Effective Java - Joshua Bloch](https://www.amazon.cn/dp/B01DPUXUWG/ref=sr_1_2?ie=UTF8&qid=1486966767&sr=8-2&keywords=Effective+Java)提倡的方式，
 * 使用枚举，除了线程安全和防止反射强行调用构造器之外，还提供了自动序列化机制，防止反序列化的时候创建新的对象。
 * 因此，Effective Java 推荐尽可能地使用枚举来实现单例。
 * 注意：枚举，虽然Effective Java中推荐使用，但是在Android平台上却是不被推荐的。
 * <p>
 * Created by gongzi on 2017/2/13.
 */
enum EnumSingleton {
    INSTANCE;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}


