package posts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import instagram.Instagram;
import post.Post;
import post_operations.IPostOperations;
import post_operations.PostOperations;
import user.InstaUser;

public class PostView {
	Scanner sc = new Scanner(System.in);
	CommonView display = new CommonView();
	
	LikeCommentView likeView = null;
	
	IPostOperations operations = null;
	ICommonOperations commonOperations= null;
	
	public PostView(Instagram instagram) {
		this.likeView = new LikeCommentView(instagram);
		this.commonOperations = new CommonOperations(instagram);
		this.operations = new PostOperations(instagram);
	}
	
	public void askPostOptions(InstaUser profile_owner) {
		createOperationsObj(profile_owner);
		boolean loop = true;
		while(loop) {
			System.out.println("Enter option to select \n"
			+ "1->Create post\n"
			+ "2->View posts\n"
			+ "3->View My posts\n"
			+ "4->Delete post\n"
			+ "5->Remove tag in post\n"
			+ "9->Back to Previos menu");
			int opt = display.getOption();
			switch (opt) {
			case 1: createPost();
				break;
			case 2: viewPosts(profile_owner);
				break;
			case 3: viewSelfPosts();
				break;
			case 4: deletePost();
				break;
			case 5: removeTag();
				break;
			case 9: display.displayMessege("Back to previous menu");
				loop = false;
				break;
			default:
				break;
			}
		}
	}

	private void removeTag() {
		boolean loop = true;
		Post post = askAndSelectPost();
		while(loop) {
			List<String> listOfTags = new ArrayList<>(post.getTags());
			int i=1;
			if(listOfTags.size() > 0) {
				System.out.println("The list of tagged users are enter tag number to remove");
				for(String tag:listOfTags) {
					System.out.println((i++)+"."+tag);
				}
				int opt = display.getOption();
				try {
					String taggedUser = listOfTags.get(opt-1);
					String status = operations.deleteTag(post, taggedUser);
					display.displayMessege("Still you want to remove tag \n"
							+ "Enter 1->Yes\n"
							+ "2->No");
					int option = display.getOption();
					if(option == 2) {
						loop = false;
					}
				}
				catch (Exception e) {
					display.displayMessege("Enter correct number");
				}
			}
			else {
				display.displayMessege("No tags available");
				loop = false;
			}
		}
		
		
		
	}
	private Post askAndSelectPost() {
		boolean loop = true;
		Post post = null;
		while(loop) {
			loop = false;
			List<Post> myPosts = operations.getSelfPosts();
			int i=1;
			for(Post p: myPosts) {
				System.out.println("Post No:-"+(i++));
				display.displayMessege(p);
			}
			System.out.println("Select post number");
			int opt = display.getOption();
			try {
				post = myPosts.get(opt-1);
			}
			catch (Exception e) {
				loop = false;
				display.displayMessege("Please enter correct post number");
			}
		}
		
		return post;
	}
	private void deletePost() {
		Post post = askAndSelectPost();
		String status = operations.deletePost(post);
		display.displayMessege(status);
	}

	private void viewSelfPosts() {
		List<Post> myPosts  = operations.getSelfPosts();
		int i=1;
		for(Post post: myPosts) {
			System.out.println("Post no :-"+(i++));
			display.displayMessege(post);
		}
	}
	
	private void createOperationsObj(InstaUser profile_owner) {
//		this.profile_owner = profile_owner;
		this.operations.setProfileOwner(profile_owner);
		this.commonOperations.setProfileOwner(profile_owner);
	}

	private void createPost() {
		System.out.println("Enter the content you need to post");
		sc.skip("((?<!\\R)\\s)*");
		String content = sc.nextLine();
		Set<String> tags =  askForTagUsers();
		LocalDateTime time = LocalDateTime.now();
		operations.createPost( content, time,tags);
		display.displayMessege("Post Created successfully");
	}

	private  Set<String> askForTagUsers() {
		Set<String> tags = new TreeSet<>();
			System.out.println("Do you want to tag peoples \n"
			+ "Press 1->Yes\n"
			+ "2->Nos");
			int opt = display.getOption();
			if(opt == 1)
				tags = getTaggedUsers();
			else if(opt == 2) {
				return null;
			}
			
			return tags;
		}

	private Set<String> getTaggedUsers() {
		boolean loop = true;
		Set<String> tags = new TreeSet<String>();
		while(loop) {
			System.out.println("Enter the user name to tag");
			String searching_user_name = sc.next();
			List<String> users =commonOperations.getSearchedUsers(searching_user_name);
			if(users.size() >0) {
				System.out.println("Select user to tag");
				int i=0;
				for(String user : users) {
					System.out.println((++i)+"."+user);
				}
				int opt = display.getOption();
				
				try {
					String taggedUser = users.get(opt-1);
					boolean isTagged = tags.add(taggedUser);
					if(!isTagged)
						display.displayMessege(taggedUser+ " already tagged");
				}
				catch(Exception e) {
					display.displayMessege("Enter the correct option");
				}
			}
			else {
				display.displayMessege("No users found for the searched name");
			}
			System.out.println("Still you want to tag \n"
					+ "1->Yes\n"
					+ "2->No");
			int opt = display.getOption();
			if(opt == 2)
				loop = false;
		}
		
		return tags;
	}
	
	private void viewPosts(InstaUser profile_owner) {
		Set<Post> posts = operations.getPosts();
		if(posts == null)
			display.displayMessege("No posts found");
		else {
			int i=1;
			for(Post post:posts)
				display.displayPosts(post, i++);
			
			likeOrCommentPost(new ArrayList<Post>(posts), profile_owner);
			}
		}

	private void likeOrCommentPost(List<Post> posts,InstaUser profile_owner) {
		System.out.println("Press 1-> For liking or commenting the post\n"
				+ "2->back previous menu");
		int opt = display.getOption();
		if(opt == 1) {
			getPostNumberFromUserForLikeComment(posts,profile_owner);
		}
		else if(opt == 2)
			return;
		
	}

	private void getPostNumberFromUserForLikeComment(List<Post> posts, InstaUser profile_owner) {
			display.displayMessege("Select post number\n"
					+ "Enter (-1)-->Previous menu");
			int postNo = display.getOption();
			if(postNo != -1) {
				try {
					Post post = posts.get(postNo-1);
					goToLikeCommentView(post,profile_owner);
				}
				catch(Exception e) {
					display.displayMessege("Enter correct post number");
				}
			}
		}

	private void goToLikeCommentView(Post post, InstaUser profile_owner) {
		likeView.getInputForLikeOrComment(post,profile_owner);
		}
}
