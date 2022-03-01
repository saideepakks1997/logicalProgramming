package atm_center;

import card.*;
import type_of_transaction.ITypeOfTransaction;

public interface IAtmMachine {
	public void withdrawMoney(ICard card);
	public void displayBalance(ICard card); 
}
