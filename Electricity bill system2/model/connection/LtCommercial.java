package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class LtCommercial extends Connection{
	
	

	public double minUnits = Tarrifs.commercialMinUnits;
	public double chargesBelowMin = Tarrifs.commercialChargesBelowMin;
	public double chargesAboveMin = Tarrifs.commercialChargesAboveMin;
	
	public LtCommercial(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
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
