package account;

import bank.Bank;
import customer.Customer;

public class CreateAccount {
	public  IAccount createAccount(Customer customer) {
		Bank bank = Bank.getBank();
		IAccount account = null;
		long accNo = bank.getAccountNoSeries();
		account = new SingleSavingAccount(accNo, customer);
		bank.setAccounts(account);
		bank.setCustomers(customer);
		customer.setAccont(account);
		return account;
		}
}
