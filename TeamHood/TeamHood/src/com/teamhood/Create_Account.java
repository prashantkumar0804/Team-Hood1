package com.teamhood;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class Create_Account extends Activity implements OnClickListener{

	TextView company_text;
	TextView create_company,create_team;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.create_account);

		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");

		company_text=(TextView)findViewById(R.id.company_text);
		company_text.setTypeface(font);
		create_company=(TextView)findViewById(R.id.create_company);
		create_company.setTypeface(font2);
		create_company.setOnClickListener(this);
		create_team=(TextView)findViewById(R.id.create_team);
		create_team.setTypeface(font2);
		create_team.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.create_company:
			Intent intent=new Intent(Create_Account.this,Company_invite_team.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;

		case R.id.create_team:
			Intent intent1=new Intent(Create_Account.this,Team_Leader_invite_Team_mamber.class);
			startActivity(intent1);
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
		Intent intent=new Intent(Create_Account.this,Invite_Screen.class);
		startActivity(intent);

		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
		finish();
	}

}
