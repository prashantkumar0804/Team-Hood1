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
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.teamhood.Team_Member_List;
import com.teamhood.model.Chat_Contact_List_Adapter;
import com.teamhood.model.Member_List_Model;

public class Get_Chat_Conversion extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,username,From_email,responseBody,email_list,Team_id;
	Context ctx;
	SharedPreferences preferences;
	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;
	int h,w;
	Chat_Contact_List_Adapter Member_List_adp;
	ListView team_member_list_team_name_list;
	public Get_Chat_Conversion(Context ctx,String username,String From_email,ProgressDialog bar, SharedPreferences sp,int w,int h,ListView team_member_list_team_name_list) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
		this.bar=bar;
		this.username=username;
		this.From_email=From_email;
		this.sp=sp;
		
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
			HttpPost httppost = new HttpPost(Constant_URL.chat_conversion);

			try {
				// Add your data
				Log.d("email//company_name", username+"//"+From_email+"//"+Team_id+"//"+email_list);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("from_email", username));

				nameValuePairs.add(new BasicNameValuePair("to_email",  From_email));
				
				
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
						JSONArray object1 = new JSONArray(object.getString("response"));

						for(int ii=0;ii<object1.length();ii++){
							JSONObject object2=object1.getJSONObject(ii);
						SharedPreferences.Editor editer4 = sp.edit();
						editer4.putString("team_member_email", object2.getString("team_member_email"));
						
						editer4.commit();
						String str2[]=object2.getString("team_member_email").split(",");
						String str3[]=object2.getString("member_ids").split(",");
						for(int i=0;i<str2.length;i++){
							Member_List_Model  Member_List_obj=new Member_List_Model();
							Member_List_obj.setMember_id(str3[i]);
							Member_List_obj.setMember_email(str2[i]);
							Log.d("email", str2[i]);
							Team_Member_List.Member_List_arrayList.add(Member_List_obj);
							Member_List_adp=new Chat_Contact_List_Adapter(ctx, Team_Member_List.Member_List_arrayList,w,h,preferences);
							
						}
						
						team_member_list_team_name_list.setAdapter(Member_List_adp);
						}
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
			if(bar.isShowing()){
				bar.dismiss();
			}
		}

		

	}
}



