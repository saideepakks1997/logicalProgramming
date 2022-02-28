package bank;
import java.util.*;

import account.*;

import card.Card;
import card.DebitCard;
import customer.Customer;
import main.MainApplication;
public class HdfcBank extends Bank{
	private long accountNoSeries = 1111111l;
	private long cardNumberSeries = 222222222l;
	String bankName = "Hdfc";
	String branch = "Chennai";
	private double cashbackPerc = 1;
	private HashMap<Long, Account> accounts = new HashMap<>();
	
	@Override
	public long getNewAccountNumber() {
		return this.accountNoSeries++;
	}
	
	@Override
	public double getCashBackPerc() {
		return this.cashbackPerc;
	}

	@Override
	public long getCardNumSeries() {
		return this.cardNumberSeries++;
	}
}
