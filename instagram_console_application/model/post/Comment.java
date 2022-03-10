package post;

import java.time.LocalDateTime;

public class Comment {
	String commented_by;
	String comment;
	LocalDateTime commentedTime;
	
	public Comment(String commented_by, String comment,LocalDateTime time) {
		this.commented_by = commented_by;
		this.comment = comment;
		this.commentedTime = time;
	}
	public String toString() {
		String comment = "Commented by :-"+this.commented_by+"\n"
				+ "Comment :-"+this.comment+"\n"
				+ "commentedTime :-"+this.commentedTime;
		return comment;
	}
	
}
