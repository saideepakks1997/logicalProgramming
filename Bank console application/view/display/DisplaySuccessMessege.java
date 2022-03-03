package display;

public class DisplaySuccessMessege {

	public void diplayWithdraw(double amount, double levyCharges, double bankBalance) {
		System.out.println("----------------------------");
		System.out.println("Withdraw amount "+amount);
		System.out.println("Levy charges "+levyCharges);
		System.out.println("Bank Balance:-  "+bankBalance);
		System.out.println("----------------------------");
	}

	public void displayBalance(double bankBalance) {
		System.out.println("----------------------------");
		System.out.println("Bank Balance:-  "+bankBalance);
		System.out.println("----------------------------");
		
	}

	public void displayDeposite(double amount, double bankBalance) {
		System.out.println("----------------------------");
		System.out.println("Deposited amount "+amount);
		System.out.println("Bank Balance:-  "+bankBalance);
		System.out.println("----------------------------");
	}

	public void displaySwipe(double amount, double cashBack, double bankBalance) {
		System.out.println("----------------------------");
		System.out.println("Swipe amount "+amount);
		System.out.println("Cash Back amount "+cashBack);
		System.out.println("Bank Balance:-  "+bankBalance);
		System.out.println("----------------------------");
	}
	
	
}
