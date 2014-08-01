package com.teamhood.model;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teamhood.R;

public class Chat_Send_Adapter extends BaseAdapter {
	
	public static ArrayList<Chat_send_Model> Chat_send_Model1=new ArrayList<Chat_send_Model>();
	Context classreference;
	private LayoutInflater l_Inflater;
	int height, width;
	String send_msg_text;

	SharedPreferences sp;
	

	

	@SuppressWarnings("static-access")
	public Chat_Send_Adapter(Context ctx, int w, int h,
			SharedPreferences preferences, String send_msg_text2,ArrayList<Chat_send_Model> Chat_send_Model) {
		// TODO Auto-generated constructor stub
		l_Inflater = LayoutInflater.from(ctx);
		this.Chat_send_Model1=Chat_send_Model;
		this.classreference = ctx;
		this.height=h;
		this.width=w;
		this.sp=preferences;
		this.send_msg_text=send_msg_text2;
	}



	public int getCount() {
		Log.d("Chat_send_Model1.size", Chat_send_Model1.size()+"");
		return Chat_send_Model1.size();
	}

	public Object getItem(int position) {
		
		return Chat_send_Model1.get(position);
	}

	public long getItemId(int position) {
		
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.chat_room_item, null);
			holder = new ViewHolder();

			
			holder.From_Text = (TextView) convertView
					.findViewById(R.id.From_Text);
			holder.To_Text = (TextView) convertView
					.findViewById(R.id.To_Text);
			
			Typeface font = Typeface.createFromAsset(classreference.getAssets(), "NesobriteLt-Regular.ttf");
			Typeface font2 = Typeface.createFromAsset(classreference.getAssets(), "NesobriteRg-Bold.ttf");
			holder.From_Text.setTypeface(font);
			holder.To_Text.setTypeface(font);
			
			
			holder.From_lay=(LinearLayout)convertView.findViewById(R.id.From_lay);
			holder.To_lay=(LinearLayout)convertView.findViewById(R.id.To_lay);
			
			LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,height/12);
			
			holder.From_lay.setLayoutParams(params3);

			convertView.setTag(holder);
		} 
		else
			holder=(ViewHolder)convertView.getTag();

		try {

			Log.d("position", position+"");
			if(Chat_send_Model1.get(position).getMessage_status().equalsIgnoreCase("To")){
			
			holder.To_Text.setText(Chat_send_Model1.get(position).getMsg());
			holder.From_Text.setText("");
//			holder.From_lay.setVisibility(View.INVISIBLE);
			}else{
				holder.From_Text.setText(Chat_send_Model1.get(position).getMsg());
				holder.To_Text.setText("");
//				holder.To_lay.setVisibility(View.GONE);
			}
			
			/*holder.leads_quate_lay.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(classreference, ""+Member_List_getArray.get(position).getMember_email(), 
							Toast.LENGTH_LONG).show();
					SharedPreferences.Editor editor=sp.edit();
					
					editor.putString("From_email",Member_List_getArray.get(position).getMember_email());
					
					editor.commit();
					
					Intent leads_quate_lay=new Intent(classreference,Chat_Room.class);
					classreference.startActivity(leads_quate_lay);
				}
			});*/

			

			

		} catch (Exception e) {
			Log.d("Exception", e+"");
		}


		return convertView;
	}

	public static class ViewHolder {
		public TextView From_Text,To_Text;
		
		public ImageView image,icon_image;
		public LinearLayout From_lay,To_lay;
	}

}


