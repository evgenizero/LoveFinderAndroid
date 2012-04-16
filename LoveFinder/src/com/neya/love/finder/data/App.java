package com.neya.love.finder.data;

import android.app.Application;

public class App extends Application {

	private static App sInstance;

	private int userId;
	
	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
	}

	public static App get() {
		return sInstance;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}
	
}