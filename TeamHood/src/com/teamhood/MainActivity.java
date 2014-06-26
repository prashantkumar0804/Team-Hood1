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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamhood.service.Get_Team_member_list;
import com.teamhood.service.Get_login;

public class MainActivity extends Activity implements OnClickListener {

	TextView sign_in_forgot_password,sign_in_botton_text_one,sign_in_botton_text_two,sign_in_next,sign_in_edit_heder_text_one,sign_in_edit_heder_text_two;
	EditText sign_in_email_edit,sign_in_password_edit;
	LinearLayout activity_main;
	InputMethodManager imm;
	Context ctx;
	ProgressDialog pro;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.activity_main);

		//		InputMethodManager imm = (InputMethodManager)getSystemService(
		//			      MainActivity.this.INPUT_METHOD_SERVICE);

		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);

		ctx=MainActivity.this;
		sign_in_edit_heder_text_one=(TextView)findViewById(R.id.sign_in_edit_heder_text_one);

		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		sign_in_edit_heder_text_one.setTypeface(font);

		activity_main=(LinearLayout)findViewById(R.id.activity_main);
		activity_main.setOnClickListener(this);

		sign_in_edit_heder_text_two=(TextView)findViewById(R.id.sign_in_edit_heder_text_two);

		sign_in_edit_heder_text_two.setTypeface(font);

		sign_in_email_edit=(EditText)findViewById(R.id.sign_in_email_edit);
		sign_in_email_edit.setTypeface(font);

		sign_in_password_edit=(EditText)findViewById(R.id.sign_in_password_edit);
		sign_in_password_edit.setTypeface(font);

		sign_in_forgot_password=(TextView)findViewById(R.id.sign_in_forgot_password);
		sign_in_forgot_password.setTypeface(font);
		sign_in_forgot_password.setOnClickListener(this);

		//		imm.hideSoftInputFromWindow(sign_in_email_edit.getWindowToken(), 0);

		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		sign_in_next=(TextView)findViewById(R.id.sign_in_next);
		sign_in_next.setTypeface(font2);
		sign_in_next.setOnClickListener(this);


		Typeface font1 = Typeface.createFromAsset(getAssets(), "NesobriteSe-Regular.ttf");

		sign_in_botton_text_one=(TextView)findViewById(R.id.sign_in_botton_text_one);
		sign_in_botton_text_one.setTypeface(font1);

		sign_in_botton_text_two=(TextView)findViewById(R.id.sign_in_botton_text_two);
		sign_in_botton_text_two.setTypeface(font1);


	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.sign_in_next:

			if(sign_in_email_edit.getText().toString().length()>2 && sign_in_password_edit.getText().toString().length()>2){
				new Get_login(ctx, pro,sign_in_email_edit.getText().toString(),sign_in_password_edit.getText().toString(),sp).execute("main");
			}
			else{
				Toast.makeText(MainActivity.this, "Please enter all data.", 
						Toast.LENGTH_LONG).show();
				
			}

			break;

		case R.id.sign_in_forgot_password:
			Intent intent1=new Intent(MainActivity.this,Forgot_Password.class);
			//			overridePendingTransition(R.anim.right_to_left, 0);


			startActivity(intent1);
			overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_dwon);
			//         	finish();


			break;

		case R.id.activity_main:
			imm.hideSoftInputFromWindow(activity_main.getWindowToken(), 0);
			break;
		default:
			break;
		}

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
		finish();
	}




}
