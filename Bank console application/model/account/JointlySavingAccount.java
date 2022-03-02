package account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import card.ICard;
import customer.Customer;
import customer.Nominee;

public class JointlySavingAccount implements IAccount,IJointAccount {
	private Long accountNo;
	
	private Date dateOfOpening;
	private double bankBalance = 300;
	//If x person dies the money in the bank will transfer
	//to the nominee registered 
	private boolean isNomineeRegistered;
	private Long nomineeRegistrationNo;
	private Nominee nominee;
	//single or joint account
	private ModeOfOperation mode;
	//instant alert through messege and through mail
	private boolean isInstantAlertAvailable;
	//an account can have multiple debit cards
	private List<ICard> cards = new ArrayList<>();
	//joint account can have more than one nominees
	private List<Nominee> nominees = new ArrayList<>();
	//joint account can have more than 2 Cutomers for same account
	private List<Customer> customers = new ArrayList<>();
	
	public JointlySavingAccount(long accNo, Customer customerInfo) {
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
	@Override
	public List<Nominee> getNominees() {
		return this.nominees;
	}
	@Override
	public List<Customer> getCustomers() {
		return this.customers;
	}
	public void setNominees(Nominee nominee) {
		this.nominees.add(nominee);
	}
	@Override
	public void setCustomerInfo(Customer customer) {
		this.customers.add(customer);
	}
}
