package com.example.localyticsplugindev;

import android.os.Bundle;
import android.view.Menu;

import org.apache.cordova.*;

import com.localytics.android.*;

public class Main extends DroidGap {
	
	// Declare our localytics session object
	private LocalyticsSession localyticsSession;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
 
        // Create a new localytics session
		this.localyticsSession = new LocalyticsSession(
				this.getApplicationContext(), // Context used to access device resources
				"APP KEY FROM STEP 2" // Key generated on the webservice
		);

		// Open the session and upload any initialized session data
		this.localyticsSession.open();
		this.localyticsSession.upload();

		// At this point, Localytics Initialization is done. After uploads
		// complete nothing
		// more will happen due to Localytics until the next time you call it.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public void onResume() {
	    // Call the super
        super.onResume();
        
        // Open the localytics session
        this.localyticsSession.open();
    }

	@Override
	protected void onPause() {
		// Close the session and upload the localytics data
		this.localyticsSession.close();
	    this.localyticsSession.upload();
	    
	    // Call the super
		super.onPause();
	}
}
