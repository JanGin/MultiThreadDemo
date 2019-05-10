package me.chan.thread.producerconsumer;

public class Producer implements Runnable {

	private EventStorage storage;
	
	public Producer(EventStorage storage) {
		this.storage = storage;
	}
	
	@Override
	public void run() {
		while (true)
			storage.produce();
	}

}
