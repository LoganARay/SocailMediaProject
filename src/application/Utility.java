package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.io.Serializable;

public class Utility  implements Serializable{
	
	private AccountBag accBag;
	private PostBag postBag;
	
	public Utility() {
		accBag = new AccountBag();
        File data = new File("Utility.dat");
        if(data.length()==0) {
            accBag = new AccountBag();
            postBag = new PostBag();
            this.save();
        }
        else {
            try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(data))) {
                Utility load = (Utility) (ois.readObject());
                    accBag = load.getAccountBag();
                    postBag = load.getPostBag();
            }
            catch (IOException | ClassNotFoundException ie) {
                ie.printStackTrace();
            }
        }
    }
  
	
	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Utility.dat"));
			Utility ult= (Utility) ois.readObject();
			accBag=ult.getAccountBag();
			postBag=ult.getPostBag();
			ois.close();
		}
		catch (IOException | ClassNotFoundException ie) {
          ie.printStackTrace();
      }
	}
	public void save() {
        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Utility.dat"))) {
            oos.writeObject(this);
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
    }
	
	public void addPost(String title, String body) {
		postBag.addPost(title, body);
	}
	
	public AccountBag getAccountBag() {
		return accBag;
	}
	
	public PostBag getPostBag() {
		return postBag;
	}

	
	public void insert(String userName, String password, String firstName, String lastName) {
		accBag.addAccount(userName, password, firstName, lastName);
	}
	
	public boolean searchForUsernameInUse(String key) {
		return accBag.searchForUsernameInUse(key);
	}
	
	
}
