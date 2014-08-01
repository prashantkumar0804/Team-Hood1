package com.teamhood;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class All_Files_Screen extends Activity implements OnClickListener{

	ImageView all_files_upload,all_files_search,all_files_back;
	TextView all_files_header,All_files_Byeveryone,All_files_Byyou;
	LinearLayout All_files_Byyou_lay,All_files_Byeveryone_lay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.all_files_screen);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		
		all_files_upload=(ImageView)findViewById(R.id.all_files_upload);
		all_files_search=(ImageView)findViewById(R.id.all_files_search);
		all_files_header=(TextView)findViewById(R.id.all_files_header);
		all_files_header.setTypeface(font2);
		all_files_back=(ImageView)findViewById(R.id.all_files_back);
		all_files_back.setOnClickListener(this);
		All_files_Byeveryone=(TextView)findViewById(R.id.All_files_Byeveryone);
		All_files_Byeveryone.setTypeface(font);
		All_files_Byyou=(TextView)findViewById(R.id.All_files_Byyou);
		All_files_Byyou.setTypeface(font);
		All_files_Byyou_lay=(LinearLayout)findViewById(R.id.All_files_Byyou_lay);
		All_files_Byyou_lay.setOnClickListener(this);
		All_files_Byeveryone_lay=(LinearLayout)findViewById(R.id.All_files_Byeveryone_lay);
		All_files_Byeveryone_lay.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.all_files_back:
			Intent intent=new Intent(All_Files_Screen.this,File_Sharing.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;
			
		case R.id.All_files_Byyou_lay:
			
			All_files_Byyou_lay.setBackgroundColor(Color.parseColor("#000000"));
			All_files_Byeveryone_lay.setBackgroundColor(Color.parseColor("#ffffff"));
			All_files_Byeveryone.setTextColor(Color.parseColor("#000000"));
			All_files_Byyou.setTextColor(Color.parseColor("#db4d2e"));
			
			break;
			
		case R.id.All_files_Byeveryone_lay:
			All_files_Byyou_lay.setBackgroundColor(Color.parseColor("#ffffff"));
			All_files_Byeveryone_lay.setBackgroundColor(Color.parseColor("#000000"));
			All_files_Byeveryone.setTextColor(Color.parseColor("#db4d2e"));
			All_files_Byyou.setTextColor(Color.parseColor("#000000"));
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent=new Intent(All_Files_Screen.this,File_Sharing.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
		finish();
	}

}
