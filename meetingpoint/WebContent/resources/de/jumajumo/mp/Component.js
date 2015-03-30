jQuery.sap.declare("jumajumo.mp.Component");

sap.ui.core.UIComponent.extend("jumajumo.mp.Component",
{

	metadata :
	{
		routing :
		{
			config :
			{
				viewType : "XML",
				viewPath : "jumajumo.mp.view",
				targetControl : "mpApp",
				clearTarget : false,
				transition : "slide"
			},
			routes :
			[
			{
				pattern : "",
				name : "meetingPointList",
				view : "MeetingPointList",
				viewPath : "jumajumo.mp.view",
				viewLevel : 0,
				preservePageInSplitContainer : true,
				targetAggregation : "masterPages",
				subroutes :
				[
				{
					pattern : "meetingpoint/{path}",
					name : "meetingpoint",
					view : "MeetingPointDetail",
					viewPath : "jumajumo.mp.view",
					viewLevel : 2,
					targetAggregation : "detailPages"
				} ]

			},
			{
				pattern : "stable/{uuid}",
				name : "stable",
				view : "MeetingPointStable",
				viewPath : "jumajumo.mp.view",
				viewLevel : 2,
				targetAggregation : "detailPages"
			} ]
		}
	},

	/**
	 * !!! The steps in here are sequence dependent !!!
	 */
	init : function()
	{

		// 1. some very generic requires
		jQuery.sap.require("sap.m.routing.RouteMatchedHandler");
		jQuery.sap.require("jumajumo.mp.MyRouter");

		// 2. call overridden init (calls createContent)
		sap.ui.core.UIComponent.prototype.init.apply(this, arguments);

		// 3a. monkey patch the router
		var router = this.getRouter();
		router.myNavBack = jumajumo.mp.MyRouter.myNavBack;
		router.myNavToWithoutHash = jumajumo.mp.MyRouter.myNavToWithoutHash;

		// on none phone devices route to the empty page / keep the master page
		// on phone device
		if (!sap.ui.Device.system.phone)
		{
			router.myNavToWithoutHash("jumajumo.mp.view.Empty", "XML", false);
		}

		// 4. initialize the router
		this.routeHandler = new sap.m.routing.RouteMatchedHandler(router);
		router.initialize();

		// navigate to the stable uuid if set by the caller
		var stableUUID = jumajumo.getApp().getStableUUID();
		if (stableUUID !== undefined && stableUUID !== "")
		{
			this._openMeetingPointRequest(stableUUID);
		}

	},

	destroy : function()
	{
		if (this.routeHandler)
		{
			this.routeHandler.destroy();
		}

		// call overridden destroy
		sap.ui.core.UIComponent.prototype.destroy.apply(this, arguments);
	},

	createContent : function()
	{
		// create root view
		var oView = sap.ui.view(
		{
			id : "app",
			viewName : "jumajumo.mp.view.App",
			type : "JS",
			viewData :
			{
				component : this
			}
		});

		// load the user context
		jQuery.sap.require("jumajumo.mp.model.UserContextModel");
		var oJSONUserContextModel = new jumajumo.mp.model.UserContextModel();
		oView.setModel(oJSONUserContextModel, "usercontext");

		oJSONUserContextModel.loadData();

		// set navigation model
		// load the global data model
		jQuery.sap.require("jumajumo.mp.model.MeetingsModel");
		var oJSONDataModel = new jumajumo.mp.model.MeetingsModel();
		oView.setModel(oJSONDataModel);

		oJSONDataModel.loadData();

		// done
		return oView;
	},

	_openMeetingPointRequest : function(sUUID)
	{
		var that = this;

		var oFormFragment = sap.ui
				.xmlfragment("de.jumajumo.mp.view.MeetingPointRequest");
		oFormFragment.bindElement("/");

		jQuery.sap.require("sap.m.Dialog");
		var dialog = new sap.m.Dialog(
		{
			title : "Trefpunkteinladung",
			content : oFormFragment,
			buttons :
			[ new sap.m.Button(
			{
				text : "Annehmen",
				press : function(oEvent)
				{
					that._joinMeetingpoint(oEvent);
					dialog.close();
				}
			}), new sap.m.Button(
			{
				text : "Nein Danke",
				press : function(oEvent)
				{
					dialog.close()
				}
			}) ]
		});

		// set navigation model
		// load the global data model
		jQuery.sap.require("jumajumo.mp.model.MeetingPointRequestModel");
		var oJSONDataModel = new jumajumo.mp.model.MeetingPointRequestModel();
		dialog.setModel(oJSONDataModel);

		oJSONDataModel.loadData(sUUID);

		dialog.open();
	},

	_joinMeetingpoint : function(oEvent)
	{
		oEvent.getSource().getModel().participate();
	}
});