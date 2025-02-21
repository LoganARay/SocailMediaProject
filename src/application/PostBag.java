package application;

import java.io.Serializable;
import java.util.LinkedList;

public class PostBag  implements Serializable{
	private LinkedList<Post> postList;

	public PostBag() {
		postList= new LinkedList<Post>();
	}
	
	public void addPost(String username, String body) {
		postList.add(new Post(username, body, postList.size()));
	}
	
	public int numberOfPosts() {
		return postList.size();
	}
	
	public Post searchByPostNumber(int postNum) {
		return postList.get(postNum);
	}
}
