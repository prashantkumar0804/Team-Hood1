package com.teamhood;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {
	
	// give your server registration url here
    static final String SERVER_URL = "http://teamhood.applifytech.com/index.php/send_notification"; 
//AIzaSyCjDF2ba01d0QOsANCthUo3VfE2nGUb_LA  -->Key for browser apps (with referers)prashantkumar@applify.guru
    // Google project id 429871867330
    
    // new browser_key:   AIzaSyCs2nAg5w65Kq5q8elj2zBFdxmrjVC5ZFs
    // Google project id 556959075388
    // prashantkumar0804@gmail.com
    public static final String SENDER_ID = "556959075388"; 

    
//    static final String SENDER_ID = "429871867330"; 
    /**
     * Tag used on log messages.
     */
    static final String TAG = "AndroidHive GCM";

    public static final String DISPLAY_MESSAGE_ACTION ="com.androidhive.pushnotifications.DISPLAY_MESSAGE";

    public static final String EXTRA_MESSAGE = "message";

    /**
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context ap plication's context.
     * @param message message to be displayed.
     */
   
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
