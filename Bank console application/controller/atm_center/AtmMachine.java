package atm_center;

import java.util.Scanner;


import card.Card;
import type_of_transaction.*;

public class AtmMachine implements IAtmMachine{
	String address ;
	double levyPercBelow100 = 2;
	double levyPercAbove100 = 4;
	ITypeOfTransaction transaction;
	static Scanner sc = new Scanner(System.in);
	
	public AtmMachine() {
		this.transaction = new CashWithDraw();
		this.address = "Tambaram";
	}

	@Override
	public double getLevyPerc(double amount) {
		if(amount <= 100)
			return this.levyPercBelow100;
		else 
			return this.levyPercAbove100;
	}
	
	public double calculateLevyPerc(double amount) {
		if(amount <= 100)
			return levyPercBelow100;
		else
			return levyPercAbove100;
	}
	@Override
	public ITypeOfTransaction getTypeOfTransaction() {
		return this.transaction;
	}

}
