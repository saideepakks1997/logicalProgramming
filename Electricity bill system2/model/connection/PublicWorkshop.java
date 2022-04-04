package connection;

import consumer.Consumer;

public class PublicWorkshop extends Connection{
	
	public double minUnits = Tarrifs.workshopMinUnits;
	public double chargesBelowMin = Tarrifs.workshopChargesBelowMin;
	public double chargesAboveMin = Tarrifs.workshopChargesAboveMin;
	
	public PublicWorkshop(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}
}
