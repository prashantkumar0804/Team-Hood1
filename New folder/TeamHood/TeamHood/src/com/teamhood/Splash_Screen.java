package com.teamhood;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class Splash_Screen extends Activity {

	 String result=null;
		SharedPreferences preferences,w;
		String loginstatus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
		
		try {
			new Handler().postDelayed(new Runnable(){
				public void run() {

					preferences = (Splash_Screen.this).getSharedPreferences("TeamHood", MODE_PRIVATE);
					if(preferences.getString("loginstatus","").equalsIgnoreCase("1"))
					{
						Intent mainIntent = new Intent(Splash_Screen.this,Dash_Board.class);
						Splash_Screen.this.startActivity(mainIntent); 
						Splash_Screen.this.finish();
					}else
					{
						Intent mainIntent = new Intent(Splash_Screen.this,MainActivity.class);
						Splash_Screen.this.startActivity(mainIntent); 
						Splash_Screen.this.finish();
					}
				}

			}, 3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	}
	
	


