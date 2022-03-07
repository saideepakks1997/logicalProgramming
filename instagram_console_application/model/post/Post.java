package post;

import java.time.LocalDateTime;
import java.util.*;

public class Post {
	private int postId;
	private String post_owner;
	private String content;
	private String caption;
	private LocalDateTime postCreatedTime;
	private String location;
	
	public Post(int postId,String post_owner, String content, LocalDateTime postCreatedTime) {
		this.postId = postId;
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
	
//	private Set<String> likes = new HashSet<String>();
	
}
