package atm_center;


import account.Account;
import account.AccountOperations;
import bank.Bank;
import card.Card;
import account.IAccountOperations;

public class AtmMachine implements IAtmMachine{
	String address;
	IAccountOperations accOperations = null;
	Bank bank = null;
	public AtmMachine(Bank bank) {
		this.bank = bank;
		accOperations = new AccountOperations(this.bank);
	}
	
	//withdraw money
	public String withdrawMoney(Card card,double amount) {
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
					String status = "Withdraw amount :- "+amount+"\n"
							+ "Levy Charges :-"+levyCharges+"\n"
							+ "Bank Balance :-"+account.getBankBalance();
					return status;
				}
				else
					return "Insufficient funds";
			}
			else 
				return "Enter amount multiples of 5";
	}
	
	//Display money
	public String getBalance(Card card) {
		return "Bank Balance :- "+card.getAccount().getBankBalance();
	}
	
	//deposite money
	public String depositMoney(Card card,double amount) {
		Account account = card.getAccount();
		double bankBalance = account.getBankBalance();
			double amountAfterTransaction = bankBalance + amount;
			accOperations.updateBankBalance(card, amountAfterTransaction);
			return "Amount deposited :- "+amount+"\n"
					+ "Bank Balance :- "+amountAfterTransaction;
	}
	
	//Validate amount(i.e) check multiples of 5
	public boolean validateAmount(double amount) {
		return (amount % 5 == 0);
	}
}
