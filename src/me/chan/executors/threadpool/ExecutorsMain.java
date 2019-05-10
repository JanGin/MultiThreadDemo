package me.chan.executors.threadpool;

public class ExecutorsMain {

	public static void main(String[] args) {
		Server server = new Server();
		for (int i=1; i <= 20; i++) {
			Task task = new Task("Task-"+i);
			server.execute(task);
		}
		
		server.shutdown();
		System.out.println("Is server terminated? :" + server.isTerminated());
	}

}
