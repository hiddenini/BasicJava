package ThreadLearn.aqs.customlock;

import java.util.concurrent.locks.Lock;

/**
 * @author xz
 * @date 2019/11/29 17:17
 **/

public class Worker extends Thread {
    private Lock lock;

    public Worker(CustomTwinsLock customTwinsLock) {
        this.lock = customTwinsLock;
    }

    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "获取到了锁");
            Thread.sleep(2000L);
        } catch (Exception ex) {

        } finally {
            lock.unlock();
        }
    }

}
