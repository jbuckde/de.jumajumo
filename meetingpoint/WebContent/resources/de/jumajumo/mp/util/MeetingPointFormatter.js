jQuery.sap.declare("jumajumo.mp.util.MeetingPointFormatter");

var jumajumo = (function(module)
{
	"use strict";

	jumajumo.mp.util.MeetingPointFormatter = function()
	{
	};

	jumajumo.mp.util.MeetingPointFormatter.nameAndDate = function(oValue)
	{
		if (null == oValue || oValue == undefined)
		{
			return "";
		}

		var context = this.getBindingContext();

		var name = this.getModel().getProperty("name", context);
		var date = this.getModel().getProperty("date", context);

		return name + ", " + date;
		// + jumajumo.core.util.DateFormatter.formateDateTime(date);
	};

	jumajumo.mp.util.MeetingPointFormatter.distanceAndDuration = function(
			oValue)
	{
		if (null == oValue || oValue == undefined)
		{
			return "";
		}

		var distance = oValue.distance.text;
		var duration = oValue.duration.text;

		return distance + " (" + duration + ")";
	}
	
	jumajumo.mp.util.MeetingPointFormatter.initiatorLabeld = function(
			oValue)
	{
		if (null == oValue || oValue == undefined)
		{
			return "";
		}

		return "Erstellt von: " + oValue;
	}

	return module;
}(jumajumo ||
{}));