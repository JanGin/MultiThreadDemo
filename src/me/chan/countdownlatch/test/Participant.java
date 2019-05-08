package me.chan.countdownlatch.test;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {

	private VideoConference vc;
	
	private final String name;
	
	public Participant(VideoConference vc, final String name) {
		this.vc = vc;
		this.name = name;
	}

	
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep((int)(Math.random()*10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		synchronized (vc) {
			vc.arrive(name);
		}*/
		vc.arrive(name);
	}
	

}
