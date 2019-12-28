package chat.ChatClasses;

import chat.ServiceLocator;
import chat.abstractClasses.Model;
import chat.commonClasses.Client;
import chat.commonClasses.MessageListener;
import chat.message.JoinChatroom;
import chat.message.LeaveChatroom;
import chat.message.ListChatroomUsers;
import chat.message.Message;
import chat.message.MessageText;
import chat.message.Result;
import chat.message.ResultType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatModel extends Model {
	ServiceLocator serviceLocator;
	String chatName;
	public volatile ObservableList<String> messages = FXCollections.observableArrayList();
	public volatile ObservableList<String> users = FXCollections.observableArrayList();
	private volatile boolean connected = true;
	private Thread updater;

	public ChatModel(String chatName) {
		super();
		this.chatName = chatName;

		connect();

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application model initialized");
	}
	
	protected void reconnect() {
		connected = true;
		JoinChatroom joinChatroom = new JoinChatroom(chatName);
		Client.getClient().addMsgListener(new MessageListener() {
			@Override
			public void receive(Message msg) {
				if (msg instanceof Result) {
					Result r = (Result) msg;
					if (r.getType() == ResultType.Simple) {
						if (r.getBoolean()) {
							serviceLocator.getLogger().info("Chatroom wieder beigetreten");
							
						} else {
							// TODO Fehlermeldung anzeigen
							serviceLocator.getLogger().info("Chatroom beitreten fehlgeschlagen");
							
							connected = false;
						}
						Client.getClient().removeMsgListener(this);
					}
				}
			}

		});
		Client.getClient().send(joinChatroom);
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
							
							Client.getClient().addMsgListener(createMessageListener());

							Client.getClient().addMsgListener(createOnlineUserListener());
							
							startUserUpdater();
						} else {
							// TODO Fehlermeldung anzeigen
							serviceLocator.getLogger().info("Chatroom beitreten fehlgeschlagen");
							
							connected = false;
						}
						Client.getClient().removeMsgListener(this);
					}
				}
			}

		});

		Client.getClient().send(joinChatroom);
	}

	protected void disconnect() {
		connected = false;

		LeaveChatroom leaveChatroom = new LeaveChatroom(chatName);

		Client.getClient().send(leaveChatroom);
	}

	private MessageListener createMessageListener() {
		return new MessageListener() {
			@Override
			public void receive(Message msg) {
				if (msg instanceof MessageText) {
					MessageText mt = (MessageText) msg;
					Platform.runLater(() -> {
						if (mt.getTargetName().equals(chatName)) {
							messages.add(mt.getUserName() + ":" + mt.getText());
						}
					});
				}
			}
		};

	}
	
	private void startUserUpdater() {
		ListChatroomUsers chatRoomUsers = new ListChatroomUsers(chatName);

		// Create thread to update Users periodically
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (connected) {
					Client.getClient().send(chatRoomUsers);

					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		updater = new Thread(r);
		updater.setDaemon(true);
		updater.setName("ChatRoomUsers" + chatName);
		updater.start();
	}

	private MessageListener createOnlineUserListener() {
		return new MessageListener() {
			@Override
			public void receive(Message msg) {
				if (msg instanceof Result) {
					Result r = (Result) msg;
					if (r.getType() == ResultType.List) {
						if (r.getBoolean()) {
							Platform.runLater(() -> {
								ChatModel.this.users.setAll(r.getList());
							});
							serviceLocator.getLogger().info("Userliste gelesen");
						}
					}
				}
			}
		};

	}

}