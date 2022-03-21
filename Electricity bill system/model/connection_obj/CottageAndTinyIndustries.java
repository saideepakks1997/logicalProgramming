package connection_obj;

public class CottageAndTinyIndustries extends ConnectionObj{

	private int minUnits = 500;
	private double chargesBelowMin = 4;
	private double chargesAboveMin = 4.6;
	
	
	public String toString() {
		String val = "less than "+minUnits+" units:- "+chargesBelowMin+" rupees per unit\n"
				+ "greater than "+minUnits+" units:- "+chargesAboveMin+" rupees per unit";
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
