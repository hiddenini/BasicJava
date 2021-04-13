package jvm.example;

public class Jmm {
    /**
     * Java内存模型(Java Memory Model，JMM)JMM主要是为了规定了线程和内存之间的一些关系。
     * 根据JMM的设计，系统存在一个主内存(Main Memory)，Java中所有变量都储存在主存中，
     * 对于所有线程都是共享的。每条线程都有自己的工作内存(Working Memory)，
     * 工作内存中保存的是主存中某些变量的拷贝，线程对所有变量的操作都是在工作内存中进行，
     * 线程之间无法相互直接访问，变量传递均需要通过主存完成。
     */

    /**
     *  jvm结构
     *  堆 栈 那几个部分
     */

    /**
     * jmm中的主内存、工作内存与jvm中的Java堆、栈、方法区等并不是同一个层次的内存划分
     * 这两者基本上是没有关系的，如果两者一定要勉强对应起来
     * 那从变量、主内存、工作内存的定义来看，主内存主要对应于Java堆中的对象实例数据部分
     * 而工作内存则对应于虚拟机栈中的部分区域。从更低层次上说，主内存就直接对应于物理硬件的内存
     * 而为了获取更好的运行速度，虚拟机（甚至是硬件系统本身的优化措施）可能会让工作内存优先存储于寄存器和高速缓存中
     * 因为程序运行时主要访问读写的是工作内存。
     */
}
