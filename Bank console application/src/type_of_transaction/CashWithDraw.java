package type_of_transaction;

import account.CalculateLevyAndCashbackAmount;
import account.IAccount;
import card.Card;

public class CashWithDraw implements ITypeOfTransaction{

	

	@Override
	public void displayScreen(Card card, double amount) {
		System.out.println("-----------------------------------------");
		double perc = card.getAccount().getLevyPerc(amount);
		double charges = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, perc);
		
		System.out.println("Amount :-"+amount);
		System.out.println("Charges :- "+charges);
		System.out.println("Available balance :- "+card.getAccount().getBalance());
		
		System.out.println("-----------------------------------------");
		
	}

	@Override
	public boolean updateMoneyInAccount(Card card, double amount) {
		IAccount account = card.getAccount();
		double perc = account.getLevyPerc(amount);
		double totalAmount = amount + CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, perc);
		
		if(account.getBalance() - totalAmount >= account.getMinimumBalance()) {
			account.updateBankBalance(account.getBalance() - totalAmount);
			return true;
		}
		else {
			System.out.println("Insufficient funds");
			return false;
		}
		
	}
	
}
