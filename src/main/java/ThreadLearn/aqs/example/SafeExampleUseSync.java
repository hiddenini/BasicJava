package ThreadLearn.aqs.example;

import java.util.concurrent.TimeUnit;

/**
 * @author xz
 * @date 2019/11/19 16:25
 **/

public class SafeExampleUseSync {
    private int value = 1;

    /**
     * 使用synchronized保证线程安全.输出的数字不会重复且最大值为21
     * @param args
     */
    public static void main(String[] args) {
        SafeExampleUseSync demo3 = new SafeExampleUseSync();

        for (int i=0;i<20;i++) {
                new Thread( () -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(demo3.incre());
                } ).start();
        }

    }

    public  synchronized int incre() {
        return ++value;
    }
}
