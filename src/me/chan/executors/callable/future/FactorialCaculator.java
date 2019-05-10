package me.chan.executors.callable.future;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCaculator implements Callable<Long> {

	private int number;
	
	public FactorialCaculator(int number) {
		if (number < 0)
			throw new IllegalArgumentException("the given number must be positive");
		
		this.number = number;
	}
	
	
	@Override
	public Long call() throws Exception {
		long result = factorialCal();
		System.out.printf("%s the returned result is : %d.\n",
				Thread.currentThread().getName(), result);
		return result;
	}
	
	
	private long factorialCal() throws Exception {
		long result = -1L;
		if (number == 0 || number == 1) {
			result = 1;
		} else {
			result = 1;
			for (int i=2; i < number; i++) {
				result *= i;
				
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		
		return result;
	}
	
}
