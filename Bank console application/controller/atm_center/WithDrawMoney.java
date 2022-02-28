package atm_center;

import java.util.Scanner;

import card.*;

public interface WithDrawMoney {
	static Scanner sc = new Scanner(System.in);
	public static void withDrawMoney(Card card,IAtmMachine atm) {
		if(ValidatePin.validatePin(card)) {
			System.out.println("Enter the amount multiples of 5");
			double amount = sc.nextDouble();
			//checks multiple of 5
			boolean isValidAmount = ValidateAmount.validateAmount(amount);
			if(isValidAmount) {
				double levyPerc = atm.calculateLevyPerc(amount);
				boolean isAmountupdated = atm.getTypeOfTransaction().updateMoneyInAccount(card,amount,levyPerc);
				if(isAmountupdated)
					atm.getTypeOfTransaction().displayScreen(card,amount,levyPerc);
			}
			else 
				System.out.println("Please enter the amount multiple of 5");
		}
	}
}
