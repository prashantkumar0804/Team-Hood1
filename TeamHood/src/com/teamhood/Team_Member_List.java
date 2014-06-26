package com.teamhood;

import com.teamhood.service.Get_Team_member_list;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Team_Member_List extends Activity implements OnClickListener{

	ImageView team_member_list_back,team_member_list_search;
	TextView team_member_list_header_text,team_member_list_header_one;
	LinearLayout team_member_list_team_name_list,team_member_list;
	SharedPreferences sp;
	ProgressDialog bar;
	int w,h;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.team_member_list);
		
		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		
		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		
//		LinearLayout.LayoutParams emailparams = new LinearLayout.LayoutParams(w-30, h-30);
//		emailparams.setMargins(w/32, w/32,w/32, w/32);
		team_member_list=(LinearLayout)findViewById(R.id.team_member_list);
//		team_member_list.setLayoutParams(emailparams);
		team_member_list_back=(ImageView)findViewById(R.id.team_member_list_back);
		team_member_list_back.setOnClickListener(this);
		team_member_list_header_text=(TextView)findViewById(R.id.team_member_list_header_text);
		team_member_list_header_text.setTypeface(font2);
		team_member_list_search=(ImageView)findViewById(R.id.team_member_list_search);
		team_member_list_header_one=(TextView)findViewById(R.id.team_member_list_header_one);
		team_member_list_header_one.setTypeface(font);
		team_member_list_team_name_list=(LinearLayout)findViewById(R.id.team_member_list_team_name_list);
		
		new Get_Team_member_list(Team_Member_List.this,sp.getString("username", ""),sp.getString("company_id", ""),sp.getString("team_id", ""),bar,sp).execute("main");
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.team_member_list_back:
			if(sp.getString("Team_Meamber_Screen", "").equalsIgnoreCase("Meassage_Screen")){
			Intent intent=new Intent(Team_Member_List.this,Create_Message.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			}else if(sp.getString("Team_Meamber_Screen", "").equalsIgnoreCase("Deah_Board")){
				Intent intent=new Intent(Team_Member_List.this,Dash_Board.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(sp.getString("Team_Meamber_Screen", "").equalsIgnoreCase("Meassage_Screen")){
			Intent intent=new Intent(Team_Member_List.this,Create_Message.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			}else if(sp.getString("Team_Meamber_Screen", "").equalsIgnoreCase("Deah_Board")){
				Intent intent=new Intent(Team_Member_List.this,Dash_Board.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
			}
	}

}
