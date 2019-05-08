package me.chan.cyclicbarrier.test;

import java.util.Random;

public class MatrixMock {

	private int[][] matrix;
	
	public MatrixMock(int size, int length, int key) {
		matrix = new int[size][length];
		int counter = 0;
		Random random = new Random();
		for (int i=0; i<size; i++) {
			for (int j=0; j<length; j++) {
				matrix[i][j] = random.nextInt(10);
				if (matrix[i][j] == key)
					++counter;
			}
		}
		System.out.printf("Mock: There are %d ocurrences of %d in generated data.\n", 
							counter, key);
	}
}
