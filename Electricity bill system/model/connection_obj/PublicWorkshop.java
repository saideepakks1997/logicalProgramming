package connection_obj;

public class PublicWorkshop extends ConnectionObj{
	public int val = 10;
	private int minUnits = 120;
	private double chargesBelowMin = 2.85;
	private double chargesAboveMin = 5.75;
	
	public String toString() {
		String val = "less than "+minUnits+" :- "+chargesBelowMin+" rupees per unit\n"
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
