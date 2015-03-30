jQuery.sap.declare("jumajumo.mp.util.Formatter");

var jumajumo = (function(module)
{
	"use strict";

	jumajumo.mp.util.Formatter = function()
	{
	};

	jumajumo.mp.util.Formatter.formatStableUrl = function(oValue)
	{
		var url = window.location.protocol + "//" + window.location.host
				+ window.location.pathname + "stable?uuid=" + oValue;

		return url;
	};

	return module;
}(jumajumo ||
{}));