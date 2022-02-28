package atm_center;

import card.Card;
import type_of_transaction.ITypeOfTransaction;

public interface IAtmMachine {
	
	public double getLevyPerc(double amount);
	public ITypeOfTransaction getTypeOfTransaction();
	public double calculateLevyPerc(double amount);
}
