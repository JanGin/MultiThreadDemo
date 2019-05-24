package me.chan.forkjoin.sync.recursivetask;

import java.util.Random;

public class DocumentMock {

	private static String[] words = {
			"hello world",
			"java",
			"ThreadPool",
			"goodbye",
			"main",
			"packet"
	};
	
	public String[][] generatorDoc(int lineNums, int lineWordNums, String word) {
		String[][] matrix = new String[lineNums][lineWordNums];
		Random random = new Random();
		int count = 0;
		for (int i=0; i < lineNums; i++) {
			for (int j=0; j < lineWordNums; j++) {
				String genword = words[random.nextInt(words.length)];
				matrix[i][j] = genword;
				
				if (genword.equals(word))
					count++;
			}
		}
		System.out.printf("DocumentMock: The word '%s' appears %d times in the document.\n",
								word, count);
		return matrix;
	}
	
}
