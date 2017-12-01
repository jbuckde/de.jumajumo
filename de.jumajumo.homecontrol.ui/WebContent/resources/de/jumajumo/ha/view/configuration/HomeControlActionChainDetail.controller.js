sap.ui.controller(
		"jumajumo.ha.view.configuration.HomeControlActionChainDetail",
		{

			onInit : function()
			{
				this.view = this.getView();

				this.router = sap.ui.core.UIComponent.getRouterFor(this);
				this.router.attachRoutePatternMatched(this._handleRouteMatched,
						this);
			},

			_handleRouteMatched : function(oEvent)
			{
				// get the path argument
				var pathArgument = oEvent.getParameter("arguments").path;

				var sPath = "/actionChains/" + pathArgument;
				var oBindingContext = new sap.ui.model.Context(this.view
						.getModel("configuration"), sPath)
				this.view.setBindingContext(oBindingContext, "configuration");
			},

			goBack : function(oEvent)
			{
				this.router.navTo("homeControlActionChainsOverview");
			}
		});