package me.chan.thread.cyclicbarrier;

public class Grouper implements Runnable {

	private Result result;

	public Grouper(Result result) {
		this.result = result;
	}
	
	@Override
	public void run() {
		System.out.printf("Grouper: processing results...\n");
		int totalCount = 0;
		int[] res = result.getResult();
		for(int num : res) {
			totalCount += num;
		}
		
		System.out.printf("Grouper: process finished, totally find %d.\n", totalCount);
	}
	
}
