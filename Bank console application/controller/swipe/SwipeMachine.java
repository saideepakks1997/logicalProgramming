package swipe;


import account.AmountTransaction;
import atm.DisplayUnsuccessTransaction;
import atm.GetAmount;
import atm.GetUserPin;
import bank.Bank;
import bank.CalculateLevyAndCashbackAmount;
import card.*;
import type_of_transaction.*;

public class SwipeMachine implements ISwipe{
	
	GetAmount getAmount = GetAmount.getAmountObj();
	DisplayUnsuccessTransaction displayError
		= DisplayUnsuccessTransaction.getDisplayObj(); 
	GetUserPin getUserPin = GetUserPin.getUserPin();
	
	ITypeOfTransaction transactionType = null;
	AmountTransaction amountTransaction = new AmountTransaction();
	CalculateLevyAndCashbackAmount calculatelevyCashback 
			= CalculateLevyAndCashbackAmount.getLevyCashbackAmount();

	@Override
	public boolean acceptMoney(ICard card) {
		Bank bank = Bank.getBank();
		transactionType = SwipeTransaction.getTransactionType();
		double amount = getAmount.getAmount();
		double cashBackPerc = bank.getCashBackPerc();
		int pin = getUserPin.getPin();
		if(card.validatePin(pin)) {
			double bankBalance = card.getAccount().getBankBalance();
			double cashBack = calculatelevyCashback.calculateLevyAndCashbackAmount(amount, cashBackPerc);
			
			boolean isTransactionPossible = amountTransaction.checkTransactionPossible(amount, card);
			
			boolean isTransactionDone = amountTransaction.updateBankBalance(card, (bankBalance + cashBack)-amount);

			if(isTransactionDone) {
				transactionType.displayScreen(card,amount,cashBackPerc);
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
