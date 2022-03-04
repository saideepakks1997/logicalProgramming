package atm_center;

import card.Card;

public interface IAtmMachine {
	public void withdrawMoney(Card card,double amount);
	public void displayBalance(Card card);
	public void depositMoney(Card card,double amount);
}
