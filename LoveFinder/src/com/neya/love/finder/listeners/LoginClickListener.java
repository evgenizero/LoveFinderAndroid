package com.neya.love.finder.listeners;

import com.neya.love.finder.R;
import com.neya.love.finder.requests.LoginUser;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginClickListener implements OnClickListener {

	private View layout;
	private Context context;
	
	public LoginClickListener(Context context, View v) {
		this.context = context;
		this.layout = v;
	}
	
	public void onClick(View v) {
		
		String username = ((EditText)layout.findViewById(R.id.username_edit_view)).getText().toString();
		String password = ((EditText)layout.findViewById(R.id.password_edit_view)).getText().toString();
	
		System.out.println(username + " " + password);
		
		if(!"".equals(username) && !"".equals(password)) {
			new LoginUser(context, username, password);
		} else {
			Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show();
		}
	}

}
