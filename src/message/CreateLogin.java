package message;



/**
 * Create a completely new login. After creating an account, the user must still
 * login!
 */
public class CreateLogin extends Message {
	private String username;
	private String password;

	public CreateLogin(String[] data) {
		super(data);
		this.username = data[1];
		this.password = data[2];
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}



}
