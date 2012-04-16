package com.neya.love.finder.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Conversation implements Parcelable {
	private String userName;
	private int userId;

	public Conversation(int userId) {
		this.userId = userId;
	}

	public Conversation() {

	}

	public Conversation(Parcel in) {
		userName = in.readString();
		userId = in.readInt();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userName);
		dest.writeInt(userId);
	}

	public static final Parcelable.Creator<Conversation> CREATOR = new Parcelable.Creator<Conversation>() {
		public Conversation createFromParcel(Parcel in) {
			return new Conversation(in);
		}

		public Conversation[] newArray(int size) {
			return null;
		}
	};
}
