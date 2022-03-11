package common_view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommonView {
	Scanner sc = new Scanner(System.in);
	public void displayMessege(String str) {
		System.out.println("----------------------------");
		System.out.println(str);
		System.out.println("----------------------------");
	}
	
	public int getOption() {
		boolean loop = true;
		int opt = 0;
		while(loop) {
			loop = false;
			try {
				opt = sc.nextInt();
			
			}
			catch(InputMismatchException exception){
				displayMessege("Wrong input enter correct input(i.e) number");
				System.out.println("Re enter the option");
				loop = true;
				sc = new Scanner(System.in);
			}
		}
		
		return opt;
	}
}
