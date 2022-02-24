package bank;

import account.IAccount;
import card.Card;
import customer.Customer;

public interface IBank {
	
	public IAccount createSavingsAccount(IBank bank, Customer customer);
	
	public IAccount createCurrentAccount();

	public Card createDebitCard( IAccount accont,Customer customer);
}
