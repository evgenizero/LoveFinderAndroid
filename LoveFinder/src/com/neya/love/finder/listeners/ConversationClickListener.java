package com.neya.love.finder.listeners;

import com.neya.love.finder.MessagesActivity;
import com.neya.love.finder.data.Conversation;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ConversationClickListener implements OnItemClickListener {

	private Context context;
	
	public ConversationClickListener(Context context) {
		this.context = context;
	}
	
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(context, MessagesActivity.class);
		Conversation conversation = (Conversation) arg0.getItemAtPosition(arg2);
		intent.putExtra("conversation", conversation);
		context.startActivity(intent);
	}

}
