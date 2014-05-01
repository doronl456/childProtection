package com.androidhive.pushnotifications;


import android.app.Activity;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class StepOutTool  extends Activity
{
	localDB DataBase;
	double latitude,longitude;
	//	private EditText phoneView;
	//private EditText latView;
	//private EditText longView;
	private EditText EtRadius;
	Button applyBtn;
	Button locationBtn;
	//boolean currentLocationFlag=false;
	//final int SEARCH_DIALOG = 1;
	//final int LACK_OF_PARAMETERS = 2;
	private LocationManager lm;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.out);
		DataBase= localDB.getInstance();
		//phoneView = (EditText)findViewById(R.id.EditPhoneOut);
		//latView=(EditText)findViewById(R.id.EditLatitudeOut);
		//longView=(EditText)findViewById(R.id.EditLongtitudeOut);
		EtRadius =(EditText)findViewById(R.id.EditRadiusOut);
		applyBtn = (Button)this.findViewById(R.id.ApplyBtnOut);
	    LocationListener locationListener;
		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	    locationListener = new MyLocationListener();
	    lm.requestLocationUpdates("gps", 10000, 10, locationListener);
		
		
		applyBtn.setOnClickListener(new OnClickListener()
		{			
			public void onClick(View arg0) 
			{	
				try
				{
					DataBase.setRadiusOut(Integer.valueOf(EtRadius.getText().toString()).intValue());
					//DataBase.setPhoneOut(phoneView.getText().toString());
					//if (singleton.getPhoneOut().equals("")) throw new NumberFormatException();
					/*if (!currentLocationFlag)
					{
						//longitude=Integer.valueOf(longView.getText().toString()).intValue();
						//latitude=Integer.valueOf(latView.getText().toString()).intValue();
						singleton.setLongtitudeOut(longitude);
						singleton.setLatitudeOut(latitude);	
					}*/
				
					startService(new Intent(StepOutTool.this,StepOutService.class));
					finish();
				}
				catch (NumberFormatException e)
				{
					//showDialog(LACK_OF_PARAMETERS);
				}
				
			}
		});

//		Button searchBtn = (Button)this.findViewById(R.id.searchButtonOut);
//		searchBtn.setOnClickListener(new OnClickListener() 
//		{
//			public void onClick(View v) 
//			{
//				showDialog(SEARCH_DIALOG);
//			}
//		});

	    locationBtn = (Button)this.findViewById(R.id.CurrentLocationBtnOut);
		locationBtn.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				//currentLocationFlag=true;
				Location location = lm.getLastKnownLocation("gps");
				//latView.setText(Double.toString(location.getLatitude()));
				latitude=location.getLatitude();
				longitude= location.getLongitude();
				DataBase.setLatitudeOut(location.getLatitude());
				//longView.setText(Double.toString(location.getLongitude()));
				DataBase.setLongtitudeOut(location.getLongitude());
				Log.d ("GPS","Altitude is: "+location.getLatitude()+ " Longtitude is: "
						+location.getLongitude());			
			}
		});
	}

	
//	@Override
//	protected Dialog onCreateDialog(int id) 
//	{		
//		switch (id)
//		{
//		case SEARCH_DIALOG:
//			return creatingCustomDialog("????? ?? ?? ??");
//		default: 
//			return creatingParamsDialog("???? ?? ????? ??????");					
//		}
//	}

//	private Dialog creatingParamsDialog(String msg) 
//	{
//		Dialog dialog = new Dialog(this);
//		dialog.setContentView(R.layout.parms_dialog);
//		dialog.setTitle("?? ???? ?? ?? ?????");
//		TextView text = (TextView) dialog.findViewById(R.id.text);
//		text.setText(msg);	
//		return dialog;
//	}

//	private Dialog creatingCustomDialog(String msg)
//	{
//		Dialog dialog = new Dialog(this);
//		final Dialog localDialog=dialog;
//		dialog.setContentView(R.layout.custom_dialog);
//		dialog.setTitle("????? ???? ???????");
//		TextView text = (TextView) dialog.findViewById(R.id.text);
//		text.setText(msg);		
//		singleton= Singleton.getInstance();
//
//		Button button = (Button)dialog.findViewById(R.id.namebtn);
//		button.setOnClickListener(new OnClickListener()
//		{
//			public void onClick(View v) 
//			{
//				singleton.setSearchName(((EditText)
//						localDialog.findViewById(R.id.findphonebyname1)).getText().toString());
//				String name=singleton.getSearchName();
//				Cursor cur=getContentResolver().query(People.CONTENT_URI,
//						null, null, null, null);
//				int nameCol = cur.getColumnIndex(People.NAME);
//				int phoneCol = cur.getColumnIndex(People.NUMBER);
//				if (cur.moveToFirst()) 
//				{		
//					do
//					{
//						String tmp=cur.getString(nameCol);
//						if(name.equals(tmp))
//						{
//							phoneView.setText(cur.getString(phoneCol));
//							break;
//						}
//						else phoneView.setText("");
//					} while (cur.moveToNext());
//				}
//				singleton.setSearchName("");
//				localDialog.cancel();
//			}
//		});
//		return dialog;
//	}
	
	private class MyLocationListener implements LocationListener 
	{
		@Override
		public void onLocationChanged(Location loc) {}
		@Override
		public void onProviderDisabled(String provider) {}
		@Override
		public void onProviderEnabled(String provider) {}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras){} 

	}
	
}
