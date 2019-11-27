package chat.LoginClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;

public class LoginModel extends Model{
    ServiceLocator serviceLocator;
   
    
    public LoginModel() {
    	super();
       
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }

}
