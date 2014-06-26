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

import com.teamhood.service.Get_Create_company;

public class Company_invite_team extends Activity implements OnClickListener{

	ImageView company_team_back;
	TextView comany_team_text,company_team_invite_team_leader;
	EditText company_team_companyname;
	private SharedPreferences sp;
	LinearLayout company_invite_team;
	InputMethodManager imm;
	ProgressDialog bar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.company_invite_team);

		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		company_team_back=(ImageView)findViewById(R.id.company_team_back);
		company_team_back.setOnClickListener(this);

		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");

		company_invite_team=(LinearLayout)findViewById(R.id.company_invite_team);
		company_invite_team.setOnClickListener(this);
		comany_team_text=(TextView)findViewById(R.id.comany_team_text);
		comany_team_text.setTypeface(font);
		company_team_companyname=(EditText)findViewById(R.id.company_team_companyname);
		company_team_companyname.setTypeface(font);
		company_team_invite_team_leader=(TextView)findViewById(R.id.company_team_invite_team_leader);
		company_team_invite_team_leader.setTypeface(font2);
		company_team_invite_team_leader.setOnClickListener(this);

	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.company_invite_team:
			imm.hideSoftInputFromWindow(company_invite_team.getWindowToken(), 0);
			break;
		case R.id.company_team_back:
			Intent intent=new Intent(Company_invite_team.this,Create_Account.class);
			startActivity(intent);

			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right  );
			finish();
			break;

		case R.id.company_team_invite_team_leader:

			if(company_team_companyname.getText().toString().trim().length()>2){
				new Get_Create_company(Company_invite_team.this, bar, sp.getString("username", ""), company_team_companyname.getText().toString(), sp).execute("main");
			}else{
				Toast.makeText(Company_invite_team.this, "Please enter company name.", 
						Toast.LENGTH_LONG).show();
			}
			/*SharedPreferences.Editor editer4 = sp.edit();
  			editer4.putString("AddType", "team_leder");
  			editer4.commit();
			Intent intent1=new Intent(Company_invite_team.this,Invite_Team.class);
         	startActivity(intent1);
         	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
         	finish();*/
			break;
		default:
			break;
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent=new Intent(Company_invite_team.this,Create_Account.class);
		startActivity(intent);

		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
		finish();
	}

}
