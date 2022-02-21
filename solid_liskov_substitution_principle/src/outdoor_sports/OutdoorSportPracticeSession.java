package outdoor_sports;

public class OutdoorSportPracticeSession {
		String sportName;
		String coachName;
		int noOfPlayerInPracticeSession;
		
		public void doWorkOut() {
			System.out.println("Do work out for 2 hours as ordered by coach "+this.coachName);
		}
		public void jog() {
			System.out.println("Complete 10 round of jogging as ordered by coach "+this.coachName);
		}
		public void takeBreak() {
			System.out.println("take break after jogging ");
		}
		public void startPracticeMatch() {
			System.out.println("split into "+(noOfPlayerInPracticeSession/2)+""
					+ " players per team and start the match");
		}
}
