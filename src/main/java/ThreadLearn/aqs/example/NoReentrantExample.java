package ThreadLearn.aqs.example;

import ThreadLearn.aqs.customlock.CustomLock;

/**
 * @author xz
 * @date 2019/11/19 16:25
 **/

public class NoReentrantExample {
    private int value = 1;
    CustomLock customLock=new CustomLock();
    /**
     * 一个使用不可重入锁的例子，只会输出a!,然后程序阻塞
     * @param args
     */
    public static void main(String[] args) {
        NoReentrantExample notTeentrantExample = new NoReentrantExample();

        new Thread( ()-> {
            notTeentrantExample.a();
        }).start();
    }

   public void a(){
       customLock.lock();
       System.out.println("a!");
       b();
       customLock.unlock();
   }

    public void b(){
        customLock.lock();
        System.out.println("b!");
        customLock.unlock();
    }
}
