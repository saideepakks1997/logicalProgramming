package common_view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import payment_options.AdminPaymentOptions;
import payment_options.ConsumerPaymentOptions;
import validator_encrypter.Validator;

public class UserInputView {
	Validator validate = new Validator();
	Scanner sc = new Scanner(System.in);
	DisplayView display = new DisplayView();
	public int getInt() {
		int chances = 1;
		boolean loop = true;
		int opt = 0;
		while(loop) {
			loop = false;
			try {
				opt = sc.nextInt();
			}
			catch(InputMismatchException exception){
				if(chances >= validate.getMaxChance()) {
					display.displayChancesMessege();
					display.displayMessege("Going back to previous menu");
					sc = new Scanner(System.in);
					return -1;
				}
				chances++;
				display.displayMessege("Wrong input enter correct input(i.e) number");
				System.out.println("Re enter the option");
				loop = true;
				sc = new Scanner(System.in);
			}
			
		}
		return opt;
	}
	public long getLong() {
		int chances = 1;
		boolean loop = true;
		long val = 0;
		while(loop) {
			loop = false;
			try {
				val = sc.nextLong();
			
			}
			catch(InputMismatchException exception){
				if(chances >= validate.getMaxChance()) {
					display.displayChancesMessege();
					display.displayMessege("Going back to previous menu");
					sc = new Scanner(System.in);
					return -1;
				}
				chances++;
				display.displayMessege("Wrong input enter correct input(i.e) number");
				System.out.println("Re enter the number");
				loop = true;
				sc = new Scanner(System.in);
			}
		}
		return val;
	}
	public String getString() {
		sc.skip("((?<!\\R)\\s)*");
		String value = sc.nextLine();
		return value;
	}
	
}
