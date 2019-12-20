package chat.message;

import chat.commonClasses.Client;

public class CreateChatroom extends Message {
	private String name;
	private boolean isPublic;

	public CreateChatroom(String name, boolean isPublic) {
		super(new String[] { "CreateChatroom", Client.getClient().getToken(), name, Boolean.toString(isPublic) });
		this.name = name;
		this.isPublic = isPublic;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
	}
}