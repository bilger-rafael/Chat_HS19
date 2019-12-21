package chat.message;

import chat.commonClasses.Client;

public class SendMessage extends Message {
	private String destination;
	private String message;

	public SendMessage(String destination, String message) {
		super(new String[] { "SendMessage", Client.getClient().getToken(), destination, message });
		this.destination = destination;
		this.message = message;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub

	}

}