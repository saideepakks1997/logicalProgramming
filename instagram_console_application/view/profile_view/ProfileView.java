package profile_view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import instagram.Instagram;
import post.Post;
import profile_operations.IProfileOperations;
import profile_operations.ProfileOperations;
import user.InstaUser;

public class ProfileView {
	Instagram instagram = null;
	Scanner sc = new Scanner(System.in);
	IProfileOperations operations = null;
	InstaUser user = null;
	public ProfileView(Instagram instagram) {
		this.instagram = instagram;
		this.operations = new ProfileOperations(instagram);
	}
	
	public void askOptions(InstaUser user) {
		this.user = user;
		boolean loop = true;
		while(loop) {
			System.out.println("Select option\n"
					+ "1-> Post content\n"
					+ "2-> Follow or UnFollow\n"
					+ "3-> View Posts\n"
					+ "4-> Log out");
			int opt = sc.nextInt();
			switch (opt) {
			case 1: postContent();
				break;
			case 2: followOrUnfollow();
				break;
			case 3: postsView(); 
				break;
			case 4: displayMessege("Logging out");
				loop = false;
				break;
			default: System.out.println("Enter correct option");
				break;
			}
		}
	}
		private void postsView() {
			Set<Post> posts = operations.getPosts(user);
			if(posts == null)
				displayMessege("No posts found");
			else
				displayPosts(posts);
		}

		private void displayPosts(Set<Post> posts) {
			for(Post post:posts) {
				System.out.println("-------------------------");
				System.out.println("Post id --->"+post.getPostId());
				System.out.println("Posted by --->"+post.getPost_owner());
				System.out.println(post.getContent());
				System.out.println("Created on "+post.getPostCreatedTime());
				System.out.println("-------------------------");
			}
				
			
		}

	// 	post content
	private void postContent() {
		System.out.println("Enter the content you need to post");
		sc.nextLine();
		String content = sc.nextLine();
		LocalDateTime time = LocalDateTime.now();
		operations.createPost(user, content, time);
		displayMessege("Post Created successfully");
	}
	
     private void displayMessege(String messege) {
    	 System.out.println("--------------------");
 		 System.out.println(messege);
 		 System.out.println("--------------------");
	}

	//	follow  or unfollow user
	private void followOrUnfollow() {
		System.out.println("Enter user name");
		String searching_user_name = sc.next();
		//get all the users of the entered text
		List<String> listOfUsers = operations.getSearchedUsers(searching_user_name);
		searching_user_name = getUserFromList(listOfUsers);
		
		if(searching_user_name != null) {
			String status = operations.checkFollowOrUnfollowStatus(user, searching_user_name);
			displayMessege(status);
			System.out.println("press 1->YES\n"
			 		+ "2->NO");
			int opt = sc.nextInt();
			 if(opt == 1) {
				 String messege = operations.changeFollowStatus(user,searching_user_name);
				 displayMessege(messege);
				}
		}
		 	
	}
	
	
	
	private String getUserFromList(List<String> listOfUsers) {
		if(listOfUsers.size() == 0) {
			displayMessege("No users found");
			return null;
		}
		else {
			System.out.println("Select user to follow or unfollow\n"
					+ "Press -1 for not selecting");
			int i=1;
			for(String user:listOfUsers)
				System.out.println((i++)+"."+user);
			int opt = sc.nextInt();
			if(opt == -1) {
				displayMessege("Not selected any user");
				return null;
			}
			else
				return listOfUsers.get(opt-1);
		}
	}

	public void displayProfileStatus(String user_name, String status) {
		System.out.println("You are "+status+" "+user_name);
	}
}
