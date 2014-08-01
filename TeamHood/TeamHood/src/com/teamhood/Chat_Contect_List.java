package com.teamhood;

import com.teamhood.service.Constant_URL;
import com.teamhood.service.Get_Chat_Contact_List;
import com.teamhood.service.Get_Team_member_list;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Chat_Contect_List extends Activity implements OnClickListener
{
	ImageView Chat_contact_list_back;
	ListView contact_list;
	SharedPreferences sp;
	ProgressDialog bar;
	int w,h;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat_contect_list);
		
		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);
		
		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();
		 
		Chat_contact_list_back=(ImageView)findViewById(R.id.Chat_contact_list_back);
		Chat_contact_list_back.setOnClickListener(this);
		contact_list=(ListView)findViewById(R.id.contact_list);
		
		if(Constant_URL.isNetworkAvailable(Chat_Contect_List.this))
		{
		new Get_Chat_Contact_List(Chat_Contect_List.this,sp.getString("email", ""),sp.getString("company_id", ""),sp.getString("team_id", ""),bar,sp,w,h,contact_list).execute("main");
		}
		else{
			Toast.makeText(Chat_Contect_List.this, "Network not available.", 
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Chat_contact_list_back:
			Intent intent=new Intent(Chat_Contect_List.this,Dash_Board.class);
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
		Intent intent=new Intent(Chat_Contect_List.this,Dash_Board.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
		finish();
	}

}
