jQuery.sap.declare("jumajumo.core.util.DateFormatter");
jQuery.sap.require("sap.ui.core.format.DateFormat");

var jumajumo = (function(module)
{
	"use strict";

	jumajumo.core.util.DateFormatter = function()
	{
	};

	jumajumo.core.util.DateFormatter.formatDate = function(oValue)
	{
		return sap.ui.core.format.DateFormat.getDateInstance().format(
				new Date(oValue));
	};

	jumajumo.core.util.DateFormatter.formatDateLong = function(oValue)
	{
		return sap.ui.core.format.DateFormat.getDateInstance(
		{
			style : 'long'
		}).format(new Date(oValue));
	};

	jumajumo.core.util.DateFormatter.formatTime = function(oValue)
	{
		return sap.ui.core.format.DateFormat.getTimeInstance().format(
				new Date(oValue));
	};

	jumajumo.core.util.DateFormatter.formatDateTime = function(oValue)
	{
		var dateValue = jumajumo.core.util.DateFormatter.formatDate(oValue)
		var timeValue = jumajumo.core.util.DateFormatter.formatTime(oValue);

		return dateValue.concat(" ").concat(timeValue);
	};

	jumajumo.core.util.DateFormatter.distanceFromNowValue = function(oValue)
	{
		var diff = _getDistance(oValue);

		if (diff < 3600000 /* 1 hour */)
		{
			return Math.ceil(diff / 1000 / 60);
		}
		if (diff < 86400000 /* 1 day */)
		{
			return Math.ceil(diff / 1000 / 60 / 60);
		}
		if (diff < 2592000000 /* 30 days */)
		{
			return Math.ceil(diff / 1000 / 60 / 60 / 24);
		}

		return jumajumo.core.util.DateFormatter.formatDate(oValue);
	};

	jumajumo.core.util.DateFormatter.distanceFromNowUnit = function(oValue)
	{
		var diff = _getDistance(oValue);

		if (diff < 3600000 /* 1 hour */)
		{
			return "Minuten";
		}
		if (diff < 86400000 /* 1 day */)
		{
			return "Stunden";
		}

		if (diff < 2592000000 /* 30 days */)
		{
			return "Tage";
		}

		return jumajumo.core.util.DateFormatter.formatTime(oValue);
	};

	jumajumo.core.util.DateFormatter.distanceToState = function(oValue)
	{
		var diff = _getDistance(oValue);

		if (diff < 0)
		{
			return "Error"
		}
		if (diff < 3600000 /* 1 hour */)
		{
			return "Warning";
		}
		if (diff < 86400000 /* 1 day */)
		{
			return "Success";
		}

		if (diff < 2592000000 /* 30 days */)
		{
			return "Success";
		}

		return "None";
	};

	function _getDistance(oValue)
	{
		var now = Date.now();
		var toDate = new Date(oValue);

		return (toDate - now);
	}

	return module;
}(jumajumo ||
{}));