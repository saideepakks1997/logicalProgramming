package admin_operations;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


import bill.Payment;
import connection.Connection;
import connection.ConnectionFiles;
import connection.TypeOfConnection;
import consumer.Consumer;
import consumer.ConsumerFiles;
import eb.ElectricityBoard;
import eb.RequestObj;
import eb.RequestStatus;

public class AdminOperations implements IAdminOperations{
	ElectricityBoard eb = null;
	ConnectionFiles connFiles = null;
	ConsumerFiles consumerFiles = null;
	public AdminOperations(ElectricityBoard eb) {
		this.eb = eb;
		this.connFiles = new ConnectionFiles(eb);
		this.consumerFiles = new ConsumerFiles(eb);
	}
	
	@Override
	public boolean setReading(long connNo,long newReadings) {
		Connection conn = this.eb.getConnections().get(connNo);
		long pastReadings = conn.getCurrentUnit();
			if(pastReadings > newReadings)
				return false;
			else {
				double readings = newReadings - pastReadings;
				conn.setCurrentUnit(newReadings);
//				Connection consumer = this.eb.getConsumers().get(1l).getConnections().get(0);
//				System.out.println(conn);
//				System.out.println(consumer);
				
//				connFiles.updateConnectionUnit(conn, newReadings);
				
				setPendingPayment(readings, conn);
				return true;
			}
		}
	
	private boolean setPendingPayment(double readings, Connection conn) {
		double payableAmount = conn.getConnectionType().getObj().calculateBill(readings);

		if(payableAmount > 0) {
			Payment payment = new Payment(payableAmount, readings); 
			conn.setPendingPayments(payment);
		}
		consumerFiles.updateConusumer(conn);
//		connFiles.updateConnection(conn);
		
		return true;
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
	public boolean changeConnectionType(TypeOfConnection connectionType, long connNo) {
		TypeOfConnection prevConnType = this.eb.getConnections().get(connNo).getConnectionType();
		if(prevConnType == connectionType) { 
			
			return false;
		}
		else {
			this.eb.getConnections().get(connNo).setConnectionType(connectionType);
			return true;
		}
	}

	@Override
	public Connection createConnectionForNewConsumer(String name,String email,long phoNo,String address,String connAddress, TypeOfConnection connType) {
		Consumer consumer = new Consumer(this.eb.getConsumerNoSeries(),name, email, phoNo, address);
		
		long serviceNo = this.eb.getConnNoSeries();
		Connection conn = new Connection(serviceNo, connType,connAddress,consumer);

		this.eb.setConnections(conn);
		this.eb.setConsumers(consumer);
		this.eb.getConsumers().get(consumer.getConsumerNO()).setConnection(conn);
		
		return conn;
	}

	@Override
	public Connection createConnectionForExistingConsumer(long customerNo,String connAddress, TypeOfConnection connType) {
		long serviceNo = this.eb.getConnNoSeries();
		Consumer consumer = this.eb.getConsumers().get(customerNo);
		Connection conn = new Connection(serviceNo, connType, connAddress, consumer);
		
		this.eb.setConnections(conn);
		this.eb.getConsumers().get(customerNo).setConnection(conn);
		
		return conn;
	}


	@Override
	public Map<Long, Consumer> getAllConsumers() {
		return this.eb.getConsumers();
	}

	@Override
	public List<RequestObj> getRequests(boolean isNewConnectionReq) {
		Set<RequestObj> allRequests = this.eb.getRequests();
		List<RequestObj> requests = new ArrayList<>();
		//all new Connection requests
		if(isNewConnectionReq) {
			for(RequestObj r : allRequests) {
				if(r.isNewConnectionReq()) {
					requests.add(r);
					System.out.println(r);
				}
					
			}
		}
		//all change of type connections change requests
		else {
			for(RequestObj r : allRequests) {
				if(!r.isNewConnectionReq())
					requests.add(r);
			}
		}
		return requests;
	}

	@Override
	public boolean updateStatus(RequestObj req) {
		req.setStatusNo();
		if(req.getStatusNo() == RequestStatus.values().length - 1) {
			removeRequest(req);
//			Set<RequestObj> requests = this.eb.getRequests();
//					requests.remove(req);
		}
		return true;
	}

	@Override
	public boolean removeRequest(RequestObj req) {
		boolean isRemoved = this.eb.getRequests().remove(req);
		req.setRequestCompleted(true);
		return isRemoved;
	}

	@Override
	public void setNotification(RequestObj req, String status) {
		long consumerNo = req.getConsumerNo();
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		consumer.setNotifis(req, status);
		req.setLastUpdatedTime();
		consumerFiles.updateConusumer(consumer);
	}
}
