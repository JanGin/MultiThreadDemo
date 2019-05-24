package me.chan.forkjoin.sync.recursivetask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class AppMain {

	public static void main(String[] args) {
		
		DocumentMock mock = new DocumentMock();
		String[][] matrix = mock.generatorDoc(100, 1000, "goodbye");
		DocTask task = new DocTask(matrix, 0, matrix.length, "goodbye");
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		
		do {
			System.out.println("************************START****************************");
			System.out.printf("Main: Task Count: %d.\n", pool.getQueuedTaskCount());
			System.out.printf("Main: Active Thread Count: %d.\n", pool.getActiveThreadCount());
			System.out.printf("Main: Thread Steal: %d.\n", pool.getStealCount());
			System.out.printf("Main: Thread Paralleism: %d.\n", pool.getParallelism());
			System.out.println("*************************END***************************");
			
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		
		pool.shutdown();
		
		try {
			pool.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.printf("DocumentMock: The word 'goodbye' appears %d times in the document.\n", task.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
