package connection;

import consumer.Consumer;

public class PublicLightTown extends Connection {
	
	public double charges = 6.35;
	
	public PublicLightTown(long serviceNo, TypeOfConnection connectionType, long currentUnits, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, currentUnits, connAddress, consumer);
	}

	public PublicLightTown(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}

	
}		
