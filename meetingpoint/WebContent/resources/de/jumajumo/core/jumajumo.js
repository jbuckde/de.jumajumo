jQuery.sap.require("jumajumo.core.app.App");

var jumajumo = (function(module)
{
	"use strict";

	jumajumo.getApp = function()
	{
		return this.oApp;
	}

	jumajumo.oApp = new jumajumo.core.app.App();

	return module;

}(jumajumo ||
{}));