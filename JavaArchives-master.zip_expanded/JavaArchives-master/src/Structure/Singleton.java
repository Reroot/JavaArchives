package Structure;

public class Singleton {

	private volatile static Singleton instance;
	
	private Singleton() {}
	
	
	public static void main(String[] args) {
		Singleton s = new Singleton();
		Singleton s2 = new Singleton();	
		System.out.println(s);
		System.out.println(s2);
		System.out.println(s == s2);//false
		System.out.println(s.equals(s2));//false
		System.out.println(s.hashCode());//
		System.out.println(s2.hashCode());//
		
	}
	
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}