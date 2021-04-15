package ThreadLearn.aqs.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx {
    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition condition = reentrantLock.newCondition();

    static int a = 0;

    public static void add() {
        reentrantLock.lock();

        while (a == 0) {
            try {
                a++;
                System.out.println("add, a=" + a);
                condition.signal();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        reentrantLock.unlock();
    }

    public static void sub() {
        reentrantLock.lock();

        while (a == 1) {
            try {
                a--;
                System.out.println("sub, a=" + a);
                condition.signal();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        reentrantLock.unlock();
    }

    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                add();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sub();
            }
        }).start();

    }
}
