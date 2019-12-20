package chat.ChatRoomCreateClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;

public class ChatRoomCreatModel extends Model{
    ServiceLocator serviceLocator;
   
    
    public ChatRoomCreatModel() {
    	super();
       
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }
    


}
