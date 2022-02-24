package type_of_transaction;

import account.CalculateLevyAndCashbackAmount;
import account.IAccount;
import card.Card;

public class SwipeTransaction implements ITypeOfTransaction{

	@Override
	public void displayScreen(Card card, double amount) {
		System.out.println("-----------------------------------------");
		double perc = card.getAccount().getCashbackPerc(amount);
		double cashBack = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, perc);
		
		System.out.println("Amount :-"+amount);
		System.out.println("Cash back :- "+cashBack);
		System.out.println("Available balance :- "+card.getAccount().getBalance());
		
		System.out.println("-----------------------------------------");
	}

	@Override
	public boolean updateMoneyInAccount(Card card, double amount) {
		IAccount account = card.getAccount();
		double perc = account.getCashbackPerc(amount);
		double cashbackAmount = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, perc);
		if(account.getBalance() - amount >= account.getMinimumBalance()) {
			account.updateBankBalance((account.getBalance() - amount )+ cashbackAmount);
			return true;
		}
		else {
			System.out.println("Insufficient funds");
			return false;
		}		
	}

}
