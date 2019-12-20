package chat.commonClasses;

import chat.message.Message;

public interface MessageListener {
	
	void receive(Message msg);
	
}
