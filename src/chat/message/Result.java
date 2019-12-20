package chat.message;

import java.util.ArrayList;

public class Result extends Message {
	private ResultType type;
	private boolean bool;
	
	//Konstruktur der Array aus Strings entgegennimmt
	public Result(String[] s) {
		super(s);
		
		this.bool = Boolean.parseBoolean(s[1]);
/*
 * TODO
 Boolean 
 Boolean Token 
 Boolean List
 
 True if the command succeeded, otherwise false
 After a successful login, also returns the authentication token 
 When a list is requested, also returns list results
 */
		
		
	}
	
	/**
	 * This message type does no processing at all
	 */
	@Override
	public void process() {
		// TODO Auto-generated method stub	
	}

	public ResultType getType() {
		return type;
	}
	
	public boolean getBoolean() {
		return bool;
	}
}
