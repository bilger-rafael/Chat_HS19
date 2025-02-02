package chat.ChatRoomClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;
import chat.commonClasses.Client;
import chat.commonClasses.MessageListener;
import chat.message.DeleteLogin;
import chat.message.ListChatrooms;
import chat.message.Logout;
import chat.message.Message;
import chat.message.Result;
import chat.message.ResultType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatRoomModel extends Model {
	ServiceLocator serviceLocator;
	public volatile ObservableList<String> chatrooms = FXCollections.observableArrayList();

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
							Platform.runLater(() -> {
								ChatRoomModel.this.chatrooms.setAll(r.getList());
							});

							serviceLocator.getLogger().info("Chatroom Liste gelesen");
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

	public void logout() {

		Logout logout = new Logout();

		Client.getClient().send(logout);

	}
	
	public void deleteUser() {

		DeleteLogin deleteLogin = new DeleteLogin();

		Client.getClient().send(deleteLogin);

	}

}
