package com.androidhive.pushnotifications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SonDetails extends Activity {

	public static String name;
	private TextView sonName;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {         

       super.onCreate(savedInstanceState);    
       setContentView(R.layout.son_details);
		// Getting name, email from intent
		Intent i = getIntent();
       name = i.getStringExtra("name");
       //sonName = (TextView) findViewById(R.id.tvSonName);
       //sonName.setText(name);
       //Toast.makeText(getApplicationContext(), name, BIND_AUTO_CREATE);
       setTitle(name);
       //rest of the code
   }
	
}
