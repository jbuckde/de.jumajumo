sap.ui
		.controller(
				"jumajumo.mp.view.App",
				{

					onInit : function()
					{

						var view = this.getView();

						// remember the App Control
						this.app = view.byId("mpApp");

						// subscribe to event bus
						var bus = sap.ui.getCore().getEventBus();
						bus.subscribe("nav", "to", this.navToHandler, this);
						bus.subscribe("nav", "back", this.navBackHandler, this);

						// set device model
						var oDeviceModel = new sap.ui.model.json.JSONModel(
						{
							isTouch : sap.ui.Device.support.touch,
							isNoTouch : !sap.ui.Device.support.touch,
							isPhone : sap.ui.Device.system.phone,
							isNoPhone : !sap.ui.Device.system.phone,
							listMode : "None",
							listItemType : "Active"
						});
						oDeviceModel.setDefaultBindingMode("OneWay");
						view.setModel(oDeviceModel, "device");
					},

					navToHandler : function(channelId, eventId, data)
					{
						if (data && data.id)
						{
							// lazy load view
							if (this.app.getPage(data.id) === null)
							{
								jQuery.sap.log.info("now loading page '"
										+ data.id + "'");
								this.app.addPage(sap.ui.jsview(data.id,
										"sap.m.mvc." + data.id));
							}
							// Navigate to given page (include bindingContext)
							this.app.to(data.id, data.data.context);
						} else
						{
							jQuery.sap.log
									.error("nav-to event cannot be processed. Invalid data: "
											+ data);
						}
					},

					navBackHandler : function()
					{
						this.app.back();
					}
				});