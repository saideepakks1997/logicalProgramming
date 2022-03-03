package bank;

public class CalculateLevyAndCashbackAmount {
	private static CalculateLevyAndCashbackAmount obj = new CalculateLevyAndCashbackAmount();
	private CalculateLevyAndCashbackAmount() {}
	public static CalculateLevyAndCashbackAmount getLevyCashbackAmount(){
		return obj;
	}
	public double calculateLevyAndCashbackAmount (double amount,double perc) {
		double money = amount * (perc/100);
		return money;
	}
}
