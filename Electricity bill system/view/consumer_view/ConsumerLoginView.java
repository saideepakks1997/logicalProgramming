package consumer_view;

import java.util.List;

import bill.Bill;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import connection.Connection;
import connection.TypeOfConnection;
import consumer_operations.ConsumerOperations;
import consumer_operations.IConsumerOperations;
import eb.ElectricityBoard;

public class ConsumerLoginView {
	IConsumerOperations operations = null;
	ICommonOperations commonOperations = null;
	
	CommonView commonView = null;
	String user_name = null;
	
	public ConsumerLoginView(ElectricityBoard eb) {
		this.operations = new ConsumerOperations(eb);
		this.commonOperations = new CommonOperations(eb);
		
		this.commonView = new CommonView(eb);
	}
	
	public void askLoggedInOptions(String user_name) {
		this.user_name = user_name;
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
			case 2: payBill();
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

	private void viewNotifications() {
		List<String> notifications = operations.getNotification(user_name);
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

	private void viewPendingTransactions() {
		long serviceNo = selectConnectionNo();
		String pendingPayments = commonOperations.getAllPedingPayments(serviceNo);
		commonView.displayMessege(pendingPayments);
	}

	private void viewAllBills() {
		long serviceNo = selectConnectionNo();
		List<Bill> bills = commonOperations.getBills(serviceNo);
		if(bills.size() == 0) {
			commonView.displayMessege("No bill available");
		}
		else {
			for(Bill bill: bills)
				commonView.displayMessege(bill);
		}
	}

	private void requestForChangeOfConnection() {
			System.out.println("Select connection type");
			TypeOfConnection connType = commonView.selectTypeOfConnection();
			long serviceNo = selectConnectionNo();
			String result = operations.changeOfConnectionRequest(this.user_name,serviceNo, connType);
			commonView.displayMessege(result);
		}

	

	private void requestForNewConnection() {
		System.out.println("Enter address for the connection");
		String address = commonView.getString();
		System.out.println("Select type of connection");
		TypeOfConnection conType = commonView.selectTypeOfConnection();
		
		String result = operations.newConnectionRequest(this.user_name, address, conType);
		commonView.displayMessege(result);
	}

	private void payBill() {
		long serviceNo = selectConnectionNo();
		commonView.viewAndPayAllPendingPayments(serviceNo);
	}

	private void viewConnectionDetails() {
		List<Connection> consumerConns = operations.getConsumerConnection(this.user_name);
		commonView.displayConnections(consumerConns);
	}
	
	private long selectConnectionNo() {
		boolean loop = true;
		while(loop) {
			loop = false;
			System.out.println("Select connection to pay bill\n");
			int i=1;
			List<Connection> conns = operations.getConsumerConnection(this.user_name);
			if(conns.size() == 0) {
				commonView.displayMessege("No connection found ");
				return -1;
			}
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
