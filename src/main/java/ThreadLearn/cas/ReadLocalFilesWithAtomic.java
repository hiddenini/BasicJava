package ThreadLearn.cas;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author xz
 *
 */


/**
 * 直接是使用线程池读取本地的三个文件并使用AtomicInteger统计总数
 */
public class ReadLocalFilesWithAtomic {


	private  static  AtomicInteger total=new AtomicInteger(0);

	public static void main(String[] args) throws Exception {

		Long startAll = System.currentTimeMillis();
		// 定义线程池
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		List<String> filenames =Arrays.asList("P40021^VIP_INFO^20190329211144.json","P40021^VIP_INFO^20190329211145.json","P40021^VIP_INFO^20190329211147.json");



		System.out.println("startAll:" + startAll);
		Long timeAll = null;
		System.out.println("size:" + filenames.size());
		for (int i = 0; i < filenames.size(); i++) {
			// 获取文件名
			String fileName = filenames.get(i);
			executorService.submit(() ->{
				String prefix = "E:\\note\\data\\P40021\\VIP_INFO\\20190329211143\\";
				String realPath = prefix + fileName;
				// 开始遍历文件
				long start = new java.util.Date().getTime();
				System.out.println(Thread.currentThread().getName() + "开始时间[" + start);
				File file = new File(realPath);
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					while ((bufferedReader.readLine()) != null) {
							total.incrementAndGet();
					}
					bufferedReader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	long end= new java.util.Date().getTime();
				System.out.println(Thread.currentThread().getName()+"结束时" + end);
				System.out.println(Thread.currentThread().getName()+"cost:"+(end-start));
			}  );
		}
		executorService.shutdown();
		while (true) {
			if (executorService.isTerminated()) {
				Long endAll = System.currentTimeMillis();
				timeAll = endAll - startAll;
				System.out.println("costAll:" + timeAll);
				
				System.out.println("sums:"+total.get());
				break;
			}
		}
		

	}
}
