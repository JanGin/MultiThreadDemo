package me.chan.executors.callable.cancel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *  Cancel Task
 */
public class AppMain {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		System.out.println("Main: Executing the task:");
		Future<String> result = executor.submit(() -> {
			while (true) {
				System.out.println("Task: test");
				Thread.sleep(100);
			}
				
		});
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Main: canceling the task");
		result.cancel(true);
		
		System.out.println("Main: is canceled? : " + result.isCancelled());
		System.out.println("Main: is done? :" + result.isDone());
	}
}
