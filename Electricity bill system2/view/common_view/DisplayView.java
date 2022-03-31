package common_view;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import bill.Bill;
import bill.Payment;
import connection.Connection;
import consumer.Consumer;
import eb.RequestObj;
import eb.RequestStatus;

public class DisplayView {
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	public void displayMessege(Object messege) {
		System.out.println("---------------------------------------------------");
		System.out.println(messege);
		System.out.println("---------------------------------------------------");
	}
	
	
	public void displayRequest(List<RequestObj> requests, boolean isNewConnRequest) {
		int i = 1;
		if(isNewConnRequest) {
			for(RequestObj req : requests) {
				System.out.println("------------------------------");
				System.out.println("S.NO"+(i++));
				System.out.println(
						"ConsumerNo :- "+req.getConsumerNo()+"\n"
								+ "Address :- "+req.getAddress()+"\n"
								+ "Type of connection requested :- "+req.getConnType()+"\n"
								+ "Request Status :- "+RequestStatus.values()[req.getStatusNo()].displayName()
						);
				System.out.println("------------------------------");
			}
		}
		else {
			for(RequestObj req : requests) {
				System.out.println("------------------------------");
				System.out.println("S.NO :- "+(i++));
				System.out.println(
						"ConsumerNo :- "+req.getConsumerNo()+"\n"
								+ "Type of connection requested for change:- "+req.getConnType()+"\n"
								+ "Request Status :- "+RequestStatus.values()[req.getStatusNo()].displayName()
						);
				System.out.println("------------------------------");
			}
		}
	}
	
	public void displayNonPayers(String connType, Long connNo, List<Payment> list) {
		System.out.println("--------Connection Type :- "+connType+"--------------");
		System.out.println("--------------Service no:-"+connNo+"---------------");
		int i=1;
		for(Payment payment: list) {
			System.out.println((i++)+"."+payment);
		}
		System.out.println("---------------------------------------");
	}
	
	public void displayBill(Bill bill) {
		int i=1;
		try {
			System.out.println("------------------");
			System.out.println("Amount has been paid successfully\n"
					+ "bill No :- "+bill.getBillNo()+"\n"
					+ "Paid amount :- "+bill.getPayment().getPayableAmount()+"\n"
					+ "Units consumed :- "+bill.getPayment().getUnitsConsumed()+"\n"
					+ "Paid date :- "+bill.getPaymentDate().format(dateFormatter)+"\n"
					+ "Paid time :- "+bill.getPaymentDate().format(timeFormatter)+"\n"
					+ "Paid Through :- "+bill.getPaidThrough());
			System.out.println("------------------");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void displayConnections(List<Connection> consumerConns) {
		System.out.println("-------------------------------------------");
		int i=1;
		System.out.printf("%3s %10s %25s %20s","Sno","ServiceNo","ConnectionType","Address\n");
		
		for(Connection con: consumerConns) {
			System.out.printf("%3d %10d %25s %20s",(i++),con.getServiceNo(),con.getConnectionType(),con.getConnAddress());
			System.out.println();
		}
		System.out.println("-------------------------------------------");
	}
	
	public void displayPendingPayment(List<Payment> pendingPayments) {
		int i=1;
		System.out.println("------------------");
		for(Payment payment: pendingPayments) {
			System.out.println((i++)+"."+payment);
		}
		System.out.println("------------------");
		
	}
	public void displayChancesMessege() {
		System.out.println("------------------");
		System.out.println("Maximum chances given please try after sometimes");
		System.out.println("------------------");
	}

	public void displayConsumerConnection(Consumer consumer) {
		System.out.println("--------------------------------------------------");
		System.out.println("Consumer No :- "+consumer.getConsumerNO());
		System.out.println("Consumer Name :- "+consumer.getName());
		System.out.println("Consumer Address :- "+consumer.getAddress());
		System.out.println("Phone No :- "+consumer.getPhoNo());
		System.out.println("Email  :- "+consumer.getEmailId());
		
		Collection<Connection> connections =  consumer.getConnections().values();
		if(connections.size() == 0)
			displayMessege("No Connections available");
		else {
			System.out.printf("%10s %15s %30s %30s","Serivce No","Current Units","Connection Type","Connection Address");
			for(Connection con: connections) {
				System.out.println();
				System.out.printf("%10d %15d %30s %30s",con.getServiceNo(),con.getCurrentUnit(),con.getConnectionType().getResponse(),con.getConnAddress());
				System.out.println();
			}
			System.out.println("--------------------------------------------------");
		}
	}
}
