package me.chan.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class AppMain {
	
	public static void main(String[] args) {
		final int ROWS = 10_000;
		final int COLS = 1000;
		final int KEY = 5;
		final int PARTICIPANTS = 5;
		final int LINE_PARTICIPANTS = 2000;
		
		MatrixMock matrix = new MatrixMock(ROWS, COLS, KEY);
		Result result = new Result(ROWS);
		Grouper grouper = new Grouper(result);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTICIPANTS, grouper);
		
		Searcher[] searchers = new Searcher[PARTICIPANTS];
		for (int i=0; i<searchers.length; i++) {
			searchers[i] = new Searcher(i*LINE_PARTICIPANTS, 
					i*LINE_PARTICIPANTS+LINE_PARTICIPANTS,
					KEY, result, cyclicBarrier, matrix);
			
			Thread thread = new Thread(searchers[i], "PARTICIPANTS-"+i);
			thread.start();
		}
		
		System.out.println("The Main Thread is finished");
	}

}
