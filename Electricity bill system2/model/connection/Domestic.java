package connection;

import consumer.Consumer;

public class Domestic extends Connection{
	
	public Domestic(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}

	public Domestic(long serviceNo, TypeOfConnection connectionType, long currentUnits, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, currentUnits, connAddress, consumer);
	}

	

	


	int freeUnits = 100;
	
	@Override
	public double calculateBill(double units) {
		double amount = 0;
		if(units <= 100)
			return 0;
		else if(units <= 200) 
			amount = lessThan200Units(units);
			
		else if(units <= 500) 
			amount = lessThan500Units(units);
			
		else if(units > 500)
			amount = greaterThan500(units);
		
		return amount;
	}

	private double lessThan200Units(double units) {
		double amount;
		units -= 100;
		amount = units * 1.5;
		System.out.println("Amount :- "+amount);
		return amount;
	}
	
	private double lessThan500Units(double units) {
		double amount;
		double chargesLessThan200 = 2;
		double chargesAbove200 = 3;
		units -= 100;
		amount = 100 * chargesLessThan200;
		units -= 100;
		amount = units * chargesAbove200;
		return amount;
	}
	
	private double greaterThan500(double units) {
		double amount;
		double chargesLessThan200 = 3.5;
		double chargesLessThan500 = 4.6;
		double chargesGreaterThan500 = 6.6;
		units -= 100;
		amount = 100 * chargesLessThan200;
		units -= 100;
		amount += (300 * chargesLessThan500);
		units -= 300;
		amount += (units * chargesGreaterThan500);
		return amount;
	}
	
	public String toString() {
		String val = "--------------Category 1(Below 100 units)---------\n"
				+ "No charges free\n"
				+ "--------------Category 2(Below 200 units)---------\n"
				+ "0 to 100 units :-   free\n"
				+ "Next 100 units :-  1.5 rupees per unit\n"
				+ "--------------Category 3(Below 500 units)---------\n"
				+ "0 to 100 units:-   free\n"
				+ "100 to 200 units:-  3 rupees per unit\n"
				+ "200 to 500 units:-  4 rupees per unit\n"
				+ "--------------Category 4(Above 500 units)---------\n"
				+ "0 to 100 units:-   free\n"
				+ "100 to 200 units:-  3.5 rupees per unit\n"
				+ "200 to 500 units:-  4.5 rupees per unit\n"
				+ "Above 500 units:-  6.6 rupees per unit";
		return val;
	}
}
