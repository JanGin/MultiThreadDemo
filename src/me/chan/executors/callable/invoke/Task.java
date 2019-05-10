package me.chan.executors.callable.invoke;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Result> {

	private String name;
	
	public Task(String name) {
		this.name = name;
	}
	
	@Override
	public Result call() throws Exception {
		System.out.printf("%s starting.\n", this.name);
		long duration = (long) (Math.random()*100);
		TimeUnit.MILLISECONDS.sleep(duration);
		
		int value = 0;
		for (int i=0; i<5; i++) {
			value += (int) (Math.random()*100);
		}
		
		Result result = new Result(this.name, value);
		return result;
	}

	
}
