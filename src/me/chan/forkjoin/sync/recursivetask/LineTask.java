package me.chan.forkjoin.sync.recursivetask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class LineTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	private String[] line;
	private int start;
	private int end;
	private String word;
	
	public LineTask(String[] line, int start, int end, String word) {
		this.line = line;
		this.start = start;
		this.end = end;
		this.word = word;
	}
	
	@Override
	protected Integer compute() {
		Integer result = 0;
		if (Math.abs(start - end) < 100) {
			result = count(line, start, end, word);
		} else {
			int mid = (start + end) / 2;
			LineTask task1 = new LineTask(line, start, mid, word);
			LineTask task2 = new LineTask(line, mid+1, end, word);
			//synchronous call
			invokeAll(task1, task2);
			try {
				result = groupResult(task1, task2);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Integer groupResult(LineTask... task) throws InterruptedException, ExecutionException {
		Integer result = 0;
		for (LineTask t:task) {
			result += t.get();
		}
		return result;
	}

	private Integer count(String[] line, int start, int end, String word) {
		Integer counter = 0;
		for (int i=start; i < end; i++) {
			if (word.equals(line[i]))
				counter++ ;
		}
		return counter;
	}

}
