package jvm.example;

public class Native {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Thread:" + Thread.currentThread() + "hello");
        }, "a name").start();
    }

    /**
     * native：凡是带了native关键字的,说明java的作用范围到不了了,会去调用底层c语言的库
     * 会进入本地方法栈,调用本地方法接口JNI
     * JNI:扩展java的使用,融合不同的编程语言为java所用
     */
    private native void start0();

    /**
     * 程序计数器：当前线程所执行的字节码的行号指示器，用于记录正在执行的虚拟机字节指令地址，线程私有。
     */

    /**
     * Native方法栈:static final Class 常量池 和虚拟栈相似，只不过它服务于Native方法，线程私有
     */

    /**
     * 栈 Java虚拟栈():存放基本数据类型、对象的引用、方法出口等，线程私有  无垃圾回收
     *
     */

    /**
     * 堆：java内存最大的一块，所有对象实例、数组都存放在java堆，GC回收的地方，线程共享 垃圾回收的主要战场
     */

    /**
     * 方法区：存放已被加载的类信息、常量、静态变量、即时编译器编译后的代码数据等。（即永久带），回收目标主要是常量池的回收和类型的卸载，各线程共享
     */

}
