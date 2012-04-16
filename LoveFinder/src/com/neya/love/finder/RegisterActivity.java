package com.neya.love.finder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.codehaus.jackson.node.BinaryNode;
import org.codehaus.jackson.node.TextNode;

import com.neya.love.finder.listeners.RegisterClickListener;
import com.neya.love.finder.requests.SendImage;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Base64;
import android.util.Base64InputStream;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private ImageView profileImage;
	private RegisterClickListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v = View.inflate(this, R.layout.register, null);
		setContentView(v);

		listener = new RegisterClickListener(this, v);
		
		((Button) findViewById(R.id.register_submit))
				.setOnClickListener(listener);

		setViews();

	}

	private void setViews() {
		setSpinnerSettings();
		profileImage = (ImageView) findViewById(R.id.register_profile_image);
	}

	private void setSpinnerSettings() {
		Spinner sexSpinner = (Spinner) findViewById(R.id.register_sex);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.sex, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sexSpinner.setAdapter(adapter);
	}

	public void onCancelClick(View v) {
		finish();
	}

	public void onChooseImageClick(View v) {
		Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, 1);
	}

	@SuppressWarnings("deprecation")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Uri chosenImageUri = data.getData();
			Bitmap b = null;
			try {
				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inJustDecodeBounds = true;

				FileInputStream fis = new FileInputStream(
						getRealPathFromURI(chosenImageUri));
				BitmapFactory.decodeStream(fis, null, o);
				fis.close();

				int scale = 1;
				if (o.outHeight > 100 || o.outWidth > 100) {
					scale = (int) Math.pow(
							2,
							(int) Math.round(Math.log(100 / (double) Math.max(
									o.outHeight, o.outWidth)) / Math.log(0.5)));
				}

				BitmapFactory.Options o2 = new BitmapFactory.Options();
				o2.inSampleSize = scale;
				fis = new FileInputStream(getRealPathFromURI(chosenImageUri));
				b = BitmapFactory.decodeStream(fis, null, o2);
				fis.close();
				profileImage.setImageBitmap(b);

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				b.compress(Bitmap.CompressFormat.JPEG, 50, baos);
				
				byte[] image = baos.toByteArray();

				listener.setImage(image);
				
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "Please choose another image",
						Toast.LENGTH_SHORT).show();

			}

		}
	}

	public String getRealPathFromURI(Uri contentUri) {

		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, proj, 
				null, 
				null, 
				null); 
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();

		return cursor.getString(column_index);
	}
}
