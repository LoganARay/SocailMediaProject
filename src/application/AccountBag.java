package application;

import java.io.Serializable;
import java.util.TreeMap;

public class AccountBag  implements Serializable{
	private TreeMap<String, Account> userMap;
	
	public AccountBag() {
		userMap= new TreeMap<String, Account>();
	}
	
	public void addAccount(String userName, String password, String firstName, String lastName) {
		userMap.put(userName, new Account(userName, password, firstName, lastName));
	}
	
	public int getBagSize() {
		return userMap.size();
	}
	
	public boolean searchForUsernameInUse(String username) {
		if(userMap.containsKey(username)) {
			return true; 
		}else {
			return false;
		}
	}
	
	public Account getAccount(String username) {
		return userMap.get(username);
	}
}
