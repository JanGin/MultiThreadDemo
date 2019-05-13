package me.chan.executors.callable.delay;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class Task implements Callable<String> {

	
	private String name;
	
	public Task (String name) {
		this.name = name;
	}
	
	
		
	
	@Override
	public String call() throws Exception {
		
		System.out.printf("%s: starting at: %s\n", this.name, LocalDateTime.now());
		return "called";
	}

}
