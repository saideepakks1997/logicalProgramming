package type_of_transaction;

import card.Card;

public interface ITypeOfTransaction {
	public void displayScreen(Card card,double amount,double perc);
	
	public boolean updateMoneyInAccount(Card card,double amount,double perc);
}
