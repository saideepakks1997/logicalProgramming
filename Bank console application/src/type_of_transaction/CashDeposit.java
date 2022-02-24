package type_of_transaction;

import account.CalculateLevyAndCashbackAmount;
import account.IAccount;
import card.Card;

public class CashDeposit implements ITypeOfTransaction{


	@Override
	public void displayScreen(Card card, double amount) {
		System.out.println("-----------------------------------------");
		
		System.out.println("Amount :-"+amount);
		System.out.println("Available balance :- "+card.getAccount().getBalance());
		
		System.out.println("-----------------------------------------");
	}

	@Override
	public boolean updateMoneyInAccount(Card card, double amount) {
		IAccount account = card.getAccount();
		double totalAmount = account.getBalance() + amount;
		account.updateBankBalance(totalAmount);
		return true;
		
	}

}
