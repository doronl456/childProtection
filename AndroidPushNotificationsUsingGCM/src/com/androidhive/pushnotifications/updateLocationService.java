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

public class updateLocationService extends Service {

	
	private LocationManager lm;
	LocationListener locationListener;
	localDB DataBase;

	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();

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
	}
	
	class ServiceWorker implements Runnable {
		public void run() {
			Location location = lm.getLastKnownLocation("gps");
			String userName = DataBase.getUserName();
			String userPassword = DataBase.getUserPassword();
			ServerUtilities.UpdateSonAndSendMessage(userName,userPassword,location.getLatitude(),location.getLongitude());
		}
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
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
