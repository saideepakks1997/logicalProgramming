package admin_view;

import java.util.List;

import admin_operations.AdminOperations;
import admin_operations.IAdminOperations;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import connection.Connection;
import connection.TypeOfConnection;
import eb.ChangeOfConnectionRequest;
import eb.ElectricityBoard;
import eb.NewConnectionRequest;
import eb.RequestStatus;
import validator_encrypter.Validator;

public class NewOrUpdateConnectionView {
	CommonView commonView = null;
	Validator validate = new Validator();

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
			//get request option
			int opt = getRequestOption(requests);
			
			if(opt == -1) {
				commonView.displayMessege("No options selected going back to previous menu");
				loop = false;
			}
			else if(opt > requests.size() || opt < 0) {
				commonView.displayMessege("Enter correct option");
			}
			else{
				display = false;
				System.out.println("Enter 1->For approval and move forward\n"
						+ "Any other number for non approval");
				int approvalOpt = commonView.getInt();
				NewConnectionRequest req = requests.get(opt-1);
				long consumerNo = req.getConsumerNo();
				String status = null;
				if(approvalOpt == 1) {
					//updating status
					operations.updateNewConnectionStatus(req, opt-1);
					status = RequestStatus.values()[req.getStatusNo()].displayName();
					commonView.displayMessege("Moved to "+status+" status");
					status += "  request number( "+req.getRequestNo()+" )";
					//last status
					if(req.getStatusNo() != RequestStatus.values().length-1) {
						//first approval
						if(req.getStatusNo() == 1)
							operations.addNotification(consumerNo,status );
						else
							operations.updateNotification(consumerNo, opt-1,status,"newConnection");
					}
					else if(req.getStatusNo() == RequestStatus.values().length-1) {
						String connAddress = req.getAddress();
						TypeOfConnection connType = req.getConnType();
						Connection con = operations.createConnectionForExistingConsumer(consumerNo,connAddress,connType);
						if(con != null) {
							status += " And your connection number is "+con.getServiceNo();
						}
						operations.updateNotification(consumerNo,opt-1, status, "newConnection");
					}
				}
				else {
					String msg = "New connection request has been not approved for request no "+req.getRequestNo();
					commonView.displayMessege(msg);
					operations.addNotification(consumerNo,status);
					operations.removeRequest(opt-1, "newConnection");
				}
			}
		}
	}
	
	private int getRequestOption(List<NewConnectionRequest> requests) {
		System.out.println("Select the request number for approval or not approving\n"
				+ "(-1) for not selecting anything");
		int i=1;
		for(NewConnectionRequest request: requests) {
			System.out.println("-----------------------");
			System.out.println("Sno :- "+(i++)+"\n"+request);
			System.out.println("-----------------------");
		}
		int opt = commonView.getInt();
		return opt;
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
			int opt = getConnectionRequestOpt(requests);
			
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
				String status = null;
				if(approvalOpt == 1) {
					operations.updateConnectionChangeStatus(req, opt-1);
					status = RequestStatus.values()[req.getStatusNo()].displayName();
					commonView.displayMessege("Moved to "+status+" status");
					//last approval
					status += " for request number "+req.getRequestNo();
					if(req.getStatusNo() != RequestStatus.values().length-1) {
						//first approval
						if(req.getStatusNo() == 1)
							operations.addNotification(consumerNo, status);
						else
							operations.updateNotification(consumerNo, opt-1,status,"newConnection");
					}
					else if(req.getStatusNo() == RequestStatus.values().length-1) {
						TypeOfConnection conn = req.getConnType();
						long connNo = req.getServiceNo();
						operations.changeConnectionType(conn,connNo);
						
						commonView.displayMessege(status+"\n"
								+ "Changed type to "+conn);
						operations.updateNotification(consumerNo,opt-1, status, "newConnection");
					}
				}
				else {
					 String messege = "Change of connection has not approved  for request no "+req.getRequestNo();
					commonView.displayMessege(messege);
					operations.addNotification(consumerNo,messege);
					operations.removeRequest(opt-1, "changeType");
				}
			}
			
		}
	}
	private int getConnectionRequestOpt(List<ChangeOfConnectionRequest> requests) {
		System.out.println("Select the request number for approval \n"
				+ "(-1) for not selecting anything");
		int i=1;
		for(ChangeOfConnectionRequest request: requests) {
			System.out.println("-----------------------");
			System.out.println("Sno :- "+(i++)+"."+request);
			System.out.println("-----------------------");
		}
		int opt = commonView.getInt();
		return opt;
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
		Connection conn = operations.createConnectionForExistingConsumer(customerNo,connAddress,connType);
		commonView.displayMessege("Connection has been created successfully .Connection number is "+conn.getServiceNo());
	}

	private void createConnectionForNewConsumer() {
		int chances = 1;
		boolean loop = true;
		System.out.println("Start entering consumer details ");
		System.out.println("Enter name ");
		String name = commonView.getString();
		
		String emailId = null;
		while(loop) {
			loop = false;
			System.out.println("Enter email id");
			emailId = commonView.getString();
			boolean isValidemail = validate.validateEmail(emailId);
			if(!isValidemail) {
				loop = true;
				if(chances >= validate.getMaxChance()) {
					commonView.displayChancesMessege();
					commonView.displayMessege("Connection creation failed \n "
							+ "Going back to previous menu");
					return;
				}
				chances++;
				commonView.displayMessege("Please enter valid email id");
			}
		}
		
		
		System.out.println("Enter phone no");
		long phoNo= commonView.getLong();
		
		System.out.println("Enter your address");
		String address = commonView.getString();
		
		System.out.println("Enter connection address");
		String connAddress = commonView.getString();
		
		TypeOfConnection connType = commonView.selectTypeOfConnection();
		Connection conn = operations.createConnectionForNewConsumer(name,emailId,phoNo,address,connAddress, connType);
		if(conn != null) {
			commonView.displayMessege("Connection has been created successfully service number is "+conn.getServiceNo()+""
					+ " and consumer no is "+conn.getConsumer().getConsumerNO());
			
		}
		else {
			commonView.displayMessege("Connection creation failed");
		}
	}
	
	private void changeConnectionType() {
		boolean loop = true;
		long connNo = commonView.getConnectionNo();
		String connType= commonOperations.getConnectionType(connNo);
		commonView.displayMessege("Current connection type is for service no"
				+ " "+connNo+" is "+connType);
		TypeOfConnection selectedconnType = commonView.selectTypeOfConnection();
		boolean isChanged = operations.changeConnectionType(selectedconnType,connNo);
		if(isChanged) {
			commonView.displayMessege("The connection type is already "+connType);
		}
		else {
			commonView.displayMessege("Connection type "+connType+" has been updated for service no "+connNo);
		}
		}	
}
