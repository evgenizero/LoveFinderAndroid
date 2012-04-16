package com.neya.love.finder.listeners;

import org.codehaus.jackson.node.BinaryNode;

import com.google.gson.Gson;
import com.neya.love.finder.R;
import com.neya.love.finder.data.User;
import com.neya.love.finder.requests.RegisterUserRequest;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterClickListener implements OnClickListener {

	private View layout;
	private Context context;
	private byte[] image;
	
	public RegisterClickListener(Context context, View v) {
		this.context = context;
		this.layout = v;
	}

	public void onClick(View v) {
		String username = ((EditText) layout
				.findViewById(R.id.register_username)).getText().toString();
		String password = ((EditText) layout
				.findViewById(R.id.register_password)).getText().toString();
		String email = ((EditText) layout.findViewById(R.id.register_email))
				.getText().toString();
		String age = ((EditText) layout.findViewById(R.id.register_age))
				.getText().toString();
		String country = ((EditText) layout.findViewById(R.id.register_country))
				.getText().toString();
		String city = ((EditText) layout.findViewById(R.id.register_city))
				.getText().toString();
		
		if ("".equals(username) || "".equals(password) || "".equals(email)
				|| "".equals(age) || "".equals(country) || "".equals(city)) {

			Toast.makeText(context, "Fille all the fields", Toast.LENGTH_SHORT).show();
		} else {
			User user = new User(username, password, email, Integer.valueOf(age),
					country, city, 0);
			user.setImage(new BinaryNode(image).getValueAsText());

			new RegisterUserRequest(context, user);
		}

	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
