package main;

import java.util.Scanner;

import account.*;
import atm_center.*;
//import atm_machine.IAtmMachine;
import bank.*;
import card.Card;
import card.DebitCard;
import customer.*;
import swipe.AcceptMoney;
import swipe.ISwipe;
import swipe.SwipeMachine;

public class MainApplication {
	static IAtmMachine atm =  new AtmMachine();
	static IDepositeMachine depositeMachine = new DepositeMachine();
//	static Card debitCard = new DebitCard(111111111l, 234, "HIMAN");
	static ISwipe swipe = new SwipeMachine();
	
	public static void main(String[] args) {
		
		Bank hdfcBank = new HdfcBank();
		Account account = hdfcBank.createAccount(hdfcBank);
		Card card = hdfcBank.createCard(account);

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
			case 1: DisplayBalance.displayBalance(card);
				break;
			case 2: WithDrawMoney.withDrawMoney(card,atm);
				break;
			case 3: DepositMoney.depositMoney(card,depositeMachine);
				break;
			case 4: AcceptMoney.acceptMoney(card,swipe);
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
