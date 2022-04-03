package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class PublicWorkshop extends Connection{
	
	public double minUnits = this.tarrif.getWorkshopMinUnits();
	public double chargesBelowMin = this.tarrif.getWorkshopChargesBelowMin();
	public double chargesAboveMin = this.tarrif.getWorkshopChargesAboveMin();
	
	public PublicWorkshop(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer,Tarrifs tarrifs) {
		super(serviceNo, connectionType, connAddress, consumer,tarrifs);
	}
}
