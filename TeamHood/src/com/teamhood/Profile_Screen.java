package com.teamhood;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile_Screen extends Activity implements OnClickListener{

	ImageView profile_screen_back,profile_screen_edit;
	TextView profile_screen_header,profile_screen_noti,profile_screen_TeamName,profile_screen_username;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.profile_screen);
		
		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		
		profile_screen_back=(ImageView)findViewById(R.id.profile_screen_back);
		profile_screen_back.setOnClickListener(this);
		profile_screen_header=(TextView)findViewById(R.id.profile_screen_header);
		profile_screen_header.setTypeface(font2);
		profile_screen_edit=(ImageView)findViewById(R.id.profile_screen_edit);
		
		profile_screen_noti=(TextView)findViewById(R.id.profile_screen_noti);
		profile_screen_noti.setTypeface(font);
		profile_screen_TeamName=(TextView)findViewById(R.id.profile_screen_TeamName);
		profile_screen_TeamName.setTypeface(font);
		profile_screen_TeamName.setText(sp.getString("team_name", ""));
		profile_screen_username=(TextView)findViewById(R.id.profile_screen_username);
		profile_screen_username.setTypeface(font2);
		profile_screen_username.setText(sp.getString("email", ""));
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.profile_screen_back:
			Intent intent=new Intent(Profile_Screen.this,Dash_Board.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
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
		Intent intent=new Intent(Profile_Screen.this,Dash_Board.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
		finish();
	}

}
