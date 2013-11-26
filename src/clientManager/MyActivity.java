package clientManager;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * @author Prashant Adesara
 * Display Activity with sending messages to server 
 * */
public class MyActivity
{
	
	public static void main(String[] args)
	{
		new MyActivity();
	}

    private TCPClient mTcpClient = null;
    private connectTask conctTask = null;
    
    public MyActivity()
    {
        String message = "test";

      //  mTcpClient = new TCPClient();

        // connect to the server
        conctTask = new connectTask();
        conctTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        
        mTcpClient.sendMessage("Android Client: "+ message);

    }
    
    /**
     * @author Prashant Adesara
     * receive the message from server with asyncTask  
     * */
    public class connectTask extends AsyncTask<String,String,TCPClient> {
        @Override
        protected TCPClient doInBackground(String... message) 
        {
        	System.out.println("TEST");
            //we create a TCPClient object and
            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() 
            {
            	
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) 
                {
                	System.out.println("TEST");

                	try
					{
                		//this method calls the onProgressUpdate
                		//publishProgress(message);
                		if(message!=null)
                		{
                			System.out.println("Return Message from Socket::::: >>>>> "+message);
                		}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
                }
            });
            mTcpClient.run();
            if(mTcpClient!=null)
            {
            	mTcpClient.sendMessage("Initial Message when connected with Socket Server");
            	
            }
            return null;
        }
    }     
}