package type_of_transaction;

import card.*;

public abstract class ITypeOfTransaction {
	public abstract void displayScreen(ICard card,double amount,double perc);
	public abstract boolean updateMoneyInAccount(ICard card,double amount,double perc);
	}