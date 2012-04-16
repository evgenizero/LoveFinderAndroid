package com.neya.love.finder.requests;


import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;

import com.neya.love.finder.MessagesActivity;
import com.neya.love.finder.data.Message;

import android.content.Context;

public class SendMessageRequest extends LoveFinderHttpPostRequest {

	public SendMessageRequest(Context context, Message message) {
		super(context, "Message");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			nameValuePairs.add(new BasicNameValuePair("message", mapper.writeValueAsString(message)));
			execute("sendMessage");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		if(result != null) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				List<Message> messages = mapper.readValue(
						result.getString("messages"),
						new TypeReference<List<Message>>() {
						});
				((MessagesActivity) context).notifyMessageChanges(messages);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
