package atm_center;

import card.Card;

public interface IAtmMachine {
	public void withDrawMoney(Card card);
	public void displayBalance(Card card);
	
}
