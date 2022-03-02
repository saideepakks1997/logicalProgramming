package card;
//It can be debit card or credit card

import account.*;
public interface ICard {
	public IAccount getAccount();
	//get atm pin
	public abstract void setPin(int pin);
	public abstract boolean validatePin(int pin);
	public void setAccount(IAccount account);
	
}
