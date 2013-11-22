package com.example.gameUI;

import android.app.Activity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.androidwargame.R;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

public class ExpandableList extends Activity {
	
	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> optionCollection;
	ExpandableListView expListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		createGroupList();

		createCollection();

		expListView = (ExpandableListView) findViewById(R.id.drawer_list);
		
		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
				this, groupList, optionCollection);
		expListView.setAdapter(expListAdapter);

		// setGroupIndicatorToRight();

		expListView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final String selected = (String) expListAdapter.getChild(
						groupPosition, childPosition);
				Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
						.show();

				return true;
			}
		});
	}

	private void createGroupList() {
		groupList = new ArrayList<String>();
		groupList.add("Attacks");
		groupList.add("Shop");
		groupList.add("Inventory");
		groupList.add("Equipped Items");
		groupList.add("Log");
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
			} else {
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

//	private void setGroupIndicatorToRight() {
//		/* Get the screen width */
//		DisplayMetrics dm = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//		int width = dm.widthPixels;
//
//		expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
//				- getDipsFromPixel(5));
//	}
//
//	// Convert pixel to dip
//	public int getDipsFromPixel(float pixels) {
//		// Get the screen's density scale
//		final float scale = getResources().getDisplayMetrics().density;
//		// Convert the dps to pixels, based on density scale
//		return (int) (pixels * scale + 0.5f);
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
