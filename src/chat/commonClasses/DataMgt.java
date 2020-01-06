package chat.commonClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import chat.ServiceLocator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataMgt implements Serializable{
		transient ServiceLocator serviceLocator;
		private transient static DataMgt dataMgt;		private transient ArrayList<String> chatnames;
		private ArrayList<Object[]> data;
		
	    private DataMgt() {
	    	this.data = new ArrayList<Object[]>();
	    	load();
	         }
	    public static DataMgt getDataMgt() {
	        if (dataMgt == null)
	        	dataMgt = new DataMgt();
	        return dataMgt;
	    }

	    
	   //Daten von HD laden
	  private void load() {
		    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:/test.bin"))) {
		    	this.data =  (ArrayList<Object[]>) in.readObject();
		    	
		        serviceLocator.getLogger().info("Daten geladen");
		    }catch(Exception e) {
		    	serviceLocator.getLogger().info("Daten laden fehlgeschlagen");
		        }

	  }
	  
	  //Erlaubt es dem ChatModel beim Connect die Daten zu laden
	  public ObservableList<String> loadChat(String chatName) {
		  Object[] temp = new Object[2];
		  ObservableList<String> tempList = FXCollections.observableArrayList();
		  
		  for(int i = 0 ; i < this.data.size(); i++ ) {
			  Object[] temp2 = new Object[2];
			  temp2 = this.data.get(i);	
			  if(temp2[0] == chatName) {
				  tempList = (ObservableList<String>) temp2[1];
			  }
		  }
		  return tempList;
	  }
	    
	  //Erlaubt es dem Chatmodel beim Disconnect die Daten zu speichern
	  public void saveChat(String chatName, ObservableList chatbackups) {
		  Object[] temp = new Object[2];
		  temp[0]=chatName;
		  temp[1]=chatbackups;
		  
		  for(int i = 0 ; i < this.data.size(); i++ ) {
			  Object[] temp2 = new Object[2];
			  temp2 = this.data.get(i);
			  if(temp2[0] == chatName) {
				  this.data.remove(i);
			  }
		  }
		  this.data.add(temp);
		  
		  
	  }
	
	  public void saveToFile() {
		  String path = System.getProperty("java.io.tmpdir") + File.separator + "Chat.txt";
		  
	    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {	
	        out.writeObject(this.data);
	        out.flush();

	      serviceLocator.getLogger().info("Daten gespeichert");
	      
	    } catch (Exception e) {
	    	serviceLocator.getLogger().info("Datenspeicherung fehlgeschlagen");
	    
	    }
	  }
}
