package chat.message;

import java.util.ArrayList;

import chat.commonClasses.Client;


public class ListChatroomUsers extends Message {
	private String token;
	private String chatName;

	public ListChatroomUsers(String chatName) {
		super(new String[]{"ListChatroomUsers", Client.getClient().getToken(), chatName});
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
}


