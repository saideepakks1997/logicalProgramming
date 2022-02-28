package type_of_transaction;

import account.*;
import card.Card;

public class SwipeTransaction implements ITypeOfTransaction{

	@Override
	public void displayScreen(Card card, double amount,double cashBackPerc) {
		System.out.println("-----------------------------------------");
		double cashBack = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, cashBackPerc);
		
		System.out.println("Amount :-"+amount);
		System.out.println("Cash back :- "+cashBack);
		System.out.println("Available balance :- "+card.getAccount().getBankBalance());
		
		System.out.println("-----------------------------------------");
	}

	@Override
	public boolean updateMoneyInAccount(Card card, double amount,double cashBackPerc) {
		Account account = card.getAccount();
		double cashbackAmount = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, cashBackPerc);
		if(account.getBankBalance() - amount >= account.getMinimumBalance()) {
			account.updateBankBalance((account.getBankBalance() - amount )+ cashbackAmount);
			return true;
		}
		else {
			System.out.println("Insufficient funds");
			return false;
		}		
	}

}
