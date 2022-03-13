package common_view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommonView {
	Scanner sc  = new Scanner(System.in);
	public void displayMessege(Object messege) {
		System.out.println("-----------------------");
		System.out.println(messege);
		System.out.println("-----------------------");
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
	public String getString() {
		sc.skip("((?<!\\R)\\s)*");
		String value = sc.nextLine();
		return value;
	}
	public long getLong() {
		boolean loop = true;
		long val = 0;
		while(loop) {
			loop = false;
			try {
				val = sc.nextLong();
			
			}
			catch(InputMismatchException exception){
				displayMessege("Wrong input enter correct input(i.e) number");
				System.out.println("Re enter the number");
				loop = true;
				sc = new Scanner(System.in);
			}
		}
		return val;
	}
	
	public long getConnectionNo() {
		System.out.println("Enter connection number");
		long connNo = getLong();
		return connNo;
	}
}
