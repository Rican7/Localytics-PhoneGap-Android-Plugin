package com.blennd.localyticsplugin;

import com.localytics.android.LocalyticsSession;

import android.app.Application;

// Public Application level class for keeping track of the Localytics Session object
// and other app-wide contexts
public class PhoneGapApp extends Application {
	
	// Declare our localytics session object
	private LocalyticsSession localyticsSession;

	// Override our onCreate method of the application to create some application wide objects
	@Override
	public void onCreate() {
		super.onCreate();
		
	    // Create a new localytics session
		this.localyticsSession = new LocalyticsSession(
				this.getApplicationContext(), // Context used to access device resources
				"APP KEY FROM STEP 2" // Key generated on the web service
		);
	}
	
	// Public method to get the Localytics Session object
	public LocalyticsSession getLocalyticsSession() {
		return this.localyticsSession;
	}
}
