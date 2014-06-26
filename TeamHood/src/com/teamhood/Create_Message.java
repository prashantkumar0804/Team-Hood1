package com.teamhood;

import java.util.Calendar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamhood.service.Get_Time_bomb_message;
import com.teamhood.service.Get_Time_bomb_task;

public class Create_Message extends Activity implements OnClickListener{

	TextView create_message_cencel,create_message_header_text,create_message_depoly;
	EditText create_message_edit;
	ImageView create_message_set_time,create_message_camera,create_message_set_photo,create_message_option;
	SharedPreferences sp;
	ProgressDialog bar;
	LinearLayout footer_datepicker;
	public static String First_editText="";
	Calendar dateandtime;
	private static final int PICK_Camera_IMAGE = 2;
	private static final int PICK_IMAGE = 1;
	Uri imageUri;
	Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.create_message);
		
		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		
		create_message_cencel=(TextView)findViewById(R.id.create_message_cencel);
		create_message_cencel.setTypeface(font2);
		create_message_cencel.setOnClickListener(this);
		create_message_header_text=(TextView)findViewById(R.id.create_message_header_text);
		create_message_header_text.setTypeface(font2);
		create_message_depoly=(TextView)findViewById(R.id.create_message_depoly);
		create_message_depoly.setTypeface(font2);
		create_message_depoly.setOnClickListener(this);
		create_message_edit=(EditText)findViewById(R.id.create_message_edit);
		create_message_edit.setTypeface(font);
		create_message_set_time=(ImageView)findViewById(R.id.create_message_set_time);
		create_message_set_time.setOnClickListener(this);
		create_message_camera=(ImageView)findViewById(R.id.create_message_camera);
		create_message_camera.setOnClickListener(this);
		create_message_set_photo=(ImageView)findViewById(R.id.create_message_set_photo);
		create_message_set_photo.setOnClickListener(this);
		create_message_option=(ImageView)findViewById(R.id.create_message_option);
		create_message_option.setOnClickListener(this);
		footer_datepicker = (LinearLayout) findViewById(R.id.footer_datepicker);
		dateandtime = Calendar.getInstance();
		new FooterClass(First_editText, footer_datepicker,
				Create_Message.this, dateandtime);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.create_message_cencel:
			Intent intent=new Intent(Create_Message.this,Time_bomb.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;
  
		case R.id.create_message_depoly:
			if(!First_editText.trim().equalsIgnoreCase("")){
			if(sp.getString("Time_bomb_screen", "").equalsIgnoreCase("message")){
			new Get_Time_bomb_message(Create_Message.this, bar, sp.getString("username", ""), sp.getString("company_id", ""), sp.getString("team_id", ""), create_message_edit.getText().toString().trim(), First_editText, sp.getString("sender_email", ""), sp).execute("main");
			}else if(sp.getString("Time_bomb_screen", "").equalsIgnoreCase("task")){
				new Get_Time_bomb_task(Create_Message.this, bar, sp.getString("username", ""), sp.getString("company_id", ""), sp.getString("team_id", ""), create_message_edit.getText().toString().trim(), First_editText, sp.getString("sender_email", ""), sp).execute("main");
					
			}
			}
			break;
			
		case R.id.create_message_set_time:
			
			footer_datepicker.setVisibility(View.VISIBLE);
			

			break;
			
		case R.id.create_message_camera:
			String fileName = "new-photo-name.jpg";
			ContentValues values = new ContentValues();
			values.put(MediaStore.Images.Media.TITLE, fileName);
			values.put(MediaStore.Images.Media.DESCRIPTION,
					"Image capture by camera");
			imageUri = getContentResolver().insert(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					values);
			// create new Intent
			Intent intent1 = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			intent1.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			startActivityForResult(intent1, PICK_Camera_IMAGE);
			break;
			
		case R.id.create_message_set_photo:
			Intent gintent = new Intent();
			gintent.setType("image/*");
			gintent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(
					gintent, "Select Picture"), PICK_IMAGE);
			break;
			
		case R.id.create_message_option:
			Intent intent2=new Intent(Create_Message.this,Dialog_Team__member.class);
			
			startActivity(intent2);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			
			break;
		default:
			break;
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Uri selectedImageUri = null;
		String filePath = null;
		switch (requestCode) {
		
		case PICK_IMAGE:
			if (resultCode == Activity.RESULT_OK) {
				selectedImageUri = data.getData();
				filePath = getPath(selectedImageUri);
				if (filePath != null) {
					decodeFile(filePath);
				} else {
					bitmap = null;
				}
			}
			//	new UplaodProfilePic().execute();
			break;
			
		case PICK_Camera_IMAGE:
			if (resultCode == RESULT_OK) {
				// use imageUri here to access the image

				selectedImageUri = imageUri;
			} else if (resultCode == RESULT_CANCELED) {
				Toast toast1 = Toast.makeText(Create_Message.this,"Picture was not taken.", Toast.LENGTH_LONG);
				toast1.setGravity(Gravity.CENTER, 0, 0);
				toast1.show();
//				Toast.makeText(this, "Picture was not taken",
//						Toast.LENGTH_SHORT).show();
			} else {
				Toast toast1 = Toast.makeText(Create_Message.this,"Picture was not taken.", Toast.LENGTH_LONG);
				toast1.setGravity(Gravity.CENTER, 0, 0);
				toast1.show();
//				Toast.makeText(this, "Picture was not taken",
//						Toast.LENGTH_SHORT).show();
			}
			if (selectedImageUri != null) {
				try {
					String filemanagerstring = selectedImageUri.getPath();

					String selectedImagePath = getPath(selectedImageUri);

					if (selectedImagePath != null) {
						filePath = selectedImagePath;
					} else if (filemanagerstring != null) {
						filePath = filemanagerstring;
					} else {
						Toast toast1 = Toast.makeText(Create_Message.this,"Unknown path.", Toast.LENGTH_LONG);
						toast1.setGravity(Gravity.CENTER, 0, 0);
						toast1.show();
//						Toast.makeText(getApplicationContext(), "Unknown path",
//								Toast.LENGTH_LONG).show();

					}
					if (filePath != null) {

						decodeFile(filePath);

					} else {
						bitmap = null;
					}
				} catch (Exception e) {
					Toast toast1 = Toast.makeText(Create_Message.this,"Internal error.", Toast.LENGTH_LONG);
					toast1.setGravity(Gravity.CENTER, 0, 0);
					toast1.show();
//					Toast.makeText(getApplicationContext(), "Internal error",
//							Toast.LENGTH_LONG).show();

				}

			}



			break;
		}
	}
	@SuppressWarnings("deprecation")
	public String getPath(Uri uri) {
		Cursor cursor = null;
		int column_index = 0;
		try {
			String[] projection = { MediaStore.Images.Media.DATA };
			cursor = managedQuery(uri, projection, null, null, null);
			column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return cursor.getString(column_index);
	}

	public void decodeFile(String filePath) {
		// Decode image size
		try {
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 512;

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			try {

				bitmap = BitmapFactory.decodeFile(filePath, o2);
				
			} catch (OutOfMemoryError e) {
				o2.inSampleSize = 2;
				bitmap = BitmapFactory.decodeFile(filePath, o2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent=new Intent(Create_Message.this,Time_bomb.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
		finish();
	}
}
