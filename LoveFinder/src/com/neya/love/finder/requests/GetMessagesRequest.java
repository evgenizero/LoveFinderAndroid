package com.neya.love.finder.requests;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;

import com.neya.love.finder.Conversations;
import com.neya.love.finder.MessagesActivity;
import com.neya.love.finder.data.App;
import com.neya.love.finder.data.Conversation;
import com.neya.love.finder.data.Message;

import android.content.Context;

public class GetMessagesRequest extends LoveFinderHttpGetRequest {

	public GetMessagesRequest(Context context, int senderId) {
		super(context, "Loading messages");

		execute("messages?senderId=" + String.valueOf(senderId)
				+ "&receiverId=" + String.valueOf(App.get().getUserId()));
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		
		cancelDialog();

		if (result != null) {

			try {
				ObjectMapper mapper = new ObjectMapper();
				List<Message> messages = mapper.readValue(
						result.getString("messages"),
						new TypeReference<List<Message>>() {
						});
				((MessagesActivity) context).processMessages(messages);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
