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
import common_view.DisplayView;
import common_view.UserInputView;
import consumer.Consumer;
import eb.ElectricityBoard;

public class AdminView {
	DisplayView display = new DisplayView();
	UserInputView input = new UserInputView();
	
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
			
			display.displayMessege("Enter option\n"
					+ "1->Set units consumed\n"
					+ "2->View non payers\n"
					+ "3->pay bill\n"
					+ "4->New connection or change type of connection\n"
					+ "5->View all consumers\n"
					+ "6->Go to consumer menu\n"
					+ "9->log out");
			Integer opt = input.getInt();
			try {
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
				display.displayMessege("logging out");
					break;
				default: display.displayMessege("please enter correct option");
					break;
			}
			}
			catch (NullPointerException e) {
				return;
			}
		}
	}
	
	//Set units consumed for the consumer for example first month
	//  month reading 100 and next month may be 175
	private void setUnitsConsumed() {
		boolean loop = true;
		try {
			while(loop) {
				loop = false;
				long connNo = commonView.getConnectionNo();
				if(connNo != -1) {
					System.out.println("Enter the reading to set");
					Long readings = input.getLong();
					
					boolean isReadingsSet = operations.setReading(connNo, readings);
					if(isReadingsSet)
						display.displayMessege("Reading has been set to "+readings);
					else
						display.displayMessege("Entered reading is less than past reading please check the readings ");
				}
			}
		}
		catch (NullPointerException e) {
			return;
		}
		
	}
	
	//View all pending payment list
	private void viewNonPayers() {
		Map<Long, List<Payment>> nonPayers =  operations.getNonPayers();
		if(nonPayers.size() == 0) {
			display.displayMessege("No pending payments");
			return;
		}
		else {
			for(Long connNo: nonPayers.keySet()) {
				String connType = commonOperations.getConnectionType(connNo);
				display.displayNonPayers(connType,connNo, nonPayers.get(connNo));
			}
		}
	}
	
	
	private void ViewAllConsumers() {
		Map<Long, Consumer> consumers = operations.getAllConsumers();
		Set<Long> consumerNos = consumers.keySet();
		for(Long cNo: consumerNos) {
			display.displayConsumerConnection(consumers.get(cNo));
			}
		
	}

	private void goToConsumerMenu() {
		boolean loop = true;
		int chances = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter Conumer number");
			Integer consumerNo = input.getInt();
			try {
				boolean isValid = commonOperations.isValidCustomerNo(consumerNo);
				if(!isValid) {
					if(chances >= 2) {
						display.displayChancesMessege();
						display.displayMessege("going back to previous menu");
						return;
					}
					display.displayMessege("Please enter valid consumer no");
					loop = true;
					chances++;
				}
				else {
					cmenuView.askOptions(consumerNo);
				}
			}
			catch (NullPointerException e) {
				return;
			}
		}
	}
}
