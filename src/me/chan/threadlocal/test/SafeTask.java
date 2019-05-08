package me.chan.threadlocal.test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {

	ThreadLocal<Date> date = new ThreadLocal<Date>() {

		@Override
		protected Date initialValue() {
			return new Date();
		}
		
	};
	
	@Override
	public void run() {
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), date.get());
		
		try {
			TimeUnit.SECONDS.sleep((int)(Math.rint(Math.random()*10)));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Thread finished: %s : %s\n", Thread.currentThread().getId(), date.get());
	}

}
