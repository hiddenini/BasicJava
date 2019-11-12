package singleton;

public class HungrySingltonThread extends Thread {
		@Override
		public void run() {
			System.out.println(HungrySingletonObj.getInstance().hashCode());
		}
}
