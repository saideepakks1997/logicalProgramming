package admin_view;

import java.util.List;

import admin_operations.AdminOperations;
import admin_operations.IAdminOperations;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import connection.Connection;
import connection.TypeOfConnection;
import eb.ElectricityBoard;
import eb.RequestObj;
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
			Integer opt = commonView.getInt();
			try {
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
			catch (NullPointerException e) {
				return;
			}
		}
	}
	//view requests for approval of new connection which 
	//is requested by consumer in online
	private void viewAndApproveNewConnection() {
		boolean loop = true;
		boolean isNewConnRequest = true;
		while(loop) {
			List<RequestObj> requests = operations.getRequests(isNewConnRequest);
			if(requests.size() == 0) {
				System.out.println("No requests in the queue");
				break;
			}
			RequestObj request = getRequestObjectFromAdmin(requests, isNewConnRequest);
			if(request != null) {
				System.out.println("Enter 1->For approval and move forward\n"
						+ "Any other number for non approval");
				Integer approvalOpt = commonView.getInt();
				try {
					String status = null;
					if(approvalOpt == 1) {
						long consumerNo = request.getConsumerNo();
						//updating status numbers
						operations.updateStatus(request);
						status = RequestStatus.values()[request.getStatusNo()].displayName();
						commonView.displayMessege("Moved to "+status+" status");
						status += "  request number( "+request.getRequestNo()+" )";
						if(request.getStatusNo() == RequestStatus.values().length-1) {
							Connection con = operations.createConnectionForExistingConsumer(consumerNo,request.getAddress(),request.getConnType());
							if(con != null) {
								status += " And your connection number is "+con.getServiceNo();
							}
						}
						operations.setNotification(request, status);
					}
					else {
						status = RequestStatus.values()[request.getStatusNo()].displayName();
						String msg = "New connection request has been not approved for request no "+request.getRequestNo()+"  failed at status "+status;
						
						commonView.displayMessege(msg);
						operations.setNotification(request, msg);
						//remove request from admin
						operations.removeRequest(request);
					}
				}
				catch(NullPointerException e) {
					return;
				}
			}
			try {
				if(!stillAcceptRequest()) 
					loop = false;
				}
			catch (NullPointerException e) {
				return;
			}
			}
			
	}
	
	private void viewAndChangetype() {
		boolean loop = true;
		boolean isNewConnRequest = false;
		while(loop) {
			List<RequestObj> requests = operations.getRequests(isNewConnRequest);
			if(requests.size() == 0) {
				System.out.println("No requests in the queue");
				break;
			}
			RequestObj request = getRequestObjectFromAdmin(requests, isNewConnRequest);

			if(request != null) {
				System.out.println("Enter 1->For approval\n"
						+ "Any other number for non approval");
				int approvalOpt = commonView.getInt();
				try {
					String status = null;
					if(approvalOpt == 1) {
						//update status number
						operations.updateStatus(request);
						status = RequestStatus.values()[request.getStatusNo()].displayName();
						commonView.displayMessege("Moved to "+status+" status");
						status += " for request number "+request.getRequestNo();
							
						if(request.getStatusNo() == RequestStatus.values().length-1) {
							TypeOfConnection conn = request.getConnType();
							long connNo = request.getServiceNo();
							operations.changeConnectionType(conn,connNo);
							commonView.displayMessege(status+"\n"
									+ "Changed type to "+conn);
						}
						operations.setNotification(request, status);
					}
					else {
						status = RequestStatus.values()[request.getStatusNo()].displayName();
						 String messege = "Change of connection has not approved  for request no "+request.getRequestNo()+" failed at status "+status;
						commonView.displayMessege(messege);
						operations.setNotification(request, status);
						operations.removeRequest(request);
					}
				}
				catch(NullPointerException e) {}
				
			}
			if(!stillAcceptRequest()) 
				loop = false;
		}
	}
	private RequestObj getRequestObjectFromAdmin(List<RequestObj> requests, boolean isNewConnRequest) {
		if(requests.size() == 0) {
			commonView.displayMessege("No requests available");
		return null;
		}
		int chances = 1;
		boolean loop = true;
		while(loop){
			try {
				loop = false;
				int opt = getRequestOption(requests, isNewConnRequest);
				if(opt == -1) {
					commonView.displayMessege("No options selected going back to previous menu");
				}
				else if(opt > requests.size() || opt < 0) {
					if(chances >= validate.getMaxChance()) {
						commonView.displayChancesMessege();
						return null;
					}
					loop = true;
					chances++;
					commonView.displayMessege("Enter correct option");
					
				}
				else {
					return requests.get(opt-1);
				}
				
			}
			catch (NullPointerException e) {
				return null;
			}
		}
		return null;
		
		
	}

	private boolean stillAcceptRequest() {
		commonView.displayMessege("Still you want to accept the request\n"
				+ "1->YES\n"
				+ "Enter any number for NO");
		Integer opt = commonView.getInt();
		try {
			if(opt != 1)
				return false;
			return true;
		}
		catch (NullPointerException e) {
			return false;
		}
		
	}

	private Integer getRequestOption(List<RequestObj> requests, boolean isNewConnRequest) {
		System.out.println("Select the request number for approval or not approving\n"
				+ "(-1) for not selecting anything");
		commonView.displayRequest(requests,isNewConnRequest);
		Integer opt = commonView.getInt();
		return opt;
	}



	//offline creation of connection
	private void createNewConnection() {
		boolean loop = true;
		while(loop) {
			commonView.displayMessege("1->New Consumer\n"
					+ "2->Existing Conusmer");
			Integer opt = commonView.getInt();
			try {
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
			catch (NullPointerException e) {
				return;
			}
			
		}
	}
	
	
	private void createConnectionForExistingConsumer() {
		int chances = 1;
		boolean loop = true;
		Integer customerNo = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter customer number");
			customerNo = commonView.getInt();
			try {
				boolean isValidCustomerId = commonOperations.isValidCustomerNo(customerNo);
				if(!isValidCustomerId) {
					if(chances >= validate.getMaxChance()) {
						commonView.displayChancesMessege();
						
						commonView.displayMessege("Connection Creation failed \n"
								+ "Going back to previous menu");
						return;
					}
					chances++;
					loop = true;
					commonView.displayMessege("Please enter valid consumer no");
					}
			}
			catch (NullPointerException e) {
				return;
			}
			
		}
		TypeOfConnection connType = commonView.selectTypeOfConnection();
		if(connType != null) {
			System.out.println("Enter the connection address");
			String connAddress = commonView.getString();
			Connection conn = operations.createConnectionForExistingConsumer(customerNo,connAddress,connType);
			commonView.displayMessege("Connection has been created successfully .Connection number is "+conn.getServiceNo());
		}
		else {
			commonView.displayMessege("Connection creation has been  Failed");
		}
		
	}

	private void createConnectionForNewConsumer() {
		int chances = 1;
		boolean loop = true;
		System.out.println("Start entering consumer details ");
		System.out.println("Enter name ");
		String name = commonView.getString();
		
		String emailId = commonView.getEmail();
		if(emailId == null) 
			return;
		
		Long phoNo = commonView.getPhoNo();
		if(phoNo == null)
			return;
		
		System.out.println("Enter your address");
		String address = commonView.getString();
		
		System.out.println("Enter connection address");
		String connAddress = commonView.getString();
		
		TypeOfConnection connType = commonView.selectTypeOfConnection();
		if(connType != null) {
			Connection conn = operations.createConnectionForNewConsumer(name,emailId,phoNo,address,connAddress, connType);
			if(conn != null) {
				commonView.displayMessege("Connection has been created successfully service number is "+conn.getServiceNo()+""
						+ " and consumer no is "+conn.getConsumer().getConsumerNO());
				}
			else {
				commonView.displayMessege("Connection creation failed");
			}
		}
		else {
			commonView.displayMessege("Connection creation failed");
		}
		
	}
	
	private void changeConnectionType() {
		long connNo = commonView.getConnectionNo();
		if(connNo != -1) {
			String connType= commonOperations.getConnectionType(connNo);
			commonView.displayMessege("Current connection type is for service no"
					+ " "+connNo+" is "+connType);
			TypeOfConnection selectedconnType = commonView.selectTypeOfConnection();
			if(selectedconnType != null) {
				boolean isChanged = operations.changeConnectionType(selectedconnType,connNo);
				if(!isChanged) {
					commonView.displayMessege("The connection type is already "+connType);
				}
				else {
					commonView.displayMessege("Connection type "+selectedconnType+" has been updated for service no "+connNo);
				}
			}
			else {
				commonView.displayMessege("Change of Connection failed");
			}
		}
		else {
			commonView.displayMessege("Change of connection type failed");
		}
		
	}
}
