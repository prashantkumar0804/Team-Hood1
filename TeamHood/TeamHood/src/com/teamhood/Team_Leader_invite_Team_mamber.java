package com.teamhood;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
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

import com.teamhood.service.Constant_URL;
import com.teamhood.service.Get_Create_Team;

public class Team_Leader_invite_Team_mamber extends Activity implements OnClickListener {

	ImageView team_member_back;
	TextView team_member_text,team_leder_invite_team_member;
	EditText team_member_companyname,team_member_teamname;
	Typeface font ;
	Typeface font2;
	private SharedPreferences sp;
	LinearLayout Team_Leader_invite_Team_mamber;
	InputMethodManager imm;
	ProgressDialog bar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.team_leader_invite_team_mamber);

		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");

		Team_Leader_invite_Team_mamber=(LinearLayout)findViewById(R.id.Team_Leader_invite_Team_mamber);
		Team_Leader_invite_Team_mamber.setOnClickListener(this);
		team_member_back=(ImageView)findViewById(R.id.team_member_back);
		team_member_back.setOnClickListener(this);
		team_member_text=(TextView)findViewById(R.id.team_member_text);
		team_member_text.setTypeface(font);
		team_member_companyname=(EditText)findViewById(R.id.team_member_companyname);
		team_member_companyname.setTypeface(font);
		team_member_teamname=(EditText)findViewById(R.id.team_member_teamname);
		team_member_teamname.setTypeface(font);
		team_leder_invite_team_member=(TextView)findViewById(R.id.team_leder_invite_team_member);
		team_leder_invite_team_member.setOnClickListener(this);
		team_leder_invite_team_member.setTypeface(font2);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.Team_Leader_invite_Team_mamber:
			imm.hideSoftInputFromWindow(Team_Leader_invite_Team_mamber.getWindowToken(), 0);
			break;
		case R.id.team_leder_invite_team_member:
			if(Constant_URL.isNetworkAvailable(Team_Leader_invite_Team_mamber.this))
			{
			if(team_member_companyname.getText().toString().trim().length()>2 && team_member_teamname.getText().toString().trim().length()>2){
				new Get_Create_Team(Team_Leader_invite_Team_mamber.this, bar, sp.getString("username", ""), team_member_companyname.getText().toString(), sp, team_member_teamname.getText().toString()).execute("main");

			}
			else{
				Toast.makeText(Team_Leader_invite_Team_mamber.this, "Please enter all data.", 
						Toast.LENGTH_LONG).show();
			}
			}
			else{
				Toast.makeText(Team_Leader_invite_Team_mamber.this, "Network not available.", 
						Toast.LENGTH_LONG).show();
			}
			/* SharedPreferences.Editor editer4 = sp.edit();

	  			editer4.putString("AddType", "team_member");
	  			editer4.commit();

			Intent intent1=new Intent(Team_Leader_invite_Team_mamber.this,Invite_Team.class);
         	startActivity(intent1);
         	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
         	finish();*/
			break;

		case R.id.team_member_back:
			Intent intent=new Intent(Team_Leader_invite_Team_mamber.this,Create_Account.class);
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
		Intent intent=new Intent(Team_Leader_invite_Team_mamber.this,Create_Account.class);
		startActivity(intent);

		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
		finish();
	}


}
