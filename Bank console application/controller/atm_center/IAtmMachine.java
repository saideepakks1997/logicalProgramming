package atm_center;

import card.*;

public interface IAtmMachine {
	public void withdrawMoney(ICard card);
	public void displayBalance(ICard card); 
}
