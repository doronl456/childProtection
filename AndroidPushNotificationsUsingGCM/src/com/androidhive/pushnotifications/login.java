package com.androidhive.pushnotifications;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class login extends Activity  {
//	final Activity localActivity= this;
//	AsyncTask<Void, Void, Void> mRegisterTask;
//	
//	public void onCreate(Bundle savedInstanceState) 
//	{
//		
//		super.onCreate(savedInstanceState);
//		//setContentView(R.layout.login);
//		final Activity mylogin = this;
//		//Button enterBtn = (Button)this.findViewById(R.id.Enter);
//		enterBtn.setOnClickListener(new OnClickListener()
//		{	
//
//			
//
//			
//			
//			public void onClick(View v) 
//			{       
//				// Asyntask
//				
//				mRegisterTask = new AsyncTask<Void, Void, Void>() {
//
//					@Override
//					protected Void doInBackground(Void... params) {
//						EditText pass= (EditText)findViewById(R.id.password);
//						EditText name = (EditText) findViewById(R.id.tEName);
//						
//							//showDialog(1);
//
//						String regId = CommonUtilities.getGCM_regId(localActivity);
//						if (!regId.equals("")) {
//							ServerUtilities.LogIn("bubu", "bubu", regId);
//						} else{
//							Toast.makeText(localActivity, "Can't get GCM reg id", 10000);
//						}
//						return null;
//					}
//
//					@Override
//					protected void onPostExecute(Void result) {
//						mRegisterTask = null;
//					}
//
//				};
//				mRegisterTask.execute(null, null, null);
//			}
//				//read from client
//
//				//Intent intent = new Intent(mylogin, Main.class);
//				//startActivity(intent);
//			
//			});

	//}
	
	@Override
	public void onResume()
	{
 	super.onResume();
//		if (Main.closeFlag==true){
//			Main.closeFlag=false;
//			localActivity.finish();
//		}
			
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
	protected Dialog onCreateDialog(int id)
	{
			//return creatingIdDialog("WRONG PASSWORD");
		return creatingParamsDialog("?????? ?????? ?????");
		
	}
	private Dialog creatingParamsDialog(String msg) 
	{
		Dialog dialog = new Dialog(this);
		//dialog.setContentView(R.layout.parms_dialog);
		dialog.setTitle("????? ?????");
		//TextView text = (TextView) dialog.findViewById(R.id.text);
		//text.setText(msg);	
		return dialog;
	}
	/*private Dialog creatingIdDialog(String msg)
	{
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.pass_dialog);
		dialog.setTitle("not for kids..");
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText(msg);
		return dialog;

	}*/
}


