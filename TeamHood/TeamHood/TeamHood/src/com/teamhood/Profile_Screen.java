package com.teamhood;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamhood.service.Constant_URL;
import com.teamhood.service.Edit_profile;
import com.teamhood.service.Get_profile;
import com.teamhood.util.ImageHelper;

public class Profile_Screen extends Activity implements OnClickListener{

	ImageView profile_screen_back,profile_screen_edit;
	TextView profile_screen_header,profile_screen_noti,profile_screen_TeamName;
	EditText profile_screen_username;
	SharedPreferences sp;
	ImageView image_employee;
	Bitmap bitmap = null;
	private static final int PICK_IMAGE = 1;
	ImageHelper yeshelp = new ImageHelper();
	private int h,w;
	InputMethodManager imm;
	String filePath="";
	ProgressDialog bar;
	Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.profile_screen);
		
		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();    
		h = display.getHeight();
		
		ctx=Profile_Screen.this;
		
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		
		profile_screen_back=(ImageView)findViewById(R.id.profile_screen_back);
		profile_screen_back.setOnClickListener(this);
		profile_screen_header=(TextView)findViewById(R.id.profile_screen_header);
		profile_screen_header.setTypeface(font2);
		profile_screen_edit=(ImageView)findViewById(R.id.profile_screen_edit);
		profile_screen_edit.setOnClickListener(this);
		profile_screen_noti=(TextView)findViewById(R.id.profile_screen_noti);
		profile_screen_noti.setTypeface(font);
		profile_screen_TeamName=(TextView)findViewById(R.id.profile_screen_TeamName);
		profile_screen_TeamName.setTypeface(font);
		profile_screen_TeamName.setText(sp.getString("team_name", ""));
		profile_screen_username=(EditText)findViewById(R.id.profile_screen_username);
		
		profile_screen_username.setTypeface(font2);
		profile_screen_username.setText(sp.getString("email", ""));
		profile_screen_username.setFocusableInTouchMode(false);
		
		image_employee=(ImageView)findViewById(R.id.image_employee);
		LinearLayout.LayoutParams emailparams = new LinearLayout.LayoutParams(w/2, w/2);
		image_employee.setLayoutParams(emailparams);
		image_employee.setOnClickListener(this);
		
		 int rotation = -1;
	        rotation = ((WindowManager)getSystemService(Context.WINDOW_SERVICE))
	                       .getDefaultDisplay().getOrientation();
	        
		if(Constant_URL.isNetworkAvailable(Profile_Screen.this)){
		new Get_profile(ctx, bar,sp.getString("email", ""),sp,image_employee,w,h,rotation).execute("main");
	}
		else{
			Toast.makeText(Profile_Screen.this, "Network not available.", 
					Toast.LENGTH_LONG).show();
		}
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.profile_screen_back:
			if(!filePath.equalsIgnoreCase("")){
				if(Constant_URL.isNetworkAvailable(Profile_Screen.this)){
			new Edit_profile(ctx, bar,profile_screen_username.getText().toString(),sp.getString("company_id", ""), sp.getString("team_id", ""),filePath,sp).execute("main");
			filePath="";
			}else{
				Toast.makeText(Profile_Screen.this, "Network not available.", 
						Toast.LENGTH_LONG).show();
			}
			}else{
			Intent intent=new Intent(Profile_Screen.this,Dash_Board.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			}
			break;
			
		case R.id.image_employee:
			try {
				bitmap = null;
				Intent gintent = new Intent();
				gintent.setType("image/*");
				gintent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(
						Intent.createChooser(gintent, "Select Picture"),
						PICK_IMAGE);

			} catch (Exception e) {
				bitmap = null;
				Toast.makeText(getApplicationContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();

			}
			break;
			
		case R.id.profile_screen_edit:
			Toast.makeText(Profile_Screen.this, "clicked", 
					Toast.LENGTH_LONG).show();
			
			profile_screen_username.setFocusableInTouchMode(true);
			profile_screen_username.setCursorVisible(true);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		break;

		default:
			break;
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Uri selectedImageUri = null;
		filePath = null;
		switch (requestCode) {
		case PICK_IMAGE:
			if (resultCode == Activity.RESULT_OK) {
				selectedImageUri = data.getData();
				filePath = getPath(selectedImageUri);
				if (filePath != null) {
					decodeFile(filePath);

					Bitmap p = yeshelp.getRoundedShape(bitmap,w,h);
					image_employee.setImageBitmap(p);
				} else {
					bitmap = null;
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
			ExifInterface exif = new ExifInterface(filePath);
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);

			int angle = 0;

			if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
				angle = 90;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
				angle = 180;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
				angle = 270;
			}

			final	Matrix mat = new Matrix();
			mat.postRotate(angle);

			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, o);

			// The new size we want to scale to
			

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp < w*2 && height_tmp < h*2)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			int scale1 = 1;
			Bitmap check=null;
			try{
				int www=(int) (w/1.5);
				int hhh=(int) (h/3.5);
				// Find the correct scale value. It should be the power of 2.
				int width_tmp1 = o.outWidth, height_tmp1 = o.outHeight;
				while (true) {
					if (width_tmp1 < www*2 && height_tmp1 < hhh*2)
					{
						break;
					}
					/*else if(width_tmp1>1700 || height_tmp1>2000)
					{
						width_tmp1 /= 5;
						height_tmp1 /= 5;
						scale1 *= 5;	
					}*/
					else{
					width_tmp1 /= 2;
					height_tmp1 /= 2;
					scale1 *= 2;
					}
				}
			}
			catch(Exception e)
			{
				
			}
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale1;

			final BitmapFactory.Options o3 = new BitmapFactory.Options();
			o3.inSampleSize = scale;
			try {

				bitmap = BitmapFactory.decodeFile(filePath, o2);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
						bitmap.getHeight(), mat, true);
				
				

			} catch (Exception e) {
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}

	}

	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent=new Intent(Profile_Screen.this,Dash_Board.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
		finish();
	}

}
