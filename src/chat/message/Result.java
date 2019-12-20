package chat.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Result extends Message {
	private ResultType type;
	private boolean bool;
	private List<String> list;
	private String token;

	// Konstruktur der Array aus Strings entgegennimmt
	public Result(String[] s) {
		super(s);
		
		if (s.length > 1) {
			this.type = ResultType.Simple;
			this.bool = Boolean.parseBoolean(s[1]);
		}
		
		if (s.length > 2) {
			this.type = ResultType.Token;
			this.token = s[2];
		}
		
		if (s.length > 3) {
			this.type = ResultType.List;
			this.token = null;
			this.list = Arrays.asList(s).stream().skip(2).collect(Collectors.toList());
		}

	}

	// Konstruktor für Array + Arraylist
	public Result(String[] s, ArrayList<String> elements) {
		super(s);
		try {
			this.list = elements;
			this.bool = Boolean.parseBoolean(s[1]);
			this.type = ResultType.List;
		} catch (Exception e) {

		}

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

	public String getToken() {
		return token;
	}

	public List<String> getList() {
		return list;
	}

}
