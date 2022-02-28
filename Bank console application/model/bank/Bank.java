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
	
	public Customer getCustomer() {
		return customers.get(0);
	}


	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public abstract long getNewAccountNumber();
	public abstract double getCashBackPerc();
	public abstract long getCardNumSeries();

	
}
