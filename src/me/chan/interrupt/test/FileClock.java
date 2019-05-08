package me.chan.interrupt.test;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable {

	@Override
	public void run() {
		for (int i=0; i<10; i++) {
			System.out.printf("%s\n", LocalTime.now());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				System.out.printf("The file clock has been interrupted\n");
			}
		}
	}

}
