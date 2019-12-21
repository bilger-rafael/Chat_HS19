package chat.message;

import chat.commonClasses.Client;

public class ChangePassword extends Message {
	private String password;

	public ChangePassword(String password) {
		super(new String[] { "ChangePassword", Client.getClient().getToken(), password });
		this.password = password;
	}


	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

}
