package admin_operations;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import bill.Payment;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;
import eb.RequestObj;
import eb.RequestStatus;
import factory.ConnectionFactoryObj;
import factory.IConnectionFactory;
import files.ConnectionFiles;
import files.ConsumerFiles;
import files.IConnectionFiles;
import files.IConsumerFiles;
import files.IPaymentFile;
import files.PaymentFile;
import files.RequestObjFiles;

public class AdminOperations implements IAdminOperations{
	ElectricityBoard eb = null;
	
	IConnectionFiles connFile = new ConnectionFiles();
	IConsumerFiles consumerFile = new ConsumerFiles();
	IPaymentFile paymentFile = new PaymentFile();
	
	IConnectionFactory connFactory = new ConnectionFactoryObj();
	RequestObjFiles reqObjFile = new RequestObjFiles();
	public AdminOperations(ElectricityBoard eb) {
		this.eb = eb;
	}
	
	@Override
	public boolean setReading(long connNo,long newReadings) {
		Connection conn = this.eb.getConnections().get(connNo);
		long pastReadings = conn.getCurrentUnit();
			if(pastReadings > newReadings)
				return false;
			else {
				System.out.println(newReadings);
				long readings = newReadings - pastReadings;
				conn.setCurrentUnit(newReadings);
				setPendingPayment(readings, conn);
				connFile.updateConnection(conn);//file update
				return true;
			}
		}
	
	private boolean setPendingPayment(Long readings, Connection conn) {
		double payableAmount = conn.calculateBill(readings);
		if(payableAmount > 0) {
			Payment payment = new Payment(this.eb.getPaymentIdSeries(),payableAmount, readings); 
			conn.setPendingPayments(payment);
			paymentFile.createPayment(payment);//file system
		}		
		return true;
	}
	
	@Override
	public Map<Long, List<Payment>> getNonPayers() {
		Map<Long,List<Payment>> nonPayers = new TreeMap<Long,List<Payment>>();
		
		for(Connection conn: this.eb.getConnections().values()) {
			List<Payment> list = new ArrayList<Payment>();
			for(Payment payment: conn.getPendingPayments()) {
				list.add(payment);
			}
			if(list.size() > 0) {
				nonPayers.put(conn.getServiceNo(), list);
			}
		}
		return nonPayers;
	}

	@Override
	public boolean changeConnectionType(TypeOfConnection connectionType, long connNo) {
		Connection conn = this.eb.getConnections().get(connNo);
		TypeOfConnection prevConnType = conn.getConnectionType();
		if(prevConnType == connectionType) { 
			return false;
		}
		else {
			conn.setConnectionType(connectionType);
			connFile.updateConnection(conn);//file system
			return true;
		}
	}

//	@Override
//	public Connection createConnectionForNewConsumer(String name,String email,long phoNo,String address,String connAddress, TypeOfConnection connType) {
//		email = email.toLowerCase();
//		Consumer consumer = new Consumer(this.eb.getConsumerNoSeries(),name, email, phoNo, address);
//		
//		long serviceNo = this.eb.getConnNoSeries();
//		Connection conn = new Connection(serviceNo, connType,connAddress,consumer);
//
//		this.eb.setConnections(conn);
//		this.eb.setConsumers(consumer);
//		consumer.setConnection(conn);
//		serialize.updateEbFile(this.eb);
//		return conn;
//	}

	@Override
	public Connection createConnection(long customerNo,String connAddress, TypeOfConnection connType) {
		
		
		long serviceNo = this.eb.getConnNoSeries();
		Consumer consumer = this.eb.getConsumers().get(customerNo);
//		Connection conn = new Connection(serviceNo, connType, connAddress, consumer);
		Connection con = connFactory.getConnectionObj(serviceNo, connType, connAddress, consumer,this.eb.getTarrifs());
		this.eb.setConnections(con);
		consumer.setConnection(con);
		
		connFile.createConnection(con);//file system
		consumerFile.updateConsumer(consumer);//file system
		
		return con;
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
		Consumer consumer = this.eb.getConsumers().get(req.getConsumerNo());
		if(req.getStatusNo() == RequestStatus.values().length - 1) {
			removeRequest(req);
		}
		reqObjFile.updateRequest(req);//file system
		consumerFile.updateConsumer(consumer);//file system
		return true;
	}

	@Override
	public boolean removeRequest(RequestObj req) {
		Consumer consumer = this.eb.getConsumers().get(req.getConsumerNo());
		boolean isRemoved = this.eb.getRequests().remove(req);
		req.setRequestCompleted(true);
		
		reqObjFile.updateRequest(req);//file system
		consumerFile.updateConsumer(consumer);//file system
		
		return isRemoved;
	}

	@Override
	public void setNotification(RequestObj req, String status) {
		long consumerNo = req.getConsumerNo();
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		consumer.setNotifis(req, status);
		
		consumerFile.updateConsumer(consumer);//file system
		
		req.setLastUpdatedTime();
	}
}
