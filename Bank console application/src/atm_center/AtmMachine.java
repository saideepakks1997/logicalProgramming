package atm_center;

import java.util.Scanner;


import card.Card;
import type_of_transaction.*;

public class AtmMachine implements IAtmMachine{
	ITypeOfTransaction transaction;
	static Scanner sc = new Scanner(System.in);
	public AtmMachine() {
		this.transaction = new CashWithDraw();
	}
	//display balance is sub class specific because display balace
	//is valid only for debit card not credit card
	@Override
	public void displayBalance(Card card) {
		if(ValidatePin.validatePin(card)) {
			System.out.println(card.getAccount().getBalance());
			return;
		}
	}

	@Override
	public void withDrawMoney(Card card) {
		if(ValidatePin.validatePin(card)) {
			System.out.println("Enter the amount multiples of 5");
			double amount = sc.nextDouble();
			//checks multiple of 5
			boolean isValidAmount = ValidateAmount.validateAmount(amount);
			if(isValidAmount) {
				boolean isAmountupdated = this.transaction.updateMoneyInAccount(card,amount);
				if(isAmountupdated) {
					this.transaction.displayScreen(card,amount);
				}
					
			}
			else {
				System.out.println("Please enter the amount multiple of 5");
			}
		}
	}
	
	

}
