package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class PrivateHostpitalInstitution extends Connection{
	public double charges = this.tarrif.getPrivateHospitalCharges();
	
	
	public PrivateHostpitalInstitution(long serviceNo, TypeOfConnection connectionType, String connAddress,
			Consumer consumer,Tarrifs tarrifs) {
		super(serviceNo, connectionType, connAddress, consumer,tarrifs);
	}

	
}
