// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package oscf;

import java.io.*;


public class ChatClient extends AbstractClient
{

	ChatIF clientUI;
	String loginId; //String to hold the users login Id

	/**
	 *  Constructor now takes loginId as a parameter as well
	 */
	public ChatClient(String loginId, String host, int port, ChatIF clientUI)
			throws IOException {
		super(host, port);
		this.clientUI = clientUI;
		this.loginId = loginId; //stores given login id in global variable
		openConnection();
		this.sendToServer("#login " + loginId); //sends the login ID to the server
	}

	public void handleMessageFromServer(Object msg) {
		clientUI.display(msg.toString());
	}

	public void handleMessageFromClientUI(String message) {
		try {
			sendToServer(message);
		} catch (IOException e) {
			clientUI.display("Could not send message to server.  Terminating client.");
			quit();
		}
	}

	/**
	 * New runCommand method. This takes in the message as a string 
	 * and responds appropriately based on the command given
	 */
	public void runCommand(String message) throws IOException {
		if (message.equals("#quit")) {
			System.out.println("Client quitting");
			System.exit(0); //exit on quit command
		} else if (message.contains("#logoff")) {
			System.out.println("Client logging off");
			closeConnection(); //close connection on logoff command
		} else if (message.contains("#sethost")) {

			/*
			 * Sets new host if not already connected
			 */
			if (!isConnected()) {
				try {
					String host = message.substring(message.indexOf(' ') + 1,
							message.length());
					host = host.trim();
					this.setHost(host);
					System.out.println("Hostname changed to: " + host);
				} catch (Exception e) {
					System.out.println("Invalid hostname");
				}
			} else {
				System.out.println("Please logoff before you set a new host");
			}

		} else if (message.contains("#setport")) {

			/*
			 * Sets new port if not already connected
			 */
			if (!isConnected()) {
				try {
					String port = message.substring(message.indexOf(' ') + 1,
							message.length());
					port = port.trim();
					int iPort = Integer.valueOf(port);
					this.setPort(iPort);
					System.out.println("Port changed to: " + port);
				} catch (Exception e) {
					System.out.println("Invalid port");
				}
			} else {
				System.out.println("Please logoff before you set a new port");
			}

		} else if (message.contains("#login")) {
			if (isConnected()) {
				System.out.println("Client is already logged in.");
			} else {
				openConnection(); //opens connection on login command
				this.sendToServer("#login " + loginId);
			}
		} else if (message.contains("#gethost")) {
			//Displays the current host in the console
			System.out.println("Current host is: " + this.getHost());
		} else if (message.contains("#getport")) {
			//Displays the current port in the console
			System.out.println("Current port is: " + this.getPort());
		} else {
			//Prints to console on invalid command
			System.out.println("Not a valid command");
		}
	}

	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	/**
	 * Calls connection closed when there is an exception
	 * Does not quit to allow the use of commands
	 */
	public void connectionException(Exception e) {
		connectionClosed();
	}

	/**
	 * Informs the user when the connection to the server is closed
	 */
	public void connectionClosed() {
		System.out.println("Lost connection to the server.");
	}

}
//End of ChatClient class
