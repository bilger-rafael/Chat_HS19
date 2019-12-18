package chat.message;

import chat.commonClasses.Client;

public class MessageError extends Message {

	public MessageError() {
		super(new String[] {"MessageError", "Invalid command"});		
	}

	//Konstruktur der Array aus Strings entgegennimmt
	public MessageError(String[] s) {
		super(s);
		
	}
	@Override
	public void process() {
		// TODO Auto-generated method stub
	}
	
}
