window.deviceOS = 'android';

var ghettoTestFunction = null; // So sad

// Let's wait for PhoneGap to load and to fire the deviceready event
document.addEventListener('deviceready', function() {
	// Tag an event
	var eventData = {
		"deviceOS": deviceOS,
		"appName": 'Test Plugin App'
	};
	LocalyticsPlugin.tagEvent('App Initialized', eventData);
	console.log('Event Data:');
	console.log(eventData);

	// Let's cache a couple of our "selectors" (hahahaha)
	var mainPage = document.getElementById('main-page');
	var aboutPage = document.getElementById('about-page');

	ghettoTestFunction = function(linkBool) {
		if (linkBool) {
			mainPage.setAttribute("style", "display: block"); // God, this is gross
			aboutPage.setAttribute("style", "display: none");

			// Tag the screen
			LocalyticsPlugin.tagScreen('Main Plugin Test View');
		}
		else {
			mainPage.setAttribute("style", "display: none");
			aboutPage.setAttribute("style", "display: block");

			// Tag the screen
			LocalyticsPlugin.tagScreen('About View');
		}

		// Tag the event!
		LocalyticsPlugin.tagEvent('View Switched', {
			"The view was what?!": "Switched!",
			"LinkBool": linkBool,
			"Wrote By": "Trevor N. Suarez (Rican7)"
		});
	}

	// Initialize, bitch
	ghettoTestFunction(true);
}, false);
