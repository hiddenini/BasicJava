package reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用 只要被gc就会被回收 应用  ThreadLocal
 */
public class WeakReferenceL {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        /**
         * set 时将key(ThreadLocal) 和value(new M())生成了一个Entry 这个Entry继承自WeakReference<ThreadLocal
         *
         * 为什么要用弱引用?
         * 引用的关系是 t  ->ThreadLocal    当前线程的内部的ThreadLocalMap(k,v)  k--->ThreadLocal
         *
         * 如果k是强引用指向了ThreadLocal,那么即使t==null;但是k的引用依然指向了ThreadLocal对象,所以会有内存泄漏的问题
         *
         * 而使用弱引用则不会
         *
         * 但是还是会有内存泄漏的风险 ThreadLocal被回收,k变成null,导致整个value再也无法被访问到。所以一旦不再使用需要remove
         */
        ThreadLocal<M> t = new ThreadLocal<>();
        t.set(new M());
        t.remove();
    }
}
