package oscf;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

public class EchoServer extends AbstractServer 
{
	
	final public static int DEFAULT_PORT = 5555;

	
	public EchoServer(int port) {
		super(port);
	}

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String message = (String) msg; //casts message to a String
		if (message.contains("#login")) {
			//Cuts login ID from the message and stores it in a variable
			String Id = message.substring(message.indexOf(' ') + 1,
					message.length());
			Id = Id.trim();
			client.setInfo("LoginId", Id); //sets the users ID
			this.sendToAllClients(Id + " has logged on"); //Inform all clients of the login
			System.out.println(Id + " has logged on"); //Prints user logon to server console
		} else {
			System.out.println("Message received: " + message + " from "
					+ client.getInfo("LoginId"));
			//Which user connected is now sent with the message
			this.sendToAllClients(client.getInfo("LoginId") + "> " + message); 
		}
	}


	protected void serverStarted() {
		System.out.println("Server listening for connections on port "
				+ getPort());
	}

	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	/**
	 * Prints to console that a client has connected
	 */
	protected void clientConnected(ConnectionToClient client) {
		System.out.println("Client " + client.getName() + " has connected.");
	}

	/**
	 * Prints to console and informs all users that a client has disconnected
	 */
	synchronized protected void clientDisconnected(ConnectionToClient client) {
		System.out.println("Client " + client.getInfo("LoginId")
				+ " has disconnected.");
		sendToAllClients("Client " + client.getInfo("LoginId")
				+ " has disconnected.");
	}

	/**
	 * Calls clientDisconnected when an exception is caught
	 */
	synchronized protected void clientException(ConnectionToClient client,
			Throwable exception) {
		clientDisconnected(client);
	}
	
	/**
	 * New runCommand method. This takes in the message as a string 
	 * and responds appropriately based on the command given
	 */
	public void runCommand(String message) throws IOException {
		if (message.equals("#quit")) {
			System.out.println("Server is quitting.");
			System.exit(0); //Shuts down the server
		} else if (message.contains("#stop")) {
			this.stopListening(); //Stops listening for connections
			this.sendToAllClients("WARNING - Server has stopped listening for connections");
		} else if (message.contains("#close")) {
			this.close(); //Closes all connections to clients
		} else if (message.contains("#setport")) {
			/*
			 * Sets new port if not already connected with clients
			 */
			if (this.getNumberOfClients() == 0) {
				try {
					String port = message.substring(message.indexOf(' ') + 1,
							message.length());
					port = port.trim();
					int iPort = Integer.valueOf(port);
					this.setPort(iPort);
					System.out.println("Port changed to: " + port);
				} catch (Exception e) {
					System.out.println("Invalid hostname");
				}
			} else {
				System.out.println("Please logoff before you set a new port");
			}
		} else if (message.contains("#start")) {
			this.listen(); //Starts listening for clients
		} else if (message.contains("#getport")) {
			//Displays the current port in the console
			System.out.println("The current port is: " + this.getPort());
		} else {
			//Prints to console on invalid command
			System.out.println("Not a valid command");
		}

	}

	public static void main(String[] args) {
		int port = 0;

		try {
			port = Integer.parseInt(args[0]);
		} catch (Throwable t) {
			port = DEFAULT_PORT;
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
		
		//ServerConsole is now created and begins accepting messages
		ServerConsole sc = new ServerConsole(sv);
		sc.accept();

	}


}
//End of EchoServer class
