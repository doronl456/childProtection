package com.androidhive.pushnotifications;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class StepOutService extends Service {


	
	private LocationManager lm;
	LocationListener locationListener;
	localDB DataBase;
	Location dst;
	String current_state = "IN";
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();

		dst = new Location("gps");
		dst.setLatitude(DataBase.getLatitudeOut());
		dst.setLongitude(DataBase.getLongtitudeOut());
		
		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	    locationListener = new MyLocationListener();
		Thread thr = new Thread(null, new ServiceWorker(), "BackgroundService");
		thr.start();
	    lm.requestLocationUpdates("gps", 10000, 10, locationListener);	    
	    DataBase= localDB.getInstance();
	}
	
	@Override
	public void onDestroy() {
	    Log.d("", "Service.onDestroy()...");
	    super.onDestroy();
	    lm.removeUpdates(locationListener);
	    DataBase.setStepOutFlag(false);
	}
	
	class ServiceWorker implements Runnable {
		public void run() {
			Location location = lm.getLastKnownLocation("gps");
			if (dst.distanceTo(location) > DataBase.getRadiusOut()) {
				String userName = DataBase.getUserName();
				String userPassword = DataBase.getUserPassword();
				ServerUtilities.UpdateSonAndSendMessage(userName,userPassword,location.getLatitude(),location.getLongitude());
			}
		}
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		DataBase.setStepOutFlag(true);
	}
	
	private class MyLocationListener implements LocationListener 
	{
		@Override
		public void onLocationChanged(Location loc) {
			Thread thr = new Thread(null, new ServiceWorker(), "BackgroundService");
			thr.start();
		}
		@Override
		public void onProviderDisabled(String provider) {}
		@Override
		public void onProviderEnabled(String provider) {}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras){} 

	}
	
}
