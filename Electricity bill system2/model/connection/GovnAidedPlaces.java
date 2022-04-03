package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class GovnAidedPlaces extends Connection{
	

	public double charges = this.tarrif.getGovnPlacesCharges();

	public GovnAidedPlaces(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer,Tarrifs tarrifs) {
		super(serviceNo, connectionType, connAddress, consumer,tarrifs);
	}
}
