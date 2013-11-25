package com.example.gameUI;

import com.example.androidwargame.R;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeScreen extends Activity {

	public final static String EXTRA_MESSAGE = "com.android.androidwargame.MESSAGE";
	private final static String TAG = "WelcomeScreen";
	private EditText server;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);
		server = (EditText) findViewById(R.id.editText1);
	}

	public void proceed(View view) {

		String serverMSG = server.getText().toString();
		Intent i = new Intent(this, MainActivity.class);
		
		switch (view.getId()) {

		case (R.id.endTurnButton): // if its button1 that is clicked
		{
			if (serverMSG == "") {
				Toast.makeText(WelcomeScreen.this, "Enter Server Address 2!",
						Toast.LENGTH_LONG).show();
				
			} else if(serverMSG != "") {
		
				i.putExtra(EXTRA_MESSAGE, "1");
				i.putExtra(EXTRA_MESSAGE, serverMSG);
				Log.d(TAG, "Chose Button 1 --> ASH");
				startActivity(i);
			}
		}
		break;
		
		case (R.id.button2): {
			if (serverMSG == "") {
				Toast.makeText(WelcomeScreen.this, "Enter Server Address 3!",
						Toast.LENGTH_LONG).show();
				break;
			} else if(serverMSG != "") {
				
				i.putExtra(EXTRA_MESSAGE, "2");
				i.putExtra(EXTRA_MESSAGE, serverMSG);
				Log.d(TAG, "Chose Button 2 --> JOSH");
				startActivity(i);
				
			}
		}
		break;
		
		}
		
		Toast.makeText(WelcomeScreen.this, String.valueOf(view.getId()),
				Toast.LENGTH_LONG).show();
		

	}

}
