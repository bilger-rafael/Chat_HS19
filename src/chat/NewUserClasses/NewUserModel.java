package chat.NewUserClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;
import chat.commonClasses.Client;
import chat.message.Message;
import javafx.beans.property.SimpleStringProperty;

public class NewUserModel extends Model{
    ServiceLocator serviceLocator;
	protected SimpleStringProperty newestMessageNewUser = new SimpleStringProperty();
    
    public NewUserModel() {
    	super();
    	newUserReader();

        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }
    
    
    //TODO Funktioniert irgendwie nicht!!!!!!!
	private void newUserReader() {
		// Create thread to read incoming messages
		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						Message msg = Message.receive(Client.getClient().getSocket());
						//TODO Property setzen

						
						ServiceLocator.getServiceLocator().getLogger().info("Received NewUserM: " + msg);
						// TODO Einkommende Message verarbeiten (Events für jeden Message Typ (Result,
						// MessageError, MessageText)
						if(msg.getType() == "Result") {
							newestMessageNewUser.set("");
							newestMessageNewUser.set(msg.getContext());
						}
					
					}
				} catch (Exception e) {
					ServiceLocator.getServiceLocator().getLogger().info("ListenerNewUserModel disconnected");
				}
			}
			

		};
		Thread t = new Thread(r);
		t.start();
	}

}