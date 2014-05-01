package com.androidhive.pushnotifications;

import com.example.androidhive.library.UserFunctions;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SunDashbordActivity extends Activity {
	UserFunctions userFunctions;
	Button btnLogout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sun_dashbord);
		userFunctions = new UserFunctions();
		
		btnLogout = (Button) findViewById(R.id.buttonLogoff);

		btnLogout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				userFunctions.logoutUser(getApplicationContext());
				Intent login = new Intent(getApplicationContext(),
						LoginActivity.class);
				login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(login);
				// Closing dashboard screen
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.sun_dashbord, menu);
		return true;
	}

}
