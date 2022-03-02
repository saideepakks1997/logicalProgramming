package swipe;

import java.util.Scanner;

import account.AmountTransaction;
import bank.Bank;
import bank.CalculateLevyAndCashbackAmount;
import bank.ValidatePin;
import card.*;
import type_of_transaction.*;

public class SwipeMachine implements ISwipe{
	ITypeOfTransaction transactionType = null;
	static Scanner sc = new Scanner(System.in);
	static AmountTransaction amountTransaction = new AmountTransaction();


	@Override
	public boolean acceptMoney(ICard card) {
		Bank bank = Bank.getBank();
		transactionType = SwipeTransaction.getTransactionType();
		System.out.println("Enter amount");
		double amount = sc.nextDouble();
		double cashBackPerc = bank.getCashBackPerc();
		if(ValidatePin.validatePin(card)) {
			double bankBalance = card.getAccount().getBankBalance();
			double cashBack = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, cashBackPerc);
			
			boolean isTransactionPossible = amountTransaction.checkTransactionPossible(amount, card);
			
			boolean isTransactionDone = amountTransaction.updateBankBalance(card, (bankBalance + cashBack)-amount);

			if(isTransactionDone) {
				transactionType.displayScreen(card,amount,cashBackPerc);
				return true;
			}
			else {
				System.out.println("Insufficient funds");
				return false;
			}
		}
	return false;
	}
}
