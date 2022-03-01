package swipe;

import java.util.Scanner;

import bank.ValidatePin;
import card.*;
import type_of_transaction.*;

public class SwipeMachine implements ISwipe{
	ITypeOfTransaction transaction = null;
	static Scanner sc = new Scanner(System.in);
	

	@Override
	public boolean acceptMoney(ICard card) {
		transaction = SwipeTransaction.getTransactionType();
		System.out.println("Enter amount");
		double amount = sc.nextDouble();
		double cashBackPerc = 1;
		if(ValidatePin.validatePin(card)) {
			boolean isAmountupdated = transaction.updateMoneyInAccount(card,amount,cashBackPerc);
			if(isAmountupdated) {
				transaction.displayScreen(card,amount,cashBackPerc);
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
