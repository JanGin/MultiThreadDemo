package me.chan.forkjoin.sync.recursiveaction;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class AppMain {

	public static void main(String[] args) {
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generate(10_000);
		Task task = new Task(0, products.size(), products, 0.20);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.execute(task);
		
		do {
			System.out.printf("Main: Task Count: %d.\n", forkJoinPool.getQueuedTaskCount());
			System.out.printf("Main: Active Thread Count: %d.\n", forkJoinPool.getActiveThreadCount());
			System.out.printf("Main: Thread Steal: %d.\n", forkJoinPool.getStealCount());
			System.out.printf("Main: Thread Paralleism: %d.\n", forkJoinPool.getParallelism());
			
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		
		forkJoinPool.shutdown();
		System.out.printf("Main: The task has completed successfully? %s.\n", task.isCompletedNormally());
	}
}
