package atm_center;

import card.*;

public interface IAtmMachine {
	public void withdrawMoney(Card card);
	public void displayBalance(Card card); 
}
