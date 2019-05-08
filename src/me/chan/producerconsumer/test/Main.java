package me.chan.producerconsumer.test;

public class Main {

	public static void main(String[] args) {
		EventStorage storage = new EventStorage();
		Thread producer = new Thread(new Producer(storage));
		Thread consumer = new Thread(new Consumer(storage));
		producer.start();
		consumer.start();
	}

}
