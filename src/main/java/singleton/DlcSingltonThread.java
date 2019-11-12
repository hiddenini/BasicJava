package singleton;

public class DlcSingltonThread extends Thread {
		@Override
		public void run() {
			System.out.println(DlcSingletonObj.getInstance().hashCode());
		}
}
