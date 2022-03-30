package connection_obj;

public class SplitChargesConnection extends ConnectionObj{
	double minUnits ;
	double chargesBelowMin;
	double chargesAboveMin;
	public SplitChargesConnection(double minUnits, double chargesBelowMin, double chargesAboveMin) {
		this.minUnits = minUnits;
		this.chargesBelowMin = chargesBelowMin;
		this.chargesAboveMin = chargesAboveMin;
	}
	@Override
	public double calculateBill(double units) {
		if(units <= minUnits)
			return units * chargesBelowMin;
		else
			return units * chargesAboveMin;
	}
	
	@Override
	public String toString() {
		String val = "less than "+(int)minUnits+" units :- "+chargesBelowMin+" rupees per unit\n"
				+ "greater than "+(int)minUnits+" units :- "+chargesAboveMin+" rupees per unit";
		return val;
	}
}
