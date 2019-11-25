package ThreadLearn.aqs.Cyb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 验证下来的结果是:使用多线程去分别去读取单个文件时消耗的时间比单线程去读取单个文件时的要长。总体消耗的时间的差不多.效果不是很明显
 * 
 * @author xz
 *
 */

public class ReadLocalFilesWithCyb {


	public static void main(String[] args) throws Exception {
		
		Long startAll = System.currentTimeMillis();
		// 定义线程池
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		// 定义4个Future去保存子数组计算结果
		final int[] results = new int[3];
		final long[] times = new long[3];
		// 定义一个循环屏障，在屏障线程中进行计算结果合并
		final List<Integer> list=new ArrayList<>();
		CyclicBarrier barrier = new CyclicBarrier(3, () -> {
			System.out.println("所有线程已经准备完毕");
			// 当所有线程进计算完自己负责的部分之后，这里进行累加。
			Long b=0l;
			int sums=0;
			for (int i = 0; i < 3; i++) {
				sums += results[i];
				b+=times[i];
			}
			list.add(sums);
	        System.out.println("数据总量是:"+sums);
		});
		
		List<String> filenames =Arrays.asList("P40021^VIP_INFO^20190329211144.json","P40021^VIP_INFO^20190329211145.json","P40021^VIP_INFO^20190329211147.json");

		System.out.println("startAll:" + startAll);
		Long timeAll = null;
		System.out.println("size:" + filenames.size());
		for (int i = 0; i < filenames.size(); i++) {
			// 获取文件名
			String fileName = filenames.get(i);
			// 盛放计算结果
			int finalI = i;
			executorService.submit(() -> {
				int num = 0;
				String prefix="E:\\note\\data\\P40021\\VIP_INFO\\20190329211143\\";
				String realPath =prefix+fileName;
				// 开始遍历文件
            	long start= new java.util.Date().getTime();
				try {
					System.out.println(Thread.currentThread().getName()+"开始时间[" +start);
					Long time = null;
					File file = new File(realPath);
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					String str = null;
					try {
						while ((str = bufferedReader.readLine()) != null) {
							num++;
						}
						bufferedReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
	            	long end= new java.util.Date().getTime();
					System.out.println(Thread.currentThread().getName()+"结束时" + end);
					System.out.println(Thread.currentThread().getName()+"cost:"+(end-start));
					System.out.println("timeSingle:" + time);
					results[finalI] = num;
					times[finalI] = time;					
				} catch (Exception e) {
					System.out.println("error:" + e.getMessage());
				}
				try {
					barrier.await();
					System.out.println(Thread.currentThread().getName()+"开始执行barrier后的逻辑");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}

			});
		}
		executorService.shutdown();
		while (true) {
			if (executorService.isTerminated()) {
				Long endAll = System.currentTimeMillis();
				timeAll = endAll - startAll;
				System.out.println("costAll:" + timeAll);
				
				System.out.println("sums:"+list.get(0));
				break;
			}
		}


	}
}
