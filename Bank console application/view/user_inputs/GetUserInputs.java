package user_inputs;

import java.util.Scanner;

import account.Account;
import account.AccountOperations;
import bank.Bank;
import card.Card;
import customer.Customer;

public class GetUserInputs {
	
	
	Scanner sc = new Scanner(System.in);

	public int getPin() {
		System.out.println("Enter the pin");
		return  sc.nextInt();
	}
	
	public double getAmount() {
		System.out.println("Enter the amount");
		return sc.nextDouble();
	}
	public String getName() {
		System.out.println("Enter name ");
		return sc.next();
	}
	public String getDob() {
		System.out.println("Enter date of birth");
		return sc.next();
	}
	public String getAdrress() {
		System.out.println("Enter address");
		return sc.next();
	}
	public long getPhoneNo() {
		System.out.println("Enter phone number");
		return sc.nextLong();
	}
	
	public long getCardNo() {
		System.out.println("Enter card no");
		return sc.nextLong();
	}
	
	public int getAccountOption() {
		System.out.println("Select \n"
				+ "1->Create Savings account");
		int opt = sc.nextInt();
		return opt;
	}
	public int getCardOption() {
		System.out.println("Select \n"
				+ "1->Create Debit card");
		int opt = sc.nextInt();
		return opt;
	}
}
