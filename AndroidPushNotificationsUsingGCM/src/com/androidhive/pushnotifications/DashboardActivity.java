
package com.androidhive.pushnotifications;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.androidhive.pushnotifications.R;
import com.example.androidhive.library.UserFunctions;

public class DashboardActivity extends Activity {
	UserFunctions userFunctions;
	Button btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * Dashboard Screen for the application
         * */        
        // Check login status in database
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        	if(userFunctions.isDeviceController(getApplicationContext())){
        	setContentView(R.layout.dashboard);
        	btnLogout = (Button) findViewById(R.id.btnLogout);
        	
        	btnLogout.setOnClickListener(new View.OnClickListener() {
    			
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				userFunctions.logoutUser(getApplicationContext());
    				Intent login = new Intent(getApplicationContext(), LoginActivity.class);
    	        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        	startActivity(login);
    	        	// Closing dashboard screen
    	        	finish();
    			}
    		});
        	ListView lv = (ListView) findViewById(R.id.lvSons);
        	ArrayList<String> your_array_list = new ArrayList<String>();
            your_array_list.add("foo");
            your_array_list.add("bar");
            
        	String[] listItems = {"David", "Dudu", "Bubu"};
        	ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this, 
                    android.R.layout.simple_list_item_1,
                    listItems );

            lv.setAdapter(arrayAdapter); 
            
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                    int position, long id) {
                	String test="";
                  final String item = (String) parent.getItemAtPosition(position);
                  Intent startNewActivityOpen = new Intent(getApplicationContext(), SonDetails.class);
                  startNewActivityOpen.putExtra("name", item);
                  startActivity(startNewActivityOpen);
                  
           
                  /*view.animate().setDuration(2000).alpha(0)
                      .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                          //list.remove(item);
                          //adapter.notifyDataSetChanged();
                          //view.setAlpha(1);
                        }
                      });*/
                }

              });
        	}
        	else{
            	// user device is not controllr
            	Intent sunDashbord = new Intent(getApplicationContext(), SunDashbordActivity.class);
            	sunDashbord.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	startActivity(sunDashbord);
            	// Closing dashboard screen
            	finish();
        	}
        }else{
        	// user is not logged in show login screen
        	Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(login);
        	// Closing dashboard screen
        	finish();
        }
        
        
        
        
    }
}