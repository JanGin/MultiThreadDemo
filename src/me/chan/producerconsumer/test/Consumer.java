package me.chan.producerconsumer.test;

public class Consumer implements Runnable {

	private EventStorage storage;
	
	public Consumer(EventStorage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		while (true)
			storage.consumer();
		
	}
}
