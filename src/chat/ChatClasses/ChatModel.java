package chat.ChatClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;

public class ChatModel extends Model{
    ServiceLocator serviceLocator;
   
    
    public ChatModel() {
    	super();
       
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }

}
