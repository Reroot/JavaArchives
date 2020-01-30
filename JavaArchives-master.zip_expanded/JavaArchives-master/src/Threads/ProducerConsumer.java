package Threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer {
	
	private static int MAX_SIZE = 5;
	private static Queue<Integer> bufferArray = new LinkedList<> ();
	private static int THREAD_RUN_COUNT = 20;
	
	ProducerConsumer() {}
	
	public static void main(final String[]  args) {
		final Producer p = new Producer(bufferArray);
		final Consumer c = new Consumer(bufferArray);
		
		p.start();
		c.start();
	}

	private static class Producer extends Thread {
		private final Queue<Integer> arr;
		
		Producer(final Queue<Integer> arr) {
			this.arr = arr;
		}
		
		@Override
		public void run() {
			int threadRunCount = 0;
			System.out.println("Building queue");
			while (threadRunCount < THREAD_RUN_COUNT) {
				synchronized (arr) {
					if (arr.size() == MAX_SIZE) {
						try {
							arr.wait();
						} catch (final InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Building queue");
					}
					threadRunCount++;
					final Random rand = new Random();
					final int toBeQueued = rand.nextInt();
					System.out.println(toBeQueued);
					arr.add(new Integer(toBeQueued));
					arr.notifyAll();
				}
			}
			
		}
	}
	
	private static class Consumer extends Thread {
		private final Queue<Integer> arr;
		
		Consumer (final Queue<Integer> arr) {
			this.arr = arr;
		}
		
		@Override
		public void run() {
			int threadRunCount = 0;
			while (threadRunCount < THREAD_RUN_COUNT) {
				synchronized (arr) {
					if (arr.size() == 0) {
						try {
							arr.wait();
						} catch (final InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Removing queue");
					}
					threadRunCount++;
					System.out.println(arr.remove());
					arr.notifyAll();
				}
			}
		}
	}
}