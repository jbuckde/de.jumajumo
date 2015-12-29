(function(module)
{
	"use strict";

	jQuery.sap.declare("formatter.DateFormatter");

	formatter.DateFormatter =
	{};

	formatter.DateFormatter.longDateFromMills = function(lMills)
	{
		var date = new Date(lMills);
		return date.toLocaleString();
	};

	return module;
}());