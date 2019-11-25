package ThreadLearn.aqs.Cyb;

import java.util.concurrent.CyclicBarrier;
/**
 * 
 * @author xz
 *CyclicBarrier试用与多线程结果合并的操作，用于多线程计算数据，最后合并计算结果的应用场景。
 *  比如我们需要统计多个Excel中的数据，然后等到一个总结果。我们可以通过多线程处理每一个Excel，
 *  执行完成后得到相应的结果，最后通过barrierAction来计算这些线程的计算结果，得到所有Excel的总和。
 *  
 *CyclicBarrier初始化时规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续。 
 *CyclicBarrier就象它名字的意思一样，可看成是个障碍， 所有的线程必须到齐后才能一起通过这个障碍。 
 *CyclicBarrier初始时还可带一个Runnable的参数， 此Runnable任务在CyclicBarrier的数目达到后，所有其它线程被唤醒前被执行。
 */
public class Cyb {
	// 自定义工作线程
	private static class Worker extends Thread {
		private CyclicBarrier cyclicBarrier;
		
		public Worker(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}
		
		@Override
		public void run() {
			super.run();
			try {
				System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
				//这里是一个屏障，当所有线程都到达了这个屏障之后开始执行屏障后面的代码
				cyclicBarrier.await();
				System.out.println(Thread.currentThread().getName() + "开始执行");
				// 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + "执行完毕");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
 
	public static void main(String[] args) {
		int threadCount = 3;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount,
				() ->
			System.out.println("all is ready ,do what you want!")
		);
		
		for (int i = 0; i < threadCount; i++) {
			System.out.println("创建工作线程" + i);
			Worker worker = new Worker(cyclicBarrier);
			worker.start();
		}
	}

}
