package main;

import java.util.Scanner;

import account.IAccount;
import account.SavingAccount;
import atm_center.*;
//import atm_machine.IAtmMachine;
import bank.*;
import card.Card;
import card.DebitCard;
import customer.*;
import swipe.ISwipe;
import swipe.SwipeMachine;

public class MainApplication {
//	public static IBank bank = new Bank();
//	static Customer customer = new Customer();
	static IAtmMachine atm =  new AtmMachine();
	static IDepositeMachine depositeMachine = new DepositeMachine();
//	static Card debitCard = new DebitCard(111111111l, 234, "HIMAN");
	static ISwipe swipe = new SwipeMachine();
	
	public static void main(String[] args) {
		Customer customer = new Customer();
		IBank hdfcBank = new Bank();
		
		customer.accont = hdfcBank.createSavingsAccount(hdfcBank,customer);
		customer.debitCard = hdfcBank.createDebitCard(customer.accont,customer);
		
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
			case 1: atm.displayBalance(customer.debitCard);
				break;
			case 2: atm.withDrawMoney(customer.debitCard);
				break;
			case 3: depositeMachine.depositMoney(customer.debitCard);
				break;
			case 4: swipe.acceptMoney(customer.debitCard);
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
