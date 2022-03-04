package swipe;


import account.AccountOperations;
import bank.Bank;
import card.Card;
import display.DisplayErrorMessege;
import display.DisplaySuccessMessege;
import user_inputs.GetUserInputs;

public class SwipeMachine implements ISwipe{
	Bank bank = null;
	AccountOperations accOperations = null;
	public SwipeMachine(Bank bank) {
		this.bank = bank;
		accOperations = new AccountOperations(this.bank);
	}
	 
	
	DisplayErrorMessege displayError = new DisplayErrorMessege(); 
	DisplaySuccessMessege displaySuccess = new DisplaySuccessMessege();
	GetUserInputs input = new GetUserInputs();
	
	@Override
	public boolean acceptMoney(Card card) {
		double amount = input.getAmount();
		double cashBackPerc = bank.getCashBackPerc();
		int pin = input.getPin();
		if(card.validatePin(pin)) {
			double bankBalance = card.getAccount().getBankBalance();
			double cashBack = accOperations.calculateLevyAndCashbackAmount(amount, cashBackPerc);
			
			boolean isTransactionPossible = accOperations.checkTransactionPossible(amount, card);
			
			boolean isTransactionDone = accOperations.updateBankBalance(card, (bankBalance + cashBack)-amount);

			if(isTransactionDone) {
				displaySuccess.displaySwipe(amount,cashBack, card.getAccount().getBankBalance());
				return true;
			}
			else {
				displayError.insufficientFunds();
				return false;
			}
		}
		else
			displayError.wrongPin();
	return false;
	}
}
