package comparator;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;

import post.Post;

public class PostObjComparator implements Comparator<Post>{

	@Override
	public int compare(Post post1, Post post2) {
		LocalDateTime postTime1 = post1.getPostCreatedTime();
		LocalDateTime postTime2 = post2.getPostCreatedTime();
		return postTime2.compareTo(postTime1);
	}
	
}
