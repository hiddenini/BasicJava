package jvm.example;

public class Heap {
    public static void main(String[] args) {
        /**
         * 默认情况下:分配的总内存是电脑内存的1/4  而初始化内存是1/64
         *
         * 可以通过:-Xms1024m -Xmx1024m -XX:+PrintGCDetails 设置内存、
         *
         * OOM
         * 1--尝试加大heap内存
         * 2--分析内存,看下哪个地方出现了问题(借助工具)
         * --如果能直接看到代码第几行出错
         * --通过内存快照Jprofiler(或者一些其他的工具)
         * 分析dump内存文件,快速定位内存泄漏
         * 获取堆中的数据
         * 获取大的对象
         *
         * 305664K+699392K=1005056=981.5M 所以元空间:逻辑上存在,物理上不存在 没有空间
         *
         *
         */
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        System.out.println("max" + maxMemory + "字节" + maxMemory / 1024 / 1024 + "MB");
        System.out.println("total" + maxMemory + "字节" + totalMemory / 1024 / 1024 + "MB");

    }
}
