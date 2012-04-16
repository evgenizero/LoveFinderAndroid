package com.neya.love.finder;

import java.util.List;

import com.neya.love.finder.adapters.MessagesAdapter;
import com.neya.love.finder.data.App;
import com.neya.love.finder.data.Conversation;
import com.neya.love.finder.data.Message;
import com.neya.love.finder.requests.GetMessagesRequest;
import com.neya.love.finder.requests.SendMessageRequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MessagesActivity extends Activity {

	private ListView messagesListView;
	private Conversation conversation;

	private EditText messageToSend;

	private int receiverId;
	
	private MessagesAdapter messageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);

		findViews();

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			conversation = extras.getParcelable("conversation");
			receiverId = conversation.getUserId();
			new GetMessagesRequest(this, receiverId);
		}

	}

	private void findViews() {
		messagesListView = (ListView) findViewById(R.id.messages_list);
		messageToSend = (EditText) findViewById(R.id.messages_text);
	}

	public void onSendMessageClick(View v) {
		String messageText = messageToSend.getText().toString();
		if (!"".equals(messageText)) {
			new SendMessageRequest(this, new Message(messageText, App.get()
					.getUserId(), receiverId));
		} else {
			Toast.makeText(this, "Message text must not be empty",
					Toast.LENGTH_SHORT).show();
		}

	}

	public void processMessages(List<Message> messages) {
		messageAdapter = new MessagesAdapter(this, messages,
				conversation);
		messagesListView.setAdapter(messageAdapter);
	}
	
	public void notifyMessageChanges(List<Message> messages) {
		messageAdapter.setMessages(messages);
		messageAdapter.notifyDataSetChanged();
	}
}
