package account;

import java.util.*;

import card.*;
import customer.Customer;
import customer.Nominee;
//it  should be indepentent
public interface  IAccount {
	
	public Long getAccountNo();
	public void setAccountNo(Long accountNo);
	
	public void setCustomerInfo(Customer customerInfo);
	
	public Date getDateOfOpening();
	public void setDateOfOpening(Date dateOfOpening);
	
	public double getBankBalance();
	public void setBankBalance(double bankBalance);
	
	public boolean isNomineeRegistered();
	public void setNomineeRegistered(boolean isNomineeRegistered);
	
	public void setNominee(Nominee nominee);
		
	public void setCards(ICard cards);
	
	public boolean isInstantAlertAvailable(); 
	public void setInstantAlertAvailable(boolean isInstantAlertAvailable);
	
	
	public Long getNomineeRegistrationNo();
	public void setNomineeRegistrationNo(Long nomineeRegistrationNo);
	
}
