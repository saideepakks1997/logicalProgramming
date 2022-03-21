package admin_view;

import java.util.List;
import java.util.Map;
import java.util.Set;

import admin_operations.AdminOperations;
import admin_operations.IAdminOperations;
import bill.Payment;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import consumer.Consumer;
import eb.ElectricityBoard;

public class AdminView {
	CommonView commonView = null;
	NewOrUpdateConnectionView connectionView = null;
	ConsumerMenuView cmenuView = null;
	
	ICommonOperations commonOperations = null;
	IAdminOperations operations = null;
	
	public AdminView(ElectricityBoard eb) {
		this.commonOperations = new CommonOperations(eb);
		this.operations = new AdminOperations(eb);
		
		this.connectionView = new NewOrUpdateConnectionView(eb);
		this.commonView = new CommonView(eb);
		this.cmenuView = new ConsumerMenuView(eb);
	}

	public void adminOptions() {
		boolean isAdmin = true;
		boolean loop = true;
		while(loop) {
			
			commonView.displayMessege("Enter option\n"
					+ "1->Set units consumed\n"
					+ "2->View non payers\n"
					+ "3->pay bill\n"
					+ "4->New connection or change type of connection\n"
					+ "5->View all consumers\n"
					+ "6->Go to consumer menu\n"
					+ "9->log out");
			int opt = commonView.getInt();
			switch(opt) {
				case 1: setUnitsConsumed();
		 			break;
				case 2: viewNonPayers();
					break;
				case 3: commonView.payBill(isAdmin);
					break;
				case 4: connectionView.askOptions();
					break;
				case 5: ViewAllConsumers();
					break;
				case 6: goToConsumerMenu();
					break;
				case 9: loop = false;
				commonView.displayMessege("logging out");
					break;
				default: commonView.displayMessege("please enter correct option");
					break;
			}
		}
	}
	

	
	//Set units consumed for the consumer for example first month
	//  month reading 100 and next month may be 175
	private void setUnitsConsumed() {
		boolean loop = true;
		while(loop) {
			loop = false;
			long connNo = commonView.getConnectionNo();
			if(connNo != -1) {
				System.out.println("Enter the reading to set");
				long readings = commonView.getLong();
				
				boolean isReadingsSet = operations.setReading(connNo, readings);
				if(isReadingsSet)
					commonView.displayMessege("Reading has been set to "+readings);
				else
					commonView.displayMessege("Entered reading is less than past reading please check the readings ");
			}
		}
	}
	
	//View all pending payment list
	private void viewNonPayers() {
		Map<Long, List<Payment>> nonPayers =  operations.getNonPayers();
		if(nonPayers.size() == 0) {
			commonView.displayMessege("No pending payments");
			return;
		}
		else {
			for(Long connNo: nonPayers.keySet()) {
				String connType = commonOperations.getConnectionType(connNo);
				commonView.displayNonPayers(connType,connNo, nonPayers.get(connNo));
			}
		}
	}
	
	
	private void ViewAllConsumers() {
		Map<Long, Consumer> consumers = operations.getAllConsumers();
		Set<Long> consumerNos = consumers.keySet();
		for(Long cNo: consumerNos) {
			commonView.displayConsumerConnection(consumers.get(cNo));
		}
		
	}

	private void goToConsumerMenu() {
		boolean loop = true;
		int chances = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter Conumer number");
			int consumerNo = commonView.getInt();
			boolean isValid = commonOperations.isValidCustomerNo(consumerNo);
			if(!isValid) {
				if(chances >= 2) {
					commonView.displayMessege("Maximum chances given going back to previous menu");
				}
				commonView.displayMessege("Please enter valid consumer no");
				loop = true;
				chances++;
			}
			else {
				cmenuView.askOptions(consumerNo);
			}
		}
	}
}
