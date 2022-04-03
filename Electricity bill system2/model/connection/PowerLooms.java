package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class PowerLooms extends Connection{
	

	public int minUnits = this.tarrif.getLoomsMinUnits();
	public double chargesBelowMin = this.tarrif.getLoomsChargesBelowMin();
	public double chargesAboveMin = this.tarrif.getLoomsChargesAboveMin();
	
	

	
	public PowerLooms(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer,Tarrifs tarrifs) {
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
