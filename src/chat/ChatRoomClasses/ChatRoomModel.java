package chat.ChatRoomClasses;

import chat.JavaFX_App_Template;
import chat.ServiceLocator;
import chat.abstractClasses.Model;
import chat.commonClasses.Client;
import chat.commonClasses.MessageListener;
import chat.message.CreateLogin;
import chat.message.ListChatrooms;
import chat.message.Message;
import chat.message.Result;
import chat.message.ResultType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatRoomModel extends Model {
	ServiceLocator serviceLocator;
	public ObservableList<String> chatrooms = FXCollections.observableArrayList();

	public ChatRoomModel() {
		super();

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application model initialized");

		refreshChatrooms();
	}

	public void refreshChatrooms() {

		ListChatrooms listChatrooms = new ListChatrooms();

		Client.getClient().addMsgListener(new MessageListener() {
			@Override
			public void receive(Message msg) {
				if (msg instanceof Result) {
					Result r = (Result) msg;
					if (r.getType() == ResultType.List) {
						if (r.getBoolean()) {
							serviceLocator.getLogger().info("Chatroom Liste gelesen");
							synchronized (ChatRoomModel.this.chatrooms) {
								ChatRoomModel.this.chatrooms = FXCollections.observableArrayList(r.getList());
							}
						} else {
							// TODO Fehlermeldung anzeigen
							serviceLocator.getLogger().info("Token ungültig");
						}
						Client.getClient().removeMsgListener(this);
					}
				}
			}

		});

		Client.getClient().send(listChatrooms);

	}

}
