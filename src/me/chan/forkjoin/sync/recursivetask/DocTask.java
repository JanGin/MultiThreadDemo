package me.chan.forkjoin.sync.recursivetask;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DocTask extends RecursiveTask<Integer> {

	
	private static final long serialVersionUID = 1L;
	private String[][] docs;
	private int start, end;
	private String word;
	
	
	public DocTask(String[][] docs, int start, int end, String word) {
		this.docs = docs;
		this.start = start;
		this.end = end;
		this.word = word;
	}
	
	@Override
	protected Integer compute() {
		Integer res = null;
		if (Math.abs(start - end) < 10) {
			res = processLines(docs, start, end, word);
		} else {
			int mid = (start + end) / 2; 
			DocTask task1 = new DocTask(docs, start, mid, word);
			DocTask task2 = new DocTask(docs, mid+1, end, word);
			invokeAll(task1, task2);
			
			try {
				res = groupResult(task1, task2);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	private Integer groupResult(DocTask... task) throws InterruptedException, ExecutionException {
		Integer result = 0;
		for (DocTask dt : task) {
			result += dt.get();
		}
		return result;
	}

	private Integer processLines(String[][] docs, int start, int end, String word) {
		Integer result = 0;
		List<LineTask> list = new LinkedList<>();
		for (int i=start; i<end; i++) {
			LineTask task = new LineTask(docs[i], 0, docs[i].length, word);
			list.add(task);
		}
		invokeAll(list);
		for (LineTask t : list) {
			try {
				result += t.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}


}
