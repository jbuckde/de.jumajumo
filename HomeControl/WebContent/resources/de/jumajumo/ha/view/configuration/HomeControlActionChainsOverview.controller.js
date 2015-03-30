sap.ui.controller(
		"de.jumajumo.ha.view.configuration.HomeControlActionChainsOverview",
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

			selectActionChain : function(oEvent)
			{
				var sBindingPath = oEvent.oSource.getBindingContext(
						"configuration").getPath();

				this.router.navTo("homeControlActionChainDetail",
				{
					path : sBindingPath
							.substring(sBindingPath.lastIndexOf("/") + 1)
				});
			}
		});