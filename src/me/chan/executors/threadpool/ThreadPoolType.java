package me.chan.executors.threadpool;

public enum ThreadPoolType {
	SINGLE,
	CACHED,
	FIXED,
	SCHEDULED;
	
	
	public String getName() {
		return this.name().toLowerCase();
	}
	
	public static void main(String[] args) {
		System.out.println(ThreadPoolType.CACHED.getName());
	}
}
