package ThreadLearn.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xz
 * @date 2019/11/19 15:17
 **/

public class Start {
    public static void main(String[] args) {

        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.newCondition();
    }
}
