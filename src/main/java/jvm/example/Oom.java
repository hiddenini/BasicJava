package jvm.example;

import java.util.ArrayList;

/**
 * -Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError   dump内存文件
 * -Xms8m -Xmx8m -XX:+PrintGCDetails   打印GC回收信息
 * <p>
 * -Xms 设置初始化内存分配大小 默认1/64
 * -Xmx 设置最大分配内存       默认1/4
 * <p>
 * 生成java_pid4372.hprof使用jprofiler打开可以看到大对象
 * <p>
 * 然后可以通过查看 ThreadDump 看到具体的线程和错误
 */
public class Oom {

    byte[] array = new byte[1024 * 1024];

    public static void main(String[] args) {
        ArrayList<Oom> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new Oom());//
                count++;
            }
        } catch (Error e) {
            System.out.println("count:" + count);
            e.printStackTrace();
        }
    }
}
