package atm_center;


import account.Account;
import account.AccountOperations;
import bank.Bank;
import card.Card;
import user_inputs.*;
import display.*;

public class AtmMachine implements IAtmMachine{
	String address;
	AccountOperations accOperations = null;
	Bank bank = null;
	public AtmMachine(Bank bank) {
		this.bank = bank;
		accOperations = new AccountOperations(this.bank);
	}
	  
	DisplayErrorMessege displayError = new DisplayErrorMessege();
	DisplaySuccessMessege displaySuccess = new DisplaySuccessMessege();
	GetUserInputs input = new GetUserInputs();
	

	
//	AmountTransaction amountTransaction = new AmountTransaction();
	public AtmMachine() {
		this.address = "Tambaram";
	}
	
	//withdraw money
	public void withdrawMoney(Card card) {
		int pin = input.getPin();
		if(card.validatePin(pin)) {
			double amount = input.getAmount();
			//checks multiple of 5
			boolean isValidAmount = this.validateAmount(amount);
			if(isValidAmount) {
				Account account = card.getAccount();
				
				double bankBalance = account.getBankBalance();
				double levyPerc = bank.getLevyPerc(amount);
				double levyCharges = accOperations.calculateLevyAndCashbackAmount(amount, levyPerc);
				//checking minimum balance
				boolean isTransactionPossible = accOperations.checkTransactionPossible(amount+levyCharges, card);
				//do transaction
				boolean isTransactionDone = accOperations.updateBankBalance(card, bankBalance - (amount+levyCharges));
				if(isTransactionDone) {
					displaySuccess.diplayWithdraw(amount,levyCharges,account.getBankBalance());
				}
			}
			else 
				displayError.invalidAmount();
		}
		else {
			displayError.wrongPin();
		}
	}
	
	//Display money
	public void displayBalance(Card card) {
		int pin = input.getPin();
		if(card.validatePin(pin)) {
			//singleton object
			displaySuccess.displayBalance(card.getAccount().getBankBalance());
		}
		else
			displayError.wrongPin();
	}
	
	//deposite money
	public void depositMoney(Card card) {
		Account account = card.getAccount();
		double bankBalance = account.getBankBalance();
		double amount = input.getAmount();
		int pin = input.getPin();
		if(card.validatePin(pin)) {
			double amountAfterTransaction = bankBalance + amount;
			accOperations.updateBankBalance(card, amountAfterTransaction);
			displaySuccess.displayDeposite(amount,amountAfterTransaction);
			
		}
		else
			displayError.wrongPin();
	}
	
	//Validate amount(i.e) check multiples of 5
	public boolean validateAmount(double amount) {
		return (amount % 5 == 0);
	}
}
