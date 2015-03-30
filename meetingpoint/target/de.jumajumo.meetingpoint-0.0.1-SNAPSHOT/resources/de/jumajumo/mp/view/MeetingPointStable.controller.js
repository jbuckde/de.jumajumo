jQuery.sap.require("jumajumo.mp.util.MeetingPointFormatter");

sap.ui.controller("de.jumajumo.mp.view.MeetingPointStable",
{

	onInit : function()
	{
		this.view = this.getView();

		this.router = sap.ui.core.UIComponent.getRouterFor(this);
		this.router.attachRoutePatternMatched(this._handleRouteMatched, this);
	},

	onExit : function()
	{
		this.router.detachRoutePatternMatched(this._handleRouteMatched, this);
	},

	_handleRouteMatched : function(oEvent)
	{
		// get the path argument
		var pathArgument = oEvent.getParameter("arguments").uuid;

		alert(pathArgument);
	},

});