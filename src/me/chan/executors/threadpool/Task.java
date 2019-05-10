package me.chan.executors.threadpool;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	private String name;
	private LocalDateTime initDate;
	
	public Task(final String name) {
		this.name = name;
		initDate = LocalDateTime.now();
	}
	
	@Override
	public void run() {
		System.out.printf("%s: Task %s: Created on : %s\n", 
				Thread.currentThread().getName(), name, initDate.toString());
		
		System.out.printf("%s: Task %s: started on : %s\n", 
				Thread.currentThread().getName(), name, LocalDateTime.now().toString());
		
		long duration = (long) (Math.random()*10);
		System.out.printf("%s: Task %s: Doing a task during %d seconds\n", 
				Thread.currentThread().getName(), name, duration);
			
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
