package chat.ChatRoomCreateClasses;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.abstractClasses.Controller;
import chat.commonClasses.Client;
import chat.commonClasses.MessageListener;
import chat.message.CreateChatroom;
import chat.message.Message;
import chat.message.Result;
import chat.message.ResultType;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;


public class ChatRoomCreatController extends Controller<ChatRoomCreatModel, ChatRoomCreatView> {
	ServiceLocator serviceLocator;

	public ChatRoomCreatController(ChatRoomCreatModel model, ChatRoomCreatView view) {
		super(model, view);

        
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        
		
        view.getBackButton().setOnAction(e -> goToChatRoom());
        
        view.getCreateButton().setOnAction(e -> create());
        
        
		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application ChatRoomController initialized");
	}

	private void create() {
		String name = view.getNameField().getText();
		boolean isPublic = view.getCheckBox().isSelected();
		
		CreateChatroom createChatroom = new CreateChatroom(name, isPublic);
		
		Client.getClient().addMsgListener(new MessageListener() {
			@Override
			public void receive(Message msg) {
				if (msg instanceof Result) {
					Result r = (Result) msg;
					if (r.getType() == ResultType.Simple) {
						if (r.getBoolean()) {
							serviceLocator.getLogger().info("Gruppe erstellt");
							Platform.runLater(() -> {
								goToChatRoom();
							});
						} else {
							//TODO Fehlermeldung anzeigen
							serviceLocator.getLogger().info("Name bereits reserviert");
						}
						Client.getClient().removeMsgListener(this);
					}
				}
			}
			
		});

		Client.getClient().send(createChatroom);
		
	
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

