package com.neya.love.finder;

import com.neya.love.finder.data.User;
import com.neya.love.finder.requests.SearchUsersRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchUserActivity extends Activity {

	private EditText userName, age, country, city;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_user);

		findView();
	}

	private void findView() {
		userName = (EditText) findViewById(R.id.search_user_username);
		age = (EditText) findViewById(R.id.search_user_age);
		country = (EditText) findViewById(R.id.search_user_country);
		city = (EditText) findViewById(R.id.search_user_city);
	}

	public void onSearchClick(View v) {
		String userAge = age.getText().toString();
		if ("".equals(userAge)) {
			userAge = "0";
		}
		User user = new User(userName.getText().toString(),
				Integer.valueOf(userAge), country.getText().toString(), city
						.getText().toString());
		Intent intent = new Intent(this, UsersList.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}
}
