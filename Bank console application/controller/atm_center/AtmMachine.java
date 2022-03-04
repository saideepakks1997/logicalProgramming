package atm_center;


import account.Account;
import account.AccountOperations;
import bank.Bank;
import card.Card;
import user_inputs.*;
import display.*;
import account.IAccountOperations;

public class AtmMachine implements IAtmMachine{
	String address;
	IAccountOperations accOperations = null;
	Bank bank = null;
	IDisplay display = new Display();
	public AtmMachine(Bank bank) {
		this.bank = bank;
		accOperations = new AccountOperations(this.bank);
	}
	
	//withdraw money
	public void withdrawMoney(Card card,double amount) {
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
				if(isTransactionPossible) {
					accOperations.updateBankBalance(card, bankBalance - (amount+levyCharges));
					display.diplayWithdrawSuccess(amount,levyCharges,account.getBankBalance());
				}
			}
			else 
				display.displayError("Invalid amount");
	}
	
	//Display money
	public void displayBalance(Card card) {
		display.displayBalanceSuccess(card.getAccount().getBankBalance());
	}
	
	//deposite money
	public void depositMoney(Card card,double amount) {
		Account account = card.getAccount();
		double bankBalance = account.getBankBalance();
			double amountAfterTransaction = bankBalance + amount;
			accOperations.updateBankBalance(card, amountAfterTransaction);
			display.displayDepositeSuccess(amount,amountAfterTransaction);
	}
	
	//Validate amount(i.e) check multiples of 5
	public boolean validateAmount(double amount) {
		return (amount % 5 == 0);
	}
}
