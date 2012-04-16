package com.neya.love.finder.requests;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.neya.love.finder.utilities.Utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Toast;

/**
 * The base class for executing HTTP requests to the Bugzilla server
 * 
 * <li>
 * {@link #trustAllHosts()} <li>
 * {@link #constructUrl(String)} <li>
 * {@link #openConnection(String[])}
 * 
 * @see BugzillaHttpGetRequest
 * @see BugzillaHttpPostPutRequest
 */
public abstract class LoveFinderHttpRequest extends AsyncTask<String, Void, JSONObject> {

	private final static String BASE_URL = "http://10.0.2.2:8080/LoveFinder/";
	
	protected Context context;
	protected String showMessage;
	protected URI url;
	protected HttpClient httpClient;
	protected static ProgressDialog dialog;

	/**
	 * Constructs new {@link BugzillaHttpRequest}
	 * 
	 * @param context
	 *            the Activity Context
	 * @param showMessage
	 *            the message to be shown. Set to null if no message to be shown
	 */
	protected LoveFinderHttpRequest(Context context, String showMessage) {
		this.context = context;
		if(showMessage != null) {
			this.showMessage = showMessage;
		}
		this.httpClient = new DefaultHttpClient();
		
	}

	protected void cancelDialog() {
		if (dialog != null) {
			dialog.cancel();
		}
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		if (!Utilities.isOnline(context)) {
			cancel(true);
		}
		
		if (showMessage != null) {
			if (dialog != null && !dialog.getContext().equals(context)) {
				dialog.cancel();
				dialog = null;
			}
			if (dialog == null)
				dialog = new ProgressDialog(context);
			dialog.setTitle(showMessage + "...");
			dialog.setCancelable(true);
			dialog.show();
			dialog.setOnCancelListener(new OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					cancel(true);
				}
			});
		}
	}

	/**
	 * Constructs the {@link URL} for the request.
	 * 
	 * @param param
	 *            the params for the {@link URL}
	 * @return new {@link URL}
	 * @throws Exception
	 */
	protected URI constructUrl(String param) throws Exception {
		StringBuilder executeUrl = new StringBuilder();
		
		executeUrl.append(BASE_URL).append(param);
		
		System.out.println("EXECUTE: " + executeUrl.toString());
		return new URI(executeUrl.toString());
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);

		cancelDialog();
		
		if (result == null) {
			Toast.makeText(context, "An error occured while contacting server",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onCancelled() {
		super.onCancelled();
		cancelDialog();
		if (!Utilities.isOnline(context)) {
			Toast.makeText(context,
					"No internet connection right now. Please try again later",
					Toast.LENGTH_SHORT).show();
		}
	}
}
