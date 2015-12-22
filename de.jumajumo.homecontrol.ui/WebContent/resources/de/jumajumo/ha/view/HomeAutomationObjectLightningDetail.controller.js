sap.ui.controller("de.jumajumo.ha.view.HomeAutomationObjectLightningDetail",
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

	switchDoorLight : function(oEvent)
	{
		var button = oEvent.getSource();

		button.setEnabled(false);
		
		var json = '{"command" : "SENSOR_TRIGGER","parameters" : {"sensorId" : "584180e8-c2a3-41ac-9f64-f6c542179d06"}}';
		jumajumo.ha.WebSocket.connection().send(json);
		
		button.setEnabled(true);

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