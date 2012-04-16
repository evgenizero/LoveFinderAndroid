package com.neya.love.finder.requests;

import java.io.IOException;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.neya.love.finder.RegisterActivity;
import com.neya.love.finder.UsersList;
import com.neya.love.finder.data.User;

import android.content.Context;
import android.widget.Toast;

public class SearchUsersRequest extends LoveFinderHttpPostRequest {

	public SearchUsersRequest(Context context, User user) {
		super(context, "Searching");

		ObjectMapper mapper = new ObjectMapper();
		try {
			nameValuePairs.add(new BasicNameValuePair("user", mapper.writeValueAsString(user)));
		}  catch (IOException e) {
			e.printStackTrace();
		}

		execute("searchUsers");
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);

		cancelDialog();

		if (result != null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				List<User> users = mapper.readValue(result.getString("users"),
						new TypeReference<List<User>>() {
						});
				((UsersList) context).processResponse(users);
				for (User user : users) {
					System.out.println(user.getUsername());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
