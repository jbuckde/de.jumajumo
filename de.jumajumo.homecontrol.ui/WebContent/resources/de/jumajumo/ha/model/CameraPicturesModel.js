jQuery.sap.declare("jumajumo.ha.model.CameraPicturesModel");

sap.ui.model.json.JSONModel.extend("jumajumo.ha.model.CameraPicturesModel",
{
	loadData : function()
	{
		var that = this;

		// Data is fetched here
		jQuery.ajax(
		{
			url : "/HomeControlCamera/dispatcher/image/collection",
			async : true,
			dataType : "json",
			type : "GET",
			success : function(data, sStatus)
			{
				that.setData(data);
			}

		});
	},

	deleteImageGroup : function(shotAt)
	{
		var that = this;

		// Data is fetched here
		jQuery.ajax(
		{
			url : "/HomeControlCamera/dispatcher/image/group/" + shotAt,
			async : false,
			type : "DELETE",

		});
	}
});