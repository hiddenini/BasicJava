package ThreadLearn.aqs.customlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xz
 * @date 2019/11/25 16:36
 **/

/**
 *  设计一个同步工具，该工具在同一时刻，只能有两个线程能够并行访问，超过限制的其他线程进入阻塞状态。
 */
public class CustomTwinsLock implements Lock {

    private final Sync  sync    = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer{
        Sync (int count){
            if(count<0){
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int acquire) {
            for(;;){
                int current=getState();
                int now=current-acquire;
                if(now<0||compareAndSetState(current,now)){
                    return now;
                }
            }

        }


        @Override
        protected boolean tryReleaseShared(int release) {
            for (; ; ) {
                int current = getState();
                int now = current + release;
                if (compareAndSetState(current, now)) {
                    return true;
                }
            }
        }
    }


    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
