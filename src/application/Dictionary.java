package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;

public class Dictionary implements Serializable{
	private HashSet<String> dictionary;
	
	public Dictionary() {
		dictionary= new HashSet<String>();
		File data = new File("Dictionary/dictionary.dat");
		if(data.length()==0) {
            dictionary = new HashSet<String>();
            
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader("Dictionary/dictionary.txt"));
                String line = reader.readLine();

                while (line != null) {
                    dictionary.add(line);
                    // read next line
                    line = reader.readLine();
                }
                this.save();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(data))) {
                Dictionary load = (Dictionary) (ois.readObject());
                    dictionary = load.getDictionary();
            }
            catch (IOException | ClassNotFoundException ie) {
                ie.printStackTrace();
            }
        }
	}
	
//	public void load() {
//		try {
//			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Dictionary/dictionary.dat"));
//			Dictionary temp= (Dictionary) ois.readObject();
//			dictionary=temp.getDictionary();
//			ois.close();
//		}
//		catch (IOException | ClassNotFoundException ie) {
//          ie.printStackTrace();
//      }
//	}
	
	public void save() {
        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Dictionary/dictionary.dat"))) {
            oos.writeObject(this);
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
    }
	
	public HashSet<String> getDictionary(){
		return dictionary;
	}
}
