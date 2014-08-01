package com.teamhood.backgroung_service;

import com.teamhood.Chat_Room;
import com.teamhood.service.Constant_URL;
import com.teamhood.service.Get_Chat_Send;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service{

	private static final String TAG = "MyService";
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		
		Toast.makeText(this, "Congrats! MyService Created", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onCreate");
	}
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		
		final Handler handler = new Handler();
		

		Runnable runnable = new Runnable() 
		{

		    public void run() 
		    {
		         //
		         // Do the stuff
		         //3
		    	Log.d("+++", "++++++");
		    	callServiceProcess();
		         handler.postDelayed(this, 5000);
		    }
		};
		runnable.run();
		/*Runnable r = new Runnable() {
			public void run() {

				while (true) {
					
					
					
					callServiceProcess();	
						
					
				}

			}
			
		};

		Thread t = new Thread(r);
		t.start();*/
		
		return Service.START_STICKY;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");	
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
	}
	protected synchronized void callServiceProcess() {
		Chat_Room.show_new_msg();
	}
}
