jQuery.sap.declare("jumajumo.ha.model.HealthCheckModel");

sap.ui.model.json.JSONModel
		.extend(
				"jumajumo.ha.model.HealthCheckModel",
				{
					loadData : function()
					{
						var that = this;

						// setup the time that is refreshing the model every 10
						// seconds
						// if (this.timer == undefined || !this.timer)
						// {
						// this.timer = setInterval(function()
						// {
						// that.loadData()
						// }, 10000);
						// }

						// perform the health check
						this._isGarageDoorSwitchActive();

					},

					_isGarageDoorSwitchActive : function()
					{
						var that = this;

						// Data is fetched here
						jQuery
								.ajax(
								{
									url : "/HomeControlServer/dispatcher/trigger/garagedevicehealthcheck/activate",
									async : true,
									dataType : "json",
									type : "GET",
									error : function(data, sStatus, sError)
									{
										that.setProperty(
												"/garageDoorSwitchActive",
												sStatus);
									},
									success : function(data, sStatus)
									{
										that.setProperty(
												"/garageDoorSwitchActive",
												data[0].success);
									},

								});
					},
				});