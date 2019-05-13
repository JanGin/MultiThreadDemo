package me.chan.executors.callable.delay;

import java.time.LocalDateTime;

public class RTask implements Runnable {

	private String name;
	
	public RTask (String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.printf("%s: starting at: %s\n", this.name, LocalDateTime.now());
	}

}
