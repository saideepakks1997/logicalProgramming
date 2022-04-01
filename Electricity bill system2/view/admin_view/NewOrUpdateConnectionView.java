package admin_view;

import java.util.List;

import admin_operations.AdminOperations;
import admin_operations.IAdminOperations;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import common_view.DisplayView;
import common_view.UserInputView;
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
	DisplayView display = new DisplayView();
	UserInputView input = new UserInputView();
	
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
			display.displayMessege("Enter the option\n"
					+ "1->Approval of new connections request\n"
					+ "2->Approval of connection type change\n"
					+ "3->Create new Connection\n"
					+ "4->Change connection type\n"
					+ "9->Back to previous menu");
			int opt = input.getInt();
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
						display.displayMessege("Going back to previos menu");
						break;
				case -1: loop = false;
						break;
				default: display.displayMessege("please enter correct option");
					break;
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
				int approvalOpt = input.getInt();
				if(approvalOpt != -1) {
					String status = null;
					if(approvalOpt == 1) {
						long consumerNo = request.getConsumerNo();
						//updating status numbers
						operations.updateStatus(request);
						status = RequestStatus.values()[request.getStatusNo()].displayName();
						display.displayMessege("Moved to "+status+" status");
						status += "  request number( "+request.getRequestNo()+" )";
						if(request.getStatusNo() == RequestStatus.values().length-1) {
							Connection con = operations.createConnection(consumerNo,request.getAddress(),request.getConnType());
							if(con != null) {
								status += " And your connection number is "+con.getServiceNo();
							}
						}
						operations.setNotification(request, status);
					}
					else {
						status = RequestStatus.values()[request.getStatusNo()].displayName();
						String msg = "New connection request has been not approved for request no "+request.getRequestNo()+"  failed at status "+status;
						
						display.displayMessege(msg);
						operations.setNotification(request, msg);
						//remove request from admin
						operations.removeRequest(request);
					}
				}
					
//				}
//				catch(NullPointerException e) {
//					return;
//				}
			}
			
				if(!stillAcceptRequest()) 
					loop = false;
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
				int approvalOpt = input.getInt();
				if(approvalOpt != -1) {
					String status = null;
					if(approvalOpt == 1) {
						//update status number
						operations.updateStatus(request);
						status = RequestStatus.values()[request.getStatusNo()].displayName();
						display.displayMessege("Moved to "+status+" status");
						status += " for request number "+request.getRequestNo();
							
						if(request.getStatusNo() == RequestStatus.values().length-1) {
							TypeOfConnection conn = request.getConnType();
							long connNo = request.getServiceNo();
							operations.changeConnectionType(conn,connNo);
							display.displayMessege(status+"\n"
									+ "Changed type to "+conn);
						}
						operations.setNotification(request, status);
					}
					else {
						status = RequestStatus.values()[request.getStatusNo()].displayName();
						 String messege = "Change of connection has not approved  for request no "+request.getRequestNo()+" failed at status "+status;
						 display.displayMessege(messege);
						operations.setNotification(request, status);
						operations.removeRequest(request);
					}
				}
					
