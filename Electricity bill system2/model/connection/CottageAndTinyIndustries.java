package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class CottageAndTinyIndustries extends Connection{
	
	public double minUnits = this.tarrif.getCottageMinUnits();
	public double chargesBelowMin = this.tarrif.getCottageChargesBelowMin();
	public double chargesAboveMin = this.tarrif.getCottageChargesAboveMin();
	
	
	
	public CottageAndTinyIndustries(long serviceNo, TypeOfConnection connectionType, String connAddress,
			Consumer consumer,Tarrifs tarrifs) {
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
