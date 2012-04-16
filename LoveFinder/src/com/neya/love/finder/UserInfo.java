package com.neya.love.finder;

import com.neya.love.finder.adapters.UserInfoAdapter;
import com.neya.love.finder.data.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class UserInfo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v = View.inflate(this, R.layout.user_info, null);
		setContentView(v);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			new UserInfoAdapter((User) extras.getParcelable("user"), v).inflate();
		}
	}
}



















