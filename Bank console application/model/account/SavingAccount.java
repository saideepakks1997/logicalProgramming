package account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bank.*;
import card.*;
import customer.Customer;

public class SavingAccount implements IAccount{
	private Long accountNo;
	private Customer customerInfo;
	private Date dateOfOpening;
	private double bankBalance = 300;
	//If x person dies the money in the bank will transfer
	//to the nominee registered 
	private boolean isNomineeRegistered;
	private Long nomineeRegistrationNo;
	private Nominee nominee;
	private double rateOfIntrest = 2.5;
	//single or joint account
	private ModeOfOperation mode;
	//instant alert through messege and through mail
	private boolean isInstantAlertAvailable;
	private List<ICard> cards = new ArrayList<>();
	
	public SavingAccount(long accNo, Customer customerInfo) {
		this.setAccountNo(accNo);
		this.setCustomerInfo(customerInfo);
		}
	//bank balance
	public double getBankBalance() {
		return bankBalance;
	}
	public void setBankBalance(double amount) {
		this.bankBalance = amount;
	}
	//account no
	public  Long getAccountNo() {
		return this.accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = this.accountNo == null ? accountNo:this.accountNo;
	}
	//customerInfo
	public Customer getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(Customer customerInfo) {
		this.customerInfo = customerInfo;
	}
	//add cards
	public void setCards(ICard card) {
		this.cards.add(card);
	}
	//nominee 
	public boolean isNomineeRegistered() {
		return isNomineeRegistered;
	}
	public void setNomineeRegistered(boolean isNomineeRegistered) {
		this.isNomineeRegistered = isNomineeRegistered;
	}
	public Long getNomineeRegistrationNo() {
		return nomineeRegistrationNo;
	}
	public void setNomineeRegistrationNo(Long nomineeRegistrationNo) {
		this.nomineeRegistrationNo = nomineeRegistrationNo;
	}
	//date of opening
	public Date getDateOfOpening() {
		return dateOfOpening;
	}
	public void setDateOfOpening(Date dateOfOpening) {
		this.dateOfOpening = this.dateOfOpening == null ? dateOfOpening : this.dateOfOpening;
	}
	//rate of intrest
	public double getRateOfIntrest() {
		return rateOfIntrest;
	}
	public void setRateOfIntrest(double rateOfIntrest) {
		this.rateOfIntrest = rateOfIntrest;
	}

	public Nominee getNominee() {
		return nominee;
	}

	public void setNominee(Nominee nominee) {
		this.nominee = nominee;
	}
	//mode of operation 
	public ModeOfOperation getMode() {
		return mode;
	}
	
	public void setMode(ModeOfOperation mode) {
		this.mode = mode;
	}
	//is messege alert available
	public boolean isInstantAlertAvailable() {
		return isInstantAlertAvailable;
	}

	public void setInstantAlertAvailable(boolean isInstantAlertAvailable) {
		this.isInstantAlertAvailable = isInstantAlertAvailable;
	}
}