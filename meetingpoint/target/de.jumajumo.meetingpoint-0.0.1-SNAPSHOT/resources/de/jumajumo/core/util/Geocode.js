jQuery.sap.declare("jumajumo.core.util.Geocode");

var jumajumo = (function(module)
{
	"use strict";

	jumajumo.core.util.Geocode = function()
	{
	};

	function _calculateCurrentLocation(fnSuccess)
	{
		var options =
		{
			enableHighAccuracy : true,
			timeout : 10000,
			maximumAge : 500000
		};

		function success(pos)
		{
			var crd = pos.coords;

			console.log('Your current position is:');
			console.log('Latitude : ' + crd.latitude);
			console.log('Longitude: ' + crd.longitude);
			console.log('More or less ' + crd.accuracy + ' meters.');
		}
		;

		function error(err)
		{
			console.warn('ERROR(' + err.code + '): ' + err.message);
		}
		;

		navigator.geolocation.getCurrentPosition(fnSuccess, error, options);
	}
	;

	jumajumo.core.util.Geocode.getCurrentLocation = function(fnCallback)
	{
		_calculateCurrentLocation(fnCallback);
	}

	jumajumo.core.util.Geocode.loadDistances = function(pos, arrDestinations,
			fnResponse)
	{
		// calculate the origin and destinations parameter
		// values
		var originValue = pos.coords.latitude + "," + pos.coords.longitude;
		var destinationsValue;
		jQuery.each(arrDestinations, function(dest)
		{
			destinationsValue += dest + "|"
		});

		var service = new google.maps.DistanceMatrixService();
		service.getDistanceMatrix(
		{
			origins :
			[ originValue ],
			destinations : arrDestinations,
			travelMode : google.maps.TravelMode.DRIVING,
			unitSystem : google.maps.UnitSystem.METRIC,
			avoidHighways : false,
			avoidTolls : false
		}, fnResponse /* function(response, status) */);
	};

	return module;
}(jumajumo ||
{}));