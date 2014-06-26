package com.teamhood;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Dialog_Team__member extends Activity implements OnClickListener{

	LinearLayout dialog_team_member;
	TextView dialog_team,dialog_direct;
	ImageView view_cross;
	int w,h;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_team_member);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		
		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();
		
		LinearLayout.LayoutParams emailparams = new LinearLayout.LayoutParams(h/3, w/2);
		
		
		dialog_team_member=(LinearLayout)findViewById(R.id.dialog_team_member);
		dialog_team_member.setLayoutParams(emailparams);
		dialog_team=(TextView)findViewById(R.id.dialog_team);
		dialog_team.setTypeface(font);
		dialog_team.setOnClickListener(this);
		dialog_direct=(TextView)findViewById(R.id.dialog_direct);
		dialog_direct.setTypeface(font);
		dialog_direct.setOnClickListener(this);
		view_cross=(ImageView)findViewById(R.id.view_cross);
		view_cross.setOnClickListener(this);
			
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.view_cross:
			
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;
			
		case R.id.dialog_direct:
			Intent intent2=new Intent(Dialog_Team__member.this,Team_Member_List.class);
			
			startActivity(intent2);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;

		default:
			break;
		}
	}
	
	

}
