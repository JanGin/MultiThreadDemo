package me.chan.thread.semaphore;

import java.util.concurrent.TimeUnit;

public class Job implements Runnable {

	private PrintQueue printQueue;
	
	public Job (PrintQueue printQueue) {
		this.printQueue = printQueue;
	}
	
	@Override
	public void run() {
		System.out.printf("%s is going to work:", Thread.currentThread().getName()+"\n");
		/*
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		*/
		printQueue.printJob(new Object());
	}
	
}
