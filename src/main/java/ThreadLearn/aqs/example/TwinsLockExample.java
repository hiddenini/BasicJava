package ThreadLearn.aqs.example;

import ThreadLearn.aqs.customlock.CustomTwinsLock;
import ThreadLearn.aqs.customlock.Worker;

/**
 * @author xz
 * @date 2019/11/29 17:04
 **/

public class TwinsLockExample {
    public static void main(String[] args) {
        final CustomTwinsLock customTwinsLock=new CustomTwinsLock();

        for (int i = 0; i <=10; i++) {
            Worker w = new Worker(customTwinsLock);
            w.start();
        }
    }

}
