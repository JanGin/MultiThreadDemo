package me.chan.semaphore.test;

public class AppMain {

	public static void main(String[] args) {
		
		PrintQueue queue = new PrintQueue();
		
		Thread[] jobs = new Thread[5];
		for (int i=0; i<jobs.length; i++) {
			jobs[i] = new Thread(new Job(queue), "Thread-"+i);
		}
		
		for (Thread job : jobs) {
			job.start();
		}
	}

}
