package swipe;


import account.AccountOperations;
import account.IAccountOperations;
import bank.Bank;
import card.Card;
import display.Display;
import display.IDisplay;

public class SwipeMachine implements ISwipe{
	Bank bank = null;
	IAccountOperations accOperations = null;
	IDisplay display= new Display();
	public SwipeMachine(Bank bank) {
		this.bank = bank;
		accOperations = new AccountOperations(this.bank);
	}
	@Override
	public void acceptMoney(Card card, double amount) {
		double cashBackPerc = bank.getCashBackPerc();
			double bankBalance = card.getAccount().getBankBalance();
			double cashBack = accOperations.calculateLevyAndCashbackAmount(amount, cashBackPerc);
			
			boolean isTransactionPossible = accOperations.checkTransactionPossible(amount, card);
			
			if(isTransactionPossible) {
				accOperations.updateBankBalance(card, (bankBalance + cashBack)-amount);
				display.displaySwipeSuccess(amount,cashBack, card.getAccount().getBankBalance());
			}
		}
}
