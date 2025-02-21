package application;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Account implements Serializable{
	//private String image;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private File imageDirectory;
	private TreeSet<String> following;
	private TreeSet<String> followers;
	private LinkedList<Integer> posts;
	private LinkedList<Integer> timeline;
	
	public Account(String username, String password, String firstName, String lastName) {
		following= new TreeSet<String>();
		followers= new TreeSet<String>();
		posts= new LinkedList<Integer>();
		timeline=new LinkedList<Integer>();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		imageDirectory= new File ("images\\Logo.jpg");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void addPostIndex(int index) {
		posts.add((Integer) index);
	}
	
	public int getNumberOfPosts() {
		return posts.size();
	}
	
	public int getPostIndexFromPostIndex(int index) {
		return posts.get(index);
	}
	
	public void addFollowing(String username) {
		if(!(following.contains(username))) {
			following.add(username);
		}
	}
	
	public void deleteFollowing(String username) {
		if(following.contains(username)) {
			following.remove(username);
		}
	}
	
	public void addFollower(String username) {
		if(!(followers.contains(username))) {
			followers.add(username);
		}
	}
	
	public void deleteFollower(String username) {
		if(followers.contains(username)) {
			followers.remove(username);
		}
	}
	
	public boolean isFollowing(String username) {
		return following.contains(username);
	}
	
	public boolean isFollowerTo(String username) {
		return followers.contains(username);
	}
	
	public void setFollowing(TreeSet<String> tree) {
		following=tree;
	}
	public int getNumberOfFollowing() {
		return following.size();
	}
	
	public TreeSet<String> getFollowingList() {
		return following;
	}
	
	public TreeSet<String> getFollowersList(){
		return followers;
	}
	
	public LinkedList<Integer> getPostsList(){
		return posts;
	}
	
	public void addToTimeline(int index) {
		timeline.add(index);
	}
	
	public LinkedList<Integer> getTimeline(){
		return timeline;
	}
	
	
	public void setProfileImage(File directory){
		imageDirectory=directory;
	}
	
	public File getProfileImage() {
		return imageDirectory;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
	
	

}
