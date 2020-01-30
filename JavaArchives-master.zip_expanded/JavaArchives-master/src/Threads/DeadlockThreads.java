package Threads;


public class DeadlockThreads {

	public static void main(String[] args) {
		Thread1 thread1 = new Thread1();
		Thread2 thread2 = new Thread2();
		
		thread1.run();
		System.out.println("X");
		thread2.run();
	}
	
	private static class Thread1 extends Thread {
		@Override
		public void run() {
			synchronized (Thread2.class) {
			
				try {
					Thread.sleep(4);
					System.out.println("Sleeping for 4");
				} catch (InterruptedException e) {}
				
				synchronized (Thread1.class) {
					
				}
			}
		}
	}
	
	private static class Thread2 extends Thread {
		@Override
		public void run() {
			synchronized (Thread1.class) {
				
				try {
					Thread.sleep(2);
					System.out.println("Sleeping for 2");
				} catch (InterruptedException e) {}
				
				synchronized (Thread2.class) {
					
				}
			}
		}
	}
	
	
}