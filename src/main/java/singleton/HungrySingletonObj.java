package singleton;

public class HungrySingletonObj {
	public static HungrySingletonObj mySingletonObj=new HungrySingletonObj();

	private HungrySingletonObj() {

	}

	public static HungrySingletonObj getInstance() {
		return mySingletonObj;
	}
}
