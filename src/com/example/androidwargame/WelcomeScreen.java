package com.example.androidwargame;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class WelcomeScreen extends Activity{
	
	public final static String EXTRA_MESSAGE = "com.android.androidwargame.MESSAGE";
	private final static String TAG = "WelcomeScreen";
	
	 @Override
     protected void onCreate(Bundle savedInstanceState) { 
             super.onCreate(savedInstanceState);
             setContentView(R.layout.welcome_screen);      
	 }
	 
	 public void proceed(View view) {
		 
		 switch(view.getId()) //get the id which is an int
		    {
		     case R.id.button1 : //if its button1 that is clicked
		         Intent i= new Intent(this,MainActivity.class);
		         i.putExtra(EXTRA_MESSAGE, "1");
		         Log.d(TAG, "Chose Button 1 --> ASH");
		         startActivity(i);
		        // use intents to pass information to secondActivity and display the image there
		      break;
		      case R.id.button2 :
		    	  Intent in= new Intent(this,MainActivity.class);
			      in.putExtra(EXTRA_MESSAGE, "2");
			      Log.d(TAG, "Chose Button 2 --> JOSH");
			      startActivity(in);
		      break;
		    }
		
		
		 
	 }
	 
	 
	 
}
