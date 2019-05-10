package me.chan.exchanger.test;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 *  One to One Producer-Consumer Case
 *  Consumer 
 */
public class Consumer implements Runnable {
	
	private List<String> buffer;
	private Exchanger<List<String>> exchanger;
	
	public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;
		for (int i=0; i < 10; i++) {
			System.out.printf("Consumer: Cycle %d\n", cycle);
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("Consumer: get %d in Cycle %d.\n", buffer.size(), cycle);
			for (int j=0; j < 10; j++) {
				String msg = buffer.get(0);
				System.out.println("Consumer consume: " + msg);
				buffer.remove(0);
			}
		}
	}

	
}
