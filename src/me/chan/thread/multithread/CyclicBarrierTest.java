package me.chan.thread.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	
	private static CyclicBarrier barrier;
	
	public static void main(String[] args) {
		barrier = new CyclicBarrier(10, new Runnable() {

			@Override
			public void run() {
				System.out.println("Æë»î¶ù£¡¿ª¸ã~");
			}
			
		});
		
		for (int i=1; i<=10; i++) {
			new TestThread("ÈË"+i).start();
		}
	}
	
	
	static class TestThread extends Thread {

		TestThread(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		
	}

}
