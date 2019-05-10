package me.chan.thread.cyclicbarrier;

public class Result {

	private int[] res;
	
	public Result(int size) {
		res = new int[size];
	}
	
	public void setData(int position, int value) {
		if (position < 0 || position >= res.length)
			throw new ArrayIndexOutOfBoundsException();
		
		res[position] = value;
	}
	
	public int[] getResult() {
		return res;
	}
}
