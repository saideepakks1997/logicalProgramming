package consumer_view;

import java.util.List;

import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import common_view.DisplayView;
import common_view.UserInputView;
import connection.TypeOfConnection;
import consumer.Consumer;
import consumer_operations.ConsumerOperations;
import consumer_operations.IConsumerOperations;
import eb.ElectricityBoard;
import connection.Tarrifs;
import validator_encrypter.Validator;

public class ConsumerView {
	Validator validate = new Validator();
	
	DisplayView display = new DisplayView();
	UserInputView input = new UserInputView();
	
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
			display.displayMessege("Select option \n"
					+ "1->Quick pay\n"
					+ "2->Register consumer\n"
					+ "3->login\n"
					+ "4->View all tarrifs\n"
					+ "9->Back to previous menu");
			int opt = input.getInt();
			
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
					display.displayMessege("Back to previous menu");
					break;
				case -1:loop = false;
					break;
				default: display.displayMessege("please enter correct option");
					break;
				}
		}
	}

	
	private void viewTarrifs() {
		display.displayDomesticTariffs();
		//Lt Commercial
		display.displaySplitChargesConn(
				"Lt Commercial",
				Tarrifs.commercialMinUnits, 
				Tarrifs.commercialChargesBelowMin, 
				Tarrifs.commercialChargesAboveMin
				);
		//Public workshop
		display.displaySplitChargesConn(
				"Public workshop",
				Tarrifs.workshopMinUnits, 
				Tarrifs.workshopChargesBelowMin, 
				Tarrifs.workshopChargesAboveMin
				);
		//Cottage industries
		display.displaySplitChargesConn(
				"Cottage industries",
				Tarrifs.cottageMinUnits, 
				Tarrifs.cottageChargesBelowMin, 
				Tarrifs.cottageChargesAboveMin
				);
		//Power looms
		display.displaySplitChargesConn(
				"Power looms",
				Tarrifs.loomsMinUnits, 
				Tarrifs.loomsChargesBelowMin, 
				Tarrifs.loomsChargesAboveMin
				);
		//public village lights 
		display.displaySameCharges("public village lights",Tarrifs.industrialMetroCharges);
		
		//Temporary Supply
		display.displaySameCharges("Temporary Supply",Tarrifs.tempSupplyCharges);
		
		//Public Light Town
		display.displaySameCharges("Public Light Town",Tarrifs.lightTowncharges);
		
		//Govn Aided Places
		display.displaySameCharges("Govn Aided Places",Tarrifs.govnPlacesCharges);
		
		//Private Hostpital Institution
		display.displaySameCharges("Private Hostpital Institution",Tarrifs.privateHospitalCharges);
	}

	private void consumerRegistration() {
		display.displayMessege("Enter option before registration\n"
				+ "1->select if you already have connection\n"
				+ "2->Select if no connection available\n"
				+ "press any other key for previous menu");
		int opt = input.getInt();
		
			switch (opt) {
			case 1: registerConsumerForExistingConnection();
				break;
			case 2: registerConsumerForNewConnection();
				break;
			default: display.displayMessege("Back to previous menu");
				break;
			}
		}
	

	private void login() {
		
		boolean loop = true;
		String user_name = "";
		int  chances = 1;
		while(loop) {
			loop = false;
			System.out.println("Enter user name");
			user_name = input.getString();
			boolean isValidUserName = commonOperations.checkUserNameAvailable(user_name, isConsumer);
			if(!isValidUserName) {
				if(chances >= validate.getMaxChance()) {
					display.displayMessege("Maximum chances has been give please try after sometime");
					loop = false;
					return;
					}
				loop = true;
				display.displayMessege("User name does not exists try again");
				chances++;
			}
		}
		 chances = 1;
		loop = true;
		while(loop) {
			loop = false;
			System.out.println("Enter the password");
			String password = input.getString();
			boolean isValidLoginDetails = commonOperations.validatePassword(user_name, password, "consumer");
			if(!isValidLoginDetails) {
				if(chances >= validate.getMaxChance()) {
					display.displayMessege("Maximum chances has been give please try after sometime");
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
		long consumerNo = 0l;
		int chances = 1;
		while(loop) {
			loop = false;
			System.out.println("Enter consumer number");
			consumerNo = input.getLong();
			if(consumerNo != -1) {
				String user_name= operations.checkUserIfAlreadyRegiestered(consumerNo);
				if(user_name != null) {
					display.displayMessege("The consumer is already registered user name is "+user_name);
					return;
				}
				boolean isValidConsumerNumber = commonOperations.isValidCustomerNo(consumerNo);
				if(!isValidConsumerNumber) {
					if(chances >= validate.getMaxChance()) {
						display.displayChancesMessege();
						display.displayMessege("Registration failed");
						return;
					}
					chances++;
					loop = true;
					display.displayMessege("Conumer number does not exists please enter valid consumer number");
				}
			}
			}
			
		String user_name = registerUser(consumerNo);
		if(user_name != null) {
			display.displayMessege(user_name+" registered successfully");
			loginView.askLoggedInOptions(consumerNo);
		}
		else {
			display.displayMessege("Registered not done");
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
			user_name = input.getString();
			boolean isUserTaken = commonOperations.checkUserNameAvailable(user_name,isConsumer);
			if(isUserTaken) {
				
				if(chances >= validate.getMaxChance()) {
					display.displayChancesMessege();
					return null;
				}
				display.displayMessege(user_name+" already taken please try different name");
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
			password = input.getString();
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
					display.displayChancesMessege();
					return null;
				}
					display.displayMessege("Please enter valid password");
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
			String reCheckPassword = input.getString();
			if(password.equals(reCheckPassword)) {
				return true;
			}
			else {
				if(chances >= validate.getMaxChance()) {
					display.displayChancesMessege();
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
		
			System.out.println("Start entering details");
			System.out.println("Enter name ");
			String name = input.getString();
			
			String email = commonView.getEmail();
			if(email == null)
				return;
			Long phoNo= commonView.getPhoNo();
			if(phoNo == null)
				return;
			
			System.out.println("Enter your address");
			String address = input.getString();
			
			System.out.println("Enter login details to set");
			String user_name = getUserNameFromUser();
			if(user_name == null) {
				return;
			}
			
			String password = getPasswordFromUser();
			if(password != null) {
				Consumer consumer = operations.registerUser(name,email,phoNo,address,user_name, password);
				if(consumer != null) {
					display.displayMessege("Consumer registered successfully and your consumer number is "+consumer.getConsumerNO());
				}
				else
					display.displayMessege("Creation of consumer Failed");
			}	
	}
	
}
