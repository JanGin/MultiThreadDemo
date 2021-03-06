package me.chan.thread.cyclicbarrier;

import java.util.Random;

public class MatrixMock {

	private int[][] matrix;
	
	public MatrixMock(int rows, int cols, int key) {
		matrix = new int[rows][cols];
		int counter = 0;
		Random random = new Random();
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				matrix[i][j] = random.nextInt(10);
				if (matrix[i][j] == key)
					++counter;
			}
		}
		System.out.printf("Mock: There are %d ocurrences of %d in generated data.\n", 
							counter, key);
	}
	
	public int[] getRow(int row) {
		int[] res = null;
		if (row >= 0 && row < matrix.length) {
			res = matrix[row];
		}
		
		return res;
	}
}
