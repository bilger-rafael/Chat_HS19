package chat.message;

/**
 * Create a completely new login. 
 */
public class CreateLogin extends Message {
	private String username;
	private String password;
	
	public CreateLogin(String username, String password) {
		super(new String[]{"CreateLogin", username, password});
		this.username = username;
		this.password = password;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
	}

}


