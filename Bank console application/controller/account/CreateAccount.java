package account;

import java.util.Scanner;

import bank.Bank;
import customer.Customer;

public class CreateAccount {
	static Scanner sc = new Scanner(System.in);
	public  IAccount createAccount() {
		Bank bank = Bank.getBank();
		System.out.println("1->create Saving account \n");
		int opt = sc.nextInt();
		IAccount account = null;
		Customer customer = createCustomer();
		long accNo = bank.getAccountNoSeries();
		if(opt == 1) {
			account = new SingleSavingAccount(accNo, customer);
		}
		bank.setAccounts(account);
		bank.setCustomers(customer);
		return account;
		}
	public Customer createCustomer() {
		sc.nextLine();
		Customer customer = new Customer();
		System.out.println("Enter customer name");
		String name = sc.nextLine();
		
		System.out.println("Enter date of birth");
		String dob = sc.next();
				
		System.out.println("Enter address");
		String address = sc.nextLine();
		sc.nextLine();
		
		System.out.println("Enter phone number");
		long phnNo = sc.nextLong();
		sc.nextLine();
		customer.setName(name);
		customer.setDob(dob);
		customer.setAddress(address);
		customer.setPhnNo(phnNo);
		
		
		return customer;
	}
}
