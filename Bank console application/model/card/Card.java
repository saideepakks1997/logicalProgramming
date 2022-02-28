package card;
//It can be debit card or credit card

import account.*;
import bank.*;
public abstract class Card {
	protected Bank bank;
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
	public abstract Bank getBank();

	public void setBank(Bank bank) {
		this.bank = bank;
	}
}
