package account;

import bank.Bank;
import customer.Customer;

public class Account {
	private long accountNo;
	private Bank bank;
	private Customer customerInfo;
	private double balance;
	private double minimumBalance = 100;
	private double bankBalance = 300;
	
	public void updateBankBalance(double amount) {
		this.setBankBalance(amount);
	}
	public double getMinimumBalance() {
		return this.minimumBalance;
		}
	public  Bank getBank() {
		return this.bank;
	}
	public  long getAccountNo() {
		return this.accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Customer getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(Customer customerInfo) {
		this.customerInfo = customerInfo;
	}
	public double getBalance() {
		return this.balance;
	}
	public  Customer getCustomer() {
		return this.customerInfo;
	}
	public double getBankBalance() {
		return bankBalance;
	}
	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}
}
