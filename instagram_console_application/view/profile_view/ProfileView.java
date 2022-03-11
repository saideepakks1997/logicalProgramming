package profile_view;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import instagram.Instagram;
import profile_operations.IProfileOperations;
import profile_operations.ProfileOperations;
import user.InstaUser;

public class ProfileView {
	Scanner sc = new Scanner(System.in);
	
	CommonView commonView = new CommonView();
	
	ICommonOperations commonOperations = null;
	IProfileOperations operations = null;
	
	public ProfileView(Instagram instagram) {
		this.commonOperations = new CommonOperations(instagram);
		this.operations = new ProfileOperations(instagram);
	}
	
	public void askProfileOptions(InstaUser profile_owner) {
		
		mapProfileOwnerToOperationsObj(profile_owner);
		boolean loop = true;
		while(loop) {
			System.out.println("Select option \n"
					+ "1->View Your profile \n"
					+ "2->Edit Your Profile\n"
					+ "3->Follow or Unfollow Profile\n"
					+ "9->Previous menu");
			int opt = commonView.getOption();
			switch(opt) {
			case 1:viewProfile(); 
				break;
			case 2: editProfile();
				break;
			case 3: followOrUnfollowProfile();
				break;
			case 9: commonView.displayMessege("Back to previous menu");
				loop = false;
				break;
			default: System.out.println("Enter correct option");
				break;
			}
		}
				
	}

	private void mapProfileOwnerToOperationsObj(InstaUser profile_owner) {
		this.operations.setProfileOwner(profile_owner);
		this.commonOperations.setProfileOwner(profile_owner);
	}
	
	private void viewProfile() {
		InstaUser profile_owner = operations.getMyProfile();
		commonView.displayMessege(profile_owner);
	}
	
	
	private void editProfile() {
		boolean loop = true;
		while(loop) {
			commonView.displayMessege(operations.getProfileFields());
			System.out.println("Select to update or add field");
			int opt = commonView.getOption();
//			
			switch(opt) {
				case 1: System.out.println("Enter name to update");
					String name = sc.nextLine();
					operations.setField(name,"name");
					break;
				case 2: System.out.println("Enter user_name to update");
					String user_name = sc.next();
					operations.setField(user_name, "user_name");
					break; 
				case 3: System.out.println("Enter bio to update or add");
					sc.skip("((?<!\\R)\\s)*");
					String bio = sc.nextLine();
					operations.setField(bio, "bio");
					break;
				case 4: setDOB(); 
					break;
				case 5: System.out.println("Enter email to update or add");
					String email = sc.next();
					operations.setField(email, "email");
					break;
				case 6: System.out.println("Enter phone no to update or add");
					try {
						Long phNo = sc.nextLong();
						operations.setField(phNo, "phNo");
					}	
					catch (InputMismatchException e) {
						commonView.displayMessege("Enter correct input phone number expected");
						sc = new Scanner(System.in);
					}
					break;
				case 7: System.out.println("Enter website to update or add");
					String website = sc.next();
					operations.setField(website, "website");
					break;
				case 8: System.out.println("Going Back to previous menu");
					loop = false;
					break;
				default: System.out.println("Enter the correct option");
					break;
			}
		}
	}
	
	
	private void setDOB() {
		boolean loop = true;
		while(loop) {
			loop = false;
			System.out.println("Enter date of birth to update or add format(yyyy-MM-dd)");
			String dob = sc.next();
			LocalDate date = commonOperations.getValidDate(dob);
			if(date != null) {
				String status = operations.setField(date, "dob");
				commonView.displayMessege(status);
			}
			else {
				loop = true;
				commonView.displayMessege("Please enter correct date");
			}
		}
		
	}

	private void followOrUnfollowProfile() {
		System.out.println("Enter user name");
		String searching_user_name = sc.next();
		//get all the users of the entered text
		List<String> listOfUsers = commonOperations.getSearchedUsers(searching_user_name);
		searching_user_name = getUserFromList(listOfUsers);
		
		if(searching_user_name != null) {
			String status = operations.checkFollowOrUnfollowStatus( searching_user_name);
			commonView.displayMessege(status);
			System.out.println("press 1->YES\n"
			 		+ "2->NO");
			int opt = sc.nextInt();
			 if(opt == 1) {
				 String messege = operations.changeFollowStatus(searching_user_name);
				 commonView.displayMessege(messege);
				}
		}
		
	}
	
	private String getUserFromList(List<String> listOfUsers) {
		String result_user = null;
		boolean loop = true;
		if(listOfUsers.size() == 0) {
			commonView.displayMessege("No users found");
			return null;
		}
		else {
			while(loop) {
				loop = false;
				System.out.println("Select user to follow or unfollow\n"
						+ "Press (-1) for not selecting");
				int i=1;
				for(String user:listOfUsers)
					System.out.println((i++)+"."+user);
				int opt = sc.nextInt();
				if(opt == -1) {
					commonView.displayMessege("Not selected any user");
					return null;
				}
				else {
					try {
						 result_user = listOfUsers.get(opt-1);
					}
					catch (IndexOutOfBoundsException e) {
						loop = true;
						commonView.displayMessege("Enter correct user option");
					}
				}
			}
		}
		return result_user;
	}
	
	
}
