package com.neya.love.finder;

import com.neya.love.finder.listeners.OnMenuItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Menu extends Activity {

	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		list = (ListView) findViewById(R.id.menu_list);

		String[] items = new String[] { "Profile", "Search", "Messages" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, items);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnMenuItemClickListener(this));
	}
	
}
