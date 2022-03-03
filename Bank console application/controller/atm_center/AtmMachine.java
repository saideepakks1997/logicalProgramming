package atm_center;


import account.AmountTransaction;
import account.IAccount;
import atm.DisplayUnsuccessTransaction;
import atm.GetAmount;
import atm.GetUserPin;
import bank.Bank;
import bank.CalculateLevyAndCashbackAmount;
import card.ICard;
import type_of_transaction.CashDeposit;
import type_of_transaction.CashWithDraw;
import type_of_transaction.DisplayBalance;
import type_of_transaction.ITypeOfTransaction;

public class AtmMachine implements IAtmMachine{
	String address ;
	GetAmount getAmount = GetAmount.getAmountObj();
	DisplayUnsuccessTransaction displayError
		= DisplayUnsuccessTransaction.getDisplayObj();
	GetUserPin getUserPin = GetUserPin.getUserPin();

	ITypeOfTransaction transactionType = null;
	CalculateLevyAndCashbackAmount calculatelevyCashback 
	= CalculateLevyAndCashbackAmount.getLevyCashbackAmount();
	AmountTransaction amountTransaction = new AmountTransaction();
	public AtmMachine() {
		this.address = "Tambaram";
	}
	
	//withdraw money
	public void withdrawMoney(ICard card) {
		Bank bank = Bank.getBank();
		transactionType = CashWithDraw.getTransactionType();//for displaying
		int pin = getUserPin.getPin();
		if(card.validatePin(pin)) {
			double amount = getAmount.getAmount();
			//checks multiple of 5
			boolean isValidAmount = this.validateAmount(amount);
			if(isValidAmount) {
				IAccount account = card.getAccount();
				
				double bankBalance = account.getBankBalance();
				double levyPerc = bank.getLevyPerc(amount);
				double levyCharges = calculatelevyCashback.calculateLevyAndCashbackAmount(amount, levyPerc);
				//checking minimum balance
				boolean isTransactionPossible = amountTransaction.checkTransactionPossible(amount+levyCharges, card);
				//do transaction
				boolean isTransactionDone = amountTransaction.updateBankBalance(card, bankBalance - (amount+levyCharges));
				
				if(isTransactionDone) {
					transactionType.displayScreen(card,amount,levyPerc);
				}
			}
			else 
				displayError.unsuccessTrasaction();
		}
		else {
			displayError.wrongPin();
		}
	}
	
	//Display money
	public void displayBalance(ICard card) {
		int pin = getUserPin.getPin();
		if(card.validatePin(pin)) {
			//singleton object
			DisplayBalance disBalance = DisplayBalance.getDisplayBalance();
			disBalance.displayBalance(card);
		}
		else
			displayError.wrongPin();
	}
	
	//deposite money
	public void depositMoney(ICard card) {
		transactionType = CashDeposit.getTransactionType();
		IAccount account = card.getAccount();
		double bankBalance = account.getBankBalance();
		double amount = getAmount.getAmount();
		int pin = getUserPin.getPin();
		if(card.validatePin(pin)) {
			double amountAfterTransaction = bankBalance + amount;
			amountTransaction.updateBankBalance(card, amountAfterTransaction);
			transactionType.displayScreen(card, amount,0);
		}
		else
			displayError.wrongPin();
	}
	
	//Validate amount(i.e) check multiples of 5
	public boolean validateAmount(double amount) {
		return (amount % 5 == 0);
	}
}
