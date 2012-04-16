package com.neya.love.finder.requests;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.widget.Toast;

public class LoveFinderHttpGetRequest extends LoveFinderHttpRequest {

	protected ProgressDialog dialog;
	private HttpGet httpGet;
	protected HttpParams requestParams;
	
	protected LoveFinderHttpGetRequest(Context context, String showMessage) {
		super(context, showMessage);
		requestParams = new BasicHttpParams();
	}

	@Override
	protected JSONObject doInBackground(String... params) {
		
		try {
			httpGet = new HttpGet(constructUrl(params[0]));
			
			HttpResponse response = httpClient.execute(httpGet);
			
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_ACCEPTED) {
				return new JSONObject(EntityUtils.toString(response
						.getEntity()));
			}
			return null;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}