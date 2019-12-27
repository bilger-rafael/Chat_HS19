package chat.ChatClasses;

import chat.commonClasses.Client;
import chat.message.ListChatroomUsers;

public class OnlineUserUpdater extends Thread {
	private ListChatroomUsers chatRoomUsers;
	
	public OnlineUserUpdater(ListChatroomUsers chatRoomUsers) {
		this.chatRoomUsers = chatRoomUsers;
		this.setDaemon(true);
	}
	
	@Override
	public void run() {
		while (true) {
			Client.getClient().send(this.chatRoomUsers);
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
			}
		}
	}
	

}
