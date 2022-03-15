package admin_operations;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bill.Payment;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ChangeOfConnectionRequest;
import eb.ElectricityBoard;
import eb.NewConnectionRequest;

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
	public String createConnectionForNewConsumer(String name,String email,long phoNo,String address,String connAddress, TypeOfConnection connType) {
		Consumer consumer = new Consumer(this.eb.getConsumerNoSeries(),name, email, phoNo, address);
		
		long serviceNo = this.eb.getConnNoSeries();
		Connection conn = new Connection(serviceNo, connType,connAddress,consumer);

		this.eb.setConnections(conn);
		this.eb.setConsumers(consumer);
		this.eb.getConsumers().get(consumer.getConsumerNO()).setConnection(conn);
		
		return "Connection has been created successfully service number is "+serviceNo+""
				+ " and consumer no is "+consumer.getConsumerNO();
	}

	@Override
	public String createConnectionForExistingConsumer(int customerNo,String connAddress, TypeOfConnection connType) {
		long serviceNo = this.eb.getConnNoSeries();
		Consumer consumer = this.eb.getConsumers().get(customerNo);
		Connection conn = new Connection(serviceNo, connType, connAddress, consumer);
		
		this.eb.setConnections(conn);
		this.eb.getConsumers().get(customerNo).setConnection(conn);
		
		return "New connection has been created service no is "+conn.getServiceNo();
	}

	@Override
	public List<NewConnectionRequest> getNewConnectionRequests() {
		List<NewConnectionRequest> requests = this.eb.getNewConnRequests();
		return requests;
	}

	@Override
	public String addNotification(int consumerNo, int reqIndex, String status,String reqType) {
		this.eb.getConsumers().get(consumerNo).setNotifications(status);
		if(reqType.equalsIgnoreCase("newConnection"))
			this.eb.getNewConnRequests().remove(reqIndex);
		else if(reqType.equalsIgnoreCase("changetype"))
			this.eb.getConnChangeRequests().remove(reqIndex);
		
		return "Notification has been added";
	}

	@Override
	public List<ChangeOfConnectionRequest> getConnectionChangeRequests() {
		List<ChangeOfConnectionRequest> req = this.eb.getConnChangeRequests();
		return req;
	}
	
}
