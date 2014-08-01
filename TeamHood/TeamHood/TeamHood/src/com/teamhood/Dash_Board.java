package com.teamhood;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamhood.CommonUtilities;
import com.teamhood.ServerUtilities;
import com.teamhood.WakeLocker;
import com.example.sliderclass.CollapseAnimation;
import com.example.sliderclass.ExpandAnimation;
import com.google.android.gcm.GCMRegistrar;
import com.teamhood.service.Constant_URL;
import com.teamhood.service.Register_push_divice;
import com.viewpagerindicator.CirclePageIndicator;

@SuppressLint("CutPasteId")
public class Dash_Board extends BaseSampleActivity implements OnClickListener, OnTouchListener{

	ImageView dash_board_setting;
	ImageView dashboard_time_bomb,dashboard_direct_chat,dashboard_files_sharing,dashboard_my_profile,dashboard_my_team;
	TextView dashboard_timebomb_text,dashboard_direct_chat_text,dashboard_file_sharing_text,dashboard_my_profile_text,dashboard_my_team_text,dashboard_chat_header_text
	,dashboard_chat_view_text,dashboard_task_header_text,dashboard_task_view_text,dashboard_happy,dashboard_hoderate,dashboard_sad,dashboard_notes_header_text
	,dashboard_notes_view_text,dashboard_welcome_text,dash_board_getStarted;
	SharedPreferences sp;
	ProgressDialog pro;
	int panelWidth;
	DisplayMetrics metrics;
	private boolean isExpanded=true;
	RelativeLayout menuPanel,dots_menuPanel;
	ImageButton leads_sidebar,leads_dots_menu; 
	FrameLayout.LayoutParams menuPanelParameters,slidingPanelParameters;
	LinearLayout slidingPanel;
	
	ViewPager pager;
	String[] imageUrls={"1","2","3","4"};
	int pos;
	
	
	private ViewPager mPager;
	static Context ctx;
	TextView manu_logout;
	static ImageView dot1 ;
	static ImageView dot2;
	static ImageView dot3;
	
	Bitmap bitmap = null;
	private int h,w;
	String encodedImage;
	
	AsyncTask<Void, Void, Void> mRegisterTask;
	public static String name;
	public static String email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.dash_board);

		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		
		ctx=Dash_Board.this;
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");

		Display display = getWindowManager().getDefaultDisplay(); 
		w = display.getWidth();    
		h = display.getHeight();
		
		
		 // Slidemenupanal object
		manu_logout=(TextView)findViewById(R.id.manu_logout);
		manu_logout.setTypeface(font);
		manu_logout.setOnClickListener(this);
		
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);   
		panelWidth = (int) ((metrics.widthPixels)*0.82);
		
		menuPanel=(RelativeLayout)findViewById(R.id.menuPanel);
		menuPanelParameters = (FrameLayout.LayoutParams) menuPanel.getLayoutParams();
		menuPanelParameters.width = panelWidth;  
		menuPanel.setLayoutParams(menuPanelParameters);
		menuPanel.setVisibility(View.GONE);
		
		slidingPanel = (LinearLayout) findViewById(R.id.dash_board);
		slidingPanelParameters = (FrameLayout.LayoutParams) slidingPanel.getLayoutParams();
		slidingPanelParameters.width = metrics.widthPixels;
		slidingPanel.setLayoutParams(slidingPanelParameters);
		slidingPanel.setLayoutParams(slidingPanelParameters);
		slidingPanel.setOnTouchListener(Dash_Board.this);
		
		//////////////////////////////
		
		dash_board_setting=(ImageView)findViewById(R.id.dash_board_setting);
		dash_board_setting.setOnClickListener(this);
		dashboard_time_bomb=(ImageView)findViewById(R.id.dashboard_time_bomb);
		dashboard_time_bomb.setOnClickListener(this);
		dashboard_timebomb_text=(TextView)findViewById(R.id.dashboard_timebomb_text);
		dashboard_timebomb_text.setTypeface(font2);
		dashboard_direct_chat=(ImageView)findViewById(R.id.dashboard_direct_chat);
		dashboard_direct_chat.setOnClickListener(this);
		dashboard_direct_chat_text=(TextView)findViewById(R.id.dashboard_direct_chat_text);
		dashboard_direct_chat_text.setTypeface(font2);
		dashboard_files_sharing=(ImageView)findViewById(R.id.dashboard_files_sharing);
		dashboard_files_sharing.setOnClickListener(this);
		dashboard_file_sharing_text=(TextView)findViewById(R.id.dashboard_file_sharing_text);
		dashboard_file_sharing_text.setTypeface(font2);
		dashboard_my_profile=(ImageView)findViewById(R.id.dashboard_my_profile);
		dashboard_my_profile.setOnClickListener(this);
		dashboard_my_profile_text=(TextView)findViewById(R.id.dashboard_my_profile_text);
		dashboard_my_profile_text.setTypeface(font2);
		dashboard_my_team=(ImageView)findViewById(R.id.dashboard_my_team);
		dashboard_my_team.setOnClickListener(this);
		dashboard_my_team_text=(TextView)findViewById(R.id.dashboard_my_team_text);
		dashboard_my_team_text.setTypeface(font2);
		dashboard_chat_header_text=(TextView)findViewById(R.id.dashboard_chat_header_text);
		dashboard_chat_header_text.setTypeface(font);
		dashboard_chat_view_text=(TextView)findViewById(R.id.dashboard_chat_view_text);
		dashboard_chat_view_text.setTypeface(font);
		dashboard_task_header_text=(TextView)findViewById(R.id.dashboard_task_header_text);
		dashboard_task_header_text.setTypeface(font);
		dashboard_task_view_text=(TextView)findViewById(R.id.dashboard_task_view_text);
		dashboard_task_view_text.setTypeface(font);
		dashboard_happy=(TextView)findViewById(R.id.dashboard_happy);
		dashboard_happy.setTypeface(font2);
		dashboard_hoderate=(TextView)findViewById(R.id.dashboard_hoderate);
		dashboard_hoderate.setTypeface(font2);
		dashboard_sad=(TextView)findViewById(R.id.dashboard_sad);
		dashboard_sad.setTypeface(font2);
		dashboard_notes_header_text=(TextView)findViewById(R.id.dashboard_notes_header_text);
		dashboard_notes_header_text.setTypeface(font);
		dashboard_notes_view_text=(TextView)findViewById(R.id.dashboard_notes_view_text);
		dashboard_notes_view_text.setTypeface(font);
