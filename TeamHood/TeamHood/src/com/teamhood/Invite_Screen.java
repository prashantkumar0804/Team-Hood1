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
import com.teamhood.service.Get_Create_account;

public class Invite_Screen extends Activity implements OnClickListener{

	TextView invite_text_one,invite_text_email,invite_text_two,invite_text_third,invite_text_four,invite_create_account;
	EditText invite_password;
	ImageView invite_back;
	LinearLayout invite_screen;
	InputMethodManager imm;
	SharedPreferences sp;
	ProgressDialog bar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		//		Invite_Screen.this.overridePendingTransition(0, R.anim.exit_slide_down);
		setContentView(R.layout.invite_screen);

		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);

		invite_screen=(LinearLayout)findViewById(R.id.invite_screen);
		invite_screen.setOnClickListener(this);

		//		 Animation a = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
		//	        a.reset();
		//	       
		//	        invite_screen.clearAnimation();
		//	        invite_screen.startAnimation(a);

		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");

		invite_text_one=(TextView)findViewById(R.id.invite_text_one);
		invite_text_one.setTypeface(font);

		invite_text_email=(TextView)findViewById(R.id.invite_text_email);
		invite_text_email.setText(sp.getString("username", ""));
		invite_text_email.setTypeface(font2);

		invite_password=(EditText)findViewById(R.id.invite_password);
		invite_password.setTypeface(font);


		invite_text_two=(TextView)findViewById(R.id.invite_text_two);
		invite_text_two.setTypeface(font2);

		invite_text_third=(TextView)findViewById(R.id.invite_text_third);
		invite_text_third.setTypeface(font2);

		invite_text_four=(TextView)findViewById(R.id.invite_text_four);
		invite_text_four.setTypeface(font2);

		invite_create_account=(TextView)findViewById(R.id.invite_create_account);
		invite_create_account.setTypeface(font2);
		invite_create_account.setOnClickListener(this);

		invite_back=(ImageView)findViewById(R.id.invite_back);
		invite_back.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.invite_screen:
			imm.hideSoftInputFromWindow(invite_screen.getWindowToken(), 0);
			break;
		case R.id.invite_back:
			Intent intent=new Intent(Invite_Screen.this,MainActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			finish();
			break;

		case R.id.invite_create_account:
			if(Constant_URL.isNetworkAvailable(Invite_Screen.this))
			{
			if(invite_password.getText().toString().length()>2){
				new Get_Create_account(Invite_Screen.this, bar, invite_text_email.getText().toString(), invite_password.getText().toString(), sp).execute("main");
			} 
			else{
				Toast.makeText(Invite_Screen.this, "Please enter password.", 
						Toast.LENGTH_LONG).show();
			}
			}
			else{
				Toast.makeText(Invite_Screen.this, "Network not available.", 
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
		Intent intent=new Intent(Invite_Screen.this,MainActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
		finish();
	}

}
