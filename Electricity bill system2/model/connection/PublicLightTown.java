package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class PublicLightTown extends Connection {
	
	public double charges = this.tarrif.getLightTowncharges();
	public PublicLightTown(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer,Tarrifs tarrifs) {
		super(serviceNo, connectionType, connAddress, consumer,tarrifs);
	}
}		
