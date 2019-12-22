package chat.NewUserClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;
import javafx.beans.property.SimpleStringProperty;

public class NewUserModel extends Model{
    ServiceLocator serviceLocator;
	protected SimpleStringProperty newestMessageNewUser = new SimpleStringProperty();
    
    public NewUserModel() {
    	super();
    	
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }

}