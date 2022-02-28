package atm_center;

import java.util.Scanner;

import card.Card;

public interface DepositMoney {
	static Scanner sc = new Scanner(System.in);
	public static void depositMoney(Card card,IDepositeMachine depositeMachine) {
		System.out.println("Enter the amount ");
		double amount = sc.nextDouble();
		if(ValidatePin.validatePin(card)) {
		boolean isAmountupdated = depositeMachine.getTypeOfTransaction().updateMoneyInAccount(card, amount,0);
			if(isAmountupdated)
				depositeMachine.getTypeOfTransaction().displayScreen(card, amount,0);
			else
				System.out.println("Server issue");
		}
		
	}
}
