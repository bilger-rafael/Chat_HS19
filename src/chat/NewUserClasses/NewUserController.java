package chat.NewUserClasses;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.ChatClasses.ChatModel;
import chat.ChatClasses.ChatView;
import chat.ChatRoomClasses.ChatRoomModel;
import chat.LoginClasses.LoginController;
import chat.LoginClasses.LoginModel;
import chat.LoginClasses.LoginView;
import chat.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NewUserController extends Controller {
	ServiceLocator serviceLocator;

	
    public NewUserController(NewUserModel model, NewUserView view) {
        super(model, view);
        
        //Action für CreateUserButton
        view.getCancelButton().setOnAction( e -> getBackLoginView());
        
        //Action für CreateUserButton
        view.getOkButton().setOnAction( e -> createUserAndBackLoginView());
        
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

	

    
    //Leitet zur CreatUserView
    private void getBackLoginView() {
    	
    	//Logik für zurück auf LoginView

		
    }
    //Leitet zur CreatUserView
    private void createUserAndBackLoginView() {
    	
    	//Validierung der Eingabe fehlt noch
    	
    	//Logik für zurück auf LoginView
    	

		
    }
}
