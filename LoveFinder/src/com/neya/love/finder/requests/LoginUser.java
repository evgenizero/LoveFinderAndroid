package com.neya.love.finder.requests;

import org.json.JSONException;
import org.json.JSONObject;

import com.neya.love.finder.Menu;
import com.neya.love.finder.data.App;

import android.content.Context;
import android.content.Intent;

public class LoginUser extends LoveFinderHttpGetRequest {

	public LoginUser(Context context, String username, String password) {
		super(context, "Logging");
		
		execute("logIn?username=" + username + "&password=" + password);
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);

		if (result != null) {
			try {
				App.get().setUserId(Integer.valueOf(result.getString("customer_id")));
				context.startActivity(new Intent(context, Menu.class));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	}
}
