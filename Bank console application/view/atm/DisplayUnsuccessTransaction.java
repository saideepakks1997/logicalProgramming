package atm;

public class DisplayUnsuccessTransaction {
	private static DisplayUnsuccessTransaction displayError = new DisplayUnsuccessTransaction();
	private DisplayUnsuccessTransaction() {}
	public static DisplayUnsuccessTransaction getDisplayObj() {
		return displayError;
	}
	public void unsuccessTrasaction() {
		System.out.println("Enter valid amount");
	}
	public void insufficientFunds() {
		System.out.println("Insufficient funds");
	}
	public void wrongPin() {
		System.out.println("Enter correct pin");
	}
}
