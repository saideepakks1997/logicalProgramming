package swipe;


import account.AccountOperations;
import account.IAccountOperations;
import bank.Bank;
import card.Card;

public class SwipeMachine implements ISwipe{
	Bank bank = null;
	IAccountOperations accOperations = null;
	public SwipeMachine(Bank bank) {
		this.bank = bank;
		accOperations = new AccountOperations(this.bank);
	}
	@Override
	public String acceptMoney(Card card, double amount) {
		double cashBackPerc = bank.getCashBackPerc();
			double bankBalance = card.getAccount().getBankBalance();
			double cashBack = accOperations.calculateLevyAndCashbackAmount(amount, cashBackPerc);
			
			boolean isTransactionPossible = accOperations.checkTransactionPossible(amount, card);
			
			if(isTransactionPossible) {
				accOperations.updateBankBalance(card, (bankBalance + cashBack)-amount);
				return "Swipe amount :- "+amount+"\n"
						+ "Cash back :- "+cashBack+"\n"
						+ "Bank Balance :- "+card.getAccount().getBankBalance();
			}
			else 
				return "Insufficiet funds";
		}
}
