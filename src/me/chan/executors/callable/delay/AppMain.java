package me.chan.executors.callable.delay;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AppMain {
	
	public static void main(String[] args) throws InterruptedException {
		//schedule();
		scheduleAtFixRate();
	}
	
	
	public static void schedule() throws InterruptedException {
		ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(
				Runtime.getRuntime().availableProcessors());
		
		System.out.println("Main: Starting at:" + LocalDateTime.now());
		for (int i=1; i <= 5; i++) {
			CTask task = new CTask("Task"+i);
			scheduledExecutor.schedule(task, i, TimeUnit.SECONDS);
		}
		
		scheduledExecutor.shutdown();
		scheduledExecutor.awaitTermination(1, TimeUnit.HOURS);
		
		System.out.println("Main: Ends at:" + LocalDateTime.now());
	}

	
	public static void scheduleAtFixRate() throws InterruptedException {
		ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
		RTask task = new RTask("task");
		System.out.println("Main: Starting at:" + LocalDateTime.now());
		ScheduledFuture<?> result = scheduledExecutor.scheduleAtFixedRate(task, 
														1, 2, TimeUnit.SECONDS);
		for (int i=0; i < 10; i++) {
			System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
			
			TimeUnit.MILLISECONDS.sleep(500);
		}
		
		scheduledExecutor.shutdown();
		scheduledExecutor.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("Main: ends at " + LocalDateTime.now());
	}
}
