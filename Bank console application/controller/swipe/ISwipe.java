package swipe;

import card.*;
import type_of_transaction.ITypeOfTransaction;

public interface ISwipe {
	public boolean acceptMoney(ICard card);
}
