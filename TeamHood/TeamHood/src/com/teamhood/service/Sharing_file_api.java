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
import android.widget.Toast;

import com.teamhood.R;
import com.teamhood.Time_bomb;

public class Sharing_file_api extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,username,company_id,team_id,responseBody,message,sending_time,sender_email;
	Context ctx;
	SharedPreferences preferences;
	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;
	String encodedImage;

	public Sharing_file_api(Context ctx,ProgressDialog bar, String username,String company_id,String team_id,String message,String sending_time,String sender_email, SharedPreferences sp, String encodedImage) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
		this.bar=bar;
		this.username=username;
		this.company_id=company_id;
		this.sp=sp;
		this.team_id=team_id;
		this.message=message;
		this.sending_time=sending_time;
		this.sender_email=sender_email;
		this.encodedImage=encodedImage;
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
			HttpPost httppost = new HttpPost(Constant_URL.FileSharing);

			try {
				// Add your data
				Log.d("email//company_name", username+"//"+company_id+"//"+message);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("email", username));

				//				nameValuePairs.add(new BasicNameValuePair("username", "ojus@applify.guru"));
				nameValuePairs.add(new BasicNameValuePair("company_id", company_id));
				nameValuePairs.add(new BasicNameValuePair("team_id", team_id));
				nameValuePairs.add(new BasicNameValuePair("file_name", message));
				nameValuePairs.add(new BasicNameValuePair("file_type", sending_time));
				nameValuePairs.add(new BasicNameValuePair("email", "prashantkumar@applify.guru"));
				nameValuePairs.add(new BasicNameValuePair("file", encodedImage));
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
					if(str1.trim().equalsIgnoreCase("\"Company already exist.\""))
					{
						if(bar.isShowing()){
							bar.dismiss();
						}
						Toast.makeText(ctx, "Company already exist.", 
								Toast.LENGTH_LONG).show();

					}else{
						JSONObject object1 = (JSONObject) new JSONTokener(str1).nextValue();
						if(object1.has("insert_id")){
						if(bar.isShowing()){
							bar.dismiss();
						}
//						SharedPreferences.Editor editer4 = sp.edit();
//						editer4.putString("company_id", object1.getString("company_id"));
//						editer4.putString("company_name", company_name);
//						
//						editer4.putString("AddType", "team_leder");
//						editer4.commit();
						Toast.makeText(ctx, "Your Task send on time.", 
								Toast.LENGTH_LONG).show();
						Intent intent=new Intent(ctx,Time_bomb.class);

						ctx.startActivity(intent);
						((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						((Activity) ctx).finish();

						}
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


