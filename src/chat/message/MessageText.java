package chat.message;

import chat.commonClasses.Client;

public class MessageText extends Message {

	private String userName;
	private String targetName;
	private String text;
	
	
	//Konstruktur der Array aus Strings entgegennimmt
	public MessageText(String[] s) {
		super(s);
		this.userName = s[1];
		this.targetName = s[2];
		this.text = s[3];
	
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
	}
	
}
