package main;

import java.util.Scanner;

import account.Account;
import account.AccountOperations;
import atm_center.AtmMachine;
import bank.Bank;
import card.*;
import customer.Customer;
import swipe.ISwipe;
import swipe.SwipeMachine;
import user_inputs.GetUserInputs;

public class MainApplication {
	
	
	public static void main(String[] args) {
		
		Bank bank = new Bank();
		AtmMachine atm_machine =  new AtmMachine(bank);
		ISwipe swipe = new SwipeMachine(bank);
//		GetUserInputs input = new GetUserInputs(bank);
		AccountOperations accOperations = new AccountOperations(bank);
		
		//Ask customer details and create account and card
		Card card = null;
		accOperations.askCustomer();
		
		//get card through card no
		card = accOperations.getCard();
		 
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			System.out.println("Enter option\n"
					+ "1->Show balance\n"
					+ "2->Withdraw\n"
					+ "3->Deposit\n"
					+ "4->Swipe\n"
					+ "5->Exit");
			int opt = sc.nextInt();
			switch (opt) {
			case 1: atm_machine.displayBalance(card);
				break;
			case 2: atm_machine.withdrawMoney(card);
				break;
			case 3: atm_machine.depositMoney(card);
				break;
			case 4: swipe.acceptMoney(card);
				break;
			case 5: loop = false;
				System.out.println("Thank you");
				break;
			default: System.out.println("Enter the correct option");
				break;
			}
		}

	}
//	public void askCustomer() {
//		Scanner sc = new Scanner(System.in);
//		Customer customer = operations.createCustomer();
//		System.out.println("select 1-> create savings account");
//		int opt = sc.nextInt(); 
//		Account account = null;
//		if(opt == 1) {
//			 account = operations.createAccount(customer);
//		}
//		System.out.println("Select 1 to create debit card");
//		opt = sc.nextInt();
//		Card card = null;
//		card = operations.createCard(account,customer,opt);
//	}
	

}
