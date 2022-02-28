package type_of_transaction;

import account.*;
import card.Card;

public class CashWithDraw implements ITypeOfTransaction{
	@Override
	public void displayScreen(Card card, double amount,double levyPerc) {
		System.out.println("-----------------------------------------");
//		double perc = card.getAccount().getLevyPerc(amount);
		double charges = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, levyPerc);
		
		System.out.println("Amount :-"+amount);
		System.out.println("Charges :- "+charges);
		System.out.println("Available balance :- "+card.getAccount().getBalance());
		
		System.out.println("-----------------------------------------");
		
	}
	@Override
	public boolean updateMoneyInAccount(Card card, double amount,double levyPerc) {
		Account account = card.getAccount();
		double totalAmount = amount + CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, levyPerc);
		
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
