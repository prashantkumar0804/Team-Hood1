package com.teamhood.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

import com.teamhood.model.Chat_Send_Adapter;
import com.teamhood.model.Chat_send_Model;

public class Get_Chat_Send extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,username,From_email,responseBody,email_list,Team_id,send_msg_text,show_new_msg;
	Context ctx;
	SharedPreferences preferences;
	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;
	int h,w;
	Chat_Send_Adapter Member_List_adp;
	ListView team_member_list_team_name_list;
	public Get_Chat_Send(Context ctx,String username,String From_email,ProgressDialog bar, SharedPreferences sp,int w,int h,ListView team_member_list_team_name_list, String send_msg_text, String show_new_msg) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
		this.bar=bar;
		this.username=username;
		this.From_email=From_email;
		this.sp=sp;
		this.send_msg_text=send_msg_text;
		this.w=w;
		this.h=h;
		this.team_member_list_team_name_list=team_member_list_team_name_list;
		this.show_new_msg=show_new_msg;
	}

	@Override
	protected void onPreExecute()
	{	
//		if(!show_new_msg.equalsIgnoreCase("")){
//		bar = new ProgressDialog(ctx);
//		bar.setMessage("Loading...");
//		bar.setIndeterminate(true);     
//		bar.show();
//		}
	}   

	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		try {

			if(send_msg_text.equalsIgnoreCase("")){
				

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
			}else{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(Constant_URL.chat_message_send);

			try {
				// Add your data
				Log.d("email//company_name", username+"//"+From_email+"//"+Team_id+"//"+email_list);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("from_email", username));

				nameValuePairs.add(new BasicNameValuePair("to_email",  From_email));
				nameValuePairs.add(new BasicNameValuePair("message", send_msg_text));
				
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
			}
		}catch(Exception e){
			Log.d("+++", e+"");
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result1) 
	{
		if(send_msg_text.equalsIgnoreCase("")){
			try {
				JSONObject object = (JSONObject) new JSONTokener(responseString).nextValue();
				if(object.has("message")){
					if(object.getString("message").equalsIgnoreCase("OK")){
						String str = object.getString("response").replace("[", "");
						String str1 = str.replace("]", "");
						System.out.println(str1);
						if(str1.trim().equalsIgnoreCase("\"No record found in this team.\""))
						{
//							if(bar.isShowing()){
//								bar.dismiss();
//							}
							Toast.makeText(ctx, "No record found in this team.", 
									Toast.LENGTH_LONG).show();

						}else{
							JSONArray object1 = new JSONArray(object.getString("response"));
							Log.d("string", object1.length()+"");
							
							Log.d("msg",object1.get(0)+"");
							String st2 = str1.replace("{", "[");
							String str3 = st2.replace("}", "]");
//							JSONObject object1 = (JSONObject) new JSONTokener(str1).nextValue();
//							JSONArray object1 = new JSONArray(str3);
//							String responseArray[]= str1.split(Pattern.quote("},"));
							Chat_Send_Adapter.Chat_send_Model1=new ArrayList<Chat_send_Model>();
							for(int ii=0;ii<object1.length();ii++){
//								JSONObject object2=(JSONObject) new JSONTokener(responseArray[ii]).nextValue();
//								String abc=object2.toString();
//								JSONArray responseArray=new JSONArray(abc);
//								for(int j=0;j<object2.length();j++){
									JSONObject object3=object1.getJSONObject(ii);
									Log.d("msg",object3.getString("massage"));

									Chat_send_Model  Chat_send_Model1=new Chat_send_Model();
									Chat_send_Model1.setId(object3.getString("id"));
									Chat_send_Model1.setMsg(object3.getString("massage"));
									Chat_send_Model1.setCreated(object3.getString("created"));
									Chat_send_Model1.setMessage_status(object3.getString("message_status"));
									Chat_send_Model1.setTo_email_user_id(object3.getString("to_email_user_id"));
									Chat_send_Model1.setFrom_email_user_id(object3.getString("from_email_user_id"));
									Chat_Send_Adapter.Chat_send_Model1.add(Chat_send_Model1);
									
								}
							Chat_Send_Adapter Member_List_adp=new Chat_Send_Adapter(ctx,w,h,preferences,send_msg_text,Chat_Send_Adapter.Chat_send_Model1);
							
//								if(object2.has("insert_id")){
//									
//									Chat_send_Model  Chat_send_Model1=new Chat_send_Model();
//									Chat_send_Model1.setMsg(send_msg_text);
//									Chat_Send_Adapter.Chat_send_Model1.add(Chat_send_Model1);
//									Chat_Send_Adapter Member_List_adp=new Chat_Send_Adapter(ctx,w,h,preferences,send_msg_text,Chat_Send_Adapter.Chat_send_Model1);
//									
//								}
//							
//							
									Member_List_adp.notifyDataSetChanged();
									
									team_member_list_team_name_list.setAdapter(Member_List_adp);
									team_member_list_team_name_list.setSelection(Member_List_adp.getCount() - 1);
							
//							}
//							if(bar.isShowing()){
//								bar.dismiss();
//							}
							/*Intent intent=new Intent(ctx,Dash_Board.class);

							ctx.startActivity(intent);
							((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
							((Activity) ctx).finish();*/


						}
					}

				}
//				if(bar.isShowing()){
//					bar.dismiss();
//				}

			} catch (Exception e) {
				// TODO: handle exception
				Log.d("++Exception1+", e+"");
				Toast.makeText(ctx, "invalid Authorization Required.", 
						Toast.LENGTH_LONG).show();
//				if(bar.isShowing()){
//					bar.dismiss();
//				}
			}

		}else{
		try {
			JSONObject object = (JSONObject) new JSONTokener(responseString).nextValue();
			if(object.has("message")){
				if(object.getString("message").equalsIgnoreCase("OK")){
					String str = object.getString("response").replace("[", "");
					String str1 = str.replace("]", "");
					System.out.println(str1);
					if(str1.trim().equalsIgnoreCase("\"No record found in this team.\""))
					{
//						if(bar.isShowing()){
//							bar.dismiss();
//						}
						Toast.makeText(ctx, "No record found in this team.", 
								Toast.LENGTH_LONG).show();

					}else{
						JSONObject object1 = (JSONObject) new JSONTokener(object.getString("response")).nextValue();
//						JSONArray object1 = new JSONArray(object.getString("response"));

//						for(int ii=0;ii<object1.length();ii++){
//							JSONObject object2=object1.getJSONObject(ii);
							if(object1.has("insert_id")){
								
								Chat_send_Model  Chat_send_Model1=new Chat_send_Model();
								Chat_send_Model1.setId(object1.getString("insert_id"));
								
								Chat_send_Model1.setCreated("3:00");
								Chat_send_Model1.setMessage_status("To");
								Chat_send_Model1.setTo_email_user_id(sp.getString("From_ID", ""));
								Chat_send_Model1.setFrom_email_user_id(sp.getString("id", ""));
								
								
								Chat_send_Model1.setMsg(send_msg_text);
								Chat_Send_Adapter.Chat_send_Model1.add(Chat_send_Model1);
								Chat_Send_Adapter Member_List_adp=new Chat_Send_Adapter(ctx,w,h,preferences,send_msg_text,Chat_Send_Adapter.Chat_send_Model1);
								
//							}
						
						
								Member_List_adp.notifyDataSetChanged();
								
								team_member_list_team_name_list.setAdapter(Member_List_adp);
								team_member_list_team_name_list.setSelection(Member_List_adp.getCount() - 1);
								SharedPreferences.Editor editer4 = sp.edit();
								editer4.putString("send_hit", "0");
								
								editer4.commit();
						}
//						if(bar.isShowing()){
//							bar.dismiss();
//						}
						/*Intent intent=new Intent(ctx,Dash_Board.class);

						ctx.startActivity(intent);
						((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						((Activity) ctx).finish();*/


					}
				}

			}
//			if(bar.isShowing()){
//				bar.dismiss();
//			}

		} catch (Exception e) {
			// TODO: handle exception
			Log.d("++Exception+", e+"");
			Toast.makeText(ctx, "invalid Authorization Required.", 
					Toast.LENGTH_LONG).show();
//			if(bar.isShowing()){
//				bar.dismiss();
//			}
		}

		}

	}
}




