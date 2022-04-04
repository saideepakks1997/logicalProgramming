package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class GovnAidedPlaces extends Connection{
	

	public double charges = Tarrifs.govnPlacesCharges;

	public GovnAidedPlaces(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}
}
