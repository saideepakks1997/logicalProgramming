package connection;

import consumer.Consumer;

public class PrivateHostpitalInstitution extends Connection{
	public double charges = Tarrifs.privateHospitalCharges;
	
	
	public PrivateHostpitalInstitution(long serviceNo, TypeOfConnection connectionType, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
	}

	
}
