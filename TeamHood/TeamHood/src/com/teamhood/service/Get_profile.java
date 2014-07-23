package com.teamhood.service;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.teamhood.Dash_Board;
import com.teamhood.Invite_Screen;
import com.teamhood.R;
import com.trigma.imagehandler.ImageLoader;
import com.trigma.imagehandler.ImageloaderSecond;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class Get_profile extends AsyncTask<String , Integer, Void>{

	String auth1,auth2,responseBody,name;
	Context ctx;
	SharedPreferences preferences;
	ProgressDialog bar;
	String responseString;
	SharedPreferences sp;
	ImageView image_employee;
	ImageloaderSecond s;
	int w,h,rotation;
	

	public Get_profile(Context ctx2, ProgressDialog bar2, String name,SharedPreferences sp2, ImageView image_employee,int w,int h,int rotation) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx2;
		this.bar=bar2;
		
		this.sp=sp2;
		this.name=name;
		this.image_employee=image_employee;
		s=new ImageloaderSecond(ctx2);
		this.w=w;
		this.h=h;
		this.rotation=rotation;
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
		
	        
	        String urlString = Constant_URL.Get_profile;
	        try
	        {
	             HttpClient client = new DefaultHttpClient();
	             HttpPost post = new HttpPost(urlString);
//	             Toast.makeText(ctx,"test", Toast.LENGTH_LONG).show();
	             
	            
//	             Toast.makeText(ctx,"test1"+bin1, Toast.LENGTH_LONG).show();
	             
	            
	             MultipartEntity reqEntity = new MultipartEntity();
	             reqEntity.addPart("email", new StringBody(name));
	            
	               
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
						editer4.commit();*/

						Intent intent=new Intent(ctx,Invite_Screen.class);
//						Intent intent=new Intent(ctx,Create_Account.class);
						ctx.startActivity(intent);
						((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						((Activity) ctx).finish();
					}else{
						JSONObject object1 = (JSONObject) new JSONTokener(str1).nextValue();

						
						
						
						SharedPreferences.Editor editer4 = sp.edit();
						editer4.putString("id", object1.getString("id"));
						editer4.putString("username", object1.getString("email"));
						editer4.putString("email", object1.getString("email"));
						
						editer4.putString("pick_url", object1.getString("pick_url"));
						
						
						editer4.commit();
						s.DisplayImage(object1.getString("pick_url"), image_employee,w,h,rotation);
						/*Intent intent=new Intent(ctx,Dash_Board.class);

						ctx.startActivity(intent);
						((Activity) ctx).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
						((Activity) ctx).finish();*/
						if(bar.isShowing()){
							bar.dismiss();
						}

					}
				}

			}
			

		} catch (Exception e) {
			// TODO: handle exception
			if(bar.isShowing()){
				bar.dismiss();
			}
			Log.d("++Exception+", e+"");
			Toast.makeText(ctx, "invalid Authorization Required.", 
					Toast.LENGTH_LONG).show();
		}



	}
}


