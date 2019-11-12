package singleton;

public class LazySingltonSyncThread extends Thread {
		@Override
		public void run() {
			System.out.println(LazySingletonObjSync.getInstance().hashCode());
		}
}
