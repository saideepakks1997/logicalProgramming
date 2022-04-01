package connection_obj;

public class LtCommericalObj extends ConnectionObj{
	public double minUnits = 100;
	public double chargesBelowMin = 5;
	public double chargesAboveMin = 8.05;
	
	@Override
	public double calculateBill(double units) {
		if(units <= minUnits)
			return units * chargesBelowMin;
		else
			return units * chargesAboveMin;
	}
}
