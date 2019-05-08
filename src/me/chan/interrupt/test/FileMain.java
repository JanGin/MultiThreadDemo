package me.chan.interrupt.test;

import java.util.concurrent.TimeUnit;

public class FileMain {

	public static void main(String[] args) {
		Thread thread = new Thread(new FileClock());
		thread.start();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("�ж�ǰ:"+thread.isInterrupted());
		thread.interrupt();
		System.out.println("�жϺ�:"+thread.isInterrupted());
	}
}
