package com.teamhood.model;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
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

public class Member_List_Adapter extends BaseAdapter {
	public static ArrayList<Member_List_Model> Member_List_getArray;
	Context classreference;
	private LayoutInflater l_Inflater;
	int height, width;

	SharedPreferences sp;
	public Member_List_Adapter(Context context, ArrayList<Member_List_Model> leads_getArray1,int w,int h, SharedPreferences preferences) {
		Member_List_getArray = leads_getArray1;
		l_Inflater = LayoutInflater.from(context);
		classreference = context;
		height=h;
		width=w;
		sp=preferences;
	}

	public int getCount() {
		Log.d("leads_getArray", Member_List_getArray.size()+"");
		return Member_List_getArray.size();
	}

	public Object getItem(int position) {
		
		return Member_List_getArray.get(position);
	}

	public long getItemId(int position) {
		
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.item_member_list, null);
			holder = new ViewHolder();

			
			holder.member_list_email = (TextView) convertView
					.findViewById(R.id.member_list_email);
			Typeface font = Typeface.createFromAsset(classreference.getAssets(), "NesobriteLt-Regular.ttf");
			Typeface font2 = Typeface.createFromAsset(classreference.getAssets(), "NesobriteRg-Bold.ttf");
			holder.member_list_email.setTypeface(font);
			
			
			holder.leads_quate_lay=(LinearLayout)convertView.findViewById(R.id.member_list_lay);
			
			LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT,height/12);
			
			holder.leads_quate_lay.setLayoutParams(params3);

			convertView.setTag(holder);
		} 
		else
			holder=(ViewHolder)convertView.getTag();

		try {

			Log.d("position", position+"");
			
			holder.member_list_email.setText(""+Member_List_getArray.get(position).getMember_email());
			
			holder.leads_quate_lay.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					/*SharedPreferences.Editor editor=sp.edit();
					
					editor.putString("Leads_title",""+leads_getArray.get(position).getLeads_title());
					editor.putString("Leads_locality",""+leads_getArray.get(position).getLeads_locality()+", "+""+leads_getArray.get(position).getLeads_region()+", "+""+leads_getArray.get(position).getLeads_distanceAway()+"Km");
					editor.putString("Leads_description",""+leads_getArray.get(position).getLeads_description());
					editor.putString("jobOwnerFirstName",leads_getArray.get(position).getLeads_jobOwnerFirstName());
					editor.putString("Price","");
					editor.putString("Quotes","send quotes");
					editor.putString("Leads_Id",leads_getArray.get(position).getLeads_id());
					editor.commit();
					
					Intent leads_quate_lay=new Intent(classreference,Product_Description.class);
					classreference.startActivity(leads_quate_lay);*/
				}
			});

			

			

		} catch (Exception e) {
			Log.d("Exception", e+"");
		}


		return convertView;
	}

	public static class ViewHolder {
		public TextView member_list_email;
		
		public ImageView image,icon_image;
		public LinearLayout leads_quate_lay,set;
	}

}
