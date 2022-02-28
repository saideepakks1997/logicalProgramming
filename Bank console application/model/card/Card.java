package card;
//It can be debit card or credit card

import account.*;
import bank.*;
public abstract class Card {
	protected long cardNo;
	protected int cvv;
	protected String expiryDate;
	protected Account accountInfo;
	protected int atmPin ;
	public abstract String getDetails();
	
	public abstract Account getAccount();
	public abstract void setAccount(Account account);
	//get atm pin
	public int getPin() {
		return this.atmPin;
	}
	
}
