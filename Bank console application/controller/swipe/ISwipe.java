package swipe;

import card.Card;
import type_of_transaction.ITypeOfTransaction;

public interface ISwipe {
	public ITypeOfTransaction getTypeOfTransaction();
}
