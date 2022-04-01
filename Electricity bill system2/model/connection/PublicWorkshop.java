package connection;

import consumer.Consumer;

public class PublicWorkshop extends Connection{
	
	public double minUnits = 120;
	public double chargesBelowMin = 2.85;
	public double chargesAboveMin = 5.75;
	
	
	public PublicWorkshop(long serviceNo, TypeOfConnection connectionType, long currentUnits, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, currentUnits, connAddress, consumer);
	}
	public PublicWorkshop(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}
	
	@Override
	public String toString() {
		String val = "less than "+(int)minUnits+" units :- "+chargesBelowMin+" rupees per unit\n"
				+ "greater than "+(int)minUnits+" units :- "+chargesAboveMin+" rupees per unit";
		return val;
	}
	
}
