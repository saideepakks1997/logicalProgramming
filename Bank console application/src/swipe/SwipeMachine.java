package swipe;

import java.util.Scanner;

import atm_center.ValidatePin;
import card.Card;
import type_of_transaction.ITypeOfTransaction;
import type_of_transaction.SwipeTransaction;

public class SwipeMachine implements ISwipe{
	ITypeOfTransaction transaction;
	public SwipeMachine() {
		this.transaction = new SwipeTransaction();
	}
	static Scanner sc = new Scanner(System.in);
	@Override
	public boolean acceptMoney(Card card) {
		System.out.println("Enter amount");
		double amount = sc.nextDouble();
		if(ValidatePin.validatePin(card)) {
			boolean isAmountupdated = this.transaction.updateMoneyInAccount(card,amount);
			if(isAmountupdated) {
				this.transaction.displayScreen(card,amount);
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
