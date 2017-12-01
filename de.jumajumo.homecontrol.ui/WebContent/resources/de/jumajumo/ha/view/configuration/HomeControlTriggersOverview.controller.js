sap.ui.controller("jumajumo.ha.view.configuration.HomeControlTriggersOverview",
{

	onInit : function()
	{
		this.view = this.getView();

		this.router = sap.ui.core.UIComponent.getRouterFor(this);
		// this.router.attachRoutePatternMatched(this._handleRouteMatched,
		// this);
	},

	goBack : function(oEvent)
	{
		this.router.navTo("homeControlConfiguration");
	},

});