package display;

public class Display implements IDisplay{
	public void displayError(String error) {
		System.out.println("------------------");
		System.out.println(error);
		System.out.println("------------------");
	}
	
	public void diplayWithdrawSuccess(double amount, double levyCharges, double bankBalance) {
		System.out.println("----------------------------");
		System.out.println("Withdraw amount "+amount);
		System.out.println("Levy charges "+levyCharges);
		System.out.println("Bank Balance:-  "+bankBalance);
		System.out.println("----------------------------");
	}

	public void displayBalanceSuccess(double bankBalance) {
		System.out.println("----------------------------");
		System.out.println("Bank Balance:-  "+bankBalance);
		System.out.println("----------------------------");
		
	}

	public void displayDepositeSuccess(double amount, double bankBalance) {
		System.out.println("----------------------------");
		System.out.println("Deposited amount "+amount);
		System.out.println("Bank Balance:-  "+bankBalance);
		System.out.println("----------------------------");
	}

	public void displaySwipeSuccess(double amount, double cashBack, double bankBalance) {
		System.out.println("----------------------------");
		System.out.println("Swipe amount "+amount);
		System.out.println("Cash Back amount "+cashBack);
		System.out.println("Bank Balance:-  "+bankBalance);
		System.out.println("----------------------------");
	}
}
