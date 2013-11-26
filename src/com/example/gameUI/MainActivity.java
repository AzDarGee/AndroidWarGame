package com.example.gameUI;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import clientManager.ClientManager;
import clientManager.TCPClient;

import com.example.androidwargame.R;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainActivity extends Activity {

	//TODO		
	private final String IP_ADDRESS = "10.69.221.97";
	private List<String> groupList;
	private List<String> childList;
	private Map<String, List<String>> optionCollection;
	private DrawerLayout drawerLayout;
	private ExpandableListView drawerListView;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private ClientManager clientManager;


	public void syncGuiChanges()
	{
		//update players

		//update attacks

		//update shop

		//update inventory

		//update equipped items
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String message = extras.getString("com.android.androidwargame.MESSAGE");
		setContentView(R.layout.activity_main);

		Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

		createGroupList();
		createCollection();                       

		clientManager = new ClientManager(this);
        clientManager.sendMessageToServer("#getClientID");

		// get ExpandableListView defined in activity_main.xml
		drawerListView = (ExpandableListView) findViewById(R.id.drawer_list);

		final ExpandableListAdapter drawerListAdapter = new ExpandableListAdapter(
				this, groupList, optionCollection);

		// Set the adapter for the list view
		drawerListView.setAdapter(drawerListAdapter);

		// App Icon
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		//Create ActionBarDrawerToggle
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
				drawerLayout, /* DrawerLayout object */
				R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
				R.string.drawer_open, /* "open drawer" description */
				R.string.drawer_close /* "close drawer" description */
				);

		// Set actionBarDrawerToggle as the DrawerListener
		drawerLayout.setDrawerListener(actionBarDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		// just styling option add shadow the right edge of the drawer
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		//drawerListView.setOnItemClickListener(new DrawerItemClickListener());


		drawerListView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final String selected = (String) drawerListAdapter.getChild(
						groupPosition, childPosition);
				Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
				.show();

				if(v instanceof TextView)
				{
					String message = ((TextView)v).getText().toString() + childPosition;
					clientManager.appendToCommandString(message);
				}
				
				return true;
			}
			//TODO	
		});

		final TextView textView = (TextView) findViewById(R.id.logView);
		Button endTurn = (Button) findViewById(R.id.button1);

		//new MyActivity();
		Log.d("MAINACTIVITY-->TEST ",textView.getText().toString());
		//                mTcpClient.sendMessage("Android Client: TEST"+ textView.getText().toString());

		endTurn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				clientManager.sendCommandStringToServer();
			}
		});             
	}



	private void createGroupList() {
		groupList = new ArrayList<String>();
		groupList.add("Attacks");
		groupList.add("Shop");
		groupList.add("Inventory");
		groupList.add("Equipped Items");
	}

	private void createCollection() {

		String[] attacks = { "Attack1", "Attack2",
				"Attack3","Attack4" };
		String[] shop = { "Buy1", "Buy2", "Buy3" };
		String[] inventory = { "Item1", "Item2", "Item 3"};
		String[] equippedItems = { "Item1", "Item2",
				"Item3", "Item4" };


		optionCollection = new LinkedHashMap<String, List<String>>();

		for (String options : groupList) {
			if (options.equals("Attacks")) {
				loadChild(attacks);
			} else if (options.equals("Shop")) {
				loadChild(shop);
			} else if (options.equals("Inventory")) {
				loadChild(inventory);
			} else if (options.equals("Equipped Items")){
				loadChild(equippedItems);
			} 

			optionCollection.put(options, childList);
		}
	}

	private void loadChild(String[] drawerOptions) {
		childList = new ArrayList<String>();
		for (String options : drawerOptions)
			childList.add(options);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		actionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// call ActionBarDrawerToggle.onOptionsItemSelected(), if it returns
		// true
		// then it has handled the app icon touch event
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			Toast.makeText(MainActivity.this, ((TextView)view).getText(), Toast.LENGTH_LONG).show();
			drawerLayout.closeDrawer(drawerListView);


			//			String message = ((TextView)view).getText().toString() + position;
			//			clientManager.appendToCommandString(message);


		}
	}
}