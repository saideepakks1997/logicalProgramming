package connection;

import consumer.Consumer;

public class GovnAidedPlaces extends Connection{
	

	public double charges = 5.75;
	
	public GovnAidedPlaces(long serviceNo, TypeOfConnection connectionType, long currentUnits, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, currentUnits, connAddress, consumer);
	}



	public GovnAidedPlaces(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}
}
