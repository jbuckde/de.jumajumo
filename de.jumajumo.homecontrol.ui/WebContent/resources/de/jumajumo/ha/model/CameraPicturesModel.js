jQuery.sap.declare("jumajumo.ha.model.CameraPicturesModel");

sap.ui.model.json.JSONModel.extend("jumajumo.ha.model.CameraPicturesModel",
{
	loadData : function(cameraName)
	{
		var that = this;

		// Data is fetched here
		jQuery.ajax(
		{
			url : "/HomeControlCamera/dispatcher/image/" + cameraName
					+ "/collection",
			async : true,
			dataType : "json",
			type : "GET",
			success : function(data, sStatus)
			{
				that.setData(data);
			}

		});
	},

	deleteImageGroup : function(shotAt, cameraName)
	{
		var that = this;

		// Data is fetched here
		jQuery.ajax(
		{
			url : "/HomeControlCamera/dispatcher/image/" + cameraName
					+ "/group/" + shotAt,
			async : false,
			type : "DELETE",

		});
	}
});