package account;

import atm.DisplayUnsuccessTransaction;
import bank.Bank;
import card.ICard;

public class AmountTransaction {
	
	public boolean checkTransactionPossible(double totalAmount,ICard card) {
		DisplayUnsuccessTransaction displayError = DisplayUnsuccessTransaction.getDisplayObj();
		Bank bank = Bank.getBank();
		IAccount account = card.getAccount();
		if(account.getBankBalance() - totalAmount >= bank.getMinimumBalance()) {
			return true;
		}
		displayError.unsuccessTrasaction();
		return false;
	}
	
	public boolean updateBankBalance(ICard card,double totalAmount) {
			card.getAccount().setBankBalance(totalAmount);
			return true;
	}
}
