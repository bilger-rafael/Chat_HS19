package chat.NewUserClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;

public class NewUserModel extends Model{
    ServiceLocator serviceLocator;
   
    
    public NewUserModel() {
    	super();
       
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }

}