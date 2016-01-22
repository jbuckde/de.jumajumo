sap.ui
		.controller(
				"de.jumajumo.ha.view.HomeAutomationObjectImageGroupDetail",
				{

					onInit : function()
					{
						this.view = this.getView();

						this.router = sap.ui.core.UIComponent
								.getRouterFor(this);
						this.router.attachRoutePatternMatched(
								this._handleRouteMatched, this);
					},

					_handleRouteMatched : function(oEvent)
					{
						// get the path argument
						var pathArgument = oEvent.getParameter("arguments").path;
						var sPath = "/" + pathArgument;

						var oBindingContext = new sap.ui.model.Context(
								this.view.getModel("pictures"), sPath)
						this.view
								.setBindingContext(oBindingContext, "pictures");
					},

					goBack : function()
					{
						this.router
								.navTo("homeAutomationObjectImageGroupOverview");
					},

					getImageUrl : function(filename)
					{
						return "/HomeControlCamera/dispatcher/image/image/"
								+ filename;
					},

					deleteGroup : function(oEvent)
					{
						var pathArgument = oEvent.getSource()
								.getBindingContext("pictures").getPath();
						var path = pathArgument.substring(pathArgument
								.lastIndexOf("/") + 1);

						var shotAt = oEvent.getSource().getModel("pictures")
								.getData()[path].shotAt

						oEvent.getSource().getModel("pictures")
								.deleteImageGroup(shotAt);

						this.goBack();
					}
				});