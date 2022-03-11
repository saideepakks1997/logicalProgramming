package home_page_view;

import java.util.List;
import java.util.Scanner;

import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import instagram.Instagram;
import post_operations.IPostOperations;
import posts.PostView;
import profile_operations.IProfileOperations;
import profile_operations.ProfileOperations;
import profile_view.ProfileView;
import user.InstaUser;

public class HomePageView {
	Scanner sc = new Scanner(System.in);
	
	CommonView display = new CommonView();
	
	ProfileView profile = null;
	PostView post = null;
	
	public HomePageView(Instagram instagram) {
		this.profile = new ProfileView(instagram);
		this.post = new PostView(instagram);
	}
	
	public void askOptions(InstaUser user) {
		boolean loop = true;
		int opt = 0;
		while(loop) {
			display.displayMessege("Select option\n"
					+ "1-> Posts menu(view posts,create post all post activities)\n"
					+ "2-> profile menu(view profile,edit profile ,follow profile)\n"
					+ "5-> Log out");
			opt = display.getOption();
			switch (opt) {
			case 1: goToPosts(user);
				break;
			case 2: goToProfile(user); 
				break;
			case 5: display.displayMessege("Logging out");
				loop = false;
				break;
			default: System.out.println("Enter correct option");
				break;
			}
		}
	}
	
	private void goToProfile(InstaUser user) {
		profile.askProfileOptions(user);
	}
	private void goToPosts(InstaUser user) {
		post.askPostOptions(user);
	}
}
