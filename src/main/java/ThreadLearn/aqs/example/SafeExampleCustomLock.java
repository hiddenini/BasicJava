package ThreadLearn.aqs.example;

import ThreadLearn.aqs.customlock.CustomLock;

import java.util.concurrent.TimeUnit;

/**
 * @author xz
 * @date 2019/11/19 16:25
 **/

public class SafeExampleCustomLock {
    private int value = 1;

    CustomLock customLock = new CustomLock();
    /**
     * 使用自定义锁实现线程安全
     * @param args
     */
    public static void main(String[] args) {
        SafeExampleCustomLock demo3 = new SafeExampleCustomLock();
        for (int i=0;i<20;i++) {
            new Thread( () -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(demo3.add());
            } ).start();
        }
    }



    public int add() {
        customLock.lock();
         value++;
        customLock.unlock();
        return value;
    }
}
