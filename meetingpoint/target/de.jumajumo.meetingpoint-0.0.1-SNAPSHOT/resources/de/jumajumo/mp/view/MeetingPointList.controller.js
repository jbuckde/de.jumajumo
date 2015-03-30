sap.ui.controller("de.jumajumo.mp.view.MeetingPointList",
{

	onInit : function()
	{
		jQuery.sap.require("jumajumo.core.util.Geocode");

		this.view = this.getView();
		this.router = sap.ui.core.UIComponent.getRouterFor(this);
		this.router.attachRoutePatternMatched(this._handleRouteMatched, this);
	},

	_handleRouteMatched : function(evt)
	{

	},

	loadData : function(oEvent)
	{
		this.view.getModel().loadData();
	},

	doSelectMeeting : function(oEvent)
	{
		var sBindingPath = oEvent.oSource.getBindingContext().getPath();
		this.router.navTo("meetingpoint",
		{
			path : sBindingPath.substring(sBindingPath.lastIndexOf("/") + 1)
		});

		sap.ui.getCore().byId("mpApp").hideMaster();
	},

	addMeetingpoint : function(oEvent)
	{
		var that = this;

		var oFormFragment = sap.ui
				.xmlfragment("de.jumajumo.mp.view.MeetingPointEdit");
		oFormFragment.bindElement("/");

		jQuery.sap.require("sap.m.Dialog");
		var dialog = new sap.m.Dialog(
		{
			title : "Trefpunkt anlegen",
			content : oFormFragment,
			buttons :
			[ new sap.m.Button(
			{
				text : "Speichern",
				press : function(oEvent)
				{
					that.saveMeetingpoint(oEvent.getSource().getModel());
					dialog.close();
				}
			}), new sap.m.Button(
			{
				text : "Close",
				press : function(oEvent)
				{
					dialog.close()
				}
			}) ]
		});

		var modelData =
		{
			date : "",
			address : "",
			name : ""
		};
		dialog.setModel(new sap.ui.model.json.JSONModel(modelData));

		dialog.open();
	},

	saveMeetingpoint : function(oModel)
	{
		var that = this;

		var data = oModel.getData();
		data.date = new Date(data.date);

		// Data is fetched here
		jQuery.ajax(
		{
			url : "dispatcher/meetings",
			async : true,
			contentType : "application/json",
			type : "POST",
			dataType : "json",
			data : JSON.stringify(data),
			success : function(data)
			{
				that.loadData();
			}
		});

	}

});