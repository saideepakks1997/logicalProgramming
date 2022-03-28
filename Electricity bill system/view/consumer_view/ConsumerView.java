package consumer_view;

import java.util.List;

import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import connection.TypeOfConnection;
import consumer_operations.ConsumerOperations;
import consumer_operations.IConsumerOperations;
import eb.ElectricityBoard;
import validator_encrypter.Validator;

public class ConsumerView {
	Validator validate = new Validator();
	
	CommonView commonView = null;
	ConsumerLoginView loginView = null;
	IConsumerOperations operations = null;
	ICommonOperations commonOperations = null;
	
	boolean isConsumer = true;
	public ConsumerView(ElectricityBoard eb) {
		this.commonView = new CommonView(eb);
		this.loginView = new ConsumerLoginView(eb);
		
		this.commonOperations = new CommonOperations(eb);
		this.operations = new ConsumerOperations(eb);
	}
	
	public void askConsumerOptions() {
		boolean isAdmin = false;
		boolean loop = true;
		while(loop) {
			commonView.displayMessege("Select option \n"
					+ "1->Quick pay\n"
					+ "2->Register consumer\n"
					+ "3->login\n"
					+ "4->View all tarrifs\n"
					+ "9->Back to previous menu");
			Integer opt = commonView.getInt();
			try {
				switch (opt) {
				case 1: commonView.payBill(isAdmin);
					break;
				case 2: consumerRegistration();
					break;
				case 3: login();
					break;
				case 4: viewTarrifs();
					break;
				case 9: loop = false;
					commonView.displayMessege("Back to previous menu");
					break;	
				default: commonView.displayMessege("please enter correct option");
					break;
				}
			}
			catch(NullPointerException e) {
				return;
			}
		}
	}
	private void viewTarrifs() {
		List<TypeOfConnection> conns = commonOperations.getAllConnectionTypes();
		for(TypeOfConnection conn:conns) {
			System.out.println("Connection Type:- "+conn);
			commonView.displayMessege(conn.getObj());
		}
		
	}


	private void consumerRegistration() {
		commonView.displayMessege("Enter option before registration\n"
				+ "1->select if you already have connection\n"
				+ "2->Select if no connection available\n"
				+ "press any other key for previous menu");
		Integer opt = commonView.getInt();
		try {
			switch (opt) {
			case 1: registerConsumerForExistingConnection();
				break;
			case 2: registerConsumerForNewConnection();
				break;
			default: commonView.displayMessege("Back to previous menu");
				break;
			}
		}
		catch (NullPointerException e) {
			return;
		}
		
	}
	

	private void login() {
		
		boolean loop = true;
		String user_name = "";
		int  chances = 1;
		while(loop) {
			loop = false;
			System.out.println("Enter user name");
			user_name = commonView.getString();
			boolean isValidUserName = commonOperations.checkUserNameAvailable(user_name, isConsumer);
			if(!isValidUserName) {
				if(chances >= validate.getMaxChance()) {
					commonView.displayMessege("Maximum chances has been give please try after sometime");
					loop = false;
					return;
					}
				loop = true;
				commonView.displayMessege("User name does not exists try again");
				chances++;
			}
		}
		 chances = 1;
		loop = true;
		while(loop) {
			loop = false;
			System.out.println("Enter the password");
			String password = commonView.getString();
			boolean isValidLoginDetails = commonOperations.validatePassword(user_name, password, "consumer");
			if(!isValidLoginDetails) {
				if(chances >= validate.getMaxChance()) {
					commonView.displayMessege("Maximum chances has been give please try after sometime");
					loop = false;
					return;
					}
				loop = true;
				chances++;
			}
			else {
				loop = false;
				long consumerNo = commonOperations.getConsumerNoFromUserName(user_name);
				loginView.askLoggedInOptions(consumerNo);
			}
		}
		
	}

