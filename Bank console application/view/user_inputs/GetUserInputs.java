package user_inputs;

import java.util.Scanner;

import account.Account;
import account.AccountOperations;
import atm_center.AtmMachine;
import atm_center.IAtmMachine;
import bank.Bank;
import card.Card;
import customer.Customer;
import display.Display;
import display.IDisplay;
import swipe.*;
import account.IAccountOperations;


public class GetUserInputs {
	Bank bank = null;
	IAccountOperations operations = null;
	IAtmMachine atm_machine = null;
	ISwipe swipe_machine = null;
	IDisplay display = new Display();
	public GetUserInputs(Bank bank) {
		this.bank = bank;
		operations = new AccountOperations(this.bank);
		atm_machine = new AtmMachine(this.bank);
		swipe_machine = new SwipeMachine(this.bank);
	}
	
	Scanner sc = new Scanner(System.in);

	public boolean checkPin(Card card) {
		System.out.println("Enter the pin");
		int pin = sc.nextInt();
		boolean isValidPin = operations.validatePin(card, pin);
		if(!isValidPin)
			display.displayError("Invalid pin");
		return isValidPin;
	}
	
	public Double getAmount(Card card) {
		System.out.println("Enter the pin");
		int pin = sc.nextInt();
		boolean isValidPin = operations.validatePin(card, pin);
		Double amount = null;
		if(isValidPin) {
			System.out.println("Enter the amount");
			amount = sc.nextDouble();
		}
		else
			display.displayError("Invalid pin");
		return amount;
	}
	
	public void askCustomer() {
		System.out.println("Enter name ");
		String name = sc.next();
		
		System.out.println("Enter date of birth");
		String dob = sc.next();
		
		System.out.println("Enter address");
		String address = sc.next();
		
		System.out.println("Enter phone number");
		long phnNo = sc.nextLong();
		
		Customer customer = operations.createCustomer(name, dob, phnNo, address);
		
		System.out.println("Select \n"
				+ "1->Create Savings account");
		int opt = sc.nextInt();
		
		Account account = null;
		if(opt == 1)
			account = operations.createAccount(customer);
		Card card = null;
		System.out.println("Select \n"
				+ "1->Create Debit card");
		opt = sc.nextInt();
		if(opt == 1)
			card = operations.createCard(account, customer, opt);
	}
	
	public Card getCard() {
		System.out.println("Enter card no");
		long cardNo = sc.nextLong();
		return operations.getCard(cardNo);
	}
	
	public void atmScreen(Card card) {
		boolean loop = true;
		Double amount = null;
		while(loop) {
			System.out.println("Enter option\n"
					+ "1->Show balance\n"
					+ "2->Withdraw\n"
					+ "3->Deposit\n"
					+ "4->Swipe\n"
					+ "5->Exit");
			int opt = sc.nextInt();
//			AtmMachine atm = (AtmMachine)atm_machine;
			switch (opt) {
			case 1:
				if(checkPin(card))
					atm_machine.displayBalance(card);
				break;
			case 2:
				amount = this.getAmount(card);
				if(amount != null)
					atm_machine.withdrawMoney(card, amount);
				break;
			case 3:
				amount = this.getAmount(card);
				if(amount != null)
					atm_machine.depositMoney(card, amount);
				break;
			case 4:
				amount = this.getAmount(card);
				if(amount != null)
					swipe_machine.acceptMoney(card, amount);
				break;
			case 5: loop = false;
				System.out.println("Thank you");
				break;
			default: System.out.println("Enter the correct option");
				break;
			}
		}
	}
}
