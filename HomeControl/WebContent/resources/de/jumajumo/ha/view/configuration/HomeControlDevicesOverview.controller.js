sap.ui.controller(
		"de.jumajumo.ha.view.configuration.HomeControlDevicesOverview",
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

			selectDevice : function(oEvent)
			{
				var sBindingPath = oEvent.oSource.getBindingContext(
						"configuration").getPath();
				this.router.navTo("homeControlDeviceDetail",
				{
					path : sBindingPath
							.substring(sBindingPath.lastIndexOf("/") + 1)
				});
			}
		});