package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class Domestic extends Connection{
	
	public Domestic(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer,Tarrifs tarrifs) {
		super(serviceNo, connectionType, connAddress, consumer, tarrifs);
	}

	int freeUnits = this.tarrif.getDomesFreeUnits();
	
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
		double lessThan200 = this.tarrif.getDomes200lessThan200();
		double amount;
		units -= 100;
		amount = units * lessThan200;
		System.out.println("Amount :- "+amount);
		return amount;
	}
	
	private double lessThan500Units(double units) {
		double amount;
		double chargesLessThan200 = this.tarrif.getDomes500lessThan200();
		double chargesAbove200 = this.tarrif.getDomes500above200();
		units -= 100;
		amount = 100 * chargesLessThan200;
		units -= 100;
		amount += (units * chargesAbove200);
		return amount;
	}
	
	private double greaterThan500(double units) {
		double amount;
		double chargesLessThan200 = this.tarrif.getDomesAbove500lessThan200();
		double chargesLessThan500 = this.tarrif.getDomesAbove500lessThan500();
		double chargesGreaterThan500 = this.tarrif.getDomesAbove500Above500();
		units -= 100;
		amount = 100 * chargesLessThan200;
		units -= 100;
		amount += (300 * chargesLessThan500);
		units -= 300;
		amount += (units * chargesGreaterThan500);
		return amount;
	}
}
