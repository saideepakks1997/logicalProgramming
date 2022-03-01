package account;

import java.util.*;

import bank.Bank;
import card.*;
import customer.Customer;
//it  should be indepentent
public interface  IAccount {
	
	public Long getAccountNo();
	public void setAccountNo(Long accountNo);
	
	public Customer getCustomerInfo();
	public void setCustomerInfo(Customer customerInfo);
	
	public Date getDateOfOpening();
	public void setDateOfOpening(Date dateOfOpening);
	
	public double getBankBalance();
	public void setBankBalance(double bankBalance);
	
	public boolean isNomineeRegistered();
	public void setNomineeRegistered(boolean isNomineeRegistered);
	
	public Nominee getNominee();
	public void setNominee(Nominee nominee);
	 
	public ModeOfOperation getMode();
	public void setMode(ModeOfOperation mode);
		
	public void setCards(ICard cards);
	
	public boolean isInstantAlertAvailable(); 
	public void setInstantAlertAvailable(boolean isInstantAlertAvailable);
	
	
	public Long getNomineeRegistrationNo();
	public void setNomineeRegistrationNo(Long nomineeRegistrationNo);
	
	
	
	
}
