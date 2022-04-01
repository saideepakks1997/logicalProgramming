package factory;

import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;

public interface IConnectionFactory {
	public Connection getConnectionObj(long serviceNo, TypeOfConnection connType, String connAddress,
			Consumer consumer);
}
