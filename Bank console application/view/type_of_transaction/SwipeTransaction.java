package type_of_transaction;

import bank.CalculateLevyAndCashbackAmount;
import card.*;

public class SwipeTransaction extends ITypeOfTransaction{
	private static ITypeOfTransaction transcation = new SwipeTransaction();
	CalculateLevyAndCashbackAmount calculatelevyCashback 
	= CalculateLevyAndCashbackAmount.getLevyCashbackAmount();
	private SwipeTransaction() {
	
	}
	public static ITypeOfTransaction getTransactionType() {
		return transcation;
	}
	@Override
	public void displayScreen(ICard card, double amount,double cashBackPerc) {
		System.out.println("-----------------------------------------");
		double cashBack = calculatelevyCashback.calculateLevyAndCashbackAmount(amount, cashBackPerc);
		
		System.out.println("Amount :-"+amount);
		System.out.println("Cash back :- "+cashBack);
		System.out.println("Available balance :- "+card.getAccount().getBankBalance());
		
		System.out.println("-----------------------------------------");
	}
}
