package chat.PWChangeClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;

public class PWChangeModel extends Model{
    ServiceLocator serviceLocator;
   
    
    public PWChangeModel() {
    	super();
       
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }
    


}
