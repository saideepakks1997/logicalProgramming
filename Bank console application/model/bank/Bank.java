package bank;

import java.util.*;
//import java.util.List;
//import java.util.Scanner;

import account.*;
//import card.Card;
import card.DebitCard;
import customer.Customer;

public class Bank {
	private int customerIdSeries = 12345;
	private String name;
	private String branch;
	private String ifscCode;
	//Which is used to identification of correctness of cheque(Magnetic Ink Character Recognition technology)
	private Long micrCode;
	private Long branchContactNo;
	private String emailId;
	private long accountNoSeries = 21111111l;
	private long cardNumberSeries = 222222222l;
	private Integer bankCode ;
	private Integer branchCode;
	private Integer cityCode;
	private double minimumBalance = 100;
	private double levyPercBelow100 = 2;
	private double levyPercAbove100 = 4;
	private double cashBackPerc = 1;
	private Long customerCare;
	private String website;
	//code is used in foreign trade(authorized dealer code)provided by RBI
	private Long adCode;
	//there are different types of bank eg:- co-operative bank is for
	//social welfare of the state for eg gives loan for less intrest 
	//Commercial bank main motto is business etc.
	private BankType type;
	private double rateOfIntrestForSavingAccount = 2.5;

	private List<Customer> customers = new ArrayList<Customer>();
	private List<IAccount> accounts = new ArrayList<IAccount>();
	
	private static Bank bank = new Bank();
	private Bank() {
		this.name="Hdfc";
		this.branch = "Poonamalle";
		this.ifscCode = "hdfcb1235";
		this.micrCode =  314001123l;
		this.branchContactNo = 9894160327l;
		this.emailId = "hdfcpoonamalle@gmail.com";
		this.bankCode = 001;
		this.branchCode = 123;
		this.cityCode = 314;
		this.website = "www.hdfcbank.co.in";
		this.customerCare = 180023451256l;
	}
	public static Bank getBank() {
		return bank;
	}
	
	//bank name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = (this.name == null)? name : this.name;
	}
	//branch
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = (this.branch == null)? branch : this.branch;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = (this.ifscCode == null)? ifscCode : this.ifscCode;
	}
	//micr code
	public long getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(Long micrCode) {
		this.micrCode = (this.micrCode == null)? micrCode : this.micrCode;
	}
	//contact no
	public long getContactNo() {
		return branchContactNo;
	}
	public void setContactNo(long contactNO) {
		this.branchContactNo = contactNO;
	}
	//email
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String email) {
		this.emailId = email;
	}
	//account no
	public long getAccountNoSeries() {
		return accountNoSeries;
	}
	//card no 
	public long getCardNumberSeries() {
		return cardNumberSeries;
	}
	//bank code
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(Integer bankCode) {
		this.bankCode = (this.bankCode == null)? bankCode : this.bankCode;
	}
	//branch code
	public int getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(Integer branchCode) {
		this.branchCode = (this.branchCode == null)? branchCode : this.branchCode;
	}
	//city code
	public int getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = (this.cityCode == null)? cityCode : this.cityCode;
	}
	//customer
	public Customer getCustomer() {
		return customers.get(0);
	}
	public void setCustomers(Customer customer) {
		this.customers.add(customer);
	}
	//account
	public IAccount getAccounts() {
		return accounts.get(0);
	}
	public void setAccounts(IAccount account) {
		this.accounts.add(account);
	}
	//min balance
	public double getMinimumBalance() {
		return minimumBalance;
	}
	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	//customer id series
	public int getCustomerIdSeries() {
		return customerIdSeries++;
	}
	//website
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	//customer care
	public long getCustomerCare() {
		return customerCare;
	}
	public void setCustomerCare(long customerCare) {
		this.customerCare = customerCare;
	}
	//ad code
	public Long getAdCode() {
		return adCode;
	}
	public void setAdCode(Long adCode) {
		this.adCode = (this.adCode == null) ? adCode : this.adCode;
	}
	public void setLevyPercBelow100(double levyPercBelow100) {
		this.levyPercBelow100 = levyPercBelow100;
	}
	public double getLevyPerc(double amount) {
		if(amount <= 100)
			return this.levyPercBelow100;
		return this.levyPercAbove100;
	}
	public void setLevyPercAbove100(double levyPercAbove100) {
		this.levyPercAbove100 = levyPercAbove100;
	}
	public double getCashBackPerc() {
		return this.cashBackPerc;
	}
	public void setCashBackPerc(double cashBackPerc) {
		this.cashBackPerc = cashBackPerc;
	}
	public BankType getType() {
		return type;
	}
	public void setType(BankType type) {
		this.type = type;
	}
	public double getRateOfIntrest() {
		return rateOfIntrestForSavingAccount;
	}
	public void setRateOfIntrest(double rateOfIntrest) {
		this.rateOfIntrestForSavingAccount = rateOfIntrest;
	}
	
}
