package singleton;

public class LazySingltonThread extends Thread {
		@Override
		public void run() {
			System.out.println(LazySingletonObj.getInstance().hashCode());
		}
}
