package com.example.androidwargame;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String[] drawerListViewItems;
	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private ActionBarDrawerToggle actionBarDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get list items from strings.xml
		drawerListViewItems = getResources().getStringArray(R.array.items);
		// get ListView defined in activity_main.xml
		drawerListView = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		drawerListView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_listview_item, drawerListViewItems));

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
		
		drawerListView.setOnItemClickListener(new DrawerItemClickListener());
		

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
	 
	        }
	    }
	 
}