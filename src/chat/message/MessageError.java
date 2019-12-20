package chat.message;

import chat.commonClasses.Client;

public class MessageError extends Message {
	private String errorContent;
	

	//Konstruktur der Array aus Strings entgegennimmt
	public MessageError(String[] s) {
		super(s);
		this.errorContent =s[1];
	}
	
	@Override
	public void process() {
		// TODO Auto-generated method stub
	}
	
}
