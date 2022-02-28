package bank;

import java.util.*;
//import java.util.List;
//import java.util.Scanner;
import java.util.stream.IntStream;

import account.*;
import account.SavingAccount;
import card.Card;
import card.DebitCard;
import customer.Customer;

public abstract class Bank {
	static Random random = new Random();
	static Scanner sc = new Scanner(System.in);
	private List<Customer> customers = new ArrayList<Customer>();
	private HashMap<Integer,Account> accounts = new HashMap<>();
//	private List<IAccount> currentAcc = new ArrayList<>();
	
	public Account createAccount(Bank bank) {
		System.out.println("Select \n"
				+ "1->Savings Account\n"
				+ "2->Current Account");
		int opt = sc.nextInt();
		Account account = null;
		Customer customer = GetCustomerDetails.getCustomerDetails();
		long accNo = this.getNewAccountNumber();
		if(opt == 1) {
			account = new SavingAccount(accNo, customer, bank);
			System.out.println("Account -> "+account.getBank());
		}
		return account;
	}
	
	public Customer getCustomer() {
		return customers.get(0);
	}


	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public abstract long getNewAccountNumber();
	public abstract double getCashBackPerc();
	public abstract long getCardNumSeries();

	public Card createCard(Account account) {
		long cardNo = this.getCardNumSeries();
		int cvv = random.nextInt(900)+100;
		int pin = random.nextInt(9000)+1000;
		System.out.println("Select option\n"
				+ "1->Debit card\n"
				+ "2->Credit card");
		int opt = sc.nextInt();
		Card card = null;
		if(opt == 1) {
			card = new DebitCard(cardNo, cvv, "09/26", pin);
			System.out.println("Debit card has been created your atm pin is "+pin);
		}
		card.setAccount(account);
//		
		return card;
	}
}
