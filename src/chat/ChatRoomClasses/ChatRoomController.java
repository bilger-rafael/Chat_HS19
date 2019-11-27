package chat.ChatRoomClasses;

import chat.ServiceLocator;
import chat.LoginClasses.LoginModel;
import chat.LoginClasses.LoginView;
import chat.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ChatRoomController extends Controller {
	ServiceLocator serviceLocator;
	
    public ChatRoomController(ChatRoomModel model, ChatRoomView view) {
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