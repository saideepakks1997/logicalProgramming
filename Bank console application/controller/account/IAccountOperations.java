package account;

import card.Card;
import customer.Customer;

public interface IAccountOperations {
	public boolean checkTransactionPossible(double totalAmount,Card card);
	public void updateBankBalance(Card card,double totalAmount);
	public Customer createCustomer(String name,String dob,long phnNo,String address);
	public  Account createAccount(Customer customer);
	public  Card createCard(Account account,Customer customer,int opt);
	public double calculateLevyAndCashbackAmount (double amount,double perc);
	public Card getCard(long cardNo);
	public boolean validatePin(Card card, int pin);
}
