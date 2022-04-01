package connection;

import consumer.Consumer;

public class TemporarySupply extends Connection{
	public double charges = 12;
	
	public TemporarySupply(long serviceNo, TypeOfConnection connectionType, long currentUnits, String connAddress,
			Consumer consumer) {
		super(serviceNo, connectionType, currentUnits, connAddress, consumer);
		
	}

	public TemporarySupply(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
		
	}

}
