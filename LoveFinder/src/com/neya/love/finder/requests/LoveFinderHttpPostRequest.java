package com.neya.love.finder.requests;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.widget.Toast;

public class LoveFinderHttpPostRequest extends LoveFinderHttpRequest {

	private HttpPost httpPost;
	protected List<NameValuePair> nameValuePairs;
	protected ProgressDialog dialog;

	protected LoveFinderHttpPostRequest(Context context, String showMessage) {
		super(context, showMessage);
		nameValuePairs = new ArrayList<NameValuePair>();
	}

	@Override
	protected JSONObject doInBackground(String... params) {

		try {
			httpPost = new HttpPost(constructUrl(params[0]));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(httpPost);
			System.out.println(response.getStatusLine());

			int status = response.getStatusLine().getStatusCode();

			if (status == HttpStatus.SC_ACCEPTED
					|| status == HttpStatus.SC_CREATED) {
				return new JSONObject(EntityUtils.toString(response.getEntity()));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}