package account;

public interface CalculateLevyAndCashbackAmount {
	public static double calculateLevyAndCashbackAmount (double amount,double perc) {
		double money = amount * (perc/100);
		return money;
	}
}
