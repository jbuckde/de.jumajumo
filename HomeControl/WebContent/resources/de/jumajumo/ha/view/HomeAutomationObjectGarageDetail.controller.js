sap.ui.controller("de.jumajumo.ha.view.HomeAutomationObjectGarageDetail",
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

	switchDoor : function(oEvent)
	{
		var button = oEvent.getSource();

		button.setEnabled(false);

		jQuery.ajax("dispatcher/trigger/opengaragedoor/activate",
		{
			complete : function(oEvent)
			{
				button.setEnabled(true);
			}
		});
	}

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