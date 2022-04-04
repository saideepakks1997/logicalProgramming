package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class CottageAndTinyIndustries extends Connection{
	
	public double minUnits = Tarrifs.cottageMinUnits;
	public double chargesBelowMin = Tarrifs.cottageChargesBelowMin;
	public double chargesAboveMin = Tarrifs.cottageChargesAboveMin;
	
	
	
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
	
}
