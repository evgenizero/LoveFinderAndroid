package com.neya.love.finder.requests;

import java.io.IOException;

import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.neya.love.finder.RegisterActivity;
import com.neya.love.finder.data.User;

import android.content.Context;
import android.widget.Toast;

public class RegisterUserRequest extends LoveFinderHttpPostRequest {

	public RegisterUserRequest(Context context, User user) {
		super(context, "Registering");

		ObjectMapper mapper = new ObjectMapper();

		try {
			nameValuePairs.add(new BasicNameValuePair("user", mapper
					.writeValueAsString(user)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		execute("register");
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);

		cancelDialog();

		if (result != null) {
			Toast.makeText(context, "Succesfully registered",
					Toast.LENGTH_SHORT).show();
			((RegisterActivity) context).finish();
		}
	}

}
