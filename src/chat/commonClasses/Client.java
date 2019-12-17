package chat.commonClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.Instant;
import java.util.Scanner;

import chat.ServiceLocator;
import chat.message.Message;

public class Client implements Sendable {
	private static Client client;
	private Socket socket;

	public static Client getClient() {
		if (client == null) {
			client = new Client();
		}
		return client;
	}

	private Client() {

		ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();

		String ipAddress = serviceLocator.getConfiguration().getOption("ServerUrl");
		int portNumber = Integer.parseInt(serviceLocator.getConfiguration().getOption("ServerPort"));

		boolean valid = false;
		
		try {
			socket = new Socket(ipAddress, portNumber);

			serviceLocator.getLogger().info("Connected");

			try (BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream())) {

				// Create thread to read incoming messages
				Runnable r = new Runnable() {
					@Override
					public void run() {
						while (true) {
							
							String msg;
							try {
								msg = socketIn.readLine();
								serviceLocator.getLogger().info("Received: " + msg);
								//TODO Einkommende Message verarbeiten (Events für jeden Message Typ (Result, MessageError, MessageText)
							} catch (IOException e) {
								break;
							}
							if (msg == null)
								break; // In case the server closes the socket
						}
					}
				};
				Thread t = new Thread(r);
				t.start();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					// we don't care
				}
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(Message msg) {
		// TODO Auto-generated method stub
		try {
			msg.send(socket);
		} catch (IOException e) {
			ServiceLocator.getServiceLocator().getLogger().warning("Server unreachable; logged out");
		}
	}

}
