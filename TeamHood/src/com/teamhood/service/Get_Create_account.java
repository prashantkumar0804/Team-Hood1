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

import com.teamhood.Create_Account;
import com.teamhood.R;

public class Get_Create_account  extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,username,password,responseBody;
	Context ctx;
	SharedPreferences preferences;
	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;

	public Get_Create_account(Context ctx,ProgressDialog bar, String username,String password, SharedPreferences sp) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
		this.bar=bar;
		this.username=username;
		this.password=password;
		this.sp=sp;
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
			HttpPost httppost = new HttpPost(Constant_URL.Create_Account);

			try {
				// Add your data
				Log.d("username//passowrd", username+"//"+password);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("email", username));
				//			        nameValuePairs.add(new BasicNameValuePair("email", username));
				nameValuePairs.add(new BasicNameValuePair("password", password));
				//			        nameValuePairs.add(new BasicNameValuePair("company_name", ""));
				//			        nameValuePairs.add(new BasicNameValuePair("lavel", ""));

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

					if(str1.trim().equalsIgnoreCase("\"Invalid email format\"")){

						Toast.makeText(ctx, "Invalid email format.", 
								Toast.LENGTH_LONG).show();
					}
					else if(str1.trim().equalsIgnoreCase("\"Email already exist.\"")){
						Toast.makeText(ctx, "Email already exist.", 
								Toast.LENGTH_LONG).show();
					}else{

						JSONObject object1 = (JSONObject) new JSONTokener(str1).nextValue();

						if(object1.has("id")){

							if(bar.isShowing()){   
								bar.dismiss();
							}
							SharedPreferences.Editor editer4 = sp.edit();
							editer4.putString("username", username);
							editer4.putString("id", object1.getString("id"));
							editer4.commit();
							Intent intent=new Intent(ctx,Create_Account.class);

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
