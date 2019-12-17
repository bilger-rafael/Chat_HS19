package chat.message;

import chat.commonClasses.Client;

public class MessageText extends Message {

	public MessageText(String name, String target, String message) {
		super(new String[] {"MessageText", name, target, message});		
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
	}
	
}
