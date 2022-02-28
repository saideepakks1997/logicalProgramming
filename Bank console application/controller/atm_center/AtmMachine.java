package atm_center;

import java.util.Scanner;


import card.Card;
import type_of_transaction.*;

public class AtmMachine implements IAtmMachine{
	double levyPercBelow100 = 2;
	double levyPercAbove100 = 4;
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
	public double getLevyPerc(double amount) {
		if(amount <= 100)
			return this.levyPercBelow100;
		else 
			return this.levyPercAbove100;
	}
	
	public double calculateLevyPerc(double amount) {
		if(amount <= 100)
			return levyPercBelow100;
		else
			return levyPercAbove100;
	}
	@Override
	public ITypeOfTransaction getTypeOfTransaction() {
		return this.transaction;
	}

}
