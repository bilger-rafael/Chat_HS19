package chat.message;

/**
 * Create a completely new login. 
 */
public class CreateLogin extends Message {
	private String username;
	private String password;

	public CreateLogin(String[] data) {
		super(data);
		this.username = data[0];
		this.password = data[1];
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
	}

}


