package com.neya.love.finder.listeners;

import com.neya.love.finder.Conversations;
import com.neya.love.finder.SearchUserActivity;
import com.neya.love.finder.data.App;
import com.neya.love.finder.data.Conversation;
import com.neya.love.finder.requests.GetConversationsRequest;
import com.neya.love.finder.requests.GetUserRequest;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnMenuItemClickListener implements OnItemClickListener {

	private Context context;

	public OnMenuItemClickListener(Context context) {
		this.context = context;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String selectedItem = (String) arg0.getItemAtPosition(arg2);

		if ("Profile".equals(selectedItem)) {
			new GetUserRequest(context, String.valueOf(App.get().getUserId()));
		} else if ("Search".equals(selectedItem)) {
			context.startActivity(new Intent(context, SearchUserActivity.class));
		} else if ("Messages".equals(selectedItem)) {
			Intent intent = new Intent(context, Conversations.class);
			context.startActivity(intent);
		}
	}

}
