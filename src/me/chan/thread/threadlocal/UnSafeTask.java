package me.chan.thread.threadlocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UnSafeTask implements Runnable {

	private Date date;
	
	@Override
	public void run() {
		date = new Date();
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), date);
		
		try {
			TimeUnit.SECONDS.sleep((int)Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Thread finished: %s : %s\n", Thread.currentThread().getId(), date);
	}
	
}
