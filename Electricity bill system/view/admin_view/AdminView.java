package admin_view;

import java.util.List;
import java.util.Map;

import admin_operations.AdminOperations;
import admin_operations.IAdminOperations;
import bill.Payment;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import eb.ElectricityBoard;

public class AdminView {
	CommonView commonView = null;
	NewOrUpdateConnectionView connectionView = null;
	
	ICommonOperations commonOperations = null;
	IAdminOperations operations = null;
	
	public AdminView(ElectricityBoard eb) {
		this.commonOperations = new CommonOperations(eb);
		this.operations = new AdminOperations(eb);
		this.connectionView = new NewOrUpdateConnectionView(eb);
		this.commonView = new CommonView(eb);
	}

	public void adminOptions() {
		boolean loop = true;
		while(loop) {
			commonView.displayMessege("Enter option\n"
					+ "1->Set units consumed\n"
					+ "2->View non payers\n"
					+ "3->pay bill\n"
					+ "4->New connection or change type of connection\n"
					+ "9->log out");
			int opt = commonView.getInt();
			switch(opt) {
				case 1: setUnitsConsumed();
		 			break;
				case 2: viewNonPayers();
					break;
				case 3: payBill();
					break;
				case 4: connectionView.askOptions();
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
		long connNo = getConnectionNo();
		System.out.println("Enter the reading to set");
		long readings = commonView.getLong();
		
		String status = operations.setReading(connNo, readings);
		commonView.displayMessege(status);
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
	//pay amount
	private void payBill() {
		boolean loop = true;
		long connNo = getConnectionNo();
		commonView.viewAndPayAllPendingPayments(connNo);
	}
	
	private long getConnectionNo() {
		boolean loop = true;
		long connNo = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter connection number");
			connNo = commonView.getLong();
			boolean isValid = commonOperations.isServiceNoValid(connNo);
			if(!isValid) {
				commonView.displayMessege("Enter valid service number");
				loop = true;
			}
		}
		return connNo;
	}
}
