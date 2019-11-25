package ThreadLearn.aqs.Cdl;

import java.util.concurrent.CountDownLatch;

public class Cdl {

	public static void main(String[] args) {   
        final CountDownLatch latch = new CountDownLatch(2);

        for (int i=0;i<2;i++) {
            new Thread(()->{
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } ).start();
        }

        try {
            System.out.println("主线程正在等待2个子线程执行完毕...");
           latch.await();
           System.out.println("2个子线程已经执行完毕");
           System.out.println("主线程继续执行");
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }

}
