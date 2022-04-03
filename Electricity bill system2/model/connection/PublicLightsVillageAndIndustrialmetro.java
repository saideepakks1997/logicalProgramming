package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class PublicLightsVillageAndIndustrialmetro extends Connection{
	public double charges = this.tarrif.getIndustrialMetroCharges();
	
	public PublicLightsVillageAndIndustrialmetro(long serviceNo, TypeOfConnection connectionType, String connAddress,
			Consumer consumer,Tarrifs tarrifs) {
		super(serviceNo, connectionType, connAddress, consumer,tarrifs);
	}
	
}
