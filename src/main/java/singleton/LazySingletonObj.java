package singleton;

public class LazySingletonObj {
	private static LazySingletonObj lazySingletonObj;
	
	
	private LazySingletonObj() {
		
	}
	
	public static LazySingletonObj  getInstance() {
		if(lazySingletonObj==null) {
			lazySingletonObj=new LazySingletonObj();
		}
		return lazySingletonObj;
	}
}