//				}
//				catch(NullPointerException e) {}
				
			}
			if(!stillAcceptRequest()) 
				loop = false;
		}
	}
	private RequestObj getRequestObjectFromAdmin(List<RequestObj> requests, boolean isNewConnRequest) {
		if(requests.size() == 0) {
			display.displayMessege("No requests available");
			return null;
		}
		int chances = 1;
		boolean loop = true;
		while(loop){
				loop = false;
				int opt = getRequestOption(requests, isNewConnRequest);
				if(opt == -1) {
					display.displayMessege("No options selected going back to previous menu");
				}
				else if(opt > requests.size() || opt < 0) {
					if(chances >= validate.getMaxChance()) {
						display.displayChancesMessege();
						return null;
					}
					loop = true;
					chances++;
					display.displayMessege("Enter correct option");
					
				}
				else {
					return requests.get(opt-1);
				}
		}
		return null;
		
		
	}

	private boolean stillAcceptRequest() {
		display.displayMessege("Still you want to accept the request\n"
				+ "1->YES\n"
				+ "Enter any number for NO");
		int opt = input.getInt();
			if(opt != 1)
				return false;
			return true;
		
	}

	private Integer getRequestOption(List<RequestObj> requests, boolean isNewConnRequest) {
		System.out.println("Select the request number for approval or not approving\n"
				+ "(-1) for not selecting anything");
		display.displayRequest(requests,isNewConnRequest);
		Integer opt = input.getInt();
		return opt;
	}



	//offline creation of connection
	private void createNewConnection() {
		boolean loop = true;
		while(loop) {
			display.displayMessege("1->New Consumer\n"
					+ "2->Existing Conusmer");
			int opt = input.getInt();
			switch (opt) {
				case 1: loop = false;
					createConnectionForNewConsumer();
					break;
				case 2: loop = false;
					createConnectionForExistingConsumer();
					break;
				case -1: loop = false;
					break;
				default: loop = true;
				display.displayMessege("Please enter correct option");
					break;
				}
			
			
			
		}
	}
	
	
	private void createConnectionForExistingConsumer() {
		int chances = 1;
		boolean loop = true;
		int customerNo = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter customer number");
			customerNo = input.getInt();
			if(customerNo != -1) {
				boolean isValidCustomerId = commonOperations.isValidCustomerNo(customerNo);
				if(!isValidCustomerId) {
					if(chances >= validate.getMaxChance()) {
						display.displayChancesMessege();
						
						display.displayMessege("Connection Creation failed \n"
								+ "Going back to previous menu");
						return;
					}
					chances++;
					loop = true;
					display.displayMessege("Please enter valid consumer no");
					}
				}
			else {
				return;
			}
		}
		TypeOfConnection connType = commonView.selectTypeOfConnection();
		if(connType != null) {
			System.out.println("Enter the connection address");
			String connAddress = input.getString();
			Connection conn = operations.createConnection(customerNo,connAddress,connType);
			display.displayMessege("Connection has been created successfully .Connection number is "+conn.getServiceNo());
		}
		else {
			display.displayMessege("Connection creation has been  Failed");
		}
		
	}

	private void createConnectionForNewConsumer() {
			long consumerNo = commonView.getConsumerDetails();
			if(consumerNo == -1)
				return ;
			System.out.println("Enter connection address");
			String connAddress = input.getString();
			
			TypeOfConnection connType = commonView.selectTypeOfConnection();
			if(connType != null) {
				Connection conn =  operations.createConnection(consumerNo, connAddress, connType);
				if(conn != null) {
					display.displayMessege("Connection has been created successfully service number is "+conn.getServiceNo()+""
							+ " and consumer no is "+conn.getConsumer().getConsumerNO());
					}
				else {
					display.displayMessege("Connection creation failed");
				}
			}
			else {
				display.displayMessege("Connection creation failed");
			}
	}
	
	private void changeConnectionType() {
		long connNo = commonView.getConnectionNo();
		if(connNo != -1) {
			String connType= commonOperations.getConnectionType(connNo);
			display.displayMessege("Current connection type is for service no"
					+ " "+connNo+" is "+connType);
			TypeOfConnection selectedconnType = commonView.selectTypeOfConnection();
			if(selectedconnType != null) {
				boolean isChanged = operations.changeConnectionType(selectedconnType,connNo);
				if(!isChanged) {
					display.displayMessege("The connection type is already "+connType);
				}
				else {
					display.displayMessege("Connection type "+selectedconnType+" has been updated for service no "+connNo);
				}
			}
			else {
				display.displayMessege("Change of Connection failed");
			}
		}
		else {
			display.displayMessege("Change of connection type failed");
		}
		
	}
}
