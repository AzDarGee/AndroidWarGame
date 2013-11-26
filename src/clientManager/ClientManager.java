package clientManager;

import android.os.AsyncTask;

import com.example.gameUI.MainActivity;

public class ClientManager {

	private int clientID;
	private MainActivity mainActivity;
	private TCPClient mTcpClient;
	private String commandString = "#";

	public ClientManager(MainActivity mainActivity)
	{
		this.mainActivity = mainActivity;

		// connect to the server
		new connectTask().execute("");
	}

	public void sendMessageToServer(String message)
	{
		//sends the message to the server
		if (mTcpClient != null) {
			mTcpClient.sendMessage(message);
		}
	}

	public void sendCommandStringToServer()
	{
		sendMessageToServer(commandString);
		commandString = "#";
	}

	public class connectTask extends AsyncTask<String,String,TCPClient> {

		@Override
		protected TCPClient doInBackground(String... message) {

			//we create a TCPClient object and
			mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
				@Override
				//here the messageReceived method is implemented
				public void messageReceived(String message) {
					//this method calls the onProgressUpdate
					System.out.println("Client received message: " + message);
					publishProgress(message);
				}
			});
			mTcpClient.run();

			return null;
		}}	

	public void appendToCommandString(String command)
	{
		commandString = commandString + "#" + command;
	}
}

