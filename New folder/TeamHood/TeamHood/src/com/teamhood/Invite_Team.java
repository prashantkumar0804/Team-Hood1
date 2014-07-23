package com.teamhood;

import java.util.ArrayList;

import com.teamhood.service.Constant_URL;
import com.teamhood.service.Get_Invite_Team_member;
import com.teamhood.service.Get_Invite_leaders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Invite_Team extends Activity implements OnClickListener {

	ImageView invite_team_back,invite_team_add_team;
	TextView invite_team_heder_text,invite_team_next;
	LinearLayout invite_team_main_lay;
	EditText email_text;
	ArrayList<String> Edit_Value;
	int Count=-1;
	int w,h,firstTime=4;
	Typeface font ;
	Typeface font2;
	private SharedPreferences sp;
	LinearLayout invite_team;
	InputMethodManager imm;
	
	ProgressDialog bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.invite_team);

		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();
		
		

		font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");

		invite_team=(LinearLayout)findViewById(R.id.invite_team);
		invite_team.setOnClickListener(this);

		invite_team_back=(ImageView)findViewById(R.id.invite_team_back);
		invite_team_back.setOnClickListener(this);
		invite_team_add_team=(ImageView)findViewById(R.id.invite_team_add_team);
		invite_team_add_team.setOnClickListener(this);
		invite_team_heder_text=(TextView)findViewById(R.id.invite_team_heder_text);
		invite_team_heder_text.setTypeface(font);
		if(sp.getString("AddType", "").equalsIgnoreCase("team_leder")){
			invite_team_heder_text.setText("Enter the email of the team leaders you want to\ninvite and add them in you company tree.");
		}
		if(sp.getString("AddType", "").equalsIgnoreCase("team_member")){
			invite_team_heder_text.setText("Enter the email of the team member you want to\ninvite and add them in you team tree.");

		}
		invite_team_main_lay=(LinearLayout)findViewById(R.id.invite_team_main_lay);
		invite_team_next=(TextView)findViewById(R.id.invite_team_next);
		invite_team_next.setTypeface(font2);
		invite_team_next.setOnClickListener(this);

		//		for(int i=0;i<firstTime;i++){
		addmember();
		//		}



	}

	private void addmember() {
		// TODO Auto-generated method stub
		for(int i=0;i<firstTime;i++){
			Count++;



			email_text = new EditText(this);
			LinearLayout.LayoutParams emailparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			emailparams.setMargins(0, w/32, 0, 0);
			email_text.setGravity(Gravity.CENTER);
			if(sp.getString("AddType", "").equalsIgnoreCase("team_leder")){
				email_text.setHint("Email (Team Leader)");
			}
			if(sp.getString("AddType", "").equalsIgnoreCase("team_member")){
				email_text.setHint("Email (Team member)");
			}
			email_text.setBackgroundResource(R.drawable.sign_in_edit_bg);
			email_text.setSingleLine(true);
			email_text.setTypeface(font);
			//		email_text.setTextColor(Color.parseColor("#000"));
			email_text.setId(Count);
			email_text.setTextColor(Color.BLACK);
			email_text.setTextSize(14);
//			email_text.setTextSize(getResources().getDimension(R.dimen.small_text_size));
			email_text.setSingleLine();
			if(firstTime==1){
				email_text.setFocusable(true);
			} 

			email_text.setLayoutParams(emailparams);

			invite_team_main_lay.addView(email_text);

		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.invite_team:
			imm.hideSoftInputFromWindow(invite_team.getWindowToken(), 0);
			break;
		case R.id.invite_team_add_team:
			firstTime=1;
			addmember();
			break;

		case R.id.invite_team_back:
			if(sp.getString("AddType", "").equalsIgnoreCase("team_leder")){

				Intent intent=new Intent(Invite_Team.this,Company_invite_team.class);
				startActivity(intent);

				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
				}
			if(sp.getString("AddType", "").equalsIgnoreCase("team_member")){
				Intent intent=new Intent(Invite_Team.this,Team_Leader_invite_Team_mamber.class);
				startActivity(intent);

				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
				finish();
			}

			break;

		case R.id.invite_team_next:
			if(Constant_URL.isNetworkAvailable(Invite_Team.this))
			{
			if(sp.getString("AddType", "").equalsIgnoreCase("team_leder")){

				try {
					Edit_Value=new ArrayList<String>();
					Log.d("Count", Count+"");
					for(int i=0;i<=Count;i++){

						EditText tx=(EditText) Invite_Team.this.findViewById(i);
						if(tx.getText().toString().length()>0){
						Edit_Value.add(tx.getText().toString());
						}
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				Log.d("array", Edit_Value.toString());
				
				new Get_Invite_leaders(Invite_Team.this,sp.getString("username", ""),sp.getString("company_name", ""),bar,sp,Edit_Value.toString()).execute("main");
			}
			if(sp.getString("AddType", "").equalsIgnoreCase("team_member")){
				try {
					Edit_Value=new ArrayList<String>();
					Log.d("Count", Count+"");
					for(int i=0;i<=Count;i++){

						EditText tx=(EditText) Invite_Team.this.findViewById(i);
						if(tx.getText().toString().length()>0){
						Edit_Value.add(tx.getText().toString());
						}
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				Log.d("array", Edit_Value.toString());
				new Get_Invite_Team_member(Invite_Team.this,sp.getString("username", ""),sp.getString("company_name", ""),sp.getString("team_name", ""),bar,sp,Edit_Value.toString()).execute("main");
			
			}

			}
			else{
				Toast.makeText(Invite_Team.this, "Network not available.", 
						Toast.LENGTH_LONG).show();
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
		if(sp.getString("AddType", "").equalsIgnoreCase("team_leder")){
			Intent intent=new Intent(Invite_Team.this,Company_invite_team.class);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();}
		if(sp.getString("AddType", "").equalsIgnoreCase("team_member")){
			Intent intent=new Intent(Invite_Team.this,Team_Leader_invite_Team_mamber.class);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
		}
	}

}
