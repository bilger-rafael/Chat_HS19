package chat.message;

import chat.commonClasses.Client;

public class DeleteLogin extends Message {

	public DeleteLogin() {
		super(new String[] { "DeleteLogin", Client.getClient().getToken() });
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
	}

}
