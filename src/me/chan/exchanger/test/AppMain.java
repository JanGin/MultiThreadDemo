package me.chan.exchanger.test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class AppMain {

	public static void main(String[] args) {
		List<String> buffer1 = new LinkedList<>();
		List<String> buffer2 = new LinkedList<>();
		Exchanger<List<String>> exchanger = new Exchanger<>();
	
		Thread producer = new Thread(new Producer(buffer1, exchanger));
		Thread consumer = new Thread(new Consumer(buffer2, exchanger));
		producer.start();
		consumer.start();
		
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Case execution completed.");
		}
	}
}
