package posts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import common_view.CommonView;
import instagram.Instagram;
import post.Comment;
import post.Post;
import post_operations.ILikeCommentOperation;
import post_operations.LikeCommentOperations;
import user.InstaUser;

public class LikeCommentView {
	CommonView display = new CommonView();
	ILikeCommentOperation operations = null;
	
	Scanner sc = new Scanner(System.in);
//	InstaUser profile_owner = null;
	public LikeCommentView(Instagram instagram) {
		this.operations = new LikeCommentOperations(instagram);
		}
	
	public void getInputForLikeOrComment(Post post,InstaUser profile_owner) {
		mapPostProfileOwnerToOperations(post,profile_owner);
		boolean loop = true;
		while(loop) {
			
			display.displayMessege("Enter option \n"
					+ "1->like post\n"
					+ "2->Comment post\n"
					+ "3->view who are all liked\n"
					+ "4->View comments\n"
					+ "5->View current post\n"
					+ "9->Back to previous menu");
			int opt = display.getOption();
			switch (opt) {
			case 1: likePost();
				break;
			case 2: commentPost();
				break;
			case 3: viewLikedUsers();
				break;
			case 4: viewComments();
				break;
			case 5: viewCurrentPost(post);
				break;
			case 9: loop = false;
				display.displayMessege("Back to previuos menu");
				break;
			default:
				break;
			}
//			if(opt != 3) {
				display.displayMessege(post);
//			}
		}
		
	}
	
	private void viewCurrentPost(Post post) {
		display.displayMessege(post);
	}

	private void mapPostProfileOwnerToOperations(Post post, InstaUser profile_owner) {
		this.operations.setPostProfileOwner(post, profile_owner);
	}

	private void likePost() {
		String status = operations.getPostLikeStatus();
		display.displayMessege("Post is "+status+"\n"
				+ "press 1->Yes\n"
				+ "2->No");
		int opt = display.getOption();
		if(opt == 1) {
			String result = operations.addOrRemoveLike();
			display.displayMessege(result);
		}
		else {
			display.displayMessege("Left as it is");
		}
		
	}
	private void viewComments() {
		List<Comment> comments = operations.getAllComments();
		if(comments.size() > 0) {
			System.out.println("List of liked users");
			for(Comment comment :comments) {
					display.displayMessege(comment);
				}
		}
		else
			display.displayMessege("No one commented");
	}

	private void viewLikedUsers() {
		Set<String> listOfLikedUsers = operations.getLikedUsers();
		System.out.println("List of liked users");
		System.out.println("-----------------");
		for(String userName:listOfLikedUsers)
			System.out.println(userName);
		System.out.println("-----------------");
	}

	private void commentPost() {
		LocalDateTime time = LocalDateTime.now();
		System.out.println("Enter the comment");
		sc.skip("((?<!\\R)\\s)*");
		String content = sc.nextLine();
		String status = operations.addComment(content,time);
		display.displayMessege(status);
	}
	
}
