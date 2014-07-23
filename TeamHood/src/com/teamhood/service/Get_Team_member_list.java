package com.teamhood.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.teamhood.Dash_Board;
import com.teamhood.R;
import com.teamhood.Team_Member_List;
import com.teamhood.model.Member_List_Adapter;
import com.teamhood.model.Member_List_Model;

public class Get_Team_member_list extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,username,company_id,responseBody,email_list,Team_id;
	Context ctx;
	SharedPreferences preferences;
	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;
	int h,w;
	Member_List_Adapter Member_List_adp;
	ListView team_member_list_team_name_list;
	public Get_Team_member_list(Context ctx,String username,String company_id, String Team_id, ProgressDialog bar, SharedPreferences sp,int w,int h,ListView team_member_list_team_name_list) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
		this.bar=bar;
		this.username=username;
		this.company_id=company_id;
		this.sp=sp;
		this.email_list=email_list;
		this.Team_id=Team_id;
		this.w=w;
		this.h=h;
		this.team_member_list_team_name_list=team_member_list_team_name_list;
	}

	@Override
	protected void onPreExecute()
	{	
		bar = new ProgressDialog(ctx);
		bar.setMessage("Loading...");
		bar.setIndeterminate(true);     
		bar.show();
	}   

	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(Constant_URL.Team_mamber_list);

			try {
				// Add your data
				Log.d("email//company_name", username+"//"+company_id+"//"+Team_id+"//"+email_list);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("email", username));

				//nameValuePairs.add(new BasicNameValuePair("username", "ojus@applify.guru"));
				nameValuePairs.add(new BasicNameValuePair("company_id", company_id));
				nameValuePairs.add(new BasicNameValuePair("team_id", Team_id));
				
//				nameValuePairs.add(new BasicNameValuePair("company_id", "1"));
//				nameValuePairs.add(new BasicNameValuePair("team_id", "1"));
				
				
				httppost.setEntity(new  UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				responseString = new BasicResponseHandler().handleResponse(response);
				System.out.println(responseString);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}

		}catch(Exception e){
			Log.d("+++", e+"");
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result1) 
	{
		try {
			JSONObject object = (JSONObject) new JSONTokener(responseString).nextValue();
			if(object.has("message")){
				if(object.getString("message").equalsIgnoreCase("OK")){
					String str = object.getString("response").replace("[", "");
					String str1 = str.replace("]", "");
					System.out.println(str1);
					if(str1.trim().equalsIgnoreCase("\"No record found in this team.\""))
					{
						if(bar.isShowing()){
							bar.dismiss();
						}
						Toast.makeText(ctx, "No record found in this team.", 
								Toast.LENGTH_LONG).show();

					}else{
						JSONObject object1 = (JSONObject) new JSONTokener(str1).nextValue();

						
						SharedPreferences.Editor editer4 = sp.edit();
						editer4.putString("team_member_email", object1.getString("team_member_email"));
						
						editer4.commit();
						String str2[]=object1.getString("team_member_email").split(",");
						for(int i=0;i<str2.length;i++){
							Member_List_Model  Member_List_obj=new Member_List_Model();
							Member_List_obj.setMember_id(i+"");
							Member_List_obj.setMember_email(str2[i]);
							Log.d("email", str2[i]);
							Team_Member_List.Member_List_arrayList.add(Member_List_obj);
							Member_List_adp=new Member_List_Adapter(ctx, Team_Member_List.Member_List_arrayList,w,h,preferences);
							
						}
						
						team_member_list_team_name_list.setAdapter(Member_List_adp);
						if(bar.isShowing()){
							bar.dismiss();
						}
						/*Intent intent=new Intent(ctx,Dash_Board.class);

						ctx.startActivity(intent);
						((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						((Activity) ctx).finish();*/


					}
				}

			}
			if(bar.isShowing()){
				bar.dismiss();
			}

		} catch (Exception e) {
			// TODO: handle exception
			Log.d("++Exception+", e+"");
			Toast.makeText(ctx, "invalid Authorization Required.", 
					Toast.LENGTH_LONG).show();
		}

		if(bar.isShowing()){
			bar.dismiss();
		}

	}
}


