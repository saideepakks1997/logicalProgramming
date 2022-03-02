package account;

import customer.Customer;
import customer.Nominee;

public interface ISingleAccount extends IAccount{
	public Customer getCustomerInfo();
	
	public Nominee getNominee();
}
