package com.teamhood.service;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constant_URL {

	public static String Login="http://teamhood.applifytech.com/index.php/login";
	public static String Create_Account="http://teamhood.applifytech.com/index.php/registration";
	public static String Create_company="http://teamhood.applifytech.com/index.php/createCompany";
	public static String Create_team="http://teamhood.applifytech.com/index.php/createTeam";
	public static String Forgot_password="http://teamhood.applifytech.com/index.php/forgotPassword";
	public static String invite_team_leaders="http://teamhood.applifytech.com/index.php/inviteTeamLeaders";
	public static String invite_team_mamber="http://teamhood.applifytech.com/index.php/inviteTeamMember";
	public static String Team_mamber_list="http://teamhood.applifytech.com/index.php/teamMemberList";
	public static String Time_Bomb_Message="http://teamhood.applifytech.com/index.php/timeBombMessage";
	public static String Time_Bomb_Task="http://teamhood.applifytech.com/index.php/timeBombTask";
	public static String Get_profile="http://teamhood.applifytech.com/index.php/getProfile";
	public static String FileSharing="http://teamhood.applifytech.com/index.php/fileSharing";
	public static String editProfile="http://teamhood.applifytech.com/index.php/editProfile";
	
	public static boolean isNetworkAvailable(Context context) {
		// TODO Auto-generated method stub
		boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	    try {
	    	ConnectivityManager cm = (ConnectivityManager) ((Activity)context).getSystemService(Context.CONNECTIVITY_SERVICE);
	 	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	 	    for (NetworkInfo ni : netInfo) {
	 	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	 	            if (ni.isConnected())
	 	                haveConnectedWifi = true;
	 	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	 	            if (ni.isConnected())
	 	                haveConnectedMobile = true;
	 	    }
//	 	    Log.i("network connectivity",haveConnectedWifi+"/"+haveConnectedMobile);
		} catch (Exception e) {
			// TODO: handle exception
		}   
	    return haveConnectedWifi || haveConnectedMobile;
	}
}
