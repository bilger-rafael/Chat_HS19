package chat.commonClasses;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import chat.ServiceLocator;
import chat.message.Message;
import chat.message.MessageError;
import chat.message.MessageText;
import chat.message.Result;

public class Client implements Sendable {
	private static Client client;
	private Socket socket;
	private List<MessageListener> msgListeners = new ArrayList<>();

	public void addMsgListener(MessageListener msgListener) {
		this.msgListeners.add(msgListener);
	}
	
	public void removeMsgListener(MessageListener msgListener) {
		this.msgListeners.remove(msgListener);
	}	

	public static Client getClient() {
		if (client == null) {
			ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();
			String ipAddress = serviceLocator.getConfiguration().getOption("ServerUrl");
			int portNumber = Integer.parseInt(serviceLocator.getConfiguration().getOption("ServerPort"));
			try {
				client = new Client(new Socket(ipAddress, portNumber));
				serviceLocator.getLogger().info("Connected");
			} catch (IOException e) {
				//TODO Error besser handeln, Programm abbrechen
				e.printStackTrace();
			} 
		}
		return client;
	}

	private Client(Socket socket) {
		
		this.socket = socket;
		
		_init_reader();

	}
	
	public Socket getSocket() {
		return this.socket;
	}

	private void _init_reader() {
		// Create thread to read incoming messages
		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						Message msg = Message.receive(socket);
						
						ServiceLocator.getServiceLocator().getLogger().info("Received: " + msg);
						// TODO Einkommende Message verarbeiten (Events für jeden Message Typ (Result,
						// MessageError, MessageText)
						
						for (MessageListener msgListener : Client.this.msgListeners) {
							msgListener.receive(msg);
						}
						
						if(msg instanceof Result) {
							//TODO Raise ResultRecived
						}
						if(msg instanceof MessageError) {
							//TODO Raise MessageErrorRecived
						}
						if(msg instanceof MessageText) {
							//TODO Raise MessageTextRecived
						}
					
					}
				} catch (Exception e) {
					ServiceLocator.getServiceLocator().getLogger().info("Client " + Client.this.getName() + " disconnected");
				}
			}
			

		};
		Thread t = new Thread(r);
		t.start();
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void send(Message msg) {
		try {
			msg.send(socket);
		} catch (IOException e) {
			ServiceLocator.getServiceLocator().getLogger().warning("Server unreachable; logged out");
		}
	}

}
