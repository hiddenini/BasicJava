package ThreadLearn.ObjectLayout;

import org.openjdk.jol.info.ClassLayout;

/**
 * 打印对象的内存布局
 * <p>
 * 未加锁
 * <p>
 * java.lang.Object object internals:
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
 * java.lang.Object object internals:
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
 * 对象头(Header) 包括2部分信息  一部分是MarkWord 另一部分是类型指针(Class Pointer)
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
 * 但是竞争很激烈,需要执行的动作很耗时轻量级锁就不合适了(会有大量的线程在进行长时间的自旋)
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
