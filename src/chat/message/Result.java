package chat.message;

import java.util.ArrayList;

public class Result extends Message {

	/**
	 * This constructor is used by most messages
	 */
	public Result(boolean result) {
		super(new String[] {"Result", Boolean.toString(result)});		
	}
	
	//Konstruktur der Array aus Strings entgegennimmt
	public Result(String[] s) {
		super(s);
		
	}
	
	/**
	 * After a successful login, the result also contains an authentication token
	 */
	public Result(boolean result, String token) {
		super(new String[] {"Result", Boolean.toString(result), token});		
	}
	
	/**
	 * When a list is requested, the result includes all list values
	 */
	public Result(boolean result, ArrayList<String> list) {
		super(new String[] {"Result", Boolean.toString(result)}, list);		
	}
	
	/**
	 * This message type does no processing at all
	 */
	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
}
