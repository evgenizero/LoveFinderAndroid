package com.neya.love.finder.adapters;

import android.view.View;
import android.widget.TextView;

import com.neya.love.finder.R;
import com.neya.love.finder.data.User;

public class UserInfoAdapter {
	private User user;
	private View v;
	
	public UserInfoAdapter(User user, View v) {
		this.user = user;
		this.v = v;
	}
	
	public void inflate() {
		((TextView)v.findViewById(R.id.user_info_username)).setText(user.getUsername());
		((TextView)v.findViewById(R.id.user_info_email)).setText(user.getEmail());
		((TextView)v.findViewById(R.id.user_info_country)).setText(user.getCountry());
		((TextView)v.findViewById(R.id.user_info_city)).setText(user.getCity());
		((TextView)v.findViewById(R.id.user_info_age)).setText(String.valueOf(user.getAge()));
	}
}
