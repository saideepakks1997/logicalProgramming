package atm_center;

import card.Card;

public interface IAtmMachine {
	public String withdrawMoney(Card card,double amount);
	public String getBalance(Card card);
	public String depositMoney(Card card,double amount);
}
