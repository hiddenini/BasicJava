package ThreadLearn.aqs.Cdl;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author xz
 *
 */


/**
 * 使用多线程访问本地的文件,总耗时比单个文件多一点点，未进行记录总数的统计
 * @author pc
 *
 */
public class ReadLocalFilesWithCdl {
	private int nThread;

	private CountDownLatch startGate;
	private CountDownLatch endGate;
	
	private static AtomicInteger total=new AtomicInteger(0);
	
	//final关键字,当你创建一个对象时，使用final关键字能够使得另一个线程不会访问到处于“部分创建”的对象
	//而且，很显然在某些场景下，final也可以解决一定的安全问题,但是下面这种就是有问题的，偶尔会输出null和少于三个singleSum
	//private final static List<Integer> list=new ArrayList<>();
	
	//或者可以使用AtomicInteger完成统计
	
	
	private  static List<Integer> list=Collections.synchronizedList(new ArrayList<>());

	public ReadLocalFilesWithCdl(int nThread, CountDownLatch startGate, CountDownLatch endGate) {

		this.nThread = nThread;
		this.startGate = startGate;
		this.endGate = endGate;
	}

	class worker implements Runnable {

		public void run() {
			try {
				startGate.await();

				int num = 0;
				long start = new java.util.Date().getTime();
				System.out.println(Thread.currentThread().getName() + "开始时间[" + start);

				File file = new File(
						"E:\\note\\data\\P40021\\VIP_INFO\\20190329211143\\P40021^VIP_INFO^20190329211144.json");
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				String str = null;
				try {
					while ((str = bufferedReader.readLine()) != null) {
						num++;
						total.incrementAndGet();
					}
					list.add(num);
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				long end = new java.util.Date().getTime();
				System.out.println(Thread.currentThread().getName() + "结束时间[" + end);
				System.out.println(Thread.currentThread().getName() + "cost:" + (end - start) + "num:" + num);

			} catch (InterruptedException | FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				endGate.countDown();
			}
		}
	}

	public long timeTasks() {

		for (int i = 0; i < nThread; i++) {
			Thread thread = new Thread(new worker());
			thread.start();
		}

		long start = System.currentTimeMillis();
		// 所有阻塞的任务同时开始
		startGate.countDown();
		try {
			// 主线程阻塞,等待其他所有 worker 线程完成后再执行
			endGate.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("用时: " + (end - start) + "ms");

		return end - start;
	}

	public static void main(String[] args) {

		int nThread = 3;
		CountDownLatch startGate = new CountDownLatch(1);
		CountDownLatch endGate = new CountDownLatch(nThread);

		new ReadLocalFilesWithCdl(nThread, startGate, endGate).timeTasks();
		
		System.out.println(list.size());
		
		for (Integer singleSum :list) {
			System.out.println("singleSum:"+singleSum);
		}
		
		System.out.println("total:"+total.get());

		
	}
}
