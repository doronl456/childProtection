package com.androidhive.pushnotifications;

import static com.androidhive.pushnotifications.CommonUtilities.SENDER_ID;
import static com.androidhive.pushnotifications.CommonUtilities.displayMessage;
import static com.androidhive.pushnotifications.CommonUtilities.dstaMessage;
import static com.androidhive.pushnotifications.CommonUtilities.EXTRA_MESSAGE;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	
	private static final String TAG = "GCMIntentService";
	private static String KEY_SUCCESS = "success";
	private static String MESSAGE_TYPE = "MessageType";
	private static String SERVICE = "Service";
	private static String NAME = "name";
	private static String ACTION = "action";
	
	
	
	

    public GCMIntentService() {
        super(SENDER_ID);
    }

    /**
     * Method called on device registered
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, "Your device registred with GCM");
        Log.d("NAME", MainActivity.name);
        ServerUtilities.register(context, MainActivity.name, MainActivity.email, registrationId);
    }

    /**
     * Method called on device un registred
     * */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        ServerUtilities.unregister(context, registrationId);
    }

    /**
     * Method called on Receiving a new message
     * */
    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
        String message = intent.getExtras().getString("price");
        try {
			JSONObject jObj = new JSONObject(message);
			if (jObj.getString(KEY_SUCCESS) != null) {
				//loginErrorMsg.setText("");
				String res = jObj.getString(KEY_SUCCESS); 
				String messageType = jObj.getString(MESSAGE_TYPE);
				if (messageType.toString().equals("Service")){
					JSONObject json_service = jObj.getJSONObject(SERVICE);
					String serviceName = json_service.getString(NAME);
					String serviceAction = json_service.getString(ACTION);
					if (serviceName.toString().equals("update_location")){
						if (serviceAction.toString().equals("On")){
							// start update Location Service
							startService(new Intent(GCMIntentService.this,updateLocationService.class));
						} else if (serviceAction.equals("Off")){
							// stop update Location Service
							stopService(new Intent(GCMIntentService.this,updateLocationService.class));
						}
					
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        localDB database= localDB.getInstance();
        if (database.isContorel()){
	        displayMessage(context, message);
	        // notifies user
	        generateNotification(context, message);
        }
        else
        {
        	dstaMessage(context, message);
        }
        
    }

    /**
     * Method called on receiving a deleted message
     * */
    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        // notifies user
        //generateNotification(context, message);//reem chnge
    }

    /**
     * Method called on Error
     * */
    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message) {
    	localDB database= localDB.getInstance();//rrem
    	if(database.isContorel())//reem
    	{
        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        
        String title = context.getString(R.string.app_name);
        
        Intent notificationIntent = new Intent(context, MainActivity.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        
        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;
        
        //notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "your_sound_file_name.mp3");
        
        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);  
    	}

    }

}
