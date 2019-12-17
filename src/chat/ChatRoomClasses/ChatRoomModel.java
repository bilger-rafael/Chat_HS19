package chat.ChatRoomClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;

public class ChatRoomModel extends Model{
    ServiceLocator serviceLocator;
   
    
    public ChatRoomModel() {
    	super();
       
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
        
        //TODO Anfrage an Server, alle Chatrooms abrufen und in Observebal Table speichern
    }

}
