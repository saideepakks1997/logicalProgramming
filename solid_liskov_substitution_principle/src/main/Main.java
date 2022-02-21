package main;

import java.util.Scanner;

import outdoor_sports.CricketPracticeSession;
import outdoor_sports.HockeyPracticeSession;
import outdoor_sports.OutdoorSportPracticeSession;

public class Main {
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {
		System.out.println("Select to enter into practice session \n"
				+ "1->Hockey Practice Session\n"
				+ "2->Cricket practice Session");
		int opt = sc.nextInt();
		switch (opt) {
		case 1: startHockeyPracticeSession();
			break;
		case 2: startCricketPracticeSession();
			break;
		default:
			break;
		}
	}
	private static void startCricketPracticeSession() {
		System.out.println("---------------------------");
		OutdoorSportPracticeSession workOut = new CricketPracticeSession();
		workOut.jog();
		workOut.doWorkOut();
		
		CricketPracticeSession practice = (CricketPracticeSession)workOut;
		practice.doBattingPractice();
		practice.doFieldingPractice();
		practice.startPracticeMatch();
		System.out.println("---------------------------");
	}
	private static void startHockeyPracticeSession() {
		System.out.println("---------------------------");
			OutdoorSportPracticeSession workOut = new HockeyPracticeSession();
			workOut.jog();
			workOut.doWorkOut();
			HockeyPracticeSession practice = (HockeyPracticeSession)workOut;
			practice.doDribblePractice();
			practice.hitTheBallToGoalKeeper();
			practice.startPracticeMatch();
			System.out.println("---------------------------");
	}
}
