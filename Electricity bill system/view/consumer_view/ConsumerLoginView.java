package consumer_view;

import java.util.List;

import bill.Bill;
import bill.Payment;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import connection.Connection;
import connection.TypeOfConnection;
import consumer_operations.ConsumerOperations;
import consumer_operations.IConsumerOperations;
import eb.ChangeOfConnectionRequest;
import eb.ElectricityBoard;
import eb.NewConnectionRequest;
import eb.RequestStatus;

public class ConsumerLoginView {
	IConsumerOperations operations = null;
	ICommonOperations commonOperations = null;
	
	CommonView commonView = null;
//	public String user_name = null;
	public long consumerNo = 0;
	
	public ConsumerLoginView(ElectricityBoard eb) {
		this.operations = new ConsumerOperations(eb);
		this.commonOperations = new CommonOperations(eb);
		
		this.commonView = new CommonView(eb);
	}
	
	public void askLoggedInOptions(long consumerNo) {
		boolean isAdmin = false;
		this.consumerNo = consumerNo;
			askToViewNotifications();
//		this.user_name = user_name;
		boolean loop = true;
		while(loop) {
			commonView.displayMessege("Enter option \n"
					+ "1->View Connection details\n"
					+ "2->Pay bill\n"
					+ "3->Request for new connection\n"
					+ "4->Request for change of connection type\n"
					+ "5->View pendings transaction"
					+ "6->View transactions\n"
					+ "7->Notifications\n"
					+ "9->Log out");
			int opt = commonView.getInt();
			switch (opt) {
			case 1: viewConnectionDetails();
				break;
			case 2: payBill(isAdmin);
				break;
			case 3: requestForNewConnection();
				break;
			case 4: requestForChangeOfConnection();
				break;
			case 5: viewPendingTransactions();
				break;
			case 6: viewAllBills();
				break;
			case 7: viewNotifications();
				break;
			case 9: loop = false;
				commonView.displayMessege("Logging out");
				break;
			default:commonView.displayMessege("Enter the correct option");
				break;
			}
		}
	}

	private void askToViewNotifications() {
		List<String> notifications = operations.getNotification(this.consumerNo);
		if(notifications.size() > 0) {
			commonView.displayMessege("You have Notifications \n"
					+ "Press 1->For view notifications\n"
					+ "Press any Number for ignore");
			int opt = commonView.getInt();
			if(opt == 1) {
				viewNotifications();
			}
			else {
				commonView.displayMessege("Back to login options");
			}
		}
		
	}

	private void viewNotifications() {
		List<String> notifications = operations.getNotification(this.consumerNo);
		if(notifications.size() == 0) {
			commonView.displayMessege("No notifications to display");
		}
		else {
			int i=1;
			System.out.println("---------------------");
			for(String notification: notifications) {
				System.out.println((i++)+"."+notification);
			}
			System.out.println("---------------------");
		}
		
		
	}

	public void viewPendingTransactions() {
		long serviceNo = selectConnectionNo();
		List<Payment> pendingPayments = commonOperations.getAllPedingPayments(serviceNo);
		if(pendingPayments == null) {
			commonView.displayMessege("No pending amount");
		}
		else {
			commonView.displayPendingPayment(pendingPayments);
		}
	}

	public void viewAllBills() {
		long serviceNo = selectConnectionNo();
		List<Bill> bills = commonOperations.getBills(serviceNo);
		if(bills.size() == 0) {
			commonView.displayMessege("No transaction available");
		}
		else {
			for(Bill bill: bills) {
				commonView.displayBill(bill);
			}
		}
	}

	private void requestForChangeOfConnection() {
		long serviceNo = selectConnectionNo();
		if(serviceNo != -1) {
			System.out.println("Select connection type");
			TypeOfConnection connType = commonView.selectTypeOfConnection();
			ChangeOfConnectionRequest requestObj = operations.changeOfConnectionRequest(this.consumerNo,serviceNo, connType);
			if(requestObj != null) {
				commonView.displayMessege("Connection is already of type "+connType);
			}
			else {
				commonView.displayMessege("Change of connection for "+requestObj.getServiceNo()+" has been requested successfully and request number is "+requestObj.getRequestNo());
			}
		}
	}

	private void requestForNewConnection() {
		System.out.println("Enter address for the connection");
		String address = commonView.getString();
		System.out.println("Select type of connection");
		TypeOfConnection conType = commonView.selectTypeOfConnection();
		
		NewConnectionRequest request = operations.newConnectionRequest(this.consumerNo, address, conType);
//		String status = RequestStatus.values()[request.getStatusNo()].displayName(); 
		commonView.displayMessege("Request has been sent and request Number is "+request.getRequestNo());
	}

	public void payBill(boolean isAdmin) {
		long serviceNo = selectConnectionNo();
		if(serviceNo != -1)
			commonView.viewAndPayAllPendingPayments(serviceNo, isAdmin);
	}

	public void viewConnectionDetails() {
		List<Connection> consumerConns = operations.getConsumerConnection(this.consumerNo);
		commonView.displayConnections(consumerConns);
	}
	
	private long selectConnectionNo() {
		boolean loop = true;
		while(loop) {
			loop = false;
			
			List<Connection> conns = operations.getConsumerConnection(this.consumerNo);
			if(conns.size() == 0) {
				commonView.displayMessege("No connection found ");
				return -1;
			}
			System.out.println("Select connection to pay bill\n");
			int i=1;
			for(Connection conn: conns) {
				System.out.println((i++)+"."+conn.getServiceNo());
			}
			int opt = commonView.getInt();
			if(opt > conns.size()) {
				commonView.displayMessege("Enter correct option");
				loop = true;
			}
			else {
				return conns.get(opt-1).getServiceNo();
			}
		}
		return 0;
	}
}
