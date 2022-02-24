package bank;
import java.util.*;

import account.IAccount;
import account.SavingAccount;
import card.Card;
import card.DebitCard;
import customer.Customer;
import main.MainApplication;
public class Bank implements IBank{
	long accountNoSeries = 1111111l;
	long cardNumberSeries = 222222222l;
	String bankName;
	String branch;
//	List<IAccount> accounts = new ArrayList<IAccount>();
	HashMap<Long, IAccount> accounts = new HashMap<>();
	


	@Override
	public IAccount createSavingsAccount(IBank bank,Customer customer) {
//		Customer customer = new Customer();
		IAccount savingsAccount = new SavingAccount(++accountNoSeries,customer,bank);
		long accNo = savingsAccount.getAccountNo();
		this.accounts.put(
				accNo, savingsAccount
				);
		System.out.println("Your account number is "+accNo);
		return savingsAccount;
	}


	@Override
	public IAccount createCurrentAccount() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Card createDebitCard( IAccount account,Customer customer) {
		Card debitCard = new DebitCard(123123,1234,"expiry date");
		debitCard.setAccount(account);
		return debitCard;
	}

	
}
