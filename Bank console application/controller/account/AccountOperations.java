package account;

import bank.Bank;
import card.Card;
import card.DebitCard;
import customer.Customer;
import display.*;

public class AccountOperations implements IAccountOperations{
	Bank bank = null;
	Display display = new Display();
	public AccountOperations(Bank bank) {
		this.bank = bank;
	}
	public boolean checkTransactionPossible(double totalAmount,Card card) {
		Account account = card.getAccount();
		if(account.getBankBalance() - totalAmount >= bank.getMinimumBalance()) {
			return true;
		}
		display.displayError("Insufficient funds");
		return false;
	}
	
	public void updateBankBalance(Card card,double totalAmount) {
		card.getAccount().setBankBalance(totalAmount);
	}
	
	
	public Customer createCustomer(String name,String dob,long phnNo,String address) {
		return new Customer(name, dob, phnNo, address);
	}
	
	public  Account createAccount(Customer customer) {
		Account account = null;
		long accNo = bank.getAccountNoSeries();
		account = new SavingsAccount(accNo, customer);
		bank.setAccounts(account);
		bank.setCustomers(customer);
		customer.setAccont(account);
		return account;
		}
	
	public  Card createCard(Account account,Customer customer,int opt) {
		Card card = null;
		long cardNo = bank.getCardNumberSeries();
		int cvv = 555;
		int pin = 1234;
		
		if(opt == 1)
			card  = new DebitCard(cardNo, cvv, "09/26", pin);
		
		card.setAccount(account);
		customer.setDebitCard(card);
		bank.setCards(card);
		return card;
	}
	public double calculateLevyAndCashbackAmount (double amount,double perc) {
		double money = amount * (perc/100);
		return money;
	}
	public Card getCard(long cardNo) {
		return bank.getCards(cardNo);
	}
	public boolean validatePin(Card card, int pin) {
		return card.validatePin(pin);
	}
}
