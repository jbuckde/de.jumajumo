sap.ui.controller(
		"de.jumajumo.ha.view.configuration.HomeControlConditionsOverview",
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