package atm;

import java.util.Scanner;

public class GetAmount {
	private static GetAmount getAmount = new GetAmount();
	private GetAmount() {}
	public static GetAmount getAmountObj() {
		return getAmount;
	}
	static Scanner sc = new Scanner(System.in);
	public double getAmount() {
		System.out.println("Enter the amount");
		return sc.nextDouble();
	}
}
