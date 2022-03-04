package atm_center;

import card.Card;

public interface IAtmMachine {
	public void withdrawMoney(Card card);
	public void displayBalance(Card card); 
}
