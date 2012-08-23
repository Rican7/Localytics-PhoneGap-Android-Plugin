package com.example.localyticsplugindev;

import android.os.Bundle;
import android.view.Menu;

import org.apache.cordova.*;

import com.blennd.localyticsplugin.PhoneGapApp;
import com.localytics.android.*;

public class Main extends DroidGap {
	
	// Declare our localytics session object
	private LocalyticsSession localyticsSession;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
        
        // Let's get a reference to our application context
        PhoneGapApp appContext = (PhoneGapApp) this.getApplicationContext();
        
        // Let's get a reference to our Localytics Session object
        this.localyticsSession = appContext.getLocalyticsSession();

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
