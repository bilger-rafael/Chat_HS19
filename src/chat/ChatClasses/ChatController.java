package chat.ChatClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Controller;
import chat.commonClasses.Client;
import chat.message.SendMessage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ChatController extends Controller<ChatModel, ChatView> {
	ServiceLocator serviceLocator;

	public ChatController(ChatModel model, ChatView view) {
		super(model, view);

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				model.disconnect();
				Platform.exit();
			}
		});

		view.chatCenter.setItems(model.messages);
		
		view.userOnlineList.setItems(model.users);

		// Action für Send
		view.getSendButton().setOnAction(e -> sendMessage());
		
		// Action für Clear
		view.getClearButton().setOnAction(e -> clearMessage());
		
		// Action für Schliessen
		view.getCloseMenuItem().setOnAction(e -> closeView());
		
		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application controller initialized");
	}
	
	private void clearMessage() {
		view.getEntryTextField().setText("");
	}
	
	private void sendMessage() {
		String message = view.getEntryTextField().getText();
		
		if (message.isEmpty()){
			return;
		}
		
		SendMessage sendMessage = new SendMessage(model.chatName, message);

		Client.getClient().send(sendMessage);

		view.getEntryTextField().setText("");
	}
	
	private void closeView() {
		model.disconnect();
		view.stop();
	}

}