package account;

import customer.Customer;

public class SavingsAccount extends Account{
	private SavingsAccountType type;
	public SavingsAccount(long accNo, Customer customerInfo) {
		this.setAccountNo(accNo);
		this.setCustomerInfo(customerInfo);
	}
	
	public SavingsAccountType getType() {
		return type;
	}
	public void setType(SavingsAccountType type) {
		this.type = type;
	}
}
