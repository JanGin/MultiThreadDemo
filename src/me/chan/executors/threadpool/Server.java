package me.chan.executors.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

	private ExecutorService executorService;
	
	
	public Server() {
		executorService = Executors.newCachedThreadPool();
	}
	
	public Server(ThreadPoolType type) {
		switch (type.getName()) {
		case "single":
			executorService = Executors.newSingleThreadExecutor();
			break;
		case "cached":
			executorService = Executors.newCachedThreadPool();
			break;
		case "scheduled":
			executorService = Executors.newScheduledThreadPool(
					Runtime.getRuntime().availableProcessors());
			break;
		case "fixed":
			executorService = Executors.newFixedThreadPool(
					Runtime.getRuntime().availableProcessors());
			break;
		default:
			executorService = Executors.newCachedThreadPool();
			break;
		}
	}
	
	
	public void execute(Runnable command) {
		executorService.execute(command);
		
		if (executorService instanceof ThreadPoolExecutor) {
			ThreadPoolExecutor threadPool = (ThreadPoolExecutor) executorService;
			System.out.println("Server:=====>ThreadPool Size: " + threadPool.getPoolSize());
			System.out.println("Server:=====>Task Count: " + threadPool.getTaskCount());
			System.out.println("Server:=====>ThreadPool Active Count: " + threadPool.getActiveCount());
			System.out.println("Server:=====>Completed Task Count: " + threadPool.getCompletedTaskCount());
			
			
		}
	}
	
	public void shutdown() {
		executorService.shutdown();
	}
	
	
	public boolean isTerminated() {
		return executorService.isTerminated();
	}
}
