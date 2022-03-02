package atm_center;

import java.util.Scanner;

import account.*;
import account.IAccount;
import bank.Bank;
import bank.CalculateLevyAndCashbackAmount;
import bank.ValidatePin;
import card.*;
import type_of_transaction.*;

public class AtmMachine implements IAtmMachine{
	String address ;
	ITypeOfTransaction transactionType = null;
	static Scanner sc = new Scanner(System.in);
	static AmountTransaction amountTransaction = new AmountTransaction();
	
	public AtmMachine() {
		this.address = "Tambaram";
	}
	
	//withdraw money
	public void withdrawMoney(ICard card) {
		Bank bank = Bank.getBank();
		transactionType = CashWithDraw.getTransactionType();//for displaying
		if(ValidatePin.validatePin(card)) {
			System.out.println("Enter the amount multiples of 5");
			double amount = sc.nextDouble();
			//checks multiple of 5
			boolean isValidAmount = this.validateAmount(amount);
			if(isValidAmount) {
				IAccount account = card.getAccount();
				
				double bankBalance = account.getBankBalance();
				double levyPerc = bank.getLevyPerc(amount);
				double levyCharges = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, levyPerc);
				//checking minimum balance
				boolean isTransactionPossible = amountTransaction.checkTransactionPossible(amount+levyCharges, card);
				//do transaction
				boolean isTransactionDone = amountTransaction.updateBankBalance(card, bankBalance - (amount+levyCharges));
				
				if(isTransactionDone) {
					transactionType.displayScreen(card,amount,levyPerc);
				}
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
		transactionType = CashDeposit.getTransactionType();
		IAccount account = card.getAccount();
		double bankBalance = account.getBankBalance();
		System.out.println("Enter the amount ");
		double amount = sc.nextDouble();
		if(ValidatePin.validatePin(card)) {
			double amountAfterTransaction = bankBalance + amount;
			amountTransaction.updateBankBalance(card, amountAfterTransaction);
			transactionType.displayScreen(card, amount,0);
		}
	}
	
	//Validate amount(i.e) check multiples of 5
	public boolean validateAmount(double amount) {
		return (amount % 5 == 0);
	}
}
