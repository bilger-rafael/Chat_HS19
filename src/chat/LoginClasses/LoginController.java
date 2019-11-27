package chat.LoginClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Controller;
import chat.appClasses.App_Model;
import chat.appClasses.App_View;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class LoginController extends Controller {
	ServiceLocator serviceLocator;
	
    public LoginController(LoginModel model, LoginView view) {
        super(model, view);
        
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
    }

	
}
