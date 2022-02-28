package swipe;

import type_of_transaction.*;

public class SwipeMachine implements ISwipe{
	ITypeOfTransaction transaction;
	
	public SwipeMachine() {
		this.transaction = new SwipeTransaction();
	}
	
	@Override
	public ITypeOfTransaction getTypeOfTransaction() {
		// TODO Auto-generated method stub
		return this.transaction;
	}
}
