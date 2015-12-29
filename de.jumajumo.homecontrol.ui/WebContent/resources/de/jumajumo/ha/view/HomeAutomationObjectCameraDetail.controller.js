sap.ui.controller("de.jumajumo.ha.view.HomeAutomationObjectCameraDetail",
{
	onInit : function()
	{
		this.view = this.getView();
		this.router = sap.ui.core.UIComponent.getRouterFor(this);

		jQuery.sap.require("jumajumo.ha.model.CameraPicturesModel");
		var oCameraPicturesModel = new jumajumo.ha.model.CameraPicturesModel();
		this.view.setModel(oCameraPicturesModel, "pictures");

		oCameraPicturesModel.loadData();
	},

	goBack : function(oEvent)
	{
		this.router.navTo("homeAutomationObjectList");
	},

});