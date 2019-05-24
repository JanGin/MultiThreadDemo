package me.chan.forkjoin.sync.recursiveaction;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {

	private static final long serialVersionUID = 1L;

	private int first;
	private int last;
	private List<Product> list;
	private double increment;
	
	public Task(int first, int last, List<Product> list, double increment) {
		this.first = first;
		this.last = last;
		this.list = list;
		this.increment = increment;
	}
	
	
	//if the size of products is greater than 10, then divides it to 2 subtasks to process
	@Override
	protected void compute() {
		if (Math.abs(this.first-this.last) <= 10) {
			updatePrices();
		} else {
			int middle = (first+last) / 2;
			System.out.printf("Task: Pending tasks:%s\n", getQueuedTaskCount());
			Task taskPre = new Task(first, middle, list, increment);
			Task taskNxt = new Task(middle+1, last, list, increment);
			//synchronous call
			invokeAll(taskPre, taskNxt);
		}
		
	}

	private void updatePrices() {
		for (int i=first; i<last; i++) {
			Product product = list.get(i);
			product.setPrice(product.getPrice() *(1+increment));
		}
	}

}
