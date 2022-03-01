package atm_center;

import java.util.Scanner;

import bank.ValidatePin;
import card.*;
import type_of_transaction.*;

public class AtmMachine implements IAtmMachine{
	String address ;
	double levyPercBelow100 = 2;
	double levyPercAbove100 = 4;
	ITypeOfTransaction transaction = null;
	static Scanner sc = new Scanner(System.in);
	
	public AtmMachine() {
		this.address = "Tambaram";
	}

	public double getLevyPerc(double amount) {
		if(amount <= 100)
			return this.levyPercBelow100;
		else 
			return this.levyPercAbove100;
	}
	
	//withdraw money
	public void withdrawMoney(ICard card) {
		transaction = CashWithDraw.getTransactionType();
		if(ValidatePin.validatePin(card)) {
			System.out.println("Enter the amount multiples of 5");
			double amount = sc.nextDouble();
			//checks multiple of 5
			boolean isValidAmount = ValidateAmount.validateAmount(amount);
			if(isValidAmount) {
				double levyPerc = this.getLevyPerc(amount);
				boolean isAmountupdated = this.transaction.updateMoneyInAccount(card,amount,levyPerc);
				if(isAmountupdated)
					this.transaction.displayScreen(card,amount,levyPerc);
			}
			else 
				System.out.println("Please enter the amount multiple of 5");
		}
	}
	
	//Display money
	public void displayBalance(ICard card) {
		if(ValidatePin.validatePin(card)) {
			//singleton object
			DisplayBalance disBalance = DisplayBalance.getDisplayBalance();
			disBalance.displayBalance(card);
		}
	}
	
	//deposite money
	public void depositMoney(ICard card) {
		transaction = CashDeposit.getTransactionType();
		System.out.println("Enter the amount ");
		double amount = sc.nextDouble();
		if(ValidatePin.validatePin(card)) {
		boolean isAmountupdated = transaction.updateMoneyInAccount(card, amount,0);
			if(isAmountupdated)
				transaction.displayScreen(card, amount,0);
			else
				System.out.println("Server issue");
		}
	}
}
