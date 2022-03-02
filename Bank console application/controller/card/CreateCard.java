package card;

import java.util.Random;

import account.IAccount;
import bank.Bank;
import customer.Customer;

public class CreateCard {
	static Random random = new Random();
	
	public  ICard createCard(IAccount account,Customer customer) {
		Bank bank = Bank.getBank();
		long cardNo = bank.getCardNumberSeries();
		int cvv = random.nextInt(900)+100;
		int pin = 1234;
		ICard card  = new DebitCard(cardNo, cvv, "09/26", pin);
		card.setAccount(account);
		customer.setDebitCard(card);
		return card;
	}
}
