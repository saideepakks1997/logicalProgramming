package card;
//It can be debit card or credit card

import account.IAccount;
import bank.*;
public abstract class Card {
	protected Bank bank;
	protected long cardNo;
	protected int cvv;
	protected String expiryDate;
	protected IAccount accountInfo;
	protected int atmPin = 1234;
	public abstract String getDetails();
	
	public abstract IAccount getAccount();
	public abstract void setAccount(IAccount account);
	//get atm pin
	public int getPin() {
		return this.atmPin;
	}
	
}
