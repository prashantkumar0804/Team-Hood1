package com.teamhood.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.teamhood.Dash_Board;
import com.teamhood.Invite_Screen;
import com.teamhood.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Edit_profile extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,Company_id,team_id,responseBody,name,filePath;
	Context ctx;
	SharedPreferences preferences;
	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;

	

	

	public Edit_profile(Context ctx2, ProgressDialog bar2, String name,
			String Company_id, String team_id, String filePath,
			SharedPreferences sp2) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx2;
		this.bar=bar2;
		this.Company_id=Company_id;
		this.team_id=team_id;
		this.sp=sp2;
		this.name=name;
		this.filePath=filePath;
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
		Log.d("filePath", filePath);
		 File file1 = new File(filePath);
	        
	        String urlString = Constant_URL.editProfile;
	        try
	        {
	             HttpClient client = new DefaultHttpClient();
	             HttpPost post = new HttpPost(urlString);
//	             Toast.makeText(ctx,"test"+filePath, Toast.LENGTH_LONG).show();
	             
	             FileBody bin1 = new FileBody(file1);
//	             Toast.makeText(ctx,"test1"+bin1, Toast.LENGTH_LONG).show();
	             
	            
	             MultipartEntity reqEntity = new MultipartEntity();
	             reqEntity.addPart("email", new StringBody(name));
	             reqEntity.addPart("company_id", new StringBody(Company_id));
	             reqEntity.addPart("team_id", new StringBody(team_id));
	             reqEntity.addPart("pic_upload", bin1);
	               
//	             Toast.makeText(ctx,bin1+"", Toast.LENGTH_LONG).show();
	             post.setEntity(reqEntity);
	             HttpResponse response = client.execute(post);
	             HttpEntity resEntity;
	             resEntity = response.getEntity();
	             responseString = EntityUtils.toString(resEntity);
//	             Toast.makeText(ctx,"response_str"+response_str, Toast.LENGTH_LONG).show();
	             
	             /*if (resEntity != null) {
	                 Log.i("RESPONSE",response_str);
	                 runOnUiThread(new Runnable(){
	                        public void run() {
	                             try {
	                                res.setTextColor(Color.GREEN);
	                                res.setText("n Response from server : n " + response_str);
	                                Toast.makeText(getApplicationContext(),"Upload Complete. Check the server uploads directory.", Toast.LENGTH_LONG).show();
	                            } catch (Exception e) {
	                                e.printStackTrace();
	                            }
	                           }
	                    });
	             }*/
	        }
	        catch (Exception ex){
	             Log.e("Debug", "error: " + ex.getMessage(), ex);
//	             Toast.makeText(ctx,"ex"+ex, Toast.LENGTH_LONG).show();
	        }
		return null;
	}

	@Override
	protected void onPostExecute(Void result1) 
	{
		try {
			/*AlertDialog.Builder builder=new AlertDialog.Builder(ctx);  
			builder.setMessage(responseString);//Please enter your weight.
			builder.setNeutralButton("Ok", null);
			builder.show(); */
//			Toast.makeText(ctx, responseString, Toast.LENGTH_LONG).show();
			Log.d("responseString", responseString);
			JSONObject object = (JSONObject) new JSONTokener(responseString).nextValue();
			if(object.has("message")){
				if(object.getString("message").equalsIgnoreCase("OK")){
					String str = object.getString("response").replace("[", "");
					String str1 = str.replace("]", "");
					System.out.println(str1);
					if(str1.trim().equalsIgnoreCase("\"User name is invalid\"")){
						if(bar.isShowing()){
							bar.dismiss();
						}
						/*SharedPreferences.Editor editer4 = sp.edit();
						editer4.putString("username", username);
						editer4.putString("password", password);
						editer4.commit();

						Intent intent=new Intent(ctx,Invite_Screen.class);
//						Intent intent=new Intent(ctx,Create_Account.class);
						ctx.startActivity(intent);
						((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						((Activity) ctx).finish();*/
					}else{
//						JSONObject object1 = (JSONObject) new JSONTokener(str1).nextValue();

						if(bar.isShowing()){
							bar.dismiss();
						}
						
						
						/*SharedPreferences.Editor editer4 = sp.edit();
						editer4.putString("id", object1.getString("id"));
						editer4.putString("username", object1.getString("email"));
						editer4.putString("email", object1.getString("email"));
						
						editer4.putString("company_name", object1.getString("company_name"));
						editer4.putString("company_id", object1.getString("company_id"));
						editer4.putString("team_name", object1.getString("team_name"));
						editer4.putString("team_id", object1.getString("team_id"));
						editer4.putString("message", object1.getString("message"));
						editer4.putString("sending_time", object1.getString("sending_time"));
						editer4.putString("timebomb_message_id", object1.getString("timebomb_message_id"));
						editer4.putString("task", object1.getString("task"));
						
						editer4.putString("timebomb_task_time", object1.getString("timebomb_task_time"));
						editer4.putString("timebomb_task_id", object1.getString("timebomb_task_id"));
						
						
						
						editer4.commit();*/
						Intent intent=new Intent(ctx,Dash_Board.class);

						ctx.startActivity(intent);
						((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						((Activity) ctx).finish();


					}
				}

			}
			if(bar.isShowing()){
				bar.dismiss();
			}

		} catch (Exception e) {
			// TODO: handle exception
			Log.d("++Exception+", e+"");
			if(bar.isShowing()){
				bar.dismiss();
			}
			Toast.makeText(ctx, "invalid Authorization Required.", 
					Toast.LENGTH_LONG).show();
		}



	}
}

