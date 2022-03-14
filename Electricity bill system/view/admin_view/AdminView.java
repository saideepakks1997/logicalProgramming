package admin_view;

import java.util.List;
import java.util.Map;

import admin_operations.AdminOperations;
import admin_operations.IAdminOperations;
import bill.Payment;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import connection.TypeOfConnection;
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
					+ "4->change connection type"
					+ "4->Accept connection requests\n"
					+ "5->update or change type of connection\n"
					+ "9->log out");
			int opt = commonView.getOption();
			switch(opt) {
				case 1: setUnitsConsumed();
		 			break;
				case 2: viewNonPayers();
					break;
				case 3: payBill();
					break;
				case 4: changeConnectionType();
					break;
				case 5: connectionView.askOptions();
					break;
				case 9: loop = false;
				commonView.displayMessege("logging out");
					break;
			}
		}
		
	}
	
	
	

	private void setUnitsConsumed() {
		long connNo = getConnectionNo();
		System.out.println("Enter the reading to set");
		long readings = commonView.getLong();
		
		String status = operations.setReading(connNo, readings);
		commonView.displayMessege(status);
	}
	
	private void viewNonPayers() {
		Map<Long, List<Payment>> nonPayers =  operations.getNonPayers();
		for(Long connNo: nonPayers.keySet()) {
			String connType = commonOperations.getConnectionType(connNo);
			commonView.displayNonPayers(connType,connNo, nonPayers.get(connNo));
		}
	}

	private void payBill() {
		boolean loop = true;
		long connNo = getConnectionNo();
		while(loop) {
			String display = commonOperations.getAllPedingPayments(connNo);
			if(display != null) {
				System.out.println("Enter option to accept the payment");
				commonView.displayMessege(display);
				int opt = commonView.getOption();
				String status = commonOperations.acceptPayment(opt, connNo);
				commonView.displayMessege(status);
				System.out.println("Still you want pay bills\n"
						+ "1-> Yes\n"
						+ "Press any no for No");
				int no = commonView.getOption();
				if(no != 1) {
					loop = false;
					commonView.displayMessege("Going back to previous menu");
				}
					
			}
			else {
				commonView.displayMessege("No pending payments");
				loop = false;
			}
			
		}
	}
	
	private void changeConnectionType() {
		long connNo = getConnectionNo();
		String status = commonOperations.getConnectionType(connNo);
		commonView.displayMessege(status);
		
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
