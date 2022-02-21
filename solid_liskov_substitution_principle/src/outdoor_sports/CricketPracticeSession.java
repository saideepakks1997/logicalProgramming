package outdoor_sports;

public class CricketPracticeSession extends OutdoorSportPracticeSession{
	int noOfWiketKeeper;
	int noOfBatsman;
	int noOfBolwer;
	int noOfAllrounder;
	public CricketPracticeSession() {
		this.noOfPlayerInPracticeSession = 20;
		this.sportName = "Cricket";
		this.coachName = "Bala";
		this.noOfBatsman = 6;
		this.noOfBolwer = 6;
		this.noOfAllrounder = 6;
		this.noOfWiketKeeper = 2;
	}
	
	public void doBattingPractice() {
		System.out.println(noOfBatsman+" doing batting practice");
	}
	public void doFieldingPractice() {
		System.out.println(noOfPlayerInPracticeSession+" doing fielding practice");
	}
}
