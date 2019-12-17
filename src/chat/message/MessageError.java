package chat.message;

import chat.commonClasses.Client;

public class MessageError extends Message {

	public MessageError() {
		super(new String[] {"MessageError", "Invalid command"});		
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
	}
	
}
