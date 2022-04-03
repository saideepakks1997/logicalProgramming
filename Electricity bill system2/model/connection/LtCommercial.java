package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class LtCommercial extends Connection{
	
	

	public double minUnits = this.tarrif.getCommercialMinUnits();
	public double chargesBelowMin = this.tarrif.getCommercialChargesBelowMin();
	public double chargesAboveMin = this.tarrif.getCommercialChargesAboveMin();
	
	public LtCommercial(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer,Tarrifs tarrifs) {
		super(serviceNo, connectionType, connAddress, consumer,tarrifs);
	}
	
	
	
	@Override
	public double calculateBill(double units) {
		if(units <= minUnits)
			return units * chargesBelowMin;
		else
			return units * chargesAboveMin;
	}
	
}
