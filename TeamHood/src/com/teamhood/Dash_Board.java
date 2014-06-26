package com.teamhood;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Dash_Board extends Activity implements OnClickListener{

	Button dash_board_setting;
	ImageView dashboard_time_bomb,dashboard_direct_chat,dashboard_files_sharing,dashboard_my_profile,dashboard_my_team;
	TextView dashboard_timebomb_text,dashboard_direct_chat_text,dashboard_file_sharing_text,dashboard_my_profile_text,dashboard_my_team_text,dashboard_chat_header_text
	,dashboard_chat_view_text,dashboard_task_header_text,dashboard_task_view_text,dashboard_happy,dashboard_hoderate,dashboard_sad,dashboard_notes_header_text
	,dashboard_notes_view_text,dashboard_welcome_text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.dash_board);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		
		dash_board_setting=(Button)findViewById(R.id.dash_board_setting);
		dashboard_time_bomb=(ImageView)findViewById(R.id.dashboard_time_bomb);
		dashboard_time_bomb.setOnClickListener(this);
		dashboard_timebomb_text=(TextView)findViewById(R.id.dashboard_timebomb_text);
		dashboard_timebomb_text.setTypeface(font2);
		dashboard_direct_chat=(ImageView)findViewById(R.id.dashboard_direct_chat);
		dashboard_direct_chat_text=(TextView)findViewById(R.id.dashboard_direct_chat_text);
		dashboard_direct_chat_text.setTypeface(font2);
		dashboard_files_sharing=(ImageView)findViewById(R.id.dashboard_files_sharing);
		dashboard_file_sharing_text=(TextView)findViewById(R.id.dashboard_file_sharing_text);
		dashboard_file_sharing_text.setTypeface(font2);
		dashboard_my_profile=(ImageView)findViewById(R.id.dashboard_my_profile);
		dashboard_my_profile_text=(TextView)findViewById(R.id.dashboard_my_profile_text);
		dashboard_my_profile_text.setTypeface(font2);
		dashboard_my_team=(ImageView)findViewById(R.id.dashboard_my_team);
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
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.dashboard_time_bomb:
			Intent intent1=new Intent(Dash_Board.this,Time_bomb.class);
			
			startActivity(intent1);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;

		default:
			break;
		}
	}



}
