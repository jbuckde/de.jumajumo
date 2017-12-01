sap.ui.controller("jumajumo.ha.view.HAImageGroupGardenOverview",
{
	cameraName : function()
	{
		return "back";
	},

	onInit : function()
	{
		this.view = this.getView();
		this.router = sap.ui.core.UIComponent.getRouterFor(this);
		this.router.attachRoutePatternMatched(this._handleRouteMatched, this);
	},

	_handleRouteMatched : function(oEvent)
	{
		this.getView().getModel("picturesGarden").loadData("back");
	},

	goBack : function(oEvent)
	{
		this.router.navTo("homeAutomationObjectList");
	},

	refreshCollection : function(oEvent)
	{
		var that = this;

		jQuery.ajax(
		{
			url : "/HomeControlCamera/dispatcher/image/" + that.cameraName()
					+ "/refreshcollection",
			async : true,
			dataType : "json",
			type : "GET",
			complete : function(data, sStatus)
			{
				that.getView().getModel("picturesGarden").loadData("back");
			}

		});
	},

	selectImageGroup : function(oEvent)
	{
		var sBindingPath = oEvent.oSource.getBindingContext("picturesGarden")
				.getPath();

		this.router.navTo("hAImageGroupGardenDetail",
		{
			path : sBindingPath.substring(sBindingPath.lastIndexOf("/") + 1)
		});
	}

});