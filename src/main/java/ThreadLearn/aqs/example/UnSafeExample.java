package ThreadLearn.aqs.example;

import java.util.concurrent.TimeUnit;

/**
 * @author xz
 * @date 2019/11/19 16:25
 **/

public class UnSafeExample {
    private int value = 1;

    /**
     * 一个的非线程安全的例子,输出的数字会重复,并且小于21
     * @param args
     */
    public static void main(String[] args) {
        UnSafeExample demo3 = new UnSafeExample();

        for (int i=0;i<20;i++) {
                new Thread( () -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(demo3.increamentAndGet());
                } ).start();
        }

    }

    public  int increamentAndGet() {
        return ++value;
    }
}
