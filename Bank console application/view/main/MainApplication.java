package main;

import java.util.Scanner;

import account.*;
import account.CreateAccount;
import atm_center.AtmMachine;
import bank.Bank;
import card.*;
import card.CreateCard;
import swipe.ISwipe;
import swipe.SwipeMachine;

public class MainApplication {
	static AtmMachine atm_machine =  new AtmMachine();
	static ISwipe swipe = new SwipeMachine();
	
	public static void main(String[] args) {
		Bank hdfcBank = Bank.getBank();
		CreateAccount createAccount = new CreateAccount();
		CreateCard createCard = new CreateCard();
		
		IAccount account = createAccount.createAccount();
		ICard card = createCard.createCard(account);

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

}
