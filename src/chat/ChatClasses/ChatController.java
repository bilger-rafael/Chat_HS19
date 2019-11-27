package chat.ChatClasses;

import chat.ServiceLocator;
import chat.ChatRoomClasses.ChatRoomModel;
import chat.ChatRoomClasses.ChatRoomView;
import chat.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ChatController  extends Controller {
	ServiceLocator serviceLocator;
	
    public ChatController(ChatModel model, ChatView view) {
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