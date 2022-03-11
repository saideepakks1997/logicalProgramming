package atm_view;

import java.util.Scanner;

import account.AccountOperations;
import account.IAccountOperations;
import atm_center.AtmMachine;
import atm_center.IAtmMachine;
import bank.Bank;
import card.Card;
import common_view.CommonView;
import swipe.ISwipe;
import swipe.SwipeMachine;

public class AtmView {
	Scanner sc = new Scanner(System.in);
	
	IAtmMachine atm_machine = null;
	ISwipe swipe_machine = null;
	IAccountOperations operations = null;
	
	CommonView commonView = new CommonView();
	public AtmView(Bank bank) {
		this.atm_machine = new AtmMachine(bank);
		this.swipe_machine = new SwipeMachine(bank);
		this.operations = new AccountOperations(bank);
	}
	

	
	public void atmScreen() {
		Card card = getCard();
		boolean loop = true;
		while(loop) {
			System.out.println("Enter option\n"
					+ "1->Show balance\n"
					+ "2->Withdraw\n"
					+ "3->Deposit\n"
					+ "4->Swipe\n"
					+ "5->Exit");
			int opt = commonView.getOption();

			switch (opt) {
			case 1:
				if(checkPin(card)) {
					String status = atm_machine.getBalance(card);
					commonView.displayMessege(status);
				}
				break;
			case 2:
				withdrawMoneyFromAccount(card);
				break;
			case 3:
				depositeMoneyToAccount(card);
				break;
			case 4:
				swipeTransaction(card);
				break;
			case 5: loop = false;
				System.out.println("Thank you");
				break;
			default: System.out.println("Enter the correct option");
				break;
			}
		}
	}

	private void depositeMoneyToAccount(Card card) {
		Double amount = getAmount(card);
		String status = atm_machine.depositMoney(card, amount);
		commonView.displayMessege(status);
	}



	private Card getCard() {
		Card card = null;
		boolean loop = true;
		while(loop) {
			loop = false;
			System.out.println("Enter card no");
			try {
				long cardNo = sc.nextLong();
				card = operations.getCard(cardNo);
			}
			catch (Exception e) {
				loop = true;
				commonView.displayMessege("Expected card number (Only enter number)");
				}
		}
		return card;
	}
	
	public boolean checkPin(Card card) {
		int pin = 0;
		boolean loop = true;
		int chances = 1;
		boolean isValidPin = false;
		while(loop) {
			loop = false;
			System.out.println("Enter the pin");
			try {
				pin = sc.nextInt();
				 isValidPin = operations.validatePin(card, pin);
				if(!isValidPin) {
					if(chances <= 3) {
						commonView.displayMessege("Invalid pin enter correct pin");
						loop = true;
						chances++;
					}
					else {
						commonView.displayMessege("Returning to back menu");
						loop = false;
					}
				}
				else {
					return isValidPin;
				}
					
			}
			catch(Exception e) {
				commonView.displayMessege("Please Enter numbers only");
				loop = true;
			}
		}
		return isValidPin;
	}
	
	public Double getAmount(Card card) {
		Double amount = null;
		boolean loop = true;
		while(loop) {
			boolean isValid = checkPin(card);
			if(isValid) {
				loop= false;
				System.out.println("Enter the amount");
				try {
					amount = sc.nextDouble();
					
					}
				catch(Exception e) {
					commonView.displayMessege("Enter valid input");
					loop= true;
				}
			}
		}
		
		return amount;
	}
	
	public void withdrawMoneyFromAccount(Card card) {
		Double amount = getAmount(card);
		String status = atm_machine.withdrawMoney(card, amount);
		commonView.displayMessege(status);
	}
	
	public void swipeTransaction(Card card) {
		Double amount = getAmount(card);
		String status = swipe_machine.acceptMoney(card, amount);
		commonView.displayMessege(status);
	}
}