//		dashboard_welcome_text=(TextView)findViewById(R.id.dashboard_welcome_text);
//		dashboard_welcome_text.setTypeface(font);
		dash_board_getStarted=(TextView)findViewById(R.id.dash_board_getStarted);
		dash_board_getStarted.setTypeface(font);
		
		if(Constant_URL.isNetworkAvailable(Dash_Board.this))
		{
//		new Get_login(Dash_Board.this, pro,sp.getString("username", ""),sp.getString("password", ""),sp).execute("main");
		}
		else{
			Toast.makeText(Dash_Board.this, "Network not available.", 
					Toast.LENGTH_LONG).show();
		}
		//Viewpager with indicator
		mAdapter = new TestFragmentAdapter(getSupportFragmentManager(),ctx);

	        mPager = (ViewPager)findViewById(R.id.pager);
	        mPager.setAdapter(mAdapter);

	        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
	        mIndicator.setViewPager(mPager);
		
		///////////
//	        PushRegistration();
	   
		
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.dashboard_direct_chat:
			/*final Intent ei = new Intent(Intent.ACTION_SEND_MULTIPLE);
			ei.setType("plain/text");
			ei.putExtra(Intent.EXTRA_EMAIL, new String[] {"me@somewhere.nodomain"});
			ei.putExtra(Intent.EXTRA_SUBJECT, "That one works");
			ArrayList<Uri> uris = new ArrayList<Uri>();

			ei.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
			startActivityForResult(Intent.createChooser(ei, "Sending multiple attachment"), 12345);
			*/
			/*String path = Environment.getExternalStorageDirectory().toString();
			Log.d("Files", "Path: " + path);
			File f = new File(path);        
			walkdir(f);*/
			Intent intent5=new Intent(Dash_Board.this,Chat_Contect_List.class);

			startActivity(intent5);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;
		case R.id.dash_board_setting:

			Log.d("yes111..", "helloo");
			if(isExpanded){
				isExpanded = false;

				Log.d("yes..", "helloo");
				panelWidth = (int) ((metrics.widthPixels)*0.82);
				menuPanelParameters.width = panelWidth;
				menuPanel.setLayoutParams(menuPanelParameters);   
				slidingPanelParameters.width = metrics.widthPixels;
				slidingPanel.setLayoutParams(slidingPanelParameters);

				menuPanel.setVisibility(View.VISIBLE);	

				//Expand
				new ExpandAnimation(slidingPanel, panelWidth,
						Animation.RELATIVE_TO_SELF, 0.0f,
						Animation.RELATIVE_TO_SELF, 0.82f, 0, 0.0f, 0, 0.0f);		    			         	    
			}else{

				isExpanded = true;
				new CollapseAnimation(slidingPanel,panelWidth,
						TranslateAnimation.RELATIVE_TO_SELF, 0.82f,
						TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0, 0.0f, menuPanel);	
			}

		
			break;
		case R.id.dashboard_time_bomb:
			Intent intent1=new Intent(Dash_Board.this,Time_bomb.class);

			startActivity(intent1);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			
			break;

		case R.id.dashboard_files_sharing:
			Intent intent2=new Intent(Dash_Board.this,File_Sharing.class);

			startActivity(intent2);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;

		case R.id.dashboard_my_profile:
			Intent intent3=new Intent(Dash_Board.this,Profile_Screen.class);

			startActivity(intent3);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;

		case R.id.dashboard_my_team:
			SharedPreferences.Editor editer4 = sp.edit();
			editer4.putString("Team_Meamber_Screen", "Deah_Board");
			editer4.commit();
			Intent intent4=new Intent(Dash_Board.this,Team_Member_List.class);

			startActivity(intent4);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;
			
		case R.id.manu_logout:
			SharedPreferences preferences =Dash_Board.this.getSharedPreferences("TeamHood", 0);
			preferences.edit().clear().commit();

			Intent intent=new Intent(Dash_Board.this,MainActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			finish();
			
			break;

		default:
			break;
		}
	}
	public void walkdir(File dir) {
	    String pdfPattern = ".pdf";

	    File listFile[] = dir.listFiles();

	    if (listFile != null) {
	        for (int i = 0; i < listFile.length; i++) {

	            if (listFile[i].isDirectory()) {
	                walkdir(listFile[i]);
	            } else {
	              if (listFile[i].getName().endsWith(pdfPattern)){
	                                  //Do what ever u want
//	            	  Toast.makeText(ctx, listFile[i].getName()+"//"+listFile[i].getPath(), 
//	      					Toast.LENGTH_LONG).show();
	            	  decodeFile(listFile[i].getPath());
	              }
	            }
	        }
	    }    
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

			
			try {
				
				File file = new File(filePath);
				@SuppressWarnings("resource")
				InputStream inputStream = new FileInputStream(file); 
				byte[] bytes = new byte[(int) file.length()];
				inputStream.read(bytes);
				
				
				bitmap = BitmapFactory.decodeFile(filePath, o2);
				 Toast.makeText(ctx, "1"+inputStream.toString(), 
	      					Toast.LENGTH_LONG).show();
				
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Toast.makeText(ctx, "3", 
      					Toast.LENGTH_LONG).show();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); 
				Toast.makeText(ctx, "4", 
      					Toast.LENGTH_LONG).show();
				byte[] b = baos.toByteArray(); 
				
				Toast.makeText(ctx, "5", 
      					Toast.LENGTH_LONG).show();
				encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
				
				 Toast.makeText(ctx, encodedImage, 
	      					Toast.LENGTH_LONG).show();

			} catch (Exception e) {
				 Toast.makeText(ctx, "6"+e+"", 
	      					Toast.LENGTH_LONG).show();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			 Toast.makeText(ctx, "7"+e+"", 
   					Toast.LENGTH_LONG).show();
		}

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
		finish();
	}
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	protected void PushRegistration() {
		////////////Push Notificetion//////////////////////////
        name=sp.getString("username", "");
        email=sp.getString("username", "");
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        registerReceiver(mHandleMessageReceiver, new IntentFilter(
				CommonUtilities.DISPLAY_MESSAGE_ACTION));
        // Get GCM registration id
		final String regId = GCMRegistrar.getRegistrationId(this);
		//		Toast.makeText(Dash_Board.this, "regId 1= "+regId, Toast.LENGTH_LONG).show();
		// Check if regid already presents
		if (regId.equals("")) {
			// Registration is not present, register now with GCM			
			GCMRegistrar.register(this, CommonUtilities.SENDER_ID);
			
			
		} else {
			if(Constant_URL.isNetworkAvailable(Dash_Board.this))
			{
				//			Toast.makeText(Dash_Board.this, "regId = "+regId, Toast.LENGTH_LONG).show();
			new Register_push_divice(Dash_Board.this, pro,regId,sp.getString("username", ""), sp.getString("username", "")).execute("main");
			// Device is already registered on GCM
			if (GCMRegistrar.isRegisteredOnServer(this)) {
				// Skips registration.				
				//				Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
			} else {
				// Try to register again, but not in the UI thread.
				// It's also necessary to cancel the thread onDestroy(),
				// hence the use of AsyncTask instead of a raw thread.
				final Context context = Dash_Board.this;
				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						// Register on our server
						// On server creates a new user
						ServerUtilities.register(context, sp.getString("username", ""), sp.getString("username", ""), regId, regId);
						
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						
						mRegisterTask = null;
					}

				};
				mRegisterTask.execute(null, null, null);
			}
		}
			else{
				Toast.makeText(Dash_Board.this, "Network not available.", 
						Toast.LENGTH_LONG).show();
			}
	}
	
   ///////////////////////////////////////////////////////
	}
	/**
	 * Receiving push messages
	 * */
	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String newMessage = intent.getExtras().getString(CommonUtilities.EXTRA_MESSAGE);
			// Waking up mobile if it is sleeping
			WakeLocker.acquire(getApplicationContext());
			
			/**
			 * Take appropriate action on this message
			 * depending upon your app requirement
			 * For now i am just displaying it on the screen
			 * */
			
			// Showing received message
			
//			Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
			
			// Releasing wake lock
			WakeLocker.release();
		}
	};
	
	/*@Override
	protected void onDestroy() {
		if (mRegisterTask != null) {
			mRegisterTask.cancel(true);
		}
		try {
			unregisterReceiver(mHandleMessageReceiver);
			GCMRegistrar.onDestroy(this);
		} catch (Exception e) {
			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
		}
		super.onDestroy();
	}*/
}
