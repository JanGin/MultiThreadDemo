package me.chan.executors.rejecthandler;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	private String name;
	
	public Task (String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.printf("Task: %s is starting...\n", name);
		long duration = (long) (Math.random() * 10);
		try {
			System.out.printf("Executing task during %d seconds...\n", duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s ending...\n", name);
	}

	@Override
	public String toString() {
		return name;
	}

}
