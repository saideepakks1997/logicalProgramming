package type_of_transaction;

import account.*;
//import account.CalculateLevyAndCashbackAmount;
import card.Card;

public class CashDeposit implements ITypeOfTransaction{


	@Override
	public void displayScreen(Card card, double amount,double perc) {
		System.out.println("-----------------------------------------");
		
		System.out.println("Amount :-"+amount);
		System.out.println("Available balance :- "+card.getAccount().getBankBalance());
		
		System.out.println("-----------------------------------------");
	}

	@Override
	public boolean updateMoneyInAccount(Card card, double amount,double perc) {
		Account account = card.getAccount();
		double totalAmount = account.getBankBalance() + amount;
		account.updateBankBalance(totalAmount);
		return true;
		
	}

}
