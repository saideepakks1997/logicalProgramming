package bill_calculation_operations;

public class CommercialBillCalculation implements ICalculateBill{
	
	@Override
	public double calculteBillAmount(double units) {
		if(units <= 100)
			return units * 5;
		else if(units > 100)
			return units * 8.05;
		return 0;
	}

}
