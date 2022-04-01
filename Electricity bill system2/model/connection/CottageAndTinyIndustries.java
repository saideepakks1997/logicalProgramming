package connection;

import consumer.Consumer;

public class CottageAndTinyIndustries extends Connection{
	
	public double minUnits = 500;
	

	public double chargesBelowMin = 4;
	public double chargesAboveMin = 4.6;
	
	
	public CottageAndTinyIndustries(long serviceNo, TypeOfConnection connectionType, long currentUnits,
			String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, currentUnits, connAddress, consumer);
	}
	public CottageAndTinyIndustries(long serviceNo, TypeOfConnection connectionType, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
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
