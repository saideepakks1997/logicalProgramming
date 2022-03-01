package type_of_transaction;

import account.*;
import bank.Bank;
import bank.CalculateLevyAndCashbackAmount;
import card.*;

public class CashWithDraw extends ITypeOfTransaction{
	private static ITypeOfTransaction transcation = new CashWithDraw();
	private CashWithDraw() {
	
	}
	public static ITypeOfTransaction getTransactionType() {
		return transcation;
	}
	@Override
	public void displayScreen(ICard card, double amount,double levyPerc) {
		System.out.println("-----------------------------------------");
		double charges = CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, levyPerc);
		
		System.out.println("Amount :-"+amount);
		System.out.println("Charges :- "+charges);
		System.out.println("Available balance :- "+card.getAccount().getBankBalance());
		
		System.out.println("-----------------------------------------");
		
	}
	@Override
	public boolean updateMoneyInAccount(ICard card, double amount,double levyPerc) {
		IAccount account = card.getAccount();
		Bank bank = Bank.getBank();
		double totalAmount = amount + CalculateLevyAndCashbackAmount.calculateLevyAndCashbackAmount(amount, levyPerc);
		
		if(account.getBankBalance() - totalAmount >= bank.getMinimumBalance()) {
			account.setBankBalance(account.getBankBalance() - totalAmount);
			return true;
		}
		else {
			System.out.println("Insufficient funds");
			return false;
		}
	}
}
