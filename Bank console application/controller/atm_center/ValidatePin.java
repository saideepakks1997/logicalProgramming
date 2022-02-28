package atm_center;

import java.util.Scanner;

import card.*;

public interface ValidatePin {
	static Scanner sc = new Scanner(System.in);
	public static boolean validatePin(Card card) {
		System.out.println("Enter pin number");
		int pin = sc.nextInt();
		if(card.getPin() == pin) {
			return true;
		}
		System.out.println("Enter valid pin");
		return false;
	}
}
