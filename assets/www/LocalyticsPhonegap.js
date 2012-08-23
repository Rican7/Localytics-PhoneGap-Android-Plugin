//
//  LocalyticsPhonegap.js
//  LocalyticsPhonegapExample
//
//  Copyright 2012 Localytics. All rights reserved.
//


var LocalyticsPlugin = {
    
    // Creates a new Localytics Session and uploads it along with any stored data
    startSession: function (appKey)
    {
        Cordova.exec(null, null, "com.localytics.phonegap", "startSession", [appKey]);
    },

    // Closes a session. This should be called when the app is exiting
    close: function()
    {
        Cordova.exec(null, null, "com.localytics.phonegap", "close", []);
    },
    
    // Tags an event as having occured. 
    // event = Name of the event
    // attributes = a hash of key/value pairs containing the event attributes
    tagEvent: function (event, attributes)
    {
        options = { "_event_name_":event };
        
        // Merge the attributes and the event name in an option hash for phonegap to pass to the native code
        for (var attrname in attributes)
        {
            options[attrname] = attributes[attrname];
        }

        Cordova.exec(null, null, "com.localytics.phonegap", "tagEvent", [options]);
    },

    // Tags a screen. Call this when a new screen is displayed w/ a pretty name for the screen
    tagScreen: function (screen)
    {
        Cordova.exec(null, null, "com.localytics.phonegap", "tagScreen", [screen]);
    },
    
    // Forces an upload. This function does not ever need to be called but can be used to gaurantee
    // upload attempts
    upload: function()
    {
        Cordova.exec(null, null, "com.localytics.phonegap", "upload", []);
    }
};
