package me.chan.executors.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReportProcessor implements Runnable {

	
	private CompletionService<String> service;
	private Boolean isEnd;
	
	public ReportProcessor(CompletionService<String> service) {
		this.service = service;
		isEnd = false;
	}
	
	@Override
	public void run() {
		
		while (!isEnd) {
			try {
				Future<String> result = service.poll(20, TimeUnit.SECONDS);
				if (result != null) {
					String res = result.get();
					System.out.println("Receive result is : " + res);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Report Sender finished");
	}
	
	
	public void setEnd(boolean end) {
		this.isEnd = end;
	}

}
