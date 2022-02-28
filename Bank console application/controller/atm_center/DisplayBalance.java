package atm_center;

import card.Card;

public interface DisplayBalance {
	public static void displayBalance(Card card) {
		if(ValidatePin.validatePin(card)) {
			System.out.println("-------------------------");
			double balance = card.getAccount().getBalance();
			System.out.println("Current balance:-"+balance);
			System.out.println("-------------------------");
			return;
		}
		
		
	}
}
