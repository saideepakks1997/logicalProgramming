package account;

import customer.Customer;

public class SavingsAccount extends Account{

	public SavingsAccount(long accNo, Customer customerInfo) {
		this.setAccountNo(accNo);
		this.setCustomerInfo(customerInfo);
	}
	
}
