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

import com.teamhood.service.Get_forgot_password;

public class Forgot_Password extends Activity implements OnClickListener{

	ImageView forgot_password_back,forgot_password_cross;
	TextView forgot_password_header_one,forgot_password_header_two,forgot_password_send;
	EditText forget_password_edit;
	LinearLayout forgot_password;
	InputMethodManager imm;
	ProgressDialog bar;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.forgot_password);


		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");

		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);

		forgot_password=(LinearLayout)findViewById(R.id.forgot_password);
		forgot_password.setOnClickListener(this);

		forgot_password_back=(ImageView)findViewById(R.id.forgot_password_back);
		forgot_password_back.setOnClickListener(this);
		forgot_password_header_one=(TextView)findViewById(R.id.forgot_password_header_one);
		forgot_password_header_one.setTypeface(font2);
		//		forgot_password_header_two=(TextView)findViewById(R.id.forgot_password_header_two);
		//		forgot_password_header_two.setTypeface(font);
		forget_password_edit=(EditText)findViewById(R.id.forget_password_edit);
		forget_password_edit.setTypeface(font);
		forgot_password_cross=(ImageView)findViewById(R.id.forgot_password_cross);
		forgot_password_cross.setOnClickListener(this);
		forgot_password_send=(TextView)findViewById(R.id.forgot_password_send);
		forgot_password_send.setTypeface(font2);
		forgot_password_send.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.forgot_password:
			imm.hideSoftInputFromWindow(forgot_password.getWindowToken(), 0);
			break;
		case R.id.forgot_password_send:
			if(forget_password_edit.getText().toString().trim().length()>2){
				new Get_forgot_password(Forgot_Password.this, bar, forget_password_edit.getText().toString(),  sp).execute("main");
			}
			else{
				Toast.makeText(Forgot_Password.this, "Please enter Email or Username.", 
						Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.forgot_password_back:
			Intent intent=new Intent(Forgot_Password.this,MainActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_dwon, R.anim.slide_out_up);
			//			 finish();
			break;

		case R.id.forgot_password_cross:
			forget_password_edit.setText("");
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		//		Intent intent=new Intent(Forgot_Password.this,MainActivity.class);
		//     	startActivity(intent);
		//		 overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_right );
		//		 finish();
	}

}
