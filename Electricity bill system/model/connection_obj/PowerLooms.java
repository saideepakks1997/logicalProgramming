package connection_obj;

public class PowerLooms extends ConnectionObj{

	private double minUnits = 1000;
	private double chargesBelowMin = 2.3;
	private double chargesAboveMin = 4.6;
	
	
	public String getTarrif() {
		String val = "less than "+minUnits+" "+chargesBelowMin+"\n"
				+ "greater than "+minUnits+" "+chargesAboveMin;
		return val;
	}
	
	public double getMinUnits() {
		return minUnits;
	}

	public void setMinUnits(double minUnits) {
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
