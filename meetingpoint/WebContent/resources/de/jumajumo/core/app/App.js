jQuery.sap.declare("jumajumo.core.app.App")

jumajumo.core.app.App = function()
{
};

jumajumo.core.app.App.prototype.getStableUUID = function()
{
	return this.stableUUID;
};

jumajumo.core.app.App.prototype.setStableUUID = function(sStableUUID)
{
	this.stableUUID = sStableUUID;
};