package connection;

import consumer.Consumer;
import eb.Tarrifs;

public class TemporarySupply extends Connection{
	public double charges = this.tarrif.getTempSupplyCharges();

	public TemporarySupply(long serviceNo, TypeOfConnection connectionType, String connAddress, Consumer consumer,Tarrifs tarrifs) {
		super(serviceNo, connectionType, connAddress, consumer,tarrifs);
		}
	}
