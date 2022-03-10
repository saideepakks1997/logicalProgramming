package post;

import java.time.LocalDateTime;
import java.util.*;

import user.InstaUser;

public class Post {
	private int postId;
	private String post_owner;
	private String content;
	private String caption;
	private LocalDateTime postCreatedTime;
	private String location;
	
	private Set<String> likes = new TreeSet<>();
	private List<Comment> comments = new ArrayList<Comment>();
	private Set<String> tags = new TreeSet<>();
	
	public Post(String post_owner, String content, LocalDateTime postCreatedTime) {
		this.post_owner = post_owner;
		this.content = content;
		this.postCreatedTime = postCreatedTime;
	}
	
	public String getContent() {
		return content;
	}
	public void setPost(String content) {
		this.content = content;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public LocalDateTime getPostCreatedTime() {
		return postCreatedTime;
	}
	public void setPostCreatedTime(LocalDateTime postCreatedTime) {
		this.postCreatedTime = postCreatedTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPost_owner() {
		return post_owner;
	}
	public void setPost_owner(String post_owner) {
		this.post_owner = post_owner;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	
	public String toString() {
		String str = "Posted by -->"+this.getPost_owner()+"\n"
				+ "Content -->"+this.getContent()+"\n"
				+ "Created Time -->"+this.getPostCreatedTime()+"\n"
				+ "Likes -->"+this.getLikes().size()+"\n"
				+ "Comments --> "+this.getComments().size()+"\n"
				+ "tags -->"+this.getTags();
		return str;
	}
//	private Set<String> likes = new HashSet<String>();

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<String> getLikes() {
		return likes;
	}

	public void setLike(String likedUser) {
		this.likes.add(likedUser);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(Comment comment) {
		this.comments.add(comment);
	}
}
