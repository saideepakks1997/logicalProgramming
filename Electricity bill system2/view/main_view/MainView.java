package main_view;


import admin_view.AdminView;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import common_view.DisplayView;
import common_view.UserInputView;
import consumer_view.ConsumerView;
import eb.ElectricityBoard;
import validator_encrypter.Validator;

public class MainView {
	CommonView commonView = null;
	AdminView adminView = null;
	ConsumerView consumerView = null;
	
	DisplayView display = new DisplayView();
	UserInputView input = new UserInputView();
	
	Validator validate = new Validator(); 
	
	ICommonOperations operations = null;
	ElectricityBoard eb = null;

	public MainView(ElectricityBoard eb) {
		this.eb = eb;
		this.adminView = new AdminView(eb);
		this.consumerView = new ConsumerView(eb);
		this.commonView = new CommonView(eb);
		this.operations = new CommonOperations(eb);
	}
	public void askOptions(){
		
		boolean loop = true;
		boolean isValid = false;
		while(loop) {
			display.displayMessege("Enter option \n"
					+ "1->Admin\n"
					+ "2->Consumer\n"
					+ "9->Log out");
			int opt = input.getInt();
			switch (opt) {
				case 1: isValid = login();
					if(isValid)
						adminView.adminOptions();
					break;
				case 2: this.consumerView.askConsumerOptions();
					break;
				case 9: loop =false;
//					ebObj.updateEbFile(this.eb);
					display.displayMessege("Logging out");
					break;
				default: display.displayMessege("Enter correct option");
					break;
				}
		}
	}
	private boolean login() {
		boolean isConsumer = false;
		boolean loop = true;
		String user_name = null;
		int chances = 1;
		while(loop) {
			loop = false;
			System.out.println("Enter user name for logging in");
			user_name = input.getString();
			if(!operations.checkUserNameAvailable(user_name, isConsumer )) {
				if(chances >= validate.getMaxChance()) {
					display.displayChancesMessege();
					display.displayMessege("Going back to previous menu");
					return false;
				}
					
				else {
					loop = true;
					chances++;
					display.displayMessege("Please enter correct user_name");
				}
			}
		}
		chances = 1;
		loop = true;
		while(loop) {
			System.out.println("Enter password");
			String password = input.getString();
			boolean isValid = operations.validatePassword(user_name,password, "admin");
			if(!isValid) {
				if(chances >= validate.getMaxChance()) {
					display.displayChancesMessege();
					display.displayMessege("Going back to previous menu");
					return false;
				}
				chances++;
				display.displayMessege("please enter correct password");
			}
			else {
				loop = false;
				display.displayMessege("Logged in successfully");
				}
		}
		return true;
	}
}
