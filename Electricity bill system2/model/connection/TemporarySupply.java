package connection;

import consumer.Consumer;

public class TemporarySupply extends Connection{
	public double charges = Tarrifs.tempSupplyCharges;

	public TemporarySupply(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer) {
		super(serviceNo, connectionType, connAddress, consumer);
		}
	}
