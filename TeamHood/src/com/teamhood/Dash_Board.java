package com.teamhood;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sliderclass.CollapseAnimation;
import com.example.sliderclass.ExpandAnimation;

public class Dash_Board extends Activity implements OnClickListener, OnTouchListener{

	Button dash_board_setting;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.dash_board);

		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");

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
		
		dash_board_setting=(Button)findViewById(R.id.dash_board_setting);
		dash_board_setting.setOnClickListener(this);
		dashboard_time_bomb=(ImageView)findViewById(R.id.dashboard_time_bomb);
		dashboard_time_bomb.setOnClickListener(this);
		dashboard_timebomb_text=(TextView)findViewById(R.id.dashboard_timebomb_text);
		dashboard_timebomb_text.setTypeface(font2);
		dashboard_direct_chat=(ImageView)findViewById(R.id.dashboard_direct_chat);
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
		dashboard_welcome_text=(TextView)findViewById(R.id.dashboard_welcome_text);
		dashboard_welcome_text.setTypeface(font);
		dash_board_getStarted=(TextView)findViewById(R.id.dash_board_getStarted);
		dash_board_getStarted.setTypeface(font);
		
//		new Get_login(Dash_Board.this, pro,sp.getString("username", ""),sp.getString("password", ""),sp).execute("main");
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
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

		default:
			break;
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

}
