package com.neya.love.finder.adapters;

import java.util.List;

import com.neya.love.finder.R;
import com.neya.love.finder.data.Conversation;
import com.neya.love.finder.data.User;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ConversationsAdapter extends BaseAdapter {
	private List<Conversation> conversations;
	private Context context;

	public class ViewHolder {
		TextView username;
	}

	public ConversationsAdapter(Context context, List<Conversation> conversations) {
		this.context = context;
		this.conversations = conversations;
	}

	public int getCount() {
		return conversations.size();
	}

	public Object getItem(int position) {
		return conversations.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null || convertView.getTag() == null) {
			convertView = View.inflate(context, R.layout.conversations_holder,
					null);
			holder = new ViewHolder();

			holder.username = (TextView) convertView
					.findViewById(R.id.conversation_holder_user);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Conversation conversation = (Conversation) getItem(position);

		holder.username.setText(conversation.getUserName());

		convertView.requestLayout();
		return convertView;
	}

}
