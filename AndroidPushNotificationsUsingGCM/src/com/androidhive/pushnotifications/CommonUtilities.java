package com.androidhive.pushnotifications;

import static com.androidhive.pushnotifications.CommonUtilities.SENDER_ID;

import com.google.android.gcm.GCMRegistrar;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {
	
	// give your server registration url here
    static final String SERVER_URL = "http://childprotection.comze.com/register.php"; 
    static final String SEND_MESSAGE_SERVER_URL = "http://childprotection.comze.com/send_message2.php"; 
    static final String ADD_PARENT_SON_SERVER_URL = "http://childprotection.comze.com/addParentSon.php"; 
    static final String UPDATE_SON_SEND_MESSAGE_SERVER_URL = "http://childprotection.comze.com/update_son_status_and_location.php";
    static final String Test_SERVER_URL = "http://childprotection.comze.com/testreturn.php";
    static final String LOG_IN_SERVER_URL = "http://childprotection.comze.com/logIn.php";
    
    // Google project id
    static final String SENDER_ID = "345086346042"; 

    /**
     * Tag used on log messages.
     */
    static final String TAG = "AndroidHive GCM";

    static final String DISPLAY_MESSAGE_ACTION =
            "com.androidhive.pushnotifications.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "newmessage";
    //static final String EXTRA_DATA = "datakey";
    

    /**
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
   static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);////////reem chnge!!!
        context.sendBroadcast(intent);
   }
    static void dstaMessage(Context context, String message) {
    	String a = message;
    	a= "hhh";
//        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
//        intent.putExtra(EXTRA_DATA, message);
//        context.sendBroadcast(intent);
    	
    	
    }
    
    static String getGCM_regId(Context context) {
		// Get GCM registration id
	    String regId = GCMRegistrar.getRegistrationId(context);
		if (regId.equals("")) {
			// Registration is not present, register now with GCM			
			GCMRegistrar.register(context, SENDER_ID);
			regId = GCMRegistrar.getRegistrationId(context);
			return regId;
		} else {
			// Device is already registered on GCM
			if (GCMRegistrar.isRegisteredOnServer(context)) {
				// Skips registration.	
				return regId;
			} else {
                GCMRegistrar.setRegisteredOnServer(context, true);
    			if (GCMRegistrar.isRegisteredOnServer(context)) {
    				// Skips registration.	
    				return regId;
    			} else {
    				return "";
    			}
			}
			}
		}
		
    
}
