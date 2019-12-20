package chat.message;

/**
 * Login to an existing account. If successful, return an authentication token
 * to the client.
 */
public class Login extends Message {
	private String username;
	private String password;

	public Login(String username, String password) {
		super(new String[]{"Login", username, password});
		this.username = username;
		this.password = password;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
}
