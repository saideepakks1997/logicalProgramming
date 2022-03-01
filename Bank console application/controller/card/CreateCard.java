package card;

import java.util.Random;
import java.util.Scanner;

import account.IAccount;
import bank.Bank;

public class CreateCard {
	static Scanner sc = new Scanner(System.in);
	static Random random = new Random();
	
	public  ICard createCard(IAccount account) {
		Bank bank = Bank.getBank();
		long cardNo = bank.getCardNumberSeries();
		int cvv = random.nextInt(900)+100;
		int pin = random.nextInt(9000)+1000;
		System.out.println("1->create Debit card\n");
		int opt = sc.nextInt();
		ICard card = null;
		if(opt == 1) {
			card = new DebitCard(cardNo, cvv, "09/26", pin);
			System.out.println("Debit card has been created your atm pin is "+pin);
		}
		card.setAccount(account);
		return card;
	}
}