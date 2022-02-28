package bank;

public interface CalculateLevyAndCashbackAmount {
	public static double calculateLevyAndCashbackAmount (double amount,int perc) {
		double money = amount * (perc/100);
		return money;
	}
}
