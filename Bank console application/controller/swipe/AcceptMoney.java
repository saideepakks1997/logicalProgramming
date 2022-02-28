package swipe;

import java.util.Scanner;

import atm_center.ValidatePin;
import card.Card;

public interface AcceptMoney {
	static Scanner sc = new Scanner(System.in);
	public static boolean acceptMoney(Card card,ISwipe swipe) {
		System.out.println("Enter amount");
		double amount = sc.nextDouble();
		double cashBackPerc = card.getAccount().getBank().getCashBackPerc();
		if(ValidatePin.validatePin(card)) {
			boolean isAmountupdated = swipe.getTypeOfTransaction().updateMoneyInAccount(card,amount,cashBackPerc);
			if(isAmountupdated) {
				swipe.getTypeOfTransaction().displayScreen(card,amount,cashBackPerc);
				return true;
			}
			else {
				System.out.println("Insufficient funds");
				return false;
			}
		}
		return false;
	}
}
