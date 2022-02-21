package outdoor_sports;

public class HockeyPracticeSession extends OutdoorSportPracticeSession{
	int noOfGoalKeeper;
	public HockeyPracticeSession() {
		this.noOfPlayerInPracticeSession = 14;
		this.sportName = "Hockey";
		this.coachName = "Surendar";
		this.noOfGoalKeeper = 1;
	}
	
	public void doDribblePractice() {
		System.out.println(noOfPlayerInPracticeSession+" players doing dribbling as ordered by coach "
				+ " "+coachName);
	}
	public void hitTheBallToGoalKeeper() {
		System.out.println((noOfPlayerInPracticeSession-noOfGoalKeeper)+""
				+ " hitting the ball at the goal keeper");
				
	}
}
