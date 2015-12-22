sap.ui.controller("de.jumajumo.ha.view.HomeAutomationObjectList",
{

	onInit : function()
	{
		this.view = this.getView();

		this.router = sap.ui.core.UIComponent.getRouterFor(this);
		// this.router.attachRoutePatternMatched(this._handleRouteMatched,
		// this);
	},

	navToGarage : function(oEvent)
	{
		this.router.navTo("homeAutomationObjectGarage");
	},

	navToCamera : function(oEvent)
	{
		this.router.navTo("homeAutomationObjectCamera");
	},

	navToLightning: function(oEvent)
	{
		this.router.navTo("homeAutomationObjectLightning");
	},
	
	navToConfiguration : function(oEvent)
	{
		this.router.navTo("homeControlConfiguration");
	},

// _handleRouteMatched : function(oEvent)
// {
// // get the path argument
// var pathArgument = oEvent.getParameter("arguments").path;
//
// var sPath = "/" + pathArgument;
// var oBindingContext = new sap.ui.model.Context(this.view.getModel(),
// sPath)
// this.view.setBindingContext(oBindingContext);
// },

});