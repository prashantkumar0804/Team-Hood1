package com.teamhood;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.teamhood.backgroung_service.MyService;
import com.teamhood.model.Chat_Send_Adapter;
import com.teamhood.model.Chat_send_Model;
import com.teamhood.service.Constant_URL;
import com.teamhood.service.Get_Chat_Send;

public class Chat_Room extends Activity implements OnClickListener{

	ImageView chat_room_back;
	TextView Chat_room_From_name;
	static ListView chat_room_list;
	EditText send_msg_text;
	Button send_msg_btn;
	static SharedPreferences sp;
	static ProgressDialog bar;
	static int w;
	static int h;
	static Context ctx;
	static String show_new_msg="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat_room);

		sp = this.getSharedPreferences("TeamHood", MODE_PRIVATE);

		Display display = getWindowManager().getDefaultDisplay();
		w=display.getWidth();
		h=display.getHeight();

		 ctx=Chat_Room.this;
		chat_room_back=(ImageView)findViewById(R.id.chat_room_back);
		chat_room_back.setOnClickListener(this);
		Chat_room_From_name=(TextView)findViewById(R.id.Chat_room_From_name);
		Chat_room_From_name.setText(sp.getString("From_email", ""));
		chat_room_list=(ListView)findViewById(R.id.chat_room_list);
		send_msg_text=(EditText)findViewById(R.id.send_msg_text);
		send_msg_btn=(Button)findViewById(R.id.send_msg_btn);
		send_msg_btn.setOnClickListener(this);

//		Chat_send_Model  Chat_send_Model=new Chat_send_Model();
//		Chat_send_Model.setMsg("");
//		Chat_Send_Adapter.Chat_send_Model.add(Chat_send_Model);
//		
//		chat_room_list.setAdapter(new Chat_Send_Adapter(ctx,w,h,sp,send_msg_text.getText().toString(),Chat_Send_Adapter.Chat_send_Model));
		
		startService(new Intent(this, MyService.class));
		Chat_Send_Adapter.Chat_send_Model1=new ArrayList<Chat_send_Model>();
//		Chat_send_Model  Chat_send_Model1=null;
		if(Constant_URL.isNetworkAvailable(Chat_Room.this))
		{
			show_new_msg="";
			new Get_Chat_Send(Chat_Room.this,sp.getString("email", ""),sp.getString("From_email", ""),bar,sp,w,h,chat_room_list,"",show_new_msg).execute("main");
		}
		else{
			Toast.makeText(Chat_Room.this, "Network not available.", 
					Toast.LENGTH_LONG).show();
		}
		SharedPreferences.Editor editer4 = sp.edit();
		editer4.putString("send_hit", "0");
		
		editer4.commit();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.chat_room_back:
			SharedPreferences.Editor editer = sp.edit();
			editer.putString("send_hit", "1");
			
			editer.commit();
			stopService(new Intent(this, MyService.class));
			Intent intent=new Intent(Chat_Room.this,Chat_Contect_List.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			break;

		case R.id.send_msg_btn:
			if(Constant_URL.isNetworkAvailable(Chat_Room.this))
			{
				SharedPreferences.Editor editer4 = sp.edit();
				editer4.putString("send_hit", "1");
				
				editer4.commit();
				show_new_msg="";
				new Get_Chat_Send(Chat_Room.this,sp.getString("email", ""),sp.getString("From_email", ""),bar,sp,w,h,chat_room_list,send_msg_text.getText().toString(),show_new_msg).execute("main");
			}
			else{
				Toast.makeText(Chat_Room.this, "Network not available.", 
						Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}
	}

	public static void show_new_msg(){
		if(sp.getString("send_hit", "").equalsIgnoreCase("0"))
		{
			show_new_msg="1";
			new Get_Chat_Send(ctx,sp.getString("email", ""),sp.getString("From_email", ""),bar,sp,w,h,chat_room_list,"",show_new_msg).execute("main");
		}
//		else{
//			Toast.makeText(ctx, "Network not available.", 
//					Toast.LENGTH_LONG).show();
//		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		SharedPreferences.Editor editer = sp.edit();
		editer.putString("send_hit", "1");
		
		editer.commit();
		Intent intent=new Intent(Chat_Room.this,Chat_Contect_List.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
		finish();
	}
}
