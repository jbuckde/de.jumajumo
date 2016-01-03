jQuery.sap.declare("jumajumo.ha.model.configuration.ConfigurationModel");

sap.ui.model.json.JSONModel.extend(
		"jumajumo.ha.model.configuration.ConfigurationModel",
		{
			loadData : function()
			{
				var that = this;

				// Data is fetched here
				jQuery.ajax(
				{
					url : "/HomeControlServer/dispatcher/configuration",
					async : true,
					dataType : "json",
					type : "GET",
					complete : function(data, sStatus)
					{
						that.setData(data);
					}

				});
			},
		});