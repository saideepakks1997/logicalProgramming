package bank_view;

import java.time.LocalDate;
import java.util.Scanner;

import account.AccountOperations;
import account.IAccountOperations;
import bank.Bank;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;

public class BankView {
	Scanner sc = new Scanner(System.in);
	
	IAccountOperations operations = null;
	ICommonOperations commonOperations = new CommonOperations();
	CommonView commonView = new CommonView();
	
	public BankView(Bank bank) {
		this.operations = new AccountOperations(bank);
	}
	
	public void askCustomerBankOptions() {
		commonView.displayMessege("Enter option\n"
				+ "1->Create Savings Account");
		int opt = commonView.getOption();
		switch (opt) {
		case 1: createSavingsAccount();
			break;
		
		default: commonView.displayMessege("Enter correct option");
			break;
		}
	}
	private void createSavingsAccount() {
		createCustomer();
		String status = operations.createSavingsAccount();
		commonView.displayMessege(status);
		askForCreationOfCard();
	}
	
	private void askForCreationOfCard() {
		commonView.displayMessege("Enter option\n"
				+ "1->Create Debit Card\n"
				+ "2->No need card");
		int opt = commonView.getOption();
		switch (opt) {
		case 1: System.out.println("Enter pin for setting pin to account");
			int pin = commonView.getOption();
			String status = operations.createCard(opt, pin);
			commonView.displayMessege(status);
			break;
		case 2:commonView.displayMessege("Card has not been created");
			break;
		default: commonView.displayMessege("Enter correct option");
			break;
		}
		
	}
	public void createCustomer() {
		boolean loop = true;
		System.out.println("Enter your details for  creation of account");
		
		System.out.println("Enter name ");
		sc.skip("((?<!\\R)\\s)*");
		String name = sc.nextLine();
		
		LocalDate dob = getDOB();
		
		System.out.println("Enter address");
		sc.skip("((?<!\\R)\\s)*");
		String address = sc.nextLine();
		
		long phnNo = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter phone number");
			try {
				phnNo = sc.nextLong();
			}
			catch(Exception e) {
				loop = true;
				commonView.displayMessege("Phone number expected please enter phone number");
			}
		}
		String status  = operations.createCustomer(name, dob, phnNo, address);
		commonView.displayMessege(status);
	}
	
	private LocalDate getDOB() {
		boolean loop = true;
		while(loop) {
			loop = false;
			System.out.println("Enter date of birth to update or add format(yyyy-MM-dd)");
			String dob = sc.next();
			LocalDate date = commonOperations.getValidDate(dob);
			if(date != null) {
				return date;
			}
			else {
				loop = true;
				commonView.displayMessege("Please enter correct date");
			}
		}
		return null;
	}
}
