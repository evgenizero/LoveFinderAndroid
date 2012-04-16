package com.neya.love.finder.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.neya.love.finder.R;
import com.neya.love.finder.data.User;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UsersAdapter extends BaseAdapter {
	private List<User> users;
	private Context context;

	public class ViewHolder {
		TextView username;
		TextView age;
		TextView country;
		TextView city;
		ImageView profileImage;
	}

	public UsersAdapter(Context context, List<User> users) {
		this.context = context;
		this.users = users;
	}

	public int getCount() {
		return users.size();
	}

	public Object getItem(int position) {
		return users.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null || convertView.getTag() == null) {
			convertView = View.inflate(context, R.layout.users_holder, null);
			holder = new ViewHolder();

			holder.username = (TextView) convertView
					.findViewById(R.id.user_holder_username);
			holder.age = (TextView) convertView
					.findViewById(R.id.user_holder_age);
			holder.country = (TextView) convertView
					.findViewById(R.id.user_holder_country);
			holder.city = (TextView) convertView
					.findViewById(R.id.user_holder_city);
			holder.profileImage = (ImageView) convertView
					.findViewById(R.id.user_holder_image);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		User user = (User) getItem(position);

		holder.username.setText(user.getUsername());
		holder.age.setText(String.valueOf(user.getAge()));
		holder.country.setText(user.getCountry());
		holder.city.setText(user.getCity());

		holder.profileImage.setTag(String.valueOf(user.getCustomerId()));
		new DownloadImagesTask().execute(holder.profileImage);
		
		
		convertView.requestLayout();
		return convertView;
	}
	
	private class DownloadImagesTask extends AsyncTask<ImageView, Void, Bitmap> {

		private ImageView imageView = null;

		@Override
		protected Bitmap doInBackground(ImageView... imageViews) {
			this.imageView = imageViews[0];
			return downloadImage((String) imageView.getTag());
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				imageView.setImageBitmap(result);
				//Cache.saveCacheFile((String) imageView.getTag(), result);
			}
			else {
				imageView.setImageResource(R.drawable.unknown_user);
			}
		}

		private synchronized Bitmap downloadImage(String url) {

			Bitmap bitmap = null;
			InputStream in = null;
			try {
				in = OpenHttpConnection("http://10.0.2.2:80/profileImage" + url + ".jpg");
				bitmap = BitmapFactory.decodeStream(in);
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			return bitmap;
		}
	}

	private InputStream OpenHttpConnection(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection)) {
			return null;
		}

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return in;
	}

}