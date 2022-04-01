package consumer_view;

import java.util.List;

import bill.Bill;
import bill.Payment;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import common_view.DisplayView;
import common_view.UserInputView;
import connection.Connection;
import connection.TypeOfConnection;
import consumer_operations.ConsumerOperations;
import consumer_operations.IConsumerOperations;
import eb.ElectricityBoard;
import eb.RequestObj;
import validator_encrypter.Validator;

public class ConsumerLoginView {
	DisplayView display = new DisplayView();
	IConsumerOperations operations = null;
	ICommonOperations commonOperations = null;
	UserInputView input = new UserInputView(); 
	
	CommonView commonView = null;
	public long consumerNo = 0;
	Validator validate = new Validator();
	public ConsumerLoginView(ElectricityBoard eb) {
		this.operations = new ConsumerOperations(eb);
		this.commonOperations = new CommonOperations(eb);
		
		this.commonView = new CommonView(eb);
	}
	
	public void askLoggedInOptions(long consumerNo) {
		boolean isAdmin = false;
		this.consumerNo = consumerNo;
		askToViewNotifications();
		boolean loop = true;
		while(loop) {
			display.displayMessege("Enter option \n"
					+ "1->View Connection details\n"
					+ "2->Pay bill\n"
					+ "3->Request for new connection\n"
					+ "4->Request for change of connection type\n"
					+ "5->View pendings transaction\n"
					+ "6->View transactions\n"
					+ "7->Notifications\n"
					+ "9->Log out");
			int opt = input.getInt();
			
				switch (opt) {
				case 1: viewConnectionDetails();
					break;
				case 2: payBillForParticularConsumer(isAdmin);
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
					display.displayMessege("Logging out");
					break;
				case -1: loop = false;
					break;
				default:display.displayMessege("Enter the correct option");
					break;
				}
			}
	}

	private void askToViewNotifications() {
		List<String> notifications = operations.getNotification(this.consumerNo);
		if(notifications.size() > 0) {
			display.displayMessege("You have Notifications \n"
					+ "Press 1->For view notifications\n"
					+ "Press any Number for ignore");
			int opt = input.getInt();
				if(opt == 1) {
					viewNotifications();
				}
				else {
					display.displayMessege("Back to login options");
				}
		}
		
	}

	private void viewNotifications() {
		List<String> notifications = operations.getNotification(this.consumerNo);
		if(notifications.size() == 0) {
			display.displayMessege("No notifications to display");
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
		if(serviceNo != -1) {
			List<Payment> pendingPayments = commonOperations.getAllPedingPayments(serviceNo);
			if(pendingPayments == null) {
				display.displayMessege("No pending amount");
			}
			else {
				display.displayPendingPayment(pendingPayments);
			}
		}
	}

	public void viewAllBills() {
		long serviceNo = selectConnectionNo();
		System.out.println("Serivicce no =="+serviceNo);
		if(serviceNo != -1) {
			List<Bill> bills = commonOperations.getBills(serviceNo);
			if(bills.size() == 0) {
				display.displayMessege("No transaction available");
			}
			else {
				for(Bill bill: bills) {
					System.out.println(bill);
					display.displayBill(bill);
				}
			}
		}
		
	}

	private void requestForChangeOfConnection() {
		long serviceNo = selectConnectionNo();
		if(serviceNo != -1) {
			System.out.println("Select connection type");
			TypeOfConnection connType = commonView.selectTypeOfConnection();
			if(connType != null) {
				RequestObj request = operations.changeOfConnectionReq(this.consumerNo, serviceNo, connType);
				if(request == null) {
					display.displayMessege("Connection is already of type "+connType);
				}
				else {
					display.displayMessege("Change of connection for "+request.getServiceNo()+" has been requested successfully and request number is "+request.getRequestNo());
				}
			}
		}
	}

	private void requestForNewConnection() {
		System.out.println("Enter address for the connection");
		String address = input.getString();
		System.out.println("Select type of connection");
		TypeOfConnection conType = commonView.selectTypeOfConnection();
		
		if(conType != null) {
			RequestObj reqObj = operations.newConnectionReq(this.consumerNo, address, conType);
			if(reqObj != null) {
				display.displayMessege("Request has been sent and request Number is "+reqObj.getRequestNo());
			}
			else {
				display.displayMessege("Request failed");
			}
		}
		
	}

	public void viewConnectionDetails() {
		List<Connection> consumerConns = operations.getConsumerConnection(this.consumerNo);
		display.displayConnections(consumerConns);
	}
	
	public long selectConnectionNo() {
		boolean loop = true;
		int chances = 1;
		while(loop) {
			loop = false;
			
			List<Connection> conns = operations.getConsumerConnection(this.consumerNo);
			if(conns.size() == 0) {
				display.displayMessege("No connection found ");
				return -1;
			}
			
			System.out.println("Select connection to pay bill\n");
			int i=1;
			
			for(Connection conn: conns) {
				System.out.println((i++)+"."+conn.getServiceNo());
			}
			
			int opt = input.getInt();
			if(opt != -1) {
				if(opt > conns.size()) {
					if(chances >= validate.getMaxChance()) {
						display.displayChancesMessege();
						display.displayMessege("Going back to previous menu");
						return -1;
					}
					chances++;
					display.displayMessege("Enter correct option");
					loop = true;
				}
				else {
					return conns.get(opt-1).getServiceNo();
				}
			}
		}
		return -1;
	}
	public void payBillForParticularConsumer(boolean isAdmin) {
		long serviceNo = selectConnectionNo();
		if(serviceNo != -1)
			commonView.viewAndPayAllPendingPayments(serviceNo, isAdmin);
		
	}
}
