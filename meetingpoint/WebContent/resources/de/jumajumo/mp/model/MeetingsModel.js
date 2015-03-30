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
		jQuery.sap.require("jumajumo.core.util.Geocode");
		var that = this;

		// get the locations-address array
		var data = this.getData();
		
		// get the addresses of the loaded meetingpoints
		var destAddresses =	[];
		jQuery.each(data, function(offs, meetingPoint)
		{
			// get the geo coordinates of the meeting point and calculate the distances of the meeting participants to the meeting point
			that._calculateParticipantsDistances(offs, meetingPoint)
			
			// push the meeting point address to the array - for calculating my distances to meeting points
			destAddresses.push(meetingPoint.address)
		});

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
	
	_calculateParticipantsDistances: function(mpOffs, meetingPoint)
	{
		console.log("calculate distances of participants to meeting point: " + meetingPoint.address);

		var that = this;
		
		// calculate the distance for each participant
		var participantPositions = []
		jQuery.each(meetingPoint.participants, function(offs, participant)
		{
			if (participant.currentPosition && (null != participant.currentPosition))
			{
				participantPositions.push("(" + participant.currentPosition.latitude + "," + participant.currentPosition.longitude + ")");
			}
			else
			{
				participantPositions.push("");
			}
		});
			
		jumajumo.core.util.Geocode.loadDistances(meetingPoint.address,participantPositions,	function(response, status)
		{
			var destElements = response.rows[0].elements;
			
			jQuery.each(destElements,function(offs,	el)
			{
				meetingPoint.participants[offs].location = {};
				meetingPoint.participants[offs].location.distance =	[];
//				meetingPoint.participants[offs].location.distance =	el.distance;
				that.setProperty("/" + mpOffs + "/participants/" + offs + "/location/distance",	el.distance);
				meetingPoint.participants[offs].location.duration = [];
//				meetingPoint.participants[offs].location.duration = el.duration;
				that.setProperty("/" + mpOffs + "/participants/" + offs + "/location/duration",	el.duration);
			});
		});
		
	}
	

});