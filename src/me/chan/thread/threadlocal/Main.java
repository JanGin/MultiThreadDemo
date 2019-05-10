package me.chan.thread.threadlocal;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		for (int i=0; i<10; i++) {
			Thread thread = new Thread(new UnSafeTask());
			//Thread thread = new Thread(new SafeTask());
			thread.start();
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
