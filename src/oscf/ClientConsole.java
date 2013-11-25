package oscf;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

/**
 *	Ash Darji - 5595821
 *	Josh
 */
public class ClientConsole implements ChatIF {

	final public static int DEFAULT_PORT = 5555;

	ChatClient client;

	/**
	 * Added loginId as a parameter to the constructor
	 */
	public ClientConsole(String loginId, String host, int port) {
		try {
			client = new ChatClient(loginId, host, port, this); //now has loginId
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!"
					+ " Terminating client.");
			System.exit(1);
		}
	}

	public void accept() {
		try {
			BufferedReader fromConsole = new BufferedReader(
					new InputStreamReader(System.in));
			String message;

			while (true) {
				message = fromConsole.readLine();
				//Checks whether or not the message is a command
				if (message.charAt(0) == '#') {
					client.runCommand(message); //method to run commands
				} else {
					client.handleMessageFromClientUI(message);
				}
			}
		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!");
		}
	}

	public void display(String message) {
		System.out.println(message);
	}

	public static void main(String[] args) {
		String host = "";
		int port = 0;
		String loginId = ""; //login ID variable
		
		
		//First argument is now the login ID
		try {
			loginId = "Ash";
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("A valid login ID is required. Quitting.");
			System.exit(0);
		}

		try {
			host = args[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Using default host localhost.");
			host = "localhost";
		}

		try {
			port = Integer.valueOf(args[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Using default port 5555.");
			port = DEFAULT_PORT;
		} catch (NumberFormatException e) {
			System.out.println("Invalid port. Using default port 5555.");
			port = DEFAULT_PORT;
		}
		
		ClientConsole chat = new ClientConsole(loginId, host, port);
		chat.accept();
	}
}
// End of ConsoleChat class
