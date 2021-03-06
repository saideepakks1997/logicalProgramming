package account;

import java.time.LocalDate;

import card.Card;
import customer.Customer;

public interface IAccountOperations {
	public boolean checkTransactionPossible(double totalAmount,Card card);
	public void updateBankBalance(Card card,double totalAmount);
	public String createCustomer(String name,LocalDate dob,long phnNo,String address);
	public  String createSavingsAccount();
	public  String createCard(int opt, int pin);
	public double calculateLevyAndCashbackAmount (double amount,double perc);
	public Card getCard(long cardNo);
	public boolean validatePin(Card card, int pin);
}
