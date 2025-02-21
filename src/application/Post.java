package application;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

public class Post implements Serializable{
	private String title;
	private String body;
	private String date;
	private File attachment;
	private LinkedList<Integer> comments;
	private HashSet<String> likes;
	private int numOfLikes;
	private int postNum;
	
	public Post(String title, String body, int postNum) {
		comments= new LinkedList<Integer>();
		likes= new HashSet<String>();
		this.attachment= new File("images\\empty.png");
		this.title=title;
		this.body=body;
		this.postNum=postNum;
		this.date = new SimpleDateFormat("MM-dd-yyyy HH:mm").format(new Date());
		numOfLikes=0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public int getCommentAtIndex(int index) {
		return comments.get(index);
	}
	
	public int numberOfComments() {
		return comments.size();
	}
	
	public int lastCommentNumber() {
		return comments.get(comments.size()-1);
	}
	
	public int getPostNum() {
		return postNum;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setAttachment(File file) {
		attachment=file;
	}
	
	public File getAttachment() {
		return attachment;
	}
	
	public void addLike(String username) {
		likes.add(username);
		numOfLikes++;
	}
	
	public void removeLike(String username) {
		likes.remove(username);
		numOfLikes--;
	}
	
	public boolean hasUserLiked(String username) {
		return likes.contains(username);
	}
	
	public int getNumberOfLikes() {
		return numOfLikes;
	}
	
	public void addComment(int index) {
		comments.add(index);
	}
}
