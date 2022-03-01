package card;
//It can be debit card or credit card

import account.*;
import bank.*;
public interface ICard {
	public abstract void setCardNo(long cardNo);
	public abstract void setCvv(int cvv);
	public abstract void setAccount(IAccount account);
	public IAccount getAccount();
	//get atm pin
	public abstract void setPin(int pin);
	public abstract boolean validatePin(int pin);
	
}
