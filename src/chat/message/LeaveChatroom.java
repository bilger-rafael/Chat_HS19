package chat.message;

import chat.commonClasses.Client;

/**
 * Remove a user as a member of a chatroom
 */
public class LeaveChatroom extends Message {
	private String chatName;

	public LeaveChatroom(String chatName) {
		super(new String[] { "LeaveChatroom", Client.getClient().getToken(), chatName ,Client.getClient().getUsername() });
		this.chatName = chatName;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
}
