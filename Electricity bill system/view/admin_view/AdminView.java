package admin_view;

import admin_operations.AdminOperations;
import admin_operations.IAdminOperations;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import eb.ElectricityBoard;

public class AdminView {
	CommonView commonView = new CommonView();
	
	ICommonOperations commonOperations = null;
	IAdminOperations operations = null;
	public AdminView(ElectricityBoard eb) {
		this.commonOperations = new CommonOperations(eb);
		this.operations = new AdminOperations(eb);
	}

	public void adminOptions() {
		commonView.displayMessege("Enter option\n"
				+ "1->Pay bill\n"
				+ "2->View Bill\n"
				+ "2->Check for new request of connection\n"
				+ "3->View non payers"
				+ "4->print bills\n"
				+ "5->change of connection\n"
				+ "");
		int opt = commonView.getOption();
		switch(opt) {
			case 1: payBill();
	 			break;
			case 2:viewConnectionStatus();
				break;
			case 3: setUnitsConsumed();
				break;
			case 4:
				break;
			case 5:
				break;
		}
	}

	private void setUnitsConsumed() {
		long connNo = commonView.getConnectionNo();
		long readings = commonView.getLong();
		String status = operations.setReading(connNo, readings);
		
	}

	private void viewConnectionStatus() {
		long connNo = commonView.getConnectionNo();
		String status = commonOperations.getConnectionStatus(connNo);
	}

	private void payBill() {
		System.out.println("Enter connection number");
		long connectionNo = commonView.getLong();
		
	}
	
}
