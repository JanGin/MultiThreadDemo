package me.chan.myphaser.test;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {

	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch (phase) {
			case 0:
				return studentArrived();
			case 1:
				return finishFirstExercise();
			case 2:
				return finishSecondExercise();
			case 3:
				return finishExamination();
			default:
				return true;
		}
	}

	private boolean studentArrived() {
		System.out.println("The exam is going to start. Students are ready");
		System.out.printf("We have %d student(s) totally.\n", getRegisteredParties());
		return false;
	}

	private boolean finishFirstExercise() {
		System.out.println("All students have finished the first exercise "
				+ "and they going to start the second one.\n");
		return false;
	}

	private boolean finishSecondExercise() {
		System.out.println("All students have finished the second exercise "
				+ "and they going to start the third one.\n");
		return false;
	}

	private boolean finishExamination() {
		System.out.println("All students have finished the examination. \n");
		return true;
	}

	
}
