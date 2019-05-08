package me.chan.cyclicbarrier.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {

	private int firstRow;
	private int lastRow;
	private int key;
	private Result res;
	private CyclicBarrier cyclicBarrier;
	private MatrixMock matrix;
	
	
	public Searcher(int firstRow, int lastRow, int key, 
				Result res, CyclicBarrier cyclicBarrier, MatrixMock matrix) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.key = key;
		this.res = res;
		this.cyclicBarrier = cyclicBarrier;
		this.matrix = matrix;
	}
	
	@Override
	public void run() {
		System.out.printf("%s processing line from %d to %d .\n", 
						Thread.currentThread().getName(), firstRow, lastRow);
		
		for (int i=firstRow; i<lastRow; i++) {
			//record the findings in each row
			int counter = 0;
			//FIXME eachRow may be null
			int[] eachRow = matrix.getRow(i);
			for (int j=0; j<eachRow.length; j++) {
				if (key == eachRow[j]) {
					counter++;
				}
			}
			res.setData(i, counter);
		}
		System.out.printf("%s Lines Proccessed.\n", Thread.currentThread().getName());
		
		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
