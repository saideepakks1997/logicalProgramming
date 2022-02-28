package atm_center;

import card.Card;
import type_of_transaction.ITypeOfTransaction;

public interface IDepositeMachine {
//	public void depositMoney(Card card,IDepositeMachine depositeMachine);
	public ITypeOfTransaction getTypeOfTransaction();
}
