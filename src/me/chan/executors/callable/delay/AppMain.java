package me.chan.executors.callable.delay;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppMain {
	
	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(
				Runtime.getRuntime().availableProcessors());
		
		System.out.println("Main: Starting at:" + LocalDateTime.now());
		for (int i=1; i <= 5; i++) {
			Task task = new Task("Task"+i);
			scheduledExecutor.schedule(task, i, TimeUnit.SECONDS);
		}
		
		scheduledExecutor.shutdown();
		scheduledExecutor.awaitTermination(1, TimeUnit.HOURS);
		
		System.out.println("Main: Ends at:" + LocalDateTime.now());
	}

}
