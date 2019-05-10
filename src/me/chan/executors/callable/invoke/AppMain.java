package me.chan.executors.callable.invoke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppMain {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		List<Task> tasks = new ArrayList<>(4);
		for (int i=0; i < 3; i++) {
			Task task = new Task("Task-"+i);
			tasks.add(task);
		}
		
		List<Future<Result>> res = null;
		try {
			res = executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
		
		System.out.println("Printing results:☟☟☟☟☟☟☟☟☟....");
		for (int i=0; i < res.size(); i++) {
			Future<Result> future = res.get(i);
			
			try {
				Result result = future.get();
				System.out.println(result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
