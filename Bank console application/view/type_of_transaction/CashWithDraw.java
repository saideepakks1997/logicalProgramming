package type_of_transaction;

import bank.CalculateLevyAndCashbackAmount;
import card.*;

public class CashWithDraw extends ITypeOfTransaction{
	private static ITypeOfTransaction transcation = new CashWithDraw();
	CalculateLevyAndCashbackAmount calculatelevyCashback 
	= CalculateLevyAndCashbackAmount.getLevyCashbackAmount();
	private CashWithDraw() {
	
	}
	public static ITypeOfTransaction getTransactionType() {
		return transcation;
	}
	@Override
	public void displayScreen(ICard card, double amount,double levyPerc) {
		System.out.println("-----------------------------------------");
		double charges = calculatelevyCashback.calculateLevyAndCashbackAmount(amount, levyPerc);
		
		System.out.println("Amount :-"+amount);
		System.out.println("Charges :- "+charges);
		System.out.println("Available balance :- "+card.getAccount().getBankBalance());
		
		System.out.println("-----------------------------------------");
		
	}
}
