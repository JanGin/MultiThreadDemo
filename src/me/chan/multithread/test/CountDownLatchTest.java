package me.chan.multithread.test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	private static CountDownLatch cdl = new CountDownLatch(10);
	
	public static void main(String[] args) {
			
			for (int i=0; i<10; i++) {
				Thread testThread = new Thread(new TestThread(), "Thread-"+i);
				testThread.start();
			}
			try {
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("人到齐了！！");
			}
			
	}
	
	
	static class TestThread implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"到了！");
			cdl.countDown();
		}
		
	}
}
