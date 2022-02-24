package account;

import bank.Bank;
import bank.IBank;
import customer.Customer;

public class SavingAccount implements IAccount{
	boolean isDebitCardAvailable = false;
	public long accountNo;
	IBank bank;
	Customer customerInfo;
	double bankBalance = 300;
	double levyPercBelow100 = 2;
	double levyPercAbove100 = 4;
	double cashBackPerc = 1;
	double minimumBalance = 100;
	public SavingAccount(long accNo, Customer customerInfo,IBank bank) {
		this.accountNo = accNo;
		this.customerInfo = customerInfo;
		this.bank = bank;
	}
	
	

	@Override
	public double getBalance() {
		return this.bankBalance;
	}

	@Override
	public double getLevyPerc(double amount) {
		if(amount <= 100)
			return this.levyPercBelow100;
		
		return this.levyPercAbove100;
	}

	@Override
	public double getCashbackPerc(double amount) {
		return this.cashBackPerc;
	}

	

	public double getMinimumBalance() {
		return this.minimumBalance;
		}
	
	public void updateBankBalance(double amount) {
		this.bankBalance = amount;
	}



	@Override
	public long getAccountNo() {
		// TODO Auto-generated method stub
		return this.accountNo;
	}
	
}
