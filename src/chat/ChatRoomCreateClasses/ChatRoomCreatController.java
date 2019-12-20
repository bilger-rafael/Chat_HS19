package chat.ChatRoomCreateClasses;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;


public class ChatRoomCreatController extends Controller<ChatRoomCreatModel, ChatRoomCreatView> {
	ServiceLocator serviceLocator;

	public ChatRoomCreatController(ChatRoomCreatModel model, ChatRoomCreatView view) {
		super(model, view);

        //Action für Abbrechen
        view.getLogoutMenuItem().setOnAction( e -> goToChatRoom());
        
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        
        //Action für CreateUserButton
        view.getLogoutMenuItem().setOnAction( e -> getBackLoginView());
		
		
		
		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application ChatRoomController initialized");
	}

	private void create() {
		
		
	}

	private void getBackLoginView() {
		// TODO Logout mit dem Netzwerk und alle Fenster schliessen
		
    	//Logik für zurück auf LoginView
    	view.stop();
    	JavaFX_App_Template.getMainProgram().getLoginView().start();
		
	}

	private void goToChatRoom() {
		this.view.stop();
		JavaFX_App_Template.getMainProgram().getChatRoom().start();
	}

}

