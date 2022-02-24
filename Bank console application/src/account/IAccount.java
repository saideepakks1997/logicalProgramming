package account;

public interface IAccount {
	public double getBalance();
	
	public double getLevyPerc(double amount);
	
	public double getCashbackPerc(double amount);
	
	public void updateBankBalance(double amount);
	
	public double getMinimumBalance();
	
	public long getAccountNo();
}
