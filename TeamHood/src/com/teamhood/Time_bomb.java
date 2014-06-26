package com.teamhood;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class Time_bomb extends Activity implements OnClickListener{

	TextView timebomb_cancel,timebomb_message,Timebomb_task,Timebomb_header_Text,Timebomb_second_header,Timebomb_header_timebomb;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.timebomb);
		
		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		
		timebomb_cancel=(TextView)findViewById(R.id.timebomb_cancel);
		timebomb_cancel.setTypeface(font2);
		timebomb_cancel.setOnClickListener(this);
		timebomb_message=(TextView)findViewById(R.id.timebomb_message);
		timebomb_message.setTypeface(font2);
		timebomb_message.setOnClickListener(this);
		Timebomb_task=(TextView)findViewById(R.id.Timebomb_task);
		Timebomb_task.setTypeface(font2);
		Timebomb_task.setOnClickListener(this);
		Timebomb_header_Text=(TextView)findViewById(R.id.Timebomb_header_Text);
		Timebomb_header_Text.setTypeface(font);
		Timebomb_second_header=(TextView)findViewById(R.id.Timebomb_second_header);
		Timebomb_second_header.setTypeface(font2);
		Timebomb_header_timebomb=(TextView)findViewById(R.id.Timebomb_header_timebomb);
		Timebomb_header_timebomb.setTypeface(font2);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.timebomb_cancel:
			Intent intent=new Intent(Time_bomb.this,Dash_Board.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;

		case R.id.timebomb_message:
			SharedPreferences.Editor editer4 = sp.edit();
			editer4.putString("Time_bomb_screen", "message");
			editer4.commit();
			Intent intent1=new Intent(Time_bomb.this,Create_Message.class);
			startActivity(intent1);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;
			
		case R.id.Timebomb_task:
			SharedPreferences.Editor editer = sp.edit();
			editer.putString("Time_bomb_screen", "task");
			editer.commit();
			Intent intent2=new Intent(Time_bomb.this,Create_Message.class);
			startActivity(intent2);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;
		default:
			break;
		}
	}
	
	

}
