package chat.ChatClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;
import chat.commonClasses.Client;
import chat.commonClasses.MessageListener;
import chat.message.JoinChatroom;
import chat.message.Login;
import chat.message.Message;
import chat.message.Result;
import chat.message.ResultType;
import javafx.application.Platform;

public class ChatModel extends Model {
	ServiceLocator serviceLocator;
	String chatName;

	public ChatModel(String chatName) {
		super();
		this.chatName = chatName;
		
		connect();

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application model initialized");
	}
	
	private void connect() {
		JoinChatroom joinChatroom = new JoinChatroom(chatName);
		
		Client.getClient().addMsgListener(new MessageListener() {
			@Override
			public void receive(Message msg) {
				if (msg instanceof Result) {
					Result r = (Result) msg;
					if (r.getType() == ResultType.Simple) {
						if (r.getBoolean()) {
							serviceLocator.getLogger().info("Chatroom beigetreten");
						} else {
							//TODO Fehlermeldung anzeigen
							serviceLocator.getLogger().info("Chatroom beitreten fehlgeschlagen");
						}
						Client.getClient().removeMsgListener(this);
					}
				}
			}
			
		});

		Client.getClient().send(joinChatroom);
	}

}
