package type_of_transaction;

import card.*;

public class DisplayBalance {
	private static DisplayBalance displayBalance = new DisplayBalance();
	public void displayBalance(ICard card) {
		System.out.println("-------------------------");
		double balance = card.getAccount().getBankBalance();
		System.out.println("Current balance:-"+balance);
		System.out.println("-------------------------");
		return;
	}
	private DisplayBalance(){
		
	}
	public static DisplayBalance getDisplayBalance() {
		return displayBalance;
	}
}
