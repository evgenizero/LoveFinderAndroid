package com.neya.love.finder.requests;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;

import com.neya.love.finder.Conversations;
import com.neya.love.finder.data.Conversation;

import android.content.Context;

public class GetConversationsRequest extends LoveFinderHttpGetRequest {

	public GetConversationsRequest(Context context, String userId) {
		super(context, "Loading conversations");

		execute("conversations?id=" + userId);
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);

		cancelDialog();

		if (result != null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				List<Conversation> conversations = mapper.readValue(
						result.getString("conversations"),
						new TypeReference<List<Conversation>>() {
						});
				((Conversations)context).processConversations(conversations);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
