package connection;

import consumer.Consumer;

public class PowerLooms extends Connection{
	

	public int minUnits = Tarrifs.loomsMinUnits;
	public double chargesBelowMin = Tarrifs.loomsChargesBelowMin;
	public double chargesAboveMin = Tarrifs.loomsChargesAboveMin;
	
	

	
	public PowerLooms(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
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
