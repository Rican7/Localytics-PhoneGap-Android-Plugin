window.deviceOS = 'android';

// Let's wait for PhoneGap to load and to fire the deviceready event
document.addEventListener('deviceready', function() {
	// Tag the screen
	LocalyticsPlugin.tagScreen('Main Plugin Test View');

	// Tag an event
	var eventData = {
		"deviceOS": deviceOS,
		"appName": 'Test Plugin App'
	};
	LocalyticsPlugin.tagEvent('App Initialized', eventData);
	console.log('Event Data:');
	console.log(eventData);

}, false);
