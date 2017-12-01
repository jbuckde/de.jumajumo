sap.ui.controller("jumajumo.ha.view.configuration.HomeControlConfiguration",
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
		this.router.navTo("homeAutomationObjectList");
	},
	goToDevicesOverview : function(oEvent)
	{
		this.router.navTo("homeControlDevicesOverview");
	},
	goToTriggersOverview : function(oEvent)
	{
		this.router.navTo("homeControlTriggersOverview");
	},
	goToActionsOverview : function(oEvent)
	{
		this.router.navTo("homeControlActionsOverview");
	},
	goToConditionsOverview : function(oEvent)
	{
		this.router.navTo("homeControlConditionsOverview");
	},
	goToActionChainsOverview : function(oEvent)
	{
		this.router.navTo("homeControlActionChainsOverview");
	},

});