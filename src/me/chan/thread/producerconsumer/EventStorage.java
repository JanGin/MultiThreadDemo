package me.chan.thread.producerconsumer;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class EventStorage {
	
	private int size;
	private Queue<Date> queue;
	
	public EventStorage() {
		size = 10;
		queue = new LinkedList<>();
	}
	
	public synchronized void produce() {
		while (size == queue.size()) {
			try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		queue.offer(new Date());
		System.out.printf("Product: %d\n", queue.size());
		notifyAll();
	}

	
	public synchronized void consumer() {
		while (0 == queue.size()) {
			try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		queue.poll();
		System.out.printf("Consumer : %d\n", queue.size());
		notifyAll();
	}
}
