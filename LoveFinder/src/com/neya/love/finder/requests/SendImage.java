package com.neya.love.finder.requests;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.node.BinaryNode;
import org.json.JSONObject;

import android.content.Context;
import android.util.Base64;

public class SendImage extends LoveFinderHttpPostRequest {

	public SendImage(Context context, byte[] image) throws IOException {
		super(context, "image");

		System.out.println("IMAGE: " + image);
		

		nameValuePairs.add(new BasicNameValuePair("image", new BinaryNode(image).getValueAsText()));

		execute("upload");
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);

		System.out.println("DONE");

	}

}
