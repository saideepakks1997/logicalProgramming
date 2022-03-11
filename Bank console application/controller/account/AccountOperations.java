package account;

import java.time.LocalDate;

import bank.Bank;
import card.Card;
import card.DebitCard;
import common_view.*;
import customer.Customer;

public class AccountOperations implements IAccountOperations{
	Bank bank = null;
	
	Customer customer = null;
	Account account = null;
	
	CommonView display = new CommonView();
	public AccountOperations(Bank bank) {
		this.bank = bank;
	}
	public boolean checkTransactionPossible(double totalAmount,Card card) {
		Account account = card.getAccount();
		if(account.getBankBalance() - totalAmount >= bank.getMinimumBalance()) {
			return true;
		}
		return false;
	}
	
	public void updateBankBalance(Card card,double totalAmount) {
		card.getAccount().setBankBalance(totalAmount);
	}
	
	
	public String createCustomer(String name,LocalDate dob,long phnNo,String address) {
		this.customer = new Customer(name, dob, phnNo, address);
		return "Customer Created Successfully";
	}
	
	public  String createSavingsAccount() {
		long accNo = bank.getAccountNoSeries();
		Account account = new SavingsAccount(accNo, customer);	
		this.account = account;
		
		bank.setAccounts(account);
		bank.setCustomers(customer);
		customer.setAccont(account);
		
		return "Account created Successfully";
		}
	
	public  String createCard(int opt,int pin) {
		Card card = null;
		long cardNo = bank.getCardNumberSeries();
		int cvv = 555;
		
		if(opt == 1)
			card  = new DebitCard(cardNo, cvv, "09/26", pin);
		
		card.setAccount(account);
		customer.setDebitCard(card);
		bank.setCards(card);
		return "Card has been created succuessfully your card no is "+card.getCardNo();
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
