jQuery.sap.declare("jumajumo.mp.model.MeetingsModel");

sap.ui.model.json.JSONModel.extend("jumajumo.mp.model.MeetingsModel",
{
	loadData : function()
	{
		var that = this;

		// setup the time that is refreshing the model every 10
		// seconds
		// if (this.timer == undefined || !this.timer)
		// {
		// this.timer = setInterval(function()
		// {
		// that.loadData()
		// }, 10000);
		// }

		// Data is fetched here
		jQuery.ajax(
		{ // load the data from a relative URL (the Data.json
			// file
			// in the same directory)
			url : "dispatcher/meetings",
			async : true,
			dataType : "json",
			// contentType: "application/json",
			type : "GET",
			success : function(data)
			{
				that.setData(data);

				// load the distances
				that.loadDistances();
			}
		});

	},

	loadDistances : function()
	{
		// get the locations-address array
		var data = this.getData();

		// get the addresses of the loaded meetingpoints
		var destAddresses =
		[];
		jQuery.each(data, function(offs, meetingPoint)
		{
			destAddresses.push(meetingPoint.address)
		});

		jQuery.sap.require("jumajumo.core.util.Geocode");
		var that = this;

		// load the distances from current user to the loaded meeting points
		jumajumo.core.util.Geocode.getCurrentLocation(function(pos)
		{
			jumajumo.core.util.Geocode.loadDistances(pos,destAddresses,	function(response, status)
			{
				var destElements = response.rows[0].elements;
				var data = that.getData();

				jQuery.each(destElements,function(offs,	el)
				{
					// member the current position of the user
					data[offs].currentAddress = response.originAddresses[0];

					data[offs].location = {};
					data[offs].location.distance =	[];
					that.setProperty("/" + offs + "/location/distance",	el.distance);
					data[offs].location.duration = [];
					that.setProperty("/" + offs	+ "/location/duration",	el.duration);
				});
			});
		});
	},
	

});