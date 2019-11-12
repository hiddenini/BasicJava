package singleton;


public class HolderSingleton {
	private HolderSingleton() {
		
	}
	
	private static class Holder {
		private static HolderSingleton instance=new HolderSingleton();
	}
	
	private static HolderSingleton getInstance() {
		return Holder.instance;	
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread( 
					() -> {
						System.out.println(HolderSingleton.getInstance());
					} )
			.start();
		}
	}
}
