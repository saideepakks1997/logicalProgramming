package connection;

import consumer.Consumer;

public class PublicLightTown extends Connection {
	
	public double charges = Tarrifs.lightTowncharges;
	public PublicLightTown(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}
}		
