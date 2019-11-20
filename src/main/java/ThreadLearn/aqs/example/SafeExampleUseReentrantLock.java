package ThreadLearn.aqs.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xz
 * @date 2019/11/19 16:25
 **/

public class SafeExampleUseReentrantLock {
    private int value = 1;

    ReentrantLock reentrantLock = new ReentrantLock();
    /**
     * 使用ReentrantLock实现线程安全
     * @param args
     */
    public static void main(String[] args) {
        SafeExampleUseReentrantLock demo3 = new SafeExampleUseReentrantLock();
        for (int i=0;i<20;i++) {
            new Thread( () -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(demo3.add());
            } ).start();
        }
    }



    public int add() {
        reentrantLock.lock();
        try {
            value++;
            return value;
        } finally {
            reentrantLock.unlock();
        }
    }
}
