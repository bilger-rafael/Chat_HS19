package chat.message;

import java.util.ArrayList;

public class Result extends Message {
	private ResultType type;
	private boolean bool;
	private ArrayList list;
	private String token;
	
	//Konstruktur der Array aus Strings entgegennimmt
	public Result(String[] s) {
		super(s);
		try {
			this.token=s[2];
			this.bool = Boolean.parseBoolean(s[1]);
			this.type = ResultType.Token;
		}catch (Exception e){
			this.bool = Boolean.parseBoolean(s[1]);
			this.type = ResultType.Simple;
		}
		
	}
		
		
	//Konstruktor für Array + Arraylist 
	public Result(String[] s, ArrayList<String> elements) {
		super(s);
		try {
			this.list =  elements;
			this.bool = Boolean.parseBoolean(s[1]);
			this.type = ResultType.List;
			}catch (Exception e) {
				
			}
		
	}
		
		
/*
 * TODO
 Boolean 
 Boolean Token 
 Boolean List
 
 True if the command succeeded, otherwise false
 After a successful login, also returns the authentication token 
 When a list is requested, also returns list results
 */
		
	
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
	
	public String getToken() {
		return token;
	}
	
	public ArrayList<String> getChatRooms() {
		return list;
	}
}
