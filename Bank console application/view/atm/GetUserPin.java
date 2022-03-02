package atm;

import java.util.Scanner;

public class GetUserPin {
	private static GetUserPin userPin = new GetUserPin();
	private GetUserPin() {}
	public static GetUserPin getUserPin() {
		return userPin;
	}
	Scanner sc = new Scanner(System.in);
	public int getPin() {
		System.out.println("Enter the pin");
		return  sc.nextInt();
	}
}
