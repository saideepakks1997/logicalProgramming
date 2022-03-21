package connection_obj;

public class PowerLooms extends ConnectionObj{

	private int minUnits = 1000;
	private double chargesBelowMin = 2.3;
	private double chargesAboveMin = 4.6;
	
	
	public String toString() {
		String val = "less than  "+minUnits+" :-  "+chargesBelowMin+" rupees per unit\n"
				+ "greater than "+minUnits+" :-  "+chargesAboveMin+" rupees per unit";
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
