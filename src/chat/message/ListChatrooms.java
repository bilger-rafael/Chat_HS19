package chat.message;

import chat.commonClasses.Client;


public class ListChatrooms extends Message {
	
	public ListChatrooms() {
		super(new String[]{"ListChatrooms", Client.getClient().getToken()});
	}


	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
}
