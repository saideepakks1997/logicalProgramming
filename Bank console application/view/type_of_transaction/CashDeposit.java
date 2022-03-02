package type_of_transaction;

import account.*;
//import account.CalculateLevyAndCashbackAmount;
import card.*;

public class CashDeposit extends ITypeOfTransaction{
	private static ITypeOfTransaction transcation = new CashDeposit();
	private CashDeposit() {
	
	}
	public static ITypeOfTransaction getTransactionType() {
		return transcation;
	}
	
	@Override
	public void displayScreen(ICard card, double amount,double perc) {
		System.out.println("-----------------------------------------");
		
		System.out.println("Amount :-"+amount);
		System.out.println("Available balance :- "+card.getAccount().getBankBalance());
		
		System.out.println("-----------------------------------------");
	}
}
