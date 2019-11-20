package ThreadLearn.aqs.example;

import ThreadLearn.aqs.customlock.CustomLockReentrant;

/**
 * @author xz
 * @date 2019/11/19 16:25
 **/

public class ReentrantExample {
    CustomLockReentrant customLockReentrant=new CustomLockReentrant();
    /**
     * 一个使用可重入锁的例子，会依次输出a! b! 然后程序结束
     * @param args
     */
    public static void main(String[] args) {
        ReentrantExample reentrantExample = new ReentrantExample();

        new Thread( ()->
                reentrantExample.a()
        ).start();
    }

   public void a(){
       customLockReentrant.lock();
       System.out.println("a!");
       b();
       customLockReentrant.unlock();
   }

    public void b(){
        customLockReentrant.lock();
        System.out.println("b!");
        customLockReentrant.unlock();
    }
}
