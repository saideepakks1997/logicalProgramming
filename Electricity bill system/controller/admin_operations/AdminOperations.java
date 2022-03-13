package admin_operations;

import connection.Connection;
import eb.ElectricityBoard;

public class AdminOperations implements IAdminOperations{
	ElectricityBoard eb = null;
	public AdminOperations(ElectricityBoard eb) {
		this.eb = eb;
	}
	
	@Override
	public String setReading(long connNo,long readings) {
		Connection conn = this.eb.getConnections().get(connNo);
		if(conn == null) {
			return "Please enter correct service no";
		}
		else {
			long pastReadings = conn.getCurrentUnit();
			if(pastReadings > readings)
				return "Entered reading is less than past reading please check the readings ";
			else {
				conn.setCurrentUnit(readings);
				return "Reading has been set to "+readings;
			}
		}
	}
	
}
