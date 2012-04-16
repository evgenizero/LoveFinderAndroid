package com.neya.love.finder.requests;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neya.love.finder.Menu;
import com.neya.love.finder.UserInfo;
import com.neya.love.finder.data.User;

import android.content.Context;
import android.content.Intent;

public class GetUserRequest extends LoveFinderHttpGetRequest {

	public GetUserRequest(Context context, String customerId) {
		super(context, null);
		
		execute("customerInfo?" + "customerId=" + customerId);
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);

		cancelDialog();
		
		if(result != null) {
		
			ObjectMapper mapper = new ObjectMapper();
			
			Intent intent = new Intent(context, UserInfo.class);
			try {
				intent.putExtra("user", mapper.readValue(result.getString("user"), User.class));
				context.startActivity(intent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}