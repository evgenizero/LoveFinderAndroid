package com.neya.love.finder;

import java.util.List;

import com.neya.love.finder.adapters.ConversationsAdapter;
import com.neya.love.finder.data.App;
import com.neya.love.finder.data.Conversation;
import com.neya.love.finder.listeners.ConversationClickListener;
import com.neya.love.finder.requests.GetConversationsRequest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Conversations extends Activity {

	private ListView conversations;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conversations);

		conversations = (ListView) findViewById(R.id.conversations_list);
		
		new GetConversationsRequest(this, String.valueOf(App.get()
				.getUserId()));
	}

	public void processConversations(List<Conversation> conversationsList) {
		conversations.setAdapter(new ConversationsAdapter(this,
				conversationsList));
		conversations.setOnItemClickListener(new ConversationClickListener(this));
	}
}
