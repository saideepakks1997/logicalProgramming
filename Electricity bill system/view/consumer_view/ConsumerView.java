package consumer_view;

import java.util.List;

import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import consumer_operations.ConsumerOperations;
import consumer_operations.IConsumerOperations;
import eb.ElectricityBoard;

public class ConsumerView {
	
	CommonView commonView = null;
	ConsumerLoginView loginView = null;
	IConsumerOperations operations = null;
	ICommonOperations commonOperations = null;
	
	public ConsumerView(ElectricityBoard eb) {
		this.commonView = new CommonView(eb);
		this.loginView = new ConsumerLoginView(eb);
		
		this.commonOperations = new CommonOperations(eb);
		this.operations = new ConsumerOperations(eb);
	}
	
	public void askConsumerOptions() {
		boolean loop = true;
		while(loop) {
			commonView.displayMessege("Select option \n"
					+ "1->Quick pay\n"
					+ "2->Register consumer\n"
					+ "3->login\n"
					+ "9->Back to previous menu");
			int opt = commonView.getInt();
			switch (opt) {
			case 1: commonView.payBill();
				break;
			case 2: consumerRegistration();
				break;
			case 3: login();
				break;
			case 9: loop = false;
				commonView.displayMessege("Back to previous menu");
				break;	
			default: commonView.displayMessege("please enter correct option");
				break;
			}
		}
		
		
	}

	private void login() {
		boolean loop = true;
		String user_name = "";
		int  chances = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter user name");
			user_name = commonView.getString();
			boolean isValidUserName = commonOperations.checkIfUserNameIsCorrect(user_name);
			if(!isValidUserName) {
				if(chances >= 2) {
					commonView.displayMessege("Maximum chances has been give please try after sometime");
					loop = false;
					return;
					}
				loop = true;
				commonView.displayMessege("User name does not exists try again");
				chances++;
			}
		}
		 chances = 0;
		loop = true;
		while(loop) {
			loop = false;
			System.out.println("Enter the password");
			String password = commonView.getString();
			boolean isValidLoginDetails = operations.checkConsumerCredentials(user_name, password);
			if(!isValidLoginDetails) {
				if(chances >= 2) {
					commonView.displayMessege("Maximum chances has been give please try after sometime");
					loop = false;
					}
				loop = true;
				chances++;
			}
			else {
				loop = false;
				loginView.askLoggedInOptions(user_name);
			}
		}
		
	}

	private void consumerRegistration() {
		commonView.displayMessege("Enter option before registration\n"
				+ "1->Already have connection\n"
				+ "2->Apply for new connection\n"
				+ "press any other key for previous menu");
		int opt = commonView.getInt();
		switch (opt) {
		case 1: registrationForExistingConnection();
			break;
		case 2:
			break;
		default: commonView.displayMessege("Back to previous menu");
			break;
		}
	}

	private void registrationForExistingConnection() {
		boolean loop = true;
		int consumerNo = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter consumer number");
			consumerNo = commonView.getInt();
			boolean isValidConumerNumber = commonOperations.isValidCustomerNo(consumerNo);
			if(!isValidConumerNumber) {
				loop = true;
				commonView.displayMessege("Conumer number does not exists please enter valid consumer number");
			}
		}
		String user_name = registerUser(consumerNo);
	}

	private String registerUser(int consumerNo) {
		boolean loop = true;
		String user_name = null; 
		while(loop) {
			loop = false;
			System.out.println("Enter user name ");
			user_name = commonView.getString();
			boolean isUserTaken = commonOperations.checkIfUserNameIsCorrect(user_name);
			if(isUserTaken) {
				commonView.displayMessege(user_name+" already taken please try different name");
				loop =  true;
			}
		}
		loop = true; 
		String password = null ;
		while(loop) {
			System.out.println("Enter the password to set ");
			password = commonView.getString();
			List<String> passwordErrors = commonOperations.checkIfPasswordIsValid(password);
			if(passwordErrors.size() == 0) {
				System.out.println("Re-enter the password ");
				String reCheckPassword = commonView.getString();
				if(password.equals(reCheckPassword)) {
					loop = false;
				}
				else {
					System.out.println("Re-entered password does not matches with password");
					System.out.println("Start entering password from starting");
				}
			}
			else
				commonView.displayPasswordError(passwordErrors);
			}
		String result = operations.registerUser(consumerNo,user_name, password);
		commonView.displayMessege(result);
		return user_name;
	}
}
