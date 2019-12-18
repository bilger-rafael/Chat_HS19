package chat.message;

import chat.commonClasses.Client;

public class MessageText extends Message {

	public MessageText(String name, String target, String message) {
		super(new String[] {"MessageText", name, target, message});		
	}
	
	//Konstruktur der Array aus Strings entgegennimmt
	public MessageText(String[] s) {
		super(s);
		
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
	}
	
}
