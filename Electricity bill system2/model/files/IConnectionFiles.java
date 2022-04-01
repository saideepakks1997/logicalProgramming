package files;

import connection.Connection;
import consumer.Consumer;
import eb.ElectricityBoard;

public interface IConnectionFiles {
	public void createConnection(Connection con);
	public void updateConnection(Connection con);
	public void loadConnection(ElectricityBoard eb, String[] connNos,Consumer consumer);
}
