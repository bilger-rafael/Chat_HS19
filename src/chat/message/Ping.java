package chat.message;

import chat.commonClasses.Client;

public class Ping extends Message {
	/**
	 * The data may optionally contain a token
	 */
	public Ping() {
		super(new String[]{"Ping", Client.getClient().getToken()});
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
	}

}
