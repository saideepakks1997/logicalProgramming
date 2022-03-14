package admin_view;

import java.util.List;

import admin_operations.AdminOperations;
import admin_operations.IAdminOperations;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import connection.TypeOfConnection;
import eb.ElectricityBoard;

public class NewOrUpdateConnectionView {
CommonView commonView = null;
	
	ICommonOperations commonOperations = null;
	IAdminOperations operations = null;
	
	ElectricityBoard eb = null;
	
	public NewOrUpdateConnectionView(ElectricityBoard eb) {
		this.commonOperations = new CommonOperations(eb);
		this.operations = new AdminOperations(eb);
		this.commonView = new CommonView(eb);
		this.eb = eb;
	}

	public void askOptions() {
		boolean loop = true;
		while(loop) {
			commonView.displayMessege("Enter the option\n"
					+ "1->Approval of new connections request\n"
					+ "2->Approval of connection type change\n"
					+ "3->Create new Connection\n"
					+ "4->Change connection type\n"
					+ "9->Back to previous menu");
			int opt = commonView.getOption();
			switch (opt) {
				case 1: 
					break;
				case 2:
					break;
				case 3: createNewConnection();
					break;
				case 4: changeConnectionType();
					break;
				case 9: loop = false;
					commonView.displayMessege("Back to previous menu");
					break;
				default: commonView.displayMessege("please enter correct option");
					break;
			}
		}
		
		
	}

	private void changeConnectionType() {
		boolean loop = true;
		long connNo = getConnectionNo();
		String connType= commonOperations.getConnectionType(connNo);
		commonView.displayMessege("Current connection type is for service no"
				+ " "+connNo+" is "+connType);
		TypeOfConnection selectedconnType = commonView.selectTypeOfConnection();
		operations.changeConnectionType(selectedconnType,connNo);
		}

	private void createNewConnection() {
		boolean loop = true;
		while(loop) {
			commonView.displayMessege("1->New Consumer\n"
					+ "2->Existing Conusmer");
			int opt = commonView.getOption();
			switch (opt) {
			case 1: loop = false;
				createConnectionForNewConsumer();
				break;
			case 2: loop = false;
				createConnectionForExistingConsumer();
				break;	
			default: loop = true;
				commonView.displayMessege("Please enter correct option");
				break;
			}
		}
		
		
	}
	
	
	private void createConnectionForExistingConsumer() {
		// TODO Auto-generated method stub
		
	}


	private void createConnectionForNewConsumer() {
		String user_name = commonView.registerUser();
		TypeOfConnection connType = commonView.selectTypeOfConnection();
		String status = operations.createConnection(user_name, connType);
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
