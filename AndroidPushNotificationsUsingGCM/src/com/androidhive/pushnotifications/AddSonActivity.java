package com.androidhive.pushnotifications;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.androidhive.library.DatabaseHandler;
import com.example.androidhive.library.UserFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class AddSonActivity extends Activity {
	
	private String pageTitle= "Add new son form";
	Button btnAddNewSon;
	EditText inputSonName;
	EditText inputSonPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {         

       super.onCreate(savedInstanceState);    
       setContentView(R.layout.activity_add_son);
       btnAddNewSon = (Button) findViewById(R.id.btnAddNewSon);
	   inputSonName = (EditText) findViewById(R.id.etSonName);
	   inputSonPassword = (EditText) findViewById(R.id.etSonPassword);
       setTitle(pageTitle);
       

		// Login button Click Event
       btnAddNewSon.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				AsyncTask<Void, Void, Void> mRegisterTask;
				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						String parentID = "1";
						String sonName = inputSonName.getText().toString();
						String sonPassword = inputSonPassword.getText().toString();
						UserFunctions userFunction = new UserFunctions();
						Log.d(sonName,sonPassword);
						//JSONObject json = userFunction.addNewSon(parentID, sonName, sonPassword);
						// check for login response
/*						try {
							if (json.getString(KEY_SUCCESS) != null) {
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}*/
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						//mRegisterTask = null;
					}

				};
				mRegisterTask.execute(null, null, null);
				
				
				
				
				/*
				JSONObject json = userFunction.loginUser(email, password);

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						loginErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS); 
						if(Integer.parseInt(res) == 1){
							// user successfully logged in
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
							
							// Clear all previous data in database
							userFunction.logoutUser(getApplicationContext());
							db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
							
							// Launch Dashboard Screen
							Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
							
							// Close all views before launching Dashboard
							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(dashboard);
							
							// Close Login Screen
							finish();
						}else{
							// Error in login
							loginErrorMsg.setText("Incorrect username/password");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}*/
			}
		});

		
   }
	
}
	
