sap.ui.controller("jumajumo.ha.view.HomeAutomationObjectCameraDetail",
{
	onInit : function()
	{
		this.view = this.getView();
		this.router = sap.ui.core.UIComponent.getRouterFor(this);
	},

	goBack : function(oEvent)
	{
		this.router.navTo("homeAutomationObjectList");
	},

	selectImageGroup : function(oEvent)
	{
		var sBindingPath = oEvent.oSource.getBindingContext("pictures")
				.getPath();

		this.router.navTo("homeAutomationObjectImageGroupDetail",
		{
			path : sBindingPath.substring(sBindingPath.lastIndexOf("/") + 1)
		});
	}

});