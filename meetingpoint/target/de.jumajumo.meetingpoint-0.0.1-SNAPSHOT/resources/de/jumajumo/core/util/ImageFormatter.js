jQuery.sap.declare("jumajumo.core.util.ImageFormatter");

var jumajumo = (function(module)
{
	"use strict";

	jumajumo.core.util.ImageFormatter = function()
	{
	};

	jumajumo.core.util.ImageFormatter.formatUserProfileUrl = function(oValue)
	{
		if (oValue)
		{
			return oValue;
		} else
		{
			return "resources/icons/letters/g_icon_96.png";
		}
	};

	return module;
}(jumajumo ||
{}));