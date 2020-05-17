package reference;

import java.lang.ref.SoftReference;

/**
 * 软引用 主要用在缓存()
 */
public class SoftReferenceL {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        //再分配一个数组,heap装不下,这个时候系统会进行垃圾先回收一次，不弱不够,会把软引用干掉
        byte[] b = new byte[1024 * 1024 * 12];
        System.out.println(m.get());
    }
}
