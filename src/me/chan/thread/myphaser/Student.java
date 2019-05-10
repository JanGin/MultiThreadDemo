package me.chan.thread.myphaser;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable {
	
	private MyPhaser phaser;
	
	public Student(MyPhaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
		System.out.printf("The %s is arrived and ready for exam at %s.\n", 
				Thread.currentThread().getName(), LocalDateTime.now().toString());
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("The %s is going to do the 1st exercise.\n", 
				Thread.currentThread().getName());
		exercise();
		System.out.printf("%s has done the 1st exercise at %s.\n", 
				Thread.currentThread().getName(), LocalDateTime.now().toString());
		phaser.arriveAndAwaitAdvance();	
		
		System.out.printf("The %s is going to do the 2nd exercise.\n", 
				Thread.currentThread().getName());
		exercise();
		System.out.printf("%s has done the 2nd exercise at %s.\n", 
				Thread.currentThread().getName(), LocalDateTime.now().toString());
		phaser.arriveAndAwaitAdvance();
		
		System.out.printf("The %s is going to do the 3rd exercise.\n", 
				Thread.currentThread().getName());
		exercise();
		System.out.printf("%s has finished the exam at %s.\n", 
				Thread.currentThread().getName(), LocalDateTime.now().toString());
		phaser.arriveAndAwaitAdvance();
	}

	private void exercise() {
		try {
			TimeUnit.SECONDS.sleep((int)(Math.random()*5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
