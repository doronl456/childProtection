/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.androidhive.pushnotifications;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidhive.pushnotifications.R;
import com.example.androidhive.library.DatabaseHandler;
import com.example.androidhive.library.UserFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends Activity {
	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	CheckBox inputIsControl;
	TextView registerErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "user_name";
	private static String KEY_EMAIL = "email";
	private static String KEY_PASSWORD = "password";
	private static String KEY_CREATED_AT = "created_at";
	private static String KEY_IS_CONTROL = "isControl";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		// Importing all assets like buttons, text fields
		inputFullName = (EditText) findViewById(R.id.registerName);
		inputEmail = (EditText) findViewById(R.id.registerEmail);
		inputPassword = (EditText) findViewById(R.id.registerPassword);
		inputIsControl =(CheckBox)findViewById(R.id.checkBoxController);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
		registerErrorMsg = (TextView) findViewById(R.id.register_error);

		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// String name = inputFullName.getText().toString();
				// String email = inputEmail.getText().toString();
				// String password = inputPassword.getText().toString();
				// UserFunctions userFunction = new UserFunctions();
				// JSONObject json = userFunction.registerUser(name, email,
				// password);

				AsyncTask<Void, Void, Void> mRegisterTask;
				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						String name = inputFullName.getText().toString();
						String email = inputEmail.getText().toString();
						String password = inputPassword.getText().toString();
						Boolean chaekPressd = inputIsControl.isChecked();
						String isConrol;
						if (chaekPressd)
							isConrol = "1"; 
						else
							isConrol = "0";							
						UserFunctions userFunction = new UserFunctions();
						Log.d(email, password);
						JSONObject json = userFunction.registerUser(name,email, password, isConrol);
						// check for login response
						try {
							if (json.getString(KEY_SUCCESS) != null) {
								// registerErrorMsg.setText("");
								String res = json.getString(KEY_SUCCESS);
								if (Integer.parseInt(res) == 1) {
									// user successfully registered
									// Store user details in SQLite Database
									DatabaseHandler db = new DatabaseHandler(
											getApplicationContext());
									JSONObject json_user = json
											.getJSONObject("user");
									//db.onCreate(db);

									// Clear all previous data in database
									userFunction
											.logoutUser(getApplicationContext());

									// get the json user content
									String userName = json_user
											.getString(KEY_NAME);// add
									String user_password = json_user
											.getString(KEY_PASSWORD);// add
									String isControl = json_user
											.getString(KEY_IS_CONTROL);// add
									// add to local db sql light
									db.addUser(userName, user_password,
											isControl);
									// add to locl db temporary
									localDB DataBase;
									DataBase = localDB.getInstance();
									DataBase.setUserName(userName);
									DataBase.setUserPassword(user_password);

									// check if the user is controls
									if (Integer.parseInt(isControl) == 1) {
										// Launch Dashboard Screen
										Intent dashboard = new Intent(
												getApplicationContext(),
												DashboardActivity.class);

										// Close all views before launching
										// Dashboard
										dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
										startActivity(dashboard);

										// Close Login Screen
										finish();
									} else {
										Intent sonDashboard = new Intent(getApplicationContext(), SunDashbordActivity.class);
										
										// Close all views before launching Dashboard
										sonDashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
										startActivity(sonDashboard);
										
										// Close Login Screen
										finish();
									}

								} else {
									//Error in registration
									 registerErrorMsg.setText("Error occured in registration");
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						// mRegisterTask = null;
					}

				};
				mRegisterTask.execute(null, null, null);

				// try {
				// if (json.getString(KEY_SUCCESS) != null) {
				// registerErrorMsg.setText("");
				// String res = json.getString(KEY_SUCCESS);
				// if(Integer.parseInt(res) == 1){
				// // user successfully registred
				// // Store user details in SQLite Database
				// DatabaseHandler db = new
				// DatabaseHandler(getApplicationContext());
				//
				// JSONObject json_user = json.getJSONObject("user");
				//
				// // Clear all previous data in database
				// userFunction.logoutUser(getApplicationContext());
				// db.addUser(json_user.getString(KEY_NAME),
				// json_user.getString(KEY_EMAIL), json.getString(KEY_UID),
				// json_user.getString(KEY_CREATED_AT));
				// // Launch Dashboard Screen
				// Intent dashboard = new Intent(getApplicationContext(),
				// DashboardActivity.class);
				// // Close all views before launching Dashboard
				// dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// startActivity(dashboard);
				// // Close Registration Screen
				// finish();
				// }else{
				// // Error in registration
				// registerErrorMsg.setText("Error occured in registration");
				// }
				// }
				// } catch (JSONException e) {
				// e.printStackTrace();
				// }
			}
		});

		// Link to Login Screen
		btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(i);
				// Close Registration View
				finish();
			}
		});
	}
}
