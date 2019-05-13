package me.chan.executors.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppMain {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		CompletionService<String> service = new ExecutorCompletionService<>(executor);
		
		Thread faceThread = new Thread(new ReportRequest("face", service));
		Thread onlineThread = new Thread(new ReportRequest("online", service));
		ReportProcessor processor = new ReportProcessor(service);
		Thread processorThread = new Thread(processor);
		
		System.out.println("Main: Threads starting...");
		
		faceThread.start();
		onlineThread.start();
		processorThread.start();
		
		System.out.println("Main: Waiting for the report generator...");
		
		try {
			faceThread.join();
			onlineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main: shutting down ExecutorService...");
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		processor.setEnd(true);
		System.out.println("Main: The main thread ends.");
	}
}
