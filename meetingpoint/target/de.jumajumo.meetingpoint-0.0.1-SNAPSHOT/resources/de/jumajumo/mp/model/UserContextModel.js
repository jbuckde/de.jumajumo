jQuery.sap.declare("jumajumo.mp.model.UserContextModel");

sap.ui.model.json.JSONModel.extend("jumajumo.mp.model.UserContextModel",
{

	loadData : function()
	{
		var that = this;

		// Data is fetched here
		jQuery.ajax(
		{
			url : "dispatcher/usercontext",
			async : true,
			dataType : "json",
			type : "GET",
			success : function(data)
			{
				that.setData(data);
			}
		});

	},

});