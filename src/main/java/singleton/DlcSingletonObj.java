package singleton;

/**
 * 假设我们现在并没有创建单例对象，即s==null
 * 那么我们调用getInstance方法的时候，会进入if块
 * 然后进入同步代码块，此时，别的线程如果想要创建Single实例
 * 就必须获取锁；等当前线程创建完实例对象，释放锁之后
 * 假设正巧有几个线程已经进入了if块中，它们会拿到锁，进入同步代码块
 * 但是由于进行了判空操作，所以不会创建Single实例
 * 而是直接返回已经创建好的Single实例。如果有多个其他线程进入了if块
 * 当它们依次进入同步代码块的时候，同理也不会创建新的Single实例
 * 而没有进入if块的线程，判空操作之后不满足条件，进不了if块
 * 而直接执行了下一条语句return s；其后的线程调用getInstance方法时
 * 只会判断一次s==null，不满足条件直接返回Single单例s，这样就大大提高了了执行效率。
 *
 * @author xz
 * 标注1是第一次判空操作目的是提高效率
 * 标注3代码是同步代码块的入口，目的是保证线程安全
 * 标注5代码进行第二次判空操作是为了保证单例对象的唯一性
 * <p>
 * 还需要注意的是这里需要加上volatile修饰，否则会拿到一个半实例化的对象
 * 对象的创建过程 new  赋值 指向  可能出现指令重排 导致先指向 还未赋值(只有初始值)
 * 加上volatile禁止指令重排
 */
public class DlcSingletonObj {
    private static volatile DlcSingletonObj dlcSingletonObj;

    private DlcSingletonObj() {

    }

    public static DlcSingletonObj getInstance() {
        if (dlcSingletonObj == null) {//1
            try {
                Thread.sleep(1000);
                synchronized (DlcSingletonObj.class) {//3
                    if (dlcSingletonObj == null) {//5
                        dlcSingletonObj = new DlcSingletonObj();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return dlcSingletonObj;
    }
}
