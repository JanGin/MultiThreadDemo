package me.chan.thread.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * One to One Producer-Consumer Case
 * Producer
 */
public class Producer implements Runnable {

	private List<String> buffer;
	private Exchanger<List<String>> exchanger;
	
	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}
	
	
	@Override
	public void run() {
		int cycle = 1;
		for (int i=0; i < 10; i++) {
			System.out.printf("Procuder: Cycle %d\n", cycle);
			for (int j=0; j < 10; j++) {
				String msg =  "Event-" + (i*10+j);
				System.out.println("Produced:" + msg);
				buffer.add(msg);
			}
			
			try {
				exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("Producer: produced %d in Cycle %d.\n", buffer.size(), cycle);
			++cycle;
		}
	}

}
