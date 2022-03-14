package admin_operations;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bill.Payment;
import connection.Connection;
import connection.TypeOfConnection;
import eb.ElectricityBoard;

public class AdminOperations implements IAdminOperations{
	ElectricityBoard eb = null;
	BillCalculation billCalculation = new BillCalculation();
	public AdminOperations(ElectricityBoard eb) {
		this.eb = eb;
	}
	
	@Override
	public String setReading(long connNo,long newReadings) {
		Connection conn = this.eb.getConnections().get(connNo);
		if(conn == null) {
			return "Please enter correct service no";
		}
		else {
			long pastReadings = conn.getCurrentUnit();
			if(pastReadings > newReadings)
				return "Entered reading is less than past reading please check the readings ";
			else {
				double readings = newReadings - pastReadings;
				conn.setCurrentUnit(newReadings);
				setPendingPayment(readings, conn);
				return "Reading has been set to "+newReadings;
			}
		}
	}

	private void setPendingPayment(double readings, Connection conn) {
		double payableAmount = billCalculation.calculateBill(conn.getConnectionType(), readings);
		Payment payment = new Payment(payableAmount, readings); 
		conn.setPendingPayments(payment);
	}

	@Override
	public Map<Long, List<Payment>> getNonPayers() {
		Map<Long,List<Payment>> nonPayers = new TreeMap<Long,List<Payment>>();
		for(Long connNo: this.eb.getConnections().keySet()) {
			Connection conn = this.eb.getConnections().get(connNo);
			List<Payment> list = new ArrayList<Payment>();
			for(Payment payment: conn.getPendingPayments()) {
				list.add(payment);
			}
			if(list.size() > 0) {
				nonPayers.put(connNo, list);
			}
		}
		return nonPayers;
	}

	@Override
	public String changeConnectionType(TypeOfConnection connectionType, long connNo) {
		this.eb.getConnections().get(connNo).setConnectionType(connectionType);
		return "Connection type "+connectionType+" has been updated for service no "+connNo;
	}

	@Override
	public String createConnection(String user_name, TypeOfConnection connType) {
		long serviceNo = this.eb.getConnNoSeries();
		Connection conn = new Connection(serviceNo, connType);
		this.eb.getConsumers().get(user_name).setConnection(conn);
		this.eb.setConnections(conn);
		System.out.println("hello"+this.eb.getConnections().containsKey(22224));
		return "Connection has been created successfully service number is "+serviceNo;
	}
	
}
