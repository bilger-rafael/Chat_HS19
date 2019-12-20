package chat.ChatRoomClasses;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.LoginClasses.LoginModel;
import chat.LoginClasses.LoginView;
import chat.NewUserClasses.NewUserModel;
import chat.NewUserClasses.NewUserView;
import chat.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ChatRoomController extends Controller<ChatRoomModel, ChatRoomView> {
	ServiceLocator serviceLocator;
	
    public ChatRoomController(ChatRoomModel model, ChatRoomView view) {
        super(model, view);
        
        //Action für CreateUserButton
        view.getLogoutMenuItem().setOnAction( e -> getBackLoginView());
        
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
        
        
		synchronized(model.chatrooms) {
			view.chatRoomCenter.setItems(model.chatrooms);
		}
        
    }

	private void getBackLoginView() {
		// TODO Logout mit dem Netzwerk und alle Fenster schliessen
		
    	//Logik für zurück auf LoginView
    	view.stop();
    	JavaFX_App_Template.getMainProgram().getLoginView().start();
		
	}

	
}