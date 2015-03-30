jQuery.sap.require("jumajumo.mp.util.MeetingPointFormatter");

sap.ui.controller("de.jumajumo.mp.view.MeetingPointDetail",
{

	onInit : function()
	{
		this.view = this.getView();

		this.router = sap.ui.core.UIComponent.getRouterFor(this);
		this.router.attachRoutePatternMatched(this._handleRouteMatched, this);
	},

	_handleRouteMatched : function(oEvent)
	{
		// get the path argument
		var pathArgument = oEvent.getParameter("arguments").path;

		var sPath = "/" + pathArgument;
		var oBindingContext = new sap.ui.model.Context(this.view.getModel(),
				sPath)
		this.view.setBindingContext(oBindingContext);
	},

	loadData : function(oEvent)
	{
		this.view.getModel().loadData();
	},

	editMeetingpoint : function(oEvent)
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

		// get the model data - remove the trabsient location-attribute
		var context = oEvent.getSource().getBindingContext();
		var modelData = oEvent.getSource().getModel().getProperty("", context);

		var editModel = new sap.ui.model.json.JSONModel(modelData);
		
		delete editModel.getData().location;
		delete editModel.getData().participants;
		delete editModel.getData().feeds;
		delete editModel.getData().currentAddress;
		
		editModel.setProperty("/date", sap.ui.core.format.DateFormat.getDateInstance(
				{
					pattern : 'yyyyMMddkkmm'
				}).format(new Date(editModel.getProperty("/date"))));
		
		// create a new dialog model
		dialog.setModel(editModel);

		dialog.open();
	},

	saveMeetingpoint : function(oModel)
	{
		var that = this;

		var data = oModel.getData();
		data.date = sap.ui.core.format.DateFormat.getDateInstance({pattern: 'yyyyMMddkkmm'}).parse(data.date);
		
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

	},

	logout : function()
	{
		location.href = "logout";
	},

	openShareActionSheet : function(oEvent)
	{
		var oButton = oEvent.getSource();

		// create action sheet only once
		if (!this._actionSheet)
		{
			this._actionSheet = sap.ui.xmlfragment(
					"jumajumo.mp.view.MeetingPointDetailShareActions", this);
			this.getView().addDependent(this._actionSheet);
		}

		this._actionSheet.openBy(oButton);
	},

	postMessage : function(oEvent)
	{
		var sValue = oEvent.getParameter("value");

		// get the meeting point uuid from model data
		var context = oEvent.getSource().getBindingContext();
		var sUuid = oEvent.getSource().getModel().getProperty("uuid", context);

		// build the post message payload
		var message =
		{
			feedMessage : sValue
		}

		var that = this;

		// ...post
		jQuery.ajax(
		{
			url : "dispatcher/meetings/" + sUuid + "/postmessage",
			async : true,
			dataType : "json",
			contentType : "application/json",
			type : "POST",
			data : JSON.stringify(message),
			success : function(data)
			{
				that.loadData();
			}
		});
	},

	trackPosition : function(oEvent)
	{
		// get the meeting point uuid from model data
		var context = oEvent.getSource().getBindingContext();
		var sUuid = oEvent.getSource().getModel().getProperty("uuid", context);

		var that = this;

		jumajumo.core.util.Geocode.getCurrentLocation(function(pos)
		{
			var message =
			{
				latitude : pos.coords.latitude,
				longitude : pos.coords.longitude
			}

			// ...post
			jQuery.ajax(
			{
				url : "dispatcher/meetings/" + sUuid + "/postgeoposition",
				async : true,
				dataType : "json",
				contentType : "application/json",
				type : "POST",
				data : JSON.stringify(message),
				success : function(data)
				{
					that.loadData();
				}
			});
		});

	}
});