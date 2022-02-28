package account;

import bank.*;
import customer.Customer;

public class SavingAccount extends Account{
	double rateOfIntrest = 2.5;
	
	public SavingAccount(long accNo, Customer customerInfo,Bank bank) {
		this.setAccountNo(accNo);
		this.setCustomerInfo(customerInfo);
		this.setBank(bank);
	}
}
