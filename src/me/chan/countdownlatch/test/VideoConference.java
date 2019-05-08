package me.chan.countdownlatch.test;

import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable {

	private CountDownLatch latch;
	
	public VideoConference(int number) {
		latch = new CountDownLatch(number);
	}
	
	@Override
	public void run() {
		
		System.out.printf("VideoConference Initialization: %d participants\n", latch.getCount());
		try {
			latch.await();
			Thread.sleep(100);
			System.out.println("VideoConference: All the participants have come.");
			System.out.println("Let's start.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void arrive(String name) {
		System.out.printf("%s has arrived\n", name);
		latch.countDown();
		System.out.printf("VedioConference: waiting for %d participants.\n", latch.getCount());
	}
}
