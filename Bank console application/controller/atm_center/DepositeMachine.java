package atm_center;

import java.util.Scanner;

import card.Card;
import type_of_transaction.CashDeposit;
import type_of_transaction.ITypeOfTransaction;

public class DepositeMachine implements IDepositeMachine{
	ITypeOfTransaction transaction;
	
	static Scanner sc = new Scanner(System.in);
	public DepositeMachine() {
		this.transaction = new CashDeposit();
	}
	public void depositMoney(Card card) {
		System.out.println("Enter the amount ");
		double amount = sc.nextDouble();
		if(ValidatePin.validatePin(card)) {
			boolean isAmountupdated = this.transaction.updateMoneyInAccount(card, amount,0);
			if(isAmountupdated)
				this.transaction.displayScreen(card, amount,0);
			else
				System.out.println("Server issue");
		}
		
	}
	@Override
	public ITypeOfTransaction getTypeOfTransaction() {
		return this.transaction;
	}
	
	
}
