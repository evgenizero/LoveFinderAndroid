package com.neya.love.finder;

import com.neya.love.finder.listeners.LoginClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v = View.inflate(this, R.layout.main, null);
		setContentView(v);
		 ((Button) findViewById(R.id.login_button))
		 .setOnClickListener(new LoginClickListener(this, v));
		 
	}
	
	public void registerButtonPressed(View v) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}

}