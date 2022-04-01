package connection;

import consumer.Consumer;

public class PublicLightsVillageAndIndustrialmetro extends Connection{
	public double charges = 6.35;
	
	public PublicLightsVillageAndIndustrialmetro(long serviceNo, TypeOfConnection connectionType, long currentUnits,
			String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, currentUnits, connAddress, consumer);
	}

	

	public PublicLightsVillageAndIndustrialmetro(long serviceNo, TypeOfConnection connectionType, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}
	
}
