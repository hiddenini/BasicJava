package singleton;

public class LazySingletonObjSync {
	private static LazySingletonObjSync lazySingletonObj;
	
	
	private LazySingletonObjSync() {
		
	}
	
	synchronized public static LazySingletonObjSync  getInstance() {
		if(lazySingletonObj==null) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lazySingletonObj=new LazySingletonObjSync();
		}
		return lazySingletonObj;
	}
}
