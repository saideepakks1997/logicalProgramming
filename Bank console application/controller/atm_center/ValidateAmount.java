package atm_center;

public interface ValidateAmount {
	public static boolean validateAmount(double amount) {
		return (amount % 5 == 0);
	}
}
