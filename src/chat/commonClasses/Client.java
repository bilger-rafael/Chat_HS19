package chat.commonClasses;

import java.io.IOException;
import java.net.Socket;
import chat.ServiceLocator;
import chat.message.Message;

public class Client implements Sendable {
	private static Client client;
	private Socket socket;

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
