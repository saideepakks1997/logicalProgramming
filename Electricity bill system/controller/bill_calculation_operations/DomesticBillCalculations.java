package bill_calculation_operations;

import connection.TypeOfConnection;

public class DomesticBillCalculations implements ICalculateBill{
	double amount = 0;
	
	@Override
	public double calculteBillAmount(double units,TypeOfConnection type) {
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
		units -= 100;
		amount = units * 1.5;
		System.out.println("Amount :- "+amount);
		return amount;
	}
	
	private double lessThan500Units(double units) {
		double chargesLessThan200 = 2;
		double chargesAbove200 = 3;
		units -= 100;
		amount = 100 * chargesLessThan200;
		units -= 100;
		amount = units * chargesAbove200;
		return amount;
	}
	
	private double greaterThan500(double units) {
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
}
