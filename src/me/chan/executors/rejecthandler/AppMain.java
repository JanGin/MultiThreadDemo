package me.chan.executors.rejecthandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AppMain {

	public static void main(String[] args) {
			RejectedTaskHandler handler = new RejectedTaskHandler();
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
			executor.setRejectedExecutionHandler(handler);
			
			System.out.println("Main: Starting...");
			for (int i=0; i < 3; i++) {
				Task task = new Task("Task-"+i);
				executor.submit(task);
			}
			
			System.out.println("Main: Shutting down the executor...");
			executor.shutdown();
			System.out.println("Main: Sending another task to executor...");
			Task rejectedTask = new Task("rejectedTask");
			
			executor.submit(rejectedTask);
			System.out.println("Main: Ends");
	}

}
