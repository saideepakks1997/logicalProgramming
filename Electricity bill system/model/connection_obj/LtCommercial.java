package connection_obj;

public class LtCommercial extends ConnectionObj{
	
	private int minUnits = 100;
	private double chargesBelowMin = 5;
	private double chargesAboveMin = 8.05;
	
	
	public String toString() {
		String val = "less than"+minUnits+" :- "+chargesBelowMin+" rupees per unit\n"
				+ "greater than "+minUnits+" :- "+chargesAboveMin+" rupees per unit";
		return val;
	}
	
	public int getMinUnits() {
		return minUnits;
	}

	public void setMinUnits(int minUnits) {
		this.minUnits = minUnits;
	}

	public double getChargesBelowMin() {
		return chargesBelowMin;
	}

	public void setChargesBelowMin(double chargesBelowMin) {
		this.chargesBelowMin = chargesBelowMin;
	}

	public double getChargesAboveMin() {
		return chargesAboveMin;
	}

	public void setChargesAboveMin(double chargesAboveMin) {
		this.chargesAboveMin = chargesAboveMin;
	}

}
