package type_of_transaction;

import account.*;
import bank.Bank;
import bank.CalculateLevyAndCashbackAmount;
import card.*;

public class SwipeTransaction extends ITypeOfTransaction{
	private static ITypeOfTransaction transcation = new SwipeTransaction();
	private SwipeTransaction() {
	
	}
	public static ITypeOfTransaction getTransactionType() {
		return transcation;
	}
	@Override
	public void displayScreen(ICard card, double amount,double cashBackPerc) {
		System.out.println("-----------------------------------------");
		double cashBack = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, cashBackPerc);
		
		System.out.println("Amount :-"+amount);
		System.out.println("Cash back :- "+cashBack);
		System.out.println("Available balance :- "+card.getAccount().getBankBalance());
		
		System.out.println("-----------------------------------------");
	}

	@Override
	public boolean updateMoneyInAccount(ICard card, double amount,double cashBackPerc) {
		Bank bank = Bank.getBank();
		IAccount account = card.getAccount();
		double cashbackAmount = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, cashBackPerc);
		if(account.getBankBalance() - amount >= bank.getMinimumBalance()) {
			account.setBankBalance((account.getBankBalance() - amount )+ cashbackAmount);
			return true;
		}
		else {
			System.out.println("Insufficient funds");
			return false;
		}		
	}
}
