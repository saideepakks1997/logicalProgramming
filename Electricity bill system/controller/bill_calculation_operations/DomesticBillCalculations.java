package bill_calculation_operations;

public class DomesticBillCalculations extends CalculateBill{
	double amount = 0;
	
	@Override
	public double calculteBillAmount(double units) {
		if(units <= 100)
			return 0;
		else if(units <= 200) {
			units -= 100;
			amount = units * 1.5;
			return amount;
		}
		else if(units <= 500) {
			units -= 100;
			amount = 100 * 2;
			units -= 100;
			amount = units * 3;
			return amount;
		}
		else if(units > 500) {
			units -= 100;
			amount = 100 * 3.5;
			units -= 100;
			amount += (300 * 4.6);
			units -= 300;
			amount += (units * 6.6);
			return amount;
		}
		return 0;
	}

}
