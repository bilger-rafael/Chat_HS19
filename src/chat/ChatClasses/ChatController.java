package chat.ChatClasses;

import chat.ServiceLocator;
import chat.ChatRoomClasses.ChatRoomModel;
import chat.ChatRoomClasses.ChatRoomView;
import chat.LoginClasses.LoginModel;
import chat.LoginClasses.LoginView;
import chat.abstractClasses.Controller;
import chat.commonClasses.Client;
import chat.commonClasses.MessageListener;
import chat.message.JoinChatroom;
import chat.message.Message;
import chat.message.MessageText;
import chat.message.Result;
import chat.message.ResultType;
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
				Platform.exit();
			}
		});

		view.chatCenter.setItems(model.messages);

		// Action für Send
		view.getSendButton().setOnAction(e -> sendMessage());
		
		// Action für Clear
		view.getClearButton().setOnAction(e -> clearMessage());
		
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

}