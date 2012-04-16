package com.neya.love.finder.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Message {
	
	private String message;
	private String date;
	private int messageSenderId;
	private int messageReceiverId;
	
	public Message(String message, int sender, int receiver) {
		this.message = message;
		this.messageSenderId = sender;
		this.messageReceiverId = receiver;
	}
	
	public Message() {
		
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getMessageSenderId() {
		return messageSenderId;
	}

	public void setMessageSenderId(int sender) {
		this.messageSenderId = sender;
	}

	public int getMessageReceiverId() {
		return messageReceiverId;
	}

	public void setMessageReceiverId(int receiver) {
		this.messageReceiverId = receiver;
	}

}
