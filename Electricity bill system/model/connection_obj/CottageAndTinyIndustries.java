package connection_obj;

public class CottageAndTinyIndustries extends ConnectionObj{

	private double minUnits = 500;
	private double chargesBelowMin = 4;
	private double chargesAboveMin = 4.6;
	@Override
	public String getTarrif() {
		String val = "less than "+minUnits+" units"+chargesBelowMin+"\n"
				+ "greater than "+minUnits+" units"+chargesAboveMin;
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
