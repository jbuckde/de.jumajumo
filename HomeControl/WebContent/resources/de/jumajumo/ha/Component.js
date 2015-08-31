jQuery.sap.declare("jumajumo.ha.Component");

sap.ui.core.UIComponent.extend("jumajumo.ha.Component",
{

	metadata :
	{
		routing :
		{
			config :
			{
				viewType : "XML",
				viewPath : "jumajumo.ha.view",
				targetControl : "haApp",
				clearTarget : false,
				transition : "slide"
			},
			routes :
			[
			{
				pattern : "",
				name : "homeAutomationObjectList",
				view : "HomeAutomationObjectList",
				viewPath : "jumajumo.ha.view",
				viewLevel : 0,
				preservePageInSplitContainer : true,
				targetAggregation : "masterPages",
				subroutes :
				[
				{
					pattern : "homeAutomationObject/garage",
					name : "homeAutomationObjectGarage",
					view : "HomeAutomationObjectGarageDetail",
					viewPath : "jumajumo.ha.view",
					viewLevel : 2,
					targetAggregation : "detailPages"
				},
				{
					pattern : "homeAutomationObject/camera",
					name : "homeAutomationObjectCamera",
					view : "HomeAutomationObjectCameraDetail",
					viewPath : "jumajumo.ha.view",
					viewLevel : 2,
					targetAggregation : "detailPages"
				},
				{
					pattern : "homeControlConfiguration",
					name : "homeControlConfiguration",
					view : "HomeControlConfiguration",
					viewPath : "jumajumo.ha.view.configuration",
					viewLevel : 2,
					targetAggregation : "masterPages",
					subroutes :
					[
					{
						pattern : "homeControlDevicesOverview",
						name : "homeControlDevicesOverview",
						view : "HomeControlDevicesOverview",
						viewPath : "jumajumo.ha.view.configuration",
						viewLevel : 3,
						targetAggregation : "detailPages",
						subroutes :
						[
						{
							pattern : "homeControlDeviceDetail/{path}",
							name : "homeControlDeviceDetail",
							view : "HomeControlDeviceDetail",
							viewPath : "jumajumo.ha.view.configuration",
							viewLevel : 4,
							targetAggregation : "detailPages",
						} ]
					},
					{
						pattern : "homeControlTriggersOverview",
						name : "homeControlTriggersOverview",
						view : "HomeControlTriggersOverview",
						viewPath : "jumajumo.ha.view.configuration",
						viewLevel : 3,
						targetAggregation : "detailPages",
					},
					{
						pattern : "homeControlActionsOverview",
						name : "homeControlActionsOverview",
						view : "HomeControlActionsOverview",
						viewPath : "jumajumo.ha.view.configuration",
						viewLevel : 3,
						targetAggregation : "detailPages",
					},
					{
						pattern : "homeControlConditionsOverview",
						name : "homeControlConditionsOverview",
						view : "HomeControlConditionsOverview",
						viewPath : "jumajumo.ha.view.configuration",
						viewLevel : 3,
						targetAggregation : "detailPages",
					},
					{
						pattern : "homeControlActionChainsOverview",
						name : "homeControlActionChainsOverview",
						view : "HomeControlActionChainsOverview",
						viewPath : "jumajumo.ha.view.configuration",
						viewLevel : 3,
						targetAggregation : "detailPages",
						subroutes :
						[
						{
							pattern : "homeControlActionChainDetail/{path}",
							name : "homeControlActionChainDetail",
							view : "HomeControlActionChainDetail",
							viewPath : "jumajumo.ha.view.configuration",
							viewLevel : 4,
							targetAggregation : "detailPages",
						} ]
					}, ]
				}, ]

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
		jQuery.sap.require("jumajumo.ha.MyRouter");
		jQuery.sap.require("jumajumo.ha.WebSocket");

		// 2. call overridden init (calls createContent)
		sap.ui.core.UIComponent.prototype.init.apply(this, arguments);

		// 3a. monkey patch the router
		var router = this.getRouter();
		router.myNavBack = jumajumo.ha.MyRouter.myNavBack;
		router.myNavToWithoutHash = jumajumo.ha.MyRouter.myNavToWithoutHash;

		// on none phone devices route to the empty page / keep the master page
		// on phone device
		if (!sap.ui.Device.system.phone)
		{
			router.myNavToWithoutHash("jumajumo.ha.view.Empty", "XML", false);
		}

		// 4. initialize the router
		this.routeHandler = new sap.m.routing.RouteMatchedHandler(router);
		router.initialize();

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
			viewName : "jumajumo.ha.view.App",
			type : "JS",
			viewData :
			{
				component : this
			}
		});

		// done
		return oView;
	}

});