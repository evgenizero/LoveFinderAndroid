package com.neya.love.finder.data;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class User implements Parcelable {
	private int customerId;
	private int status;
	private String username;
	private String password;
	private String email;
	private int age;
	private String country;
	private String city;
	private int isHidden;
	private int aboutMe;
	private String image;
	
	public User() {
	}

	public User(String username, int age, String country, String city) {
		this.username = username;
		this.age = age;
		this.country = country;
		this.city = city;
	}
	
	public User(String username, String password, String email, int age,
			String country, String city, int isHidden) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.country = country;
		this.city = city;
		this.isHidden = isHidden;
	}

	public User(int customerId, int status, String username, String password,
			String email, int age, String country, String city, int aboutMe,
			int isHidden) {
		this.customerId = customerId;
		this.status = status;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.country = country;
		this.city = city;
		this.aboutMe = aboutMe;
		this.isHidden = isHidden;
	}

	public User(Parcel src) {
		customerId = src.readInt();
		status = src.readInt();
		username = src.readString();
		password = src.readString();
		email = src.readString();
		age = src.readInt();
		country = src.readString();
		city = src.readString();
		isHidden = src.readInt();
		aboutMe = src.readInt();
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the aboutMe
	 */
	public int getAboutMe() {
		return aboutMe;
	}

	/**
	 * @param aboutMe
	 *            the aboutMe to set
	 */
	public void setAboutMe(int aboutMe) {
		this.aboutMe = aboutMe;
	}

	/**
	 * @return the isHidden
	 */
	public int getIsHidden() {
		return isHidden;
	}

	/**
	 * @param isHidden
	 *            the isHidden to set
	 */
	public void setIsHidden(int isHidden) {
		this.isHidden = isHidden;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(customerId);
		dest.writeInt(status);
		dest.writeString(username);
		dest.writeString(password);
		dest.writeString(email);
		dest.writeInt(age);
		dest.writeString(country);
		dest.writeString(city);
		dest.writeInt(aboutMe);
		dest.writeInt(isHidden);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {

		public User[] newArray(int size) {
			return null;
		}

		public User createFromParcel(Parcel source) {
			return new User(source);
		}
	};
}
