package ThreadLearn.ThreadLocalSight;

import java.util.Random;

/**
 * @author xz
 * @date 2020/4/3 14:47
 **/

/**
 * 对于 ThreadLocal 的使用场景，一般来说，当某些数据是以线程为作用域并且不同线程具有不同的数据副本的时候
 *
 * ThreadLocal另一个使用场景是复杂逻辑下的对象传递
 * Thread中维护了一个类型为ThreadLocalMap(这个类型是ThreadLocal类中的static class ThreadLocalMap 一个内部类) 的变量 threadLocals(ThreadLocal.ThreadLocalMap threadLocals).
 *
 *在实例化ThreadLocalMap时创建了一个长度为16的Entry数组。通过hashCode与length位运算确定出一个索引值i，这个i就是被存储在table数组中的位置。
 *
 * 每个线程Thread持有一个ThreadLocalMap类型的实例threadLocals
 *
 * 结合此处的构造方法可以理解成每个线程Thread都持有一个Entry型的数组table，而一切的读取过程都是通过操作这个数组table完成的。
 *
 * ThreadLocalMap 构造方法ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) key是ThreadLocal,值是设置的值
 *
 * 如同时声明了三个ThreadLocal变量
 *      ThreadLocal<A> sThreadLocalA = new ThreadLocal<A>();
 *      ThreadLocal<B> sThreadLocalB = new ThreadLocal<B>();
 *      ThreadLocal<C> sThreadLocalC = new ThreadLocal<C>();
 *
 *  对于一个Thread来说只有持有一个ThreadLocalMap，所以ABC对应同一个ThreadLocalMap对象。为了管理ABC，于是将他们存储在一个数组的不同位置，而这个数组就是上面提到的Entry型的数组table。
 *
 *  对于某一ThreadLocal来讲，他的索引值i是确定的，在不同线程之间访问时访问的是不同的table数组的同一位置即都为table[i]，只不过这个不同线程之间的table是独立的。
*   对于同一线程的不同ThreadLocal来讲，这些ThreadLocal实例共享一个table数组，然后每个ThreadLocal实例在table中的索引i是不同的。
 */
public class Test {
    public static class MyRunnable implements Runnable{
        private ThreadLocal<Integer>threadLocal=new ThreadLocal<>();
        @Override
        public void run() {
            int i = new Random().nextInt(100);
            System.out.println(Thread.currentThread().getId()+"==="+i);
            threadLocal.set(i);
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId()+":"+threadLocal.get());
        }

    }
    public static void main(String[] args) {
        System.out.println("start");
        MyRunnable runnable=new MyRunnable();
        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
