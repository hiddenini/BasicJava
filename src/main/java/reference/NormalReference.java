package reference;

import java.io.IOException;

/**
 * 强引用 普通的对象的引用，当没有引用指向当前对象时 会被回收
 */
public class NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc(); //DisableExplicitGc
        System.in.read();
    }
}
