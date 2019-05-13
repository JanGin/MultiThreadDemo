package me.chan.executors.rejecthandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedTaskHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.printf("RejectedExecutionHandler: The task %s has been rejected.\n", r.toString());
		System.out.println("RejectedExecutionHandler: " + executor.toString());
		System.out.println("RejectedExecutionHandler: Terminating? " + executor.isTerminating());
		System.out.println("RejectedExecutionHandler: Terminated? " + executor.isTerminated());
		
	}

}
