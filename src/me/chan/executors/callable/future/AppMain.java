package me.chan.executors.callable.future;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppMain {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(
											Runtime.getRuntime().availableProcessors());
		List<Future<Long>> results = new LinkedList<>();
		//generate 10 pseudorandom number
		Random generator = new Random();
		for (int i = 0; i < 10; i++) {
			int number = generator.nextInt(20);
			FactorialCaculator task = new FactorialCaculator(number);
			Future<Long> res = executor.submit(task);
			results.add(res);
		}
		
		do {
			System.out.printf("Main: the number of completed tasks : %d.\n", executor.getCompletedTaskCount());
			for (int i=0, size=results.size(); i < size; ++i) {
				Future<Long> res = results.get(i);
				System.out.printf("Main: Task %d: %b\n", i, res.isDone());
			}
			
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} while(executor.getCompletedTaskCount() < results.size());
		
		
		System.out.println("Main: final results:");
		int i=0;
		for (Future<Long> result : results) {
			Long res = null;
			try {
				res = result.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
			System.out.printf("Main: Task %d: result is : %d\n", i, res);
			++i;
		}
		
		executor.shutdown();
	}

}
