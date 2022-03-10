package main_view;

import java.util.List;
import java.util.Scanner;

import common_view.CommonView;
import home_page_view.HomePageView;
import instagram.Instagram;
import login_registration_operations.ILoginRefistrationOperations;
import login_registration_operations.LoginRegistrationOperations;
import user.InstaUser;

public class MainView {
	Scanner sc = new Scanner(System.in);
	ILoginRefistrationOperations operations = null;
	
	
	HomePageView profile = null;
	
	CommonView display = new CommonView();
	
	public MainView(Instagram instagram) {
		this.operations = new LoginRegistrationOperations(instagram);
		this.profile = new HomePageView(instagram);
	}
	
	public void landingPage() {
		boolean loop = true;
		int opt =0;
		while(loop) {
			System.out.println("1->Register user\n"
					+ "2->Login user\n"
					+ "3->Exit");
			opt = display.getOption();
			switch(opt) {
				case 1:resgistrationInputs();
					break;
				case 2:loginInputs();
					break;
				case 3: System.out.println("Thank you for using application");
					loop = false;
					break;
				default: System.out.println("Enter correct option");
					break;
			}
		}
	}
	
	private void resgistrationInputs() {
		boolean loop = true;
		sc.nextLine();
		System.out.println("Enter your name");
		String name = sc.nextLine();
		String user_name = null;
		while(loop) {
			System.out.println("Enter user name ");
			user_name = sc.next();
			boolean isUserTaken = operations.checkIfUserNameIsCorrect(user_name);
			if(isUserTaken)
				display.displayMessege(user_name+" already taken please try different name");
			else 
				loop = false;
		}
		loop = true; 
		sc.nextLine();
		String password = null ;
		while(loop) {
			System.out.println("Enter the password to set ");
			password = sc.nextLine();
			List<String> passwordErrors = operations.checkIfPasswordIsValid(password);
			if(passwordErrors.size() == 0) {
				System.out.println("Re-enter the password ");
				String reCheckPassword = sc.next();
				if(password.equals(reCheckPassword)) {
					loop = false;
				}
				else {
					System.out.println("Re-entered password does not matches with password");
					System.out.println("Start entering password from starting");
					sc.nextLine();
				}
			}
				
			else
				display.displayPasswordError(passwordErrors);
			}
		InstaUser user = operations.registerUser(name, user_name, password);
		display.displayMessege(user_name+ " registered successfully");
		profile.askOptions(user);
	}
	
	private void loginInputs() {
		boolean loop = true;
		String user_name = null;
		boolean isUserAvailable = false;
		while(loop) {
			System.out.println("Enter user name");
			user_name = sc.next();
			isUserAvailable = operations.checkIfUserNameIsCorrect(user_name);
			if(!isUserAvailable) {
				display.displayMessege(user_name+" NOT EXISTS");
				loop = true;
			}
			else
				loop = false;
		}
		loop = true;
		while(loop) {
			System.out.println("Enter password ");
			String password = sc.next();
			
			InstaUser user = operations.loginUser(user_name, password);
				if(user == null) {
					loop = true;
					display.displayMessege("password invalid");
				}
				else {
					loop = false;
					profile.askOptions(user);
				}
		}
	}
}
