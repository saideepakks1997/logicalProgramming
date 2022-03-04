package display;

public interface IDisplay {
	public void displayError(String error);
	public void diplayWithdrawSuccess(double amount, double levyCharges, double bankBalance);
	public void displayBalanceSuccess(double bankBalance);
	public void displayDepositeSuccess(double amount, double bankBalance);
	public void displaySwipeSuccess(double amount, double cashBack, double bankBalance);
}
