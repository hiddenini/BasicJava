package ThreadLearn.ObjectLayout;

import org.openjdk.jol.info.ClassLayout;

/**
 * 打印对象的内存布局
 * <p>
 * 未加锁
 * <p>
 * java.java.lang.Object object internals:
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
 * 12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 * <p>
 * <p>
 * 加上synchronized
 * <p>
 * java.java.lang.Object object internals:
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 * 0     4        (object header)                           a8 f3 1c 03 (10101000 11110011 00011100 00000011) (52229032)
 * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
 * 12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 * <p>
 * 在HotSpot虚拟机中，对象在内存中的布局主要分为3块区域:对象头(Header),实例数据(Instance Data)
 * 和对齐填充(padding，因为hotspot要求对象的起始地址必须是8字节的整数倍)
 * <p>
 * 对象头(Header) 包括2部分信息  一部分是MarkWord 另一部分是类型指针(Klass Pointer)
 * <p>
 * MarkWord 主要存储锁信息 gc信息 hashCode
 * <p>
 * 上面两个布局,加上锁之后对象的内存布局中的MarkWord发生了变化,也说明了MarkWord中是存储了锁的信息的
 * <p>
 * jdk早期的synchronized是重量级锁 因为申请资源必须要通过kernel系统调用
 * <p>
 * 可以比较下加synchronized前后的001->00 从无锁到轻量级的锁
 * 00000001
 * 10101000
 * <p>
 * 00 轻量级锁
 * 10 重量级锁
 * 001 无锁
 * 101 偏向锁(目的是消除数据在无竞争情况下的同步原语) 对于有些方法来说大部分情况下只会有一个线程执行
 * eg StringBuffer中的一些sync方法 Vector中的一些sync方法 所以在MarkWord中记录了该线程的id  后续持有偏向锁的线程进入到
 * 这个锁相关的同步块时,虚拟机都可以不再进行任何同步操作
 * <p>
 * 但是有其他线程去尝试获取这个锁时,偏向模式就宣告结束,根据锁对象目前是否处于被锁定的状态
 * 恢复到未锁定(01)或者轻量级锁(00)状态
 * <p>
 * 为什么有轻量级锁还要有重量级锁?
 * <p>
 * 当竞争不激烈,需要执行的动作很快时适合轻量级锁
 * <p>
 * 但是竞争很激烈,需要执行的动作很耗时轻量级锁就不合适了(会有大量的线程在进行长时间的自旋,自旋锁是消费cpu资源的)
 * <p>
 * 偏向锁是否一定比自旋锁效率高?
 * 不一定，在明确知道会有多线程竞争的情况下，偏向锁肯定会涉及锁撤销，这时候直接使用自旋锁
 * JVM启动时会有很多线程竞争(明确)，所以默认情况启动时不打开偏向锁，过一段时间再打开(4s)
 * Lock指令(汇编层面)  volatile 是 lock addl   synchronized 是 lock cmpxchg
 * 作用在于多处理器中的执行指令时对共享内存的独占使用
 * 它的作用是能够将当前处理器对应的缓存的内容刷新到主存，并使其他处理器对应的缓存失效
 * 另外还提供了有序的指令无法越过这个内存屏障的作用
 * MESI Cache 缓存一致性协议（因特尔的cpu）指的是缓存行的四种状态 Modified Exclusive Shared Invalid
 * <p>
 * 一个cache line Modified 之后要将另外一个使用了同样数据的cache line置为Invalid(个人描述)
 * <p>
 * 局部性原理  按块读取 cpu读取数据时是64(折中值,局部性空间效率和读取时间的折中)个字节一起读到缓存
 * <p>
 * 伪共享问题  eg 2个线程对于size为2的T数组中的T的的volatile变量x的一亿次累加
 * <p>
 * 下面这种的时间大概是在3s,原因是这2个x在内存中的位置是非常靠近，位于一个cache line 所以存在互相通知将自己的cache line失效的过程
 * T{
 * public volatile long x =0l;
 * }
 * <p>
 * 下面这种的时间大概是在1s,因为x前后有占位的变量，并且字节数量为56 ,也就是说用这种方式保证了不会和其他x在同一个cache line
 * <p>
 * 也就保证了不会存在互相通知将缓存失效的过程
 * T{
 * public volatile long p1,p2,p3,p4,p5,p6,p7;
 * public volatile long x =0l;
 * public volatile long p9,p10,p11,p12,p13,p14,p15;
 * }
 * <p>
 * wc(缓存) 合并写技术 cpu内部的一个缓存，4个字节
 * <p>
 * synchronized 底层原语:monitorenter monitorexit
 * volatile  底层原语:lockaddl
 * cas 底层原语:汇编语句原语支持 lock cmpxchg指令 lock指令在执行后面指令的时候锁定一个北桥信号 不采用锁总线的方式
 */
public class Jol {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());


        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
