package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class PublicLightsVillageAndIndustrialmetro extends Connection{
	public double charges = Tarrifs.industrialMetroCharges;
	
	public PublicLightsVillageAndIndustrialmetro(long serviceNo, TypeOfConnection connectionType, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}
	
}
