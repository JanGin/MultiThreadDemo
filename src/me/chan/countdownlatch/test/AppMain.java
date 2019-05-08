package me.chan.countdownlatch.test;

public class AppMain {

	public static void main(String[] args) {
		
		VideoConference vc = new VideoConference(10);
		Thread conference = new Thread(vc);
		conference.start();
		
		for (int i=1; i<=10; i++) {
			Thread participant = new Thread(new Participant(vc, "Participant-"+i));
			participant.start();
		}
	}
}
