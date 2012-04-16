package com.neya.love.finder.adapters;

import java.util.List;

import com.neya.love.finder.R;
import com.neya.love.finder.data.Conversation;
import com.neya.love.finder.data.Message;
import com.neya.love.finder.data.User;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessagesAdapter extends BaseAdapter {
	private List<Message> messages;
	private Context context;
	private Conversation conversation;
	
	public class ViewHolder {
		TextView sender, date, message;
	}

	public MessagesAdapter(Context context, List<Message> messages,
			Conversation conversation) {
		this.context = context;
		this.messages = messages;
		this.conversation = conversation;
	}

	public int getCount() {
		return messages.size();
	}

	public Object getItem(int position) {
		return messages.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null || convertView.getTag() == null) {
			convertView = View.inflate(context, R.layout.message_holder, null);
			holder = new ViewHolder();

			holder.sender = (TextView) convertView
					.findViewById(R.id.message_holder_sender);
			holder.date = (TextView) convertView
					.findViewById(R.id.message_holder_date);
			holder.message = (TextView) convertView
					.findViewById(R.id.message_holder_message);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Message message = (Message) getItem(position);

		if (conversation.getUserId() == message.getMessageSenderId()) {
			holder.sender.setText(conversation.getUserName() + ":");
		} else {
			holder.sender.setText("Me" + ":");
		}

		holder.date.setText("Sent: " + message.getDate());
		holder.message.setText(message.getMessage());

		convertView.requestLayout();
		return convertView;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
		notifyDataSetChanged();
	}

}
