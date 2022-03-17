package admin_view;

import java.util.List;

import admin_operations.AdminOperations;
import admin_operations.IAdminOperations;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import connection.TypeOfConnection;
import eb.ChangeOfConnectionRequest;
import eb.ElectricityBoard;
import eb.NewConnectionRequest;

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
			int opt = commonView.getInt();
			switch (opt) {
				case 1: viewAndApproveNewConnection();
					break;
				case 2: viewAndChangetype();
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
	//view requests for approval of new connection which 
	//is requested by consumer in online
	private void viewAndApproveNewConnection() {
		boolean loop = true;
		boolean display = true;
		while(loop) {
			List<NewConnectionRequest> requests = operations.getNewConnectionRequests();
			if(requests.size() == 0) {
				//This will display on first iteration only
				if(display)
					commonView.displayMessege("No requests available");
				return;
			}
			System.out.println("Select the request number for approval or not approving\n"
					+ "(-1) for not selecting anything");
			int i=1;
			for(NewConnectionRequest request: requests) {
				System.out.println("-----------------------");
				System.out.println("Sno :- "+(i++)+"\n"+request);
				System.out.println("-----------------------");
			}
			int opt = commonView.getInt();
			if(opt == -1) {
				commonView.displayMessege("No options selected going back to previous menu");
				loop = false;
			}
			else if(opt > requests.size() || opt < 0) {
				commonView.displayMessege("Enter correct option");
			}
			else {
				display = false;
				System.out.println("Enter 1->For approval\n"
						+ "Any other number for non approval");
				int approvalOpt = commonView.getInt();
				NewConnectionRequest req = requests.get(opt-1);
				long consumerNo = req.getConsumerNo(); 
				if(approvalOpt == 1) {
					String connAddress = req.getAddress();
					TypeOfConnection connType = req.getConnType();
					String status = operations.createConnectionForExistingConsumer(consumerNo,connAddress,connType);
					commonView.displayMessege(status);
					operations.addNotification(consumerNo, opt-1,status,"newConnection");
				}
				else {
					String status = "New connection request has been not approved";
					commonView.displayMessege(status);
					operations.addNotification(consumerNo, opt-1,status,"newConnection");
				}
			}
		}
	}
	//view requests for change type connection which 
	//is requested by consumer in online
	private void viewAndChangetype() {
		boolean loop = true;
		boolean display = true;
		while(loop) {
			List<ChangeOfConnectionRequest> requests = operations.getConnectionChangeRequests();
			if(requests.size() == 0) {
				if(display)
					commonView.displayMessege("No requests available");
				return;
			}
			System.out.println("Select the request number for approval \n"
					+ "(-1) for not selecting anything");
			int i=1;
			for(ChangeOfConnectionRequest request: requests) {
				System.out.println("-----------------------");
				System.out.println("Sno :- "+(i++)+"\n"+request);
				System.out.println("-----------------------");
			}
			int opt = commonView.getInt();
			if(opt == -1) {
				commonView.displayMessege("No options selected");
				loop = false;
			}
			else if(opt > requests.size() || opt < 0) {
				commonView.displayMessege("Enter correct option");
			}
			else {
				display = false;
				System.out.println("Enter 1->For approval\n"
						+ "Any other number for non approval");
				int approvalOpt = commonView.getInt();
				ChangeOfConnectionRequest req = requests.get(opt-1);
				long consumerNo = req.getConsumerNo(); 
//						this.eb.getConsumerUserName().get(req.getUser_name()).getConsumerNO();
				if(approvalOpt == 1) {
					String status = operations.changeConnectionType(req.getConnType(), req.getServiceNo());
					commonView.displayMessege(status);
					status = operations.addNotification(consumerNo, opt-1, status, "changetype");
					commonView.displayMessege(status);
				}
				else {
					String status = "Change of connection request has been not approved";
					commonView.displayMessege(status);
					operations.addNotification(consumerNo, opt-1,status,"changetype");
				}
			}
		}
	}
	//offline creation of connection
	private void createNewConnection() {
		boolean loop = true;
		while(loop) {
			commonView.displayMessege("1->New Consumer\n"
					+ "2->Existing Conusmer");
			int opt = commonView.getInt();
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
		boolean loop = true;
		int customerNo = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter customer number");
			customerNo = commonView.getInt();
			boolean isValidCustomerId = commonOperations.isValidCustomerNo(customerNo);
			if(!isValidCustomerId) {
				loop = true;
				commonView.displayMessege("Please enter valid consumer no");
				}
		}
		TypeOfConnection connType = commonView.selectTypeOfConnection();
		System.out.println("Enter the connection address");
		String connAddress = commonView.getString();
		String status = operations.createConnectionForExistingConsumer(customerNo,connAddress,connType);
		commonView.displayMessege(status);
	}

	private void createConnectionForNewConsumer() {
		System.out.println("Start entering consumer details ");
		System.out.println("Enter name ");
		String name = commonView.getString();
		
		System.out.println("Enter email id");
		String emailId = commonView.getString();
		
		System.out.println("Enter phone no");
		long phoNo= commonView.getLong();
		
		System.out.println("Enter your address");
		String address = commonView.getString();
		
		System.out.println("Enter connection address");
		String connAddress = commonView.getString();
		
		TypeOfConnection connType = commonView.selectTypeOfConnection();
		String status = operations.createConnectionForNewConsumer(name,emailId,phoNo,address,connAddress, connType);
		commonView.displayMessege(status);
		}
	
	private void changeConnectionType() {
		boolean loop = true;
		long connNo = commonView.getConnectionNo();
		String connType= commonOperations.getConnectionType(connNo);
		commonView.displayMessege("Current connection type is for service no"
				+ " "+connNo+" is "+connType);
		TypeOfConnection selectedconnType = commonView.selectTypeOfConnection();
		String result = operations.changeConnectionType(selectedconnType,connNo);
		commonView.displayMessege(result);
		}	
}
