package chat.message;

import chat.commonClasses.Client;

/**
 * Add a user as a member of a chatroom.
 */
public class JoinChatroom extends Message {
	private String chatName;

	public JoinChatroom(String chatName) {
		super(new String[] { "JoinChatroom", Client.getClient().getToken(), chatName ,Client.getClient().getUsername() });
		this.chatName = chatName;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
}
