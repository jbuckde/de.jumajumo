sap.ui.controller("de.jumajumo.ha.view.HomeAutomationObjectImageGroupOverview",
{
	cameraName : function()
	{
		return "door";
	},

	onInit : function()
	{
		this.view = this.getView();
		this.router = sap.ui.core.UIComponent.getRouterFor(this);
		this.router.attachRoutePatternMatched(this._handleRouteMatched, this);
	},

	_handleRouteMatched : function(oEvent)
	{
		this.getView().getModel("pictures").loadData();
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
				that.getView().getModel("pictures").loadData();
			}

		});
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