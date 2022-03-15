package main_view;

import admin_view.AdminView;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import consumer_view.ConsumerView;
import eb.ElectricityBoard;

public class MainView {
	CommonView commonView = null;
	AdminView adminView = null;
	ConsumerView consumerView = null;
	
	ICommonOperations operations = null;
	public MainView(ElectricityBoard eb) {
		this.adminView = new AdminView(eb);
		this.consumerView = new ConsumerView(eb);
		this.commonView = new CommonView(eb);
		this.operations = new CommonOperations(eb);
	}
	public void askOptions(){
		boolean loop = true;
		boolean isValid = false;
		while(loop) {
			commonView.displayMessege("Enter option \n"
					+ "1->Admin\n"
					+ "2->Consumer\n"
					+ "9->Log out");
			int opt = commonView.getInt();
			switch (opt) {
			case 1: isValid = login("admin");
				if(isValid)
					adminView.adminOptions();
				break;
			case 2: this.consumerView.askConsumerOptions();
//				isValid = login("consumer");
//				consumerView.consumerOptions();
				break;
			case 9: loop =false;
				commonView.displayMessege("Logging out");
				break;
			default: commonView.displayMessege("Enter correct option");
				break;
			}
		}
		
		
	}
	private boolean login(String userType) {
		boolean loop = true;
		String user_name = null;
		int chances = 0;
		while(loop) {
			loop = false;
			System.out.println("Enter user name for logging in");
			user_name = commonView.getString();
			if(!operations.checkUserNameAvailable(user_name, userType)) {
				if(chances >= 2) {
					loop = false;
					commonView.displayMessege("Chances over please try after sometime");
					return false;
				}
					
				else {
					loop = true;
					chances++;
					commonView.displayMessege("Please enter correct user_name");
				}
			}
		}
		chances = 0;
		loop = true;
		while(loop) {
			System.out.println("Enter password");
			String password = commonView.getString();
			boolean isValid = operations.validatePassword(user_name,password, userType);
			if(!isValid) {
				if(chances >= 2) {
					loop = false;
					commonView.displayMessege("Chances over please try after sometime");
					return false;
				}
				commonView.displayMessege("please enter correct password");
			}
			else {
				loop = false;
				commonView.displayMessege("Logged in successfully");
				}
		}
		return true;
	}
}
