package com.neya.love.finder;

import java.util.List;

import com.neya.love.finder.adapters.UsersAdapter;
import com.neya.love.finder.data.User;
import com.neya.love.finder.requests.SearchUsersRequest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class UsersList extends Activity {
	
	private ListView usersList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.users);
		
		usersList = (ListView) findViewById(R.id.users_list);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			User user = extras.getParcelable("user");
			new SearchUsersRequest(this, user);
		}
		
	}
	
	public void processResponse(List<User> users) {
		usersList.setAdapter(new UsersAdapter(this, users));
	}
}
