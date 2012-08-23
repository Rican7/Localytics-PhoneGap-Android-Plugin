/* ******************************************* */
/*                                             */
/*            LocalyticsPlugin.java            */
/*     Localytics Cordova/PhoneGap plugin      */
/*      Created by Trevor Suarez (Rican7)      */
/*  Based on the Cordova Docs Plugin Template  */
/*                                             */
/* ******************************************* */

package com.blennd.localyticsplugin;

import java.util.Map;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.localytics.android.LocalyticsSession;

import android.util.Log;

// Class to implement a Localytics PhoneGap/Cordova plugin
public final class LocalyticsPlugin extends Plugin {
	
	// Declare some variables/objects
	private PhoneGapApp appContext;
	private LocalyticsSession localyticsSession;
	
	// Declare some final variables
	protected static final String EVENT_NAME_STRING = "_event_name_";
	protected static final String LOG_TAG = "LocalyticsPlugin";

	/**
	 *  Create a public "execute" method to handle the JavaScript function calls through the Cordova Plugin interface
	 *  
	 *  @param action		The action to execute
	 *  @param args			JSONArray of arguments for the plugin
	 *  @param callbackId	The callback id used when calling back into JavaScript.
	 *  @return				A PluginResult object with a status and message
	 */
	public PluginResult execute(String action, JSONArray args, String callbackId) {
		
		// If the app context or the localytics session is null, let's set them
		if (appContext == null || localyticsSession == null) {
			// Let's get a reference to our application context
	        appContext = (PhoneGapApp) this.ctx.getApplicationContext();
	        
	        // Let's get a reference to our Localytics Session object
	        this.localyticsSession = appContext.getLocalyticsSession();
		}

		try {
			// Log our passed action
			Log.d(LOG_TAG, "Action called: " + action);
			Log.d(LOG_TAG, "Arguments passed: " + args.getString(0));
			
			// Our different logic depending on the action
			if (action.equals("startSession") || action.equals("open")) {
				// Start a new Localytics session
				this.open();
				
				// Return an OK result with a useful message
				return new PluginResult(PluginResult.Status.OK, "Localytics Session opened");
			}
			else if (action.equals("close")) {
				// Closes the Localytics session
				this.close();
				
				// Return an OK result with a useful message
				return new PluginResult(PluginResult.Status.OK, "Localytics Session closed");
			}
			else if (action.equals("upload")) {
				// Uploads the Localytics session data
				this.upload();
				
				// Return an OK result with a useful message
				return new PluginResult(PluginResult.Status.OK, "Localytics Session uploaded");
			}
			else if (action.equals("tagScreen")) {
				// Tags a screen
				this.tagScreen(args.getString(0));
				
				// Return an OK result with a useful message
				return new PluginResult(PluginResult.Status.OK, "Localytics screen has been tagged");
			}
			else if (action.equals("tagEvent")) {
				// Tags an event
				this.tagEvent(args.getString(0), args);
				
				// Return an OK result with a useful message
				return new PluginResult(PluginResult.Status.OK, "Localytics event has been tagged");
			}
			else {
				// Hmm, the action we sent through our Plugin Interface isn't one we support
				// Log our error
				Log.d(LOG_TAG, "An invalid action has been called");
				
				// Return an error as a result
				return new PluginResult(PluginResult.Status.INVALID_ACTION);
			}
		}
		catch (JSONException e) {
			// Log our exception
			Log.d(LOG_TAG, "A JSONException has been called");
			
			// Return an exception as a result
			return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
		}
		catch (Exception e) {
			// If we got here, something must have gone wrong
			// Log our error
			Log.d(LOG_TAG, "An error has been returned");
			
			// Return an error as a result
			return new PluginResult(PluginResult.Status.ERROR);
		}
	}
	
	/**
	 * Private open method to open a localytics session
	 */
	private void open() {
		// Call the Localytics API
		this.localyticsSession.open();
		
		// Log our API call
		Log.d(LOG_TAG, "Localytics API Call: Open");
	}
	
	/**
	 * Private close method to close a localytics session
	 */
	private void close() {
		// Call the Localytics API
		this.localyticsSession.close();
		
		// Log our API call
		Log.d(LOG_TAG, "Localytics API Call: Close");
	}
	
	/**
	 * Private upload method to upload any localytics session data
	 */
	private void upload() {
		// Call the Localytics API
		this.localyticsSession.upload();
		
		// Log our API call
		Log.d(LOG_TAG, "Localytics API Call: Upload");
	}
	
	/**
	 * Private tagScreen method to tag a screen/view in analytics
	 */
	private void tagScreen(String screenName) {
		// Call the Localytics API
		this.localyticsSession.tagScreen(screenName);
		
		// Log our API call
		Log.d(LOG_TAG, "Localytics API Call: tagScreen WITH Name: " + screenName);
	}
	
	/**
	 * Private tagScreen method to tag a screen/view in analytics
	 */
	private void tagEvent(String eventName, JSONArray options) {
		// Let's convert the JSONArray of options to a Map of key:value pairs to match the Localytics API
		Map<String,String> eventOptions = (Map) options;
		
		// Call the Localytics API
		this.localyticsSession.tagEvent(eventName, eventOptions);
		
		// Log our API call
		Log.d(LOG_TAG, "Localytics API Call: tagEvent WITH Name: " + eventName + " and OPTIONS: " + eventOptions.toString());
	}
	
}
