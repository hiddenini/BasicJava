package interview.blockingqueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Sight {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

        /**
         * putIndex 0->1->2 循环结束后 putIndex:0
         * takeIndex 0
         */
        for (int i = 1; i < 4; i++) {
            queue.offer(i);
        }
        /**
         * 拿一个
         * putIndex 0    循环结束后 putIndex:0
         * takeIndex 0->1   循环结束后 takeIndex:1
         */
        queue.poll();

        /**
         * 再加1个
         * putIndex 0>1    循环结束后 putIndex:1
         * takeIndex 0->1   循环结束后 takeIndex:1
         */
        for (int i = 4; i < 5; i++) {
            queue.offer(i);
        }

        /**
         * 全部拿
         * putIndex 1   putIndex:1
         * takeIndex 1->2->0   takeIndex:1
         */
        for (int i = 1; i < 4; i++) {
            System.out.println(queue.poll());
        }

        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(100);

        blockingQueue.offer(1);
        blockingQueue.poll();
    }
}
