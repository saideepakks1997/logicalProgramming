package account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import card.*;
import customer.Customer;

public class Account{
	protected Long accountNo;
	protected Customer customerInfo;
	protected Date dateOfOpening;
	protected double bankBalance = 300;
	
	private List<Card> cards = new ArrayList<>();
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
	public void setCards(Card card) {
		this.cards.add(card);
	}
	//date of opening
	public Date getDateOfOpening() {
		return dateOfOpening;
	}
	public void setDateOfOpening(Date dateOfOpening) {
		this.dateOfOpening = this.dateOfOpening == null ? dateOfOpening : this.dateOfOpening;
	}
}
