package me.chan.phaser.test;

import java.util.concurrent.Phaser;

public class AppMain {

	public static void main(String[] args) {
		String suffix = ".log";
		Phaser phaser = new Phaser(2);
		FileSearcher apps = new FileSearcher("C:\\Program Files", suffix, phaser);
		FileSearcher system = new FileSearcher("C:\\Windows", suffix, phaser);
		
		Thread appThread = new Thread(apps, "apps");
		Thread sysThread = new Thread(system, "sys");
		
		appThread.start();
		sysThread.start();
		
		try {
			appThread.join();
			sysThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Terminated ? " + phaser.isTerminated());
	}

}