	private void registerConsumerForExistingConnection() {
		boolean loop = true;
		Long consumerNo = 0l;
		int chances = 1;
		while(loop) {
			try {
			loop = false;
			System.out.println("Enter consumer number");
			consumerNo = commonView.getLong();
			
				String user_name= operations.getUserNameIfAlreadyRegiestered(consumerNo);
				if(user_name != null) {
					commonView.displayMessege("The consumer is already registered user name is "+user_name);
					return;
				}
				boolean isValidConsumerNumber = commonOperations.isValidCustomerNo(consumerNo);
				if(!isValidConsumerNumber) {
					if(chances >= validate.getMaxChance()) {
						commonView.displayChancesMessege();
						commonView.displayMessege("Registration failed");
						return;
					}
					chances++;
					loop = true;
					commonView.displayMessege("Conumer number does not exists please enter valid consumer number");
				}
			}
			catch (NullPointerException e) {
				return;
			}
			}
			
		String user_name = registerUser(consumerNo);
		if(user_name != null) {
			commonView.displayMessege(user_name+" registered successfully");
			loginView.askLoggedInOptions(consumerNo);
		}
		else {
			commonView.displayMessege("Registered not done");
		}
	}

	private String registerUser(long consumerNo) {
		System.out.println("Enter login details to set");
		String user_name = getUserNameFromUser();
		if(user_name == null) {
			return null;
		}
		
		String password = getPasswordFromUser();
		if(password != null) {
			boolean isRegistered = operations.registerUser(consumerNo,user_name, password);
			if(isRegistered) {
				return user_name;
			}
		}
		return null;
	}
	
	private String getUserNameFromUser() {
		boolean loop = true;
		int chances = 1;
		String user_name = null;
		while(loop) {
			loop = false;
			System.out.println("Enter user name to set");
			user_name = commonView.getString();
			boolean isUserTaken = commonOperations.checkUserNameAvailable(user_name,isConsumer);
			if(isUserTaken) {
				
				if(chances >= validate.getMaxChance()) {
					commonView.displayChancesMessege();
					return null;
				}
				commonView.displayMessege(user_name+" already taken please try different name");
				loop =  true;
				chances++;
			}
		}
		return user_name;
	}
	
	private String getPasswordFromUser() {
		boolean loop = true;
		int chances = 1;
		String password = null;
		//Getting password
		while(loop) {
			System.out.println("Enter the password to set ");
			password = commonView.getString();
			boolean isValidPassword = validate.validatePassword(password);
			if(isValidPassword) {
				boolean isReenterValid =reEnterPassword(password);
				if(!isReenterValid)
					return null;
				else
					loop = false;
			}
			else {
				if(chances >= validate.getMaxChance()) {
					loop = false;
					commonView.displayChancesMessege();
					return null;
				}
					commonView.displayMessege("Please enter valid password");
					chances++;
			}
		}
		return password;
	}

	private boolean reEnterPassword(String password) {
		boolean loop = true;
		int chances = 1;
		while(loop){
			System.out.println("Re-enter the password to check");
			String reCheckPassword = commonView.getString();
			if(password.equals(reCheckPassword)) {
				return true;
			}
			else {
				if(chances >= validate.getMaxChance()) {
					commonView.displayChancesMessege();
					return false;
				}
				chances++;
				System.out.println("Re-entered password does not matches with password\n"
						+ "Again enter the password to recheck");
			}
		}
		return false;
	}


	private void registerConsumerForNewConnection() {
		System.out.println("Start entering details for registration");
		System.out.println("Enter name ");
		String name = commonView.getString();
		
		String email = commonView.getEmail();
		if(email == null)
			return;
		
		Long phoNo= commonView.getPhoNo();
		if(phoNo == null)
			return;
		
		System.out.println("Enter your address");
		String address = commonView.getString();
		
		long consumerNo = operations.createConsumer(name,email,phoNo,address);
		String user_name = registerUser(consumerNo);
		commonView.displayMessege("Your consumer number is "+consumerNo+" and user name is "+user_name);
		loginView.askLoggedInOptions(consumerNo);
	}
	
	
}
