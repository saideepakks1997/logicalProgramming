package bank;

import java.util.Scanner;

import card.*;

public class ValidatePin {
	static Scanner sc = new Scanner(System.in);
	public static boolean validatePin(ICard card) {
		System.out.println("Enter the pin");
		int pin = sc.nextInt();
		if(!card.validatePin(pin)) {
			System.out.println("Enter valid pin");
			return false;
		}
		return true;
	}
}
