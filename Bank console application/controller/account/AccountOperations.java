package account;


import bank.Bank;
import card.Card;
import card.DebitCard;
import customer.Customer;
import display.*;
import user_inputs.GetUserInputs;

public class AccountOperations {
	Bank bank = null;
	GetUserInputs inputs = new GetUserInputs();
	DisplayErrorMessege displayError = new DisplayErrorMessege();
	public AccountOperations(Bank bank) {
		this.bank = bank;
	}
	public boolean checkTransactionPossible(double totalAmount,Card card) {
		Account account = card.getAccount();
		if(account.getBankBalance() - totalAmount >= bank.getMinimumBalance()) {
			return true;
		}
		displayError.insufficientFunds();
		return false;
	}
	
	public boolean updateBankBalance(Card card,double totalAmount) {
		card.getAccount().setBankBalance(totalAmount);
		return true;
	}
	
	public Customer createCustomer() {
		String name = inputs.getName();
		String dob = inputs.getDob();
		long phnNo = inputs.getPhoneNo();
		String address  = inputs.getAdrress(); 
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

	public Card getCard() {
		long cardNo = inputs.getCardNo();
		Card card = bank.getCards(cardNo);
		return card;
	}
	public void askCustomer() {
		Customer customer = this.createCustomer();
		int opt = inputs.getAccountOption(); 
		Account account = null;
		if(opt == 1) {
			 account = this.createAccount(customer);
		}
		opt = inputs.getCardOption();
		Card card = null;
		card = this.createCard(account,customer,opt);
	}
}
