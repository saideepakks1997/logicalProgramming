package connection;

import consumer.Consumer;

public class PrivateHostpitalInstitution extends Connection{
	public double charges = 7.5;
	
	public PrivateHostpitalInstitution(long serviceNo, TypeOfConnection connectionType, long currentUnits,
			String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, currentUnits, connAddress, consumer);
	}

	public PrivateHostpitalInstitution(long serviceNo, TypeOfConnection connectionType, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}

	
}
