package bank;

import java.util.Scanner;

import account.Account;
import account.SavingAccount;
import customer.Customer;

public interface CreateAccount {
	static Scanner sc = new Scanner(System.in);
	public static Account createAccount(Bank bank) {
		System.out.println("1->create Saving account \n");
		int opt = sc.nextInt();
		Account account = null;
		Customer customer = GetCustomerDetails.getCustomerDetails();
		long accNo = bank.getNewAccountNumber();
		if(opt == 1) {
			account = new SavingAccount(accNo, customer, bank);
		}
		return account;
	}
}
