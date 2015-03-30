jQuery.sap.declare("jumajumo.core.controls.map.GoogleMap");

var jumajumo = (function(module)
{
	"use strict";

	sap.ui.core.Control.extend("jumajumo.core.controls.map.GoogleMap",
	{
		metadata :
		{
			properties :
			{
				"visible" :
				{
					type : "boolean",
					defaultValue : true
				},

				"sourceAddress" :
				{
					type : "string",
					defaultValue : ""
				},

				"sourceAddressIcon" :
				{
					type : "string",
					defaultValue : ""
				},

				"targetAddress" :
				{
					type : "string",
					defaultValue : ""
				},

				"targetAddressIcon" :
				{
					type : "string",
					defaultValue : ""
				}
			},

			aggregations :
			{},

			events :
			{}

		},
		renderer : function(oRm, oMap)
		{
			// do not render invisible cards
			if (!oMap.getVisible())
			{
				return;
			}

			// HTML
			oRm.write("<div");

			oRm.writeControlData(oMap);

			oRm.writeClasses();

			oRm.addStyle("width", "auto");
			oRm.addStyle("height", "400px");
			oRm.writeStyles();

			oRm.write("/>");

		}

	});

	jumajumo.core.controls.map.GoogleMap.prototype.onAfterRendering = function()
	{
		var _this = this;

		var mapOptions =
		{
			center : new google.maps.LatLng(44.5403, -78.5463),
			zoom : 8,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}

		// create the map
		var map = new google.maps.Map(document.getElementById(this.getId()),
				mapOptions);

		var geocoder = new google.maps.Geocoder();

		// geocode the target address
		geocoder.geocode(
		{
			'address' : this.getProperty("targetAddress")
		}, function(results, status)
		{
			if (status == google.maps.GeocoderStatus.OK)
			{
				map.setCenter(results[0].geometry.location);
				var marker = new google.maps.Marker(
				{
					map : map,
					position : results[0].geometry.location,
				// icon : _this.getProperty("targetAddressIcon")
				});
			}
		});

		// geocode the source address
		geocoder.geocode(
		{
			'address' : this.getProperty("sourceAddress")
		}, function(results, status)
		{
			if (status == google.maps.GeocoderStatus.OK)
			{
				var marker = new google.maps.Marker(
				{
					map : map,
					position : results[0].geometry.location,
				// icon :
				// {
				// url : _this.getProperty("sourceAddressIcon"),
				// size : new google.maps.Size(32, 32),
				// }
				});
			}
		});
	}

	return module;
}(jumajumo ||
{}));